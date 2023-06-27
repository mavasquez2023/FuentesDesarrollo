package cl.araucana.cp.presentacion.struts.actions.changePassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.presentation.base.AppAction;

import com.bh.talon.User;


public class PrepareChangePasswordAction extends AppAction
{
	
	private static Logger logger = Logger.getLogger(PrepareChangePasswordAction.class);
	
	protected ActionForward doTask(User usuario, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		logger.debug("Enviando a Cambiar Clave de usuario: " + usuario.getLogin());
		
			return mapping.findForward("changePasword");

	}

}
