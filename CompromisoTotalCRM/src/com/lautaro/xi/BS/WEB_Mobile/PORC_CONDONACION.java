/**
 * PORC_CONDONACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003302
 */
public class PORC_CONDONACION  implements java.io.Serializable {
    private java.math.BigDecimal CONDONAC_GRV;

    private java.math.BigDecimal CONDONAC_GC;

    private java.math.BigDecimal CONDONAC_HONO;

    public PORC_CONDONACION() {
    }

    public PORC_CONDONACION(
           java.math.BigDecimal CONDONAC_GRV,
           java.math.BigDecimal CONDONAC_GC,
           java.math.BigDecimal CONDONAC_HONO) {
           this.CONDONAC_GRV = CONDONAC_GRV;
           this.CONDONAC_GC = CONDONAC_GC;
           this.CONDONAC_HONO = CONDONAC_HONO;
    }


    /**
     * Gets the CONDONAC_GRV value for this PORC_CONDONACION.
     * 
     * @return CONDONAC_GRV
     */
    public java.math.BigDecimal getCONDONAC_GRV() {
        return CONDONAC_GRV;
    }


    /**
     * Sets the CONDONAC_GRV value for this PORC_CONDONACION.
     * 
     * @param CONDONAC_GRV
     */
    public void setCONDONAC_GRV(java.math.BigDecimal CONDONAC_GRV) {
        this.CONDONAC_GRV = CONDONAC_GRV;
    }


    /**
     * Gets the CONDONAC_GC value for this PORC_CONDONACION.
     * 
     * @return CONDONAC_GC
     */
    public java.math.BigDecimal getCONDONAC_GC() {
        return CONDONAC_GC;
    }


    /**
     * Sets the CONDONAC_GC value for this PORC_CONDONACION.
     * 
     * @param CONDONAC_GC
     */
    public void setCONDONAC_GC(java.math.BigDecimal CONDONAC_GC) {
        this.CONDONAC_GC = CONDONAC_GC;
    }


    /**
     * Gets the CONDONAC_HONO value for this PORC_CONDONACION.
     * 
     * @return CONDONAC_HONO
     */
    public java.math.BigDecimal getCONDONAC_HONO() {
        return CONDONAC_HONO;
    }


    /**
     * Sets the CONDONAC_HONO value for this PORC_CONDONACION.
     * 
     * @param CONDONAC_HONO
     */
    public void setCONDONAC_HONO(java.math.BigDecimal CONDONAC_HONO) {
        this.CONDONAC_HONO = CONDONAC_HONO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PORC_CONDONACION)) return false;
        PORC_CONDONACION other = (PORC_CONDONACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CONDONAC_GRV==null && other.getCONDONAC_GRV()==null) || 
             (this.CONDONAC_GRV!=null &&
              this.CONDONAC_GRV.equals(other.getCONDONAC_GRV()))) &&
            ((this.CONDONAC_GC==null && other.getCONDONAC_GC()==null) || 
             (this.CONDONAC_GC!=null &&
              this.CONDONAC_GC.equals(other.getCONDONAC_GC()))) &&
            ((this.CONDONAC_HONO==null && other.getCONDONAC_HONO()==null) || 
             (this.CONDONAC_HONO!=null &&
              this.CONDONAC_HONO.equals(other.getCONDONAC_HONO())));
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
        if (getCONDONAC_GRV() != null) {
            _hashCode += getCONDONAC_GRV().hashCode();
        }
        if (getCONDONAC_GC() != null) {
            _hashCode += getCONDONAC_GC().hashCode();
        }
        if (getCONDONAC_HONO() != null) {
            _hashCode += getCONDONAC_HONO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PORC_CONDONACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "PORC_CONDONACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONDONAC_GRV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONDONAC_GRV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONDONAC_GC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONDONAC_GC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONDONAC_HONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONDONAC_HONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
