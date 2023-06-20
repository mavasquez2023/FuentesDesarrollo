package cl.laaraucana.silmsil.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class DescargaList_Form extends ActionForm{
	
	private String rutaOrigenZip;
	private String nombreZip;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		nombreZip = "";
		rutaOrigenZip="";
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
    }

	public String getNombreZip() {
		return nombreZip;
	}

	public void setNombreZip(String nombreZip) {
		this.nombreZip = nombreZip;
	}

	public String getRutaOrigenZip() {
		return rutaOrigenZip;
	}

	public void setRutaOrigenZip(String rutaOrigenZip) {
		this.rutaOrigenZip = rutaOrigenZip;
	}
    
	
	
}//end class DescargaList_Form
