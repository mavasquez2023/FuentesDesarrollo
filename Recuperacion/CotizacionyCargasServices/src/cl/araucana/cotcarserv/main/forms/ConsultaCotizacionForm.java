package cl.araucana.cotcarserv.main.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>rut - [your comment here]
 * </ul>
 * @version 	1.0
 * @author J-Factory
 */
public class ConsultaCotizacionForm extends ActionForm

{
	private static final long serialVersionUID = -245035565385424671L;
	private String rutEmpresa = null;
	private int numeroMeses = 1;
	

    /**
     * Get rutEmpresa
     * @return String
     */
    public String getRutEmpresa() {
	return rutEmpresa;
    }

    /**
     * Set numeroMeses
     * @param <code>int</code>
     */
    public void setRutEmpresa(String r) {
	this.rutEmpresa = r;
    }
    
    

	/**
	 * @return the numeroMeses
	 */
	public int getNumeroMeses() {
		return numeroMeses;
	}

	/**
	 * @param numeroMeses the numeroMeses to set
	 */
	public void setNumeroMeses(int numeroMeses) {
		this.numeroMeses = numeroMeses;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	rutEmpresa = null;
	numeroMeses= 1;
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.

	// if ((field == null) || (field.length() == 0)) {
	//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
	// }
	return errors;

    }
}
