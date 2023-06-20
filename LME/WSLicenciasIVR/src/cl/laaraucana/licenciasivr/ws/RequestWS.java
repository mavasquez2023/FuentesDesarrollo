/**
 * 
 */
package cl.laaraucana.licenciasivr.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "Consulta",
		propOrder = { "usuario", "password", "rut", "fecha"})
public class RequestWS implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="USUARIO", required=true)
	private String usuario;
	@XmlElement(name="CLAVE", required=true)
	private String password;
	@XmlElement(name="RUT", required=true)
	private String rut;
	@XmlElement(name="FECHADESDE", required=true)
	private String fecha;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}