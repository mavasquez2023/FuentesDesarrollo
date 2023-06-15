/**
 * DT_SimuladorRepro_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003038 - Simulador web Repro
 */
public class DT_SimuladorRepro_RES  implements java.io.Serializable {
    private java.math.BigDecimal MONTO_ADEUDADO;

    private java.math.BigDecimal MONTO_CUOTA;

    private java.math.BigDecimal CAE;

    private java.math.BigDecimal COSTO_TOTAL;

    private java.math.BigDecimal GRV_CONDONADO;

    private java.math.BigDecimal GC_CONDONADO;

    private java.math.BigDecimal HONO_CONDONADO;

    private java.math.BigDecimal INT_CONDONADO;

    private java.util.Date FECHA_PRIMER_VTO;

    private java.math.BigDecimal SEG_CES;

    private java.math.BigDecimal SEG_DESG;

    /* 8000003746 */
    private java.math.BigDecimal TASA_INT_MENSUAL;

    /* 8000003746 */
    private java.math.BigDecimal PORC_MAX_ENDEUD;

    /* 8000003746 */
    private java.math.BigDecimal PORC_ENDEUD_SIM;

    private com.lautaro.xi.BS.Treasury.Log LOG;

    public DT_SimuladorRepro_RES() {
    }

    public DT_SimuladorRepro_RES(
           java.math.BigDecimal MONTO_ADEUDADO,
           java.math.BigDecimal MONTO_CUOTA,
           java.math.BigDecimal CAE,
           java.math.BigDecimal COSTO_TOTAL,
           java.math.BigDecimal GRV_CONDONADO,
           java.math.BigDecimal GC_CONDONADO,
           java.math.BigDecimal HONO_CONDONADO,
           java.math.BigDecimal INT_CONDONADO,
           java.util.Date FECHA_PRIMER_VTO,
           java.math.BigDecimal SEG_CES,
           java.math.BigDecimal SEG_DESG,
           java.math.BigDecimal TASA_INT_MENSUAL,
           java.math.BigDecimal PORC_MAX_ENDEUD,
           java.math.BigDecimal PORC_ENDEUD_SIM,
           com.lautaro.xi.BS.Treasury.Log LOG) {
           this.MONTO_ADEUDADO = MONTO_ADEUDADO;
           this.MONTO_CUOTA = MONTO_CUOTA;
           this.CAE = CAE;
           this.COSTO_TOTAL = COSTO_TOTAL;
           this.GRV_CONDONADO = GRV_CONDONADO;
           this.GC_CONDONADO = GC_CONDONADO;
           this.HONO_CONDONADO = HONO_CONDONADO;
           this.INT_CONDONADO = INT_CONDONADO;
           this.FECHA_PRIMER_VTO = FECHA_PRIMER_VTO;
           this.SEG_CES = SEG_CES;
           this.SEG_DESG = SEG_DESG;
           this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
           this.PORC_MAX_ENDEUD = PORC_MAX_ENDEUD;
           this.PORC_ENDEUD_SIM = PORC_ENDEUD_SIM;
           this.LOG = LOG;
    }


    /**
     * Gets the MONTO_ADEUDADO value for this DT_SimuladorRepro_RES.
     * 
     * @return MONTO_ADEUDADO
     */
    public java.math.BigDecimal getMONTO_ADEUDADO() {
        return MONTO_ADEUDADO;
    }


    /**
     * Sets the MONTO_ADEUDADO value for this DT_SimuladorRepro_RES.
     * 
     * @param MONTO_ADEUDADO
     */
    public void setMONTO_ADEUDADO(java.math.BigDecimal MONTO_ADEUDADO) {
        this.MONTO_ADEUDADO = MONTO_ADEUDADO;
    }


    /**
     * Gets the MONTO_CUOTA value for this DT_SimuladorRepro_RES.
     * 
     * @return MONTO_CUOTA
     */
    public java.math.BigDecimal getMONTO_CUOTA() {
        return MONTO_CUOTA;
    }


    /**
     * Sets the MONTO_CUOTA value for this DT_SimuladorRepro_RES.
     * 
     * @param MONTO_CUOTA
     */
    public void setMONTO_CUOTA(java.math.BigDecimal MONTO_CUOTA) {
        this.MONTO_CUOTA = MONTO_CUOTA;
    }


