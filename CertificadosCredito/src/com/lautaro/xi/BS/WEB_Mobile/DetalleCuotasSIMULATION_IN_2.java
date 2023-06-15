/**
 * DetalleCuotasSIMULATION_IN_2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class DetalleCuotasSIMULATION_IN_2  implements java.io.Serializable {
    private java.lang.String INCLUYE_CTA_TRNST;

    private java.util.Date FECHA_PAGO;

    private java.lang.String MONTO_EPO;

    private java.lang.String MONEDA_CONV;

    private java.lang.String MONTO_EPO_CONV;

    public DetalleCuotasSIMULATION_IN_2() {
    }

    public DetalleCuotasSIMULATION_IN_2(
           java.lang.String INCLUYE_CTA_TRNST,
           java.util.Date FECHA_PAGO,
           java.lang.String MONTO_EPO,
           java.lang.String MONEDA_CONV,
           java.lang.String MONTO_EPO_CONV) {
           this.INCLUYE_CTA_TRNST = INCLUYE_CTA_TRNST;
           this.FECHA_PAGO = FECHA_PAGO;
           this.MONTO_EPO = MONTO_EPO;
           this.MONEDA_CONV = MONEDA_CONV;
           this.MONTO_EPO_CONV = MONTO_EPO_CONV;
    }


    /**
     * Gets the INCLUYE_CTA_TRNST value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @return INCLUYE_CTA_TRNST
     */
    public java.lang.String getINCLUYE_CTA_TRNST() {
        return INCLUYE_CTA_TRNST;
    }


    /**
     * Sets the INCLUYE_CTA_TRNST value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @param INCLUYE_CTA_TRNST
     */
    public void setINCLUYE_CTA_TRNST(java.lang.String INCLUYE_CTA_TRNST) {
        this.INCLUYE_CTA_TRNST = INCLUYE_CTA_TRNST;
    }


    /**
     * Gets the FECHA_PAGO value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @return FECHA_PAGO
     */
    public java.util.Date getFECHA_PAGO() {
        return FECHA_PAGO;
    }


    /**
     * Sets the FECHA_PAGO value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @param FECHA_PAGO
     */
    public void setFECHA_PAGO(java.util.Date FECHA_PAGO) {
        this.FECHA_PAGO = FECHA_PAGO;
    }


    /**
     * Gets the MONTO_EPO value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @return MONTO_EPO
     */
    public java.lang.String getMONTO_EPO() {
        return MONTO_EPO;
    }


    /**
     * Sets the MONTO_EPO value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @param MONTO_EPO
     */
    public void setMONTO_EPO(java.lang.String MONTO_EPO) {
        this.MONTO_EPO = MONTO_EPO;
    }


    /**
     * Gets the MONEDA_CONV value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @return MONEDA_CONV
     */
    public java.lang.String getMONEDA_CONV() {
        return MONEDA_CONV;
    }


    /**
     * Sets the MONEDA_CONV value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @param MONEDA_CONV
     */
    public void setMONEDA_CONV(java.lang.String MONEDA_CONV) {
        this.MONEDA_CONV = MONEDA_CONV;
    }


    /**
     * Gets the MONTO_EPO_CONV value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @return MONTO_EPO_CONV
     */
    public java.lang.String getMONTO_EPO_CONV() {
        return MONTO_EPO_CONV;
    }


    /**
     * Sets the MONTO_EPO_CONV value for this DetalleCuotasSIMULATION_IN_2.
     * 
     * @param MONTO_EPO_CONV
     */
    public void setMONTO_EPO_CONV(java.lang.String MONTO_EPO_CONV) {
        this.MONTO_EPO_CONV = MONTO_EPO_CONV;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalleCuotasSIMULATION_IN_2)) return false;
        DetalleCuotasSIMULATION_IN_2 other = (DetalleCuotasSIMULATION_IN_2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INCLUYE_CTA_TRNST==null && other.getINCLUYE_CTA_TRNST()==null) || 
             (this.INCLUYE_CTA_TRNST!=null &&
              this.INCLUYE_CTA_TRNST.equals(other.getINCLUYE_CTA_TRNST()))) &&
            ((this.FECHA_PAGO==null && other.getFECHA_PAGO()==null) || 
             (this.FECHA_PAGO!=null &&
              this.FECHA_PAGO.equals(other.getFECHA_PAGO()))) &&
            ((this.MONTO_EPO==null && other.getMONTO_EPO()==null) || 
             (this.MONTO_EPO!=null &&
              this.MONTO_EPO.equals(other.getMONTO_EPO()))) &&
            ((this.MONEDA_CONV==null && other.getMONEDA_CONV()==null) || 
             (this.MONEDA_CONV!=null &&
              this.MONEDA_CONV.equals(other.getMONEDA_CONV()))) &&
            ((this.MONTO_EPO_CONV==null && other.getMONTO_EPO_CONV()==null) || 
             (this.MONTO_EPO_CONV!=null &&
              this.MONTO_EPO_CONV.equals(other.getMONTO_EPO_CONV())));
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
        if (getINCLUYE_CTA_TRNST() != null) {
            _hashCode += getINCLUYE_CTA_TRNST().hashCode();
        }
        if (getFECHA_PAGO() != null) {
            _hashCode += getFECHA_PAGO().hashCode();
        }
        if (getMONTO_EPO() != null) {
            _hashCode += getMONTO_EPO().hashCode();
        }
        if (getMONEDA_CONV() != null) {
            _hashCode += getMONEDA_CONV().hashCode();
        }
        if (getMONTO_EPO_CONV() != null) {
            _hashCode += getMONTO_EPO_CONV().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalleCuotasSIMULATION_IN_2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DetalleCuotasSIMULATION_IN_2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INCLUYE_CTA_TRNST");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INCLUYE_CTA_TRNST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_PAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_EPO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_EPO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONEDA_CONV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONEDA_CONV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_EPO_CONV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_EPO_CONV"));
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
