package cl.laaraucana.ventaremota.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditoDescripcion", propOrder = { "numeroOferta", "rutCliente", "nombreCliente",
		"fechaEnvio", "horaEnvio", "numeroDocumento", "estado", "tipoCreditoNormal", "folioCredito", "montoSolicitado",
		"montoTotalSolicitado", "montoComision", "montoLiquido", "formaPago", "montoRenegociado",
		"montoPagoMora", "fechaOtorgamiento", "montoCuota", "numeroCuotas", "tasaInteresMensual", "CAE", "Impuesto",
		"gastoNotarial", "seguroCesantia", "seguroDesgravamen", "montoCompraCartera",
		"idTipoContrato", "descripcionTipoContrato", "rentaDepurada", "numeroDireccion", "celular", "codigoComuna",
		"codigoTipoCuenta", "codigoBanco", "plazoCredito",
		"rutEmpresa", "fechaNacimiento", "regimenSalud", "creditosVigentes", "tipoReprogramacion", "contratoCR", "deudorAlimenticio"})

public class CreditoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "NUMERO_OFERTA", required = true)
	private long numeroOferta;
	@XmlElement(name = "RUT_CLIENTE", required = true)
	private String rutCliente;
	@XmlElement(name = "NOMBRE_CLIENTE", required = true)
	private String nombreCliente;
	@XmlElement(name = "FECHA_ENVIO", required = true)
	private String fechaEnvio;
	@XmlElement(name = "HORA_ENVIO", required = true)
	private String horaEnvio;
	@XmlElement(name = "NUMERO_DOCUMENTO", required = true)
	private String numeroDocumento;
	@XmlElement(name = "ESTADO", required = true)
	private String estado;
	@XmlElement(name = "TIPO_DE_CREDITO", required = true)
	private String tipoCreditoNormal;
	@XmlElement(name = "FOLIO_CREDITO", required = true)
	private String folioCredito;
	@XmlElement(name = "MONTO_SOLIICITADO", required = true)
	private long montoSolicitado;
	@XmlElement(name = "MONTO_TOTAL_SOLIICITADO", required = true)
	private long montoTotalSolicitado;
	@XmlElement(name = "MONTO_COMISION", required = true)
	private long montoComision;
	@XmlElement(name = "MONTO_LIQUIDO", required = true)
	private long montoLiquido;
	@XmlElement(name = "FORMA_PAGO", required = true)
	private String formaPago;
	@XmlElement(name = "MONTO_RENEGOCIADO", required = true)
	private long montoRenegociado;
	@XmlElement(name = "MONTO_PAGO_MORA", required = true)
	private long montoPagoMora;
	@XmlElement(name = "FECHA_OTORGAMIENTO", required = true)
	private String fechaOtorgamiento;
	@XmlElement(name = "MONTO_CUOTA", required = true)
	private long montoCuota;
	@XmlElement(name = "NUMERO_CUOTAS", required = true)
	private int numeroCuotas;
	@XmlElement(name = "TASA_INTERES", required = true)
	private String tasaInteresMensual;
	@XmlElement(name = "CAE", required = true)
	private String CAE;
	@XmlElement(name = "IMPUESTO", required = true)
	private long Impuesto;
	@XmlElement(name = "GASTO_NOTARIAL", required = true)
	private long gastoNotarial;
	@XmlElement(name = "SEGURO_CESANTIA", required = true)
	private long seguroCesantia;
	@XmlElement(name = "SEGURO_DESGRAVAMEN", required = true)
	private long seguroDesgravamen;
	@XmlElement(name = "MONTO_CARTERA", required = true)
	private long montoCompraCartera;
	//Se agregan nuevos parametros por set 3 preguntas
	@XmlElement(name = "ID_TIPO_CONTRATO", required = true)
	private String idTipoContrato;
	@XmlElement(name = "DESCRIPCION_TIPO_CONTRATO", required = true)
	private String descripcionTipoContrato;
	@XmlElement(name = "RENTA_DEPURADA", required = true)
	private long rentaDepurada;
	@XmlElement(name = "NUMERO_DIRECCION", required = true)
	private String numeroDireccion;
	@XmlElement(name = "CELULAR", required = true)
	private long celular;
	@XmlElement(name = "CODIGO_COMUNA", required = true)
	private String codigoComuna;
	@XmlElement(name = "CODIGO_TIPO_CUENTA", required = true)
	private String codigoTipoCuenta;
	@XmlElement(name = "CODIGO_BANCO", required = true)
	private String codigoBanco;
	@XmlElement(name = "PLAZO_CREDITO", required = true)
	private int plazoCredito;
	@XmlElement(name = "RUT_EMPRESA", required = true)
	private String rutEmpresa;
	@XmlElement(name = "FECHA_NACIMIENTO", required = true)
	private String fechaNacimiento;
	@XmlElement(name = "REGIMEN_SALUD", required = true)
	private String regimenSalud;
	@XmlElement(name = "CREDITOS_VIGENTES", required = true)
	private String creditosVigentes;
	@XmlElement(name = "TIPO_REPROGRAMACION", required = true)
	private String tipoReprogramacion;
	@XmlElement(name = "CONTRATO_USO_CANALES", required = true)
	private String contratoCR;
	@XmlElement(name = "DEUDOR_ALIMENTICIO", required = true)
	private String deudorAlimenticio;

	public long getNumeroOferta() {
		return numeroOferta;
	}

	public void setNumeroOferta(long numeroOferta) {
		this.numeroOferta = numeroOferta;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getHoraEnvio() {
		return horaEnvio;
	}

	public void setHoraEnvio(String horaEnvio) {
		this.horaEnvio = horaEnvio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoCreditoNormal() {
		return tipoCreditoNormal;
	}

	public void setTipoCreditoNormal(String tipoCreditoNormal) {
		this.tipoCreditoNormal = tipoCreditoNormal;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public long getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(long montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public long getMontoTotalSolicitado() {
		return montoTotalSolicitado;
	}

	public void setMontoTotalSolicitado(long montoTotalSolicitado) {
		this.montoTotalSolicitado = montoTotalSolicitado;
	}

	public long getMontoComision() {
		return montoComision;
	}

	public void setMontoComision(long montoComision) {
		this.montoComision = montoComision;
	}

	public long getMontoLiquido() {
		return montoLiquido;
	}

	public void setMontoLiquido(long montoLiquido) {
		this.montoLiquido = montoLiquido;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public long getMontoRenegociado() {
		return montoRenegociado;
	}

	public void setMontoRenegociado(long montoRenegociado) {
		this.montoRenegociado = montoRenegociado;
	}

	public long getMontoPagoMora() {
		return montoPagoMora;
	}

	public void setMontoPagoMora(long montoPagoMora) {
		this.montoPagoMora = montoPagoMora;
	}

	public String getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	public void setFechaOtorgamiento(String fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}

	public long getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(long montoCuota) {
		this.montoCuota = montoCuota;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getTasaInteresMensual() {
		return tasaInteresMensual;
	}

	public void setTasaInteresMensual(String tasaInteresMensual) {
		this.tasaInteresMensual = tasaInteresMensual;
	}

	public String getCAE() {
		return CAE;
	}

	public void setCAE(String cae) {
		CAE = cae;
	}

	public long getImpuesto() {
		return Impuesto;
	}

	public void setImpuesto(long impuesto) {
		Impuesto = impuesto;
	}

	public long getGastoNotarial() {
		return gastoNotarial;
	}

	public void setGastoNotarial(long gastoNotarial) {
		this.gastoNotarial = gastoNotarial;
	}

	public long getSeguroCesantia() {
		return seguroCesantia;
	}

	public void setSeguroCesantia(long seguroCesantia) {
		this.seguroCesantia = seguroCesantia;
	}

	public long getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(long seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public long getMontoCompraCartera() {
		return montoCompraCartera;
	}

	public void setMontoCompraCartera(long montoCompraCartera) {
		this.montoCompraCartera = montoCompraCartera;
	}

	/**
	 * @return the idTipoContrato
	 */
	public String getIdTipoContrato() {
		return idTipoContrato;
	}

	/**
	 * @param idTipoContrato the idTipoContrato to set
	 */
	public void setIdTipoContrato(String idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	/**
	 * @return the descripcionTipoContrato
	 */
	public String getDescripcionTipoContrato() {
		return descripcionTipoContrato;
	}

	/**
	 * @param descripcionTipoContrato the descripcionTipoContrato to set
	 */
	public void setDescripcionTipoContrato(String descripcionTipoContrato) {
		this.descripcionTipoContrato = descripcionTipoContrato;
	}

	/**
	 * @return the rentaDepurada
	 */
	public long getRentaDepurada() {
		return rentaDepurada;
	}

	/**
	 * @param rentaDepurada the rentaDepurada to set
	 */
	public void setRentaDepurada(long rentaDepurada) {
		this.rentaDepurada = rentaDepurada;
	}

	/**
	 * @return the numeroDireccion
	 */
	public String getNumeroDireccion() {
		return numeroDireccion;
	}

	/**
	 * @param numeroDireccion the numeroDireccion to set
	 */
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	/**
	 * @return the celular
	 */
	public long getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(long celular) {
		this.celular = celular;
	}

	/**
	 * @return the codigoComuna
	 */
	public String getCodigoComuna() {
		return codigoComuna;
	}

	/**
	 * @param codigoComuna the codigoComuna to set
	 */
	public void setCodigoComuna(String codigoComuna) {
		this.codigoComuna = codigoComuna;
	}

	/**
	 * @return the codigoTipoCuenta
	 */
	public String getCodigoTipoCuenta() {
		return codigoTipoCuenta;
	}

	/**
	 * @param codigoTipoCuenta the codigoTipoCuenta to set
	 */
	public void setCodigoTipoCuenta(String codigoTipoCuenta) {
		this.codigoTipoCuenta = codigoTipoCuenta;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the plazoCredito
	 */
	public int getPlazoCredito() {
		return plazoCredito;
	}

	/**
	 * @param plazoCredito the plazoCredito to set
	 */
	public void setPlazoCredito(int plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the regimenSalud
	 */
	public String getRegimenSalud() {
		return regimenSalud;
	}

	/**
	 * @param regimenSalud the regimenSalud to set
	 */
	public void setRegimenSalud(String regimenSalud) {
		this.regimenSalud = regimenSalud;
	}

	/**
	 * @return the creditosVigentes
	 */
	public String getCreditosVigentes() {
		return creditosVigentes;
	}

	/**
	 * @param creditosVigentes the creditosVigentes to set
	 */
	public void setCreditosVigentes(String creditosVigentes) {
		this.creditosVigentes = creditosVigentes;
	}

	/**
	 * @return the tipoReprogramacion
	 */
	public String getTipoReprogramacion() {
		return tipoReprogramacion;
	}

	/**
	 * @param tipoReprogramacion the tipoReprogramacion to set
	 */
	public void setTipoReprogramacion(String tipoReprogramacion) {
		this.tipoReprogramacion = tipoReprogramacion;
	}

	/**
	 * @return the contratoCR
	 */
	public String getContratoCR() {
		return contratoCR;
	}

	/**
	 * @param contratoCR the contratoCR to set
	 */
	public void setContratoCR(String contratoCR) {
		this.contratoCR = contratoCR;
	}

	/**
	 * @return the deudorAlimenticio
	 */
	public String getDeudorAlimenticio() {
		return deudorAlimenticio;
	}

	/**
	 * @param deudorAlimenticio the deudorAlimenticio to set
	 */
	public void setDeudorAlimenticio(String deudorAlimenticio) {
		this.deudorAlimenticio = deudorAlimenticio;
	}
	
	
	
}
