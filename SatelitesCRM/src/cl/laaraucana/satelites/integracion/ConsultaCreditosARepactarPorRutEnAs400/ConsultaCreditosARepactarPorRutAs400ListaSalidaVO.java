//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaCreditosARepactarPorRutEnAs400;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaCreditosARepactarPorRutAs400ListaSalidaVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaCreditosARepactarPorRutAs400ListaSalidaVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadCreditos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaSalida" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}consultaCreditosARepactarPorRutAs400SalidaVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="log" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}log" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaCreditosARepactarPorRutAs400ListaSalidaVO", propOrder = {
    "cantidadCreditos",
    "listaSalida",
    "log",
    "resultCode"
})
public class ConsultaCreditosARepactarPorRutAs400ListaSalidaVO {

    protected String cantidadCreditos;
    @XmlElement(nillable = true)
    protected List<ConsultaCreditosARepactarPorRutAs400SalidaVO> listaSalida;
    protected Log log;
    protected String resultCode;

    /**
     * Gets the value of the cantidadCreditos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadCreditos() {
        return cantidadCreditos;
    }

    /**
     * Sets the value of the cantidadCreditos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadCreditos(String value) {
        this.cantidadCreditos = value;
    }

    /**
     * Gets the value of the listaSalida property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaSalida property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaSalida().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsultaCreditosARepactarPorRutAs400SalidaVO }
     * 
     * 
     */
    public List<ConsultaCreditosARepactarPorRutAs400SalidaVO> getListaSalida() {
        if (listaSalida == null) {
            listaSalida = new ArrayList<ConsultaCreditosARepactarPorRutAs400SalidaVO>();
        }
        return this.listaSalida;
    }

    /**
     * Gets the value of the log property.
     * 
     * @return
     *     possible object is
     *     {@link Log }
     *     
     */
    public Log getLog() {
        return log;
    }

    /**
     * Sets the value of the log property.
     * 
     * @param value
     *     allowed object is
     *     {@link Log }
     *     
     */
    public void setLog(Log value) {
        this.log = value;
    }

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultCode(String value) {
        this.resultCode = value;
    }

}
