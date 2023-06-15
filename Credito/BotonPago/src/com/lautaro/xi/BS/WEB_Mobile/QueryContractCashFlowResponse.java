/**
 * QueryContractCashFlowResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Contract Cash Flow
 */
public class QueryContractCashFlowResponse  implements java.io.Serializable {
    private com.lautaro.xi.BS.Treasury.MessageHeader messageHeader;

    private java.lang.String nroCuenta;

    private java.lang.String lineaComercial;

    private com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF[] detalleCuotas;

    private com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION porc_Condonacion;

    private java.lang.Short e_TOTAL_CUOTAS;

    private java.lang.String resultCode;

    private com.lautaro.xi.BS.Treasury.Log log;

    private java.lang.String ESTADO;

    private java.math.BigInteger CANT_CUOTAS_PAG;

    private java.math.BigInteger CANT_CUOTAS_MOR;

    private java.math.BigInteger CANT_CUOTAS_FUT;

    public QueryContractCashFlowResponse() {
    }

    public QueryContractCashFlowResponse(
           com.lautaro.xi.BS.Treasury.MessageHeader messageHeader,
           java.lang.String nroCuenta,
           java.lang.String lineaComercial,
           com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF[] detalleCuotas,
           com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION porc_Condonacion,
           java.lang.Short e_TOTAL_CUOTAS,
           java.lang.String resultCode,
           com.lautaro.xi.BS.Treasury.Log log,
           java.lang.String ESTADO,
           java.math.BigInteger CANT_CUOTAS_PAG,
           java.math.BigInteger CANT_CUOTAS_MOR,
           java.math.BigInteger CANT_CUOTAS_FUT) {
           this.messageHeader = messageHeader;
           this.nroCuenta = nroCuenta;
           this.lineaComercial = lineaComercial;
           this.detalleCuotas = detalleCuotas;
           this.porc_Condonacion = porc_Condonacion;
           this.e_TOTAL_CUOTAS = e_TOTAL_CUOTAS;
           this.resultCode = resultCode;
           this.log = log;
           this.ESTADO = ESTADO;
           this.CANT_CUOTAS_PAG = CANT_CUOTAS_PAG;
           this.CANT_CUOTAS_MOR = CANT_CUOTAS_MOR;
           this.CANT_CUOTAS_FUT = CANT_CUOTAS_FUT;
    }


    /**
     * Gets the messageHeader value for this QueryContractCashFlowResponse.
     * 
     * @return messageHeader
     */
    public com.lautaro.xi.BS.Treasury.MessageHeader getMessageHeader() {
        return messageHeader;
    }


    /**
     * Sets the messageHeader value for this QueryContractCashFlowResponse.
     * 
     * @param messageHeader
     */
    public void setMessageHeader(com.lautaro.xi.BS.Treasury.MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }


    /**
     * Gets the nroCuenta value for this QueryContractCashFlowResponse.
     * 
     * @return nroCuenta
     */
    public java.lang.String getNroCuenta() {
        return nroCuenta;
    }


    /**
     * Sets the nroCuenta value for this QueryContractCashFlowResponse.
     * 
     * @param nroCuenta
     */
    public void setNroCuenta(java.lang.String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }


    /**
     * Gets the lineaComercial value for this QueryContractCashFlowResponse.
     * 
     * @return lineaComercial
     */
    public java.lang.String getLineaComercial() {
        return lineaComercial;
    }


    /**
     * Sets the lineaComercial value for this QueryContractCashFlowResponse.
     * 
     * @param lineaComercial
     */
    public void setLineaComercial(java.lang.String lineaComercial) {
        this.lineaComercial = lineaComercial;
    }


    /**
     * Gets the detalleCuotas value for this QueryContractCashFlowResponse.
     * 
     * @return detalleCuotas
     */
    public com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF[] getDetalleCuotas() {
        return detalleCuotas;
    }


    /**
     * Sets the detalleCuotas value for this QueryContractCashFlowResponse.
     * 
     * @param detalleCuotas
     */
    public void setDetalleCuotas(com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF[] detalleCuotas) {
        this.detalleCuotas = detalleCuotas;
    }

    public com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF getDetalleCuotas(int i) {
        return this.detalleCuotas[i];
    }

