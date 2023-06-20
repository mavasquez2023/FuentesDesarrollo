/**
 * BOLETADefTypeDscRcgGlobal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeDscRcgGlobal  implements java.io.Serializable {
    /* Numero Secuencial de Linea */
    private org.apache.axis.types.PositiveInteger nroLinDR;

    /* Tipo de Movimiento */
    private com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoMov tpoMov;

    /* Descripcion del Descuento o Recargo */
    private java.lang.String glosaDR;

    /* Unidad en que se Expresa el Valor */
    private com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoValor tpoValor;

    /* Valor del Descuento o Recargo */
    private java.math.BigDecimal valorDR;

    /* Indica si el Descuento o Recargo Afecta a Itemes Exentos o
     * No Facturables */
    private com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalIndExeDR indExeDR;

    public BOLETADefTypeDscRcgGlobal() {
    }

    public BOLETADefTypeDscRcgGlobal(
           org.apache.axis.types.PositiveInteger nroLinDR,
           com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoMov tpoMov,
           java.lang.String glosaDR,
           com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoValor tpoValor,
           java.math.BigDecimal valorDR,
           com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalIndExeDR indExeDR) {
           this.nroLinDR = nroLinDR;
           this.tpoMov = tpoMov;
           this.glosaDR = glosaDR;
           this.tpoValor = tpoValor;
           this.valorDR = valorDR;
           this.indExeDR = indExeDR;
    }


    /**
     * Gets the nroLinDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return nroLinDR   * Numero Secuencial de Linea
     */
    public org.apache.axis.types.PositiveInteger getNroLinDR() {
        return nroLinDR;
    }


    /**
     * Sets the nroLinDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param nroLinDR   * Numero Secuencial de Linea
     */
    public void setNroLinDR(org.apache.axis.types.PositiveInteger nroLinDR) {
        this.nroLinDR = nroLinDR;
    }


    /**
     * Gets the tpoMov value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return tpoMov   * Tipo de Movimiento
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoMov getTpoMov() {
        return tpoMov;
    }


    /**
     * Sets the tpoMov value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param tpoMov   * Tipo de Movimiento
     */
    public void setTpoMov(com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoMov tpoMov) {
        this.tpoMov = tpoMov;
    }


    /**
     * Gets the glosaDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return glosaDR   * Descripcion del Descuento o Recargo
     */
    public java.lang.String getGlosaDR() {
        return glosaDR;
    }


    /**
     * Sets the glosaDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param glosaDR   * Descripcion del Descuento o Recargo
     */
    public void setGlosaDR(java.lang.String glosaDR) {
        this.glosaDR = glosaDR;
    }


    /**
     * Gets the tpoValor value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return tpoValor   * Unidad en que se Expresa el Valor
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoValor getTpoValor() {
        return tpoValor;
    }


    /**
     * Sets the tpoValor value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param tpoValor   * Unidad en que se Expresa el Valor
     */
    public void setTpoValor(com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalTpoValor tpoValor) {
        this.tpoValor = tpoValor;
    }


    /**
     * Gets the valorDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return valorDR   * Valor del Descuento o Recargo
     */
    public java.math.BigDecimal getValorDR() {
        return valorDR;
    }


    /**
     * Sets the valorDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param valorDR   * Valor del Descuento o Recargo
     */
    public void setValorDR(java.math.BigDecimal valorDR) {
        this.valorDR = valorDR;
    }


    /**
     * Gets the indExeDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @return indExeDR   * Indica si el Descuento o Recargo Afecta a Itemes Exentos o
     * No Facturables
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalIndExeDR getIndExeDR() {
        return indExeDR;
    }


    /**
     * Sets the indExeDR value for this BOLETADefTypeDscRcgGlobal.
     * 
     * @param indExeDR   * Indica si el Descuento o Recargo Afecta a Itemes Exentos o
     * No Facturables
     */
    public void setIndExeDR(com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobalIndExeDR indExeDR) {
        this.indExeDR = indExeDR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeDscRcgGlobal)) return false;
        BOLETADefTypeDscRcgGlobal other = (BOLETADefTypeDscRcgGlobal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nroLinDR==null && other.getNroLinDR()==null) || 
             (this.nroLinDR!=null &&
              this.nroLinDR.equals(other.getNroLinDR()))) &&
            ((this.tpoMov==null && other.getTpoMov()==null) || 
             (this.tpoMov!=null &&
              this.tpoMov.equals(other.getTpoMov()))) &&
            ((this.glosaDR==null && other.getGlosaDR()==null) || 
             (this.glosaDR!=null &&
              this.glosaDR.equals(other.getGlosaDR()))) &&
            ((this.tpoValor==null && other.getTpoValor()==null) || 
             (this.tpoValor!=null &&
              this.tpoValor.equals(other.getTpoValor()))) &&
            ((this.valorDR==null && other.getValorDR()==null) || 
             (this.valorDR!=null &&
              this.valorDR.equals(other.getValorDR()))) &&
            ((this.indExeDR==null && other.getIndExeDR()==null) || 
             (this.indExeDR!=null &&
              this.indExeDR.equals(other.getIndExeDR())));
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
        if (getNroLinDR() != null) {
            _hashCode += getNroLinDR().hashCode();
        }
        if (getTpoMov() != null) {
            _hashCode += getTpoMov().hashCode();
        }
        if (getGlosaDR() != null) {
            _hashCode += getGlosaDR().hashCode();
        }
        if (getTpoValor() != null) {
            _hashCode += getTpoValor().hashCode();
        }
        if (getValorDR() != null) {
            _hashCode += getValorDR().hashCode();
        }
        if (getIndExeDR() != null) {
            _hashCode += getIndExeDR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeDscRcgGlobal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>DscRcgGlobal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroLinDR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NroLinDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpoMov");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TpoMov"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>DscRcgGlobal>TpoMov"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("glosaDR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GlosaDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tpoValor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TpoValor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>DscRcgGlobal>TpoValor"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorDR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ValorDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indExeDR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IndExeDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>DscRcgGlobal>IndExeDR"));
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
