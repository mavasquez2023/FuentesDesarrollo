/**
 * 
 */
package cl.araucana.wsempresa.vo;

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
	
private int rut_empresa;

/**
 * @return the rut_empresa
 */
public int getRut_empresa() {
	return rut_empresa;
}

/**
 * @param rut_empresa the rut_empresa to set
 */
public void setRut_empresa(int rut_empresa) {
	this.rut_empresa = rut_empresa;
}



}