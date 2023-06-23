package cl.laaraucana.capaservicios.persistencia.vo;

public class DetalleRendicion {
	private String idRendicion;
	private String idDetalleRendicion;
	private String periodo;
	private String fechaVencimiento;
	private String rut;
	//private String folioTesoreria;
	private String folioCredito;
	private String montoPago;
	private String fechaContable;
	private String fechaPago;
	private String estadoRecup;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
/*	public String getFolioTesoreria() {
		return folioTesoreria;
	}
	public void setFolioTesoreria(String folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}*/
	public String getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}
	public String getMontoPago() {
		return montoPago;
	}
	public void setMontoPago(String montoPago) {
		this.montoPago = montoPago;
	}
	public String getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(String idRendicion) {
		this.idRendicion = idRendicion;
	}
	public String getFechaContable() {
		return fechaContable;
	}
	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getEstadoRecup() {
		return estadoRecup;
	}
	public void setEstadoRecup(String estadoRecup) {
		this.estadoRecup = estadoRecup;
	}
	public String getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(String idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
