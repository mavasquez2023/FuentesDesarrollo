package cl.laaraucana.simat.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class KeyPaginacionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tabla;
	private int inicio;
	private int fin;
	private int minId;
	private int maxId;

	public int getFin() {
		return fin;
	}

	public int getInicio() {
		return inicio;
	}

	public int getMaxId() {
		return maxId;
	}

	public int getMinId() {
		return minId;
	}

	public String getTabla() {
		return tabla;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		tabla = "";
		inicio = 0;
		fin = 0;
		minId = 0;
		maxId = 0;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
}
