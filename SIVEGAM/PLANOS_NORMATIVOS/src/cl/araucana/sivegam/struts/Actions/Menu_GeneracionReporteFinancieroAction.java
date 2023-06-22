package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;

/**
 * @version 	1.0
 * @author
 */
public class Menu_GeneracionReporteFinancieroAction extends Action
{
	SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();
	private String error = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			//Comprobación sesión de usuario siga activa.
			HttpSession session = request.getSession();
	        String usuarioLogeado = (String) session.getAttribute("IDAnalista");
	        
	        if (usuarioLogeado == null) {
	        	logger.debug("Usuario no se ha acreditado de manera correcta.");
	        	error = "Sesion cerrada. Por favor vuelva a ingresar.";
	            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
	            return mapping.findForward("logout");
	        }
	        
	        //Búsqueda fecha actual del sistema y carga página jsp.
	        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
	        int a = 0;
	        if (String.valueOf(34404).equals(fechaSistema)) {
	            a = 34404;
	        }
	        if (a != 0) {
	        	error = "Ha ocurrido un error al conectar con el servidor de Base de Datos. " +
		            	"Comuniquese con su administrador del Sistema.";
	            session.setAttribute("Error", "Ha ocurrido un error al conectar con el servidor de Base de Datos. " +
	            	"Comuniquese con su administrador del Sistema.");
	            return mapping.findForward("login");
	        }else{
	        	 return mapping.findForward("informeFinanciero");
	        }
		} catch (Exception e) {
			request.setAttribute("Error", error);
			forward = mapping.findForward("error");
			e.printStackTrace();
		}
	// Finish with
	return (forward);

    }
}
