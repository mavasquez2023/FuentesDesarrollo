package cl.araucana.tesoreria.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cl.araucana.tesoreria.modelo.Usuario;
import cl.araucana.tesoreria.util.WebUtils;

public class CrearUsuarioAction extends org.apache.struts.action.Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		//HttpSession session = request.getSession(true);
		String cmd = request.getParameter("_cmd");
		String target = "inicio"; // default
		try {
			if (cmd.equals("crear")) {
				String rutUsuario = request.getParameter("rutUsuario");
				String perfilUsuario = request.getParameter("perfilUsuario");
				Usuario usuario = new Usuario();
				usuario.setRut(WebUtils.getCuerpoRut(rutUsuario).toString());
				usuario.setDv(WebUtils.getDigitoRut(rutUsuario).toString());
				usuario.setRol(perfilUsuario.toUpperCase());
				usuario.setEstado("A");
				WebUtils.setMessage(request, "message.usuarioCreado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			WebUtils.setError(request, "message.errorUsuarioCreado");
		}
		if (cmd == null || cmd.equals("")) {
			target = "inicio"; // default
		} else if (cmd.equalsIgnoreCase("resultado")) {
			target = "resultado"; // default
		}
		return mapping.findForward(target);
	}
}
