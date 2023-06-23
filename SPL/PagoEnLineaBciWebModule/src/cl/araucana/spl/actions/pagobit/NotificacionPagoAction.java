package cl.araucana.spl.actions.pagobit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.UtilesNotificacion;
import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.mgr.PagoBitManager;
import cl.araucana.spl.mgr.PagoEftManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.XmlHelper;
import cl.araucana.spl.util.XmlHelperBit;

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

public class NotificacionPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);

	PagoBitManager pagoBitManager = new PagoBitManager();

	PagoEftManager pagoEftManager = new PagoEftManager();

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BIT;
	}

	protected TransaccionEft getTransaccionRequest(String mensaje) throws PagoEnLineaException {
		TransaccionBit trx = null;
		try {
			logger.debug("Estoy en getTransaccionRequest BIT");

			//Se comenta ya que el banco itau no manda nombre de parametro solo el xml
			//String strTx = parseParameter("TX", mensaje);
			String strTx = mensaje.trim();
			if (strTx != null) {
				mensaje = strTx;
			}

			//mensaje = parseParameter("xml", mensaje);
			trx = XmlHelperBit.parseMensajeNotificacion(mensaje, new TransaccionBit());

		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (UnsupportedEncodingException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParseException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		}
		return trx;
	}

	protected TransaccionEft consultaTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Entre a consultaTransaccionBD BIT");
		TransaccionBit trx = pagoBitManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	protected boolean validaTrxNotificacion(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaTrxNotificacion BIT");
			result = pagoBitManager.validaTrxNotificacion((TransaccionBit) trxBD, (TransaccionBit) trx);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaTrxNotificacion: " + trx, e);
		}
		return result;
	}

	protected boolean validaEstadoNotifTrxBanco(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaEstadoNotifTrxBanco BIT");

			result = pagoBitManager.checkCodRetornoTrx((TransaccionBit) trx, (TransaccionBit) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaEstadoNotifTrxBanco: " + trx, e);
		}
		return result;
	}

	protected void notificacionOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionOk BIT");

			pagoBitManager.notificacionBancoOk((TransaccionBit) trx, (TransaccionBit) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionOk: " + trx, e);
		}
	}

	protected void notificacionNOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionNOk BIT");

			pagoBitManager.notificacionBancoNOk((TransaccionBit) trx, (TransaccionBit) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionNOk: " + trx, e);
		}
	}

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String banco = getCodigoMedio();
		logger.debug("Entrando a NotificacionPago del banco: " + banco);

		BigDecimal idPago = null;
		String sistemaOrigen = null;
		boolean esOK = false;
		TransaccionEft trxBD = null;
		try {
			DaoConfig.startTransaction();

			String contenidoLlamada = getContenidoLlamada(request);
			String mensaje = contenidoLlamada;
			logger.debug("El mensaje que llega NotificacionPago es: " + mensaje);

			if (mensaje == null || mensaje.equals("")) {
				//GMALLEA se recibe todos los parametros ya que el banco itau manda el xml sin parametro
				logger.debug("Mensaje vacio: Tratando de leer desde el query string...");
				mensaje = URLDecoder.decode(request.getQueryString(), "iso-8859-1");
				logger.info("Mensaje directamente desde getParameter: " + mensaje);
			}

			TransaccionEft trx = getTransaccionRequest(mensaje);
			if (logger.isDebugEnabled())
				logger.debug("TransaccionRequest: " + trx.toString());

			String codigoIdTrx = trx.getCodigoIdTrx();

			// Validaciones
			trxBD = consultaTransaccionBD(codigoIdTrx);
			if (logger.isDebugEnabled())
				logger.debug("TransaccionBD: " + trxBD);
			if (trxBD != null) {
				idPago = trxBD.getPago().getId();
				sistemaOrigen = trxBD.getPago().getSistema().getCodigo();
			}

			boolean validaTrx = validaTrxNotificacion(trx, trxBD);
			boolean checkEstadoNotificacion = validaEstadoNotifTrxBanco(trx, trxBD);

			//Update transaccion y el pago relacionado 
			if (validaTrx && checkEstadoNotificacion) {
				notificacionOk(trx, trxBD);
				esOK = true;
			} else if (!checkEstadoNotificacion) {
				notificacionNOk(trx, trxBD);
			}

			DaoConfig.commitTransaction();

		} catch (Exception ex) {
			esOK = false;
			logger.warn("Problemas recibiendo notificacion", ex);

			String mensaje = "Error al recibir NotificacionPago... idPago: " + idPago + " /sistemaOrigen: " + sistemaOrigen;
			mensaje = mensaje + ": " + ex.getMessage();
			logger.error(mensaje, ex);

			MailSender.sendError(mensaje, ex);
			// No se lanza excepcion. Siempre se responde ya sea OK o NOK al banco.
		} finally {
			DaoConfig.endTransaction();
		}

		enviarRespuestaBanco(response, esOK);

		try {
			//REQ-8534 - Cursar comprobante de tesorería y registrar en libro de banco
			if (esOK) {
				CursarOperacionManager curse = new CursarOperacionManager();
				curse.cursarPago(trxBD.getPago(), banco);
			} else {
				logger.warn("No se pudo cursar el pago: No se encontró el Nro de pago enviado");
			}
		} catch (Exception e) {
			logger.debug("Error al cursar el comprobante y registrar en librobanco " + e);
			String mensaje = "Error al cursar el comprobante y registrar en librobanco... ,MedioPago: " + banco + " idPago: " + idPago + " /sistemaOrigen: " + sistemaOrigen;
			mensaje = mensaje + ": " + e.getMessage();
			logger.error(mensaje, e);
			MailSender.sendError(mensaje, e);
		}

		avisarResultadoOrigen(idPago, esOK);

		return null;
	}

	/**
	 * Notifica al sistema de origen (tipicamente CP) del exito del pago.
	 */
	private void avisarResultadoOrigen(BigDecimal idPago, boolean esOK) {
		if (esOK) {
			UtilesNotificacion.notificaOrigen(idPago);
		}
	}

	/**
	 * OK o NOK de vuelta al banco
	 */
	private void enviarRespuestaBanco(HttpServletResponse response, boolean esOK) throws IOException {
		String mensajeRespuesta = XmlHelper.formatRespuestaNotificacion(esOK);
		logger.debug("La respuesta enviada es:\n" + mensajeRespuesta);
		sendResponse(mensajeRespuesta, response);
	}
}
