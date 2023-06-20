/**
 * 
 */
package cl.laaraucana.licenciasivr.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cl.laaraucana.licenciasivr.vo.LicenciaVO;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "responseIVR",
		propOrder = { "licencias", "codigoRespuesta"})
public class ResponseWS implements Serializable{
	
	@XmlElement(name="LICENCIA", required=false)
	private List<LicenciaWS> licencias;
	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private String codigoRespuesta;
	/**
	 * @return the licencias
	 */
	public List<LicenciaWS> getLicencias() {
		return licencias;
	}
	/**
	 * @param licencias the licencias to set
	 */
	public void setLicencias(List<LicenciaWS> licencias) {
		this.licencias = licencias;
	}
	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	

}
