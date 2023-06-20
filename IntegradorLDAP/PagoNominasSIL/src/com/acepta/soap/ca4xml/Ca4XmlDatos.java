/**
 * Ca4XmlDatos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class Ca4XmlDatos  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.DTEDefType DTE;

    public Ca4XmlDatos() {
    }

    public Ca4XmlDatos(
           com.acepta.soap.ca4xml.DTEDefType DTE) {
           this.DTE = DTE;
    }


    /**
     * Gets the DTE value for this Ca4XmlDatos.
     * 
     * @return DTE
     */
    public com.acepta.soap.ca4xml.DTEDefType getDTE() {
        return DTE;
    }


    /**
     * Sets the DTE value for this Ca4XmlDatos.
     * 
     * @param DTE
     */
    public void setDTE(com.acepta.soap.ca4xml.DTEDefType DTE) {
        this.DTE = DTE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ca4XmlDatos)) return false;
        Ca4XmlDatos other = (Ca4XmlDatos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DTE==null && other.getDTE()==null) || 
             (this.DTE!=null &&
              this.DTE.equals(other.getDTE())));
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
        if (getDTE() != null) {
            _hashCode += getDTE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ca4XmlDatos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">ca4xml>datos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "DTEDefType"));
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
