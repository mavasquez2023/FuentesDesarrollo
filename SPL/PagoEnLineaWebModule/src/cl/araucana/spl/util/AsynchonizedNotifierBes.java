/*
 * @(#) AsynchonizedNotifierBes.java    1.0 06-10-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.UtilesNotificacion;
import cl.araucana.spl.actions.pagobes.NotificacionPagoAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.mgr.PagoBesManager;

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
 *            <TD> 06-10-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Se encarga de implementar la logica de negocio asociada a una notificación del banco.</TD>
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
public class AsynchonizedNotifierBes implements Runnable {
	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);

	PagoBesManager pagoBesManager = new PagoBesManager();

	private String mensaje = null;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public AsynchonizedNotifierBes(String msg) {
		setMensaje(msg);
	}

	public void run() {

		BigDecimal idPago = null;
		String sistemaOrigen = null;
		boolean esOK = false;
		TransaccionBes trxBD = null;
		try {
			DaoConfig.startTransaction();

			TransaccionBes trx = getTransaccionRequest(getMensaje());
			if (logger.isDebugEnabled())
				logger.debug("TransaccionRequest: " + trx.toString());

			String codigoIdTrx = trx.getId().toString();

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
				logger.debug("datos de la respuesta: " + trx.toString());
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
		} finally {
			DaoConfig.endTransaction();
		}
		String banco = null;
		try {
			//REQ-8534 - Cursar comprobante de tesorería y registrar en libro de banco
			if (esOK) {
				banco = Constants.MEDIO_CODIGO_BES;
				CursarOperacionManager curse = new CursarOperacionManager();
				curse.cursarPago(trxBD.getPago(), banco);
			} else {
				logger.debug("Resultado de pago no exitoso, no se realizará curse de comprobante ni registro en libro de banco");
			}
		} catch (Exception e) {
			logger.debug("Error al cursar el comprobante y registrar en librobanco " + e);
			String mensaje = "Error al cursar el comprobante y registrar en librobanco... ,MedioPago: " + banco + " idPago: " + idPago + " /sistemaOrigen: " + sistemaOrigen;
			mensaje = mensaje + ": " + e.getMessage();
			logger.error(mensaje, e);
			MailSender.sendError(mensaje, e);
		}

		avisarResultadoOrigen(idPago, esOK);
	}

	private TransaccionBes getTransaccionRequest(String mensaje) throws PagoEnLineaException {
		TransaccionBes trx = null;
		try {
			logger.debug("Estoy en getTransaccionRequest BES");

			//mensaje = parseParameter("xml", mensaje);
			trx = XmlHelperBes.parseMensajeNotificacion(mensaje, new TransaccionBes());

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

	private TransaccionBes consultaTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Entre a consultaTransaccionBD BES");
		TransaccionBes trx = pagoBesManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	private boolean validaTrxNotificacion(TransaccionBes trx, TransaccionBes trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaTrxNotificacion BES");
			result = pagoBesManager.validaTrxNotificacion(trxBD, trx);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaTrxNotificacion: " + trx, e);
		}
		return result;
	}

	private boolean validaEstadoNotifTrxBanco(TransaccionBes trx, TransaccionBes trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaEstadoNotifTrxBanco BES");

			result = pagoBesManager.checkCodRetornoTrx(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaEstadoNotifTrxBanco: " + trx, e);
		}
		return result;
	}

	private void notificacionOk(TransaccionBes trx, TransaccionBes trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionOk BES");

			pagoBesManager.notificacionBancoOk(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionOk: " + trx, e);
		}
	}

	private void notificacionNOk(TransaccionBes trx, TransaccionBes trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionNOk BES");

			pagoBesManager.notificacionBancoNOk(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionNOk: " + trx, e);
		}
	}

	private void avisarResultadoOrigen(BigDecimal idPago, boolean esOK) {
		if (esOK) {
			UtilesNotificacion.notificaOrigen(idPago);
		}
	}
}
