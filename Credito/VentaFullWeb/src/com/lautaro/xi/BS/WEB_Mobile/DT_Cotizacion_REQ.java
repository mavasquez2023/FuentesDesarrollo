/**
 * DT_Cotizacion_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class DT_Cotizacion_REQ  implements java.io.Serializable {
    private java.lang.String MONEDA;

    private String FECHA;

    public DT_Cotizacion_REQ() {
    }

    public DT_Cotizacion_REQ(
           java.lang.String MONEDA,
           String FECHA) {
           this.MONEDA = MONEDA;
           this.FECHA = FECHA;
    }


    /**
     * Gets the MONEDA value for this DT_Cotizacion_REQ.
     * 
     * @return MONEDA
     */
    public java.lang.String getMONEDA() {
        return MONEDA;
    }


    /**
     * Sets the MONEDA value for this DT_Cotizacion_REQ.
     * 
     * @param MONEDA
     */
    public void setMONEDA(java.lang.String MONEDA) {
        this.MONEDA = MONEDA;
    }


    /**
     * Gets the FECHA value for this DT_Cotizacion_REQ.
     * 
     * @return FECHA
     */
    public String getFECHA() {
        return FECHA;
    }


    /**
     * Sets the FECHA value for this DT_Cotizacion_REQ.
     * 
     * @param fechaFormateada
     */
    public void setFECHA(String fechaFormateada) {
        this.FECHA = fechaFormateada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_Cotizacion_REQ)) return false;
        DT_Cotizacion_REQ other = (DT_Cotizacion_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MONEDA==null && other.getMONEDA()==null) || 
             (this.MONEDA!=null &&
              this.MONEDA.equals(other.getMONEDA()))) &&
            ((this.FECHA==null && other.getFECHA()==null) || 
             (this.FECHA!=null &&
              this.FECHA.equals(other.getFECHA())));
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
        if (getMONEDA() != null) {
            _hashCode += getMONEDA().hashCode();
        }
        if (getFECHA() != null) {
            _hashCode += getFECHA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_Cotizacion_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_Cotizacion_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONEDA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONEDA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
