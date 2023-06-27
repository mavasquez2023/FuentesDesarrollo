package cl.araucana.wsatento.business.to;

public class EntidadTO {
	private Integer eId;
	private String nombre;
	private String nombreLargo;
	private String rut;
	private String estado;
	
	public EntidadTO() {}
	
	public EntidadTO(Integer eId) {
		this.eId = eId;
	}
	
	public EntidadTO(Integer eId, String nombre, String nombreLargo, String rut, String estado) {
		this.eId = eId;
		this.nombre = nombre;
		this.nombreLargo = nombreLargo;
		this.rut = rut;
		this.estado = estado;
	}

	public Integer getEId() {
		return eId;
	}

	public void setEId(Integer id) {
		eId = id;
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

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
}
