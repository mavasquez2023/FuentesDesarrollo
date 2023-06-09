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
 * <p>Java class for CT_ZONA_AC complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_ZONA_AC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coddiagnostico_principal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="diagnostico_secundario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coddiagnostico_secundario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coddiagnostico_otro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email_trabajador" type="{urn:www:lme:gov:cl:lme}STEmail" minOccurs="0"/>
 *         &lt;element name="canal_contacto" type="{urn:www:lme:gov:cl:lme}STContacto" minOccurs="0"/>
 *         &lt;element name="celular_contacto" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="direccion_contacto" type="{urn:www:lme:gov:cl:lme}CTDireccion" minOccurs="0"/>
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
@XmlType(name = "CT_ZONA_AC", propOrder = {
    "coddiagnosticoPrincipal",
    "diagnosticoSecundario",
    "coddiagnosticoSecundario",
    "coddiagnosticoOtro",
    "emailTrabajador",
    "canalContacto",
    "celularContacto",
    "direccionContacto"
})
public class CTZONAAC {

    @XmlElement(name = "coddiagnostico_principal", required = true)
    protected String coddiagnosticoPrincipal;
    @XmlElement(name = "diagnostico_secundario")
    protected String diagnosticoSecundario;
    @XmlElement(name = "coddiagnostico_secundario")
    protected String coddiagnosticoSecundario;
    @XmlElement(name = "coddiagnostico_otro")
    protected String coddiagnosticoOtro;
    @XmlElement(name = "email_trabajador")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String emailTrabajador;
    @XmlElement(name = "canal_contacto")
    protected BigInteger canalContacto;
    @XmlElement(name = "celular_contacto")
    protected BigInteger celularContacto;
    @XmlElement(name = "direccion_contacto")
    protected CTDireccion direccionContacto;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Gets the value of the coddiagnosticoPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoddiagnosticoPrincipal() {
        return coddiagnosticoPrincipal;
    }

    /**
     * Sets the value of the coddiagnosticoPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoddiagnosticoPrincipal(String value) {
        this.coddiagnosticoPrincipal = value;
    }

    /**
     * Gets the value of the diagnosticoSecundario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosticoSecundario() {
        return diagnosticoSecundario;
    }

    /**
     * Sets the value of the diagnosticoSecundario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosticoSecundario(String value) {
        this.diagnosticoSecundario = value;
    }

    /**
     * Gets the value of the coddiagnosticoSecundario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoddiagnosticoSecundario() {
        return coddiagnosticoSecundario;
    }

    /**
     * Sets the value of the coddiagnosticoSecundario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoddiagnosticoSecundario(String value) {
        this.coddiagnosticoSecundario = value;
    }

    /**
     * Gets the value of the coddiagnosticoOtro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoddiagnosticoOtro() {
        return coddiagnosticoOtro;
    }

    /**
     * Sets the value of the coddiagnosticoOtro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoddiagnosticoOtro(String value) {
        this.coddiagnosticoOtro = value;
    }

    /**
     * Gets the value of the emailTrabajador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    /**
     * Sets the value of the emailTrabajador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailTrabajador(String value) {
        this.emailTrabajador = value;
    }

    /**
     * Gets the value of the canalContacto property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanalContacto() {
        return canalContacto;
    }

    /**
     * Sets the value of the canalContacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanalContacto(BigInteger value) {
        this.canalContacto = value;
    }

    /**
     * Gets the value of the celularContacto property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCelularContacto() {
        return celularContacto;
    }

    /**
     * Sets the value of the celularContacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCelularContacto(BigInteger value) {
        this.celularContacto = value;
    }

    /**
     * Gets the value of the direccionContacto property.
     * 
     * @return
     *     possible object is
     *     {@link CTDireccion }
     *     
     */
    public CTDireccion getDireccionContacto() {
        return direccionContacto;
    }

    /**
     * Sets the value of the direccionContacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTDireccion }
     *     
     */
    public void setDireccionContacto(CTDireccion value) {
        this.direccionContacto = value;
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
