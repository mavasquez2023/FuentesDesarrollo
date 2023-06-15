/**
 * EarlyPayoffContract2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class EarlyPayoffContract2  implements java.io.Serializable {
    private java.lang.String CONTRACT_ID_I;

    private java.lang.String TIPO_PREPAGO;

    private java.util.Date FECHA;

    public EarlyPayoffContract2() {
    }

    public EarlyPayoffContract2(
           java.lang.String CONTRACT_ID_I,
           java.lang.String TIPO_PREPAGO,
           java.util.Date FECHA) {
           this.CONTRACT_ID_I = CONTRACT_ID_I;
           this.TIPO_PREPAGO = TIPO_PREPAGO;
           this.FECHA = FECHA;
    }


    /**
     * Gets the CONTRACT_ID_I value for this EarlyPayoffContract2.
     * 
     * @return CONTRACT_ID_I
     */
    public java.lang.String getCONTRACT_ID_I() {
        return CONTRACT_ID_I;
    }


    /**
     * Sets the CONTRACT_ID_I value for this EarlyPayoffContract2.
     * 
     * @param CONTRACT_ID_I
     */
    public void setCONTRACT_ID_I(java.lang.String CONTRACT_ID_I) {
        this.CONTRACT_ID_I = CONTRACT_ID_I;
    }


    /**
     * Gets the TIPO_PREPAGO value for this EarlyPayoffContract2.
     * 
     * @return TIPO_PREPAGO
     */
    public java.lang.String getTIPO_PREPAGO() {
        return TIPO_PREPAGO;
    }


    /**
     * Sets the TIPO_PREPAGO value for this EarlyPayoffContract2.
     * 
     * @param TIPO_PREPAGO
     */
    public void setTIPO_PREPAGO(java.lang.String TIPO_PREPAGO) {
        this.TIPO_PREPAGO = TIPO_PREPAGO;
    }


    /**
     * Gets the FECHA value for this EarlyPayoffContract2.
     * 
     * @return FECHA
     */
    public java.util.Date getFECHA() {
        return FECHA;
    }


    /**
     * Sets the FECHA value for this EarlyPayoffContract2.
     * 
     * @param FECHA
     */
    public void setFECHA(java.util.Date FECHA) {
        this.FECHA = FECHA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EarlyPayoffContract2)) return false;
        EarlyPayoffContract2 other = (EarlyPayoffContract2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CONTRACT_ID_I==null && other.getCONTRACT_ID_I()==null) || 
             (this.CONTRACT_ID_I!=null &&
              this.CONTRACT_ID_I.equals(other.getCONTRACT_ID_I()))) &&
            ((this.TIPO_PREPAGO==null && other.getTIPO_PREPAGO()==null) || 
             (this.TIPO_PREPAGO!=null &&
              this.TIPO_PREPAGO.equals(other.getTIPO_PREPAGO()))) &&
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
        if (getCONTRACT_ID_I() != null) {
            _hashCode += getCONTRACT_ID_I().hashCode();
        }
        if (getTIPO_PREPAGO() != null) {
            _hashCode += getTIPO_PREPAGO().hashCode();
        }
        if (getFECHA() != null) {
            _hashCode += getFECHA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EarlyPayoffContract2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "EarlyPayoffContract2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT_ID_I");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRACT_ID_I"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_PREPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_PREPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
