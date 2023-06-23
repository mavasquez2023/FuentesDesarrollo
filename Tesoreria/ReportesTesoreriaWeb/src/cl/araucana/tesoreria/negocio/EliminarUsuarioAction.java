package cl.araucana.tesoreria.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cl.araucana.tesoreria.modelo.Usuario;
import cl.araucana.tesoreria.util.WebUtils;

public class EliminarUsuarioAction extends org.apache.struts.action.Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String cmd = request.getParameter("_cmd");
		String target = "inicio"; // default
		try {
			if (cmd.equals("eliminar")) {
				String rutUsuario = request.getParameter("rutUsuario");
				Usuario usuario = new Usuario();
				usuario.setRut(WebUtils.getCuerpoRut(rutUsuario).toString());
				usuario.setDv(WebUtils.getDigitoRut(rutUsuario).toString());
				WebUtils.setMessage(request, "message.usuarioEliminado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			WebUtils.setError(request, "message.errorUsuarioEliminado");
		}
		if (cmd == null || cmd.equals("")) {
			target = "inicio"; // default
		} else if (cmd.equalsIgnoreCase("resultado")) {
			target = "resultado"; // default
		}
		return mapping.findForward(target);
	}
}
