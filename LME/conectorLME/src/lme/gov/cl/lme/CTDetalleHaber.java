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
 * <p>Java class for CTDetalleHaber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTDetalleHaber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ano_mes_renta" type="{http://www.w3.org/2001/XMLSchema}gYearMonth"/>
 *         &lt;element name="nombre_haber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monto_haber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTDetalleHaber", propOrder = {
    "anoMesRenta",
    "nombreHaber",
    "montoHaber"
})
public class CTDetalleHaber {

    @XmlElement(name = "ano_mes_renta", required = true)
    @XmlSchemaType(name = "gYearMonth")
    protected XMLGregorianCalendar anoMesRenta;
    @XmlElement(name = "nombre_haber", required = true)
    protected String nombreHaber;
    @XmlElement(name = "monto_haber", required = true)
    protected BigInteger montoHaber;

    /**
     * Gets the value of the anoMesRenta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnoMesRenta() {
        return anoMesRenta;
    }

    /**
     * Sets the value of the anoMesRenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnoMesRenta(XMLGregorianCalendar value) {
        this.anoMesRenta = value;
    }

    /**
     * Gets the value of the nombreHaber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreHaber() {
        return nombreHaber;
    }

    /**
     * Sets the value of the nombreHaber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreHaber(String value) {
        this.nombreHaber = value;
    }

    /**
     * Gets the value of the montoHaber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMontoHaber() {
        return montoHaber;
    }

    /**
     * Sets the value of the montoHaber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMontoHaber(BigInteger value) {
        this.montoHaber = value;
    }

}
