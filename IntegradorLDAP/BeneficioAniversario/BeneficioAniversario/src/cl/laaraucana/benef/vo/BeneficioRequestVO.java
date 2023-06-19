package cl.laaraucana.benef.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "CONFIRMA",
		propOrder = {"codigoBeneficio", "hotel" })
public class BeneficioRequestVO implements Serializable{
	@XmlElement(name="CODIGO_BENEFICIO", required=true, nillable=false)
	private String codigoBeneficio;
	@XmlElement(name="HOTEL", required=true, nillable=false)
	private String hotel;
	/**
	 * @return the codigoBeneficio
	 */
	public String getCodigoBeneficio() {
		return codigoBeneficio;
	}
	/**
	 * @param codigoBeneficio the codigoBeneficio to set
	 */
	public void setCodigoBeneficio(String codigoBeneficio) {
		this.codigoBeneficio = codigoBeneficio;
	}
	/**
	 * @return the hotel
	 */
	public String getHotel() {
		return hotel;
	}
	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	
	

}
