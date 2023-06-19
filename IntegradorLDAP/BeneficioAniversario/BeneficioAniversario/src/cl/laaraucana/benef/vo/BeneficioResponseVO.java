package cl.laaraucana.benef.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "BENEFICIO_RESPONSE", 
		propOrder = {"log", "beneficio"})
public class BeneficioResponseVO implements Serializable{
	
	@XmlElement(name="BENEFICIO", required=true)
	private BeneficioVO beneficio;
	
	@XmlElement(name="LOG_RESPUESTA ", required=true)
	private SalidaVO log;

	/**
	 * @return the beneficio
	 */
	public BeneficioVO getBeneficio() {
		return beneficio;
	}

	/**
	 * @param beneficio the beneficio to set
	 */
	public void setBeneficio(BeneficioVO beneficio) {
		this.beneficio = beneficio;
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
