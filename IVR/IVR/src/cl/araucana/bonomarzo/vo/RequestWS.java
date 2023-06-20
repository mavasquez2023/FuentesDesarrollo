/**
 * 
 */
package cl.araucana.bonomarzo.vo;

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
		name = "requestWSTGR",
		propOrder = { "ws_usuario", "ws_clave", "id_consulta", "rut_cliente" })
public class RequestWS implements Serializable{
	
@XmlElement(name="ws_usuario", required=true)
private String ws_usuario;
@XmlElement(name="ws_clave", required=true)
private String ws_clave;
@XmlElement(name="id_consulta", required=true)
private String id_consulta;
@XmlElement(name="rut_cliente", required=true)
private String rut_cliente;
/**
 * @return the ws_usuario
 */
public String getWs_usuario() {
	return ws_usuario;
}
/**
 * @param ws_usuario the ws_usuario to set
 */
public void setWs_usuario(String ws_usuario) {
	this.ws_usuario = ws_usuario;
}
/**
 * @return the ws_clave
 */
public String getWs_clave() {
	return ws_clave;
}
/**
 * @param ws_clave the ws_clave to set
 */
public void setWs_clave(String ws_clave) {
	this.ws_clave = ws_clave;
}
/**
 * @return the id_consulta
 */
public String getId_consulta() {
	return id_consulta;
}
/**
 * @param id_consulta the id_consulta to set
 */
public void setId_consulta(String id_consulta) {
	this.id_consulta = id_consulta;
}
/**
 * @return the rut_cliente
 */
public String getRut_cliente() {
	return rut_cliente;
}
/**
 * @param rut_cliente the rut_cliente to set
 */
public void setRut_cliente(String rut_cliente) {
	this.rut_cliente = rut_cliente;
}

}