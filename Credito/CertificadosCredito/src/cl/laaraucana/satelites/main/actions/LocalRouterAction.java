package cl.laaraucana.satelites.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LocalRouterAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = ""; // return value

		try {
			String tipo= request.getParameter("tipo");
			String rutemp= (String)request.getSession().getAttribute("rutEmpresa");
			String url=null;
			if(tipo==null || tipo.equals("trabajador")){
				url = request.getContextPath();
			}
			if(url != null){
				url +="/" + request.getParameter("url");
			}else{
				if(rutemp!=null){
					url = request.getParameter("url")+ "?rutEmpresa=" + rutemp;
				}
			}
			request.setAttribute("url", url);
			forward = "success";
		} catch (Exception e) {
			request.setAttribute("msg", "Error Genérico");
			forward = "error";
		}
		return (mapping.findForward(forward));
	}
}
