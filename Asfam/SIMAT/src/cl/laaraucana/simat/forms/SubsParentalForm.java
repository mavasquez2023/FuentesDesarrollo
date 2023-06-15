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
public class SubsParentalForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id = 0;

	private String mes_informacion = "";
	private int codigo_entidad = 0;
	private String agencia_entidad = "";
	private String rut_empleador = "";
	private String nombre_empleador = "";
	private String run_beneficiario_parental = "";
	private String nombre_beneficiario_parental = "";
	private int edad = 0;
	private String sexo = "";
	private int calidad_trabajador = 0;

	private String actividad_laboral_trabajador = "";
	private String cod_comuna_beneficiario = "";
	private String run_beneficiario_postnatal = "";
	private String nombre_beneficiario_postnatal = "";
	private String nro_licencia = "";
	private int vinculo_beneficiario_menor = 0;
	private String nro_resolucion = "";
	private int tipo_extension_postnatal_parental = 0;
	private String fecha_inicio_postnatal_parental = "0001-01-01";
	private String fecha_termino_postnatal_parental = "0001-01-01";

	private int num_dias_permiso_pagado = 0;
	private int tipo_de_pago = 0;
	private long monto_sub_dfl44_1978 = 0;
	private long monto_sub_pagado = 0;
	private String tipo_emision = "";
	private String mes_nomina = "";
	private int mod_pago = 0;
	private String serie_documento = "";
	private String num_documento = "";
	private long monto_documento = 0;

	private String fecha_emision_documento = "0001-01-01";
	private int causal_emision = 0;
	private String codigo_banco = "";
	private String cta_cte = "";
	private int num_dias_cotiz_pagados = 0;
	private long monto_remun_imponible = 0;
	private long monto_fp = 0;
	private long monto_salud = 0;
	private long monto_salud_ad = 0;
	private long monto_desahucio = 0;

	private long monto_cotiz_fu = 0;
	private long monto_cotiz_sc = 0;
	private int entidad_previsional = 0;
	private int traspaso = 0;
	private int subsidio_iniciado = 0;

	private int indicador_convenio = 0;

	private int serv_salud = 0;

	public SubsParentalForm() {
		super();
	}

	public int getServ_salud() {
		return serv_salud;
	}

	public void setServ_salud(int serv_salud) {
		this.serv_salud = serv_salud;
	}

	public int getIndicador_convenio() {
		return indicador_convenio;
	}

	public void setIndicador_convenio(int indicador_convenio) {
		this.indicador_convenio = indicador_convenio;
	}

	public String getAgencia_entidad() {
		return agencia_entidad;
	}

	public void setAgencia_entidad(String agencia_entidad) {
		this.agencia_entidad = agencia_entidad;
	}

	public int getCalidad_trabajador() {
		return calidad_trabajador;
	}

	public void setCalidad_trabajador(int calidad_trabajador) {
		this.calidad_trabajador = calidad_trabajador;
	}

	public int getCausal_emision() {
		return causal_emision;
	}

	public void setCausal_emision(int causal_emision) {
		this.causal_emision = causal_emision;
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

	public int getEntidad_previsional() {
		return entidad_previsional;
	}

	public void setEntidad_previsional(int entidad_previsional) {
		this.entidad_previsional = entidad_previsional;
	}

	public String getFecha_inicio_postnatal_parental() {
		return fecha_inicio_postnatal_parental;
	}

	public String getFecha_termino_postnatal_parental() {
		return fecha_termino_postnatal_parental;
	}

	public String getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public void setFecha_emision_documento(String fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public void setFecha_inicio_postnatal_parental(String fecha_inicio_postnatal_parental) {
		this.fecha_inicio_postnatal_parental = fecha_inicio_postnatal_parental;
	}

	public void setFecha_termino_postnatal_parental(String fecha_termino_postnatal_parental) {
		this.fecha_termino_postnatal_parental = fecha_termino_postnatal_parental;
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

	public long getMonto_cotiz_fu() {
		return monto_cotiz_fu;
	}

	public void setMonto_cotiz_fu(long monto_cotiz_fu) {
		this.monto_cotiz_fu = monto_cotiz_fu;
	}

	public long getMonto_cotiz_sc() {
		return monto_cotiz_sc;
	}

	public void setMonto_cotiz_sc(long monto_cotiz_sc) {
		this.monto_cotiz_sc = monto_cotiz_sc;
	}

	public long getMonto_desahucio() {
		return monto_desahucio;
	}

	public void setMonto_desahucio(long monto_desahucio) {
		this.monto_desahucio = monto_desahucio;
	}

	public long getMonto_documento() {
		return monto_documento;
	}

	public void setMonto_documento(long monto_documento) {
		this.monto_documento = monto_documento;
	}

	public long getMonto_fp() {
		return monto_fp;
	}

	public void setMonto_fp(long monto_fp) {
		this.monto_fp = monto_fp;
	}

	public long getMonto_remun_imponible() {
		return monto_remun_imponible;
	}

	public void setMonto_remun_imponible(long monto_remun_imponible) {
		this.monto_remun_imponible = monto_remun_imponible;
	}

	public long getMonto_salud() {
		return monto_salud;
	}

	public void setMonto_salud(long monto_salud) {
		this.monto_salud = monto_salud;
	}

	public long getMonto_salud_ad() {
		return monto_salud_ad;
	}

	public void setMonto_salud_ad(long monto_salud_ad) {
		this.monto_salud_ad = monto_salud_ad;
	}

	public long getMonto_sub_dfl44_1978() {
		return monto_sub_dfl44_1978;
	}

	public void setMonto_sub_dfl44_1978(long monto_sub_dfl44_1978) {
		this.monto_sub_dfl44_1978 = monto_sub_dfl44_1978;
	}

	public long getMonto_sub_pagado() {
		return monto_sub_pagado;
	}

	public void setMonto_sub_pagado(long monto_sub_pagado) {
		this.monto_sub_pagado = monto_sub_pagado;
	}

	public String getNombre_beneficiario_parental() {
		return nombre_beneficiario_parental;
	}

	public void setNombre_beneficiario_parental(String nombre_beneficiario_parental) {
		this.nombre_beneficiario_parental = nombre_beneficiario_parental;
	}

	public String getNombre_beneficiario_postnatal() {
		return nombre_beneficiario_postnatal;
	}

	public void setNombre_beneficiario_postnatal(String nombre_beneficiario_postnatal) {
		this.nombre_beneficiario_postnatal = nombre_beneficiario_postnatal;
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

	public String getNro_resolucion() {
		return nro_resolucion;
	}

	public void setNro_resolucion(String nro_resolucion) {
		this.nro_resolucion = nro_resolucion;
	}

	public int getNum_dias_cotiz_pagados() {
		return num_dias_cotiz_pagados;
	}

	public void setNum_dias_cotiz_pagados(int num_dias_cotiz_pagados) {
		this.num_dias_cotiz_pagados = num_dias_cotiz_pagados;
	}

	public int getNum_dias_permiso_pagado() {
		return num_dias_permiso_pagado;
	}

	public void setNum_dias_permiso_pagado(int num_dias_permiso_pagado) {
		this.num_dias_permiso_pagado = num_dias_permiso_pagado;
	}

	public String getNum_documento() {
		return num_documento;
	}

	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento;
	}

	public String getRun_beneficiario_parental() {
		return run_beneficiario_parental;
	}

	public void setRun_beneficiario_parental(String run_beneficiario_parental) {
		this.run_beneficiario_parental = run_beneficiario_parental;
	}

	public String getRun_beneficiario_postnatal() {
		return run_beneficiario_postnatal;
	}

	public void setRun_beneficiario_postnatal(String run_beneficiario_postnatal) {
		this.run_beneficiario_postnatal = run_beneficiario_postnatal;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getSubsidio_iniciado() {
		return subsidio_iniciado;
	}

	public void setSubsidio_iniciado(int subsidio_iniciado) {
		this.subsidio_iniciado = subsidio_iniciado;
	}

	public int getTipo_de_pago() {
		return tipo_de_pago;
	}

	public void setTipo_de_pago(int tipo_de_pago) {
		this.tipo_de_pago = tipo_de_pago;
	}

	public String getTipo_emision() {
		return tipo_emision;
	}

	public void setTipo_emision(String tipo_emision) {
		this.tipo_emision = tipo_emision;
	}

	public int getTipo_extension_postnatal_parental() {
		return tipo_extension_postnatal_parental;
	}

	public void setTipo_extension_postnatal_parental(int tipo_extension_postnatal_parental) {
		this.tipo_extension_postnatal_parental = tipo_extension_postnatal_parental;
	}

	public int getTraspaso() {
		return traspaso;
	}

	public void setTraspaso(int traspaso) {
		this.traspaso = traspaso;
	}

	public int getVinculo_beneficiario_menor() {
		return vinculo_beneficiario_menor;
	}

	public void setVinculo_beneficiario_menor(int vinculo_beneficiario_menor) {
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
	}

	public String getActividad_laboral_trabajador() {
		return actividad_laboral_trabajador;
	}

	public String getCod_comuna_beneficiario() {
		return cod_comuna_beneficiario;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setActividad_laboral_trabajador(String actividad_laboral_trabajador) {
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
	}

	public void setCod_comuna_beneficiario(String cod_comuna_beneficiario) {
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset field values here.

		this.id = 0;
		this.mes_informacion = "";
		this.codigo_entidad = 0;
		this.agencia_entidad = "";
		this.rut_empleador = "";
		this.nombre_empleador = "";
		this.run_beneficiario_parental = "";
		this.nombre_beneficiario_parental = "";
		this.edad = 0;
		this.sexo = "";
		this.calidad_trabajador = 0;
		this.actividad_laboral_trabajador = "";
		this.cod_comuna_beneficiario = "";
		this.run_beneficiario_postnatal = "";
		this.nombre_beneficiario_postnatal = "";
		this.nro_licencia = "";
		this.vinculo_beneficiario_menor = 0;
		this.nro_resolucion = "";
		this.tipo_extension_postnatal_parental = 0;
		//fechas, iso	
		this.fecha_inicio_postnatal_parental = "0001-01-01";
		this.fecha_termino_postnatal_parental = "0001-01-01";

		this.num_dias_permiso_pagado = 0;
		this.tipo_de_pago = 0;

		this.monto_sub_dfl44_1978 = 0;
		this.monto_sub_pagado = 0;
		this.tipo_emision = "";
		this.mes_nomina = "";
		this.mod_pago = 0;
		this.serie_documento = "";
		this.num_documento = "";
		this.monto_documento = 0;
		//fechas, iso		
		this.fecha_emision_documento = "0001-01-01";

		this.causal_emision = 0;
		this.codigo_banco = "";
		this.cta_cte = "";
		this.num_dias_cotiz_pagados = 0;

		this.monto_remun_imponible = 0;
		this.monto_fp = 0;
		this.monto_salud = 0;
		this.monto_salud_ad = 0;
		this.monto_desahucio = 0;
		this.monto_cotiz_fu = 0;
		this.monto_cotiz_sc = 0;

		this.entidad_previsional = 0;
		this.traspaso = 0;
		this.subsidio_iniciado = 0;

		this.indicador_convenio = 0;
		this.serv_salud = 0;

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
