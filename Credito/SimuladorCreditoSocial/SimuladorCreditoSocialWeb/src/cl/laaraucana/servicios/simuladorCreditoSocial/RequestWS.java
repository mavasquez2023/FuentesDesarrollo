package cl.laaraucana.servicios.simuladorCreditoSocial;

public class RequestWS  implements java.io.Serializable {
    private int MONTO;

    private int CUOTAS;

    private java.lang.String SUCURSAL;

    private java.lang.String TIPO_AFILIADO;

    private java.lang.String SEGURO_CESANTIA;

    private java.lang.String SEGURO_DESGRAVAMEN;

    public RequestWS() {
    }

    public RequestWS(
           int MONTO,
           int CUOTAS,
           java.lang.String SUCURSAL,
           java.lang.String TIPO_AFILIADO,
           java.lang.String SEGURO_CESANTIA,
           java.lang.String SEGURO_DESGRAVAMEN) {
           this.MONTO = MONTO;
           this.CUOTAS = CUOTAS;
           this.SUCURSAL = SUCURSAL;
           this.TIPO_AFILIADO = TIPO_AFILIADO;
           this.SEGURO_CESANTIA = SEGURO_CESANTIA;
           this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
    }


    /**
     * Gets the MONTO value for this RequestWS.
     * 
     * @return MONTO
     */
    public int getMONTO() {
        return MONTO;
    }


    /**
     * Sets the MONTO value for this RequestWS.
     * 
     * @param MONTO
     */
    public void setMONTO(int MONTO) {
        this.MONTO = MONTO;
    }


    /**
     * Gets the CUOTAS value for this RequestWS.
     * 
     * @return CUOTAS
     */
    public int getCUOTAS() {
        return CUOTAS;
    }


    /**
     * Sets the CUOTAS value for this RequestWS.
     * 
     * @param CUOTAS
     */
    public void setCUOTAS(int CUOTAS) {
        this.CUOTAS = CUOTAS;
    }


    /**
     * Gets the SUCURSAL value for this RequestWS.
     * 
     * @return SUCURSAL
     */
    public java.lang.String getSUCURSAL() {
        return SUCURSAL;
    }


    /**
     * Sets the SUCURSAL value for this RequestWS.
     * 
     * @param SUCURSAL
     */
    public void setSUCURSAL(java.lang.String SUCURSAL) {
        this.SUCURSAL = SUCURSAL;
    }


    /**
     * Gets the TIPO_AFILIADO value for this RequestWS.
     * 
     * @return TIPO_AFILIADO
     */
    public java.lang.String getTIPO_AFILIADO() {
        return TIPO_AFILIADO;
    }


    /**
     * Sets the TIPO_AFILIADO value for this RequestWS.
     * 
     * @param TIPO_AFILIADO
     */
    public void setTIPO_AFILIADO(java.lang.String TIPO_AFILIADO) {
        this.TIPO_AFILIADO = TIPO_AFILIADO;
    }


    /**
     * Gets the SEGURO_CESANTIA value for this RequestWS.
     * 
     * @return SEGURO_CESANTIA
     */
    public java.lang.String getSEGURO_CESANTIA() {
        return SEGURO_CESANTIA;
    }


    /**
     * Sets the SEGURO_CESANTIA value for this RequestWS.
     * 
     * @param SEGURO_CESANTIA
     */
    public void setSEGURO_CESANTIA(java.lang.String SEGURO_CESANTIA) {
        this.SEGURO_CESANTIA = SEGURO_CESANTIA;
    }


    /**
     * Gets the SEGURO_DESGRAVAMEN value for this RequestWS.
     * 
     * @return SEGURO_DESGRAVAMEN
     */
    public java.lang.String getSEGURO_DESGRAVAMEN() {
        return SEGURO_DESGRAVAMEN;
    }


    /**
     * Sets the SEGURO_DESGRAVAMEN value for this RequestWS.
     * 
     * @param SEGURO_DESGRAVAMEN
     */
    public void setSEGURO_DESGRAVAMEN(java.lang.String SEGURO_DESGRAVAMEN) {
        this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequestWS)) return false;
        RequestWS other = (RequestWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.MONTO == other.getMONTO() &&
            this.CUOTAS == other.getCUOTAS() &&
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
              this.SEGURO_DESGRAVAMEN.equals(other.getSEGURO_DESGRAVAMEN())));
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
        _hashCode += getMONTO();
        _hashCode += getCUOTAS();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "requestWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
