//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//

package cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultaDatosAfiliacionAs400Response complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="consultaDatosAfiliacionAs400Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}consultaDatosAfiliacionAs400SalidaVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaDatosAfiliacionAs400Response", propOrder = { "_return" })
public class ConsultaDatosAfiliacionAs400Response {

	@XmlElement(name = "return")
	protected ConsultaDatosAfiliacionAs400SalidaVO _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link ConsultaDatosAfiliacionAs400SalidaVO }
	 * 
	 */
	public ConsultaDatosAfiliacionAs400SalidaVO getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link ConsultaDatosAfiliacionAs400SalidaVO }
	 * 
	 */
	public void setReturn(ConsultaDatosAfiliacionAs400SalidaVO value) {
		this._return = value;
	}

}
