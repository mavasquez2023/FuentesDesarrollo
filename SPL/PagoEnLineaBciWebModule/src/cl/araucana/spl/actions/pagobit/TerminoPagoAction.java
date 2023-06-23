/*
 * @(#) TerminoPagoAction.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.pagobit;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.TerminoPagoEftBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBitManager;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class TerminoPagoAction extends TerminoPagoEftBaseAction {
	private static final Logger logger = Logger.getLogger(TerminoPagoAction.class);
			
	
	private PagoBitManager  pagoBitManager = new PagoBitManager();
		
	protected TransaccionEft getTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Estoy en getTransaccionBD / BIT (codigoIdTrx: " + codigoIdTrx + ")");
		TransaccionEft trx = pagoBitManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	protected void updateTransaccionBanco(TransaccionEft trx) throws PagoEnLineaException {
		logger.debug("Estoy en updateTransaccionBanco / BIT");
		pagoBitManager.updateNotificacionTrx((TransaccionBit) trx);
	}

	protected boolean reconfirmarTransaccionBanco(TransaccionEft trx, TransaccionEft trxBD, String urlReconfirmacion) throws PagoEnLineaException {
		logger.debug("Estoy en reconfirmarTransaccionBanco / BIT");
		boolean result = false;
		try {
			result = pagoBitManager.reconfirmarPagoTermino((TransaccionBit)trx, (TransaccionBit)trxBD, urlReconfirmacion);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en reconfirmarTransaccionBanco BIT / trxBD: " + trxBD, e);
		}
		return result;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BIT;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		logger.info("Inicio TerminoPago... banco: " + Constants.MEDIO_CODIGO_BIT);
		HttpSession session = request.getSession();
		PagoBitManager  pagoBitManager = new PagoBitManager();
		String codigoIdTrx = null;

		// Desde aqui estoy en la pagina base, redirigido por el popup del banco.
		try {

			codigoIdTrx = request.getParameter("retorno");
			//codigoIdTrx = (String)session.getAttribute("retorno");
			session.removeAttribute("retorno");
			logger.info("TerminoPago. Redirigido desde popup. Recuperando parametros de sesion codigoIdTrx: " + codigoIdTrx);
			
			// Validaciones
			TransaccionBit trxBD = pagoBitManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
			if (logger.isDebugEnabled())
				logger.debug("BD: "+trxBD);
			
			//Seteos antes de volver al sistema cliente
			setsTermino(request, trxBD);
			
			logger.info("Fin TerminoPago... banco: " + Constants.MEDIO_CODIGO_BIT);
			return mapping.findForward("termino");

		} catch (Exception ex) {
			String mensaje = "El error se produjo al finalizar el pago (TerminoPago)... idPago: " + codigoIdTrx;
			mensaje = mensaje + ": " + ex.getMessage();
			logger.error(mensaje+": "+ex.getMessage());
			
			MailSender.sendError(mensaje, ex);
			throw ex;	
		}
	}*/
	
	/**
	 * Seteos necesarios para volver al sistema cliente.
	 * @param request
	 * @param trxBD
	 * @throws PagoEnLineaException
	 * @throws TripleDesEngineException
	 */
	/*private void setsTermino(HttpServletRequest request, TransaccionBit trxBD)
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
	}*/
	
}