    /**
     * Gets the CAE value for this DT_SimuladorRepro_RES.
     * 
     * @return CAE
     */
    public java.math.BigDecimal getCAE() {
        return CAE;
    }


    /**
     * Sets the CAE value for this DT_SimuladorRepro_RES.
     * 
     * @param CAE
     */
    public void setCAE(java.math.BigDecimal CAE) {
        this.CAE = CAE;
    }


    /**
     * Gets the COSTO_TOTAL value for this DT_SimuladorRepro_RES.
     * 
     * @return COSTO_TOTAL
     */
    public java.math.BigDecimal getCOSTO_TOTAL() {
        return COSTO_TOTAL;
    }


    /**
     * Sets the COSTO_TOTAL value for this DT_SimuladorRepro_RES.
     * 
     * @param COSTO_TOTAL
     */
    public void setCOSTO_TOTAL(java.math.BigDecimal COSTO_TOTAL) {
        this.COSTO_TOTAL = COSTO_TOTAL;
    }


    /**
     * Gets the GRV_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @return GRV_CONDONADO
     */
    public java.math.BigDecimal getGRV_CONDONADO() {
        return GRV_CONDONADO;
    }


    /**
     * Sets the GRV_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @param GRV_CONDONADO
     */
    public void setGRV_CONDONADO(java.math.BigDecimal GRV_CONDONADO) {
        this.GRV_CONDONADO = GRV_CONDONADO;
    }


    /**
     * Gets the GC_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @return GC_CONDONADO
     */
    public java.math.BigDecimal getGC_CONDONADO() {
        return GC_CONDONADO;
    }


    /**
     * Sets the GC_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @param GC_CONDONADO
     */
    public void setGC_CONDONADO(java.math.BigDecimal GC_CONDONADO) {
        this.GC_CONDONADO = GC_CONDONADO;
    }


    /**
     * Gets the HONO_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @return HONO_CONDONADO
     */
    public java.math.BigDecimal getHONO_CONDONADO() {
        return HONO_CONDONADO;
    }


    /**
     * Sets the HONO_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @param HONO_CONDONADO
     */
    public void setHONO_CONDONADO(java.math.BigDecimal HONO_CONDONADO) {
        this.HONO_CONDONADO = HONO_CONDONADO;
    }


    /**
     * Gets the INT_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @return INT_CONDONADO
     */
    public java.math.BigDecimal getINT_CONDONADO() {
        return INT_CONDONADO;
    }


    /**
     * Sets the INT_CONDONADO value for this DT_SimuladorRepro_RES.
     * 
     * @param INT_CONDONADO
     */
    public void setINT_CONDONADO(java.math.BigDecimal INT_CONDONADO) {
        this.INT_CONDONADO = INT_CONDONADO;
    }


    /**
     * Gets the FECHA_PRIMER_VTO value for this DT_SimuladorRepro_RES.
     * 
     * @return FECHA_PRIMER_VTO
     */
    public java.util.Date getFECHA_PRIMER_VTO() {
        return FECHA_PRIMER_VTO;
    }


    /**
     * Sets the FECHA_PRIMER_VTO value for this DT_SimuladorRepro_RES.
     * 
     * @param FECHA_PRIMER_VTO
     */
    public void setFECHA_PRIMER_VTO(java.util.Date FECHA_PRIMER_VTO) {
        this.FECHA_PRIMER_VTO = FECHA_PRIMER_VTO;
    }


    /**
     * Gets the SEG_CES value for this DT_SimuladorRepro_RES.
     * 
     * @return SEG_CES
     */
    public java.math.BigDecimal getSEG_CES() {
        return SEG_CES;
    }


    /**
     * Sets the SEG_CES value for this DT_SimuladorRepro_RES.
     * 
     * @param SEG_CES
     */
    public void setSEG_CES(java.math.BigDecimal SEG_CES) {
        this.SEG_CES = SEG_CES;
    }


    /**
     * Gets the SEG_DESG value for this DT_SimuladorRepro_RES.
     * 
     * @return SEG_DESG
     */
    public java.math.BigDecimal getSEG_DESG() {
        return SEG_DESG;
    }


    /**
     * Sets the SEG_DESG value for this DT_SimuladorRepro_RES.
     * 
     * @param SEG_DESG
     */
    public void setSEG_DESG(java.math.BigDecimal SEG_DESG) {
        this.SEG_DESG = SEG_DESG;
    }


