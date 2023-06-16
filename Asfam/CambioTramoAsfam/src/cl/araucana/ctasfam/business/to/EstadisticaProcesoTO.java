package cl.araucana.ctasfam.business.to;

public class EstadisticaProcesoTO {
	private String oficina;
	private String nombreOficina;
	private String rutEmpresa;
	private String nombreEmpresa;
	private String totalDeclarado;
	private String totalNoInformado;
	private String totalPropuesta;
	private String porcentajePendiente;
	private String totalNuevo;
	
	
	public EstadisticaProcesoTO(String oficina, String nombreOficina, String rutEmpresa, String nombreEmpresa, String totalDeclarado, String totalNoInformado, String totalPropuesta, String totalNuevo, String porcentajePendiente) {
		super();
		this.oficina = oficina;
		this.nombreOficina = nombreOficina;
		this.rutEmpresa = rutEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.totalDeclarado = totalDeclarado;
		this.totalNoInformado = totalNoInformado;
		this.totalPropuesta = totalPropuesta;
		this.totalNuevo = totalNuevo;
		this.porcentajePendiente = porcentajePendiente;
	}


	public String getOficina() {
		return oficina;
	}


	public void setOficina(String oficina) {
		this.oficina = oficina;
	}


	public String getNombreOficina() {
		return nombreOficina;
	}


	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}


	public String getRutEmpresa() {
		return rutEmpresa;
	}


	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getTotalDeclarado() {
		return totalDeclarado;
	}


	public void setTotalDeclarado(String totalDeclarado) {
		this.totalDeclarado = totalDeclarado;
	}


	public String getTotalNoInformado() {
		return totalNoInformado;
	}


	public void setTotalNoInformado(String totalNoInformado) {
		this.totalNoInformado = totalNoInformado;
	}


	public String getTotalPropuesta() {
		return totalPropuesta;
	}


	public void setTotalPropuesta(String totalPropuesta) {
		this.totalPropuesta = totalPropuesta;
	}


	public String getPorcentajePendiente() {
		return porcentajePendiente;
	}


	public void setPorcentajePendiente(String porcentajePendiente) {
		this.porcentajePendiente = porcentajePendiente;
	}


	public String getTotalNuevo() {
		return totalNuevo;
	}


	public void setTotalNuevo(String totalNuevo) {
		this.totalNuevo = totalNuevo;
	}

	

	
}
