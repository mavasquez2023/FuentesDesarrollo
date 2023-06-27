package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author		Pablo Palacios 
 */
public class RegistrarProfesionalAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;			

		Logger logger = Logger.getLogger(RegistrarProfesionalAction.class);
		
		Profesional profesional = new Profesional();
		
		profesional.setRut(Carga.formateaRut((String)dynaValidatorActionForm.get("rut")));
		profesional.setDigito((String)dynaValidatorActionForm.get("dv"));
		profesional.setNombre((String)dynaValidatorActionForm.get("nombre"));
		
		logger.debug("Rut: "+profesional.getRut());
		
		ServicesCasosDelegate delegateCasos = new ServicesCasosDelegate();
		
		//Ejecuta la consulta
		delegateCasos.registrarProfesional(profesional);
		
		dynaValidatorActionForm.set("rut","");
		dynaValidatorActionForm.set("nombre","");

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaProfesionales");
		return (forward);


	}
}
