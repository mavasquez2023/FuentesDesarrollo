package cl.laaraucana.sinacofi.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.sinacofi.utils.Configuraciones;




/**
 * @version 1.0
 * @author
 */
public class InitAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		String mensaje="";
		try {
			forward = mapping.findForward("success");
			
			String ep = Configuraciones.getConfig("ep.sinacofi");
			String usuario = Configuraciones.getConfig("servicios.sinacofi.username");
			String clave= Configuraciones.getConfig("servicios.sinacofi.pass");
			request.setAttribute("url", ep);
			request.setAttribute("usuario", usuario);
			request.setAttribute("clave", clave);
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error Init: " + e.getMessage());
			request.setAttribute("mensaje", "servicio_error");
		}
		
		return (forward);

	}
	
	    
}
