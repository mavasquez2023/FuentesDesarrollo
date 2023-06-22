package cl.araucana.infcottra.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.satelites.Utils.RutUtil;

public class InitAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		String rutEmpresa = request.getParameter("rutEmpresa");
		if (rutEmpresa!=null){
			if(rutEmpresa.indexOf("-")>-1){
				request.setAttribute("rutEmpresa", rutEmpresa);
			}else{
				request.setAttribute("rutEmpresa", rutEmpresa + "-" + RutUtil.obtenerDigitoVerificador(Integer.parseInt(rutEmpresa)));
			}
		}
		
		return mapping.findForward("success");
	}
}
