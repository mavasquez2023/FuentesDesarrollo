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
		name = "requestPasswordWS")
public class RequestPasswordWS implements Serializable{
	
@XmlElement(name="rut", required=true)
private String rut;

@XmlElement(name="celular", required=false)
private String celular;

@XmlElement(name="email", required=false)
private String email;

public String getRut() {
	return rut;
}

public void setRut(String rut) {
	this.rut = rut;
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


}