    /**
     * Gets the TASA_INT_MENSUAL value for this DT_SimuladorRepro_RES.
     * 
     * @return TASA_INT_MENSUAL   * 8000003746
     */
    public java.math.BigDecimal getTASA_INT_MENSUAL() {
        return TASA_INT_MENSUAL;
    }


    /**
     * Sets the TASA_INT_MENSUAL value for this DT_SimuladorRepro_RES.
     * 
     * @param TASA_INT_MENSUAL   * 8000003746
     */
    public void setTASA_INT_MENSUAL(java.math.BigDecimal TASA_INT_MENSUAL) {
        this.TASA_INT_MENSUAL = TASA_INT_MENSUAL;
    }


    /**
     * Gets the PORC_MAX_ENDEUD value for this DT_SimuladorRepro_RES.
     * 
     * @return PORC_MAX_ENDEUD   * 8000003746
     */
    public java.math.BigDecimal getPORC_MAX_ENDEUD() {
        return PORC_MAX_ENDEUD;
    }


    /**
     * Sets the PORC_MAX_ENDEUD value for this DT_SimuladorRepro_RES.
     * 
     * @param PORC_MAX_ENDEUD   * 8000003746
     */
    public void setPORC_MAX_ENDEUD(java.math.BigDecimal PORC_MAX_ENDEUD) {
        this.PORC_MAX_ENDEUD = PORC_MAX_ENDEUD;
    }


    /**
     * Gets the PORC_ENDEUD_SIM value for this DT_SimuladorRepro_RES.
     * 
     * @return PORC_ENDEUD_SIM   * 8000003746
     */
    public java.math.BigDecimal getPORC_ENDEUD_SIM() {
        return PORC_ENDEUD_SIM;
    }


    /**
     * Sets the PORC_ENDEUD_SIM value for this DT_SimuladorRepro_RES.
     * 
     * @param PORC_ENDEUD_SIM   * 8000003746
     */
    public void setPORC_ENDEUD_SIM(java.math.BigDecimal PORC_ENDEUD_SIM) {
        this.PORC_ENDEUD_SIM = PORC_ENDEUD_SIM;
    }


    /**
     * Gets the LOG value for this DT_SimuladorRepro_RES.
     * 
     * @return LOG
     */
    public com.lautaro.xi.BS.Treasury.Log getLOG() {
        return LOG;
    }


