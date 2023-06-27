package cl.araucana.spl.actions.pagobbv;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.UtilesNotificacion;
import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBbv;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.mgr.PagoBbvManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.XmlHelper;

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

	PagoBbvManager pagoBbvManager = new PagoBbvManager();

	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String transaccion = req.getParameter("transaccion");
		String fecha = req.getParameter("fecha");
		String monto = req.getParameter("monto");
		String estado = req.getParameter("estado");
		String mensaje = req.getParameter("mensaje");

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyyMMdd");

		if (logger.isInfoEnabled()) {
			logger.info("Recibiendo notificacion. transaccion = " + transaccion + ", fecha = " + fecha + ", monto = " + monto + ", estado = " + estado + ", mensaje = " + mensaje);
		}

		boolean pagado = false;
		TransaccionBbv trxBD = null;
		BigDecimal idPago = null;
		String sistemaOrigen = null;
		try {

			validaEntrada(transaccion, fecha, monto, estado, mensaje);

			DaoConfig.startTransaction();

			PagoBbvManager mgr = new PagoBbvManager();
			trxBD = mgr.getTransaccionByCodigoIdTrx(transaccion);

			if (trxBD != null) {
				idPago = trxBD.getPago().getId();
				sistemaOrigen = trxBD.getPago().getSistema().getCodigo();

				TransaccionBbv transaccionBbv = new TransaccionBbv();

				transaccionBbv.setPago(new Pago());
				transaccionBbv.setCodigoIdTrx(transaccion);
				transaccionBbv.setFechaTransaccion(formatoDelTexto.parse(fecha));
				transaccionBbv.setCodRetorno(new BigDecimal(estado));
				transaccionBbv.setDescRetorno(mensaje);

				boolean validaTrx = validaTrxNotificacion(transaccionBbv, trxBD);
				boolean checkEstadoNotificacion = validaEstadoNotifTrxBanco(transaccionBbv, trxBD);

				if (validaTrx && checkEstadoNotificacion) {
					pagado = mgr.notificaPago(new BigDecimal(transaccionBbv.getCodigoIdTrx()), transaccionBbv.getPago().getFechaTransaccion(), new BigDecimal(monto), estado, mensaje);
				} else if (!checkEstadoNotificacion) {
					notificacionNOk(transaccionBbv, trxBD);
				}

				DaoConfig.commitTransaction();
				enviarRespuestaBanco(resp, pagado);

				avisarResultadoOrigen(trxBD.getPago().getId(), pagado);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}
		String banco = null;
		try {
			//REQ-8534 - Cursar comprobante de tesorería y registrar en libro de banco
			if (pagado) {
				banco = Constants.MEDIO_CODIGO_BBV;
				CursarOperacionManager curse = new CursarOperacionManager();
				curse.cursarPago(trxBD.getPago(), banco);
			} else {
				logger.debug("Resultado de pago no exitoso, no se realizará curse de comprobante ni registro en libro de banco");
			}
		} catch (Exception e) {
			logger.debug("Error al cursar el comprobante y registrar en librobanco " + e);
			String mens = "Error al cursar el comprobante y registrar en librobanco... ,MedioPago: " + banco + " idPago: " + idPago + " /sistemaOrigen: " + sistemaOrigen;
			mens = mens + ": " + e.getMessage();
			logger.error(mens, e);
			MailSender.sendError(mens, e);
		}

		return null;
	}

	/**
	 * Valida los parametros de entrada enviados del banco
	 */
	private void validaEntrada(String transaccion, String fecha, String monto, String estado, String mensaje) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			if (transaccion == null || new BigDecimal(transaccion).longValue() <= 0) {
				throw new IllegalArgumentException(" ID Transaccion invalido: " + transaccion);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ID Transaccion invalido: " + transaccion + ": " + e);
		}

		if (fecha == null) {
			try {
				df.parse(fecha);
			} catch (ParseException e) {
				throw new IllegalArgumentException("fecha invalida: " + fecha + ": " + e);
			}
		}

		try {
			if (monto == null || new BigDecimal(monto).longValue() <= 0) {
				throw new IllegalArgumentException(" ID monto invalido: " + monto);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ID monto invalido: " + monto + ": " + e);
		}

		if (estado == null || !estado.trim().equals("000")) {
			throw new IllegalArgumentException("fecha estado invalido: " + estado);
		}
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

	/**
	 * Valida los datos de la tabla transaccion .. comprueba que la transaccion exista y que no esta pagada.
	 */
	protected boolean validaTrxNotificacion(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaTrxNotificacion BBV");
			result = pagoBbvManager.validaTrxNotificacion((TransaccionBbv) trxBD, (TransaccionBbv) trx);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaTrxNotificacion: " + trx, e);
		}
		return result;
	}

	/**
	 * Valida la transaccion del banco y la transaccion bd
	 */
	protected boolean validaEstadoNotifTrxBanco(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaEstadoNotifTrxBanco BBV");

			result = pagoBbvManager.checkCodRetornoTrx((TransaccionBbv) trx, (TransaccionBbv) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaEstadoNotifTrxBanco: " + trx, e);
		}
		return result;
	}

	protected void notificacionOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionOk BBV");

			pagoBbvManager.notificacionBancoOk((TransaccionBbv) trx, (TransaccionBbv) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionOk: " + trx, e);
		}
	}

	protected void notificacionNOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionNOk BBV");

			pagoBbvManager.notificacionBancoNOk((TransaccionBbv) trx, (TransaccionBbv) trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionNOk: " + trx, e);
		}
	}

}