//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vIBM 2.2.3-11/28/2011 06:21 AM(foreman)- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.10 at 07:19:46 PM CLT 
//


package lme.gov.cl.lme;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CT_ZONA_CC complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_ZONA_CC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_tramitacion_CCAF" type="{urn:www:lme:gov:cl:lme}STCodigoCCAF"/>
 *         &lt;element name="tiene_mas100" type="{urn:www:lme:gov:cl:lme}STSiNo" minOccurs="0"/>
 *         &lt;element name="haberes" type="{urn:www:lme:gov:cl:lme}CTHaberes"/>
 *         &lt;element name="run_hijo" type="{urn:www:lme:gov:cl:lme}STRut" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CT_ZONA_CC", propOrder = {
    "codigoTramitacionCCAF",
    "tieneMas100",
    "haberes",
    "runHijo"
})
public class CTZONACC {

    @XmlElement(name = "codigo_tramitacion_CCAF", required = true)
    protected BigInteger codigoTramitacionCCAF;
    @XmlElement(name = "tiene_mas100")
    protected BigInteger tieneMas100;
    @XmlElement(required = true)
    protected CTHaberes haberes;
    @XmlElement(name = "run_hijo")
    protected List<String> runHijo;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Gets the value of the codigoTramitacionCCAF property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoTramitacionCCAF() {
        return codigoTramitacionCCAF;
    }

    /**
     * Sets the value of the codigoTramitacionCCAF property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoTramitacionCCAF(BigInteger value) {
        this.codigoTramitacionCCAF = value;
    }

    /**
     * Gets the value of the tieneMas100 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTieneMas100() {
        return tieneMas100;
    }

    /**
     * Sets the value of the tieneMas100 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTieneMas100(BigInteger value) {
        this.tieneMas100 = value;
    }

    /**
     * Gets the value of the haberes property.
     * 
     * @return
     *     possible object is
     *     {@link CTHaberes }
     *     
     */
    public CTHaberes getHaberes() {
        return haberes;
    }

    /**
     * Sets the value of the haberes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTHaberes }
     *     
     */
    public void setHaberes(CTHaberes value) {
        this.haberes = value;
    }

    /**
     * Gets the value of the runHijo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the runHijo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRunHijo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRunHijo() {
        if (runHijo == null) {
            runHijo = new ArrayList<String>();
        }
        return this.runHijo;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
