package cl.laaraucana.copagocredito.model;

import java.util.Date;

public class CreditoVo {

	private long idBita;

	private String rutCliente;

	private String dvCliente;

	private long nCredito;

	private long ncuota;

	private String valorCuota;

	private String montoBeneficio;

	private String correo;

	private String autorizado;

	private Date fecha;

	private String emailRechazo;

	private String emailDescarga;
	
	private String nombreCliente;
	
	private String serie;
	
	private String password;

	public long getIdBita() {
		return idBita;
	}

	public void setIdBita(long idBita) {
		this.idBita = idBita;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}

	public long getnCredito() {
		return nCredito;
	}

	public void setnCredito(long nCredito) {
		this.nCredito = nCredito;
	}

	public long getNcuota() {
		return ncuota;
	}

	public void setNcuota(long ncuota) {
		this.ncuota = ncuota;
	}

	public String getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}

	public String getMontoBeneficio() {
		return montoBeneficio;
	}

	public void setMontoBeneficio(String montoBeneficio) {
		this.montoBeneficio = montoBeneficio;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEmailRechazo() {
		return emailRechazo;
	}

	public void setEmailRechazo(String emailRechazo) {
		this.emailRechazo = emailRechazo;
	}

	public String getEmailDescarga() {
		return emailDescarga;
	}

	public void setEmailDescarga(String emailDescarga) {
		this.emailDescarga = emailDescarga;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
