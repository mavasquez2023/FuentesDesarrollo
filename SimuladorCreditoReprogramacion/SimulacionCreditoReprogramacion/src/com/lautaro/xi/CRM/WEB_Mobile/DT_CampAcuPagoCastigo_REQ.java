/**
 * DT_CampAcuPagoCastigo_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;


/**
 * 8000003746
 */
public class DT_CampAcuPagoCastigo_REQ  implements java.io.Serializable {
    private java.lang.String RUT;

    private java.lang.String CONTRATO;

    public DT_CampAcuPagoCastigo_REQ() {
    }

    public DT_CampAcuPagoCastigo_REQ(
           java.lang.String RUT,
           java.lang.String CONTRATO) {
           this.RUT = RUT;
           this.CONTRATO = CONTRATO;
    }


    /**
     * Gets the RUT value for this DT_CampAcuPagoCastigo_REQ.
     * 
     * @return RUT
     */
    public java.lang.String getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this DT_CampAcuPagoCastigo_REQ.
     * 
     * @param RUT
     */
    public void setRUT(java.lang.String RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the CONTRATO value for this DT_CampAcuPagoCastigo_REQ.
     * 
     * @return CONTRATO
     */
    public java.lang.String getCONTRATO() {
        return CONTRATO;
    }


    /**
     * Sets the CONTRATO value for this DT_CampAcuPagoCastigo_REQ.
     * 
     * @param CONTRATO
     */
    public void setCONTRATO(java.lang.String CONTRATO) {
        this.CONTRATO = CONTRATO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_CampAcuPagoCastigo_REQ)) return false;
        DT_CampAcuPagoCastigo_REQ other = (DT_CampAcuPagoCastigo_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUT==null && other.getRUT()==null) || 
             (this.RUT!=null &&
              this.RUT.equals(other.getRUT()))) &&
            ((this.CONTRATO==null && other.getCONTRATO()==null) || 
             (this.CONTRATO!=null &&
              this.CONTRATO.equals(other.getCONTRATO())));
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
        if (getRUT() != null) {
            _hashCode += getRUT().hashCode();
        }
        if (getCONTRATO() != null) {
            _hashCode += getCONTRATO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_CampAcuPagoCastigo_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "DT_CampAcuPagoCastigo_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRATO"));
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
