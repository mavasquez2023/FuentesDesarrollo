package cl.araucana.spl.actions.pagobase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.MailSender;

import com.bh.talon.User;

/**
 * @version 	1.0
 * @author		malvarez
 */
public abstract class SalidaPagoEftBaseAction extends AppAction {
	private static final Logger log = Logger.getLogger(SalidaPagoEftBaseAction.class);
	
	protected abstract TransaccionEft parseMensajeFinal(String mensaje) throws PagoEnLineaException;
	protected abstract String getCodigoMedio();
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		log.info("Inicio SalidaPagoAction... banco: " + getCodigoMedio());
		HttpSession session = req.getSession();
		
		try {
			// Vengo desde el banco: Recibo los parametros, los almaceno 
			//en sesion y el jsp cierra el popup y redirige en pagina padre.
			String contenidoLlamada = getContenidoLlamada(req);
			log.info("El contenido de la llamada es:\n"+contenidoLlamada);
			
			String mensaje = parseParameter("TX", contenidoLlamada);
			log.info(mensaje);
			
			if (mensaje == null) {
				log.warn("Mensaje vacio: Tratando de leer desde el query string...");
				mensaje = req.getParameter("TX");
				log.info("Mensaje directamente desde getParameter: " + mensaje);
			}
			
			TransaccionEft trx = parseMensajeFinal(mensaje);
			session.setAttribute("trx", trx);
			if (log.isDebugEnabled())
				log.debug(trx.toString());

			String urlReconfirmacion = parseParameter("url_reconfirmacion", contenidoLlamada);
			if (urlReconfirmacion != null) {
				session.setAttribute("urlReconfirmacion", urlReconfirmacion);
			}
			log.info("Redirigiendo a jsp de cierre de popup");
			
			log.info("Fin SalidaPagoAction... banco: " + getCodigoMedio());
			return mapping.findForward("cierraPopup");

		} catch (Exception ex) {
			String mensaje = "El error se produjo al ejecutar la Salida pago (SalidaPagoAction)";
			log.error(mensaje+": "+ex.getMessage());
			MailSender.sendError(ex.getMessage(), ex);
			throw ex;	
		}
	}
}