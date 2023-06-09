/**
 * DT_FeriadosAnuales_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000006061 - Venta de créditos full digital
 */
public class DT_FeriadosAnuales_REQ  implements java.io.Serializable {
    private java.math.BigInteger ANIO;

    public DT_FeriadosAnuales_REQ() {
    }

    public DT_FeriadosAnuales_REQ(
           java.math.BigInteger ANIO) {
           this.ANIO = ANIO;
    }


    /**
     * Gets the ANIO value for this DT_FeriadosAnuales_REQ.
     * 
     * @return ANIO
     */
    public java.math.BigInteger getANIO() {
        return ANIO;
    }


    /**
     * Sets the ANIO value for this DT_FeriadosAnuales_REQ.
     * 
     * @param ANIO
     */
    public void setANIO(java.math.BigInteger ANIO) {
        this.ANIO = ANIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_FeriadosAnuales_REQ)) return false;
        DT_FeriadosAnuales_REQ other = (DT_FeriadosAnuales_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ANIO==null && other.getANIO()==null) || 
             (this.ANIO!=null &&
              this.ANIO.equals(other.getANIO())));
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
        if (getANIO() != null) {
            _hashCode += getANIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_FeriadosAnuales_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_FeriadosAnuales_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ANIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
