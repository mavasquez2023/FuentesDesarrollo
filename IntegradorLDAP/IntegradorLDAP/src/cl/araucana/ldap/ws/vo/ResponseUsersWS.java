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
		name = "usersRol",
		propOrder = {"codigo_respuesta", "usuarios" })
public class ResponseUsersWS implements Serializable{

	@XmlElementWrapper(name="USERS")
	@XmlElement(name="USERID", required=false)
	private List<String> usuarios;
	
	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private int codigo_respuesta;

	public List<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}

	public int getCodigo_respuesta() {
		return codigo_respuesta;
	}

	public void setCodigo_respuesta(int codigo_respuesta) {
		this.codigo_respuesta = codigo_respuesta;
	}



}
