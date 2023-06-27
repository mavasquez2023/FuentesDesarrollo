package cl.araucana.cp.presentacion.struts.actions.changePassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.struts.forms.ChangePasswordActionForm;
import cl.araucana.cp.utils.ProxyLDAP;

import com.bh.talon.User;


public class ChangePasswordAction extends AppAction
{
	
	private static Logger logger = Logger.getLogger(ChangePasswordAction.class);
	
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ChangePasswordActionForm actForm = (ChangePasswordActionForm) form;
		
		logger.debug("Cambiando clave de usuario: " + usuario.getLogin());
		
		String oldPassword = actForm.getClaveActual();
		String newPassword = actForm.getNuevaClave();
		String reNewPassword = actForm.getConfirmacionNuevaClave();
		
		if( ProxyLDAP.changePassword(Integer.parseInt(usuario.getLogin()), oldPassword, newPassword, reNewPassword) ) {
			logger.debug("OK Cambio clave de usuario: " + usuario.getLogin());
			return mapping.findForward("exito");
		} else {
			logger.debug("ERROR Cambio clave de usuario: " + usuario.getLogin());
			return mapping.findForward("error");
		}
	}

}
