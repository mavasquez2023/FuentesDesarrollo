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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CTLicencia_anterior complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTLicencia_anterior">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lma_ndias" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="lma_fecha_desde" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="lma_fecha_hasta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTLicencia_anterior", propOrder = {
    "lmaNdias",
    "lmaFechaDesde",
    "lmaFechaHasta"
})
public class CTLicenciaAnterior {

    @XmlElement(name = "lma_ndias", required = true)
    protected BigInteger lmaNdias;
    @XmlElement(name = "lma_fecha_desde", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lmaFechaDesde;
    @XmlElement(name = "lma_fecha_hasta", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lmaFechaHasta;

    /**
     * Gets the value of the lmaNdias property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLmaNdias() {
        return lmaNdias;
    }

    /**
     * Sets the value of the lmaNdias property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLmaNdias(BigInteger value) {
        this.lmaNdias = value;
    }

    /**
     * Gets the value of the lmaFechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLmaFechaDesde() {
        return lmaFechaDesde;
    }

    /**
     * Sets the value of the lmaFechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLmaFechaDesde(XMLGregorianCalendar value) {
        this.lmaFechaDesde = value;
    }

    /**
     * Gets the value of the lmaFechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLmaFechaHasta() {
        return lmaFechaHasta;
    }

    /**
     * Sets the value of the lmaFechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLmaFechaHasta(XMLGregorianCalendar value) {
        this.lmaFechaHasta = value;
    }

}
