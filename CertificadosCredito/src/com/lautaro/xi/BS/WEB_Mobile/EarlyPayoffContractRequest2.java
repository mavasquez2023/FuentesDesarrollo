/**
 * EarlyPayoffContractRequest2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class EarlyPayoffContractRequest2  implements java.io.Serializable {
    private com.lautaro.xi.BS.Treasury.MessageHeader messageHeader;

    private com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContract2 earlyPayoffContract;

    public EarlyPayoffContractRequest2() {
    }

    public EarlyPayoffContractRequest2(
           com.lautaro.xi.BS.Treasury.MessageHeader messageHeader,
           com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContract2 earlyPayoffContract) {
           this.messageHeader = messageHeader;
           this.earlyPayoffContract = earlyPayoffContract;
    }


    /**
     * Gets the messageHeader value for this EarlyPayoffContractRequest2.
     * 
     * @return messageHeader
     */
    public com.lautaro.xi.BS.Treasury.MessageHeader getMessageHeader() {
        return messageHeader;
    }


    /**
     * Sets the messageHeader value for this EarlyPayoffContractRequest2.
     * 
     * @param messageHeader
     */
    public void setMessageHeader(com.lautaro.xi.BS.Treasury.MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }


    /**
     * Gets the earlyPayoffContract value for this EarlyPayoffContractRequest2.
     * 
     * @return earlyPayoffContract
     */
    public com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContract2 getEarlyPayoffContract() {
        return earlyPayoffContract;
    }


    /**
     * Sets the earlyPayoffContract value for this EarlyPayoffContractRequest2.
     * 
     * @param earlyPayoffContract
     */
    public void setEarlyPayoffContract(com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContract2 earlyPayoffContract) {
        this.earlyPayoffContract = earlyPayoffContract;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EarlyPayoffContractRequest2)) return false;
        EarlyPayoffContractRequest2 other = (EarlyPayoffContractRequest2) obj;
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
            ((this.earlyPayoffContract==null && other.getEarlyPayoffContract()==null) || 
             (this.earlyPayoffContract!=null &&
              this.earlyPayoffContract.equals(other.getEarlyPayoffContract())));
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
        if (getEarlyPayoffContract() != null) {
            _hashCode += getEarlyPayoffContract().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EarlyPayoffContractRequest2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "EarlyPayoffContractRequest2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "MessageHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("earlyPayoffContract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EarlyPayoffContract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "EarlyPayoffContract2"));
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
