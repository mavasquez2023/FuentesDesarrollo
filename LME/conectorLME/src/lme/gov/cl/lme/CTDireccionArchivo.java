//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vIBM 2.2.3-11/28/2011 06:21 AM(foreman)- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.10 at 07:19:46 PM CLT 
//


package lme.gov.cl.lme;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CTDireccion_archivo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTDireccion_archivo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipo_archivo" type="{urn:www:lme:gov:cl:lme}STTipo_archivo"/>
 *         &lt;element name="url_archivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTDireccion_archivo", propOrder = {
    "tipoArchivo",
    "urlArchivo"
})
public class CTDireccionArchivo {

    @XmlElement(name = "tipo_archivo", required = true)
    protected BigInteger tipoArchivo;
    @XmlElement(name = "url_archivo", required = true)
    protected String urlArchivo;

    /**
     * Gets the value of the tipoArchivo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTipoArchivo() {
        return tipoArchivo;
    }

    /**
     * Sets the value of the tipoArchivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTipoArchivo(BigInteger value) {
        this.tipoArchivo = value;
    }

    /**
     * Gets the value of the urlArchivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlArchivo() {
        return urlArchivo;
    }

    /**
     * Sets the value of the urlArchivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlArchivo(String value) {
        this.urlArchivo = value;
    }

}