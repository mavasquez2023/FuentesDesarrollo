package cl.laaraucana.botonpago.web.vo;

public class DatosActualizablesVO {

	private String estcivil;
	private String fono1;
	private String fono2;
	private String fono3;
	private String email;
	private String direccion;
	private String comuna;
	private String provincia;
	private String region;
	private String envecta;

	public DatosActualizablesVO() {
		super();
	}

	public DatosActualizablesVO(String estcivil, String fono1, String fono2, String fono3, String email, String direccion, String comuna, String provincia, String region, String envecta) {
		super();
		this.estcivil = estcivil;
		this.fono1 = fono1;
		this.fono2 = fono2;
		this.fono3 = fono3;
		this.email = email;
		this.direccion = direccion;
		this.comuna = comuna;
		this.provincia = provincia;
		this.region = region;
		this.envecta = envecta;
	}

	public String getEstcivil() {
		return estcivil;
	}

	public void setEstcivil(String estcivil) {
		this.estcivil = estcivil;
	}

	public String getFono1() {
		return fono1;
	}

	public void setFono1(String fono1) {
		this.fono1 = fono1;
	}

	public String getFono2() {
		return fono2;
	}

	public void setFono2(String fono2) {
		this.fono2 = fono2;
	}

	public String getFono3() {
		return fono3;
	}

	public void setFono3(String fono3) {
		this.fono3 = fono3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEnvecta() {
		return envecta;
	}

	public void setEnvecta(String envecta) {
		this.envecta = envecta;
	}

}
