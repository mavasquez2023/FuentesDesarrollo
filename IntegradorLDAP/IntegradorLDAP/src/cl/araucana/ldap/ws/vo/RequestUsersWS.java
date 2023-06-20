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
		name = "requestUsersWS")
public class RequestUsersWS implements Serializable{
	
@XmlElement(name="rolId", required=true)
private String rolId;

@XmlElement(name="appId", required=true)
private String appId;


public String getRolId() {
	return rolId;
}

public void setRolId(String rolId) {
	this.rolId = rolId;
}

public String getAppId() {
	return appId;
}

public void setAppId(String appId) {
	this.appId = appId;
}


}