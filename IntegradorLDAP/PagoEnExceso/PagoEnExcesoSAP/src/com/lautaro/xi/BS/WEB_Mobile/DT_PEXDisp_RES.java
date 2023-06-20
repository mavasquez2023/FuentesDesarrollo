/**
 * DT_PEXDisp_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class DT_PEXDisp_RES  implements java.io.Serializable {
    private int MONTO;

    public DT_PEXDisp_RES() {
    }

    public DT_PEXDisp_RES(
           int MONTO) {
           this.MONTO = MONTO;
    }


    /**
     * Gets the MONTO value for this DT_PEXDisp_RES.
     * 
     * @return MONTO
     */
    public int getMONTO() {
        return MONTO;
    }


    /**
     * Sets the MONTO value for this DT_PEXDisp_RES.
     * 
     * @param MONTO
     */
    public void setMONTO(int MONTO) {
        this.MONTO = MONTO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_PEXDisp_RES)) return false;
        DT_PEXDisp_RES other = (DT_PEXDisp_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.MONTO == other.getMONTO();
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
        _hashCode += getMONTO();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_PEXDisp_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_PEXDisp_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
