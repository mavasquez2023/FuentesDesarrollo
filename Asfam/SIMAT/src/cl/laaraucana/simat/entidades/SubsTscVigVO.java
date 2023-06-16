package cl.laaraucana.simat.entidades;

import java.util.Date;

public class SubsTscVigVO {

	private int idSubsTscVig;
	/*
	 * 'corresponde al mes del informe financiero',
	 * */
	private String mes_informacion;
	/*
	 * 'se debe indicar el codigo asociado a la entidad pagadora de subsidios, de acuerdo a la codificacion del anexo n° 3'
	 * */
	private int codigo_entidad;
	/*
	 * identificacion de sucrusal o agencia de la entidad pagadora donde el beneficiario tramito la licencia medica'
	 * */
	private String agencia_entidad;
	/*
	 * 'identificacion de sucrusal o agencia de la entidad pagadora donde el beneficiario tramito la licencia medica'
	 * */
	private String run_beneficiaria;
	/*
	 * 'nombres y apellidos de la beneficiaria'
	 * */
	private String nombre_beneficiaria;
	/*
	 * edad de la beneficiaria'
	 * */
	private int edad;
	/*
	 * 'codigo  de la comuna de la beneficiaria segun codificacion de anexo n° 3'
	 * */
	private int cod_comuna_beneficiaria;
	/*
	 * 'corresponde a registrar el codigo de la actividad laboral del beneficiario de la licencia medica indicado 
	 * en la seccion c.1, de acuerdo a codificacion del anexo n° 3'
	 * */
	private int actividad_laboral_trabajador;
	/*
	 * numero de folio completo de la licencia medica prenatal o postnatal segun corresponda. el formato requerido es:
	 *  codigo de formulario de licencia medica (indicado en anexo n° 3), guion y el numero de la licencia sin digito
	 *   verificador, incluyendo ceros despues del guion, en caso que el numero tenga un largo inferior a 12, 
	 *   para completar el largo del campo. ej:"1-000019980551"'
	 * */
	private String nro_licencia;
	/*
	 * 'se debera identificar 4 situaciones: codigo correspondiente para indentificar la licencia medica por reposo
	 *  prenatal, periodo prenatal complementario, licencia medica por reposo postnatal y el periodo postnatal parental.
	 *   para el caso de la licencia medica prenatal, el codigo sera 650, para prenatal complementario sera 651, 
	 *   para postnatal 1650, y para postnatal parental 1651'
	 * */
	private String codigo_diagnostico;
	/*
	 * 'numero de niños nacidos en el parto utilizado para el calculo de dias autorizados de la licencia postnatal.
	 *  en caso de corresponder a un subsidio prenatal o prenatal complementario se debera indicar 0(cero)'
	 * */
	private int nro_nacimientos;
	/*
	 * 'fecha de nacimiento de o los niños'
	 * */
	private Date fecha_nacimiento;
	/*
	 * 'correspondera indicar la totalidad de dias autorizados por el compin',
	 * */
	private int num_dias_autorizados;
	/*
	 * 'fecha de inicio del subsidio'
	 * */
	private Date fecha_inicio_subsidio;
	/*
	 * 'fecha de termino del subsidio'
	 * */
	private Date fecha_termino_subsidio;
	/*
	 * 'numero d dias de subsidio pagados en este registro',
	 * */
	private int num_dias_subsidio_pagado;
	/*
	 * 'monto el subsidio pagados en este registro',
	 * */
	private int monto_sub_pagado;
	/*
	 * 'codigo del tipo de emision del subsidio segun codificacion de anexo n° 3, correspondiente a un pago o una 
	 * reliquidacion. ya sea de subsidios o  cotizaciones, cando reliquiden subsidios y  cotizaciones, se deberan 
	 * informar en registros separados'
	 * */
	private String tipo_emision;
	/*
	 * 'en el evento que exista una reliquidacion, y que corresponda esta a años anteriores al mes del informe,
	 *  se debera registrar el mes y año anteriores al mes del informe, se debera registrar el mes y año en que el
	 *   beneficio fue imputado como gasto al fondo unico, en caso contrario se debera dejar vacio.'
	 * */
	private String mes_nomina;

