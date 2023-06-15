/**
 * DT_SimuladorSocial_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003422 - Simulador de Crédito Social
 */
public class DT_SimuladorSocial_REQ  implements java.io.Serializable {
    private java.math.BigDecimal MONTO;

    private java.lang.String CUOTAS;

    private java.lang.String SUCURSAL;

    private java.lang.String TIPO_AFILIADO;

    private java.lang.String SEGURO_CESANTIA;

    private java.lang.String SEGURO_DESGRAVAMEN;

    private java.lang.String TIPO_SEGURO;

    public DT_SimuladorSocial_REQ() {
    }

    public DT_SimuladorSocial_REQ(
           java.math.BigDecimal MONTO,
           java.lang.String CUOTAS,
           java.lang.String SUCURSAL,
           java.lang.String TIPO_AFILIADO,
           java.lang.String SEGURO_CESANTIA,
           java.lang.String SEGURO_DESGRAVAMEN,
           java.lang.String TIPO_SEGURO) {
           this.MONTO = MONTO;
           this.CUOTAS = CUOTAS;
           this.SUCURSAL = SUCURSAL;
           this.TIPO_AFILIADO = TIPO_AFILIADO;
           this.SEGURO_CESANTIA = SEGURO_CESANTIA;
           this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
           this.TIPO_SEGURO = TIPO_SEGURO;
    }


    /**
     * Gets the MONTO value for this DT_SimuladorSocial_REQ.
     * 
     * @return MONTO
     */
    public java.math.BigDecimal getMONTO() {
        return MONTO;
    }


    /**
     * Sets the MONTO value for this DT_SimuladorSocial_REQ.
     * 
     * @param MONTO
     */
    public void setMONTO(java.math.BigDecimal MONTO) {
        this.MONTO = MONTO;
    }


    /**
     * Gets the CUOTAS value for this DT_SimuladorSocial_REQ.
     * 
     * @return CUOTAS
     */
    public java.lang.String getCUOTAS() {
        return CUOTAS;
    }


    /**
     * Sets the CUOTAS value for this DT_SimuladorSocial_REQ.
     * 
     * @param CUOTAS
     */
    public void setCUOTAS(java.lang.String CUOTAS) {
        this.CUOTAS = CUOTAS;
    }


    /**
     * Gets the SUCURSAL value for this DT_SimuladorSocial_REQ.
     * 
     * @return SUCURSAL
     */
    public java.lang.String getSUCURSAL() {
        return SUCURSAL;
    }


    /**
     * Sets the SUCURSAL value for this DT_SimuladorSocial_REQ.
     * 
     * @param SUCURSAL
     */
    public void setSUCURSAL(java.lang.String SUCURSAL) {
        this.SUCURSAL = SUCURSAL;
    }


    /**
     * Gets the TIPO_AFILIADO value for this DT_SimuladorSocial_REQ.
     * 
     * @return TIPO_AFILIADO
     */
    public java.lang.String getTIPO_AFILIADO() {
        return TIPO_AFILIADO;
    }


    /**
     * Sets the TIPO_AFILIADO value for this DT_SimuladorSocial_REQ.
     * 
     * @param TIPO_AFILIADO
     */
    public void setTIPO_AFILIADO(java.lang.String TIPO_AFILIADO) {
        this.TIPO_AFILIADO = TIPO_AFILIADO;
    }


    /**
     * Gets the SEGURO_CESANTIA value for this DT_SimuladorSocial_REQ.
     * 
     * @return SEGURO_CESANTIA
     */
    public java.lang.String getSEGURO_CESANTIA() {
        return SEGURO_CESANTIA;
    }


    /**
     * Sets the SEGURO_CESANTIA value for this DT_SimuladorSocial_REQ.
     * 
     * @param SEGURO_CESANTIA
     */
    public void setSEGURO_CESANTIA(java.lang.String SEGURO_CESANTIA) {
        this.SEGURO_CESANTIA = SEGURO_CESANTIA;
    }


