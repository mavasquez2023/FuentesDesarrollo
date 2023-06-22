/**
 * 
 */
package cl.araucana.wssiagf.vo;

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
		name = "dataWS",
		propOrder = { "rutTrabajador", "rutEmpresa", "fechaEmision", "fechaExtincion" })

public class DataRequest implements Serializable{
	
@XmlElement(name="RUT_TRABAJADOR", required=true)
private String rutTrabajador;

@XmlElement(name="RUT_EMPRESA", required=true)
private String rutEmpresa;

@XmlElement(name="FECHA_EMISION", required=true)
private String fechaEmision;

@XmlElement(name="FECHA_EXTINCION", required=true)
private String fechaExtincion;

public String getRutTrabajador() {
	return rutTrabajador;
}

public void setRutTrabajador(String rutTrabajador) {
	this.rutTrabajador = rutTrabajador;
}

public String getRutEmpresa() {
	return rutEmpresa;
}

public void setRutEmpresa(String rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}

public String getFechaEmision() {
	return fechaEmision;
}

public void setFechaEmision(String fechaEmision) {
	this.fechaEmision = fechaEmision;
}

public String getFechaExtincion() {
	return fechaExtincion;
}

public void setFechaExtincion(String fechaExtincion) {
	this.fechaExtincion = fechaExtincion;
}



}