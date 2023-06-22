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
 * DATOS DEL LUGAR DEL REPOSO
 * 
 * <p>Java class for CTLugar_reposo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTLugar_reposo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_lugar_reposo" type="{urn:www:lme:gov:cl:lme}STTipo_lugar_reposo"/>
 *         &lt;element name="direccion_reposo" type="{urn:www:lme:gov:cl:lme}CTDireccion"/>
 *         &lt;element name="justifica_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTLugar_reposo", propOrder = {
    "codigoLugarReposo",
    "direccionReposo",
    "justificaDomicilio"
})
public class CTLugarReposo {

    @XmlElement(name = "codigo_lugar_reposo", required = true)
    protected BigInteger codigoLugarReposo;
    @XmlElement(name = "direccion_reposo", required = true)
    protected CTDireccion direccionReposo;
    @XmlElement(name = "justifica_domicilio")
    protected String justificaDomicilio;

    /**
     * Gets the value of the codigoLugarReposo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoLugarReposo() {
        return codigoLugarReposo;
    }

    /**
     * Sets the value of the codigoLugarReposo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoLugarReposo(BigInteger value) {
        this.codigoLugarReposo = value;
    }

    /**
     * Gets the value of the direccionReposo property.
     * 
     * @return
     *     possible object is
     *     {@link CTDireccion }
     *     
     */
    public CTDireccion getDireccionReposo() {
        return direccionReposo;
    }

    /**
     * Sets the value of the direccionReposo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTDireccion }
     *     
     */
    public void setDireccionReposo(CTDireccion value) {
        this.direccionReposo = value;
    }

    /**
     * Gets the value of the justificaDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificaDomicilio() {
        return justificaDomicilio;
    }

    /**
     * Sets the value of the justificaDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificaDomicilio(String value) {
        this.justificaDomicilio = value;
    }

}