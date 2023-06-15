package cl.araucana.ctasfam.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ValidaTerminosPageAction extends DispatchAction{
	
	
	public ActionForward homeDivisionPrevisional(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		 
		System.out.println("aqui43534534");
		
	return arg0.findForward("homedivision");
	}

}
