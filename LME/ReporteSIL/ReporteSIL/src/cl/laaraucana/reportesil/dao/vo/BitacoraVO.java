/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class BitacoraVO {
	private int rut;
	private String dv;
	private String nombre;
	private String celular;
	private String telefono;
	private String email;
	private String tipoenvio;
	
	public String getRut_DV() {
		return rut + "-" + dv;
	}
	public int getRut() {
		return rut;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
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
	public String getTipoenvio() {
		return tipoenvio;
	}
	public void setTipoenvio(String tipoenvio) {
		this.tipoenvio = tipoenvio;
	}

		
}
