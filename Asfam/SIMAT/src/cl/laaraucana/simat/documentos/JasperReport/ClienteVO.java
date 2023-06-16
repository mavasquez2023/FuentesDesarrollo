package cl.laaraucana.simat.documentos.JasperReport;

public class ClienteVO {

	private String rut;
	private String nombre;
	private String ciudad;
	private String region;
	private String telefono;
	private String fecha_emision;

	public ClienteVO() {
	}

	public ClienteVO(String rut, String nombre, String ciudad, String region, String telefono, String fecha_emision) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.region = region;
		this.telefono = telefono;
		this.fecha_emision = fecha_emision;
	}

	public String getRut() {
		return rut;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getRegion() {
		return region;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getFecha_emision() {
		return fecha_emision;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

}
