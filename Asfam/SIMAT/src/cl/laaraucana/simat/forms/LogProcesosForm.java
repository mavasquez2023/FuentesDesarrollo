package cl.laaraucana.simat.forms;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class LogProcesosForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_registro = 0;
	private String tipo_log = "";
	private Timestamp fecha_hora = null;
	private String periodo = "";
	private String id_usuario = "";
	private String proceso_afectado = "";
	private String tabla = "";
	private String registro_id = "";
	private String descripcion = "";

	public String getProceso_afectado() {
		return proceso_afectado;
	}

	public void setProceso_afectado(String proceso_afectado) {
		this.proceso_afectado = proceso_afectado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getRegistro_id() {
		return registro_id;
	}

	public void setRegistro_id(String registro_id) {
		this.registro_id = registro_id;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTipo_log() {
		return tipo_log;
	}

	public void setTipo_log(String tipo_log) {
		this.tipo_log = tipo_log;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.
		id_registro = 0;
		tipo_log = "";
		fecha_hora = null;
		periodo = "";
		id_usuario = "";
		tabla = "";
		registro_id = "";
		descripcion = "";
		proceso_afectado = "";
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
