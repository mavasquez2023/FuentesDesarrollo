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
public class ReintegrosForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private String mes_nomina = "";
	private String run_beneficiario = "";
	private String nombre_beneficiario = "";
	private int tipo_subsidio = 0;
	private String nro_licencia = "";
	private String rut_empleador = "";
	private String nombre_empleador = "";
	private int origen_reintegro = 0;

	private long monto_total_a_reintegrar = 0;
	private long monto_recuperado = 0;
	private long monto_recuperado_acum = 0;
	private long total_saldo_a_reintegrar = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
	}

	public String getMes_nomina() {
		return mes_nomina;
	}

	public void setMes_nomina(String mes_nomina) {
		this.mes_nomina = mes_nomina;
	}

	public void setMonto_total_a_reintegrar(int monto_total_a_reintegrar) {
		this.monto_total_a_reintegrar = monto_total_a_reintegrar;
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

	public String getNro_licencia() {
		return nro_licencia;
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia;
	}

	public int getOrigen_reintegro() {
		return origen_reintegro;
	}

	public void setOrigen_reintegro(int origen_reintegro) {
		this.origen_reintegro = origen_reintegro;
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

	public int getTipo_subsidio() {
		return tipo_subsidio;
	}

	public void setTipo_subsidio(int tipo_subsidio) {
		this.tipo_subsidio = tipo_subsidio;
	}

	public long getMonto_recuperado() {
		return monto_recuperado;
	}

	public long getMonto_recuperado_acum() {
		return monto_recuperado_acum;
	}

	public long getMonto_total_a_reintegrar() {
		return monto_total_a_reintegrar;
	}

	public long getTotal_saldo_a_reintegrar() {
		return total_saldo_a_reintegrar;
	}

	public void setMonto_recuperado(long monto_recuperado) {
		this.monto_recuperado = monto_recuperado;
	}

	public void setMonto_recuperado_acum(long monto_recuperado_acum) {
		this.monto_recuperado_acum = monto_recuperado_acum;
	}

	public void setMonto_total_a_reintegrar(long monto_total_a_reintegrar) {
		this.monto_total_a_reintegrar = monto_total_a_reintegrar;
	}

	public void setTotal_saldo_a_reintegrar(long total_saldo_a_reintegrar) {
		this.total_saldo_a_reintegrar = total_saldo_a_reintegrar;
	}

	public void setTotal_saldo_a_reintegrar(int total_saldo_a_reintegrar) {
		this.total_saldo_a_reintegrar = total_saldo_a_reintegrar;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.
		id = 0;
		mes_informacion = "";
		codigo_entidad = 0;
		mes_nomina = "";
		run_beneficiario = "";
		nombre_beneficiario = "";
		tipo_subsidio = 0;
		nro_licencia = "";
		rut_empleador = "";
		nombre_empleador = "";
		origen_reintegro = 0;
		monto_total_a_reintegrar = 0;
		monto_recuperado = 0;
		monto_recuperado_acum = 0;
		total_saldo_a_reintegrar = 0;

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
