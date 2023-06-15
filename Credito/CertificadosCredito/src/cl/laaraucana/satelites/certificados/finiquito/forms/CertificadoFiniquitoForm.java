package cl.laaraucana.satelites.certificados.finiquito.forms;

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
public class CertificadoFiniquitoForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5496336746502184639L;
	private String rutEmpleado;
	private String fechaFiniquito;
		
    
	public String getRutEmpleado() {
		return rutEmpleado;
	}

	public void setRutEmpleado(String rutEmpleado) {
		this.rutEmpleado = rutEmpleado;
	}

	public String getFechaFiniquito() {
		return fechaFiniquito;
	}

	public void setFechaFiniquito(String fechaFiniquito) {
		this.fechaFiniquito = fechaFiniquito;
	}	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		rutEmpleado = "";
    	fechaFiniquito = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
    	

	ActionErrors errors = new ActionErrors();
	if ((rutEmpleado == null) || (rutEmpleado.length() == 0)) {
		errors.add("rutEmpleado", new ActionMessage("errors.rut.invalido"));
	}else if(!RutUtil.IsRutValido(rutEmpleado)){
		errors.add("rutEmpleado", new ActionMessage("errors.rut.invalido"));
	}
	
	if ((fechaFiniquito == null) || (fechaFiniquito.length() == 0)) {
		errors.add("fechaFiniquito", new ActionMessage("error.finiquito.fechaFiniquito.required"));
	}else if(!FiniquitoLocalUtil.isFormatoFechaFiniquitoValida(fechaFiniquito)){
		errors.add("fechaFiniquito", new ActionMessage("error.finiquito.fechaFiniquito.formato"));
	}else if (!FiniquitoLocalUtil.isFechaFiniquitoValida(fechaFiniquito)){
		errors.add("fechaFiniquito", new ActionMessage("error.finiquito.fechaFiniquito.rango"));
	}
	
	
	return errors;

    }
}
