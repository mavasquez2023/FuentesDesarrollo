package cl.araucana.spl.actions.pagobase;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.XmlHelper;

import com.bh.talon.User;

/**
 * @version 	1.0
 * @author		malvarez
 */
public abstract class NotificacionPagoEftBaseAction extends AppAction {
	private static final Logger logger = Logger.getLogger(NotificacionPagoEftBaseAction.class);

	protected abstract String getCodigoMedio();

	protected abstract TransaccionEft getTransaccionRequest(String mensaje) throws PagoEnLineaException;

	protected abstract TransaccionEft consultaTransaccionBD(String codigoIdTrx) throws PagoEnLineaException;

	protected abstract boolean validaTrxNotificacion(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException;

	protected abstract boolean validaEstadoNotifTrxBanco(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException;

	protected abstract void notificacionOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException;

	protected abstract void notificacionNOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException;

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String banco = getCodigoMedio();
		logger.debug("Entrando a NotificacionPago del banco: " + banco);

		BigDecimal idPago = null;
		String sistemaOrigen = null;
		TransaccionEft trxBD = null;
		boolean esOK = false;
		try {
			DaoConfig.startTransaction();

			String contenidoLlamada = getContenidoLlamada(request);
			String mensaje = contenidoLlamada;
			logger.debug("El mensaje que llega NotificacionPago es: " + mensaje);

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
				logger.warn("El resultado del pago es NOK");
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
