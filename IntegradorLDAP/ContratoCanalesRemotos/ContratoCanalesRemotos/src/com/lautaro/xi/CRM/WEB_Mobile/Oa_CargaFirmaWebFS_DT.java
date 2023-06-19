/**
 * Oa_CargaFirmaWebFS_DT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class Oa_CargaFirmaWebFS_DT  implements java.io.Serializable {
    private java.lang.String NUM_OFERTA;

    private java.lang.String ESTADO_OFERTA;

    public Oa_CargaFirmaWebFS_DT() {
    }

    public Oa_CargaFirmaWebFS_DT(
           java.lang.String NUM_OFERTA,
           java.lang.String ESTADO_OFERTA) {
           this.NUM_OFERTA = NUM_OFERTA;
           this.ESTADO_OFERTA = ESTADO_OFERTA;
    }


    /**
     * Gets the NUM_OFERTA value for this Oa_CargaFirmaWebFS_DT.
     * 
     * @return NUM_OFERTA
     */
    public java.lang.String getNUM_OFERTA() {
        return NUM_OFERTA;
    }


    /**
     * Sets the NUM_OFERTA value for this Oa_CargaFirmaWebFS_DT.
     * 
     * @param NUM_OFERTA
     */
    public void setNUM_OFERTA(java.lang.String NUM_OFERTA) {
        this.NUM_OFERTA = NUM_OFERTA;
    }


    /**
     * Gets the ESTADO_OFERTA value for this Oa_CargaFirmaWebFS_DT.
     * 
     * @return ESTADO_OFERTA
     */
    public java.lang.String getESTADO_OFERTA() {
        return ESTADO_OFERTA;
    }


    /**
     * Sets the ESTADO_OFERTA value for this Oa_CargaFirmaWebFS_DT.
     * 
     * @param ESTADO_OFERTA
     */
    public void setESTADO_OFERTA(java.lang.String ESTADO_OFERTA) {
        this.ESTADO_OFERTA = ESTADO_OFERTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Oa_CargaFirmaWebFS_DT)) return false;
        Oa_CargaFirmaWebFS_DT other = (Oa_CargaFirmaWebFS_DT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUM_OFERTA==null && other.getNUM_OFERTA()==null) || 
             (this.NUM_OFERTA!=null &&
              this.NUM_OFERTA.equals(other.getNUM_OFERTA()))) &&
            ((this.ESTADO_OFERTA==null && other.getESTADO_OFERTA()==null) || 
             (this.ESTADO_OFERTA!=null &&
              this.ESTADO_OFERTA.equals(other.getESTADO_OFERTA())));
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
        if (getNUM_OFERTA() != null) {
            _hashCode += getNUM_OFERTA().hashCode();
        }
        if (getESTADO_OFERTA() != null) {
            _hashCode += getESTADO_OFERTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Oa_CargaFirmaWebFS_DT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "oa_CargaFirmaWebFS_DT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_OFERTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUM_OFERTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO_OFERTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO_OFERTA"));
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
