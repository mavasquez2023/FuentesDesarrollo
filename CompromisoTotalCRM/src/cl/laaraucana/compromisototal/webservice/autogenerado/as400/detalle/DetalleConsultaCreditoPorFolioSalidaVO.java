//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.compromisototal.webservice.autogenerado.as400.detalle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for detalleConsultaCreditoPorFolioSalidaVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detalleConsultaCreditoPorFolioSalidaVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoAbonado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoCapitalAmortizado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoGravamen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoInteres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoSeguros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldoCapital" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleConsultaCreditoPorFolioSalidaVO", propOrder = {
    "estadoCuota",
    "fechaVencimiento",
    "montoAbonado",
    "montoCapitalAmortizado",
    "montoGravamen",
    "montoInteres",
    "montoSeguros",
    "numeroCuota",
    "saldoCapital"
})
public class DetalleConsultaCreditoPorFolioSalidaVO {

    protected String estadoCuota;
    protected String fechaVencimiento;
    protected String montoAbonado;
    protected String montoCapitalAmortizado;
    protected String montoGravamen;
    protected String montoInteres;
    protected String montoSeguros;
    protected String numeroCuota;
    protected String saldoCapital;

    /**
     * Gets the value of the estadoCuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCuota() {
        return estadoCuota;
    }

    /**
     * Sets the value of the estadoCuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCuota(String value) {
        this.estadoCuota = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the montoAbonado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoAbonado() {
        return montoAbonado;
    }

    /**
     * Sets the value of the montoAbonado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoAbonado(String value) {
        this.montoAbonado = value;
    }

    /**
     * Gets the value of the montoCapitalAmortizado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoCapitalAmortizado() {
        return montoCapitalAmortizado;
    }

    /**
     * Sets the value of the montoCapitalAmortizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoCapitalAmortizado(String value) {
        this.montoCapitalAmortizado = value;
    }

    /**
     * Gets the value of the montoGravamen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoGravamen() {
        return montoGravamen;
    }

    /**
     * Sets the value of the montoGravamen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoGravamen(String value) {
        this.montoGravamen = value;
    }

    /**
     * Gets the value of the montoInteres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoInteres() {
        return montoInteres;
    }

    /**
     * Sets the value of the montoInteres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoInteres(String value) {
        this.montoInteres = value;
    }

    /**
     * Gets the value of the montoSeguros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoSeguros() {
        return montoSeguros;
    }

    /**
     * Sets the value of the montoSeguros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoSeguros(String value) {
        this.montoSeguros = value;
    }

    /**
     * Gets the value of the numeroCuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * Sets the value of the numeroCuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuota(String value) {
        this.numeroCuota = value;
    }

    /**
     * Gets the value of the saldoCapital property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoCapital() {
        return saldoCapital;
    }

    /**
     * Sets the value of the saldoCapital property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoCapital(String value) {
        this.saldoCapital = value;
    }

}
