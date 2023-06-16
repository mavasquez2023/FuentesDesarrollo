package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.core.registry.AppRole;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.forms.PermisoForm;
import cl.laaraucana.botonpago.web.manager.ManagerDeudor;
import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * @version 1.0
 * @author
 */
public class OtorgaPermisoAction extends DispatchAction {
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward busca(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//		HttpSession session = request.getSession();

		UserRegistryConnection urConnection = new UserRegistryConnection();
		try {

			//			String userid = ((User) session.getAttribute("user")).getID();
			ActionErrors errors = new ActionErrors();
			String id = request.getParameter("id").replace(".", "");

			if (id == null || id.length() == 0) {
				logger.error("id vacio");
				errors.add("rut", new ActionMessage("error.required"));
				saveErrors(request, errors);
				return mapping.findForward("error");
			}
			//			if (id.equalsIgnoreCase(userid)) {
			//				logger.error("Error, no es posible cambiar sus propios perfiles");
			//				errors.add("rut", new ActionMessage("error.perfiles"));
			//				saveErrors(request, errors);
			//				return mapping.findForward("error");
			//			}

			User user = urConnection.getUser(id);

			@SuppressWarnings("unchecked")
			ArrayList<String> roles = new ArrayList<String>(urConnection.getUserRoles(id, Constantes.getInstancia().LDAP_APLICACION));

			String admin = null;
			String ejecutivo = null;
			String deudor = null;

			for (String r : roles) {
				if (r.trim().equals(Constantes.getInstancia().LDAP_ROL_ADMIN.trim())) {
					admin = r.trim();
				} else if (r.trim().equals(Constantes.getInstancia().LDAP_ROL_DEUDOR.trim())) {
					deudor = r.trim();
				} else if (r.trim().equals(Constantes.getInstancia().LDAP_ROL_EJECUTIVO.trim())) {
					ejecutivo = r.trim();
				}
			}

			request.setAttribute("id", user.getID());
			request.setAttribute("nombre", user.getFullName(true));
			request.setAttribute("mail", user.getEmail());
			request.setAttribute("admin", admin);
			request.setAttribute("ejecutivo", ejecutivo);
			request.setAttribute("deudor", deudor);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "No se encontro el usuario");
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_INFO);
		} finally {
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
		return mapping.findForward("success");
	}

	public ActionForward ini(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PermisoForm formu = (PermisoForm) form;
		UserRegistryConnection urConnection = new UserRegistryConnection();
		try {

			String mensaje = "";
			String tipo = "";

			@SuppressWarnings("unchecked")
			Collection<AppRole> coll = urConnection.getAppRoles(Constantes.getInstancia().LDAP_APLICACION);
			ArrayList<String> allRoles = new ArrayList<String>();

			for (@SuppressWarnings("rawtypes")
			Iterator iterator = coll.iterator(); iterator.hasNext();) {
				AppRole appRole = (AppRole) iterator.next();

				allRoles.add(appRole.getID());

			}

			@SuppressWarnings("unchecked")
			ArrayList<String> oldRoles = new ArrayList<String>(urConnection.getUserRoles(formu.getId(), Constantes.getInstancia().LDAP_APLICACION));

			String[] r = formu.getRoles();

			if (r == null || r.length == 0) {
				//unasign all roles
				for (String oldRol : oldRoles) {
					urConnection.unassignAppRole(formu.getId(), Constantes.getInstancia().LDAP_APLICACION, oldRol);

					mensaje = "Los permisos para el usuario " + formu.getId() + " se actualizaron correctamente!";
					tipo = Constantes.getInstancia().MSG_TIPO_EXITO;

				}
			} else {
				ArrayList<String> newRoles = new ArrayList<String>(Arrays.asList(r));
				//asign new roles

				mensaje = "Los permisos para el usuario " + formu.getId() + " se actualizaron correctamente!";
				tipo = Constantes.getInstancia().MSG_TIPO_EXITO;

				for (String newRol : newRoles) {
					if (allRoles.contains(newRol) && !oldRoles.contains(newRol)) {
						if (newRol.equals(Constantes.getInstancia().LDAP_ROL_DEUDOR)) {
							ManagerDeudor mgrDeu = new ManagerDeudor();

							SalidaDeudorVO salida = mgrDeu.esDeudorNoAfiliadoSapYAs400(formu.getId());
							if (salida.isDeudor()) {
								urConnection.assignAppRole(formu.getId(), Constantes.getInstancia().LDAP_APLICACION, newRol);
							} else {
								mensaje = "No se puede asignar rol deudor";
								tipo = Constantes.getInstancia().MSG_TIPO_ALERTA;
							}
						} else {
							urConnection.assignAppRole(formu.getId(), Constantes.getInstancia().LDAP_APLICACION, newRol);
						}
					}
				}
				//unasign roles
				for (String oldRol : oldRoles) {
					if (!newRoles.contains(oldRol)) {
						urConnection.unassignAppRole(formu.getId(), Constantes.getInstancia().LDAP_APLICACION, oldRol);
					}
				}
			}

			logger.debug("Los permisos para el usuario " + formu.getId() + " se actualizaron correctamente!");

			request.setAttribute("mensaje", mensaje);
			request.setAttribute("titulo", "Mensaje");
			request.setAttribute("tipo", tipo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al actualizar permisos para el usuario " + formu.getId());
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "Error al actualizar permisos");
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_INFO);
		} finally {
			try {
				urConnection.close();
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
		return mapping.findForward("success");
	}
}
