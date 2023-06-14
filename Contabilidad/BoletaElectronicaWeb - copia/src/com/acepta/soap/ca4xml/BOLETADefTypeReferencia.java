/**
 * BOLETADefTypeReferencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeReferencia  implements java.io.Serializable {
    private org.apache.axis.types.PositiveInteger nroLinRef;

    private java.lang.String codRef;

    private java.lang.String razonRef;

    private java.lang.String codVndor;

    private java.lang.String codCaja;

    public BOLETADefTypeReferencia() {
    }

    public BOLETADefTypeReferencia(
           org.apache.axis.types.PositiveInteger nroLinRef,
           java.lang.String codRef,
           java.lang.String razonRef,
           java.lang.String codVndor,
           java.lang.String codCaja) {
           this.nroLinRef = nroLinRef;
           this.codRef = codRef;
           this.razonRef = razonRef;
           this.codVndor = codVndor;
           this.codCaja = codCaja;
    }


    /**
     * Gets the nroLinRef value for this BOLETADefTypeReferencia.
     * 
     * @return nroLinRef
     */
    public org.apache.axis.types.PositiveInteger getNroLinRef() {
        return nroLinRef;
    }


    /**
     * Sets the nroLinRef value for this BOLETADefTypeReferencia.
     * 
     * @param nroLinRef
     */
    public void setNroLinRef(org.apache.axis.types.PositiveInteger nroLinRef) {
        this.nroLinRef = nroLinRef;
    }


    /**
     * Gets the codRef value for this BOLETADefTypeReferencia.
     * 
     * @return codRef
     */
    public java.lang.String getCodRef() {
        return codRef;
    }


    /**
     * Sets the codRef value for this BOLETADefTypeReferencia.
     * 
     * @param codRef
     */
    public void setCodRef(java.lang.String codRef) {
        this.codRef = codRef;
    }


    /**
     * Gets the razonRef value for this BOLETADefTypeReferencia.
     * 
     * @return razonRef
     */
    public java.lang.String getRazonRef() {
        return razonRef;
    }


    /**
     * Sets the razonRef value for this BOLETADefTypeReferencia.
     * 
     * @param razonRef
     */
    public void setRazonRef(java.lang.String razonRef) {
        this.razonRef = razonRef;
    }


    /**
     * Gets the codVndor value for this BOLETADefTypeReferencia.
     * 
     * @return codVndor
     */
    public java.lang.String getCodVndor() {
        return codVndor;
    }


    /**
     * Sets the codVndor value for this BOLETADefTypeReferencia.
     * 
     * @param codVndor
     */
    public void setCodVndor(java.lang.String codVndor) {
        this.codVndor = codVndor;
    }


    /**
     * Gets the codCaja value for this BOLETADefTypeReferencia.
     * 
     * @return codCaja
     */
    public java.lang.String getCodCaja() {
        return codCaja;
    }


    /**
     * Sets the codCaja value for this BOLETADefTypeReferencia.
     * 
     * @param codCaja
     */
    public void setCodCaja(java.lang.String codCaja) {
        this.codCaja = codCaja;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeReferencia)) return false;
        BOLETADefTypeReferencia other = (BOLETADefTypeReferencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nroLinRef==null && other.getNroLinRef()==null) || 
             (this.nroLinRef!=null &&
              this.nroLinRef.equals(other.getNroLinRef()))) &&
            ((this.codRef==null && other.getCodRef()==null) || 
             (this.codRef!=null &&
              this.codRef.equals(other.getCodRef()))) &&
            ((this.razonRef==null && other.getRazonRef()==null) || 
             (this.razonRef!=null &&
              this.razonRef.equals(other.getRazonRef()))) &&
            ((this.codVndor==null && other.getCodVndor()==null) || 
             (this.codVndor!=null &&
              this.codVndor.equals(other.getCodVndor()))) &&
            ((this.codCaja==null && other.getCodCaja()==null) || 
             (this.codCaja!=null &&
              this.codCaja.equals(other.getCodCaja())));
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
        if (getNroLinRef() != null) {
            _hashCode += getNroLinRef().hashCode();
        }
        if (getCodRef() != null) {
            _hashCode += getCodRef().hashCode();
        }
        if (getRazonRef() != null) {
            _hashCode += getRazonRef().hashCode();
        }
        if (getCodVndor() != null) {
            _hashCode += getCodVndor().hashCode();
        }
        if (getCodCaja() != null) {
            _hashCode += getCodCaja().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeReferencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Referencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroLinRef");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NroLinRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codRef");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razonRef");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RazonRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codVndor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodVndor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCaja");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodCaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
