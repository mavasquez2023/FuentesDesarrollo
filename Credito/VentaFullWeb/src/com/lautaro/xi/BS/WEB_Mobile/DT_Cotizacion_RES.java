/**
 * DT_Cotizacion_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class DT_Cotizacion_RES  implements java.io.Serializable {
    private java.lang.String COTIZACION;

    private com.lautaro.xi.BS.WEB_Mobile.Log[] LOG;

    public DT_Cotizacion_RES() {
    }

    public DT_Cotizacion_RES(
           java.lang.String COTIZACION,
           com.lautaro.xi.BS.WEB_Mobile.Log[] LOG) {
           this.COTIZACION = COTIZACION;
           this.LOG = LOG;
    }


    /**
     * Gets the COTIZACION value for this DT_Cotizacion_RES.
     * 
     * @return COTIZACION
     */
    public java.lang.String getCOTIZACION() {
        return COTIZACION;
    }


    /**
     * Sets the COTIZACION value for this DT_Cotizacion_RES.
     * 
     * @param COTIZACION
     */
    public void setCOTIZACION(java.lang.String COTIZACION) {
        this.COTIZACION = COTIZACION;
    }


    /**
     * Gets the LOG value for this DT_Cotizacion_RES.
     * 
     * @return LOG
     */
    public com.lautaro.xi.BS.WEB_Mobile.Log[] getLOG() {
        return LOG;
    }


    /**
     * Sets the LOG value for this DT_Cotizacion_RES.
     * 
     * @param LOG
     */
    public void setLOG(com.lautaro.xi.BS.WEB_Mobile.Log[] LOG) {
        this.LOG = LOG;
    }

    public com.lautaro.xi.BS.WEB_Mobile.Log getLOG(int i) {
        return this.LOG[i];
    }

    public void setLOG(int i, com.lautaro.xi.BS.WEB_Mobile.Log _value) {
        this.LOG[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_Cotizacion_RES)) return false;
        DT_Cotizacion_RES other = (DT_Cotizacion_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.COTIZACION==null && other.getCOTIZACION()==null) || 
             (this.COTIZACION!=null &&
              this.COTIZACION.equals(other.getCOTIZACION()))) &&
            ((this.LOG==null && other.getLOG()==null) || 
             (this.LOG!=null &&
              java.util.Arrays.equals(this.LOG, other.getLOG())));
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
        if (getCOTIZACION() != null) {
            _hashCode += getCOTIZACION().hashCode();
        }
        if (getLOG() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLOG());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLOG(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_Cotizacion_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_Cotizacion_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COTIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COTIZACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "Log"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
