package cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO;


public class SalidaAfiliadoVO {
	private String rutEmpresa;
	private String nombreCompleto;
	private String fechaAfiliacion;
	private String estadoAfiliacion;
	private String rol;
	private String razonSocial;
	
	public SalidaAfiliadoVO (){}
	


	public SalidaAfiliadoVO(String rutEmpresa, String nombreCompleto, String fechaAfiliacion, String estadoAfiliacion, String rol, String razonSocial) {
		super();
		this.rutEmpresa = rutEmpresa;
		this.nombreCompleto = nombreCompleto;
		this.fechaAfiliacion = fechaAfiliacion;
		this.estadoAfiliacion = estadoAfiliacion;
		this.rol = rol;
		this.razonSocial = razonSocial;
	}



	

	public String getRutEmpresa() {
		return rutEmpresa;
	}



	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}



	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public String getEstadoAfiliacion() {
		return estadoAfiliacion;
	}

	public void setEstadoAfiliacion(String estadoAfiliacion) {
		this.estadoAfiliacion = estadoAfiliacion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
}