    /**
     * Gets the SEGURO_DESGRAVAMEN value for this DT_SimuladorSocial_REQ.
     * 
     * @return SEGURO_DESGRAVAMEN
     */
    public java.lang.String getSEGURO_DESGRAVAMEN() {
        return SEGURO_DESGRAVAMEN;
    }


    /**
     * Sets the SEGURO_DESGRAVAMEN value for this DT_SimuladorSocial_REQ.
     * 
     * @param SEGURO_DESGRAVAMEN
     */
    public void setSEGURO_DESGRAVAMEN(java.lang.String SEGURO_DESGRAVAMEN) {
        this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
    }


    /**
     * Gets the TIPO_SEGURO value for this DT_SimuladorSocial_REQ.
     * 
     * @return TIPO_SEGURO
     */
    public java.lang.String getTIPO_SEGURO() {
        return TIPO_SEGURO;
    }


    /**
     * Sets the TIPO_SEGURO value for this DT_SimuladorSocial_REQ.
     * 
     * @param TIPO_SEGURO
     */
    public void setTIPO_SEGURO(java.lang.String TIPO_SEGURO) {
        this.TIPO_SEGURO = TIPO_SEGURO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimuladorSocial_REQ)) return false;
        DT_SimuladorSocial_REQ other = (DT_SimuladorSocial_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MONTO==null && other.getMONTO()==null) || 
             (this.MONTO!=null &&
              this.MONTO.equals(other.getMONTO()))) &&
            ((this.CUOTAS==null && other.getCUOTAS()==null) || 
             (this.CUOTAS!=null &&
              this.CUOTAS.equals(other.getCUOTAS()))) &&
            ((this.SUCURSAL==null && other.getSUCURSAL()==null) || 
             (this.SUCURSAL!=null &&
              this.SUCURSAL.equals(other.getSUCURSAL()))) &&
            ((this.TIPO_AFILIADO==null && other.getTIPO_AFILIADO()==null) || 
             (this.TIPO_AFILIADO!=null &&
              this.TIPO_AFILIADO.equals(other.getTIPO_AFILIADO()))) &&
            ((this.SEGURO_CESANTIA==null && other.getSEGURO_CESANTIA()==null) || 
             (this.SEGURO_CESANTIA!=null &&
              this.SEGURO_CESANTIA.equals(other.getSEGURO_CESANTIA()))) &&
            ((this.SEGURO_DESGRAVAMEN==null && other.getSEGURO_DESGRAVAMEN()==null) || 
             (this.SEGURO_DESGRAVAMEN!=null &&
              this.SEGURO_DESGRAVAMEN.equals(other.getSEGURO_DESGRAVAMEN()))) &&
            ((this.TIPO_SEGURO==null && other.getTIPO_SEGURO()==null) || 
             (this.TIPO_SEGURO!=null &&
              this.TIPO_SEGURO.equals(other.getTIPO_SEGURO())));
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
        if (getMONTO() != null) {
            _hashCode += getMONTO().hashCode();
        }
        if (getCUOTAS() != null) {
            _hashCode += getCUOTAS().hashCode();
        }
        if (getSUCURSAL() != null) {
            _hashCode += getSUCURSAL().hashCode();
        }
        if (getTIPO_AFILIADO() != null) {
            _hashCode += getTIPO_AFILIADO().hashCode();
        }
        if (getSEGURO_CESANTIA() != null) {
            _hashCode += getSEGURO_CESANTIA().hashCode();
        }
        if (getSEGURO_DESGRAVAMEN() != null) {
            _hashCode += getSEGURO_DESGRAVAMEN().hashCode();
        }
        if (getTIPO_SEGURO() != null) {
            _hashCode += getTIPO_SEGURO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimuladorSocial_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimuladorSocial_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUCURSAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUCURSAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_SEGURO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_SEGURO"));
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
