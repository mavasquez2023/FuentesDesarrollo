package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dbo.NominaTefDet", schema = "TEFDTA")
public class DetalleEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idDetalle;
	@Column(name = "idCabecera")
	private long idCabecera;
	@Column(name = "Codbene")
	private String codigoBeneficio;
	@Column(name = "DescPago")
	private String descripcionPago;
	@Column(name = "RutAfiliado")
	private int rutAfiliado;
	@Column(name = "dvAfiliado")
	private String dvAfiliado;
	@Column(name = "Nombres")
	private String nombres;
	@Column(name = "CodBanco")
	private String codigoBanco;
	@Column(name = "DescBanco")
	private String descripcionBanco;
	@Column(name = "Cuenta")
	private long numerocuenta;
	@Column(name = "TipoCuenta")
	private int tipoCuenta;
	@Column(name = "Correo")
	private String email;
	@Column(name = "CodFormaPago")
	private String codigoFormaPago;
	@Column(name = "DescFormapago")
	private String descripcionFormaPago;
	@Column(name = "Monto")
	private long monto;
	@Column(name = "EstadoPago")
	private int estadoPago;
	@Column(name = "DescEstadoPago")
	private String descripcionEstadoPago;
	@Column(name = "DescRechazo")
	private String descripcionRechazo;
	@Column(name = "Referencia1")
	private String referencia1;
	@Column(name = "Referencia2")
	private String referencia2;
	@Column(name = "FechaCambio")
	private Date fechaCambio;
	@Column(name = "idReferencia")
	private long idReferencia;
	
	@Column(name = "CodigoNomina")
	private Long codigoNomina;
	
	/**
	 * @return the idDetalle
	 */
	public long getIdDetalle() {
		return idDetalle;
	}
	/**
	 * @param idDetalle the idDetalle to set
	 */
	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}
	/**
	 * @return the idCabecera
	 */
	public long getIdCabecera() {
		return idCabecera;
	}
	/**
	 * @param idCabecera the idCabecera to set
	 */
	public void setIdCabecera(long idCabecera) {
		this.idCabecera = idCabecera;
	}
	/**
	 * @return the codigoBeneficio
	 */
	public String getCodigoBeneficio() {
		return codigoBeneficio;
	}
	/**
	 * @param codigoBeneficio the codigoBeneficio to set
	 */
	public void setCodigoBeneficio(String codigoBeneficio) {
		this.codigoBeneficio = codigoBeneficio;
	}
	/**
	 * @return the descripcionPago
	 */
	public String getDescripcionPago() {
		return descripcionPago;
	}
	/**
	 * @param descripcionPago the descripcionPago to set
	 */
	public void setDescripcionPago(String descripcionPago) {
		this.descripcionPago = descripcionPago;
	}
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	 * @return the descripcionBanco
	 */
	public String getDescripcionBanco() {
		return descripcionBanco;
	}
	/**
	 * @param descripcionBanco the descripcionBanco to set
	 */
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}
	/**
	 * @return the numerocuenta
	 */
	public long getNumerocuenta() {
		return numerocuenta;
	}
	/**
	 * @param numerocuenta the numerocuenta to set
	 */
	public void setNumerocuenta(long numerocuenta) {
		this.numerocuenta = numerocuenta;
	}
	/**
	 * @return the tipoCuenta
	 */
	public int getTipoCuenta() {
		return tipoCuenta;
	}
	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the codigoFormaPago
	 */
	public String getCodigoFormaPago() {
		return codigoFormaPago;
	}
	/**
	 * @param codigoFormaPago the codigoFormaPago to set
	 */
	public void setCodigoFormaPago(String codigoFormaPago) {
		this.codigoFormaPago = codigoFormaPago;
	}
	/**
	 * @return the descripcionFormaPago
	 */
	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}
	/**
	 * @param descripcionFormaPago the descripcionFormaPago to set
	 */
	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}
	/**
	 * @return the monto
	 */
	public long getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}
	/**
	 * @return the estadoPago
	 */
	public int getEstadoPago() {
		return estadoPago;
	}
	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(int estadoPago) {
		this.estadoPago = estadoPago;
	}
	/**
	 * @return the descripcionEstadoPago
	 */
	public String getDescripcionEstadoPago() {
		return descripcionEstadoPago;
	}
	/**
	 * @param descripcionEstadoPago the descripcionEstadoPago to set
	 */
	public void setDescripcionEstadoPago(String descripcionEstadoPago) {
		this.descripcionEstadoPago = descripcionEstadoPago;
	}
	/**
	 * @return the descripcionRechazo
	 */
	public String getDescripcionRechazo() {
		return descripcionRechazo;
	}
	/**
	 * @param descripcionRechazo the descripcionRechazo to set
	 */
	public void setDescripcionRechazo(String descripcionRechazo) {
		this.descripcionRechazo = descripcionRechazo;
	}
	/**
	 * @return the referencia1
	 */
	public String getReferencia1() {
		return referencia1;
	}
	/**
	 * @param referencia1 the referencia1 to set
	 */
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	/**
	 * @return the referencia2
	 */
	public String getReferencia2() {
		return referencia2;
	}
	/**
	 * @param referencia2 the referencia2 to set
	 */
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}
	/**
	 * @return the fechaCambio
	 */
	public Date getFechaCambio() {
		return fechaCambio;
	}
	/**
	 * @param fechaCambio the fechaCambio to set
	 */
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	/**
	 * @return the idReferencia
	 */
	public long getIdReferencia() {
		return idReferencia;
	}
	/**
	 * @param idReferencia the idReferencia to set
	 */
	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}
	/**
	 * @return the codigoNomina
	 */
	public Long getCodigoNomina() {
		return codigoNomina;
	}
	/**
	 * @param codigoNomina the codigoNomina to set
	 */
	public void setCodigoNomina(Long codigoNomina) {
		this.codigoNomina = codigoNomina;
	}
	@Override
	public String toString() {
		return "DetalleEntity [idDetalle=" + idDetalle + ", idCabecera=" + idCabecera + ", codigoBeneficio="
				+ codigoBeneficio + ", descripcionPago=" + descripcionPago + ", rutAfiliado=" + rutAfiliado
				+ ", dvAfiliado=" + dvAfiliado + ", nombres=" + nombres + ", codigoBanco=" + codigoBanco
				+ ", descripcionBanco=" + descripcionBanco + ", numerocuenta=" + numerocuenta + ", tipoCuenta="
				+ tipoCuenta + ", email=" + email + ", codigoFormaPago=" + codigoFormaPago + ", descripcionFormaPago="
				+ descripcionFormaPago + ", monto=" + monto + ", estadoPago=" + estadoPago + ", descripcionEstadoPago="
				+ descripcionEstadoPago + ", descripcionRechazo=" + descripcionRechazo + ", referencia1=" + referencia1
				+ ", referencia2=" + referencia2 + ", fechaCambio=" + fechaCambio + ", idReferencia=" + idReferencia
				+ ", codigoNomina=" + codigoNomina + "]";
	}
	
	
	}