    /**
     * Sets the LOG value for this DT_SimuladorRepro_RES.
     * 
     * @param LOG
     */
    public void setLOG(com.lautaro.xi.BS.Treasury.Log LOG) {
        this.LOG = LOG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimuladorRepro_RES)) return false;
        DT_SimuladorRepro_RES other = (DT_SimuladorRepro_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MONTO_ADEUDADO==null && other.getMONTO_ADEUDADO()==null) || 
             (this.MONTO_ADEUDADO!=null &&
              this.MONTO_ADEUDADO.equals(other.getMONTO_ADEUDADO()))) &&
            ((this.MONTO_CUOTA==null && other.getMONTO_CUOTA()==null) || 
             (this.MONTO_CUOTA!=null &&
              this.MONTO_CUOTA.equals(other.getMONTO_CUOTA()))) &&
            ((this.CAE==null && other.getCAE()==null) || 
             (this.CAE!=null &&
              this.CAE.equals(other.getCAE()))) &&
            ((this.COSTO_TOTAL==null && other.getCOSTO_TOTAL()==null) || 
             (this.COSTO_TOTAL!=null &&
              this.COSTO_TOTAL.equals(other.getCOSTO_TOTAL()))) &&
            ((this.GRV_CONDONADO==null && other.getGRV_CONDONADO()==null) || 
             (this.GRV_CONDONADO!=null &&
              this.GRV_CONDONADO.equals(other.getGRV_CONDONADO()))) &&
            ((this.GC_CONDONADO==null && other.getGC_CONDONADO()==null) || 
             (this.GC_CONDONADO!=null &&
              this.GC_CONDONADO.equals(other.getGC_CONDONADO()))) &&
            ((this.HONO_CONDONADO==null && other.getHONO_CONDONADO()==null) || 
             (this.HONO_CONDONADO!=null &&
              this.HONO_CONDONADO.equals(other.getHONO_CONDONADO()))) &&
            ((this.INT_CONDONADO==null && other.getINT_CONDONADO()==null) || 
             (this.INT_CONDONADO!=null &&
              this.INT_CONDONADO.equals(other.getINT_CONDONADO()))) &&
            ((this.FECHA_PRIMER_VTO==null && other.getFECHA_PRIMER_VTO()==null) || 
             (this.FECHA_PRIMER_VTO!=null &&
              this.FECHA_PRIMER_VTO.equals(other.getFECHA_PRIMER_VTO()))) &&
            ((this.SEG_CES==null && other.getSEG_CES()==null) || 
             (this.SEG_CES!=null &&
              this.SEG_CES.equals(other.getSEG_CES()))) &&
            ((this.SEG_DESG==null && other.getSEG_DESG()==null) || 
             (this.SEG_DESG!=null &&
              this.SEG_DESG.equals(other.getSEG_DESG()))) &&
            ((this.TASA_INT_MENSUAL==null && other.getTASA_INT_MENSUAL()==null) || 
             (this.TASA_INT_MENSUAL!=null &&
              this.TASA_INT_MENSUAL.equals(other.getTASA_INT_MENSUAL()))) &&
            ((this.PORC_MAX_ENDEUD==null && other.getPORC_MAX_ENDEUD()==null) || 
             (this.PORC_MAX_ENDEUD!=null &&
              this.PORC_MAX_ENDEUD.equals(other.getPORC_MAX_ENDEUD()))) &&
            ((this.PORC_ENDEUD_SIM==null && other.getPORC_ENDEUD_SIM()==null) || 
             (this.PORC_ENDEUD_SIM!=null &&
              this.PORC_ENDEUD_SIM.equals(other.getPORC_ENDEUD_SIM()))) &&
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
        if (getMONTO_ADEUDADO() != null) {
            _hashCode += getMONTO_ADEUDADO().hashCode();
        }
        if (getMONTO_CUOTA() != null) {
            _hashCode += getMONTO_CUOTA().hashCode();
        }
        if (getCAE() != null) {
            _hashCode += getCAE().hashCode();
        }
        if (getCOSTO_TOTAL() != null) {
            _hashCode += getCOSTO_TOTAL().hashCode();
        }
        if (getGRV_CONDONADO() != null) {
            _hashCode += getGRV_CONDONADO().hashCode();
        }
        if (getGC_CONDONADO() != null) {
            _hashCode += getGC_CONDONADO().hashCode();
        }
        if (getHONO_CONDONADO() != null) {
            _hashCode += getHONO_CONDONADO().hashCode();
        }
        if (getINT_CONDONADO() != null) {
            _hashCode += getINT_CONDONADO().hashCode();
        }
        if (getFECHA_PRIMER_VTO() != null) {
            _hashCode += getFECHA_PRIMER_VTO().hashCode();
        }
        if (getSEG_CES() != null) {
            _hashCode += getSEG_CES().hashCode();
        }
        if (getSEG_DESG() != null) {
            _hashCode += getSEG_DESG().hashCode();
        }
        if (getTASA_INT_MENSUAL() != null) {
            _hashCode += getTASA_INT_MENSUAL().hashCode();
        }
        if (getPORC_MAX_ENDEUD() != null) {
            _hashCode += getPORC_MAX_ENDEUD().hashCode();
        }
        if (getPORC_ENDEUD_SIM() != null) {
            _hashCode += getPORC_ENDEUD_SIM().hashCode();
        }
        if (getLOG() != null) {
            _hashCode += getLOG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimuladorRepro_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimuladorRepro_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_ADEUDADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_ADEUDADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CUOTA"));
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
        elemField.setFieldName("COSTO_TOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COSTO_TOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GRV_CONDONADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GRV_CONDONADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GC_CONDONADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GC_CONDONADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HONO_CONDONADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HONO_CONDONADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INT_CONDONADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INT_CONDONADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_PRIMER_VTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_PRIMER_VTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEG_CES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEG_CES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEG_DESG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEG_DESG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("PORC_MAX_ENDEUD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORC_MAX_ENDEUD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORC_ENDEUD_SIM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PORC_ENDEUD_SIM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "Log"));
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
