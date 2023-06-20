package cl.laaraucana.reportesil.actions;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * @version 1.0
 * @author
 */
public class IniciarLicenciaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {	
			request.getSession().setAttribute("licencia", null);
			request.getSession().setAttribute("listLicencias", null);
			request.setAttribute("rut", "");
			forward = mapping.findForward("init");
			
		} catch (Exception e) {
		}
		
		return (forward);

	}
	
	    
}
