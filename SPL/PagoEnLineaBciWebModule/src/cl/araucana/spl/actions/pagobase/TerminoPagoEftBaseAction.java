package cl.araucana.spl.actions.pagobase;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.Sistema;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoEftManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

import com.bh.talon.User;

/**
 * @version 	1.0
 * @author		malvarez
 */
public abstract class TerminoPagoEftBaseAction extends AppAction {
	private static final Logger log = Logger.getLogger(TerminoPagoEftBaseAction.class);
	
	protected abstract TransaccionEft getTransaccionBD(String codigoIdTrx) throws PagoEnLineaException; 
	protected abstract void updateTransaccionBanco(TransaccionEft trx) throws PagoEnLineaException;
	protected abstract boolean reconfirmarTransaccionBanco(TransaccionEft trx, TransaccionEft trxBD, String urlReconfirmacion) throws PagoEnLineaException;
	protected abstract String getCodigoMedio();
		
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		log.info("Inicio TerminoPago... banco: " + getCodigoMedio());
		
		boolean resultOperacion = true;
		HttpSession session = req.getSession();
		BigDecimal idPago = null;
		String sistemaOrigen = null;		

		// Desde aqui estoy en la pagina base, redirigido por el popup del banco.
		try {
			PagoEftManager pagoEftManager = new PagoEftManager();
			DaoConfig.startTransaction();
			
			log.info("TerminoPago. Redirigido desde popup. Recuperando parametros de sesion");
			TransaccionEft trx = (TransaccionEft) session.getAttribute("trx");
			String urlReconfirmacion = (String) session.getAttribute("urlReconfirmacion");
			session.removeAttribute("trx");
			session.removeAttribute("urlReconfirmacion");
			
			if (log.isDebugEnabled())
				log.debug("transaccion: " + trx);
			
			log.info("url reconfirmacion: " + urlReconfirmacion);
			if (trx == null) {
				resultOperacion = false;				
			}
			
			// Validaciones
			TransaccionEft trxBD = getTransaccionBD(trx.getCodigoIdTrx());
			if (log.isDebugEnabled())
				log.debug("BD: "+trxBD);
			
			if (trxBD != null) {
				idPago = trxBD.getPago().getId();
				sistemaOrigen = trxBD.getPago().getSistema().getCodigo();
				
				log.info("idPago: " + idPago + " / sistemaOrigen: " + sistemaOrigen);
			}

			// Si no ha habido problemas actualizar registro en BD
			if (pagoEftManager.validaTrxTermino(trxBD, trx)) {
				log.info("Pago aceptado...");
				trxBD.addDatosFinalizacion(trx);
				updateTransaccionBanco(trxBD);
				
			} else {
				// Caso Reconfirmacion de Pagos
				resultOperacion = reconfirmarTransaccionBanco(trx, trxBD, urlReconfirmacion); 
				
				log.info("Resultado reconfirmacion: " + resultOperacion);
			}

			//Seteos antes de volver al sistema cliente
			setsTermino(req, trxBD);
			
			DaoConfig.commitTransaction();
			
			log.info("Fin TerminoPago... banco: " + getCodigoMedio());
			return mapping.findForward("termino");

		} catch (Exception ex) {
			String mensaje = "El error se produjo al finalizar el pago (TerminoPago)... idPago: " + idPago;
			mensaje = mensaje + ": " + ex.getMessage();
			log.error(mensaje+": "+ex.getMessage());
			
			MailSender.sendError(mensaje, ex);
			throw ex;	
		} finally {
			DaoConfig.endTransaction();
		}
	}
	
	/**
	 * Seteos necesarios para volver al sistema cliente.
	 * @param request
	 * @param trxBD
	 * @throws PagoEnLineaException
	 * @throws TripleDesEngineException
	 */
	private void setsTermino(HttpServletRequest request, TransaccionEft trxBD)
		throws PagoEnLineaException, TripleDesEngineException {
		
		BigDecimal idPago = new BigDecimal(0);
		SistemaManager sisManager = new SistemaManager();
		PagoManager pagoMgr = new PagoManager();
		idPago = trxBD.getPago().getId();
		
		Pago pago = pagoMgr.getPagoById(idPago);
		Sistema sistema = sisManager.getSistemaByPago(pago.getId());
		log.debug("url retorno: " + pago.getUrlRetornoOrigen());
		request.setAttribute("urlRetorno", pago.getUrlRetornoOrigen());

		TripleDesEngine ecipher = new TripleDesEngine();
		SimpleEncoder encoder = new SimpleEncoder();
		CryptResult result = ecipher.encrypt(sistema.getClave(), String.valueOf(pago.getId()), Constants.CHARSET);
		
		request.getSession().setAttribute("idPago", pago.getId().toString()); //Para Consulta termino pago
		request.setAttribute("trx", encoder.bytesToHex(result.getCrypted()));
		request.setAttribute("vector", encoder.bytesToHex(result.getIvector()));		
	}
	
}