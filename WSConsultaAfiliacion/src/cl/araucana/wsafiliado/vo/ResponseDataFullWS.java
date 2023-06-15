/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "dataFullWS",
		propOrder = { "rutConsulta", "codigo_respuesta", "titular", "carga" })

public class ResponseDataFullWS implements Serializable{

	@XmlElement(name="RUT_CONSULTA", required=true)
	private String rutConsulta;

	@XmlElement(name="TITULAR", required=true)
	private TitularVO titular;
	
	@XmlElementWrapper(name="CARGAS")
	@XmlElement(name="CARGA", required=false)
	private List<CargaVO> carga;
	
	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private int codigo_respuesta;

	/**
	 * @return the rutConsulta
	 */
	public String getRutConsulta() {
		return rutConsulta;
	}

	/**
	 * @param rutConsulta the rutConsulta to set
	 */
	public void setRutConsulta(String rutConsulta) {
		this.rutConsulta = rutConsulta;
	}

	/**
	 * @return the titular
	 */
	public TitularVO getTitular() {
		return titular;
	}

	/**
	 * @param titular the titular to set
	 */
	public void setTitular(TitularVO titular) {
		this.titular = titular;
	}

	/**
	 * @return the carga
	 */
	public List<CargaVO> getCarga() {
		return carga;
	}

	/**
	 * @param carga the carga to set
	 */
	public void setCarga(List<CargaVO> carga) {
		this.carga = carga;
	}

	/**
	 * @return the codigo_respuesta
	 */
	public int getCodigo_respuesta() {
		return codigo_respuesta;
	}

	/**
	 * @param codigo_respuesta the codigo_respuesta to set
	 */
	public void setCodigo_respuesta(int codigo_respuesta) {
		this.codigo_respuesta = codigo_respuesta;
	}
	
	
}
