/**
 * BOLETADefTypeDetalleCdgItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeDetalleCdgItem  implements java.io.Serializable {
    /* Tipo de Codificacion */
    private java.lang.String tpoCodigo;

    /* Valor del Codigo de Item, para la Codificacion Particular */
    private java.lang.String vlrCodigo;

    public BOLETADefTypeDetalleCdgItem() {
    }

    public BOLETADefTypeDetalleCdgItem(
           java.lang.String tpoCodigo,
           java.lang.String vlrCodigo) {
           this.tpoCodigo = tpoCodigo;
           this.vlrCodigo = vlrCodigo;
    }


    /**
     * Gets the tpoCodigo value for this BOLETADefTypeDetalleCdgItem.
     * 
     * @return tpoCodigo   * Tipo de Codificacion
     */
    public java.lang.String getTpoCodigo() {
        return tpoCodigo;
    }


    /**
     * Sets the tpoCodigo value for this BOLETADefTypeDetalleCdgItem.
     * 
     * @param tpoCodigo   * Tipo de Codificacion
     */
    public void setTpoCodigo(java.lang.String tpoCodigo) {
        this.tpoCodigo = tpoCodigo;
    }


    /**
     * Gets the vlrCodigo value for this BOLETADefTypeDetalleCdgItem.
     * 
     * @return vlrCodigo   * Valor del Codigo de Item, para la Codificacion Particular
     */
    public java.lang.String getVlrCodigo() {
        return vlrCodigo;
    }


    /**
     * Sets the vlrCodigo value for this BOLETADefTypeDetalleCdgItem.
     * 
     * @param vlrCodigo   * Valor del Codigo de Item, para la Codificacion Particular
     */
    public void setVlrCodigo(java.lang.String vlrCodigo) {
        this.vlrCodigo = vlrCodigo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeDetalleCdgItem)) return false;
        BOLETADefTypeDetalleCdgItem other = (BOLETADefTypeDetalleCdgItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tpoCodigo==null && other.getTpoCodigo()==null) || 
             (this.tpoCodigo!=null &&
              this.tpoCodigo.equals(other.getTpoCodigo()))) &&
            ((this.vlrCodigo==null && other.getVlrCodigo()==null) || 
             (this.vlrCodigo!=null &&
              this.vlrCodigo.equals(other.getVlrCodigo())));
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
        if (getTpoCodigo() != null) {
            _hashCode += getTpoCodigo().hashCode();
        }
        if (getVlrCodigo() != null) {
            _hashCode += getVlrCodigo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeDetalleCdgItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Detalle>CdgItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TpoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlrCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VlrCodigo"));
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
