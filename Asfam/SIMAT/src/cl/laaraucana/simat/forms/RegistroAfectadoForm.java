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
public class RegistroAfectadoForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tabla_afectada = "";
	private String registro_afectado = "";

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getRegistro_afectado() {
		return registro_afectado;
	}

	public void setRegistro_afectado(String registro_afectado) {
		this.registro_afectado = registro_afectado;
	}

	public String getTabla_afectada() {
		return tabla_afectada;
	}

	public void setTabla_afectada(String tabla_afectada) {
		this.tabla_afectada = tabla_afectada;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		tabla_afectada = "";
		registro_afectado = "";
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
