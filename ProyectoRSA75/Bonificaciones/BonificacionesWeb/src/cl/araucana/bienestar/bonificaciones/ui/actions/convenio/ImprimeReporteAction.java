package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class ImprimeReporteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String destino=request.getParameter("destino");
		if(destino==null) destino="";

		String target="failed";
		
		if(destino.equals("resumenMovimientos")){
			target="resumenMovimientos";			 
		}

		request.setAttribute("print","ON");

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
