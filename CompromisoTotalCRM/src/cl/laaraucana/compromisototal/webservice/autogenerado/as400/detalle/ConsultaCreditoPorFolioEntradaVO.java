//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.compromisototal.webservice.autogenerado.as400.detalle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaCreditoPorFolioEntradaVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaCreditoPorFolioEntradaVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaCreditoPorFolioEntradaVO", propOrder = {
    "folioCredito",
    "tipoCredito"
})
public class ConsultaCreditoPorFolioEntradaVO {

    protected String folioCredito;
    protected String tipoCredito;

    /**
     * Gets the value of the folioCredito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioCredito() {
        return folioCredito;
    }

    /**
     * Sets the value of the folioCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioCredito(String value) {
        this.folioCredito = value;
    }

    /**
     * Gets the value of the tipoCredito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCredito() {
        return tipoCredito;
    }

    /**
     * Sets the value of the tipoCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCredito(String value) {
        this.tipoCredito = value;
    }

}
