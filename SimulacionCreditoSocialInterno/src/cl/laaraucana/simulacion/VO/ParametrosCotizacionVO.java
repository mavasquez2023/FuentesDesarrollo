package cl.laaraucana.simulacion.VO;

public class ParametrosCotizacionVO {

	private String extension = "";
	private String preMovil = "";
	private String fono = "";
	private String celular = "";
	private String email = "";
	private String calle = "";
	private String calleNro = "";
	private String nroDpto = "";
	private String region = "";
	private String comuna = "";
	private boolean contacto = false;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPreMovil() {
		return preMovil;
	}

	public void setPreMovil(String preMovil) {
		this.preMovil = preMovil;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalleNro() {
		return calleNro;
	}

	public void setCalleNro(String calleNro) {
		this.calleNro = calleNro;
	}

	public String getNroDpto() {
		return nroDpto;
	}

	public void setNroDpto(String nroDpto) {
		this.nroDpto = nroDpto;
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

	public boolean isContacto() {
		return contacto;
	}

	public void setContacto(boolean contacto) {
		this.contacto = contacto;
	}

}
