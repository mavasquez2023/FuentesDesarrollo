/**
 * DT_SimuladorRepro_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * 8000003038 - Simulador web Repro
 */
public class DT_SimuladorRepro_REQ  implements java.io.Serializable {
    private java.lang.String RUT;

    private java.lang.String NRO_CONTRATO;

    private java.math.BigInteger PLAZO;

    private java.math.BigInteger MESES_DE_GRACIA;

    private java.lang.String OFICINA;

    private java.lang.String TIPO_AFILIADO;

    private java.math.BigDecimal MONTO_ABONO;

    private java.lang.String SEG_CES;

    private java.lang.String SEG_DESG;

    private java.lang.String TASA_INTERES;

    /* 8000003746 */
    private java.lang.String PRODUCTO;

    /* 8000003746 */
    private java.lang.String ANEXO;

    /* 8000003746 */
    private java.lang.String NRO_INSCRIPCION;

    /* 8000003746 */
    private java.math.BigDecimal RENTA;

    private com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQSTR_CONDONACION STR_CONDONACION;

    public DT_SimuladorRepro_REQ() {
    }

    public DT_SimuladorRepro_REQ(
           java.lang.String RUT,
           java.lang.String NRO_CONTRATO,
           java.math.BigInteger PLAZO,
           java.math.BigInteger MESES_DE_GRACIA,
           java.lang.String OFICINA,
           java.lang.String TIPO_AFILIADO,
           java.math.BigDecimal MONTO_ABONO,
           java.lang.String SEG_CES,
           java.lang.String SEG_DESG,
           java.lang.String TASA_INTERES,
           java.lang.String PRODUCTO,
           java.lang.String ANEXO,
           java.lang.String NRO_INSCRIPCION,
           java.math.BigDecimal RENTA,
           com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQSTR_CONDONACION STR_CONDONACION) {
           this.RUT = RUT;
           this.NRO_CONTRATO = NRO_CONTRATO;
           this.PLAZO = PLAZO;
           this.MESES_DE_GRACIA = MESES_DE_GRACIA;
           this.OFICINA = OFICINA;
           this.TIPO_AFILIADO = TIPO_AFILIADO;
           this.MONTO_ABONO = MONTO_ABONO;
           this.SEG_CES = SEG_CES;
           this.SEG_DESG = SEG_DESG;
           this.TASA_INTERES = TASA_INTERES;
           this.PRODUCTO = PRODUCTO;
           this.ANEXO = ANEXO;
           this.NRO_INSCRIPCION = NRO_INSCRIPCION;
           this.RENTA = RENTA;
           this.STR_CONDONACION = STR_CONDONACION;
    }


