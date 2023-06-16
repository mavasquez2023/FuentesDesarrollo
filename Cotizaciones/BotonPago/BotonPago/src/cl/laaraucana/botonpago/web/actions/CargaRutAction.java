package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserNotFoundUserRegistryException;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.Rut;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * @version 1.0
 * @author
 */
public class CargaRutAction extends Action {

	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserRegistryConnection urConnection = new UserRegistryConnection();
		try {

			HttpSession session = request.getSession();

			String rut = (String) request.getParameter("rut").replace(".", "").toUpperCase();
			//User user = urConnection.getUser(rut);

			/*@SuppressWarnings("unchecked")
			 ArrayList<String> roles = new ArrayList<String>(urConnection.getUserRoles(rut, Constantes.getInstancia().LDAP_APLICACION));
			boolean isDeudor= isDeudor(rut);
			if(isDeudor){
				roles.add("deudor");
			} */
			//if (!roles.isEmpty()) {

				session.setAttribute("rutDeudor", rut);
				session.setAttribute("nombreDeudor", "");

				session.removeAttribute("listaCreditos");

				request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_EXITO);
				request.setAttribute("titulo", "Mensaje");
				request.setAttribute("mensaje", "El usuario " + rut + " se cargó correctamente.");
				logger.info("El usuario " + rut + " se cargó correctamente.");
				return mapping.findForward("MensajeForward");

			/*} else {
				request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
				request.setAttribute("titulo", "Mensaje");
				request.setAttribute("mensaje", "El usuario " + rut + " no existe en la aplicación");
				logger.info("El usuario " + rut + " no existe en la aplicación");
				return mapping.findForward("MensajeForward");

			}*/

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "No se pudo cargar el usuario " + e.getMessage());
			logger.error("No se pudo cargar el usuario " + e.getMessage());
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
			return mapping.findForward("MensajeForward");
		} finally {
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
	}
	
}
