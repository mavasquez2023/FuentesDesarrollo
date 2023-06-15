package cl.laaraucana.simulacion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ParentAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		request.getSession();
		if (request.getAttribute("datosUsuario") == null) {
			request.setAttribute("errorMsg", "Error; Se ha terminado la sesión de usuario");
			return mapping.findForward("error");
		}
		
		try {
			
		} catch (Exception e) {
			logger.debug("Error al consultar el monto pre aprobado: " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al consultar el monto pre aprobado");
			return mapping.findForward("error");
		}
		return forward;
	}
}
