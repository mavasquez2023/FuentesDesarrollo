package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Pablo Palacios 
 */
public class PrepareAdminProfesionalAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(PrepareAdminProfesionalAction.class);
		
		// Objeto de Permisos de la Aplicación
		//cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		//if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		String rut = request.getParameter("rut");
		String dv = request.getParameter("dv");
		String nombre = request.getParameter("nombre");
		String apepat = request.getParameter("apepat");
		String apemat = request.getParameter("apemat"); 
logger.debug("rut:"+rut);
logger.debug("dv:"+dv);
logger.debug("nombre:"+nombre);
logger.debug("apepat:"+apepat);
logger.debug("apemat:"+apemat);


		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("adminProfesionales");
		return (forward);


	}
}
