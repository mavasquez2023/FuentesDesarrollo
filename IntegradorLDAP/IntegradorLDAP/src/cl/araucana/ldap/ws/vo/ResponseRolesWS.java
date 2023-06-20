/**
 * 
 */
package cl.araucana.ldap.ws.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "rolesAPP",
		propOrder = {"codigo_respuesta", "roles" })
public class ResponseRolesWS implements Serializable{

	@XmlElementWrapper(name="ROLES")
	@XmlElement(name="ROL", required=false)
	private List<String> roles;
	
	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private int codigo_respuesta;

public List<String> getRoles() {
	return roles;
}

public void setRoles(List<String> roles) {
	this.roles = roles;
}

public int getCodigo_respuesta() {
	return codigo_respuesta;
}

public void setCodigo_respuesta(int codigo_respuesta) {
	this.codigo_respuesta = codigo_respuesta;
}


}
