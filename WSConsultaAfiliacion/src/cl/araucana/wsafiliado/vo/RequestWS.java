/**
 * 
 */
package cl.araucana.wsafiliado.vo;

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
		name = "requestWS")
public class RequestWS implements Serializable{
	
@XmlElement(name="rut_trabajador", required=true)
private int rut_trabajador;


public int getRut_trabajador() {
	return rut_trabajador;
}
public void setRut_trabajador(int rut_trabajador) {
	this.rut_trabajador = rut_trabajador;
}
}