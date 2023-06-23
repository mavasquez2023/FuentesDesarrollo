package cl.araucana.spl.actions.pagobci;

import java.io.IOException;
import java.math.BigDecimal;

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
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.mgr.CursarOperacionManager;
import cl.araucana.spl.mgr.PagoBciManager;
import cl.araucana.spl.util.MailSender;

import com.bh.talon.User;
import com.ibatis.dao.client.DaoManager;

/**
 * Servlet encargado de recibir la notificacion de pago en tiempo real.
 * Se llama usando el comando registrado en la configuracion del CGI.
 * 
 * @param trx El identificador del pago
 * @param estado El estado de la transaccion (ver tabla en docs. de BCI)
 *
 **/
public class NotificacionPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);

	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String idPago = req.getParameter("trx");
		String estado = req.getParameter("estado");
		if (logger.isInfoEnabled()) {
			logger.info("Recibiendo notificacion. Pago = " + idPago + ", estado = " + estado);
		}

		if (!validaDireccionOrigen(req, resp)) {
			return null;
		}

		boolean pagado = false;
		try {
			DaoConfig.startTransaction();

			validaEntrada(idPago, estado);
			PagoBciManager mgr = new PagoBciManager();
			pagado = mgr.notificaPago(new BigDecimal(idPago), estado);

			plainResponse(resp, "Notificacion de pago recibida. pago=" + idPago + ", estado=" + estado);

			DaoConfig.commitTransaction();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}
		String sistema = null;
		String banco = null;
		try {
			if (pagado) {
				//REQ-8534 - Cursar comprobante de tesorería y registrar en libro de banco
				banco = Constants.MEDIO_CODIGO_BCI;
				DaoManager daoManager = DaoConfig.getDaoManager();
				PagoDAO pagoDAO = (PagoDAO) daoManager.getDao(PagoDAO.class);
				Pago pago = pagoDAO.findPagoById(new BigDecimal(idPago));
				CursarOperacionManager curse = new CursarOperacionManager();
				if (pago != null) {
					curse.cursarPago(pago, banco);
					sistema = pago.getSistema().getCodigo();
				} else {
					throw new Exception("No se pudo cursar el pago: No se encontró el Nro de pago enviado");
				}
			} else {
				logger.debug("Resultado de pago no exitoso, no se realizará curse de comprobante ni registro en libro de banco");
			}
		} catch (Exception e) {
			logger.debug("Error al cursar el comprobante y registrar en librobanco " + e);
			String mensaje = "Error al cursar el comprobante y registrar en librobanco... ,MedioPago: " + banco + " idPago: " + idPago + " /sistemaOrigen: " + sistema;
			mensaje = mensaje + ": " + e.getMessage();
			logger.error(mensaje, e);
			MailSender.sendError(mensaje, e);
		}
		UtilesNotificacion.notificaOrigen(new BigDecimal(idPago));

		return null;
	}

	private boolean validaDireccionOrigen(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String address = req.getRemoteAddr();
		String ips = getServlet().getServletContext().getInitParameter("ACL_URLS_NOTIFICACION_BCI");
		logger.debug("direcciones permitidas: "+ips);
		boolean found = ips.indexOf(address) >= 0;
		if (found) {
			logger.debug("Direccion permitida: " + address);
		} else {
			logger.warn("Direccion no permitida: " + address);
			plainResponse(resp, "Direccion no permitida: " + address);
		}
		return found;
	}

	private void validaEntrada(String idPago, String estado) {
		try {
			if (idPago == null || new BigDecimal(idPago).longValue() <= 0) {
				throw new IllegalArgumentException("ID de pago invalido: " + idPago);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("ID de pago invalido: " + idPago + ": " + e);
		}

		if (estado == null || estado.trim().length() == 0) {
			throw new IllegalArgumentException("Estado invalido: " + estado);
		}
	}
}