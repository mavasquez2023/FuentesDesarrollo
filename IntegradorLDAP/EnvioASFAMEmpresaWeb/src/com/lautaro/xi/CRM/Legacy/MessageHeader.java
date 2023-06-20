/**
 * MessageHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.Legacy;


/**
 * Header Entries
 */
public class MessageHeader  implements java.io.Serializable {
    /* Fecha Creacion */
    private java.lang.String DATECREATION;

    /* Sistema Generador */
    private java.lang.String INTERNALORGANIZATION;

    /* Host Generador */
    private java.lang.String HOST;

    /* Usuario Generador */
    private java.lang.String USER;

    public MessageHeader() {
    }

    public MessageHeader(
           java.lang.String DATECREATION,
           java.lang.String INTERNALORGANIZATION,
           java.lang.String HOST,
           java.lang.String USER) {
           this.DATECREATION = DATECREATION;
           this.INTERNALORGANIZATION = INTERNALORGANIZATION;
           this.HOST = HOST;
           this.USER = USER;
    }


    /**
     * Gets the DATECREATION value for this MessageHeader.
     * 
     * @return DATECREATION   * Fecha Creacion
     */
    public java.lang.String getDATECREATION() {
        return DATECREATION;
    }


    /**
     * Sets the DATECREATION value for this MessageHeader.
     * 
     * @param DATECREATION   * Fecha Creacion
     */
    public void setDATECREATION(java.lang.String DATECREATION) {
        this.DATECREATION = DATECREATION;
    }


    /**
     * Gets the INTERNALORGANIZATION value for this MessageHeader.
     * 
     * @return INTERNALORGANIZATION   * Sistema Generador
     */
    public java.lang.String getINTERNALORGANIZATION() {
        return INTERNALORGANIZATION;
    }


    /**
     * Sets the INTERNALORGANIZATION value for this MessageHeader.
     * 
     * @param INTERNALORGANIZATION   * Sistema Generador
     */
    public void setINTERNALORGANIZATION(java.lang.String INTERNALORGANIZATION) {
        this.INTERNALORGANIZATION = INTERNALORGANIZATION;
    }


    /**
     * Gets the HOST value for this MessageHeader.
     * 
     * @return HOST   * Host Generador
     */
    public java.lang.String getHOST() {
        return HOST;
    }


    /**
     * Sets the HOST value for this MessageHeader.
     * 
     * @param HOST   * Host Generador
     */
    public void setHOST(java.lang.String HOST) {
        this.HOST = HOST;
    }


    /**
     * Gets the USER value for this MessageHeader.
     * 
     * @return USER   * Usuario Generador
     */
    public java.lang.String getUSER() {
        return USER;
    }


    /**
     * Sets the USER value for this MessageHeader.
     * 
     * @param USER   * Usuario Generador
     */
    public void setUSER(java.lang.String USER) {
        this.USER = USER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageHeader)) return false;
        MessageHeader other = (MessageHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DATECREATION==null && other.getDATECREATION()==null) || 
             (this.DATECREATION!=null &&
              this.DATECREATION.equals(other.getDATECREATION()))) &&
            ((this.INTERNALORGANIZATION==null && other.getINTERNALORGANIZATION()==null) || 
             (this.INTERNALORGANIZATION!=null &&
              this.INTERNALORGANIZATION.equals(other.getINTERNALORGANIZATION()))) &&
            ((this.HOST==null && other.getHOST()==null) || 
             (this.HOST!=null &&
              this.HOST.equals(other.getHOST()))) &&
            ((this.USER==null && other.getUSER()==null) || 
             (this.USER!=null &&
              this.USER.equals(other.getUSER())));
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
        if (getDATECREATION() != null) {
            _hashCode += getDATECREATION().hashCode();
        }
        if (getINTERNALORGANIZATION() != null) {
            _hashCode += getINTERNALORGANIZATION().hashCode();
        }
        if (getHOST() != null) {
            _hashCode += getHOST().hashCode();
        }
        if (getUSER() != null) {
            _hashCode += getUSER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/Legacy", "MessageHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATECREATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATECREATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNALORGANIZATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTERNALORGANIZATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HOST");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HOST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
