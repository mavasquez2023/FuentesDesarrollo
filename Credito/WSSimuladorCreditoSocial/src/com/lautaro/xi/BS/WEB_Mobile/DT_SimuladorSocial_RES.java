/**
 * DT_SimuladorSocial_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003422 - Simulador de Crédito Social
 */
public class DT_SimuladorSocial_RES  implements java.io.Serializable {
    private com.lautaro.xi.BS.WEB_Mobile.MessageHeader MESSAGE_HEADER;

    private java.lang.String RESULT_CODE;

    private java.math.BigDecimal MONTO_CUOTA;

    private java.math.BigDecimal TASA_INT_MENSUAL;

    private java.math.BigDecimal TASA_INT_ANUAL;

    private java.math.BigDecimal CAE;

    private java.math.BigDecimal IMPUESTO;

    private java.math.BigDecimal GASTO_NOTARIAL;

    private java.math.BigDecimal CTC;

    private java.math.BigDecimal COSTO_MENSUAL_DESGRAVAMEN;

    private java.math.BigDecimal COSTO_TOTAL_DESGRAVAMEN;

    private java.math.BigDecimal COSTO_MENSUAL_CESANTIA;

    private java.math.BigDecimal COSTOS_TOTAL_CESANTIA;

    private java.util.Date FECHA_PRIMER_VENCIMIENTO;

    private com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE[] DETALLE;

    private com.lautaro.xi.BS.Treasury.Log[] LOG;

    public DT_SimuladorSocial_RES() {
    }

    public DT_SimuladorSocial_RES(
           com.lautaro.xi.BS.WEB_Mobile.MessageHeader MESSAGE_HEADER,
           java.lang.String RESULT_CODE,
           java.math.BigDecimal MONTO_CUOTA,
           java.math.BigDecimal TASA_INT_MENSUAL,
           java.math.BigDecimal TASA_INT_ANUAL,
           java.math.BigDecimal CAE,
           java.math.BigDecimal IMPUESTO,
           java.math.BigDecimal GASTO_NOTARIAL,
           java.math.BigDecimal CTC,
           java.math.BigDecimal COSTO_MENSUAL_DESGRAVAMEN,
           java.math.BigDecimal COSTO_TOTAL_DESGRAVAMEN,
           java.math.BigDecimal COSTO_MENSUAL_CESANTIA,
           java.math.BigDecimal COSTOS_TOTAL_CESANTIA,
           java.util.Date FECHA_PRIMER_VENCIMIENTO,
           com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE[] DETALLE,
           com.lautaro.xi.BS.Treasury.Log[] LOG) {
           this.MESSAGE_HEADER = MESSAGE_HEADER;
           this.RESULT_CODE = RESULT_CODE;
           this.MONTO_CUOTA = MONTO_CUOTA;
           this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
           this.TASA_INT_ANUAL = TASA_INT_ANUAL;
           this.CAE = CAE;
           this.IMPUESTO = IMPUESTO;
           this.GASTO_NOTARIAL = GASTO_NOTARIAL;
           this.CTC = CTC;
           this.COSTO_MENSUAL_DESGRAVAMEN = COSTO_MENSUAL_DESGRAVAMEN;
           this.COSTO_TOTAL_DESGRAVAMEN = COSTO_TOTAL_DESGRAVAMEN;
           this.COSTO_MENSUAL_CESANTIA = COSTO_MENSUAL_CESANTIA;
           this.COSTOS_TOTAL_CESANTIA = COSTOS_TOTAL_CESANTIA;
           this.FECHA_PRIMER_VENCIMIENTO = FECHA_PRIMER_VENCIMIENTO;
           this.DETALLE = DETALLE;
           this.LOG = LOG;
    }


    /**
     * Gets the MESSAGE_HEADER value for this DT_SimuladorSocial_RES.
     * 
     * @return MESSAGE_HEADER
     */
    public com.lautaro.xi.BS.WEB_Mobile.MessageHeader getMESSAGE_HEADER() {
        return MESSAGE_HEADER;
    }


