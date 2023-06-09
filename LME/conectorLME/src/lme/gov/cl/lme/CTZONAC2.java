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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CT_ZONA_C2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CT_ZONA_C2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prev_fecha_recepcion_ccaf" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="codigo_tipo_regimen_previsional" type="{urn:www:lme:gov:cl:lme}STRegimen_previsional"/>
 *         &lt;element name="codigo_regimen_previsional" type="{urn:www:lme:gov:cl:lme}STCodigo_regimen_prevision"/>
 *         &lt;element name="codigo_letra_caja" type="{urn:www:lme:gov:cl:lme}STLetra_caja" minOccurs="0"/>
 *         &lt;element name="prev_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_calidad_trabajador" type="{urn:www:lme:gov:cl:lme}STCalidad_trabajador"/>
 *         &lt;element name="codigo_seguro_afc" type="{urn:www:lme:gov:cl:lme}STSiNo"/>
 *         &lt;element name="codigo_seguro_indef" type="{urn:www:lme:gov:cl:lme}STSiNo"/>
 *         &lt;element name="fecha_afiliacion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fecha_contrato" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="codigo_entidad_pagadora" type="{urn:www:lme:gov:cl:lme}STEntidad_pagadora"/>
 *         &lt;element name="prev_nombre_pagador" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CT_ZONA_C2", propOrder = {
    "prevFechaRecepcionCcaf",
    "codigoTipoRegimenPrevisional",
    "codigoRegimenPrevisional",
    "codigoLetraCaja",
    "prevNombre",
    "codigoCalidadTrabajador",
    "codigoSeguroAfc",
    "codigoSeguroIndef",
    "fechaAfiliacion",
    "fechaContrato",
    "codigoEntidadPagadora",
    "prevNombrePagador"
})
public class CTZONAC2 {

    @XmlElement(name = "prev_fecha_recepcion_ccaf")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prevFechaRecepcionCcaf;
    @XmlElement(name = "codigo_tipo_regimen_previsional", required = true)
    protected BigInteger codigoTipoRegimenPrevisional;
    @XmlElement(name = "codigo_regimen_previsional", required = true)
    protected BigInteger codigoRegimenPrevisional;
    @XmlElement(name = "codigo_letra_caja")
    protected String codigoLetraCaja;
    @XmlElement(name = "prev_nombre", required = true)
    protected String prevNombre;
    @XmlElement(name = "codigo_calidad_trabajador", required = true)
    protected BigInteger codigoCalidadTrabajador;
    @XmlElement(name = "codigo_seguro_afc", required = true)
    protected BigInteger codigoSeguroAfc;
    @XmlElement(name = "codigo_seguro_indef", required = true)
    protected BigInteger codigoSeguroIndef;
    @XmlElement(name = "fecha_afiliacion", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAfiliacion;
    @XmlElement(name = "fecha_contrato", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaContrato;
    @XmlElement(name = "codigo_entidad_pagadora", required = true)
    protected STEntidadPagadora codigoEntidadPagadora;
    @XmlElement(name = "prev_nombre_pagador", required = true)
    protected String prevNombrePagador;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Gets the value of the prevFechaRecepcionCcaf property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrevFechaRecepcionCcaf() {
        return prevFechaRecepcionCcaf;
    }

    /**
     * Sets the value of the prevFechaRecepcionCcaf property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrevFechaRecepcionCcaf(XMLGregorianCalendar value) {
        this.prevFechaRecepcionCcaf = value;
    }

    /**
     * Gets the value of the codigoTipoRegimenPrevisional property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoTipoRegimenPrevisional() {
        return codigoTipoRegimenPrevisional;
    }

    /**
     * Sets the value of the codigoTipoRegimenPrevisional property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoTipoRegimenPrevisional(BigInteger value) {
        this.codigoTipoRegimenPrevisional = value;
    }

    /**
     * Gets the value of the codigoRegimenPrevisional property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoRegimenPrevisional() {
        return codigoRegimenPrevisional;
    }

    /**
     * Sets the value of the codigoRegimenPrevisional property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoRegimenPrevisional(BigInteger value) {
        this.codigoRegimenPrevisional = value;
    }

    /**
     * Gets the value of the codigoLetraCaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLetraCaja() {
        return codigoLetraCaja;
    }

    /**
     * Sets the value of the codigoLetraCaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLetraCaja(String value) {
        this.codigoLetraCaja = value;
    }

    /**
     * Gets the value of the prevNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevNombre() {
        return prevNombre;
    }

    /**
     * Sets the value of the prevNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevNombre(String value) {
        this.prevNombre = value;
    }

    /**
     * Gets the value of the codigoCalidadTrabajador property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoCalidadTrabajador() {
        return codigoCalidadTrabajador;
    }

    /**
     * Sets the value of the codigoCalidadTrabajador property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoCalidadTrabajador(BigInteger value) {
        this.codigoCalidadTrabajador = value;
    }

    /**
     * Gets the value of the codigoSeguroAfc property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoSeguroAfc() {
        return codigoSeguroAfc;
    }

    /**
     * Sets the value of the codigoSeguroAfc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoSeguroAfc(BigInteger value) {
        this.codigoSeguroAfc = value;
    }

    /**
     * Gets the value of the codigoSeguroIndef property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoSeguroIndef() {
        return codigoSeguroIndef;
    }

    /**
     * Sets the value of the codigoSeguroIndef property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoSeguroIndef(BigInteger value) {
        this.codigoSeguroIndef = value;
    }

    /**
     * Gets the value of the fechaAfiliacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    /**
     * Sets the value of the fechaAfiliacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAfiliacion(XMLGregorianCalendar value) {
        this.fechaAfiliacion = value;
    }

    /**
     * Gets the value of the fechaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaContrato() {
        return fechaContrato;
    }

    /**
     * Sets the value of the fechaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaContrato(XMLGregorianCalendar value) {
        this.fechaContrato = value;
    }

    /**
     * Gets the value of the codigoEntidadPagadora property.
     * 
     * @return
     *     possible object is
     *     {@link STEntidadPagadora }
     *     
     */
    public STEntidadPagadora getCodigoEntidadPagadora() {
        return codigoEntidadPagadora;
    }

    /**
     * Sets the value of the codigoEntidadPagadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link STEntidadPagadora }
     *     
     */
    public void setCodigoEntidadPagadora(STEntidadPagadora value) {
        this.codigoEntidadPagadora = value;
    }

    /**
     * Gets the value of the prevNombrePagador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevNombrePagador() {
        return prevNombrePagador;
    }

    /**
     * Sets the value of the prevNombrePagador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevNombrePagador(String value) {
        this.prevNombrePagador = value;
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
