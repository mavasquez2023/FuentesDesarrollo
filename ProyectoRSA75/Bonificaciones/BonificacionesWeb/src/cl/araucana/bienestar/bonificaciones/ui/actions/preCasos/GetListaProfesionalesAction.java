package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author		Pablo Palacios 
 */
public class GetListaProfesionalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetListaProfesionalesAction.class);
		
		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ServicesCasosDelegate delegateCasos = new ServicesCasosDelegate();

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;
	
		Profesional profesional = new Profesional();
		profesional.setRut((String)dynaValidatorActionForm.get("rutFiltro"));
		profesional.setNombre((String)dynaValidatorActionForm.get("nombreFiltro"));

		ArrayList listaProfesionales=new ArrayList();
				
		//Ejecuta la consulta
		listaProfesionales=delegateCasos.getListaProfesionales(profesional);
		request.getSession(false).setAttribute("lista.profesionales",listaProfesionales);
		logger.debug("lista.size:"+listaProfesionales.size());
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaProfesionales");
		return (forward);


	}
}
