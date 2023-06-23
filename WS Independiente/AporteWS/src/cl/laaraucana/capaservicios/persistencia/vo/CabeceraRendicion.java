package cl.laaraucana.capaservicios.persistencia.vo;

public class CabeceraRendicion {
	private String idRendicion;
	private String nombreArchivo;
	private String fecha;
	private String comprobante;
	private String totalRegistros;
	private String totalRendicion;
	private String codBanco;
	private String ctaCorriente;
	private String nroOperacion;
	private String fkLibroBanco;
	private String estadoRendicion;
	private String fechaProceso;
	
	public String getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(String idRendicion) {
		this.idRendicion = idRendicion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public String getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public String getTotalRendicion() {
		return totalRendicion;
	}
	public void setTotalRendicion(String totalRendicion) {
		this.totalRendicion = totalRendicion;
	}
	public String getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	public String getCtaCorriente() {
		return ctaCorriente;
	}
	public void setCtaCorriente(String ctaCorriente) {
		this.ctaCorriente = ctaCorriente;
	}
	public String getNroOperacion() {
		return nroOperacion;
	}
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}
	public String getEstadoRendicion() {
		return estadoRendicion;
	}
	public void setEstadoRendicion(String estadoRendicion) {
		this.estadoRendicion = estadoRendicion;
	}
	public String getFkLibroBanco() {
		return fkLibroBanco;
	}
	public void setFkLibroBanco(String fkLibroBanco) {
		this.fkLibroBanco = fkLibroBanco;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
}