    public void setDetalleCuotas(int i, com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF _value) {
        this.detalleCuotas[i] = _value;
    }


    /**
     * Gets the porc_Condonacion value for this QueryContractCashFlowResponse.
     * 
     * @return porc_Condonacion
     */
    public com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION getPorc_Condonacion() {
        return porc_Condonacion;
    }


    /**
     * Sets the porc_Condonacion value for this QueryContractCashFlowResponse.
     * 
     * @param porc_Condonacion
     */
    public void setPorc_Condonacion(com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION porc_Condonacion) {
        this.porc_Condonacion = porc_Condonacion;
    }


    /**
     * Gets the e_TOTAL_CUOTAS value for this QueryContractCashFlowResponse.
     * 
     * @return e_TOTAL_CUOTAS
     */
    public java.lang.Short getE_TOTAL_CUOTAS() {
        return e_TOTAL_CUOTAS;
    }


    /**
     * Sets the e_TOTAL_CUOTAS value for this QueryContractCashFlowResponse.
     * 
     * @param e_TOTAL_CUOTAS
     */
    public void setE_TOTAL_CUOTAS(java.lang.Short e_TOTAL_CUOTAS) {
        this.e_TOTAL_CUOTAS = e_TOTAL_CUOTAS;
    }


    /**
     * Gets the resultCode value for this QueryContractCashFlowResponse.
     * 
     * @return resultCode
     */
    public java.lang.String getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this QueryContractCashFlowResponse.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * Gets the log value for this QueryContractCashFlowResponse.
     * 
     * @return log
     */
    public com.lautaro.xi.BS.Treasury.Log getLog() {
        return log;
    }


    /**
     * Sets the log value for this QueryContractCashFlowResponse.
     * 
     * @param log
     */
    public void setLog(com.lautaro.xi.BS.Treasury.Log log) {
        this.log = log;
    }


