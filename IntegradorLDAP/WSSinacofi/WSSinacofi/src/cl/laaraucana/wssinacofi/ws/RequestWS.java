/**
 * 
 */
package cl.laaraucana.wssinacofi.ws;

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
		name = "DatosCedula")
public class RequestWS implements Serializable{

	@XmlElement(name="RUT", required=true)
	private String rut;
	
	@XmlElement(name="NUMERO_SERIE", required=true)
	private String numero_serie;

	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return the numero_serie
	 */
	public String getNumero_serie() {
		return numero_serie;
	}

	/**
	 * @param numero_serie the numero_serie to set
	 */
	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}
	
	
}