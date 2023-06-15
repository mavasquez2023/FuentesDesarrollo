/**
 * DT_CampAcuPagoCastigo_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;


/**
 * 8000003746
 */
public class DT_CampAcuPagoCastigo_RES  implements java.io.Serializable {
    private java.math.BigDecimal TOTAL;

    private java.math.BigDecimal CONVENIO;

    private java.util.Date VIGENCIA;

    public DT_CampAcuPagoCastigo_RES() {
    }

    public DT_CampAcuPagoCastigo_RES(
           java.math.BigDecimal TOTAL,
           java.math.BigDecimal CONVENIO,
           java.util.Date VIGENCIA) {
           this.TOTAL = TOTAL;
           this.CONVENIO = CONVENIO;
           this.VIGENCIA = VIGENCIA;
    }


    /**
     * Gets the TOTAL value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @return TOTAL
     */
    public java.math.BigDecimal getTOTAL() {
        return TOTAL;
    }


    /**
     * Sets the TOTAL value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @param TOTAL
     */
    public void setTOTAL(java.math.BigDecimal TOTAL) {
        this.TOTAL = TOTAL;
    }


    /**
     * Gets the CONVENIO value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @return CONVENIO
     */
    public java.math.BigDecimal getCONVENIO() {
        return CONVENIO;
    }


    /**
     * Sets the CONVENIO value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @param CONVENIO
     */
    public void setCONVENIO(java.math.BigDecimal CONVENIO) {
        this.CONVENIO = CONVENIO;
    }


    /**
     * Gets the VIGENCIA value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @return VIGENCIA
     */
    public java.util.Date getVIGENCIA() {
        return VIGENCIA;
    }


    /**
     * Sets the VIGENCIA value for this DT_CampAcuPagoCastigo_RES.
     * 
     * @param VIGENCIA
     */
    public void setVIGENCIA(java.util.Date VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_CampAcuPagoCastigo_RES)) return false;
        DT_CampAcuPagoCastigo_RES other = (DT_CampAcuPagoCastigo_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TOTAL==null && other.getTOTAL()==null) || 
             (this.TOTAL!=null &&
              this.TOTAL.equals(other.getTOTAL()))) &&
            ((this.CONVENIO==null && other.getCONVENIO()==null) || 
             (this.CONVENIO!=null &&
              this.CONVENIO.equals(other.getCONVENIO()))) &&
            ((this.VIGENCIA==null && other.getVIGENCIA()==null) || 
             (this.VIGENCIA!=null &&
              this.VIGENCIA.equals(other.getVIGENCIA())));
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
        if (getTOTAL() != null) {
            _hashCode += getTOTAL().hashCode();
        }
        if (getCONVENIO() != null) {
            _hashCode += getCONVENIO().hashCode();
        }
        if (getVIGENCIA() != null) {
            _hashCode += getVIGENCIA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_CampAcuPagoCastigo_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "DT_CampAcuPagoCastigo_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONVENIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONVENIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VIGENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VIGENCIA"));
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
