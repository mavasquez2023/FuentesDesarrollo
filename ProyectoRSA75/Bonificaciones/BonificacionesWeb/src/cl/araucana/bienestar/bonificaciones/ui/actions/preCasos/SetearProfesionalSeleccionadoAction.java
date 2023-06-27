package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * @author DESEX14
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class SetearProfesionalSeleccionadoAction extends Action {
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
				
		String target = null;
	
		dynaValidatorActionForm.set("nombre",request.getParameter("nombre"));
		dynaValidatorActionForm.set("rut",request.getParameter("rut"));
		dynaValidatorActionForm.set("dv",request.getParameter("dv"));
		
		target="datosProfesionales";
		
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
