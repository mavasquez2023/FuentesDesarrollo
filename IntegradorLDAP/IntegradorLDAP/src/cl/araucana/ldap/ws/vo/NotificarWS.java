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
		name = "requestNotificarWS")
public class NotificarWS implements Serializable{
	
@XmlElement(name="mail", required=false)
private String mail;

@XmlElement(name="sms", required=false)
private String sms;

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getSms() {
	return sms;
}

public void setSms(String sms) {
	this.sms = sms;
}


}