/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.simulaCredito;

public class Request  implements java.io.Serializable {
    private java.lang.String USUARIO;

    private java.lang.String CLAVE;

    private java.lang.String MONTO;

    private java.lang.String CUOTAS;

    private java.lang.String SUCURSAL;

    private java.lang.String TIPO_AFILIADO;

    private java.lang.String SEGURO_CESANTIA;

    private java.lang.String SEGURO_DESGRAVAMEN;

    private java.lang.String TIPO_SEGURO;

    public Request() {
    }

    public Request(
           java.lang.String USUARIO,
           java.lang.String CLAVE,
           java.lang.String MONTO,
           java.lang.String CUOTAS,
           java.lang.String SUCURSAL,
           java.lang.String TIPO_AFILIADO,
           java.lang.String SEGURO_CESANTIA,
           java.lang.String SEGURO_DESGRAVAMEN,
           java.lang.String TIPO_SEGURO) {
           this.USUARIO = USUARIO;
           this.CLAVE = CLAVE;
           this.MONTO = MONTO;
           this.CUOTAS = CUOTAS;
           this.SUCURSAL = SUCURSAL;
           this.TIPO_AFILIADO = TIPO_AFILIADO;
           this.SEGURO_CESANTIA = SEGURO_CESANTIA;
           this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
           this.TIPO_SEGURO = TIPO_SEGURO;
    }


    /**
     * Gets the USUARIO value for this Request.
     * 
     * @return USUARIO
     */
    public java.lang.String getUSUARIO() {
        return USUARIO;
    }


    /**
     * Sets the USUARIO value for this Request.
     * 
     * @param USUARIO
     */
    public void setUSUARIO(java.lang.String USUARIO) {
        this.USUARIO = USUARIO;
    }


    /**
     * Gets the CLAVE value for this Request.
     * 
     * @return CLAVE
     */
    public java.lang.String getCLAVE() {
        return CLAVE;
    }


    /**
     * Sets the CLAVE value for this Request.
     * 
     * @param CLAVE
     */
    public void setCLAVE(java.lang.String CLAVE) {
        this.CLAVE = CLAVE;
    }


    /**
     * Gets the MONTO value for this Request.
     * 
     * @return MONTO
     */
    public java.lang.String getMONTO() {
        return MONTO;
    }


    /**
     * Sets the MONTO value for this Request.
     * 
     * @param MONTO
     */
    public void setMONTO(java.lang.String MONTO) {
        this.MONTO = MONTO;
    }


    /**
     * Gets the CUOTAS value for this Request.
     * 
     * @return CUOTAS
     */
    public java.lang.String getCUOTAS() {
        return CUOTAS;
    }


    /**
     * Sets the CUOTAS value for this Request.
     * 
     * @param CUOTAS
     */
    public void setCUOTAS(java.lang.String CUOTAS) {
        this.CUOTAS = CUOTAS;
    }


    /**
     * Gets the SUCURSAL value for this Request.
     * 
     * @return SUCURSAL
     */
    public java.lang.String getSUCURSAL() {
        return SUCURSAL;
    }


    /**
     * Sets the SUCURSAL value for this Request.
     * 
     * @param SUCURSAL
     */
    public void setSUCURSAL(java.lang.String SUCURSAL) {
        this.SUCURSAL = SUCURSAL;
    }


    /**
     * Gets the TIPO_AFILIADO value for this Request.
     * 
     * @return TIPO_AFILIADO
     */
    public java.lang.String getTIPO_AFILIADO() {
        return TIPO_AFILIADO;
    }


    /**
     * Sets the TIPO_AFILIADO value for this Request.
     * 
     * @param TIPO_AFILIADO
     */
    public void setTIPO_AFILIADO(java.lang.String TIPO_AFILIADO) {
        this.TIPO_AFILIADO = TIPO_AFILIADO;
    }


    /**
     * Gets the SEGURO_CESANTIA value for this Request.
     * 
     * @return SEGURO_CESANTIA
     */
    public java.lang.String getSEGURO_CESANTIA() {
        return SEGURO_CESANTIA;
    }


    /**
     * Sets the SEGURO_CESANTIA value for this Request.
     * 
     * @param SEGURO_CESANTIA
     */
    public void setSEGURO_CESANTIA(java.lang.String SEGURO_CESANTIA) {
        this.SEGURO_CESANTIA = SEGURO_CESANTIA;
    }


    /**
     * Gets the SEGURO_DESGRAVAMEN value for this Request.
     * 
     * @return SEGURO_DESGRAVAMEN
     */
    public java.lang.String getSEGURO_DESGRAVAMEN() {
        return SEGURO_DESGRAVAMEN;
    }


    /**
     * Sets the SEGURO_DESGRAVAMEN value for this Request.
     * 
     * @param SEGURO_DESGRAVAMEN
     */
    public void setSEGURO_DESGRAVAMEN(java.lang.String SEGURO_DESGRAVAMEN) {
        this.SEGURO_DESGRAVAMEN = SEGURO_DESGRAVAMEN;
    }


    /**
     * Gets the TIPO_SEGURO value for this Request.
     * 
     * @return TIPO_SEGURO
     */
    public java.lang.String getTIPO_SEGURO() {
        return TIPO_SEGURO;
    }


    /**
     * Sets the TIPO_SEGURO value for this Request.
     * 
     * @param TIPO_SEGURO
     */
    public void setTIPO_SEGURO(java.lang.String TIPO_SEGURO) {
        this.TIPO_SEGURO = TIPO_SEGURO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Request)) return false;
        Request other = (Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.USUARIO==null && other.getUSUARIO()==null) || 
             (this.USUARIO!=null &&
              this.USUARIO.equals(other.getUSUARIO()))) &&
            ((this.CLAVE==null && other.getCLAVE()==null) || 
             (this.CLAVE!=null &&
              this.CLAVE.equals(other.getCLAVE()))) &&
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
        if (getUSUARIO() != null) {
            _hashCode += getUSUARIO().hashCode();
        }
        if (getCLAVE() != null) {
            _hashCode += getCLAVE().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simulaCredito", "Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USUARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLAVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
