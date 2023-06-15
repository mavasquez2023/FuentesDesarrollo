/**
 * DT_SimAcuPago_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003746
 */
public class DT_SimAcuPago_RES  implements java.io.Serializable {
    private java.math.BigDecimal CAPITAL_ADEUDADO;

    private java.math.BigInteger CUOTAS_X_PAGAR;

    private java.math.BigDecimal TASA_INT_MENSUAL;

    private java.math.BigDecimal CAPITAL_COMPROMETIDO;

    private java.math.BigDecimal CAPITAL_A_CONDONAR;

    private java.math.BigDecimal VALOR_CUOTA_MENS;

    private java.math.BigDecimal CAE;

    private java.math.BigDecimal CTC;

    private java.util.Date FECHA_1ER_VTO;

    private com.lautaro.xi.BS.WEB_Mobile.Log LOG;

    public DT_SimAcuPago_RES() {
    }

    public DT_SimAcuPago_RES(
           java.math.BigDecimal CAPITAL_ADEUDADO,
           java.math.BigInteger CUOTAS_X_PAGAR,
           java.math.BigDecimal TASA_INT_MENSUAL,
           java.math.BigDecimal CAPITAL_COMPROMETIDO,
           java.math.BigDecimal CAPITAL_A_CONDONAR,
           java.math.BigDecimal VALOR_CUOTA_MENS,
           java.math.BigDecimal CAE,
           java.math.BigDecimal CTC,
           java.util.Date FECHA_1ER_VTO,
           com.lautaro.xi.BS.WEB_Mobile.Log LOG) {
           this.CAPITAL_ADEUDADO = CAPITAL_ADEUDADO;
           this.CUOTAS_X_PAGAR = CUOTAS_X_PAGAR;
           this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
           this.CAPITAL_COMPROMETIDO = CAPITAL_COMPROMETIDO;
           this.CAPITAL_A_CONDONAR = CAPITAL_A_CONDONAR;
           this.VALOR_CUOTA_MENS = VALOR_CUOTA_MENS;
           this.CAE = CAE;
           this.CTC = CTC;
           this.FECHA_1ER_VTO = FECHA_1ER_VTO;
           this.LOG = LOG;
    }


    /**
     * Gets the CAPITAL_ADEUDADO value for this DT_SimAcuPago_RES.
     * 
     * @return CAPITAL_ADEUDADO
     */
    public java.math.BigDecimal getCAPITAL_ADEUDADO() {
        return CAPITAL_ADEUDADO;
    }


    /**
     * Sets the CAPITAL_ADEUDADO value for this DT_SimAcuPago_RES.
     * 
     * @param CAPITAL_ADEUDADO
     */
    public void setCAPITAL_ADEUDADO(java.math.BigDecimal CAPITAL_ADEUDADO) {
        this.CAPITAL_ADEUDADO = CAPITAL_ADEUDADO;
    }


    /**
     * Gets the CUOTAS_X_PAGAR value for this DT_SimAcuPago_RES.
     * 
     * @return CUOTAS_X_PAGAR
     */
    public java.math.BigInteger getCUOTAS_X_PAGAR() {
        return CUOTAS_X_PAGAR;
    }


    /**
     * Sets the CUOTAS_X_PAGAR value for this DT_SimAcuPago_RES.
     * 
     * @param CUOTAS_X_PAGAR
     */
    public void setCUOTAS_X_PAGAR(java.math.BigInteger CUOTAS_X_PAGAR) {
        this.CUOTAS_X_PAGAR = CUOTAS_X_PAGAR;
    }


    /**
     * Gets the TASA_INT_MENSUAL value for this DT_SimAcuPago_RES.
     * 
     * @return TASA_INT_MENSUAL
     */
    public java.math.BigDecimal getTASA_INT_MENSUAL() {
        return TASA_INT_MENSUAL;
    }


    /**
     * Sets the TASA_INT_MENSUAL value for this DT_SimAcuPago_RES.
     * 
     * @param TASA_INT_MENSUAL
     */
    public void setTASA_INT_MENSUAL(java.math.BigDecimal TASA_INT_MENSUAL) {
        this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
    }


