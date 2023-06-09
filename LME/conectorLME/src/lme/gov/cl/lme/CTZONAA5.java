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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CT_ZONA_A5 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_ZONA_A5">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="profesional" type="{urn:www:lme:gov:cl:lme}CTPersona"/>
 *         &lt;element name="prof_especialidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_tipo_profesional" type="{urn:www:lme:gov:cl:lme}STTipo_profesional"/>
 *         &lt;element name="prof_registro_colegio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prof_email" type="{urn:www:lme:gov:cl:lme}STEmail" minOccurs="0"/>
 *         &lt;element name="prof_telefono" type="{urn:www:lme:gov:cl:lme}CTTelefono" minOccurs="0"/>
 *         &lt;element name="prof_direccion" type="{urn:www:lme:gov:cl:lme}CTDireccion"/>
 *         &lt;element name="prof_fax" type="{urn:www:lme:gov:cl:lme}CTTelefono" minOccurs="0"/>
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
@XmlType(name = "CT_ZONA_A5", propOrder = {
    "profesional",
    "profEspecialidad",
    "codigoTipoProfesional",
    "profRegistroColegio",
    "profEmail",
    "profTelefono",
    "profDireccion",
    "profFax"
})
public class CTZONAA5 {

    @XmlElement(required = true)
    protected CTPersona profesional;
    @XmlElement(name = "prof_especialidad", required = true)
    protected String profEspecialidad;
    @XmlElement(name = "codigo_tipo_profesional", required = true)
    protected BigInteger codigoTipoProfesional;
    @XmlElement(name = "prof_registro_colegio")
    protected String profRegistroColegio;
    @XmlElement(name = "prof_email")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String profEmail;
    @XmlElement(name = "prof_telefono")
    protected CTTelefono profTelefono;
    @XmlElement(name = "prof_direccion", required = true)
    protected CTDireccion profDireccion;
    @XmlElement(name = "prof_fax")
    protected CTTelefono profFax;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Gets the value of the profesional property.
     * 
     * @return
     *     possible object is
     *     {@link CTPersona }
     *     
     */
    public CTPersona getProfesional() {
        return profesional;
    }

    /**
     * Sets the value of the profesional property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTPersona }
     *     
     */
    public void setProfesional(CTPersona value) {
        this.profesional = value;
    }

    /**
     * Gets the value of the profEspecialidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfEspecialidad() {
        return profEspecialidad;
    }

    /**
     * Sets the value of the profEspecialidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfEspecialidad(String value) {
        this.profEspecialidad = value;
    }

    /**
     * Gets the value of the codigoTipoProfesional property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoTipoProfesional() {
        return codigoTipoProfesional;
    }

    /**
     * Sets the value of the codigoTipoProfesional property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoTipoProfesional(BigInteger value) {
        this.codigoTipoProfesional = value;
    }

    /**
     * Gets the value of the profRegistroColegio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfRegistroColegio() {
        return profRegistroColegio;
    }

    /**
     * Sets the value of the profRegistroColegio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfRegistroColegio(String value) {
        this.profRegistroColegio = value;
    }

    /**
     * Gets the value of the profEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfEmail() {
        return profEmail;
    }

    /**
     * Sets the value of the profEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfEmail(String value) {
        this.profEmail = value;
    }

    /**
     * Gets the value of the profTelefono property.
     * 
     * @return
     *     possible object is
     *     {@link CTTelefono }
     *     
     */
    public CTTelefono getProfTelefono() {
        return profTelefono;
    }

    /**
     * Sets the value of the profTelefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTTelefono }
     *     
     */
    public void setProfTelefono(CTTelefono value) {
        this.profTelefono = value;
    }

    /**
     * Gets the value of the profDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link CTDireccion }
     *     
     */
    public CTDireccion getProfDireccion() {
        return profDireccion;
    }

    /**
     * Sets the value of the profDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTDireccion }
     *     
     */
    public void setProfDireccion(CTDireccion value) {
        this.profDireccion = value;
    }

    /**
     * Gets the value of the profFax property.
     * 
     * @return
     *     possible object is
     *     {@link CTTelefono }
     *     
     */
    public CTTelefono getProfFax() {
        return profFax;
    }

    /**
     * Sets the value of the profFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTTelefono }
     *     
     */
    public void setProfFax(CTTelefono value) {
        this.profFax = value;
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
