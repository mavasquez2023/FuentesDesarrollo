/**
 * 
 */
package cl.araucana.ldap.ws.vo;

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
		name = "requestUsuarioWS")
public class RequestUsuarioWS implements Serializable{
	
@XmlElement(name="rut", required=true)
private String rut;

@XmlElement(name="nombre", required=true)
private String nombre;

@XmlElement(name="celular", required=true)
private String celular;

@XmlElement(name="telefono", required=false)
private String telefono;

@XmlElement(name="email", required=true)
private String email;

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


}