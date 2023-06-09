//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.lautaro.xi.bs.web_mobile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.lautaro.xi.bs.treasury.MessageHeader;


/**
 * Query Compact Contract Header Request
 * 
 * <p>Java class for QueryCompactContractHeaderRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryCompactContractHeaderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageHeader" type="{http://lautaro.com/xi/BS/Treasury}MessageHeader"/>
 *         &lt;element name="QueryCompactContractHeader" type="{http://lautaro.com/xi/BS/WEB-Mobile}QueryCompactContract"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryCompactContractHeaderRequest", propOrder = {
    "messageHeader",
    "queryCompactContractHeader"
})
public class QueryCompactContractHeaderRequest {

    @XmlElement(name = "MessageHeader", required = true)
    protected MessageHeader messageHeader;
    @XmlElement(name = "QueryCompactContractHeader", required = true)
    protected QueryCompactContract queryCompactContractHeader;

    /**
     * Gets the value of the messageHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeader }
     *     
     */
    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    /**
     * Sets the value of the messageHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeader }
     *     
     */
    public void setMessageHeader(MessageHeader value) {
        this.messageHeader = value;
    }

    /**
     * Gets the value of the queryCompactContractHeader property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCompactContract }
     *     
     */
    public QueryCompactContract getQueryCompactContractHeader() {
        return queryCompactContractHeader;
    }

    /**
     * Sets the value of the queryCompactContractHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCompactContract }
     *     
     */
    public void setQueryCompactContractHeader(QueryCompactContract value) {
        this.queryCompactContractHeader = value;
    }

}
