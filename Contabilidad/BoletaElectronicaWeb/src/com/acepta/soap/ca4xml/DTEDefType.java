/**
 * DTEDefType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class DTEDefType  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.BOLETADefType boleta;

    private java.math.BigDecimal version;  // attribute

    public DTEDefType() {
    }

    public DTEDefType(
           com.acepta.soap.ca4xml.BOLETADefType boleta,
           java.math.BigDecimal version) {
           this.boleta = boleta;
           this.version = version;
    }


    /**
     * Gets the boleta value for this DTEDefType.
     * 
     * @return boleta
     */
    public com.acepta.soap.ca4xml.BOLETADefType getBoleta() {
        return boleta;
    }


    /**
     * Sets the boleta value for this DTEDefType.
     * 
     * @param boleta
     */
    public void setBoleta(com.acepta.soap.ca4xml.BOLETADefType boleta) {
        this.boleta = boleta;
    }


    /**
     * Gets the version value for this DTEDefType.
     * 
     * @return version
     */
    public java.math.BigDecimal getVersion() {
        return version;
    }


    /**
     * Sets the version value for this DTEDefType.
     * 
     * @param version
     */
    public void setVersion(java.math.BigDecimal version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DTEDefType)) return false;
        DTEDefType other = (DTEDefType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.boleta==null && other.getBoleta()==null) || 
             (this.boleta!=null &&
              this.boleta.equals(other.getBoleta()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
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
        if (getBoleta() != null) {
            _hashCode += getBoleta().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DTEDefType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "DTEDefType"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("version");
        attrField.setXmlName(new javax.xml.namespace.QName("", "version"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boleta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Boleta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "BOLETADefType"));
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
