//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaDatosPagosCreditosPorFolioAs400ListaSalidaVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaDatosPagosCreditosPorFolioAs400ListaSalidaVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaCuotas" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}detalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="log" type="{http://delegate.toAS.legados.integracion.laaraucana.cl/}log" minOccurs="0"/>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nTotalCuotas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaDatosPagosCreditosPorFolioAs400ListaSalidaVO", propOrder = {
    "listaCuotas",
    "log",
    "resultCode",
    "nTotalCuotas"
})
public class ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO {

    @XmlElement(nillable = true)
    protected List<DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO> listaCuotas;
    protected Log log;
    protected String resultCode;
    protected String nTotalCuotas;

    /**
     * Gets the value of the listaCuotas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCuotas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCuotas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO }
     * 
     * 
     */
    public List<DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO> getListaCuotas() {
        if (listaCuotas == null) {
            listaCuotas = new ArrayList<DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO>();
        }
        return this.listaCuotas;
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

    /**
     * Gets the value of the nTotalCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNTotalCuotas() {
        return nTotalCuotas;
    }

    /**
     * Sets the value of the nTotalCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNTotalCuotas(String value) {
        this.nTotalCuotas = value;
    }

}
