//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}consultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Response", propOrder = {
    "_return"
})
public class ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400Response {

    @XmlElement(name = "return")
    protected ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO }
     *     
     */
    public ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO }
     *     
     */
    public void setReturn(ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO value) {
        this._return = value;
    }

}
