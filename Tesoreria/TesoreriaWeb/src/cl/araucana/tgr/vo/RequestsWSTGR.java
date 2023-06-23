/**
 * 
 */
package cl.araucana.tgr.vo;

import java.io.Serializable;
import java.util.List;

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
		name = "requestsWSTGR",
		propOrder = { "rut_empleador", "periodo", "rut_trabajador" })
public class RequestsWSTGR implements Serializable{
	
@XmlElement(name="rut_empleador", required=true)
private int rut_empleador;
@XmlElement(name="periodo", required=true)
private int periodo;
@XmlElement(name="rut_trabajador", required=true)
private List<Integer> rut_trabajador;

/**
 * @return the rut_empleador
 */
public int getRut_empleador() {
	return rut_empleador;
}
/**
 * @param rut_empleador the rut_empleador to set
 */
public void setRut_empleador(int rut_empleador) {
	this.rut_empleador = rut_empleador;
}
/**
 * @return the periodo
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo the periodo to set
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return the rut_trabajador
 */
public List<Integer> getRut_trabajador() {
	return rut_trabajador;
}
/**
 * @param rut_trabajador the rut_trabajador to set
 */
public void setRut_trabajador(List<Integer> rut_trabajador) {
	this.rut_trabajador = rut_trabajador;
}


}