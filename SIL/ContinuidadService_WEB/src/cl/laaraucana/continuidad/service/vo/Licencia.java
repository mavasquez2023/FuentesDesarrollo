package cl.laaraucana.continuidad.service.vo;

public class Licencia {
	private String tipo;
	private String licencia;
	private String fechaDesde;
	private String fechaHasta;
	private String tipoLicencia;
	private String cantidadDias;
	private String rutPadreParental;
	public String getTipo() {
		return tipo;
	}
	public String getLicencia() {
		return licencia;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public String getTipoLicencia() {
		return tipoLicencia;
	}
	public String getCantidadDias() {
		return cantidadDias;
	}
	public String getRutPadreParental() {
		return rutPadreParental;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	public void setCantidadDias(String cantidadDias) {
		this.cantidadDias = cantidadDias;
	}
	public void setRutPadreParental(String rutPadreParental) {
		this.rutPadreParental = rutPadreParental;
	}
}
