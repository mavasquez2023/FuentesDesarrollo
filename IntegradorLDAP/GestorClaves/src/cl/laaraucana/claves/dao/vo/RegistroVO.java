/**
 * 
 */
package cl.laaraucana.claves.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class RegistroVO {
	private int rut;
	private String dv;
	private String nombre;
	private String celular;
	private String telefono;
	private String email;
	private String tipoenvio;
	private String celularX;
	private String emailX;
	
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
	/**
	 * @return the celularX
	 */
	public String getCelularX() {
		if(this.celular.length()>3){
			return this.celular.substring(0, this.celular.length()-3) + "XXX";
		}
		return "";
		
	}
	/**
	 * @param celularX the celularX to set
	 */
	public void setCelularX(String celularX) {
		this.celularX = celularX;
	}
	/**
	 * @return the emailX
	 */
	public String getEmailX() {
		if(this.email.length()>0){
			String[] mail= this.email.split("@");
			return mail[0].substring(0, mail[0].length()-3) + "xxx@" + mail[1];
		}
		return "";
	}
	/**
	 * @param emailX the emailX to set
	 */
	public void setEmailX(String emailX) {
		this.emailX = emailX;
	}
	
		
}
