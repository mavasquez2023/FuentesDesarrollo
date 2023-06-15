package cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo;

import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class SalidaQueryBPVO extends AbstractSalidaVO {

	private String rutEmpresa;
	private String nombreCompleto;
	private String fechaAfiliacion;
	private String estadoAfiliacion;
	private String rol;
	private String razonSocial;

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public SalidaQueryBPVO(String rutEmpresa, String nombreCompleto, String fechaAfiliacion, String estadoAfiliacion, String rol, String razonSocial) {
		super();
		this.rutEmpresa = rutEmpresa;
		this.nombreCompleto = nombreCompleto;
		this.fechaAfiliacion = fechaAfiliacion;
		this.estadoAfiliacion = estadoAfiliacion;
		this.rol = rol;
		this.razonSocial = razonSocial;
	}

	public String getEstadoAfiliacion() {
		return estadoAfiliacion;
	}

	public void setEstadoAfiliacion(String estadoAfiliacion) {
		this.estadoAfiliacion = estadoAfiliacion;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public String getRol() {
		return rol;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}
