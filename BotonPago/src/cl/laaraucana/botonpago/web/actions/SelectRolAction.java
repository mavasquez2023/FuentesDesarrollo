package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import cl.araucana.core.registry.User;
import cl.laaraucana.botonpago.web.forms.RolForm;
import cl.laaraucana.botonpago.web.vo.Rol;
import cl.laaraucana.botonpago.web.vo.RolXML;

/**
 * @version 1.0
 * @author
 */
public class SelectRolAction extends DispatchAction

{
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward muestra(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Collection<String> rolesC = ((Collection<String>) session.getAttribute("roles"));
			ArrayList<Rol> roles = new ArrayList<Rol>();

			for (String r : rolesC) {
				if (r.equals("admin")) {
					roles.add(new Rol(r, "Administrador"));
				} else if (r.equals("deudor")) {
					roles.add(new Rol(r, "Deudor"));
				} else if (r.equals("ejecutivo")) {
					roles.add(new Rol(r, "Ejecutivo"));
				}
			}

			request.setAttribute("roles", roles);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener roles");
		}
		return mapping.findForward("success");

	}

	public ActionForward selecciona(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("rol");
			session.removeAttribute("permisos");
			session.removeAttribute("rutDeudor");
			session.removeAttribute("nombreDeudor");
			session.removeAttribute("listaCreditos");

			User user = (User) session.getAttribute("user");
			session.setAttribute("rutDeudor", user.getID());
			session.setAttribute("nombreDeudor", user.getFullName(true));

			// @SuppressWarnings("unchecked")
			// ArrayList<String> rolesC = new ArrayList<String>((Collection<String>)
			// session.getAttribute("roles"));

			RolForm frm = (RolForm) form;
			String rol = frm.getRol();

			if (rol == null) {
				logger.error("Error al cargar Rol = null");
				ActionErrors errors = new ActionErrors();
				errors.add("rol", new ActionMessage("error.required"));
				saveErrors(request, errors);
				return mapping.findForward("myself");
				// rol = rolesC.get(0);//por default primer rol

			}

			session.setAttribute("rol", rol);

			@SuppressWarnings("unchecked")
			ArrayList<RolXML> listaPermisos = (ArrayList<RolXML>) session.getAttribute("listaPermisos");
			for (RolXML r : listaPermisos) {
				if (r.getNombre().equals(rol)) {
					session.setAttribute("permisos", r.getOpcion());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al cargar rol del usuario");

		}
		return mapping.findForward("welcome");
	}
}
