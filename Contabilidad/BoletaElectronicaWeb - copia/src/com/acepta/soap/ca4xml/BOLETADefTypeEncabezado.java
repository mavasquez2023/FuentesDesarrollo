/**
 * BOLETADefTypeEncabezado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeEncabezado  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc idDoc;

    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor emisor;

    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor receptor;

    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales totales;

    public BOLETADefTypeEncabezado() {
    }

    public BOLETADefTypeEncabezado(
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc idDoc,
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor emisor,
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor receptor,
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales totales) {
           this.idDoc = idDoc;
           this.emisor = emisor;
           this.receptor = receptor;
           this.totales = totales;
    }


    /**
     * Gets the idDoc value for this BOLETADefTypeEncabezado.
     * 
     * @return idDoc
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc getIdDoc() {
        return idDoc;
    }


    /**
     * Sets the idDoc value for this BOLETADefTypeEncabezado.
     * 
     * @param idDoc
     */
    public void setIdDoc(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc idDoc) {
        this.idDoc = idDoc;
    }


    /**
     * Gets the emisor value for this BOLETADefTypeEncabezado.
     * 
     * @return emisor
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor getEmisor() {
        return emisor;
    }


    /**
     * Sets the emisor value for this BOLETADefTypeEncabezado.
     * 
     * @param emisor
     */
    public void setEmisor(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor emisor) {
        this.emisor = emisor;
    }


    /**
     * Gets the receptor value for this BOLETADefTypeEncabezado.
     * 
     * @return receptor
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor getReceptor() {
        return receptor;
    }


    /**
     * Sets the receptor value for this BOLETADefTypeEncabezado.
     * 
     * @param receptor
     */
    public void setReceptor(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor receptor) {
        this.receptor = receptor;
    }


    /**
     * Gets the totales value for this BOLETADefTypeEncabezado.
     * 
     * @return totales
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales getTotales() {
        return totales;
    }


    /**
     * Sets the totales value for this BOLETADefTypeEncabezado.
     * 
     * @param totales
     */
    public void setTotales(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales totales) {
        this.totales = totales;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeEncabezado)) return false;
        BOLETADefTypeEncabezado other = (BOLETADefTypeEncabezado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idDoc==null && other.getIdDoc()==null) || 
             (this.idDoc!=null &&
              this.idDoc.equals(other.getIdDoc()))) &&
            ((this.emisor==null && other.getEmisor()==null) || 
             (this.emisor!=null &&
              this.emisor.equals(other.getEmisor()))) &&
            ((this.receptor==null && other.getReceptor()==null) || 
             (this.receptor!=null &&
              this.receptor.equals(other.getReceptor()))) &&
            ((this.totales==null && other.getTotales()==null) || 
             (this.totales!=null &&
              this.totales.equals(other.getTotales())));
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
        if (getIdDoc() != null) {
            _hashCode += getIdDoc().hashCode();
        }
        if (getEmisor() != null) {
            _hashCode += getEmisor().hashCode();
        }
        if (getReceptor() != null) {
            _hashCode += getReceptor().hashCode();
        }
        if (getTotales() != null) {
            _hashCode += getTotales().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeEncabezado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Encabezado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>IdDoc"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emisor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Emisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>Emisor"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receptor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Receptor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>Receptor"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Totales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>Totales"));
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
