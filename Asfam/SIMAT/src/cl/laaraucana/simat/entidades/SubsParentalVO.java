package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class SubsParentalVO {

	private int idParental;
	/*
	 * 'corresponde al mes del informe financiero.'
	 * */
	private String mes_informacion;
	/*
	 * 'se debe indicar el c�digo asociado a la entidad o instituci�n pagadora de subsidios, de acuerdo a la
	 *  codificaci�n del anexo n� 3.'
	 * */
	private int codigo_entidad;
	/*
	 * 'identificaci�n de surcusal o agencia de la entidad pagadora donde el beneficiario tramit� la licencia m�dica.'
	 * */
	private String agencia_entidad;
	/*
	 * 'rol �nico tributario del empleador con d�gito verificador. trat�ndose de trabajadores independientes se
	 *  deber� indicar el run del beneficiario.'
	 * */
	private String rut_empleador;
	/*
	 * 'nombre o raz�n social del empleador. trat�ndose de trabajadores independientes se deber� indicar el 
	 * nombre del beneficiario.  '
	 * */
	private String nombre_empleador;
	/*
	 * 'c�dula de identidad del beneficiario del subsidio por permiso postnatal parental, con d�gito verificador. 
	 * en caso de corresponder a un traspaso, se deber� indicar el run del padre.'
	 * */
	private String run_beneficiario_parental;
	/*
	 * 'nombres y apellidos del beneficiario del subsidio por permiso postnatal parental.'
	 * */
	private String nombre_beneficiario_parental;
	/*
	 * 'edad del beneficiario del subsidio por permiso postnatal parental'
	 * */
	private int edad;
	/*
	 * 'sexo del beneficiario del subsidio por permiso postnatal parental, seg�n codificaci�n de anexo n� 3.'
	 * */
	private String sexo;
	/*
	 * 'c�digo de la calidad del trabajdor, seg�n codificaci�n en anexo n� 3.',
	 * */
	private int calidad_trabajador;
	/*
	 * 'corresponde registrar el c�digo de la actividad laboral del trabajador (beneficiario del subsidio), de acuerdo a 
	 * codificaci�n de anexo n� 3.'
	 * */
	private String actividad_laboral_trabajador;
	/*
	 * 'c�digo de la comuna de residencia del beneficiario del subsidio por permiso postnatalparental seg�n codificaci�n 
	 * de anexo n� 3.'
	 * */
	private String cod_comuna_beneficiario;
	/*
	 * 'c�dula de identidad del beneficiario, con d�gito verificador, de la licencia postnatal que da origen al permiso
	 *  postnatal parental. en caso de corresponder a un transpaso al padre, se deber� indicar el run de la madre.'
	 * */
	private String run_beneficiario_postnatal;
	/*
	 * 'nombres y apellidos del beneficiario de la licencia postnatal que da origen al permiso postnatal parental.'
	 * */
	private String nombre_beneficiario_postnatal;
	/*
	 * 'n�mero de folio completo de la licencia m�dica p�stnatal que da origen al subsidio por permiso postnatal parental. el formato requerido es: c�digo de formulario de licencia m�dica (indicado en anexo n� 3), gu�on y el n�mero de la licencia sin d�gito verificador, inclueyendo ceros despu�s del gu�on, en caso que el n�mero tenga un largo inferior a 12, para completar el largo del campo. ej: "1-00001998d551"'
	 * */
	private String nro_licencia;
	/*
	 * 'c�digo del v�nculo que posee el beneficiario con el menor seg�n codificaci�n de anexo n� 3. en caso de 
	 * corresponder a subsidios por repososo prenatal, corresponder� informar el c�digo de dominio para madre.'
	 * */
	private int vinculo_beneficiario_menor;
	/*
	 * 'n�mero de la resoluci�n en caso que se tratre de un postnatal parental de un menor adoptado.'
	 * */
	private String nro_resolucion;
	/*
	 * 'c�digo del tipo de extensi�n del permiso postnatal parental, correspondiente a jornada parcial o completa 
	 * seg�n codificaci�n en anexo n� 3.'
	 * */
	private int tipo_extension_postnatal_parental;
	/*
	 * 'fecha en que se comienza a hacer uso ek beneficiario del permiso postnatal parental. trat�ndose de permiso 
	 * traspasados al padre, se deber� indicar la fecha en que �ste comienza a hacer uso del beneficio.'
	 * */
	private Date fecha_inicio_postnatal_parental;
	private String fecha_inicio_postnatal_parental_Char;
	/*
	 * 'fecha en que termina el permiso parental, de acuerdo a su extensi�n.',
	 * */
	private Date fecha_termino_postnatal_parental;
	private String fecha_termino_postnatal_parental_Char;
	/*
	 * 'n�mero de d�as de permiso postnatal pagados en este registro. '
	 * */
	private int num_dias_permiso_pagado;
	/*
	 * 'c�digo del tipo de pago, seg�n codificaci�n de anexo n� 3, para los casos que corresponde a un pago
	 *  directo al beneficiario o un reembolso a un empleador. en los casos en que a un empleador se le haya 
	 *  efectuado m�s de un reembolso en el mismo mes informado, se deber� incluir este empleador tantas veces como 
	 *  reembolsos tenga, incluy�ndose en cada caso los datos de los beneficiarios correspondientes.'
	 * */
	private int tipo_de_pago;

	/*
	 * 'monto del subsidio diario que resulte del c�lculo realizado de acuerdo con las normas legales vigente, 
	 * multiplicado por los d�as de subsidio a pagar indicados anteriormente. '
	 * */
	private long monto_sub_dfl44_1978;
	/*
	 * 'monto que efectivamente deb�o recibir el subsidiado en el mes que se informa, descontado el
	 *  monto de la cotizaci�n para el seguro de cesant�a en los casos que corresponda. cuando no proceda 
	 *  descontar cotizaci�n para el seguro de cesant�a el monto indicado en este campo deber� coincidir con 
	 *  el campo "monto_buc_dfl44_1978".4" '
	 * */
	private long monto_sub_pagado;
	/*
	 * 'c�digo del tipo de emisi�n del subsidio seg�n codificaci�n de anexo n� 3, correspondiente a un pago o una 
	 * reliquidaci�n, ya sea de subsidios o cotizaciones. cuando se reliqueden subsidios y cotizaciones,
	 *  se deber� informar en registro separados.'
	 * */
	private String tipo_emision;
	/*
	 * 'en el evento que exista una reliquidaci�n, y que corresponda est� a a�os anterirores al mes del informe, 
	 * se deber� registrar el mes y el a�o en que el beneficio fue inputado como pagado al fondo �nico. 
	 * en caso contrario se deber� dejar vac�o.'
	 * */
	private String mes_nomina;
	/*
	 * 'c�digo de la modalidad de pago seg�n codificaci�n de anexo n� 3, correspondientes a cheque, orden de pago u d�posito.'
	 * */
	private int mod_pago;
	/*
	 * 'n�mero de serie del cheque, transferencia u orden de pago, seg�n corresponda.'
	 * */
	private String serie_documento;
	/*
	 * 'n�mero del documento que le corresponda a cada beneficiario, seg�n la modalidad de pago.'
	 * */
	private String num_documento;
	/*
	 * 'fecha se�alada en el cheque u orden de pago, la que no puede ser diferente de la fecdha asignada para 
	 * el pago de subsidio. en el caso de una transferencia electr�nica o dep�sito, corresponder� a la fecha de la
	 *  operaci�n.'
	 * */
	private Date fecha_emision_documento;
	private String fecha_emision_documento_Char;
	/*
	 * 'monto por el cual se extendio el documento de pago: cheque, orden de pago o transferencia electr�nica.',
	 * */
	private long monto_documento;
	/*
	 * 'c�digo del banco asociado al cheque, orden de pago y transferencia electr�nica, seg�n codificaci�n de anexo n� 3.'
	 * */
	private String codigo_banco;
	/*
	 * 'cuenta corriente de dinde se transfierieron los fondos para el pago del subsidio.'
	 * */
	private String cta_cte;
	/*
	 * 'c�digo de la causual de emisi�n seg�n codificaci�n de anexo n� 3, para las siguientes situaciones: 
	 * por reemplazo de un documento previamente caducado; por reemplazo de un documento previamente anulado; 
	 * por pago normal; por cambio de un nombre del beneficiario; por fallecimiento o incapacidad de �ste.'
	 * */
	private int causal_emision;
	/*
	 * 'n�mero efectivo de d�as de cotizaci�n pagados cuyos 
	 * valor deber� coincidir con el n�mero de d�as de licencia m�dica devengados en el mes que se informa.'
	 * */
	private int num_dias_cotiz_pagados;

	/*
	 * 'monto de la remuneraci�n o renta imponible (en base a 30 d�as) del mes anterior al que se inicie la
	 *  licencia m�dica, o en su defecto, la estipulada en el contrato de trabajo.'
	 * */
	private long monto_remun_imponible;
	/*
	 * '-	monto que resulte de aplicar el porcentaje de cotizaci�n al fondo de pensiones que tenga establecido la 
	 * instituci�n previsional en la cual se encuentre afiliado el subsidiado (administradora de fondos de pensiones o
	 *  el respectivo r�gimen de pensi�n de administrado por el instituto de previsi�n social)),  sobre la remuneraci�n
	 *   imponible del mes anterior o en su defecto la estipulada en el contrato de trabajo (si es un trabajador 
	 *   dependiente) o la renta imponible (si es un trabajador independiente) del mes anterior al que se indica la 
	 *   licencia m�dica, dividida por 30  y multiplicada por el n�mero de d�as indicados en el campo anterior.'
	 * */
	private long monto_fp;
	/*
	 * '-	valor equivalente al 7% de la remuneraci�n o renta imponible del mes anterior al inicio de la licencia,
	 *  correspondiente al n�mero de d�as de incapacidad laboral.'
	 * */
	private long monto_salud;
	/*
	 * '-	diferencia entre monto correspondiente a aquella cotizaci�n superior que el trabajador haya pactado con
	 *  su isapre y el monto equivalente a la cotizaci�n del 7%.'
	 * */
	private long monto_salud_ad;
	/*
	 * '-	monto de cotizaci�n desahucio indemnizaciones la cual el subsidiado se encuentre afecto en relaci�n  con el referido beneficio.'
	 * */
	private long monto_desahucio;
	/*
	 * '-	monto correspondiente a la suma de los  4 campos anteriores.',
	 * */
	private long monto_cotiz_fu;
	/*
	 * '-	cantidad descontada del subsidio por concepto del 0,6% de cotizaci�n para el seguro de cesant�a, recaudada 
	 * por las administradoras de fondos  de pensiones para la a.f.c.'
	 * */
	private long monto_cotiz_sc;

	/*
	 * '-	c�digo de la entidad previsional de afiliaci�n del beneficiario seg�n codificaci�n en anexo n� 3.'
	 * */
	private int entidad_previsional;
	/*
	 * '-	se debe indicar cuando corresponde a un traspaso al padre seg�n codificaci�n en anexo n� 3.'
	 * */
	private int traspaso;
	/*
	 * '-	se deber� indicar si el subsidio es iniciado, en aquellos casos que el primer pago del subsidio se cobre
	 *  al fondo o no. en primer aquellos casos de traspasos de subsidios al padre no podr�n ser considerados como 
	 *  subsidios iniciados ya que fueron informados cuando la madre percibi� el subsidio. la codificaci�n del campo 
	 *  se encuentra en el anexo n� 3.'
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
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public SubsParentalVO() {
	}

	public SubsParentalVO(int idParental, String mes_informacion, int codigo_entidad, String agencia_entidad, String rut_empleador, String nombre_empleador, String run_beneficiario_parental,
			String nombre_beneficiario_parental, int edad, String sexo, int calidad_trabajador, String actividad_laboral_trabajador, String cod_comuna_beneficiario, String run_beneficiario_postnatal,
			String nombre_beneficiario_postnatal, String nro_licencia, int vinculo_beneficiario_menor, String nro_resolucion, int tipo_extension_postnatal_parental,
			Date fecha_inicio_postnatal_parental, Date fecha_termino_postnatal_parental, int num_dias_permiso_pagado, int tipo_de_pago, long monto_sub_dfl44_1978, long monto_sub_pagado,
			String tipo_emision, String mes_nomina, int mod_pago, String serie_documento, String num_documento, Date fecha_emision_documento, long monto_documento, String codigo_banco,
			String cta_cte, int causal_emision, int num_dias_cotiz_pagados, long monto_remun_imponible, long monto_fp, long monto_salud, long monto_salud_ad, long monto_desahucio,
			long monto_cotiz_fu, long monto_cotiz_sc, int entidad_previsional, int traspaso, int subsidio_iniciado, int indicador_convenio, int serv_salud, int keyInicio, int keyFin) {
		super();
		this.idParental = idParental;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.agencia_entidad = agencia_entidad;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.run_beneficiario_parental = run_beneficiario_parental;
		this.nombre_beneficiario_parental = nombre_beneficiario_parental;
		this.edad = edad;
		this.sexo = sexo;
		this.calidad_trabajador = calidad_trabajador;
		this.actividad_laboral_trabajador = actividad_laboral_trabajador;
		this.cod_comuna_beneficiario = cod_comuna_beneficiario;
		this.run_beneficiario_postnatal = run_beneficiario_postnatal;
		this.nombre_beneficiario_postnatal = nombre_beneficiario_postnatal;
		this.nro_licencia = nro_licencia;
		this.vinculo_beneficiario_menor = vinculo_beneficiario_menor;
		this.nro_resolucion = nro_resolucion;
		this.tipo_extension_postnatal_parental = tipo_extension_postnatal_parental;
		this.fecha_inicio_postnatal_parental = fecha_inicio_postnatal_parental;
		this.fecha_termino_postnatal_parental = fecha_termino_postnatal_parental;
		this.num_dias_permiso_pagado = num_dias_permiso_pagado;
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
		this.monto_remun_imponible = monto_remun_imponible;
		this.monto_fp = monto_fp;
		this.monto_salud = monto_salud;
		this.monto_salud_ad = monto_salud_ad;
		this.monto_desahucio = monto_desahucio;
		this.monto_cotiz_fu = monto_cotiz_fu;
		this.monto_cotiz_sc = monto_cotiz_sc;
		this.entidad_previsional = entidad_previsional;
		this.traspaso = traspaso;
		this.subsidio_iniciado = subsidio_iniciado;
		this.indicador_convenio = indicador_convenio;
		this.serv_salud = serv_salud;
		this.keyInicio = keyInicio;
		this.keyFin = keyFin;
	}

	public String getIdParental() {
		return String.valueOf(idParental);
	}

	public int getServ_salud() {
		return serv_salud;
	}

	public void setServ_salud(int serv_salud) {
		this.serv_salud = serv_salud;
	}

	//----------------------------------------------------------
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

	public void setIdParental(int idParental) {
		this.idParental = idParental;
	}

	public String getAgencia_entidad() {
		return agencia_entidad;
	}

	public int getCalidad_trabajador() {
		return calidad_trabajador;
	}

	public int getCausal_emision() {
		return causal_emision;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public String getCta_cte() {
		return cta_cte;
	}

	public int getEdad() {
		return edad;
	}

	public int getEntidad_previsional() {
		return entidad_previsional;
	}

	public Date getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public Date getFecha_inicio_postnatal_parental() {
		return fecha_inicio_postnatal_parental;
	}

	public Date getFecha_termino_postnatal_parental() {
		return fecha_termino_postnatal_parental;
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

	public long getMonto_remun_imponible() {
		return monto_remun_imponible;
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

	public String getNombre_beneficiario_parental() {
		return nombre_beneficiario_parental;
	}

	public String getNombre_beneficiario_postnatal() {
		return nombre_beneficiario_postnatal;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
	}

	public String getNro_licencia() {
		return nro_licencia;
	}

	public String getNro_resolucion() {
		return nro_resolucion;
	}

	public int getNum_dias_cotiz_pagados() {
		return num_dias_cotiz_pagados;
	}

	public int getNum_dias_permiso_pagado() {
		return num_dias_permiso_pagado;
	}

	public String getNum_documento() {
		return num_documento;
	}

	public String getRun_beneficiario_parental() {
		return run_beneficiario_parental;
	}

	public String getRun_beneficiario_postnatal() {
		return run_beneficiario_postnatal;
	}

	public String getRut_empleador() {
		return rut_empleador;
	}

	public String getSerie_documento() {
		return serie_documento;
	}

	public String getSexo() {
		return sexo;
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

	public int getTipo_extension_postnatal_parental() {
		return tipo_extension_postnatal_parental;
	}

	public int getTraspaso() {
		return traspaso;
	}

	public int getVinculo_beneficiario_menor() {
		return vinculo_beneficiario_menor;
	}

	public void setAgencia_entidad(String agencia_entidad) {
		this.agencia_entidad = agencia_entidad.trim();
	}

	public void setCalidad_trabajador(int calidad_trabajador) {
		this.calidad_trabajador = calidad_trabajador;
	}

	public void setCausal_emision(int causal_emision) {
		this.causal_emision = causal_emision;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setCta_cte(String cta_cte) {
		this.cta_cte = cta_cte.trim();
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setEntidad_previsional(int entidad_previsional) {
		this.entidad_previsional = entidad_previsional;
	}

	public void setFecha_emision_documento(Date fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public void setFecha_inicio_postnatal_parental(Date fecha_inicio_postnatal_parental) {
		this.fecha_inicio_postnatal_parental = fecha_inicio_postnatal_parental;
	}

	public void setFecha_termino_postnatal_parental(Date fecha_termino_postnatal_parental) {
		this.fecha_termino_postnatal_parental = fecha_termino_postnatal_parental;
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

	public void setMonto_remun_imponible(long monto_remun_imponible) {
		this.monto_remun_imponible = monto_remun_imponible;
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

	public void setNombre_beneficiario_parental(String nombre_beneficiario_parental) {
		this.nombre_beneficiario_parental = nombre_beneficiario_parental.trim();
	}

	public void setNombre_beneficiario_postnatal(String nombre_beneficiario_postnatal) {
		this.nombre_beneficiario_postnatal = nombre_beneficiario_postnatal.trim();
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador.trim();
	}

	public void setNro_licencia(String nro_licencia) {
		this.nro_licencia = nro_licencia.trim();
	}

	public void setNro_resolucion(String nro_resolucion) {
		this.nro_resolucion = nro_resolucion.trim();
	}

	public void setNum_dias_cotiz_pagados(int num_dias_cotiz_pagados) {
		this.num_dias_cotiz_pagados = num_dias_cotiz_pagados;
	}

	public void setNum_dias_permiso_pagado(int num_dias_permiso_pagado) {
		this.num_dias_permiso_pagado = num_dias_permiso_pagado;
	}

	public void setNum_documento(String num_documento) {
		this.num_documento = num_documento.trim();
	}

	public void setRun_beneficiario_parental(String run_beneficiario_parental) {
		this.run_beneficiario_parental = run_beneficiario_parental.trim();
	}

	public void setRun_beneficiario_postnatal(String run_beneficiario_postnatal) {
		this.run_beneficiario_postnatal = run_beneficiario_postnatal.trim();
	}

	public void setRut_empleador(String rut_empleador) {
		this.rut_empleador = rut_empleador.trim();
	}

	public void setSerie_documento(String serie_documento) {
		this.serie_documento = serie_documento.trim();
	}

	public void setSexo(String sexo) {
		this.sexo = sexo.trim();
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

	public void setTipo_extension_postnatal_parental(int tipo_extension_postnatal_parental) {
		this.tipo_extension_postnatal_parental = tipo_extension_postnatal_parental;
	}

	public void setTraspaso(int traspaso) {
		this.traspaso = traspaso;
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

	public String getFecha_emision_documento_Char() {
		return fecha_emision_documento_Char;
	}

	public String getFecha_inicio_postnatal_parental_Char() {
		return fecha_inicio_postnatal_parental_Char;
	}

	public String getFecha_termino_postnatal_parental_Char() {
		return fecha_termino_postnatal_parental_Char;
	}

	public void setFecha_emision_documento_Char(String fecha_emision_documento_Char) {
		this.fecha_emision_documento_Char = fecha_emision_documento_Char;
	}

	public void setFecha_inicio_postnatal_parental_Char(String fecha_inicio_postnatal_parental_Char) {
		this.fecha_inicio_postnatal_parental_Char = fecha_inicio_postnatal_parental_Char;
	}

	public void setFecha_termino_postnatal_parental_Char(String fecha_termino_postnatal_parental_Char) {
		this.fecha_termino_postnatal_parental_Char = fecha_termino_postnatal_parental_Char;
	}

}
