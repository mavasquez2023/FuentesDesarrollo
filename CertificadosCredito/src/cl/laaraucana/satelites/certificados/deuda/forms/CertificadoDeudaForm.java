package cl.laaraucana.satelites.certificados.deuda.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.certificados.finiquito.utils.FiniquitoLocalUtil;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class CertificadoDeudaForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5496336746502184639L;
	private String fechaAdmisibilidad;
		

	public String getFechaAdmisibilidad() {
		return fechaAdmisibilidad;
	}

	public void setFechaAdmisibilidad(String fechaAdmisibilidad) {
		this.fechaAdmisibilidad = fechaAdmisibilidad;
	}	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		fechaAdmisibilidad = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
    	

	ActionErrors errors = new ActionErrors();
	
	if ((fechaAdmisibilidad == null) || (fechaAdmisibilidad.length() == 0)) {
		errors.add("fechaAdmisibilidad", new ActionMessage("error.deuda.fechaAdmisibilidad.required"));
	}else if(!FiniquitoLocalUtil.isFormatoFechaFiniquitoValida(fechaAdmisibilidad)){
		errors.add("fechaAdmisibilidad", new ActionMessage("error.deuda.fechaAdmisibilidad.formato"));
	}else if (!FiniquitoLocalUtil.isFechaFiniquitoValida(fechaAdmisibilidad)){
		errors.add("fechaAdmisibilidad", new ActionMessage("error.deuda.fechaAdmisibilidad.rango"));
	}
	
	
	return errors;

    }
}