    /**
     * Gets the RUT value for this DT_SimuladorRepro_REQ.
     * 
     * @return RUT
     */
    public java.lang.String getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this DT_SimuladorRepro_REQ.
     * 
     * @param RUT
     */
    public void setRUT(java.lang.String RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the NRO_CONTRATO value for this DT_SimuladorRepro_REQ.
     * 
     * @return NRO_CONTRATO
     */
    public java.lang.String getNRO_CONTRATO() {
        return NRO_CONTRATO;
    }


    /**
     * Sets the NRO_CONTRATO value for this DT_SimuladorRepro_REQ.
     * 
     * @param NRO_CONTRATO
     */
    public void setNRO_CONTRATO(java.lang.String NRO_CONTRATO) {
        this.NRO_CONTRATO = NRO_CONTRATO;
    }


    /**
     * Gets the PLAZO value for this DT_SimuladorRepro_REQ.
     * 
     * @return PLAZO
     */
    public java.math.BigInteger getPLAZO() {
        return PLAZO;
    }


    /**
     * Sets the PLAZO value for this DT_SimuladorRepro_REQ.
     * 
     * @param PLAZO
     */
    public void setPLAZO(java.math.BigInteger PLAZO) {
        this.PLAZO = PLAZO;
    }


    /**
     * Gets the MESES_DE_GRACIA value for this DT_SimuladorRepro_REQ.
     * 
     * @return MESES_DE_GRACIA
     */
    public java.math.BigInteger getMESES_DE_GRACIA() {
        return MESES_DE_GRACIA;
    }


    /**
     * Sets the MESES_DE_GRACIA value for this DT_SimuladorRepro_REQ.
     * 
     * @param MESES_DE_GRACIA
     */
    public void setMESES_DE_GRACIA(java.math.BigInteger MESES_DE_GRACIA) {
        this.MESES_DE_GRACIA = MESES_DE_GRACIA;
    }


    /**
     * Gets the OFICINA value for this DT_SimuladorRepro_REQ.
     * 
     * @return OFICINA
     */
    public java.lang.String getOFICINA() {
        return OFICINA;
    }


    /**
     * Sets the OFICINA value for this DT_SimuladorRepro_REQ.
     * 
     * @param OFICINA
     */
    public void setOFICINA(java.lang.String OFICINA) {
        this.OFICINA = OFICINA;
    }


    /**
     * Gets the TIPO_AFILIADO value for this DT_SimuladorRepro_REQ.
     * 
     * @return TIPO_AFILIADO
     */
    public java.lang.String getTIPO_AFILIADO() {
        return TIPO_AFILIADO;
    }


    /**
     * Sets the TIPO_AFILIADO value for this DT_SimuladorRepro_REQ.
     * 
     * @param TIPO_AFILIADO
     */
    public void setTIPO_AFILIADO(java.lang.String TIPO_AFILIADO) {
        this.TIPO_AFILIADO = TIPO_AFILIADO;
    }


    /**
     * Gets the MONTO_ABONO value for this DT_SimuladorRepro_REQ.
     * 
     * @return MONTO_ABONO
     */
    public java.math.BigDecimal getMONTO_ABONO() {
        return MONTO_ABONO;
    }


    /**
     * Sets the MONTO_ABONO value for this DT_SimuladorRepro_REQ.
     * 
     * @param MONTO_ABONO
     */
    public void setMONTO_ABONO(java.math.BigDecimal MONTO_ABONO) {
        this.MONTO_ABONO = MONTO_ABONO;
    }


    /**
     * Gets the SEG_CES value for this DT_SimuladorRepro_REQ.
     * 
     * @return SEG_CES
     */
    public java.lang.String getSEG_CES() {
        return SEG_CES;
    }


    /**
     * Sets the SEG_CES value for this DT_SimuladorRepro_REQ.
     * 
     * @param SEG_CES
     */
    public void setSEG_CES(java.lang.String SEG_CES) {
        this.SEG_CES = SEG_CES;
    }


    /**
     * Gets the SEG_DESG value for this DT_SimuladorRepro_REQ.
     * 
     * @return SEG_DESG
     */
    public java.lang.String getSEG_DESG() {
        return SEG_DESG;
    }


    /**
     * Sets the SEG_DESG value for this DT_SimuladorRepro_REQ.
     * 
     * @param SEG_DESG
     */
    public void setSEG_DESG(java.lang.String SEG_DESG) {
        this.SEG_DESG = SEG_DESG;
    }


    /**
     * Gets the TASA_INTERES value for this DT_SimuladorRepro_REQ.
     * 
     * @return TASA_INTERES
     */
    public java.lang.String getTASA_INTERES() {
        return TASA_INTERES;
    }


    /**
     * Sets the TASA_INTERES value for this DT_SimuladorRepro_REQ.
     * 
     * @param TASA_INTERES
     */
    public void setTASA_INTERES(java.lang.String TASA_INTERES) {
        this.TASA_INTERES = TASA_INTERES;
    }


    /**
     * Gets the PRODUCTO value for this DT_SimuladorRepro_REQ.
     * 
     * @return PRODUCTO   * 8000003746
     */
    public java.lang.String getPRODUCTO() {
        return PRODUCTO;
    }


    /**
     * Sets the PRODUCTO value for this DT_SimuladorRepro_REQ.
     * 
     * @param PRODUCTO   * 8000003746
     */
    public void setPRODUCTO(java.lang.String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }


    /**
     * Gets the ANEXO value for this DT_SimuladorRepro_REQ.
     * 
     * @return ANEXO   * 8000003746
     */
    public java.lang.String getANEXO() {
        return ANEXO;
    }


    /**
     * Sets the ANEXO value for this DT_SimuladorRepro_REQ.
     * 
     * @param ANEXO   * 8000003746
     */
    public void setANEXO(java.lang.String ANEXO) {
        this.ANEXO = ANEXO;
    }


    /**
     * Gets the NRO_INSCRIPCION value for this DT_SimuladorRepro_REQ.
     * 
     * @return NRO_INSCRIPCION   * 8000003746
     */
    public java.lang.String getNRO_INSCRIPCION() {
        return NRO_INSCRIPCION;
    }


    /**
     * Sets the NRO_INSCRIPCION value for this DT_SimuladorRepro_REQ.
     * 
     * @param NRO_INSCRIPCION   * 8000003746
     */
    public void setNRO_INSCRIPCION(java.lang.String NRO_INSCRIPCION) {
        this.NRO_INSCRIPCION = NRO_INSCRIPCION;
    }


    /**
     * Gets the RENTA value for this DT_SimuladorRepro_REQ.
     * 
     * @return RENTA   * 8000003746
     */
    public java.math.BigDecimal getRENTA() {
        return RENTA;
    }


    /**
     * Sets the RENTA value for this DT_SimuladorRepro_REQ.
     * 
     * @param RENTA   * 8000003746
     */
    public void setRENTA(java.math.BigDecimal RENTA) {
        this.RENTA = RENTA;
    }


    /**
     * Gets the STR_CONDONACION value for this DT_SimuladorRepro_REQ.
     * 
     * @return STR_CONDONACION
     */
    public com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQSTR_CONDONACION getSTR_CONDONACION() {
        return STR_CONDONACION;
    }


    /**
     * Sets the STR_CONDONACION value for this DT_SimuladorRepro_REQ.
     * 
     * @param STR_CONDONACION
     */
    public void setSTR_CONDONACION(com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQSTR_CONDONACION STR_CONDONACION) {
        this.STR_CONDONACION = STR_CONDONACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SimuladorRepro_REQ)) return false;
        DT_SimuladorRepro_REQ other = (DT_SimuladorRepro_REQ) obj;
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
            ((this.NRO_CONTRATO==null && other.getNRO_CONTRATO()==null) || 
             (this.NRO_CONTRATO!=null &&
              this.NRO_CONTRATO.equals(other.getNRO_CONTRATO()))) &&
            ((this.PLAZO==null && other.getPLAZO()==null) || 
             (this.PLAZO!=null &&
              this.PLAZO.equals(other.getPLAZO()))) &&
            ((this.MESES_DE_GRACIA==null && other.getMESES_DE_GRACIA()==null) || 
             (this.MESES_DE_GRACIA!=null &&
              this.MESES_DE_GRACIA.equals(other.getMESES_DE_GRACIA()))) &&
            ((this.OFICINA==null && other.getOFICINA()==null) || 
             (this.OFICINA!=null &&
              this.OFICINA.equals(other.getOFICINA()))) &&
            ((this.TIPO_AFILIADO==null && other.getTIPO_AFILIADO()==null) || 
             (this.TIPO_AFILIADO!=null &&
              this.TIPO_AFILIADO.equals(other.getTIPO_AFILIADO()))) &&
            ((this.MONTO_ABONO==null && other.getMONTO_ABONO()==null) || 
             (this.MONTO_ABONO!=null &&
              this.MONTO_ABONO.equals(other.getMONTO_ABONO()))) &&
            ((this.SEG_CES==null && other.getSEG_CES()==null) || 
             (this.SEG_CES!=null &&
              this.SEG_CES.equals(other.getSEG_CES()))) &&
            ((this.SEG_DESG==null && other.getSEG_DESG()==null) || 
             (this.SEG_DESG!=null &&
              this.SEG_DESG.equals(other.getSEG_DESG()))) &&
            ((this.TASA_INTERES==null && other.getTASA_INTERES()==null) || 
             (this.TASA_INTERES!=null &&
              this.TASA_INTERES.equals(other.getTASA_INTERES()))) &&
            ((this.PRODUCTO==null && other.getPRODUCTO()==null) || 
             (this.PRODUCTO!=null &&
              this.PRODUCTO.equals(other.getPRODUCTO()))) &&
            ((this.ANEXO==null && other.getANEXO()==null) || 
             (this.ANEXO!=null &&
              this.ANEXO.equals(other.getANEXO()))) &&
            ((this.NRO_INSCRIPCION==null && other.getNRO_INSCRIPCION()==null) || 
             (this.NRO_INSCRIPCION!=null &&
              this.NRO_INSCRIPCION.equals(other.getNRO_INSCRIPCION()))) &&
            ((this.RENTA==null && other.getRENTA()==null) || 
             (this.RENTA!=null &&
              this.RENTA.equals(other.getRENTA()))) &&
            ((this.STR_CONDONACION==null && other.getSTR_CONDONACION()==null) || 
             (this.STR_CONDONACION!=null &&
              this.STR_CONDONACION.equals(other.getSTR_CONDONACION())));
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
        if (getNRO_CONTRATO() != null) {
            _hashCode += getNRO_CONTRATO().hashCode();
        }
        if (getPLAZO() != null) {
            _hashCode += getPLAZO().hashCode();
        }
        if (getMESES_DE_GRACIA() != null) {
            _hashCode += getMESES_DE_GRACIA().hashCode();
        }
        if (getOFICINA() != null) {
            _hashCode += getOFICINA().hashCode();
        }
        if (getTIPO_AFILIADO() != null) {
            _hashCode += getTIPO_AFILIADO().hashCode();
        }
        if (getMONTO_ABONO() != null) {
            _hashCode += getMONTO_ABONO().hashCode();
        }
        if (getSEG_CES() != null) {
            _hashCode += getSEG_CES().hashCode();
        }
        if (getSEG_DESG() != null) {
            _hashCode += getSEG_DESG().hashCode();
        }
        if (getTASA_INTERES() != null) {
            _hashCode += getTASA_INTERES().hashCode();
        }
        if (getPRODUCTO() != null) {
            _hashCode += getPRODUCTO().hashCode();
        }
        if (getANEXO() != null) {
            _hashCode += getANEXO().hashCode();
        }
        if (getNRO_INSCRIPCION() != null) {
            _hashCode += getNRO_INSCRIPCION().hashCode();
        }
        if (getRENTA() != null) {
            _hashCode += getRENTA().hashCode();
        }
        if (getSTR_CONDONACION() != null) {
            _hashCode += getSTR_CONDONACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SimuladorRepro_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DT_SimuladorRepro_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRO_CONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NRO_CONTRATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLAZO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PLAZO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESES_DE_GRACIA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESES_DE_GRACIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OFICINA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OFICINA"));
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
        elemField.setFieldName("MONTO_ABONO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_ABONO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEG_CES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEG_CES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEG_DESG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEG_DESG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INTERES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INTERES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANEXO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ANEXO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRO_INSCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NRO_INSCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STR_CONDONACION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STR_CONDONACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", ">DT_SimuladorRepro_REQ>STR_CONDONACION"));
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
