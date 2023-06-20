package cl.laaraucana.transferencias.banco.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "REVOCAR",
		propOrder = {"rutafi", "dvafi", "nombre", "email", "mensaje"})
public class ParamRevocarVO implements Serializable{

	@XmlElement(name="RUT", required=true)
	private int rutafi;
	@XmlElement(name="DV", required=true)
	private String dvafi;
	@XmlElement(name="NOMBRE", required=false)
	private String nombre;
	@XmlElement(name="EMAIL", required=false)
	private String email;
	@XmlElement(name="MENSAJE", required=false)
	private String mensaje;
	
	/**
	 * @return the rutafi
	 */
	public int getRutafi() {
		return rutafi;
	}
	/**
	 * @param rutafi the rutafi to set
	 */
	public void setRutafi(int rutafi) {
		this.rutafi = rutafi;
	}
	/**
	 * @return the dvafi
	 */
	public String getDvafi() {
		return dvafi;
	}
	/**
	 * @param dvafi the dvafi to set
	 */
	public void setDvafi(String dvafi) {
		this.dvafi = dvafi;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
