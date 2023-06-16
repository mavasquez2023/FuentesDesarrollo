package cl.araucana.fonasa.main.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.fonasa.business.impl.ProcesaBDThread;




/**
 * @version 1.0
 * @author
 */
public class ValidacionFonasaBDAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
		
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		
		request.setAttribute("menu", "fonasa");
		try {
			ProcesaBDThread procesaBD= new ProcesaBDThread();
			procesaBD.start();
			
			forward = mapping.findForward("success");
			return (forward);

		} catch (Exception e) {
			logger.error("Error en Validación BD licencias:" + e.getMessage());
							
		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	
	    
	    
}
