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
public class DatosLicCobForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private String nro_licencia = "";
	private String run_beneficiario = "";
	private String nombre_beneficiario = "";
	private int edad = 0;
	private String sexo = "";
	private String fecha_emision_licencia = "0001-01-01";
	private String fecha_inicio_reposo = "0001-01-01";
	private String fecha_termino_reposo = "0001-01-01";
	private int nro_dias_licencia = 0;
	private int num_dias_licencia_autorizados = 0;
	private String run_menor_enfermo = "";
	private String nombre_menor_enfermo = "";
	private String fecha_nac_menor_enfermo = "0001-01-01";
	private int cod_tipo_licencia = 0;
	private String cod_comuna_beneficiario = "";
	private String run_profesional = "";
	private String nombre_profesional = "";
	private String registro_colegio_profesional = "";
	private String codigo_diagnostico = "";
	private String rut_empleador = "";
	private String nombre_empleador = "";
	private int calidad_trabajador = 0;

	public int getNum_dias_licencia_autorizados() {
		return num_dias_licencia_autorizados;
	}

	public void setNum_dias_licencia_autorizados(int num_dias_licencia_autorizados) {
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCalidad_trabajador() {
		return calidad_trabajador;
	}

	public void setCalidad_trabajador(int calidad_trabajador) {
		this.calidad_trabajador = calidad_trabajador;
	}

	public int getCod_tipo_licencia() {
		return cod_tipo_licencia;
	}

	public void setCod_tipo_licencia(int cod_tipo_licencia) {
		this.cod_tipo_licencia = cod_tipo_licencia;
	}

	public String getCodigo_diagnostico() {
		return codigo_diagnostico;
	}

	public void setCodigo_diagnostico(String codigo_diagnostico) {
		this.codigo_diagnostico = codigo_diagnostico;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFecha_emision_licencia() {
		return fecha_emision_licencia;
	}

	public void setFecha_emision_licencia(String fecha_emision_licencia) {
		this.fecha_emision_licencia = fecha_emision_licencia;
	}

	public String getFecha_inicio_reposo() {
		return fecha_inicio_reposo;
	}

	public void setFecha_inicio_reposo(String fecha_inicio_reposo) {
		this.fecha_inicio_reposo = fecha_inicio_reposo;
	}

	public String getFecha_nac_menor_enfermo() {
		return fecha_nac_menor_enfermo;
	}

	public void setFecha_nac_menor_enfermo(String fecha_nac_menor_enfermo) {
		this.fecha_nac_menor_enfermo = fecha_nac_menor_enfermo;
	}

	public String getFecha_termino_reposo() {
		return fecha_termino_reposo;
	}

	public void setFecha_termino_reposo(String fecha_termino_reposo) {
		this.fecha_termino_reposo = fecha_termino_reposo;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
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

	public String getNombre_menor_enfermo() {
		return nombre_menor_enfermo;
	}

	public void setNombre_menor_enfermo(String nombre_menor_enfermo) {
		this.nombre_menor_enfermo = nombre_menor_enfermo;
	}

	public String getNombre_profesional() {
		return nombre_profesional;
	}

	public void setNombre_profesional(String nombre_profesional) {
		this.nombre_profesional = nombre_profesional;
	}

	public int getNro_dias_licencia() {
		return nro_dias_licencia;
	}

	public void setNro_dias_licencia(int nro_dias_licencia) {
		this.nro_dias_licencia = nro_dias_licencia;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia;
	}

	public String getRegistro_colegio_profesional() {
		return registro_colegio_profesional;
	}

	public void setRegistro_colegio_profesional(String registro_colegio_profesional) {
		this.registro_colegio_profesional = registro_colegio_profesional;
	}

	public String getRun_beneficiario() {
		return run_beneficiario;
	}

	public void setRun_beneficiario(String run_beneficiario) {
		this.run_beneficiario = run_beneficiario;
	}

	public String getRun_menor_enfermo() {
		return run_menor_enfermo;
	}

	public void setRun_menor_enfermo(String run_menor_enfermo) {
		this.run_menor_enfermo = run_menor_enfermo;
	}

	public String getRun_profesional() {
		return run_profesional;
	}

	public void setRun_profesional(String run_profesional) {
		this.run_profesional = run_profesional;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCod_comuna_beneficiario() {
		return cod_comuna_beneficiario;
	}

	public void setCod_comuna_beneficiario(String cod_comuna_beneficiario) {
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.
		id = 0;
		mes_informacion = "";
		codigo_entidad = 0;
		nro_licencia = "";
		run_beneficiario = "";
		nombre_beneficiario = "";
		edad = 0;
		sexo = "";
		fecha_emision_licencia = "0001-01-01";
		fecha_inicio_reposo = "0001-01-01";
		fecha_termino_reposo = "0001-01-01";
		nro_dias_licencia = 0;
		num_dias_licencia_autorizados = 0;
		run_menor_enfermo = "";
		nombre_menor_enfermo = "";
		fecha_nac_menor_enfermo = "0001-01-01";
		cod_tipo_licencia = 0;
		cod_comuna_beneficiario = "";
		run_profesional = "";
		nombre_profesional = "";
		registro_colegio_profesional = "";
		codigo_diagnostico = "";
		rut_empleador = "";
		nombre_empleador = "";
		calidad_trabajador = 0;
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
