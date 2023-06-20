package cl.laaraucana.sinacofi.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import cl.laaraucana.sinacofi.business.ConsultaSinacofi;




/**
 * @version 1.0
 * @author
 */
public class ValidaSinacofiAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			forward = mapping.findForward("success");
			String rut = request.getParameter("rut");
			if(rut==null){
				return forward;
			}
			request.getSession().setAttribute("rut", rut);
			rut= rut.replaceAll("\\.", "");
			String serie = request.getParameter("serie");
			if(serie!=null){
				serie= serie.trim();
			}
			String url = request.getParameter("url");
			String usuario = request.getParameter("usuario");
			String clave = request.getParameter("clave");
			
			logger.info("Consultando Sinacofi, Rut:" + rut + ", serie=" + serie);
			ConsultaSinacofi sinacofi= new ConsultaSinacofi(url, usuario, clave);
			String mensaje= sinacofi.consultaSinacofi(rut, serie);
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("url", url);
			request.setAttribute("usuario", usuario);
			request.setAttribute("clave", clave);
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Valida RUT: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	    
}
