package cl.laaraucana.resultadonrp.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.resultadonrp.threads.DisponibilizacionThread;


/**
 * @version 1.0
 * @author
 */
public class ResumenDisponibilizacionAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		
		DisponibilizacionThread disponibilizar= new DisponibilizacionThread();
		disponibilizar.start();
		
		forward = mapping.findForward("success");
		return (forward);
	}
	    
}
