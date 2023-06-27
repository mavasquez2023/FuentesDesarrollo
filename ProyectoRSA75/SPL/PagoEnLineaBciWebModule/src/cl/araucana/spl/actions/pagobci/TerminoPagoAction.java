package cl.araucana.spl.actions.pagobci;

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
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;

import com.bh.talon.User;


public class TerminoPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(TerminoPagoAction.class);
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("Ejecutando termino de pago");
		
		try {
			DaoConfig.startTransaction();
			Pago pago = getPago(request);
			HttpSession sessionHttp = request.getSession();
			
			SistemaManager sisManager = new SistemaManager();
			Sistema sistema = sisManager.getSistemaByPago(pago.getId());
			logger.debug("url retorno: " + pago.getUrlRetornoOrigen());
			request.setAttribute("urlRetorno", pago.getUrlRetornoOrigen());

			TripleDesEngine ecipher = new TripleDesEngine();
			SimpleEncoder encoder = new SimpleEncoder();
			CryptResult result = ecipher.encrypt(sistema.getClave(), String.valueOf(pago.getId()), Constants.CHARSET);
			
			sessionHttp.setAttribute("idPago", pago.getId().toString());
			request.setAttribute("trx", encoder.bytesToHex(result.getCrypted()));
			request.setAttribute("vector", encoder.bytesToHex(result.getIvector()));
			
			DaoConfig.commitTransaction();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			// TODO: Manejar excepciones (email, etc)
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}

		return mapping.findForward("target");
	}
	
	private Pago getPago(HttpServletRequest request) throws PagoEnLineaException {
		BigDecimal idPago = null;
		String trx = null;
		try {
			trx = request.getParameter("trx");
			idPago = new BigDecimal(trx);
		} catch (Exception e) {
			throw new PagoEnLineaException("Transaccion no valida: " + trx);
		}
		PagoManager mgr = new PagoManager();
		Pago pago = mgr.getPagoById(idPago);
		if (pago == null) {
			throw new PagoEnLineaException("Pago no encontrado: " + idPago);
		}
		return pago;
	}
}
