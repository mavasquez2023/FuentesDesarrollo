package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.CargaManualDet", schema = "TEFDTA")
public class DetalleManualEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDetalle")
	private long idDetalle;
	@Column(name = "IdCabecera")
	private long idCabecera;
	@Column(name = "rutAfi")
	private int rutAfiliado;
	@Column(name = "dvAfi")
	private String dvAfiliado;
	@Column(name = "NomAfi")
	private String nombreAfiliado;
	@Column(name = "DesPago")
	private String descripcionPago;
	@Column(name = "MontoPago")
	private long montoPago;
	@Column(name = "IdEstado")
	private int estado;
	@Column(name = "BancoAfi")
	private String bancoAfiliado;
	@Column(name = "NUMCUENTA")
	private String numeroCuenta;
	@Column(name = "ID_TIPCTA")
	private int tipoCuenta;
	@Column(name = "Correo")
	private String email;
	@Column(name = "DesRechBan")
	private String descripcionRechazo;
	@Column(name = "FechaTrans")
	private Date fechaTransferencia;
	@Column(name = "NomNomina")
	private String nombreNomina;
	@Column(name = "FolNomiBan")
	private long folioNomina;
	@Column(name = "CodBene")
	private String beneficio;
	@Column(name = "Referencia1")
	private String referencia1;
	@Column(name = "Referencia2")
	private String referencia2;
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
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
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
	 * @return the montoPago
	 */
	public long getMontoPago() {
		return montoPago;
	}
	/**
	 * @param montoPago the montoPago to set
	 */
	public void setMontoPago(long montoPago) {
		this.montoPago = montoPago;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the bancoAfiliado
	 */
	public String getBancoAfiliado() {
		return bancoAfiliado;
	}
	/**
	 * @param bancoAfiliado the bancoAfiliado to set
	 */
	public void setBancoAfiliado(String bancoAfiliado) {
		this.bancoAfiliado = bancoAfiliado;
	}
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	 * @return the fechaTransferencia
	 */
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}
	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}
	/**
	 * @return the nombreNomina
	 */
	public String getNombreNomina() {
		return nombreNomina;
	}
	/**
	 * @param nombreNomina the nombreNomina to set
	 */
	public void setNombreNomina(String nombreNomina) {
		this.nombreNomina = nombreNomina;
	}
	/**
	 * @return the folioNomina
	 */
	public long getFolioNomina() {
		return folioNomina;
	}
	/**
	 * @param folioNomina the folioNomina to set
	 */
	public void setFolioNomina(long folioNomina) {
		this.folioNomina = folioNomina;
	}
	/**
	 * @return the beneficio
	 */
	public String getBeneficio() {
		return beneficio;
	}
	/**
	 * @param beneficio the beneficio to set
	 */
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
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
	
	
}
