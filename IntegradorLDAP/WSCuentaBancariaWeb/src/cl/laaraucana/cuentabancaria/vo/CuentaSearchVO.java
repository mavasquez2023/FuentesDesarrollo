package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentaSearch",
		propOrder = {"rutafi", "dvafi"})
public class CuentaSearchVO implements Serializable{

	@XmlElement(name="RUT", required=true)
	private int rutafi;
	@XmlElement(name="DV", required=true)
	private String dvafi;
	
	/**
	 * @return the rutafi
	 */
	public int getRutafi() {
		return rutafi;
	}
	/**
	 * @param rutafi the rutafi to set
	 */
	public void setRutafi(int rutafi) {
		this.rutafi = rutafi;
	}
	/**
	 * @return the dvafi
	 */
	public String getDvafi() {
		return dvafi;
	}
	/**
	 * @param dvafi the dvafi to set
	 */
	public void setDvafi(String dvafi) {
		this.dvafi = dvafi;
	}

	
}
