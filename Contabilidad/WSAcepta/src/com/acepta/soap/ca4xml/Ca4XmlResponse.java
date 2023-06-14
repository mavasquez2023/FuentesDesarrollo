/**
 * Ca4XmlResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class Ca4XmlResponse  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.Ca4XmlResponseRetval retval;

    public Ca4XmlResponse() {
    }

    public Ca4XmlResponse(
           com.acepta.soap.ca4xml.Ca4XmlResponseRetval retval) {
           this.retval = retval;
    }


    /**
     * Gets the retval value for this Ca4XmlResponse.
     * 
     * @return retval
     */
    public com.acepta.soap.ca4xml.Ca4XmlResponseRetval getRetval() {
        return retval;
    }


    /**
     * Sets the retval value for this Ca4XmlResponse.
     * 
     * @param retval
     */
    public void setRetval(com.acepta.soap.ca4xml.Ca4XmlResponseRetval retval) {
        this.retval = retval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ca4XmlResponse)) return false;
        Ca4XmlResponse other = (Ca4XmlResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.retval==null && other.getRetval()==null) || 
             (this.retval!=null &&
              this.retval.equals(other.getRetval())));
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
        if (getRetval() != null) {
            _hashCode += getRetval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ca4XmlResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "ca4xmlResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retval");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">ca4xmlResponse>retval"));
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
