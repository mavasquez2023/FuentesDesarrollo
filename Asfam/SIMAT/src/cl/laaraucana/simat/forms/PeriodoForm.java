package cl.laaraucana.simat.forms;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class PeriodoForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id = 0;
	private String estado = "";
	private String periodo = "";
	private Date fecha_hora = null;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = 0;
		estado = "";
		periodo = "";
		fecha_hora = null;
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
