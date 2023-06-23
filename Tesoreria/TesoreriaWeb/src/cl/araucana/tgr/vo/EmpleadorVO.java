/**
 * 
 */
package cl.araucana.tgr.vo;

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
		name = "empleadorVO",
		propOrder = { "region_domicilio", "razon_social", "rut_empleador", "dv_empleador"  })
public class EmpleadorVO {
@XmlElement(name="RUT_EMPLEADOR", required=true)
private int rut_empleador;
@XmlElement(name="DV_EMPLEADOR", required=true)
private String dv_empleador;
@XmlElement(name="RAZON_SOCIAL", required=true)
private String razon_social;
@XmlElement(name="REGION_DOMICILIO", required=true)
private int region_domicilio;

public String getDv_empleador() {
	return dv_empleador;
}
public void setDv_empleador(String dv_empleador) {
	this.dv_empleador = dv_empleador;
}
public String getRazon_social() {
	return razon_social;
}
public void setRazon_social(String razon_social) {
	this.razon_social = razon_social;
}
public int getRegion_domicilio() {
	return region_domicilio;
}
public void setRegion_domicilio(int region_domicilio) {
	this.region_domicilio = region_domicilio;
}
public int getRut_empleador() {
	return rut_empleador;
}
public void setRut_empleador(int rut_empleador) {
	this.rut_empleador = rut_empleador;
}


}
