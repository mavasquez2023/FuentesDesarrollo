package cl.laaraucana.licenciascompinemp.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class RegistroDocPendienteVo {

	private long id;
	private String rut;
	private String nombre;
	private String serie;
	private String telefono;
	private String email;
	private String comuna;
	private String region;
	private String folioLicencia;
	private CommonsMultipartFile documento;
	private boolean maternal;
	private String sucursal;
	
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

	/**
	 * @return the documento
	 */
	public CommonsMultipartFile getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(CommonsMultipartFile documento) {
		this.documento = documento;
	}

	/**
	 * @return the maternal
	 */
	public boolean isMaternal() {
		return maternal;
	}

	/**
	 * @param maternal the maternal to set
	 */
	public void setMaternal(boolean maternal) {
		this.maternal = maternal;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	

}
