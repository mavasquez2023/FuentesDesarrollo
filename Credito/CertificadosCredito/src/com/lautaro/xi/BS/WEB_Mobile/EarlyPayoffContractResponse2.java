/**
 * EarlyPayoffContractResponse2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class EarlyPayoffContractResponse2  implements java.io.Serializable {
    private com.lautaro.xi.BS.Treasury.MessageHeader messageHeader;

    private com.lautaro.xi.BS.WEB_Mobile.PayoffContract2 payoffContract;

    private java.lang.String resultCode;

    private com.lautaro.xi.BS.Treasury.Log log;

    public EarlyPayoffContractResponse2() {
    }

    public EarlyPayoffContractResponse2(
           com.lautaro.xi.BS.Treasury.MessageHeader messageHeader,
           com.lautaro.xi.BS.WEB_Mobile.PayoffContract2 payoffContract,
           java.lang.String resultCode,
           com.lautaro.xi.BS.Treasury.Log log) {
           this.messageHeader = messageHeader;
           this.payoffContract = payoffContract;
           this.resultCode = resultCode;
           this.log = log;
    }


    /**
     * Gets the messageHeader value for this EarlyPayoffContractResponse2.
     * 
     * @return messageHeader
     */
    public com.lautaro.xi.BS.Treasury.MessageHeader getMessageHeader() {
        return messageHeader;
    }


    /**
     * Sets the messageHeader value for this EarlyPayoffContractResponse2.
     * 
     * @param messageHeader
     */
    public void setMessageHeader(com.lautaro.xi.BS.Treasury.MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }


    /**
     * Gets the payoffContract value for this EarlyPayoffContractResponse2.
     * 
     * @return payoffContract
     */
    public com.lautaro.xi.BS.WEB_Mobile.PayoffContract2 getPayoffContract() {
        return payoffContract;
    }


    /**
     * Sets the payoffContract value for this EarlyPayoffContractResponse2.
     * 
     * @param payoffContract
     */
    public void setPayoffContract(com.lautaro.xi.BS.WEB_Mobile.PayoffContract2 payoffContract) {
        this.payoffContract = payoffContract;
    }


    /**
     * Gets the resultCode value for this EarlyPayoffContractResponse2.
     * 
     * @return resultCode
     */
    public java.lang.String getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this EarlyPayoffContractResponse2.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * Gets the log value for this EarlyPayoffContractResponse2.
     * 
     * @return log
     */
    public com.lautaro.xi.BS.Treasury.Log getLog() {
        return log;
    }


    /**
     * Sets the log value for this EarlyPayoffContractResponse2.
     * 
     * @param log
     */
    public void setLog(com.lautaro.xi.BS.Treasury.Log log) {
        this.log = log;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EarlyPayoffContractResponse2)) return false;
        EarlyPayoffContractResponse2 other = (EarlyPayoffContractResponse2) obj;
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
            ((this.payoffContract==null && other.getPayoffContract()==null) || 
             (this.payoffContract!=null &&
              this.payoffContract.equals(other.getPayoffContract()))) &&
            ((this.resultCode==null && other.getResultCode()==null) || 
             (this.resultCode!=null &&
              this.resultCode.equals(other.getResultCode()))) &&
            ((this.log==null && other.getLog()==null) || 
             (this.log!=null &&
              this.log.equals(other.getLog())));
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
        if (getPayoffContract() != null) {
            _hashCode += getPayoffContract().hashCode();
        }
        if (getResultCode() != null) {
            _hashCode += getResultCode().hashCode();
        }
        if (getLog() != null) {
            _hashCode += getLog().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EarlyPayoffContractResponse2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "EarlyPayoffContractResponse2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "MessageHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoffContract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PayoffContract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "PayoffContract2"));
        elemField.setNillable(false);
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