	/*
	 * 'codigo de la modalidad de pago segun codificacion de anexo n° 3. correspondiente a cheque, orden de pago o deposito'
	 * */
	private int mod_pago;
	/*
	 * 'numero de serie del chque, transferencia u orden de pago.',
	 * */
	private String serie_documento;
	/*
	 * 'numero del codumento que corresponda a cada beneficiario, segun la modalidad de pago'
	 * */
	private String num_documento;
	/*
	 * 'fecha señalada en el cheque, orden de pago, la que no puede ser diferente de la fecha asignada para el pago 
	 * de subsdio, en el caso de una transferecia electronica o deposito, correspondera a la fecha de operacion.'
	 * */
	private Date fecha_emision_documento;
	/*
	 * 'monto por el cual se extendio el documento de pago: cheque, orden de pago, o transferencia electronica.'
	 * */
	private int monto_documento;
	/*
	 * 'codigo del banco asociado al cheque, orden de pago y transferencia electronica, segun codificacion anexo n° 3'
	 * */
	private int codigo_banco;
	/*
	 * 'cuenta corriente de donde se transfirireron los fondos para el pago del subsidio.'
	 * */
	private String cta_cte;
	/*
	 *  'codigo de la causal de emision segun codificacion de anexo n° 3, para las siguientes situaciones: por reemplazo 
	 * de un documento previamente caducado, por reemplazo de un documento previamente anulado, por pago normal;
	 *  por cambio de nobre de beneficicario, por fallecimiento o incapacidad de este.
	 * 
	 * */
	private int causal_emision;
	/*
	 * numero efectivo de dias de cotizacion pagados cyo valor debera coincidir con el numero de dias de licencia 
	 * devengados en el mes que se informa'
	 * */
	private int num_dias_cotiz_pagados;
	/*
	 * monto que resulte de aplicar el porcentaje de cotizacion del fondo de pensiones que tenga establecido la 
	 * institucion previsional en la cual se encuentre afiliada la beneficiaria(adminsitradora de fondos de pensinoes 
	 * o el respectivo regimen de pension administrado por el instituto de prevision social.) sobre el onto del subsidio,
	 *  dividida por 30 y multiplicada por el numero de dias indicados en el campo anterior'
	 * */
	private int monto_fp;
	/*
	 * valor equivalente al 7% del subsidio calculado, correspondiente al numero de dias de licencia medica.
	 * */
	private int monto_salud;
	/*
	 * monto correspondiente a la suma de los 2 campos anteriores'
	 * */
	private int monto_cotiz;
	/*'
	 * codigo de la entidad previsional de afiliacion de la beneficiaria segun codificacion de anexo n° 3'
	 * */
	private int entidad_previsional;
	/*'
	 * debera indicar si el subsidio es aquel orginado en unan primera licenciamedica  que inicia su pago en el mes
	 *  informado o no, en los casos que se trate de una prorroga de licenncia medica, segun codificacion de anexo n° 3
	 * 
	 * */
	private int subsidio_iniciado;

	public SubsTscVigVO() {
	}

	public SubsTscVigVO(int idSubsTscVig, String mes_informacion, int codigo_entidad, String agencia_entidad, String run_beneficiaria, String nombre_beneficiaria, int edad,
			int cod_comuna_beneficiaria, int actividad_laboral_trabajador, String nro_licencia, String codigo_diagnostico, int nro_nacimientos, Date fecha_nacimiento, int num_dias_autorizados,
			Date fecha_inicio_subsidio, Date fecha_termino_subsidio, int num_dias_subsidio_pagado, int monto_sub_pagado, String tipo_emision, String mes_nomina, int mod_pago, String serie_documento,
			String num_documento, Date fecha_emision_documento, int monto_documento, int codigo_banco, String cta_cte, int causal_emision, int num_dias_cotiz_pagados, int monto_fp, int monto_salud,
			int monto_cotiz, int entidad_previsional, int subsidio_iniciado) {
		super();
		this.idSubsTscVig = idSubsTscVig;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.agencia_entidad = agencia_entidad;
		this.run_beneficiaria = run_beneficiaria;
		this.nombre_beneficiaria = nombre_beneficiaria;
		this.edad = edad;
		this.cod_comuna_beneficiaria = cod_comuna_beneficiaria;
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
		this.nro_licencia = nro_licencia;
		this.codigo_diagnostico = codigo_diagnostico;
		this.nro_nacimientos = nro_nacimientos;
		this.fecha_nacimiento = fecha_nacimiento;
		this.num_dias_autorizados = num_dias_autorizados;
		this.fecha_inicio_subsidio = fecha_inicio_subsidio;
		this.fecha_termino_subsidio = fecha_termino_subsidio;
		this.num_dias_subsidio_pagado = num_dias_subsidio_pagado;
		this.monto_sub_pagado = monto_sub_pagado;
		this.tipo_emision = tipo_emision;
		this.mes_nomina = mes_nomina;
		this.mod_pago = mod_pago;
		this.serie_documento = serie_documento;
		this.num_documento = num_documento;
		this.fecha_emision_documento = fecha_emision_documento;
		this.monto_documento = monto_documento;
		this.codigo_banco = codigo_banco;
		this.cta_cte = cta_cte;
		this.causal_emision = causal_emision;
		this.num_dias_cotiz_pagados = num_dias_cotiz_pagados;
		this.monto_fp = monto_fp;
		this.monto_salud = monto_salud;
		this.monto_cotiz = monto_cotiz;
		this.entidad_previsional = entidad_previsional;
		this.subsidio_iniciado = subsidio_iniciado;
	}

	public String getIdSubsTscVig() {
		return String.valueOf(this.idSubsTscVig);
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

}
