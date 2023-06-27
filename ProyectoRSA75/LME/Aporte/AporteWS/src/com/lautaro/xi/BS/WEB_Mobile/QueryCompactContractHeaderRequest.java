/**
 * QueryCompactContractHeaderRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Compact Contract Header Request
 */
public class QueryCompactContractHeaderRequest  implements java.io.Serializable {
    private com.lautaro.xi.BS.Treasury.MessageHeader messageHeader;

    private com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract queryCompactContractHeader;

    public QueryCompactContractHeaderRequest() {
    }

    public QueryCompactContractHeaderRequest(
           com.lautaro.xi.BS.Treasury.MessageHeader messageHeader,
           com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract queryCompactContractHeader) {
           this.messageHeader = messageHeader;
           this.queryCompactContractHeader = queryCompactContractHeader;
    }


    /**
     * Gets the messageHeader value for this QueryCompactContractHeaderRequest.
     * 
     * @return messageHeader
     */
    public com.lautaro.xi.BS.Treasury.MessageHeader getMessageHeader() {
        return messageHeader;
    }


    /**
     * Sets the messageHeader value for this QueryCompactContractHeaderRequest.
     * 
     * @param messageHeader
     */
    public void setMessageHeader(com.lautaro.xi.BS.Treasury.MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }


    /**
     * Gets the queryCompactContractHeader value for this QueryCompactContractHeaderRequest.
     * 
     * @return queryCompactContractHeader
     */
    public com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract getQueryCompactContractHeader() {
        return queryCompactContractHeader;
    }


    /**
     * Sets the queryCompactContractHeader value for this QueryCompactContractHeaderRequest.
     * 
     * @param queryCompactContractHeader
     */
    public void setQueryCompactContractHeader(com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract queryCompactContractHeader) {
        this.queryCompactContractHeader = queryCompactContractHeader;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryCompactContractHeaderRequest)) return false;
        QueryCompactContractHeaderRequest other = (QueryCompactContractHeaderRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageHeader==null && other.getMessageHeader()==null) || 
             (this.messageHeader!=null &&
              this.messageHeader.equals(other.getMessageHeader()))) &&
            ((this.queryCompactContractHeader==null && other.getQueryCompactContractHeader()==null) || 
             (this.queryCompactContractHeader!=null &&
              this.queryCompactContractHeader.equals(other.getQueryCompactContractHeader())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMessageHeader() != null) {
            _hashCode += getMessageHeader().hashCode();
        }
        if (getQueryCompactContractHeader() != null) {
            _hashCode += getQueryCompactContractHeader().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryCompactContractHeaderRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContractHeaderRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "MessageHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryCompactContractHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QueryCompactContractHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContract"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
