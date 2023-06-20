package cl.laaraucana.licenciascompinemp.vo;

public class RegistroVo {

	private long id;
	private String rut;
	private String nombre;
	private String serie;
	private String telefono;
	private String email;
	private String comuna;
	private String region;
	private String folioLicencia;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFolioLicencia() {
		return folioLicencia;
	}

	public void setFolioLicencia(String folioLicencia) {
		this.folioLicencia = folioLicencia;
	}

}
