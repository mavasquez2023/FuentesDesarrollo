package cl.laaraucana.simat.forms;

//import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class ControlDocuForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private int tipo_subsidio = 0;
	private String rut_empleador = "";
	private String nombre_empleador = "";
	private String run_beneficiario = "";
	private String nombre_beneficiario = "";
	private int mod_pago = 0;
	private String serie_documento = "";
	private String num_documento = "";
	private String fecha_emision_documento = "0001-01-01";
	private long monto_documento = 0;
	private String codigo_banco = "";
	private int estado_documento = 0;
	private String fecha_cambio_estado = "0001-01-01";

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public int getEstado_documento() {
		return estado_documento;
	}

	public void setEstado_documento(int estado_documento) {
		this.estado_documento = estado_documento;
	}

	public String getFecha_cambio_estado() {
		return fecha_cambio_estado;
	}

	public void setFecha_cambio_estado(String fecha_cambio_estado) {
		this.fecha_cambio_estado = fecha_cambio_estado;
	}

	public String getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public void setFecha_emision_documento(String fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
	}

	public int getMod_pago() {
		return mod_pago;
	}

	public void setMod_pago(int mod_pago) {
		this.mod_pago = mod_pago;
	}

	public long getMonto_documento() {
		return monto_documento;
	}

	public void setMonto_documento(long monto_documento) {
		this.monto_documento = monto_documento;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador;
	}

	public String getNum_documento() {
		return num_documento;
	}

	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento;
	}

	public String getRun_beneficiario() {
		return run_beneficiario;
	}

	public void setRun_beneficiario(String run_beneficiario) {
		this.run_beneficiario = run_beneficiario;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador;
	}

	public String getSerie_documento() {
		return serie_documento;
	}

	public void setSerie_documento(String serie_documento) {
		this.serie_documento = serie_documento;
	}

	public int getTipo_subsidio() {
		return tipo_subsidio;
	}

	public void setTipo_subsidio(int tipo_subsidio) {
		this.tipo_subsidio = tipo_subsidio;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = 0;
		mes_informacion = "";
		codigo_entidad = 0;
		tipo_subsidio = 0;
		rut_empleador = "";
		nombre_empleador = "";
		run_beneficiario = "";
		nombre_beneficiario = "";
		mod_pago = 0;
		serie_documento = "";
		num_documento = "";
		fecha_emision_documento = "0001-01-01";
		monto_documento = 0;
		codigo_banco = "";
		estado_documento = 0;
		fecha_cambio_estado = "0001-01-01";
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
