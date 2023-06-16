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
public class UsuariosForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String nombre_usuario = "";
	private String acceso = "";
	private String ultima_coneccion = null;

	public String getAcceso() {
		return acceso;
	}

	public int getId() {
		return id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public String getUltima_coneccion() {
		return ultima_coneccion;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public void setUltima_coneccion(String ultima_coneccion) {
		this.ultima_coneccion = ultima_coneccion;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.
		id = 0;
		nombre_usuario = "";
		acceso = "";
		ultima_coneccion = null;
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