    /**
     * Sets the MESSAGE_HEADER value for this DT_SimuladorSocial_RES.
     * 
     * @param MESSAGE_HEADER
     */
    public void setMESSAGE_HEADER(com.lautaro.xi.BS.WEB_Mobile.MessageHeader MESSAGE_HEADER) {
        this.MESSAGE_HEADER = MESSAGE_HEADER;
    }


    /**
     * Gets the RESULT_CODE value for this DT_SimuladorSocial_RES.
     * 
     * @return RESULT_CODE
     */
    public java.lang.String getRESULT_CODE() {
        return RESULT_CODE;
    }


    /**
     * Sets the RESULT_CODE value for this DT_SimuladorSocial_RES.
     * 
     * @param RESULT_CODE
     */
    public void setRESULT_CODE(java.lang.String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }


    /**
     * Gets the MONTO_CUOTA value for this DT_SimuladorSocial_RES.
     * 
     * @return MONTO_CUOTA
     */
    public java.math.BigDecimal getMONTO_CUOTA() {
        return MONTO_CUOTA;
    }


    /**
     * Sets the MONTO_CUOTA value for this DT_SimuladorSocial_RES.
     * 
     * @param MONTO_CUOTA
     */
    public void setMONTO_CUOTA(java.math.BigDecimal MONTO_CUOTA) {
        this.MONTO_CUOTA = MONTO_CUOTA;
    }


    /**
     * Gets the TASA_INT_MENSUAL value for this DT_SimuladorSocial_RES.
     * 
     * @return TASA_INT_MENSUAL
     */
    public java.math.BigDecimal getTASA_INT_MENSUAL() {
        return TASA_INT_MENSUAL;
    }


    /**
     * Sets the TASA_INT_MENSUAL value for this DT_SimuladorSocial_RES.
     * 
     * @param TASA_INT_MENSUAL
     */
    public void setTASA_INT_MENSUAL(java.math.BigDecimal TASA_INT_MENSUAL) {
        this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
    }


    /**
     * Gets the TASA_INT_ANUAL value for this DT_SimuladorSocial_RES.
     * 
     * @return TASA_INT_ANUAL
     */
    public java.math.BigDecimal getTASA_INT_ANUAL() {
        return TASA_INT_ANUAL;
    }


    /**
     * Sets the TASA_INT_ANUAL value for this DT_SimuladorSocial_RES.
     * 
     * @param TASA_INT_ANUAL
     */
    public void setTASA_INT_ANUAL(java.math.BigDecimal TASA_INT_ANUAL) {
        this.TASA_INT_ANUAL = TASA_INT_ANUAL;
    }


    /**
     * Gets the CAE value for this DT_SimuladorSocial_RES.
     * 
     * @return CAE
     */
    public java.math.BigDecimal getCAE() {
        return CAE;
    }


    /**
     * Sets the CAE value for this DT_SimuladorSocial_RES.
     * 
     * @param CAE
     */
    public void setCAE(java.math.BigDecimal CAE) {
        this.CAE = CAE;
    }


    /**
     * Gets the IMPUESTO value for this DT_SimuladorSocial_RES.
     * 
     * @return IMPUESTO
     */
    public java.math.BigDecimal getIMPUESTO() {
        return IMPUESTO;
    }


    /**
     * Sets the IMPUESTO value for this DT_SimuladorSocial_RES.
     * 
     * @param IMPUESTO
     */
    public void setIMPUESTO(java.math.BigDecimal IMPUESTO) {
        this.IMPUESTO = IMPUESTO;
    }


    /**
     * Gets the GASTO_NOTARIAL value for this DT_SimuladorSocial_RES.
     * 
     * @return GASTO_NOTARIAL
     */
    public java.math.BigDecimal getGASTO_NOTARIAL() {
        return GASTO_NOTARIAL;
    }


    /**
     * Sets the GASTO_NOTARIAL value for this DT_SimuladorSocial_RES.
     * 
     * @param GASTO_NOTARIAL
     */
    public void setGASTO_NOTARIAL(java.math.BigDecimal GASTO_NOTARIAL) {
        this.GASTO_NOTARIAL = GASTO_NOTARIAL;
    }


    /**
     * Gets the CTC value for this DT_SimuladorSocial_RES.
     * 
     * @return CTC
     */
    public java.math.BigDecimal getCTC() {
        return CTC;
    }


