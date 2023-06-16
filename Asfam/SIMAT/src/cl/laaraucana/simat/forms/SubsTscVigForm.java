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
public class SubsTscVigForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSubsTscVig = 0;
	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private String agencia_entidad = "";

	private String run_beneficiaria = "";
	private String nombre_beneficiaria = "";
	private int edad = 0;
	private int cod_comuna_beneficiaria = 0;
	private int actividad_laboral_trabajador = 0;
	private String nro_licencia = "";
	private String codigo_diagnostico = "";
	private int nro_nacimientos = 0;
	private Date fecha_nacimiento = null;
	private int num_dias_autorizados = 0;

	private Date fecha_inicio_subsidio = null;
	private Date fecha_termino_subsidio = null;
	private int num_dias_subsidio_pagado = 0;
	private int monto_sub_pagado = 0;
	private String tipo_emision = "";
	private String mes_nomina = "";
	private int mod_pago = 0;
	private String serie_documento = "";
	private String num_documento = "";
	private Date fecha_emision_documento = null;

	private int monto_documento = 0;
	private int codigo_banco = 0;
	private String cta_cte = "";
	private int causal_emision = 0;
	private int num_dias_cotiz_pagados = 0;
	private int monto_fp = 0;
	private int monto_salud = 0;
	private int monto_cotiz = 0;
	private int entidad_previsional = 0;
	private int subsidio_iniciado = 0;

	public int getIdSubsTscVig() {
		return idSubsTscVig;
	}

	public void setIdSubsTscVig(int idSubsTscVig) {
		this.idSubsTscVig = idSubsTscVig;
	}

	public int getActividad_laboral_trabajador() {
		return actividad_laboral_trabajador;
	}

	public void setActividad_laboral_trabajador(int actividad_laboral_trabajador) {
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
	}

	public String getAgencia_entidad() {
		return agencia_entidad;
	}

	public void setAgencia_entidad(String agencia_entidad) {
		this.agencia_entidad = agencia_entidad;
	}

	public int getCausal_emision() {
		return causal_emision;
	}

	public void setCausal_emision(int causal_emision) {
		this.causal_emision = causal_emision;
	}

	public int getCod_comuna_beneficiaria() {
		return cod_comuna_beneficiaria;
	}

	public void setCod_comuna_beneficiaria(int cod_comuna_beneficiaria) {
		this.cod_comuna_beneficiaria = cod_comuna_beneficiaria;
	}

	public int getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(int codigo_banco) {
		this.codigo_banco = codigo_banco;
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

	public String getCta_cte() {
		return cta_cte;
	}

	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public void setFecha_emision_documento(Date fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public Date getFecha_inicio_subsidio() {
		return fecha_inicio_subsidio;
	}

	public void setFecha_inicio_subsidio(Date fecha_inicio_subsidio) {
		this.fecha_inicio_subsidio = fecha_inicio_subsidio;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Date getFecha_termino_subsidio() {
		return fecha_termino_subsidio;
	}

	public void setFecha_termino_subsidio(Date fecha_termino_subsidio) {
		this.fecha_termino_subsidio = fecha_termino_subsidio;
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

	public int getMod_pago() {
		return mod_pago;
	}

	public void setMod_pago(int mod_pago) {
		this.mod_pago = mod_pago;
	}

	public int getMonto_cotiz() {
		return monto_cotiz;
	}

	public void setMonto_cotiz(int monto_cotiz) {
		this.monto_cotiz = monto_cotiz;
	}

	public int getMonto_documento() {
		return monto_documento;
	}

	public void setMonto_documento(int monto_documento) {
		this.monto_documento = monto_documento;
	}

	public int getMonto_fp() {
		return monto_fp;
	}

	public void setMonto_fp(int monto_fp) {
		this.monto_fp = monto_fp;
	}

	public int getEntidad_previsional() {
		return entidad_previsional;
	}

	public void setEntidad_previsional(int entidad_previsional) {
		this.entidad_previsional = entidad_previsional;
	}

	public int getMonto_salud() {
		return monto_salud;
	}

	public void setMonto_salud(int monto_salud) {
		this.monto_salud = monto_salud;
	}

	public int getMonto_sub_pagado() {
		return monto_sub_pagado;
	}

	public void setMonto_sub_pagado(int monto_sub_pagado) {
		this.monto_sub_pagado = monto_sub_pagado;
	}

	public String getNombre_beneficiaria() {
		return nombre_beneficiaria;
	}

	public void setNombre_beneficiaria(String nombre_beneficiaria) {
		this.nombre_beneficiaria = nombre_beneficiaria;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia;
	}

	public int getNro_nacimientos() {
		return nro_nacimientos;
	}

	public void setNro_nacimientos(int nro_nacimientos) {
		this.nro_nacimientos = nro_nacimientos;
	}

	public int getNum_dias_autorizados() {
		return num_dias_autorizados;
	}

	public void setNum_dias_autorizados(int num_dias_autorizados) {
		this.num_dias_autorizados = num_dias_autorizados;
	}

	public int getNum_dias_cotiz_pagados() {
		return num_dias_cotiz_pagados;
	}

	public void setNum_dias_cotiz_pagados(int num_dias_cotiz_pagados) {
		this.num_dias_cotiz_pagados = num_dias_cotiz_pagados;
	}

	public int getNum_dias_subsidio_pagado() {
		return num_dias_subsidio_pagado;
	}

	public void setNum_dias_subsidio_pagado(int num_dias_subsidio_pagado) {
		this.num_dias_subsidio_pagado = num_dias_subsidio_pagado;
	}

	public String getNum_documento() {
		return num_documento;
	}

	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento;
	}

	public String getRun_beneficiaria() {
		return run_beneficiaria;
	}

	public void setRun_beneficiaria(String run_beneficiaria) {
		this.run_beneficiaria = run_beneficiaria;
	}

	public String getSerie_documento() {
		return serie_documento;
	}

	public void setSerie_documento(String serie_documento) {
		this.serie_documento = serie_documento;
	}

	public int getSubsidio_iniciado() {
		return subsidio_iniciado;
	}

	public void setSubsidio_iniciado(int subsidio_iniciado) {
		this.subsidio_iniciado = subsidio_iniciado;
	}

	public String getTipo_emision() {
		return tipo_emision;
	}

	public void setTipo_emision(String tipo_emision) {
		this.tipo_emision = tipo_emision;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.
		idSubsTscVig = 0;
		mes_informacion = "";
		codigo_entidad = 0;
		agencia_entidad = "";

		run_beneficiaria = "";
		nombre_beneficiaria = "";
		edad = 0;
		cod_comuna_beneficiaria = 0;
		actividad_laboral_trabajador = 0;
		nro_licencia = "";
		codigo_diagnostico = "";
		nro_nacimientos = 0;
		fecha_nacimiento = null;
		num_dias_autorizados = 0;

		fecha_inicio_subsidio = null;
		fecha_termino_subsidio = null;
		num_dias_subsidio_pagado = 0;
		monto_sub_pagado = 0;
		tipo_emision = "";
		mes_nomina = "";
		mod_pago = 0;
		serie_documento = "";
		num_documento = "";
		fecha_emision_documento = null;

		monto_documento = 0;
		codigo_banco = 0;
		cta_cte = "";
		causal_emision = 0;
		num_dias_cotiz_pagados = 0;
		monto_fp = 0;
		monto_salud = 0;
		monto_cotiz = 0;
		entidad_previsional = 0;
		subsidio_iniciado = 0;
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
