package cl.laaraucana.simat.entidades;

import java.sql.Date;

public class ControlDocuVO {
	private int idControlDocu;
	/*
	 * Corresponde al mes del Informe Financiero.
	 */
	private String mes_informacion;
	/*
	  * Se debe indicar el c�digo asociado a la entidad pagadora de subsidios, de acuerdo a 
	  * la codificaci�n del Anexo n� 3.
	  */
	private int codigo_entidad;
	/*
	  * C�digo del tipo de subsidio que el documento paga seg�n codificaci�n de Anexo n� 3.
	  */
	private int tipo_subsidio;
	/*
	  * Rol �nico Tributario del empleador con d�gito verificador.
	  */
	private String rut_empleador;
	/*
	  * Nombre o raz�n social del empleador.
	  */
	private String nombre_empleador;
	/*
	  * C�dula de identidad del beneficiario, con d�gito verificador.
	  */
	private String run_beneficiario;
	/*
	  * Nombre completo del beneficiario.
	  */
	private String nombre_beneficiario;
	/*
	  * C�digo de la modalidad de pago seg�n codificaci�n de Anexo n� 3, correspondiente a 
	  * cheque, orden de pago o d�posito.
	  */
	private int mod_pago;
	/*
	  * N�mero de serie del cheque, transferencia u orden de pago, seg�n corresponda.
	  */
	private String serie_documento;
	/*
	  * N�mero del documento que le corresponda a cada beneficiario, seg�n la modalidad de pago.
	  */
	private String num_documento;
	/*
	  * Fecha se�a�ada en el cheque u orden de pago, la que no puede ser diferente de la 
	  * fecha asignada para el pago de subsidio. En el caso de una transferencia electr�nica 
	  * o d�posito, corresponder� a la fecha de operaci�n.
	  */
	private Date fecha_emision_documento;

	private String fecha_emision_documento_Char;
	/*
	  * Monto de subsidio, cotizaci�n previsional o ambos, por el cual se extendi� el 
	  * documento, es decir, el cheque, orden de pago o transferencia eletr�nica.
	  */
	private long monto_documento;
	/*
	  * C�digo del banco asociado al cheque, orden de pago y transferencia electr�nica, 
	  * seg�n codificaci�n de Anexo n� 3.
	  */
	private String codigo_banco;
	/*
	  * C�digo del estado del documento emitido, seg�n codificaci�n de Anexo n� 3 para 
	  * las siguientes situaciones: cuando �ste es efectivamente cobrado por el beneficiario, 
	  * anulado o caducado. Por lo tanto, en el caso de documentos depositados o transferidos 
	  * electr�nicamente, se da por cobrado cuando se efect�a la transacci�n. En las 
	  * situaciones de cheques y ordenes de pago, se da por cobrado cuando el beneficiario 
	  * cobra efectivamente el beneficio.
	  */
	private int estado_documento;
	/*
	  * Fecha del cobro del documento, anulaci�n o caducidad, de acuerdo a lo se�alado 
	  * anteriormente.
	  */
	private Date fecha_cambio_estado;

	private String fecha_cambio_estado_Char;

	/*
	 * key para poder mostrar cantidad de registros limitados
	 * */
	private int keyInicio;
	private int keyFin;
	private int paginacion;

	public ControlDocuVO() {

	}

	public ControlDocuVO(int idControlDocu, String mes_informacion, int codigo_entidad, int tipo_subsidio, String rut_empleador, String nombre_empleador, String run_beneficiario,
			String nombre_beneficiario, int mod_pago, String serie_documento, String num_documento, Date fecha_emision_documento, long monto_documento, String codigo_banco, int estado_documento,
			Date fecha_cambio_estado) {
		super();
		this.idControlDocu = idControlDocu;
		this.mes_informacion = mes_informacion;
		this.codigo_entidad = codigo_entidad;
		this.tipo_subsidio = tipo_subsidio;
		this.rut_empleador = rut_empleador;
		this.nombre_empleador = nombre_empleador;
		this.run_beneficiario = run_beneficiario;
		this.nombre_beneficiario = nombre_beneficiario;
		this.mod_pago = mod_pago;
		this.serie_documento = serie_documento;
		this.num_documento = num_documento;
		this.fecha_emision_documento = fecha_emision_documento;
		this.monto_documento = monto_documento;
		this.codigo_banco = codigo_banco;
		this.estado_documento = estado_documento;
		this.fecha_cambio_estado = fecha_cambio_estado;
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

	public String getIdControlDocu() {
		return String.valueOf(idControlDocu);
	}

	public void setIdControlDocu(int idControlDocu) {
		this.idControlDocu = idControlDocu;
	}

	public int getCodigo_entidad() {
		return codigo_entidad;
	}

	public int getEstado_documento() {
		return estado_documento;
	}

	public Date getFecha_cambio_estado() {
		return fecha_cambio_estado;
	}

	public Date getFecha_emision_documento() {
		return fecha_emision_documento;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public int getMod_pago() {
		return mod_pago;
	}

	public long getMonto_documento() {
		return monto_documento;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public String getNombre_empleador() {
		return nombre_empleador;
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

	public int getTipo_subsidio() {
		return tipo_subsidio;
	}

	public void setCodigo_entidad(int codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public void setEstado_documento(int estado_documento) {
		this.estado_documento = estado_documento;
	}

	public void setFecha_cambio_estado(Date fecha_cambio_estado) {
		this.fecha_cambio_estado = fecha_cambio_estado;
	}

	public void setFecha_emision_documento(Date fecha_emision_documento) {
		this.fecha_emision_documento = fecha_emision_documento;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion.trim();
	}

	public void setMod_pago(int mod_pago) {
		this.mod_pago = mod_pago;
	}

	public void setMonto_documento(long monto_documento) {
		this.monto_documento = monto_documento;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario.trim();
	}

	public void setNombre_empleador(String nombre_empleador) {
		this.nombre_empleador = nombre_empleador.trim();
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

	public void setTipo_subsidio(int tipo_subsidio) {
		this.tipo_subsidio = tipo_subsidio;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getFecha_cambio_estado_Char() {
		return fecha_cambio_estado_Char;
	}

	public String getFecha_emision_documento_Char() {
		return fecha_emision_documento_Char;
	}

	public void setFecha_cambio_estado_Char(String fecha_cambio_estado_Char) {
		this.fecha_cambio_estado_Char = fecha_cambio_estado_Char;
	}

	public void setFecha_emision_documento_Char(String fecha_emision_documento_Char) {
		this.fecha_emision_documento_Char = fecha_emision_documento_Char;
	}

}