    /**
     * Sets the CTC value for this DT_SimuladorSocial_RES.
     * 
     * @param CTC
     */
    public void setCTC(java.math.BigDecimal CTC) {
        this.CTC = CTC;
    }


    /**
     * Gets the COSTO_MENSUAL_DESGRAVAMEN value for this DT_SimuladorSocial_RES.
     * 
     * @return COSTO_MENSUAL_DESGRAVAMEN
     */
    public java.math.BigDecimal getCOSTO_MENSUAL_DESGRAVAMEN() {
        return COSTO_MENSUAL_DESGRAVAMEN;
    }


    /**
     * Sets the COSTO_MENSUAL_DESGRAVAMEN value for this DT_SimuladorSocial_RES.
     * 
     * @param COSTO_MENSUAL_DESGRAVAMEN
     */
    public void setCOSTO_MENSUAL_DESGRAVAMEN(java.math.BigDecimal COSTO_MENSUAL_DESGRAVAMEN) {
        this.COSTO_MENSUAL_DESGRAVAMEN = COSTO_MENSUAL_DESGRAVAMEN;
    }


    /**
     * Gets the COSTO_TOTAL_DESGRAVAMEN value for this DT_SimuladorSocial_RES.
     * 
     * @return COSTO_TOTAL_DESGRAVAMEN
     */
    public java.math.BigDecimal getCOSTO_TOTAL_DESGRAVAMEN() {
        return COSTO_TOTAL_DESGRAVAMEN;
    }


    /**
     * Sets the COSTO_TOTAL_DESGRAVAMEN value for this DT_SimuladorSocial_RES.
     * 
     * @param COSTO_TOTAL_DESGRAVAMEN
     */
    public void setCOSTO_TOTAL_DESGRAVAMEN(java.math.BigDecimal COSTO_TOTAL_DESGRAVAMEN) {
        this.COSTO_TOTAL_DESGRAVAMEN = COSTO_TOTAL_DESGRAVAMEN;
    }


    /**
     * Gets the COSTO_MENSUAL_CESANTIA value for this DT_SimuladorSocial_RES.
     * 
     * @return COSTO_MENSUAL_CESANTIA
     */
    public java.math.BigDecimal getCOSTO_MENSUAL_CESANTIA() {
        return COSTO_MENSUAL_CESANTIA;
    }


    /**
     * Sets the COSTO_MENSUAL_CESANTIA value for this DT_SimuladorSocial_RES.
     * 
     * @param COSTO_MENSUAL_CESANTIA
     */
    public void setCOSTO_MENSUAL_CESANTIA(java.math.BigDecimal COSTO_MENSUAL_CESANTIA) {
        this.COSTO_MENSUAL_CESANTIA = COSTO_MENSUAL_CESANTIA;
    }


    /**
     * Gets the COSTOS_TOTAL_CESANTIA value for this DT_SimuladorSocial_RES.
     * 
     * @return COSTOS_TOTAL_CESANTIA
     */
    public java.math.BigDecimal getCOSTOS_TOTAL_CESANTIA() {
        return COSTOS_TOTAL_CESANTIA;
    }


    /**
     * Sets the COSTOS_TOTAL_CESANTIA value for this DT_SimuladorSocial_RES.
     * 
     * @param COSTOS_TOTAL_CESANTIA
     */
    public void setCOSTOS_TOTAL_CESANTIA(java.math.BigDecimal COSTOS_TOTAL_CESANTIA) {
        this.COSTOS_TOTAL_CESANTIA = COSTOS_TOTAL_CESANTIA;
    }


    /**
     * Gets the FECHA_PRIMER_VENCIMIENTO value for this DT_SimuladorSocial_RES.
     * 
     * @return FECHA_PRIMER_VENCIMIENTO
     */
    public java.util.Date getFECHA_PRIMER_VENCIMIENTO() {
        return FECHA_PRIMER_VENCIMIENTO;
    }


    /**
     * Sets the FECHA_PRIMER_VENCIMIENTO value for this DT_SimuladorSocial_RES.
     * 
     * @param FECHA_PRIMER_VENCIMIENTO
     */
    public void setFECHA_PRIMER_VENCIMIENTO(java.util.Date FECHA_PRIMER_VENCIMIENTO) {
        this.FECHA_PRIMER_VENCIMIENTO = FECHA_PRIMER_VENCIMIENTO;
    }