    /**
     * Gets the CAPITAL_COMPROMETIDO value for this DT_SimAcuPago_RES.
     * 
     * @return CAPITAL_COMPROMETIDO
     */
    public java.math.BigDecimal getCAPITAL_COMPROMETIDO() {
        return CAPITAL_COMPROMETIDO;
    }


    /**
     * Sets the CAPITAL_COMPROMETIDO value for this DT_SimAcuPago_RES.
     * 
     * @param CAPITAL_COMPROMETIDO
     */
    public void setCAPITAL_COMPROMETIDO(java.math.BigDecimal CAPITAL_COMPROMETIDO) {
        this.CAPITAL_COMPROMETIDO = CAPITAL_COMPROMETIDO;
    }


    /**
     * Gets the CAPITAL_A_CONDONAR value for this DT_SimAcuPago_RES.
     * 
     * @return CAPITAL_A_CONDONAR
     */
    public java.math.BigDecimal getCAPITAL_A_CONDONAR() {
        return CAPITAL_A_CONDONAR;
    }


    /**
     * Sets the CAPITAL_A_CONDONAR value for this DT_SimAcuPago_RES.
     * 
     * @param CAPITAL_A_CONDONAR
     */
    public void setCAPITAL_A_CONDONAR(java.math.BigDecimal CAPITAL_A_CONDONAR) {
        this.CAPITAL_A_CONDONAR = CAPITAL_A_CONDONAR;
    }


    /**
     * Gets the VALOR_CUOTA_MENS value for this DT_SimAcuPago_RES.
     * 
     * @return VALOR_CUOTA_MENS
     */
    public java.math.BigDecimal getVALOR_CUOTA_MENS() {
        return VALOR_CUOTA_MENS;
    }


    /**
     * Sets the VALOR_CUOTA_MENS value for this DT_SimAcuPago_RES.
     * 
     * @param VALOR_CUOTA_MENS
     */
    public void setVALOR_CUOTA_MENS(java.math.BigDecimal VALOR_CUOTA_MENS) {
        this.VALOR_CUOTA_MENS = VALOR_CUOTA_MENS;
    }


    /**
     * Gets the CAE value for this DT_SimAcuPago_RES.
     * 
     * @return CAE
     */
    public java.math.BigDecimal getCAE() {
        return CAE;
    }


    /**
     * Sets the CAE value for this DT_SimAcuPago_RES.
     * 
     * @param CAE
     */
    public void setCAE(java.math.BigDecimal CAE) {
        this.CAE = CAE;
    }


    /**
     * Gets the CTC value for this DT_SimAcuPago_RES.
     * 
     * @return CTC
     */
    public java.math.BigDecimal getCTC() {
        return CTC;
    }


    /**
     * Sets the CTC value for this DT_SimAcuPago_RES.
     * 
     * @param CTC
     */
    public void setCTC(java.math.BigDecimal CTC) {
        this.CTC = CTC;
    }


    /**
     * Gets the FECHA_1ER_VTO value for this DT_SimAcuPago_RES.
     * 
     * @return FECHA_1ER_VTO
     */
    public java.util.Date getFECHA_1ER_VTO() {
        return FECHA_1ER_VTO;
    }


    /**
     * Sets the FECHA_1ER_VTO value for this DT_SimAcuPago_RES.
     * 
     * @param FECHA_1ER_VTO
     */
    public void setFECHA_1ER_VTO(java.util.Date FECHA_1ER_VTO) {
        this.FECHA_1ER_VTO = FECHA_1ER_VTO;
    }


    /**
     * Gets the LOG value for this DT_SimAcuPago_RES.
     * 
     * @return LOG
     */
    public com.lautaro.xi.BS.WEB_Mobile.Log getLOG() {
        return LOG;
    }


