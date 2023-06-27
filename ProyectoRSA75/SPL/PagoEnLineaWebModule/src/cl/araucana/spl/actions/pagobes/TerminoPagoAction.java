/*
 * @(#) TerminoPagoAction.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.pagobes;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.Sistema;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBesManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;
import cl.araucana.spl.util.crypto.TripleDesEngineException;

import com.bh.talon.User;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Se encarga de la recepción de la respuesta del banco estado
 *            cuando ha finalizado un pago. 
 *            </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Alejandro Sepúlveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class TerminoPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(TerminoPagoAction.class);
			
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		logger.info("Inicio TerminoPago... banco: " + Constants.MEDIO_CODIGO_BES);
		HttpSession session = request.getSession();
		PagoBesManager  pagoBesManager = new PagoBesManager();
		String codigoIdTrx = null;

		// Desde aqui estoy en la pagina base, redirigido por el popup del banco.
		try {

			//codigoIdTrx = request.getParameter("retorno");
			codigoIdTrx = (String)session.getAttribute("retorno");
			session.removeAttribute("retorno");
			logger.info("TerminoPago. Redirigido desde popup. Recuperando parametros de sesion codigoIdTrx: " + codigoIdTrx);
			
			// Validaciones
			TransaccionBes trxBD = pagoBesManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
			if (logger.isDebugEnabled())
				logger.debug("BD: "+trxBD);
			
			//Seteos antes de volver al sistema cliente
			setsTermino(request, trxBD);
			
			logger.info("Fin TerminoPago... banco: " + Constants.MEDIO_CODIGO_BES);
			return mapping.findForward("termino");

		} catch (Exception ex) {
			String mensaje = "El error se produjo al finalizar el pago (TerminoPago)... idPago: " + codigoIdTrx;
			mensaje = mensaje + ": " + ex.getMessage();
			logger.error(mensaje+": "+ex.getMessage());
			
			MailSender.sendError(mensaje, ex);
			throw ex;	
		}
	}
	
	/**
	 * Seteos necesarios para volver al sistema cliente.
	 * @param request
	 * @param trxBD
	 * @throws PagoEnLineaException
	 * @throws TripleDesEngineException
	 */
	private void setsTermino(HttpServletRequest request, TransaccionBes trxBD)
		throws PagoEnLineaException, TripleDesEngineException {
		
		BigDecimal idPago = new BigDecimal(0);
		SistemaManager sisManager = new SistemaManager();
		PagoManager pagoMgr = new PagoManager();
		idPago = trxBD.getPago().getId();
		
		Pago pago = pagoMgr.getPagoById(idPago);
		Sistema sistema = sisManager.getSistemaByPago(pago.getId());
		logger.debug("url retorno: " + pago.getUrlRetornoOrigen());
		request.setAttribute("urlRetorno", pago.getUrlRetornoOrigen());

		TripleDesEngine ecipher = new TripleDesEngine();
		SimpleEncoder encoder = new SimpleEncoder();
		CryptResult result = ecipher.encrypt(sistema.getClave(), String.valueOf(pago.getId()), Constants.CHARSET);
		
		request.getSession().setAttribute("idPago", pago.getId().toString()); //Para Consulta termino pago
		request.setAttribute("trx", encoder.bytesToHex(result.getCrypted()));
		request.setAttribute("vector", encoder.bytesToHex(result.getIvector()));		
	}
	
}