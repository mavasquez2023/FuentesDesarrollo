package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentasRUT", 
		propOrder = {"log", "cuentas"})
public class CuentasResponseVO implements Serializable{
	
	@XmlElementWrapper(name="CUENTAS")
	@XmlElement(name="CUENTA", required=true)
	private List<CuentaDescripcionVO> cuentas;
	
	@XmlElement(name="LOG_RESPUESTA ", required=true)
	private SalidaVO log;

	/**
	 * @return the cuentas
	 */
	public List<CuentaDescripcionVO> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas the cuentas to set
	 */
	public void setCuentas(List<CuentaDescripcionVO> cuentas) {
		this.cuentas = cuentas;
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
