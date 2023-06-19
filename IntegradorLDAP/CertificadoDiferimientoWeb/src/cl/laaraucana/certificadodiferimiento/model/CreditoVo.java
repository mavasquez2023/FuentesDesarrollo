package cl.laaraucana.certificadodiferimiento.model;


public class CreditoVo {

	private long idCredito;

	private String rutCliente;

	private String folioCredito;

	private String cuotaDiferir;

	private String montoCuota;

	private String fechaVencActual;

	private String fechaVencNuevo;

	private String rutEmpresa;

	private String dvEmpresa;

	private String nombreEmpresa;

	private String nombreCliente;

	private String serie;
	
	private String email;
	
	private String emailRechazo;
	
	private String emailDescarga;

	public long getIdCredito() {
		return idCredito;
	}

	public void setIdCredito(long idCredito) {
		this.idCredito = idCredito;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public String getCuotaDiferir() {
		return cuotaDiferir;
	}

	public void setCuotaDiferir(String cuotaDiferir) {
		this.cuotaDiferir = cuotaDiferir;
	}

	public String getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	public String getFechaVencActual() {
		return fechaVencActual;
	}

	public void setFechaVencActual(String fechaVencActual) {
		this.fechaVencActual = fechaVencActual;
	}

	public String getFechaVencNuevo() {
		return fechaVencNuevo;
	}

	public void setFechaVencNuevo(String fechaVencNuevo) {
		this.fechaVencNuevo = fechaVencNuevo;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getDvEmpresa() {
		return dvEmpresa;
	}

	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailRechazo() {
		return emailRechazo;
	}

	public void setEmailRechazo(String emailRechazo) {
		this.emailRechazo = emailRechazo;
	}

	/**
	 * @return the emailDescarga
	 */
	public String getEmailDescarga() {
		return emailDescarga;
	}

	/**
	 * @param emailDescarga the emailDescarga to set
	 */
	public void setEmailDescarga(String emailDescarga) {
		this.emailDescarga = emailDescarga;
	}
	
	
	

}
