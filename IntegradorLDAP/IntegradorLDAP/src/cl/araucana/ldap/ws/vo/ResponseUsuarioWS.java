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
		name = "usuariosLDAP",
		propOrder = {"codigo_respuesta", "estado" })
public class ResponseUsuarioWS implements Serializable{

	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private int codigo_respuesta;
	
	@XmlElement(name="ESTADO", required=true)
	private int estado;

	public int getCodigo_respuesta() {
		return codigo_respuesta;
	}

	public void setCodigo_respuesta(int codigo_respuesta) {
		this.codigo_respuesta = codigo_respuesta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	



}
