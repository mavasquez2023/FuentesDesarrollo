package cl.araucana.wsatento.business.to;

public class WebServicesTO {
	private Integer wsId;
	private String nombre;
	private String descripcion;
	private String estado;
	
	public WebServicesTO(){}
			
	public WebServicesTO(Integer wsId, String nombre, String descripcion, String estado) {
		this.wsId = wsId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getWsId() {
		return wsId;
	}
	public void setWsId(Integer wsId) {
		this.wsId = wsId;
	}
}
