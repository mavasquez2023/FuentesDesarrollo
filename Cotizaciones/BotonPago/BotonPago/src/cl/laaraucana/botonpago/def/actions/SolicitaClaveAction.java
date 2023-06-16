package cl.laaraucana.botonpago.def.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.botonpago.def.forms.SolicitaClaveForm;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.manager.ManagerDeudor;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.LdapUtil;
import cl.laaraucana.botonpago.web.utils.UtilEmail;

/**
 * @version 1.0
 * @author
 */
public class SolicitaClaveAction extends org.apache.struts.action.Action {

	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SolicitaClaveForm solForm = (SolicitaClaveForm) form;
		String rutdeudor = solForm.getRut().toUpperCase().replace(".", "");
		String emailUser = solForm.getEmail();

		try {
			ManagerDeudor mgrDeu = new ManagerDeudor();
			SalidaDeudorVO salida = mgrDeu.esDeudorNoAfiliadoSapYAs400(rutdeudor);
			
			User user = null;

			if (!salida.isDeudor()) {
				request.setAttribute("mensaje", "El usuario " + rutdeudor + " no tiene deuda de crédito");
				return mapping.findForward("error");
			}

			// insert en la tabla de deudores
			
			DatosDeudorDAO dao = new DatosDeudorDAO();
			if (dao.getDatoDeudorByRut(rutdeudor.split("-")[0], rutdeudor.split("-")[1]) == null) {
				salida.setRutDeudor(rutdeudor);
				salida.setEmail(emailUser);
				Bpagf001 entradaInsert = mgrDeu.mapeoDeudor(salida);
				dao.insertDeudor(entradaInsert);
			} else {
				logger.debug("el usuario " + rutdeudor + " ya existe en tabla");
				request.setAttribute("mensaje", "El usuario ya se encuentra registrado en el portal, si no recuerda su contraseña ingrese a la opción de recuperar clave");
				return mapping.findForward("error");
			}
			UserRegistryConnection urConnection = new UserRegistryConnection();
			try {
				user = urConnection.getUser(rutdeudor);
			} catch (Exception e) {
				logger.error("usuario no existe en LDAP: " + e.getMessage());
			}
			// si usuario no existe en LDAP se debe crear
			if (user == null) {
				user = new User();
				user.setID(rutdeudor);
				user.setName(salida.getNombreDeudor());
				user.setFirstName(salida.getAppDeu());
				user.setLastName(salida.getApmDeu());
				user.setEmail(salida.getEmail());
				user.setPhone(salida.getFono1());
				user.setSex(salida.getSexoDeu().toUpperCase());
				user.setSituation("");
				user.setQuestion("");
				user.setAnswer("");
				user.setServices("");
				// se crea usuario y se asigna rol deudor la clave corresponde a los primeros 4 digitos del rut
				urConnection.createUser(user, salida.getRutDeudor().substring(0, 4));
				// Se crea usuario en LDAP
				logger.debug("El usuario " + rutdeudor + " se creo en LDAP");

			}
			
			// Asignar contraseña inicial
			LdapUtil ldap = new LdapUtil();
			ldap.changePassword(rutdeudor, rutdeudor.substring(0, 4));
			// Activar el cambio de clave obligatorio
			urConnection.forceUserChangePassword(rutdeudor);

			// Enviar correo
			try {
				UtilEmail.sendEmailAdjunto(emailUser, "Solicitud de contraseña Portal de Pago La Araucana", getCuerpoMensaje(rutdeudor));
			} catch (Exception e) {
				//urConnection.unassignAppRole(rutdeudor, Constantes.getInstancia().LDAP_APLICACION, Constantes.getInstancia().LDAP_ROL_DEUDOR);
				e.printStackTrace();
				logger.error("Error al enviar Email" + e);
				request.setAttribute("mensaje", "Error al enviar correo electrónico, intente nuevamente.");
				try {
					urConnection.close();
				} catch (Exception a) {
					a.printStackTrace();
				}
				return mapping.findForward("error");
			}

			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
			return mapping.findForward("success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al crear el usuario " + e);
			request.setAttribute("mensaje", "Error al registrar el usuario, intente nuevamente");
			return mapping.findForward("error");
		}
	}

	private String getCuerpoMensaje(String rutUsuario) throws Exception {

		String mensaje = "Estimado(a): <br><br>" + "Bienvenido a nuestro servicio de Pago para Deduores Afiliados y No Afiliados de La Araucana CCAF." + "<br>" + "<br>"
				+ "Comunicamos la apertura de su cuenta en el sitio "
				+ Constantes.getInstancia().URL_SITIO_WEB
				+ " de La Araucana CCAF , "
				+ "la primera vez que ingrese al sitio su contraseña es: "
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
				+ "<a href='http://www.previpass.cl/images/documentos/Manual_Pagos_Deudor_Directo.pdf'>Manual de Pagos Deudores</a>"
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
