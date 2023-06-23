/**
 * 
 */
package cl.araucana.tgr.vo;

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
		propOrder = { "rut_empleador", "periodo", "rut_trabajador" })
public class RequestWSTGR implements Serializable{
	
@XmlElement(name="rut_empleador", required=true)
private int rut_empleador;
@XmlElement(name="periodo", required=true)
private int periodo;
@XmlElement(name="rut_trabajador", required=true)
private int rut_trabajador;

public int getPeriodo() {
	return periodo;
}
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
public int getRut_empleador() {
	return rut_empleador;
}
public void setRut_empleador(int rut_empleador) {
	this.rut_empleador = rut_empleador;
}
public int getRut_trabajador() {
	return rut_trabajador;
}
public void setRut_trabajador(int rut_trabajador) {
	this.rut_trabajador = rut_trabajador;
}
}