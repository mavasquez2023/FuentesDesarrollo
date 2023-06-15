/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author J-Factory
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "segmentoWS",
		propOrder = { "trabajador", "pensionado"})
@XmlRootElement(name="SEGMENTO")
public class SegmentoVO {
	@XmlElement(name="TRABAJADOR", required=true)
	private String trabajador;
	
	@XmlElement(name="PENSIONADO", required=true)
	private String pensionado;

	/**
	 * @return the trabajador
	 */
	public String getTrabajador() {
		return trabajador;
	}

	/**
	 * @param trabajador the trabajador to set
	 */
	public void setTrabajador(String trabajador) {
		this.trabajador = trabajador;
	}

	/**
	 * @return the pensionado
	 */
	public String getPensionado() {
		return pensionado;
	}

	/**
	 * @param pensionado the pensionado to set
	 */
	public void setPensionado(String pensionado) {
		this.pensionado = pensionado;
	}
	
	
}
