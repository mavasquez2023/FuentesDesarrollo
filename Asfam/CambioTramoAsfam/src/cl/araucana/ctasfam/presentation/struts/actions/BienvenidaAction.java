package cl.araucana.ctasfam.presentation.struts.actions;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

//--alexis advise 15-06-2012--//

public class BienvenidaAction extends Action{

	

	    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        ActionMessages errors = new ActionMessages();
	        ActionForward forward = new ActionForward(); // return value
	        String mensaje=null;
	        

	        try {
	        	
	        } catch (Exception e) {

	        	 mensaje="La sesión expiró o el sistema encontro una excepción";
	            // Report the error using the appropriate name and ID.
	            errors.add("name", new ActionMessage("id"));

	        }


	        if (!errors.isEmpty()) {
	          
	           request.setAttribute("mensaje", mensaje);
	            // Forward control to the appropriate 'failure' URI (change name as desired)
	            forward = mapping.findForward("onError");

	        } else {
	        	
	        	 
	        		 forward = mapping.findForward("menu");
	        	 
	        }

	        // Finish with
	        return (forward);

	    }
	
}
