package cl.laaraucana.claves.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.claves.utils.Configuraciones;


public class InitAction extends Action {
	private static Logger logger = Logger.getLogger("InitAction");
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 

		String keyCaptcha= Configuraciones.getConfig("servicios.recaptcha.key");
		request.setAttribute("recaptcha", keyCaptcha);

		forward = mapping.findForward("success");
		return forward;
	}

}
