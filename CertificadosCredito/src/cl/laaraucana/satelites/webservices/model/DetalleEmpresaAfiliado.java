package cl.laaraucana.satelites.webservices.model;

public class DetalleEmpresaAfiliado {

	private String nombreEmpresa;
	private String rutEmpresa;
	private String fechaAfiliacion;
	private String tipoAfiliado;
	private String estadoAfiliacion;
	private String fechaRol;

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getEstadoAfiliacion() {
		return estadoAfiliacion;
	}

	public void setEstadoAfiliacion(String estadoAfiliacion) {
		this.estadoAfiliacion = estadoAfiliacion;
	}

	/**
	 * @return the fechaRol
	 */
	public String getFechaRol() {
		return fechaRol;
	}

	/**
	 * @param fechaRol the fechaRol to set
	 */
	public void setFechaRol(String fechaRol) {
		this.fechaRol = fechaRol;
	}
	
	
}