    /**
     * Gets the DETALLE value for this DT_SimuladorSocial_RES.
     * 
     * @return DETALLE
     */
    public com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE[] getDETALLE() {
        return DETALLE;
    }


    /**
     * Sets the DETALLE value for this DT_SimuladorSocial_RES.
     * 
     * @param DETALLE
     */
    public void setDETALLE(com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE[] DETALLE) {
        this.DETALLE = DETALLE;
    }

    public com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE getDETALLE(int i) {
        return this.DETALLE[i];
    }

    public void setDETALLE(int i, com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE _value) {
        this.DETALLE[i] = _value;
    }


    /**
     * Gets the LOG value for this DT_SimuladorSocial_RES.
     * 
     * @return LOG
     */
    public com.lautaro.xi.BS.Treasury.Log[] getLOG() {
        return LOG;
    }


    /**
     * Sets the LOG value for this DT_SimuladorSocial_RES.
     * 
     * @param LOG
     */
    public void setLOG(com.lautaro.xi.BS.Treasury.Log[] LOG) {
        this.LOG = LOG;
    }

    public com.lautaro.xi.BS.Treasury.Log getLOG(int i) {
        return this.LOG[i];
    }

    public void setLOG(int i, com.lautaro.xi.BS.Treasury.Log _value) {
        this.LOG[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimuladorSocial_RES)) return false;
        DT_SimuladorSocial_RES other = (DT_SimuladorSocial_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MESSAGE_HEADER==null && other.getMESSAGE_HEADER()==null) || 
             (this.MESSAGE_HEADER!=null &&
              this.MESSAGE_HEADER.equals(other.getMESSAGE_HEADER()))) &&
            ((this.RESULT_CODE==null && other.getRESULT_CODE()==null) || 
             (this.RESULT_CODE!=null &&
              this.RESULT_CODE.equals(other.getRESULT_CODE()))) &&
            ((this.MONTO_CUOTA==null && other.getMONTO_CUOTA()==null) || 
             (this.MONTO_CUOTA!=null &&
              this.MONTO_CUOTA.equals(other.getMONTO_CUOTA()))) &&
            ((this.TASA_INT_MENSUAL==null && other.getTASA_INT_MENSUAL()==null) || 
             (this.TASA_INT_MENSUAL!=null &&
              this.TASA_INT_MENSUAL.equals(other.getTASA_INT_MENSUAL()))) &&
            ((this.TASA_INT_ANUAL==null && other.getTASA_INT_ANUAL()==null) || 
             (this.TASA_INT_ANUAL!=null &&
              this.TASA_INT_ANUAL.equals(other.getTASA_INT_ANUAL()))) &&
            ((this.CAE==null && other.getCAE()==null) || 
             (this.CAE!=null &&
              this.CAE.equals(other.getCAE()))) &&
            ((this.IMPUESTO==null && other.getIMPUESTO()==null) || 
             (this.IMPUESTO!=null &&
              this.IMPUESTO.equals(other.getIMPUESTO()))) &&
            ((this.GASTO_NOTARIAL==null && other.getGASTO_NOTARIAL()==null) || 
             (this.GASTO_NOTARIAL!=null &&
              this.GASTO_NOTARIAL.equals(other.getGASTO_NOTARIAL()))) &&
            ((this.CTC==null && other.getCTC()==null) || 
             (this.CTC!=null &&
              this.CTC.equals(other.getCTC()))) &&
            ((this.COSTO_MENSUAL_DESGRAVAMEN==null && other.getCOSTO_MENSUAL_DESGRAVAMEN()==null) || 
             (this.COSTO_MENSUAL_DESGRAVAMEN!=null &&
              this.COSTO_MENSUAL_DESGRAVAMEN.equals(other.getCOSTO_MENSUAL_DESGRAVAMEN()))) &&
            ((this.COSTO_TOTAL_DESGRAVAMEN==null && other.getCOSTO_TOTAL_DESGRAVAMEN()==null) || 
             (this.COSTO_TOTAL_DESGRAVAMEN!=null &&
              this.COSTO_TOTAL_DESGRAVAMEN.equals(other.getCOSTO_TOTAL_DESGRAVAMEN()))) &&
            ((this.COSTO_MENSUAL_CESANTIA==null && other.getCOSTO_MENSUAL_CESANTIA()==null) || 
             (this.COSTO_MENSUAL_CESANTIA!=null &&
              this.COSTO_MENSUAL_CESANTIA.equals(other.getCOSTO_MENSUAL_CESANTIA()))) &&
            ((this.COSTOS_TOTAL_CESANTIA==null && other.getCOSTOS_TOTAL_CESANTIA()==null) || 
             (this.COSTOS_TOTAL_CESANTIA!=null &&
              this.COSTOS_TOTAL_CESANTIA.equals(other.getCOSTOS_TOTAL_CESANTIA()))) &&
            ((this.FECHA_PRIMER_VENCIMIENTO==null && other.getFECHA_PRIMER_VENCIMIENTO()==null) || 
             (this.FECHA_PRIMER_VENCIMIENTO!=null &&
              this.FECHA_PRIMER_VENCIMIENTO.equals(other.getFECHA_PRIMER_VENCIMIENTO()))) &&
            ((this.DETALLE==null && other.getDETALLE()==null) || 
             (this.DETALLE!=null &&
              java.util.Arrays.equals(this.DETALLE, other.getDETALLE()))) &&
            ((this.LOG==null && other.getLOG()==null) || 
             (this.LOG!=null &&
              java.util.Arrays.equals(this.LOG, other.getLOG())));
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
        if (getMESSAGE_HEADER() != null) {
            _hashCode += getMESSAGE_HEADER().hashCode();
        }
        if (getRESULT_CODE() != null) {
            _hashCode += getRESULT_CODE().hashCode();
        }
        if (getMONTO_CUOTA() != null) {
            _hashCode += getMONTO_CUOTA().hashCode();
        }
        if (getTASA_INT_MENSUAL() != null) {
            _hashCode += getTASA_INT_MENSUAL().hashCode();
        }
        if (getTASA_INT_ANUAL() != null) {
            _hashCode += getTASA_INT_ANUAL().hashCode();
        }
        if (getCAE() != null) {
            _hashCode += getCAE().hashCode();
        }
        if (getIMPUESTO() != null) {
            _hashCode += getIMPUESTO().hashCode();
        }
        if (getGASTO_NOTARIAL() != null) {
            _hashCode += getGASTO_NOTARIAL().hashCode();
        }
        if (getCTC() != null) {
            _hashCode += getCTC().hashCode();
        }
        if (getCOSTO_MENSUAL_DESGRAVAMEN() != null) {
            _hashCode += getCOSTO_MENSUAL_DESGRAVAMEN().hashCode();
        }
        if (getCOSTO_TOTAL_DESGRAVAMEN() != null) {
            _hashCode += getCOSTO_TOTAL_DESGRAVAMEN().hashCode();
        }
        if (getCOSTO_MENSUAL_CESANTIA() != null) {
            _hashCode += getCOSTO_MENSUAL_CESANTIA().hashCode();
        }
        if (getCOSTOS_TOTAL_CESANTIA() != null) {
            _hashCode += getCOSTOS_TOTAL_CESANTIA().hashCode();
        }
        if (getFECHA_PRIMER_VENCIMIENTO() != null) {
            _hashCode += getFECHA_PRIMER_VENCIMIENTO().hashCode();
        }
        if (getDETALLE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDETALLE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDETALLE(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLOG() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLOG());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLOG(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimuladorSocial_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimuladorSocial_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE_HEADER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESSAGE_HEADER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "MessageHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESULT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESULT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INT_MENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INT_MENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INT_ANUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INT_ANUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPUESTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IMPUESTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GASTO_NOTARIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GASTO_NOTARIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CTC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_MENSUAL_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_MENSUAL_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_TOTAL_DESGRAVAMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_TOTAL_DESGRAVAMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTO_MENSUAL_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_MENSUAL_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COSTOS_TOTAL_CESANTIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTOS_TOTAL_CESANTIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_PRIMER_VENCIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_PRIMER_VENCIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DETALLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DETALLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", ">DT_SimuladorSocial_RES>DETALLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "Log"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
