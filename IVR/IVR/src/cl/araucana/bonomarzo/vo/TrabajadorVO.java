/**
 * 
 */
package cl.araucana.bonomarzo.vo;

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
		name = "trabajadorVO",
		propOrder = { "nombres", "apellidoPaterno", "apellidoMaterno", "codigoMensaje", "mensajeCorto", "mensajelargo"})
public class TrabajadorVO {
@XmlElement(name="NOMBRES", required=true)
private String nombres;
@XmlElement(name="APELLIDO_PATERNO", required=true)
private String apellidoPaterno;
@XmlElement(name="APELLIDO_MATERNO", required=true)
private String apellidoMaterno;
@XmlElement(name="COD_MENSAJE", required=true)
private String codigoMensaje;
@XmlElement(name="MENSAJE_CORTO", required=true)
private String mensajeCorto;
@XmlElement(name="MENSAJE_LARGO", required=true)
private String mensajelargo;
/**
 * @return the nombres
 */
public String getNombres() {
	return nombres;
}
/**
 * @param nombres the nombres to set
 */
public void setNombres(String nombres) {
	this.nombres = nombres;
}
/**
 * @return the apellidoPaterno
 */
public String getApellidoPaterno() {
	return apellidoPaterno;
}
/**
 * @param apellidoPaterno the apellidoPaterno to set
 */
public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
}
/**
 * @return the apellidoMaterno
 */
public String getApellidoMaterno() {
	return apellidoMaterno;
}
/**
 * @param apellidoMaterno the apellidoMaterno to set
 */
public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
}
/**
 * @return the codigoMensaje
 */
public String getCodigoMensaje() {
	return codigoMensaje;
}
/**
 * @param codigoMensaje the codigoMensaje to set
 */
public void setCodigoMensaje(String codigoMensaje) {
	this.codigoMensaje = codigoMensaje;
}
/**
 * @return the mensajeCorto
 */
public String getMensajeCorto() {
	return mensajeCorto;
}
/**
 * @param mensajeCorto the mensajeCorto to set
 */
public void setMensajeCorto(String mensajeCorto) {
	this.mensajeCorto = mensajeCorto;
}
/**
 * @return the mensajelargo
 */
public String getMensajelargo() {
	return mensajelargo;
}
/**
 * @param mensajelargo the mensajelargo to set
 */
public void setMensajelargo(String mensajelargo) {
	this.mensajelargo = mensajelargo;
}


}
