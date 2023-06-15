package cl.laaraucana.botonpago.def.actions;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.http.Router;
import cl.laaraucana.botonpago.def.forms.CambioClaveForm;

/**
 * 
 * @author LaAraucana
 * Cambia la password del usuario
 */
public class CambiarClaveAction extends Action{
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("Cambiando clave:");
		String forward = "success";
		CambioClaveForm form = (CambioClaveForm) _form;
		try {
			String claveInicial = form.getClaveInicial();
			String claveNueva = form.getClaveNueva();
			Principal userPrincipal = request.getUserPrincipal();
			UserRegistryConnection urConnection = null;
			String userID = userPrincipal.getName();
			logger.info("userID: " + userID);
			try {
				urConnection = new UserRegistryConnection();
				urConnection.changeUserPassword(userID, claveInicial, claveNueva);
				Router.reinject(request, userID.trim(), claveNueva.trim());
				logger.debug("Clave usuario: " + userID + ", cambiada correctamente.");
				request.setAttribute("mensaje", "Su clave ha sido cambiada exitosamente.");
				forward = "success";
			} catch (UserRegistryException e) {
				logger.debug("Error al cambiar la clave: " + e.getMessage());
				if(urConnection != null) {
					try {
						urConnection.close();
					} catch (UserRegistryException a) {
					}
				}
				logger.debug("Error al cambiar la clave de usuario: " + userID);
				request.setAttribute("mensaje", "Error al cambiar la clave, verifique su contraseña");
				forward = "error";
			}
		} catch (Exception e) {
			forward = "error";
		}
		return mapping.findForward(forward);
	}
}
