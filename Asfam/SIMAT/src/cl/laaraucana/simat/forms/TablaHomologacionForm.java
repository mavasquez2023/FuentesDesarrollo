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
public class TablaHomologacionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_registro = 0;
	private int clasificacion = 0;
	private String descripcion = "";
	private String codigo_suceso = "";
	private String codigo_la = "";

	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getCodigo_la() {
		return codigo_la;
	}

	public void setCodigo_la(String codigo_la) {
		this.codigo_la = codigo_la;
	}

	public String getCodigo_suceso() {
		return codigo_suceso;
	}

	public void setCodigo_suceso(String codigo_suceso) {
		this.codigo_suceso = codigo_suceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id_registro = 0;
		clasificacion = 0;
		descripcion = "";
		codigo_suceso = "";
		codigo_la = "";
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