    /**
     * Sets the LOG value for this DT_SimAcuPago_RES.
     * 
     * @param LOG
     */
    public void setLOG(com.lautaro.xi.BS.WEB_Mobile.Log LOG) {
        this.LOG = LOG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimAcuPago_RES)) return false;
        DT_SimAcuPago_RES other = (DT_SimAcuPago_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CAPITAL_ADEUDADO==null && other.getCAPITAL_ADEUDADO()==null) || 
             (this.CAPITAL_ADEUDADO!=null &&
              this.CAPITAL_ADEUDADO.equals(other.getCAPITAL_ADEUDADO()))) &&
            ((this.CUOTAS_X_PAGAR==null && other.getCUOTAS_X_PAGAR()==null) || 
             (this.CUOTAS_X_PAGAR!=null &&
              this.CUOTAS_X_PAGAR.equals(other.getCUOTAS_X_PAGAR()))) &&
            ((this.TASA_INT_MENSUAL==null && other.getTASA_INT_MENSUAL()==null) || 
             (this.TASA_INT_MENSUAL!=null &&
              this.TASA_INT_MENSUAL.equals(other.getTASA_INT_MENSUAL()))) &&
            ((this.CAPITAL_COMPROMETIDO==null && other.getCAPITAL_COMPROMETIDO()==null) || 
             (this.CAPITAL_COMPROMETIDO!=null &&
              this.CAPITAL_COMPROMETIDO.equals(other.getCAPITAL_COMPROMETIDO()))) &&
            ((this.CAPITAL_A_CONDONAR==null && other.getCAPITAL_A_CONDONAR()==null) || 
             (this.CAPITAL_A_CONDONAR!=null &&
              this.CAPITAL_A_CONDONAR.equals(other.getCAPITAL_A_CONDONAR()))) &&
            ((this.VALOR_CUOTA_MENS==null && other.getVALOR_CUOTA_MENS()==null) || 
             (this.VALOR_CUOTA_MENS!=null &&
              this.VALOR_CUOTA_MENS.equals(other.getVALOR_CUOTA_MENS()))) &&
            ((this.CAE==null && other.getCAE()==null) || 
             (this.CAE!=null &&
              this.CAE.equals(other.getCAE()))) &&
            ((this.CTC==null && other.getCTC()==null) || 
             (this.CTC!=null &&
              this.CTC.equals(other.getCTC()))) &&
            ((this.FECHA_1ER_VTO==null && other.getFECHA_1ER_VTO()==null) || 
             (this.FECHA_1ER_VTO!=null &&
              this.FECHA_1ER_VTO.equals(other.getFECHA_1ER_VTO()))) &&
            ((this.LOG==null && other.getLOG()==null) || 
             (this.LOG!=null &&
              this.LOG.equals(other.getLOG())));
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
        if (getCAPITAL_ADEUDADO() != null) {
            _hashCode += getCAPITAL_ADEUDADO().hashCode();
        }
        if (getCUOTAS_X_PAGAR() != null) {
            _hashCode += getCUOTAS_X_PAGAR().hashCode();
        }
        if (getTASA_INT_MENSUAL() != null) {
            _hashCode += getTASA_INT_MENSUAL().hashCode();
        }
        if (getCAPITAL_COMPROMETIDO() != null) {
            _hashCode += getCAPITAL_COMPROMETIDO().hashCode();
        }
        if (getCAPITAL_A_CONDONAR() != null) {
            _hashCode += getCAPITAL_A_CONDONAR().hashCode();
        }
        if (getVALOR_CUOTA_MENS() != null) {
            _hashCode += getVALOR_CUOTA_MENS().hashCode();
        }
        if (getCAE() != null) {
            _hashCode += getCAE().hashCode();
        }
        if (getCTC() != null) {
            _hashCode += getCTC().hashCode();
        }
        if (getFECHA_1ER_VTO() != null) {
            _hashCode += getFECHA_1ER_VTO().hashCode();
        }
        if (getLOG() != null) {
            _hashCode += getLOG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimAcuPago_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimAcuPago_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAPITAL_ADEUDADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAPITAL_ADEUDADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTAS_X_PAGAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTAS_X_PAGAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INT_MENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INT_MENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAPITAL_COMPROMETIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAPITAL_COMPROMETIDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAPITAL_A_CONDONAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAPITAL_A_CONDONAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALOR_CUOTA_MENS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VALOR_CUOTA_MENS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CTC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_1ER_VTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_1ER_VTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "Log"));
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
