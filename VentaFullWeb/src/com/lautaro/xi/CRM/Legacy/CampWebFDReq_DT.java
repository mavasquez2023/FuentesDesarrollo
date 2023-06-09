/**
 * CampWebFDReq_DT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.Legacy;

public class CampWebFDReq_DT  implements java.io.Serializable {
    private java.lang.String IV_RUT_AFILIADO;

    public CampWebFDReq_DT() {
    }

    public CampWebFDReq_DT(
           java.lang.String IV_RUT_AFILIADO) {
           this.IV_RUT_AFILIADO = IV_RUT_AFILIADO;
    }


    /**
     * Gets the IV_RUT_AFILIADO value for this CampWebFDReq_DT.
     * 
     * @return IV_RUT_AFILIADO
     */
    public java.lang.String getIV_RUT_AFILIADO() {
        return IV_RUT_AFILIADO;
    }


    /**
     * Sets the IV_RUT_AFILIADO value for this CampWebFDReq_DT.
     * 
     * @param IV_RUT_AFILIADO
     */
    public void setIV_RUT_AFILIADO(java.lang.String IV_RUT_AFILIADO) {
        this.IV_RUT_AFILIADO = IV_RUT_AFILIADO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CampWebFDReq_DT)) return false;
        CampWebFDReq_DT other = (CampWebFDReq_DT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IV_RUT_AFILIADO==null && other.getIV_RUT_AFILIADO()==null) || 
             (this.IV_RUT_AFILIADO!=null &&
              this.IV_RUT_AFILIADO.equals(other.getIV_RUT_AFILIADO())));
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
        if (getIV_RUT_AFILIADO() != null) {
            _hashCode += getIV_RUT_AFILIADO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CampWebFDReq_DT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/Legacy", "CampWebFDReq_DT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IV_RUT_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IV_RUT_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
