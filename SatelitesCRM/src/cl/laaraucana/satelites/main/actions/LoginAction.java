package cl.laaraucana.satelites.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.laaraucana.satelites.main.VO.UsuarioVO;
import cl.laaraucana.satelites.main.dao.UsuarioDAO;
import cl.laaraucana.satelites.main.forms.LoginForm;

/**
 * @version 1.0
 * @author
 */
public class LoginAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages errors = new ActionMessages();
		ActionForward forward = new ActionForward(); // return value
		LoginForm loginForm = (LoginForm) form;

		String mobile = (String) ((request.getAttribute("esMobile")== null)?"":request.getAttribute("esMobile"));
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			UsuarioVO usuario = usuarioDAO.getUsuario(loginForm.getRut().replace(".", ""),loginForm.getClave());
						
			
			if (usuario != null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				request.setAttribute("title", "Iniciar sesión");
				
				String actionAnterior = (String) sesion.getAttribute("actionAnterior");
				if(actionAnterior != null){
					sesion.removeAttribute("actionAnterior");
					logger.error("=========================================");
					logger.error("redirecciona a "+actionAnterior);
					
					return new ActionForward(actionAnterior); 
				}
				
				forward = mapping.findForward("success"+mobile);
			} else {

				errors.add("login", new ActionMessage(
						"error.main.usuario.noexistente"));
				saveErrors(request, errors);
				forward = mapping.findForward("error"+mobile);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			forward = mapping.findForward("error"+mobile);
		}
		return (forward);
	}
}
