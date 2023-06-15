package cl.araucana.autoconsulta.ui.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class ChangeClaveAction extends Action {
	
	private static Logger logger = Logger.getLogger(ChangeClaveAction.class);
	
	public static final String GLOBAL_FORWARD_success = "success";
	public static final String GLOBAL_FORWARD_welcome = "welcome";
	public static final String GLOBAL_FORWARD_changeClave = "changeClave";
	public static final String GLOBAL_FORWARD_changeClave2 = "changeClave2";

	public static final String GLOBAL_FORWARD_loginpage = "loginpage";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			HttpSession session = request.getSession(true);
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			String target=null;
			DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
						
			String textNewPassword = null;
			String textReNewPassword= null;
			int newPassword=0;
			int reNewPassword=0;
			
			
			if (session.getAttribute("encargadoEmpresa")!=null) {
				target=GLOBAL_FORWARD_loginpage;
				session.setAttribute("security.message","errors.security.opcionnovalida");
				session.removeAttribute("datosUsuario");
				session.removeAttribute("application.username");
				session.removeAttribute("encargadoEmpresa");
				session.removeAttribute("empresasACargo");
				return mapping.findForward(target);			
			}
						
			UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
			
			textNewPassword =(String)daf.get("newClave");
			textReNewPassword =(String)daf.get("reNewClave");
			
			// Detectando si es por pasos (caso Modulos)
			String pasostr =request.getParameter("paso");
			logger.debug("Paso: "+pasostr);
			if (pasostr!=null && !pasostr.equalsIgnoreCase("")) {
				int paso = Integer.parseInt(pasostr);
				if (paso==1) {
					// va a pedir la 2da vez
					target=GLOBAL_FORWARD_changeClave2;
					return mapping.findForward(target);
				}
			}
						
			
			if((textNewPassword==null || textNewPassword.length()==0) && (textReNewPassword==null || textReNewPassword.length()==0)) {
				target=GLOBAL_FORWARD_changeClave;
				session.setAttribute("volverA","changeClave");			
				return mapping.findForward(target);
			}
			
			if(textNewPassword.length()<4 || textReNewPassword.length()<4) {
				target=GLOBAL_FORWARD_changeClave;
				session.setAttribute("security.message","errors.security.largo.invalido");
				session.setAttribute("volverA","changeClave");			
				return mapping.findForward(target);
			}
			
			try{
				newPassword=Integer.parseInt(textNewPassword);
				reNewPassword=Integer.parseInt(textReNewPassword);
			}catch (Exception e){
				target=GLOBAL_FORWARD_changeClave;
				session.setAttribute("security.message","errors.security.formatNewClaveInvalida");
				session.setAttribute("volverA","changeClave");			
				return mapping.findForward(target);
			}
			
			if(newPassword!=reNewPassword){
				target=GLOBAL_FORWARD_changeClave;
				session.setAttribute("security.message","errors.security.claves.nuevas.no.coinciden");
				session.setAttribute("volverA","changeClave");			
				return mapping.findForward(target);
			}

			String subapp = (String)session.getAttribute("struts.subapplication");
			delegate.modificarClavePersonal(usuario.getRut(),newPassword);
			if (subapp.equalsIgnoreCase("modulo"))
				target=GLOBAL_FORWARD_welcome;
			else
				target=GLOBAL_FORWARD_success;

			return mapping.findForward(target);
	}
}
