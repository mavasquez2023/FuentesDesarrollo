/**
 * DT_SimAcuPago_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003746
 */
public class DT_SimAcuPago_REQ  implements java.io.Serializable {
    private java.lang.String RUT;

    private java.lang.String CONTRATO;

    private java.lang.String TIPO_AFILIADO;

    private java.math.BigInteger CUOTAS;

    private java.math.BigDecimal MONTO_ABONO_INMEDIATO;

    private java.math.BigDecimal PORC_COND_CAPITAL;

    private java.lang.String PRODUCTO;

    public DT_SimAcuPago_REQ() {
    }

    public DT_SimAcuPago_REQ(
           java.lang.String RUT,
           java.lang.String CONTRATO,
           java.lang.String TIPO_AFILIADO,
           java.math.BigInteger CUOTAS,
           java.math.BigDecimal MONTO_ABONO_INMEDIATO,
           java.math.BigDecimal PORC_COND_CAPITAL,
           java.lang.String PRODUCTO) {
           this.RUT = RUT;
           this.CONTRATO = CONTRATO;
           this.TIPO_AFILIADO = TIPO_AFILIADO;
           this.CUOTAS = CUOTAS;
           this.MONTO_ABONO_INMEDIATO = MONTO_ABONO_INMEDIATO;
           this.PORC_COND_CAPITAL = PORC_COND_CAPITAL;
           this.PRODUCTO = PRODUCTO;
    }


    /**
     * Gets the RUT value for this DT_SimAcuPago_REQ.
     * 
     * @return RUT
     */
    public java.lang.String getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this DT_SimAcuPago_REQ.
     * 
     * @param RUT
     */
    public void setRUT(java.lang.String RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the CONTRATO value for this DT_SimAcuPago_REQ.
     * 
     * @return CONTRATO
     */
    public java.lang.String getCONTRATO() {
        return CONTRATO;
    }


    /**
     * Sets the CONTRATO value for this DT_SimAcuPago_REQ.
     * 
     * @param CONTRATO
     */
    public void setCONTRATO(java.lang.String CONTRATO) {
        this.CONTRATO = CONTRATO;
    }


    /**
     * Gets the TIPO_AFILIADO value for this DT_SimAcuPago_REQ.
     * 
     * @return TIPO_AFILIADO
     */
    public java.lang.String getTIPO_AFILIADO() {
        return TIPO_AFILIADO;
    }


    /**
     * Sets the TIPO_AFILIADO value for this DT_SimAcuPago_REQ.
     * 
     * @param TIPO_AFILIADO
     */
    public void setTIPO_AFILIADO(java.lang.String TIPO_AFILIADO) {
        this.TIPO_AFILIADO = TIPO_AFILIADO;
    }


    /**
     * Gets the CUOTAS value for this DT_SimAcuPago_REQ.
     * 
     * @return CUOTAS
     */
    public java.math.BigInteger getCUOTAS() {
        return CUOTAS;
    }


    /**
     * Sets the CUOTAS value for this DT_SimAcuPago_REQ.
     * 
     * @param CUOTAS
     */
    public void setCUOTAS(java.math.BigInteger CUOTAS) {
        this.CUOTAS = CUOTAS;
    }


    /**
     * Gets the MONTO_ABONO_INMEDIATO value for this DT_SimAcuPago_REQ.
     * 
     * @return MONTO_ABONO_INMEDIATO
     */
    public java.math.BigDecimal getMONTO_ABONO_INMEDIATO() {
        return MONTO_ABONO_INMEDIATO;
    }


    /**
     * Sets the MONTO_ABONO_INMEDIATO value for this DT_SimAcuPago_REQ.
     * 
     * @param MONTO_ABONO_INMEDIATO
     */
    public void setMONTO_ABONO_INMEDIATO(java.math.BigDecimal MONTO_ABONO_INMEDIATO) {
        this.MONTO_ABONO_INMEDIATO = MONTO_ABONO_INMEDIATO;
    }


    /**
     * Gets the PORC_COND_CAPITAL value for this DT_SimAcuPago_REQ.
     * 
     * @return PORC_COND_CAPITAL
     */
    public java.math.BigDecimal getPORC_COND_CAPITAL() {
        return PORC_COND_CAPITAL;
    }


    /**
     * Sets the PORC_COND_CAPITAL value for this DT_SimAcuPago_REQ.
     * 
     * @param PORC_COND_CAPITAL
     */
    public void setPORC_COND_CAPITAL(java.math.BigDecimal PORC_COND_CAPITAL) {
        this.PORC_COND_CAPITAL = PORC_COND_CAPITAL;
    }


    /**
     * Gets the PRODUCTO value for this DT_SimAcuPago_REQ.
     * 
     * @return PRODUCTO
     */
    public java.lang.String getPRODUCTO() {
        return PRODUCTO;
    }


    /**
     * Sets the PRODUCTO value for this DT_SimAcuPago_REQ.
     * 
     * @param PRODUCTO
     */
    public void setPRODUCTO(java.lang.String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimAcuPago_REQ)) return false;
        DT_SimAcuPago_REQ other = (DT_SimAcuPago_REQ) obj;
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
              this.CONTRATO.equals(other.getCONTRATO()))) &&
            ((this.TIPO_AFILIADO==null && other.getTIPO_AFILIADO()==null) || 
             (this.TIPO_AFILIADO!=null &&
              this.TIPO_AFILIADO.equals(other.getTIPO_AFILIADO()))) &&
            ((this.CUOTAS==null && other.getCUOTAS()==null) || 
             (this.CUOTAS!=null &&
              this.CUOTAS.equals(other.getCUOTAS()))) &&
            ((this.MONTO_ABONO_INMEDIATO==null && other.getMONTO_ABONO_INMEDIATO()==null) || 
             (this.MONTO_ABONO_INMEDIATO!=null &&
              this.MONTO_ABONO_INMEDIATO.equals(other.getMONTO_ABONO_INMEDIATO()))) &&
            ((this.PORC_COND_CAPITAL==null && other.getPORC_COND_CAPITAL()==null) || 
             (this.PORC_COND_CAPITAL!=null &&
              this.PORC_COND_CAPITAL.equals(other.getPORC_COND_CAPITAL()))) &&
            ((this.PRODUCTO==null && other.getPRODUCTO()==null) || 
             (this.PRODUCTO!=null &&
              this.PRODUCTO.equals(other.getPRODUCTO())));
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
        if (getTIPO_AFILIADO() != null) {
            _hashCode += getTIPO_AFILIADO().hashCode();
        }
        if (getCUOTAS() != null) {
            _hashCode += getCUOTAS().hashCode();
        }
        if (getMONTO_ABONO_INMEDIATO() != null) {
            _hashCode += getMONTO_ABONO_INMEDIATO().hashCode();
        }
        if (getPORC_COND_CAPITAL() != null) {
            _hashCode += getPORC_COND_CAPITAL().hashCode();
        }
        if (getPRODUCTO() != null) {
            _hashCode += getPRODUCTO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimAcuPago_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimAcuPago_REQ"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_ABONO_INMEDIATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_ABONO_INMEDIATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORC_COND_CAPITAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORC_COND_CAPITAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCTO"));
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
