/**
 * QueryCompactContractHeaderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Compact Contract Header Response
 */
public class QueryCompactContractHeaderResponse  implements java.io.Serializable {
    private com.lautaro.xi.BS.Treasury.MessageHeader messageHeader;

    private com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader[] queryCompactContractHeader;

    private java.lang.String resultCode;

    private com.lautaro.xi.BS.Treasury.Log[] log;

    public QueryCompactContractHeaderResponse() {
    }

    public QueryCompactContractHeaderResponse(
           com.lautaro.xi.BS.Treasury.MessageHeader messageHeader,
           com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader[] queryCompactContractHeader,
           java.lang.String resultCode,
           com.lautaro.xi.BS.Treasury.Log[] log) {
           this.messageHeader = messageHeader;
           this.queryCompactContractHeader = queryCompactContractHeader;
           this.resultCode = resultCode;
           this.log = log;
    }


    /**
     * Gets the messageHeader value for this QueryCompactContractHeaderResponse.
     * 
     * @return messageHeader
     */
    public com.lautaro.xi.BS.Treasury.MessageHeader getMessageHeader() {
        return messageHeader;
    }


    /**
     * Sets the messageHeader value for this QueryCompactContractHeaderResponse.
     * 
     * @param messageHeader
     */
    public void setMessageHeader(com.lautaro.xi.BS.Treasury.MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }


    /**
     * Gets the queryCompactContractHeader value for this QueryCompactContractHeaderResponse.
     * 
     * @return queryCompactContractHeader
     */
    public com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader[] getQueryCompactContractHeader() {
        return queryCompactContractHeader;
    }


    /**
     * Sets the queryCompactContractHeader value for this QueryCompactContractHeaderResponse.
     * 
     * @param queryCompactContractHeader
     */
    public void setQueryCompactContractHeader(com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader[] queryCompactContractHeader) {
        this.queryCompactContractHeader = queryCompactContractHeader;
    }

    public com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader getQueryCompactContractHeader(int i) {
        return this.queryCompactContractHeader[i];
    }

    public void setQueryCompactContractHeader(int i, com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader _value) {
        this.queryCompactContractHeader[i] = _value;
    }


    /**
     * Gets the resultCode value for this QueryCompactContractHeaderResponse.
     * 
     * @return resultCode
     */
    public java.lang.String getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this QueryCompactContractHeaderResponse.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * Gets the log value for this QueryCompactContractHeaderResponse.
     * 
     * @return log
     */
    public com.lautaro.xi.BS.Treasury.Log[] getLog() {
        return log;
    }


    /**
     * Sets the log value for this QueryCompactContractHeaderResponse.
     * 
     * @param log
     */
    public void setLog(com.lautaro.xi.BS.Treasury.Log[] log) {
        this.log = log;
    }

    public com.lautaro.xi.BS.Treasury.Log getLog(int i) {
        return this.log[i];
    }

    public void setLog(int i, com.lautaro.xi.BS.Treasury.Log _value) {
        this.log[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryCompactContractHeaderResponse)) return false;
        QueryCompactContractHeaderResponse other = (QueryCompactContractHeaderResponse) obj;
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
              java.util.Arrays.equals(this.queryCompactContractHeader, other.getQueryCompactContractHeader()))) &&
            ((this.resultCode==null && other.getResultCode()==null) || 
             (this.resultCode!=null &&
              this.resultCode.equals(other.getResultCode()))) &&
            ((this.log==null && other.getLog()==null) || 
             (this.log!=null &&
              java.util.Arrays.equals(this.log, other.getLog())));
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
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQueryCompactContractHeader());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQueryCompactContractHeader(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResultCode() != null) {
            _hashCode += getResultCode().hashCode();
        }
        if (getLog() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLog());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLog(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryCompactContractHeaderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContractHeaderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "MessageHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryCompactContractHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QueryCompactContractHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContractHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("log");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Log"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "Log"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
