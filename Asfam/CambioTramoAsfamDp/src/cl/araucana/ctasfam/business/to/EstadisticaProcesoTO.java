package cl.araucana.ctasfam.business.to;

public class EstadisticaProcesoTO {
	private String oficina;
	private String nombreOficina;
	private String rutEmpresa;
	private String nombreEmpresa;
	private String porcentajeInformado;
	private String totalInformado;
	private String porcentajePendiente;
	private String totalPendiente;
	private String total;
	
	
	public EstadisticaProcesoTO(String oficina, String nombreOficina, String rutEmpresa, String nombreEmpresa, String porcentajeInformado, String totalInformado, String porcentajePendiente, String totalPendiente, String total) {
		super();
		this.oficina = oficina;
		this.nombreOficina = nombreOficina;
		this.rutEmpresa = rutEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.porcentajeInformado = porcentajeInformado;
		this.totalInformado = totalInformado;
		this.porcentajePendiente = porcentajePendiente;
		this.totalPendiente = totalPendiente;
		this.total = total;
	}


	public EstadisticaProcesoTO(){
		super();
		this.oficina = null;
		this.nombreOficina = null;
		this.rutEmpresa = null;
		this.nombreEmpresa = null;
		this.porcentajeInformado = null;
		this.totalInformado = null;
		this.porcentajePendiente = null;
		this.totalPendiente = null;
		this.total = null;
	}
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreOficina() {
		return nombreOficina;
	}
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getPorcentajeInformado() {
		return porcentajeInformado;
	}
	public void setPorcentajeInformado(String porcentajeInformado) {
		this.porcentajeInformado = porcentajeInformado;
	}
	public String getPorcentajePendiente() {
		return porcentajePendiente;
	}
	public void setPorcentajePendiente(String porcentajePendiente) {
		this.porcentajePendiente = porcentajePendiente;
	}
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalInformado() {
		return totalInformado;
	}
	public void setTotalInformado(String totalInformado) {
		this.totalInformado = totalInformado;
	}
	public String getTotalPendiente() {
		return totalPendiente;
	}
	public void setTotalPendiente(String totalPendiente) {
		this.totalPendiente = totalPendiente;
	}
	
	
}
