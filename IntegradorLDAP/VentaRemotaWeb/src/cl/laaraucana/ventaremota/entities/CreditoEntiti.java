package cl.laaraucana.ventaremota.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "creditoRemoto")
public class CreditoEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCredito")
	private long idCredito;
	@Column(name = "numeroOferta")
	private long numeroOferta;
	@Column(name = "rutCliente")
	private long rutCliente;
	@Column(name = "dvCliente")
	private String dvCliente;
	@Column(name = "nombreCliente")
	private String nombreCliente;
	@Column(name = "fechaEnvio")
	private String fechaEnvio;
	@Column(name = "horaEnvio")
	private String horaEnvio;
	@Column(name = "numeroDocumento")
	private String numeroDocumento;
	@Column(name = "estado")
	private String estado;
	@Column(name = "tipoCreditoNormal")
	private String tipoCreditoNormal;
	@Column(name = "folioCredito")
	private String folioCredito;
	@Column(name = "montoSolicitado")
	private long montoSolicitado;
	@Column(name = "montoTotalSolicitado")
	private long montoTotalSolicitado;
	@Column(name = "montoComision")
	private long montoComision;
	@Column(name = "montoLiquido")
	private long montoLiquido;
	@Column(name = "formaPago")
	private String formaPago;
	@Column(name = "montoRenegociado")
	private long montoRenegociado;
	@Column(name = "montoPagoMora")
	private long montoPagoMora;
	@Column(name = "fechaOtorgamiento")
	private String fechaOtorgamiento;
	@Column(name = "montoCuota")
	private long montoCuota;
	@Column(name = "numeroCuotas")
	private int numeroCuotas;
	@Column(name = "tasaInteresMensual")
	private String tasaInteresMensual;
	@Column(name = "CAE")
	private String cae;
	@Column(name = "Impuesto")
	private long impuesto;
	@Column(name = "gastoNotarial")
	private long gastoNotarial;
	@Column(name = "seguroCesantia")
	private long seguroCesantia;
	@Column(name = "seguroDesgravamen")
	private long seguroDesgravamen;
	@Column(name = "montoCompraCartera")
	private long montoCompraCartera;
	
	//Se agregan nuevos parametros por set 3 preguntas
	@Column(name = "idTipoContrato")
	private String idTipoContrato;
	@Column(name = "descripcionTipoContrato")
	private String descripcionTipoContrato;
	@Column(name = "rentaDepurada")
	private long rentaDepurada;
	@Column(name = "numeroDireccion")
	private String numeroDireccion;
	@Column(name = "celular")
	private String celular;
	@Column(name = "codigoComuna")
	private String codigoComuna;
	@Column(name = "codigoTipoCuenta")
	private String codigoTipoCuenta;
	@Column(name = "codigoBanco")
	private String codigoBanco;
	@Column(name = "plazoCredito")
	private int plazoCredito;
	@Column(name = "rutEmpresa")
	private String rutEmpresa;
	@Column(name = "fechaNacimiento")
	private String fechaNacimiento;
	@Column(name = "regimenSalud")
	private String regimenSalud;
	@Column(name = "creditosVigentes")
	private String creditosVigentes;
	@Column(name = "tipoReprogramacion")
	private String tipoReprogramacion;
	@Column(name = "contratoCR")
	private String contratoCR;
	
	//Se agrega nuevo campo por Deudor Alimenticio
	@Column(name = "deudorAlimenticio")
	private String deudorAlimenticio;
	
	public long getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(long idCredito) {
		this.idCredito = idCredito;
	}
	public long getNumeroOferta() {
		return numeroOferta;
	}
	public void setNumeroOferta(long numeroOferta) {
		this.numeroOferta = numeroOferta;
	}
	public long getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}
	public String getDvCliente() {
		return dvCliente;
	}
	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
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
	public String getCae() {
		return cae;
	}
	public void setCae(String cae) {
		this.cae = cae;
	}
	public long getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(long impuesto) {
		this.impuesto = impuesto;
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
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
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
	
	public String toString(){
		StringBuffer sb= new StringBuffer();
		sb.append("idCredito:" + idCredito + ",");
		sb.append("numeroOferta:" + numeroOferta+ ",");
		sb.append("rutCliente:" + rutCliente+ ",");
		sb.append("dvCliente:" + dvCliente+ ",");
		sb.append("nombreCliente:" + nombreCliente+ ",");
		sb.append("fechaEnvio:" + fechaEnvio+ ",");
		sb.append("horaEnvio:" + horaEnvio+ ",");
		sb.append("numeroDocumento:" + numeroDocumento+ ",");
		sb.append("estado:" + estado+ ",");
		sb.append("tipoCreditoNormal:" + tipoCreditoNormal+ ",");
		sb.append("folioCredito:" + folioCredito+ ",");
		sb.append("montoSolicitado:" + montoSolicitado+ ",");
		sb.append("montoTotalSolicitado:" + montoTotalSolicitado+ ",");
		sb.append("montoComision:" + montoComision+ ",");
		sb.append("montoLiquido:" + montoLiquido+ ",");
		sb.append("formaPago:" + formaPago+ ",");
		sb.append("montoRenegociado:" + montoRenegociado+ ",");
		sb.append("montoPagoMora:" + montoPagoMora+ ",");
		sb.append("fechaOtorgamiento:" + fechaOtorgamiento+ ",");
		sb.append("montoCuota:" + montoCuota+ ",");
		sb.append("numeroCuotas:" + numeroCuotas+ ",");
		sb.append("tasaInteresMensual:" + tasaInteresMensual+ ",");
		sb.append("cae:" + cae+ ",");
		sb.append("impuesto:" + impuesto+ ",");
		sb.append("gastoNotarial:" + gastoNotarial+ ",");
		sb.append("seguroCesantia:" + seguroCesantia+ ",");
		sb.append("seguroDesgravamen:" + seguroDesgravamen+ ",");
		sb.append("montoCompraCartera:" + montoCompraCartera+ ",");
		sb.append("idTipoContrato:" + idTipoContrato+ ",");
		sb.append("descripcionTipoContrato:" + descripcionTipoContrato+ ",");
		sb.append("rentaDepurada:" + rentaDepurada+ ",");
		sb.append("numeroDireccion:" + numeroDireccion+ ",");
		sb.append("celular:" + celular+ ",");
		sb.append("codigoComuna:" + codigoComuna+ ",");
		sb.append("codigoTipoCuenta:" + codigoTipoCuenta+ ",");
		sb.append("codigoBanco:" + codigoBanco+ ",");
		sb.append("codigoBanco:" + codigoBanco+ ",");
		sb.append("rutEmpresa:" + rutEmpresa+ ",");
		sb.append("fechaNacimiento:" + fechaNacimiento+ ",");
		sb.append("regimenSalud:" + regimenSalud+ ",");
		sb.append("creditosVigentes:" + creditosVigentes+ ",");
		sb.append("tipoReprogramacion:" + tipoReprogramacion+ ",");
		sb.append("contratoCR:" + contratoCR + ",");
		sb.append("deudorAlimenticio:" + deudorAlimenticio);
		return sb.toString();
	}
	
	
}
