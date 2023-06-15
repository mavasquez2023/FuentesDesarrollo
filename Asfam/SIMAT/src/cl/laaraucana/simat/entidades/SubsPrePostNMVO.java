package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class SubsPrePostNMVO {

	private int id;
	/*
	 * 'corresponde al mes del informe financiero'
	 * */
	private String mes_informacion;
	/*
	 * 'se debe indicar el codigo asociado a la entidad pagadora de subsidios, de acuerdo a la codificacion
	 *  del anexo n° 3'
	 * */
	private int codigo_entidad;
	/*
	 * 'identificacion de sucursal o agencia de la entidad pagadora donde el beneficiario tramito la licencia
	 *  medica'
	 * */
	private String agencia_entidad;
	/*
	 * 'rol unico tributario del empleador con digito verificador, tratandose de trabajadores independientes 
	 * se debera indicar al run del beneficiario'
	 * */
	private String rut_empleador;
	/*
	 * 'nombre o razon social del empleador. tratandose de trabajadores independientes se debera indicar el 
	 * nombre del beneficiario'
	 * */
	private String nombre_empleador;
	/*
	 * 'cedula de identidad del beneficiario, con digito erificador'
	 * */
	private String run_beneficiario;
	/*
	 * 'nombres y apellidos del beneficiario',
	 * */
	private String nombre_beneficiario;
	/*
	 * 'numero de folio completo de la licencia medica en caso que el tipo de subsidio corresponda a prenatal, 
	 * postnatal o enfermedad grave del niño menor de un año. el formato requerido es: codigo de formulario de 
	 * licencia meica (indicado en anexo n°3), guion y el numero de la licencia sin digito verificador, 
	 * incluyendo ceros despues del guion, en caso que el numero tenga un largo inferior a 12, para completar 
	 * el largo del campo. ej: "1-000019980551"'
	 * */
	private String nro_licencia;
	/*
	 * 'codigo correspondiente segun la "clasificaion estadistica internacional de enfermedades y problemas 
	 * relacionados con la salud(cie 10)", parael caso de la licencia medica prenatal, el codigo sera 650 y 
	 * para posnatal 1650'
	 * */
	private String codigo_diagnostico;
	/*
	 * 'codigo  del vinculo que posee el beneficiario con el menor segun codificacion n° 3, en caso de 
	 * corresponder a un subsidio de reposo prenatal, correspondera informar l codigo de dominio para "madre"'
	 * */
	private int vinculo_beneficiario_menor;
	/*
	 * 'corresponde registrar el codigo de actividad aboral del beneficiario de la licencia medica, indicado 
	 * en la seccion c.1, de acuerdo a codificacion de anexo n° 3'
	 * */
	private String actividad_laboral_trabajador;
	/*
	 * 'codigo de la comuna de residencia del beneficiario del subsidio maternal segun codificacion de 
	 * anexo n° 3'
	 * */
	private String cod_comuna_beneficiario;
	/*
	 * 'se debera informar el numero de la resolucion en caso que se trate de una madre o padre en adopcion,
	 *  o de un tutor del menor.'
	 * */
	private String nro_resolucion;
	/*
	 * 'codigo de la causal de extension del postnatal segun codificacion de anexo n° 3'
	 * */
	private int extension_postnatal;
	/*
	 * 'numero de niños nacidos en el parto utilizado para el calculo de dias autorizados de la licencia
	 *  postnatal. en caso de corresponder a un subsidio prenatal o por enfermedad grave del niño menor de 
	 *  un año se debera indicar 0'
	 * */
	private int nro_nacimientos;
	/*
	 * 'numero total de dias autorizados de la licencia (indicados en la seccion b del formulario de licencias 
	 * medicas)',
	 * */
	private int num_dias_licencia_autorizados;
	/*
	 *'fecha de inicio de la incapacidad laboral (indicado en la seccion b del formulario de licencias medicas)' 
	 * */
	private Date fecha_inicio_reposo;

	private String fecha_inicio_reposo_Char;
	/*
	 * 
	 * 'fecha termino de la incapacidad laboral (indicado en la seccion b del formulario de licencias medicas)'
	 * */
	private Date fecha_termino_reposo;

	private String fecha_termino_reposo_Char;
	/*
	 * 'dias efectivamente pagados al beneficiario en el mes que se informa'
	 * */
	private int num_dias_sub_pagadados;
	/*
	 * 'codigo del tipo de pago, segun codificacion de anexo n° 3, para los casos que correspondan a un pago 
	 * directo al beneficiario o u reembolso a un empleador. en los casos en que a un empleador se le haya 
	 * efectuado as de un reembolso en el mismo mes informado, se debera incluir  este empleador tantas veces
	 *  como reembolsos tenga, incluyendose en cada caso los datos de los beneficiarios correspondientes.'
	 * */
	private int tipo_de_pago;
	/*
	 * 'monto del subsidio diario que rsulte del calculo realizado de acuerdo con las normas egales vigentes,
	 *  multiplicado por los dias de subsidio a pagar indicados anteriormente'
	 * */
	private long monto_sub_dfl44_1978;

	/*
	 * 'monto que efectivamente debio recibir el subsidiado en el mes que se informa, descontado el monto de la
	 *  cotizacion en el mes que se informa, descontado el monto de la cotizacion para el seguro de cesantia en 
	 *  los casos que corresponda. cuando no proceda descontar cotizacion para el seguro de cesantia el monto
	 *   indicado en este campo debera coincidir con el campo "monto_sub_dfl44_1978"'
	 * */
	private long monto_sub_pagado;
	/*
	 * 'codigo del tipo de emision del subsidio segun codificacion de anexo n° 3 correspondiente a 
	 * un pago o una liquidacion, ya sea de subsidios o cotizaciones, se deberan informar informar en 
	 * registros separados'
	 * */
	private String tipo_emision;
	/*
	 *  
	 * 'en el evento que exista una reliquidacion, y que corresponda esta a años anteriores al mes 
	 * del informe, se debera registrar el mes y año en el que el beneficio fue imputado como gasto al
	 *  fondo unico. en caso contrario se debera dejar vacio'
	 * */
	private String mes_nomina;
	/*
	 *  'codigo de la modalidad de pago segun codificacion de anexo n° 3, correspondiente a cheque, 
	 *  orden de pago o deposito'
	 * */
	private int mod_pago;
	/*
	 * 'numero de serie del cheque, transferencia u orden de pago, segun corresponda',
	 * */
	private String serie_documento;
	/*
	 * 'numero del codumento que le corresponda a cada beneficiario, segun corresponda'
	 * */
	private String num_documento;
	/*
	 * 'fecha señalada en el cheque u orden de pago, la que no puede ser diferente de la fecha asignada para 
	 * el pago de subsidio, en el caso de una transferencia electronica o deposito, correspondera a la fecha
	 *  de la operacion'
	 * */
	private Date fecha_emision_documento;

	private String fecha_emision_documento_Char;

	/*
	 * 'monto por el cual se extendio el documento de pago: cheque, orden de pago o transferencia electronica.'
	 * */
	private long monto_documento;
	/*
	 * 'codig del banco asociado al cheque, orden de pago o transferencia electronica, segun codificacion del anexo n° 3'
	 * */
	private String codigo_banco;
	/*
	 * 'cuenta corriente de donde se transfirieron los fondos para el pago del subsidio'
	 * */
	private String cta_cte;
	/*
	 * 'codigo de la causal de emision segun codificacion de anexo n° 3,  para las siquientes situaciones: 
	 * por reemplazo de un documento previamente caducado; por reemplazo de un documento previamente caducado;
	 *  por reemplazo de un documento previamente anulado; por pago normal; por cambio de nombre del beneficiario;
	 *   por fallecimiento o incapacidad de este'
	 * */
	private int causal_emision;
	/*
	 * 'numero efectivo de das cotizacion pagados cuyo valor debera coincidir con el numero de dias de
	 *  licencia devengados en el mes que se informa'
	 * */
	private int num_dias_cotiz_pagados;
	/*
	 * 'monto de la remuneracion o renta imponible(en base a 30 dias) del mes anterior al que se inicie
	 *  la licencia medica, o en su defecto, la estipulada en el contrato de trabajo'
	 * */
	private long monto_renum_imponible;
	/*
	 * 'monto que resulte de aplicar el porcentaje de cotizacion al fondo de pensiones que tenga 
	 * establecido la institucion previsional en la cualse encuentre afiliado el subsidiado
	 *  (administradora de fondosde pensiones o el respectivo regimen de pension administrado por 
	 *  el instiuto de prevision social), sobre la remuneracion imponible del mes anterior, 
	 *  o en su defecto la estipulada en el contrato de trabajo(si es un trabajador dependiente) 
	 *  o la renta imponible (si es un trabajador independiente) del mes anterior al que se indica
	 *   la licencia medica, dividida por 30 y multiplicad por el numero de dias indicados en el campo anterior'
	 * */
	private long monto_fp;
	/*
	 * 'valor equivalente al 7% de la remuneracion o renta imponible del mes anterioral inicio de la 
	 * licencia, correspondiente al numero  de dias de incapacidad laboral.'
	 * */
	private long monto_salud;
	/*
	 * 'diferencia entre el monto correspondiente a aquella cotizacion superior que el trabajador 
	 * haya pactado con su isapre y el monto equivalente a la cotizacion del 7%'
	 * */
	private long monto_salud_ad;
	/*
	 * 'monto de la cotizacion  deshaucio e indemnizaciones la cual el subsidiado se encuentre afecto 
	 * en relacion con el referido beneficio'
	 * */
	private long monto_desahucio;
	/*
	 * 'monto correspondiente a la suma de los 4 campos anteriores'
	 * */
	private long monto_cotiz_fu;
	/*
	 * 'cantidad descontada del subsidio por concepto del 0,6% de la cotizacion para el seguro de cesantia, 
	 * recaudada por las administradoreas de fondos de pensiones para la a.f.c'
	 * */
	private long monto_cotiz_sc;

	/*
	 * 'codigo entidad previsional de afiliacion del beneficirio segun codificacion de anexo n° 3'
	 * */
	private int entidad_previsional;
	/*
	 * 'se debera indicar si el subsidio es aquel originado en una primera licencia medica que inicia 
	 * su pago en el mes informado o no, en los casos que se trate de una proroga de licencia medica,
	 *  segun codificacion de anexo n° 3'
	 * */
	private int subsidio_iniciado;

	/*
	 * indicador de convenio, para cuadros resumen de cotizacines
	 * 
	 * */
	private int indicador_convenio;
	/*
	 * 
	 * */
	private int serv_salud;
	/*
	 * 
	 * */
	private long monto_cot;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public SubsPrePostNMVO() {
	}

	public SubsPrePostNMVO(int id, String mes_informacion, int codigo_entidad, String agencia_entidad, String rut_empleador, String nombre_empleador, String run_beneficiario,
			String nombre_beneficiario, String nro_licencia, String codigo_diagnostico, int vinculo_beneficiario_menor, String actividad_laboral_trabajador, String cod_comuna_beneficiario,
			String nro_resolucion, int extension_postnatal, int nro_nacimientos, int num_dias_licencia_autorizados, Date fecha_inicio_reposo, Date fecha_termino_reposo, int num_dias_sub_pagadados,
			int tipo_de_pago, long monto_sub_dfl44_1978, long monto_sub_pagado, String tipo_emision, String mes_nomina, int mod_pago, String serie_documento, String num_documento,
			Date fecha_emision_documento, long monto_documento, String codigo_banco, String cta_cte, int causal_emision, int num_dias_cotiz_pagados, long monto_renum_imponible, long monto_fp,
			long monto_salud, long monto_salud_ad, long monto_desahucio, long monto_cotiz_fu, long monto_cotiz_sc, int entidad_previsional, int subsidio_iniciado, int indicador_convenio) {
		super();
		this.id = id;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.agencia_entidad = agencia_entidad;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.run_beneficiario = run_beneficiario;
		this.nombre_beneficiario = nombre_beneficiario;
		this.nro_licencia = nro_licencia;
		this.codigo_diagnostico = codigo_diagnostico;
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
		this.nro_resolucion = nro_resolucion;
		this.extension_postnatal = extension_postnatal;
		this.nro_nacimientos = nro_nacimientos;
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
		this.fecha_inicio_reposo = fecha_inicio_reposo;
		this.fecha_termino_reposo = fecha_termino_reposo;
		this.num_dias_sub_pagadados = num_dias_sub_pagadados;
		this.tipo_de_pago = tipo_de_pago;
		this.monto_sub_dfl44_1978 = monto_sub_dfl44_1978;
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
		this.monto_renum_imponible = monto_renum_imponible;
		this.monto_fp = monto_fp;
		this.monto_salud = monto_salud;
		this.monto_salud_ad = monto_salud_ad;
		this.monto_desahucio = monto_desahucio;
		this.monto_cotiz_fu = monto_cotiz_fu;
		this.monto_cotiz_sc = monto_cotiz_sc;
		this.entidad_previsional = entidad_previsional;
		this.subsidio_iniciado = subsidio_iniciado;
		this.indicador_convenio = indicador_convenio;
	}

	public int getServ_salud() {
		return serv_salud;
	}

	public void setServ_salud(int serv_salud) {
		this.serv_salud = serv_salud;
	}

	public long getMonto_cot() {
		return monto_cot;
	}

	public void setMonto_cot(long monto_cot) {
		this.monto_cot = monto_cot;
	}

	//	----------------------------------------------------------
	public int getKeyFin() {
		return keyFin;
	}

	public int getKeyInicio() {
		return keyInicio;
	}

	public void setKeyFin(int keyFin) {
		this.keyFin = keyFin;
	}

	public void setKeyInicio(int keyInicio) {
		this.keyInicio = keyInicio;
	}

	public int getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

	//-----------------------------------------------------------

	public String getAgencia_entidad() {
		return agencia_entidad;
	}

	public int getCausal_emision() {
		return causal_emision;
	}

	public String getCodigo_diagnostico() {
		return codigo_diagnostico;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public String getCta_cte() {
		return cta_cte;
	}

	public int getEntidad_previsional() {
		return entidad_previsional;
	}

	public int getExtension_postnatal() {
		return extension_postnatal;
	}

	public Date getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public Date getFecha_inicio_reposo() {
		return fecha_inicio_reposo;
	}

	public Date getFecha_termino_reposo() {
		return fecha_termino_reposo;
	}

	public String getId() {
		return String.valueOf(id);
	}

	public int getIndicador_convenio() {
		return indicador_convenio;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public String getMes_nomina() {
		return mes_nomina;
	}

	public int getMod_pago() {
		return mod_pago;
	}

	public long getMonto_cotiz_fu() {
		return monto_cotiz_fu;
	}

	public long getMonto_cotiz_sc() {
		return monto_cotiz_sc;
	}

	public long getMonto_desahucio() {
		return monto_desahucio;
	}

	public long getMonto_documento() {
		return monto_documento;
	}

	public long getMonto_fp() {
		return monto_fp;
	}

	public long getMonto_renum_imponible() {
		return monto_renum_imponible;
	}

	public long getMonto_salud() {
		return monto_salud;
	}

	public long getMonto_salud_ad() {
		return monto_salud_ad;
	}

	public long getMonto_sub_dfl44_1978() {
		return monto_sub_dfl44_1978;
	}

	public long getMonto_sub_pagado() {
		return monto_sub_pagado;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public int getNro_nacimientos() {
		return nro_nacimientos;
	}

	public String getNro_resolucion() {
		return nro_resolucion;
	}

	public int getNum_dias_cotiz_pagados() {
		return num_dias_cotiz_pagados;
	}

	public int getNum_dias_licencia_autorizados() {
		return num_dias_licencia_autorizados;
	}

	public int getNum_dias_sub_pagadados() {
		return num_dias_sub_pagadados;
	}

	public String getNum_documento() {
		return num_documento;
	}

	public String getRun_beneficiario() {
		return run_beneficiario;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public String getSerie_documento() {
		return serie_documento;
	}

	public int getSubsidio_iniciado() {
		return subsidio_iniciado;
	}

	public int getTipo_de_pago() {
		return tipo_de_pago;
	}

	public String getTipo_emision() {
		return tipo_emision;
	}

	public int getVinculo_beneficiario_menor() {
		return vinculo_beneficiario_menor;
	}

	public void setAgencia_entidad(String agencia_entidad) {
		this.agencia_entidad = agencia_entidad.trim();
	}

	public void setCausal_emision(int causal_emision) {
		this.causal_emision = causal_emision;
	}

	public void setCodigo_diagnostico(String codigo_diagnostico) {
		this.codigo_diagnostico = codigo_diagnostico.trim();
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte.trim();
	}

	public void setEntidad_previsional(int entidad_previsional) {
		this.entidad_previsional = entidad_previsional;
	}

	public void setExtension_postnatal(int extension_postnatal) {
		this.extension_postnatal = extension_postnatal;
	}

	public void setFecha_emision_documento(Date fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public void setFecha_inicio_reposo(Date fecha_inicio_reposo) {
		this.fecha_inicio_reposo = fecha_inicio_reposo;
	}

	public void setFecha_termino_reposo(Date fecha_termino_reposo) {
		this.fecha_termino_reposo = fecha_termino_reposo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIndicador_convenio(int indicador_convenio) {
		this.indicador_convenio = indicador_convenio;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion.trim();
	}

	public void setMes_nomina(String mes_nomina) {
		this.mes_nomina = mes_nomina.trim();
	}

	public void setMod_pago(int mod_pago) {
		this.mod_pago = mod_pago;
	}

	public void setMonto_cotiz_fu(long monto_cotiz_fu) {
		this.monto_cotiz_fu = monto_cotiz_fu;
	}

	public void setMonto_cotiz_sc(long monto_cotiz_sc) {
		this.monto_cotiz_sc = monto_cotiz_sc;
	}

	public void setMonto_desahucio(long monto_desahucio) {
		this.monto_desahucio = monto_desahucio;
	}

	public void setMonto_documento(long monto_documento) {
		this.monto_documento = monto_documento;
	}

	public void setMonto_fp(long monto_fp) {
		this.monto_fp = monto_fp;
	}

	public void setMonto_renum_imponible(long monto_renum_imponible) {
		this.monto_renum_imponible = monto_renum_imponible;
	}

	public void setMonto_salud(long monto_salud) {
		this.monto_salud = monto_salud;
	}

	public void setMonto_salud_ad(long monto_salud_ad) {
		this.monto_salud_ad = monto_salud_ad;
	}

	public void setMonto_sub_dfl44_1978(long monto_sub_dfl44_1978) {
		this.monto_sub_dfl44_1978 = monto_sub_dfl44_1978;
	}

	public void setMonto_sub_pagado(long monto_sub_pagado) {
		this.monto_sub_pagado = monto_sub_pagado;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario.trim();
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador.trim();
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia.trim();
	}

	public void setNro_nacimientos(int nro_nacimientos) {
		this.nro_nacimientos = nro_nacimientos;
	}

	public void setNro_resolucion(String nro_resolucion) {
		this.nro_resolucion = nro_resolucion.trim();
	}

	public void setNum_dias_cotiz_pagados(int num_dias_cotiz_pagados) {
		this.num_dias_cotiz_pagados = num_dias_cotiz_pagados;
	}

	public void setNum_dias_licencia_autorizados(int num_dias_licencia_autorizados) {
		this.num_dias_licencia_autorizados = num_dias_licencia_autorizados;
	}

	public void setNum_dias_sub_pagadados(int num_dias_sub_pagadados) {
		this.num_dias_sub_pagadados = num_dias_sub_pagadados;
	}

	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento.trim();
	}

	public void setRun_beneficiario(String run_beneficiario) {
		this.run_beneficiario = run_beneficiario.trim();
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador.trim();
	}

	public void setSerie_documento(String serie_documento) {
		this.serie_documento = serie_documento.trim();
	}

	public void setSubsidio_iniciado(int subsidio_iniciado) {
		this.subsidio_iniciado = subsidio_iniciado;
	}

	public void setTipo_de_pago(int tipo_de_pago) {
		this.tipo_de_pago = tipo_de_pago;
	}

	public void setTipo_emision(String tipo_emision) {
		this.tipo_emision = tipo_emision.trim();
	}

	public void setVinculo_beneficiario_menor(int vinculo_beneficiario_menor) {
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
	}

	public String getCod_comuna_beneficiario() {
		return cod_comuna_beneficiario;
	}

	public void setCod_comuna_beneficiario(String cod_comuna_beneficiario) {
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getActividad_laboral_trabajador() {
		return actividad_laboral_trabajador;
	}

	public void setActividad_laboral_trabajador(String actividad_laboral_trabajador) {
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
	}

	public String getFecha_emision_documento_Char() {
		return fecha_emision_documento_Char;
	}

	public void setFecha_emision_documento_Char(String fecha_emision_documento_Char) {
		this.fecha_emision_documento_Char = fecha_emision_documento_Char;
	}

	public String getFecha_inicio_reposo_Char() {
		return fecha_inicio_reposo_Char;
	}

	public String getFecha_termino_reposo_Char() {
		return fecha_termino_reposo_Char;
	}

	public void setFecha_inicio_reposo_Char(String fecha_inicio_reposo_Char) {
		this.fecha_inicio_reposo_Char = fecha_inicio_reposo_Char;
	}

	public void setFecha_termino_reposo_Char(String fecha_termino_reposo_Char) {
		this.fecha_termino_reposo_Char = fecha_termino_reposo_Char;
	}

}
