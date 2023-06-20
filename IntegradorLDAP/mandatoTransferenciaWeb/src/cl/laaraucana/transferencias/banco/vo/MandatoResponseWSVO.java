package cl.laaraucana.transferencias.banco.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "RespuestaInsertMandato", 
		propOrder = {"idMandato", "log"})
public class MandatoResponseWSVO implements Serializable{
	
	@XmlElement(name="ID_MANDATO", required=true)
	String  idMandato;
	
	@XmlElement(name="LOG_RESPUESTA ", required=true)
	private SalidaVO log;


	/**
	 * @return the idMandato
	 */
	public String getIdMandato() {
		return idMandato;
	}

	/**
	 * @param idMandato the idMandato to set
	 */
	public void setIdMandato(String idMandato) {
		this.idMandato = idMandato;
	}

	/**
	 * @return the log
	 */
	public SalidaVO getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(SalidaVO log) {
		this.log = log;
	}

	
	
}