    /**
     * Gets the ESTADO value for this QueryContractCashFlowResponse.
     * 
     * @return ESTADO
     */
    public java.lang.String getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this QueryContractCashFlowResponse.
     * 
     * @param ESTADO
     */
    public void setESTADO(java.lang.String ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the CANT_CUOTAS_PAG value for this QueryContractCashFlowResponse.
     * 
     * @return CANT_CUOTAS_PAG
     */
    public java.math.BigInteger getCANT_CUOTAS_PAG() {
        return CANT_CUOTAS_PAG;
    }


    /**
     * Sets the CANT_CUOTAS_PAG value for this QueryContractCashFlowResponse.
     * 
     * @param CANT_CUOTAS_PAG
     */
    public void setCANT_CUOTAS_PAG(java.math.BigInteger CANT_CUOTAS_PAG) {
        this.CANT_CUOTAS_PAG = CANT_CUOTAS_PAG;
    }


    /**
     * Gets the CANT_CUOTAS_MOR value for this QueryContractCashFlowResponse.
     * 
     * @return CANT_CUOTAS_MOR
     */
    public java.math.BigInteger getCANT_CUOTAS_MOR() {
        return CANT_CUOTAS_MOR;
    }


    /**
     * Sets the CANT_CUOTAS_MOR value for this QueryContractCashFlowResponse.
     * 
     * @param CANT_CUOTAS_MOR
     */
    public void setCANT_CUOTAS_MOR(java.math.BigInteger CANT_CUOTAS_MOR) {
        this.CANT_CUOTAS_MOR = CANT_CUOTAS_MOR;
    }


    /**
     * Gets the CANT_CUOTAS_FUT value for this QueryContractCashFlowResponse.
     * 
     * @return CANT_CUOTAS_FUT
     */
    public java.math.BigInteger getCANT_CUOTAS_FUT() {
        return CANT_CUOTAS_FUT;
    }


    /**
     * Sets the CANT_CUOTAS_FUT value for this QueryContractCashFlowResponse.
     * 
     * @param CANT_CUOTAS_FUT
     */
    public void setCANT_CUOTAS_FUT(java.math.BigInteger CANT_CUOTAS_FUT) {
        this.CANT_CUOTAS_FUT = CANT_CUOTAS_FUT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryContractCashFlowResponse)) return false;
        QueryContractCashFlowResponse other = (QueryContractCashFlowResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageHeader==null && other.getMessageHeader()==null) || 
             (this.messageHeader!=null &&
              this.messageHeader.equals(other.getMessageHeader()))) &&
            ((this.nroCuenta==null && other.getNroCuenta()==null) || 
             (this.nroCuenta!=null &&
              this.nroCuenta.equals(other.getNroCuenta()))) &&
            ((this.lineaComercial==null && other.getLineaComercial()==null) || 
             (this.lineaComercial!=null &&
              this.lineaComercial.equals(other.getLineaComercial()))) &&
            ((this.detalleCuotas==null && other.getDetalleCuotas()==null) || 
             (this.detalleCuotas!=null &&
              java.util.Arrays.equals(this.detalleCuotas, other.getDetalleCuotas()))) &&
            ((this.porc_Condonacion==null && other.getPorc_Condonacion()==null) || 
             (this.porc_Condonacion!=null &&
              this.porc_Condonacion.equals(other.getPorc_Condonacion()))) &&
            ((this.e_TOTAL_CUOTAS==null && other.getE_TOTAL_CUOTAS()==null) || 
             (this.e_TOTAL_CUOTAS!=null &&
              this.e_TOTAL_CUOTAS.equals(other.getE_TOTAL_CUOTAS()))) &&
            ((this.resultCode==null && other.getResultCode()==null) || 
             (this.resultCode!=null &&
              this.resultCode.equals(other.getResultCode()))) &&
            ((this.log==null && other.getLog()==null) || 
             (this.log!=null &&
              this.log.equals(other.getLog()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.CANT_CUOTAS_PAG==null && other.getCANT_CUOTAS_PAG()==null) || 
             (this.CANT_CUOTAS_PAG!=null &&
              this.CANT_CUOTAS_PAG.equals(other.getCANT_CUOTAS_PAG()))) &&
            ((this.CANT_CUOTAS_MOR==null && other.getCANT_CUOTAS_MOR()==null) || 
             (this.CANT_CUOTAS_MOR!=null &&
              this.CANT_CUOTAS_MOR.equals(other.getCANT_CUOTAS_MOR()))) &&
            ((this.CANT_CUOTAS_FUT==null && other.getCANT_CUOTAS_FUT()==null) || 
             (this.CANT_CUOTAS_FUT!=null &&
              this.CANT_CUOTAS_FUT.equals(other.getCANT_CUOTAS_FUT())));
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
        if (getMessageHeader() != null) {
            _hashCode += getMessageHeader().hashCode();
        }
        if (getNroCuenta() != null) {
            _hashCode += getNroCuenta().hashCode();
        }
        if (getLineaComercial() != null) {
            _hashCode += getLineaComercial().hashCode();
        }
        if (getDetalleCuotas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleCuotas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleCuotas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPorc_Condonacion() != null) {
            _hashCode += getPorc_Condonacion().hashCode();
        }
        if (getE_TOTAL_CUOTAS() != null) {
            _hashCode += getE_TOTAL_CUOTAS().hashCode();
        }
        if (getResultCode() != null) {
            _hashCode += getResultCode().hashCode();
        }
        if (getLog() != null) {
            _hashCode += getLog().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getCANT_CUOTAS_PAG() != null) {
            _hashCode += getCANT_CUOTAS_PAG().hashCode();
        }
        if (getCANT_CUOTAS_MOR() != null) {
            _hashCode += getCANT_CUOTAS_MOR().hashCode();
        }
        if (getCANT_CUOTAS_FUT() != null) {
            _hashCode += getCANT_CUOTAS_FUT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryContractCashFlowResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryContractCashFlowResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MessageHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "MessageHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NroCuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineaComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineaComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleCuotas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DetalleCuotas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DetalleCuotasCF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porc_Condonacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Porc_Condonacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "PORC_CONDONACION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("e_TOTAL_CUOTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "E_TOTAL_CUOTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("log");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Log"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/Treasury", "Log"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANT_CUOTAS_PAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CANT_CUOTAS_PAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANT_CUOTAS_MOR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CANT_CUOTAS_MOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANT_CUOTAS_FUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CANT_CUOTAS_FUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
