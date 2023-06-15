package cl.laaraucana.satelites.main.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.main.forms.IntegracionCrmForm;

/**
 * @version 1.0
 * @author
 */
public class CrmAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			IntegracionCrmForm formCRM = (IntegracionCrmForm) form;

			String rut = formCRM.getRut();

			HttpSession sesion = request.getSession();
			sesion.invalidate();
			sesion = request.getSession();
			
			UsuarioVO userCRM = new UsuarioVO();
			String rutCRM = request.getParameter("rut");
				logger.debug("Login Filter: Entro desde CRM");
				if (rutCRM != null) {
					try {
						userCRM.setRut(Long.parseLong(rutCRM.substring(0, rutCRM.length() - 2)));
						userCRM.setDv(rutCRM.substring(rutCRM.length() - 1, rutCRM.length()));
					} catch (Exception e) {
						// Rut no valido
						logger.debug("Sesion no iniciada");
						RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
						request.setAttribute("title", "Usuario CRM");
						request.setAttribute("errorMsg", "Rut no válido");
						rq.forward(request, response);					
					}
					userCRM.setNombre("CRM");
					sesion.setAttribute("datosUsuario", userCRM);	
			}
				
				

				request.setAttribute("rut", rut);
			forward = mapping.findForward("success");
		} catch (Exception e) {
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}
		return (forward);

	}
}
