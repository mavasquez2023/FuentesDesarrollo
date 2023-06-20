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
		name = "cuentaVigente", 
		propOrder = {"log", "cuenta"})
public class CuentaResponseWSVO implements Serializable{
	
	@XmlElement(name="CUENTA", required=true)
	CuentaDescripcionVO cuenta;
	
	@XmlElement(name="LOG_RESPUESTA ", required=true)
	private SalidaVO log;

	/**
	 * @return the cuenta
	 */
	public CuentaDescripcionVO getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(CuentaDescripcionVO cuenta) {
		this.cuenta = cuenta;
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
