/**
 * WmImed_SrvBajaBeneficiario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class WmImed_SrvBajaBeneficiario  implements java.io.Serializable {
    private java.lang.String rutBeneficiario;

    private java.util.Date fechaFinVigencia;

    private java.lang.String codigoPlan;

    private java.lang.String rutTitular;

    public WmImed_SrvBajaBeneficiario() {
    }

    public WmImed_SrvBajaBeneficiario(
           java.lang.String rutBeneficiario,
           java.util.Date fechaFinVigencia,
           java.lang.String codigoPlan,
           java.lang.String rutTitular) {
           this.rutBeneficiario = rutBeneficiario;
           this.fechaFinVigencia = fechaFinVigencia;
           this.codigoPlan = codigoPlan;
           this.rutTitular = rutTitular;
    }


    /**
     * Gets the rutBeneficiario value for this WmImed_SrvBajaBeneficiario.
     * 
     * @return rutBeneficiario
     */
    public java.lang.String getRutBeneficiario() {
        return rutBeneficiario;
    }


    /**
     * Sets the rutBeneficiario value for this WmImed_SrvBajaBeneficiario.
     * 
     * @param rutBeneficiario
     */
    public void setRutBeneficiario(java.lang.String rutBeneficiario) {
        this.rutBeneficiario = rutBeneficiario;
    }


    /**
     * Gets the fechaFinVigencia value for this WmImed_SrvBajaBeneficiario.
     * 
     * @return fechaFinVigencia
     */
    public java.util.Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }


    /**
     * Sets the fechaFinVigencia value for this WmImed_SrvBajaBeneficiario.
     * 
     * @param fechaFinVigencia
     */
    public void setFechaFinVigencia(java.util.Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }


    /**
     * Gets the codigoPlan value for this WmImed_SrvBajaBeneficiario.
     * 
     * @return codigoPlan
     */
    public java.lang.String getCodigoPlan() {
        return codigoPlan;
    }


    /**
     * Sets the codigoPlan value for this WmImed_SrvBajaBeneficiario.
     * 
     * @param codigoPlan
     */
    public void setCodigoPlan(java.lang.String codigoPlan) {
        this.codigoPlan = codigoPlan;
    }


    /**
     * Gets the rutTitular value for this WmImed_SrvBajaBeneficiario.
     * 
     * @return rutTitular
     */
    public java.lang.String getRutTitular() {
        return rutTitular;
    }


    /**
     * Sets the rutTitular value for this WmImed_SrvBajaBeneficiario.
     * 
     * @param rutTitular
     */
    public void setRutTitular(java.lang.String rutTitular) {
        this.rutTitular = rutTitular;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WmImed_SrvBajaBeneficiario)) return false;
        WmImed_SrvBajaBeneficiario other = (WmImed_SrvBajaBeneficiario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rutBeneficiario==null && other.getRutBeneficiario()==null) || 
             (this.rutBeneficiario!=null &&
              this.rutBeneficiario.equals(other.getRutBeneficiario()))) &&
            ((this.fechaFinVigencia==null && other.getFechaFinVigencia()==null) || 
             (this.fechaFinVigencia!=null &&
              this.fechaFinVigencia.equals(other.getFechaFinVigencia()))) &&
            ((this.codigoPlan==null && other.getCodigoPlan()==null) || 
             (this.codigoPlan!=null &&
              this.codigoPlan.equals(other.getCodigoPlan()))) &&
            ((this.rutTitular==null && other.getRutTitular()==null) || 
             (this.rutTitular!=null &&
              this.rutTitular.equals(other.getRutTitular())));
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
        if (getRutBeneficiario() != null) {
            _hashCode += getRutBeneficiario().hashCode();
        }
        if (getFechaFinVigencia() != null) {
            _hashCode += getFechaFinVigencia().hashCode();
        }
        if (getCodigoPlan() != null) {
            _hashCode += getCodigoPlan().hashCode();
        }
        if (getRutTitular() != null) {
            _hashCode += getRutTitular().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WmImed_SrvBajaBeneficiario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>wmImed_SrvBaja>Beneficiario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RutBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaFinVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPlan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoPlan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RutTitular"));
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
