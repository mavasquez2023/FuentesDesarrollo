package cl.laaraucana.botonpago.def.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.botonpago.def.forms.OlvidoClaveForm;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.LdapUtil;
import cl.laaraucana.botonpago.web.utils.UtilEmail;

/**
 * @version 1.0
 * @author
 */
public class OlvidoClaveAction extends org.apache.struts.action.Action

{
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		OlvidoClaveForm solForm = (OlvidoClaveForm) form;
		String rutdeudor = solForm.getRut().toUpperCase().replace(".", "");

		UserRegistryConnection urConnection = new UserRegistryConnection();
		try {

			User user = null;
			//boolean isUserInApplication = false;
			try {
				user = urConnection.getUser(rutdeudor);
				//isUserInApplication = urConnection.isUserInApplication(rutdeudor, Constantes.getInstancia().LDAP_APLICACION);
			} catch (Exception e) {
				logger.error("usuario " + rutdeudor + " no econtrado en LDAP");
			}
		/*	if (user == null || !isUserInApplication) {
				logger.debug("El usuario " + rutdeudor + " no se encuentra registrado en la aplicación.");
				request.setAttribute("mensaje", "El usuario " + rutdeudor + " no se encuentra registrado en la aplicación.");
				return mapping.findForward("error");
			}
*/
			DatosDeudorDAO datosDao = new DatosDeudorDAO();
			String[] rutArray = rutdeudor.split("-");
			Bpagf001 datos = null;
			String emailUser = null;
			try {
				datos = datosDao.getDatoDeudorByRut(rutArray[0], rutArray[1]);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al consultar los datos del deudor " + rutdeudor + " " + e.getMessage());
				//				request.setAttribute("mensaje", "Ocurrió un error al consultar los datos del deudor.");
				//				return mapping.findForward("error");
			}
			if (datos == null) {
				emailUser = user.getEmail().trim();
			} else {
				emailUser = datos.getEmail().trim();
			}
			if (emailUser == null || emailUser.isEmpty()) {
				logger.debug("El usuario " + rutdeudor + "  no tiene correo electrónico registrado");
				request.setAttribute("mensaje", "El usuario " + rutdeudor + "  no tiene correo electrónico registrado.");
				return mapping.findForward("error");
			}
			LdapUtil ldapUtil = new LdapUtil();
			ldapUtil.changePassword(rutdeudor, rutdeudor.substring(0, 4));
			urConnection.forceUserChangePassword(rutdeudor);

			try {
				UtilEmail.sendEmailAdjunto(emailUser, "Solicitud de contraseña portal de pago deudor no afiliado La Araucana", getCuerpoMensaje(rutdeudor));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error en envio de email al deudor " + rutdeudor + " con email " + emailUser + " " + e);
				request.setAttribute("mensaje", "Ocurrió un error al enviar el correo electrónico.");
				return mapping.findForward("error");
			}
			String correo = emailUser.split("@")[0] + "@...";
			request.setAttribute("correo", correo);

			return mapping.findForward("success");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
			logger.error("Error en reinicio de clave para deudor " + rutdeudor + " " + e);
			request.setAttribute("mensaje", "Error en la solicitud, intente nuevamente");
			return mapping.findForward("error");
		} finally {
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
	}

	private String getCuerpoMensaje(String rutUsuario) throws Exception {

		String mensaje = "Estimado(a): <br><br>" + "Bienvenido a nuestro servicio de Pago De Deudores No Afiliados La Araucana CCAF." + "<br>" + "<br>"
				+ "Comunicamos la restauración de su cuenta en el sitio "
				+ Constantes.getInstancia().URL_SITIO_WEB
				+ " de La Araucana CCAF , "
				+ "su contraseña fue cambiada por: "
				+ rutUsuario.substring(0, 4)
				+ "<br>"
				+ "<br>"
				+ "Los pasos para ingresar a nuestros servicios son los siguientes:"
				+ "<br>"
				+ "<br>"
				+ "1-  Ingrese a nuestro portal <a href='"
				+ Constantes.getInstancia().URL_SITIO_WEB
				+ "'>"
				+ Constantes.getInstancia().URL_SITIO_WEB
				+ "</a><br>"
				+ "2-  Inicie sesión con la clave proporcionada."
				+ "<br>"
				+ "<br>"
				+ "<table>"
				+ "<tr><td align='center'>"
				+ "Para contactarnos:"
				+ "</td></tr>"
				+ "<tr><td align='center'>"
				+ "Call Center: "
				+ Constantes.getInstancia().TEL_CONTACTO
				+ " :: "
				+ Constantes.getInstancia().EMAIL_CONTACTO
				+ "</td></tr>" + "</table>";

		return mensaje;

	}

}
