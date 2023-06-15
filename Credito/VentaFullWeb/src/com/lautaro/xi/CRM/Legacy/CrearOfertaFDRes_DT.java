/**
 * CrearOfertaFDRes_DT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.Legacy;

public class CrearOfertaFDRes_DT  implements java.io.Serializable {
    private java.lang.String RUT_AFILIADO;

    private java.lang.String NRO_OFERTA_GENERADA;

    private java.lang.String FOLIO_GENERADO;

    private java.lang.String SUCURSAL_OFERTA;

    private java.lang.String ESTADO_OFERTA;

    private java.lang.String ESTADO_APROB_CLIENTE;

    private java.lang.String MENSAJE;

    public CrearOfertaFDRes_DT() {
    }

    public CrearOfertaFDRes_DT(
           java.lang.String RUT_AFILIADO,
           java.lang.String NRO_OFERTA_GENERADA,
           java.lang.String FOLIO_GENERADO,
           java.lang.String SUCURSAL_OFERTA,
           java.lang.String ESTADO_OFERTA,
           java.lang.String ESTADO_APROB_CLIENTE,
           java.lang.String MENSAJE) {
           this.RUT_AFILIADO = RUT_AFILIADO;
           this.NRO_OFERTA_GENERADA = NRO_OFERTA_GENERADA;
           this.FOLIO_GENERADO = FOLIO_GENERADO;
           this.SUCURSAL_OFERTA = SUCURSAL_OFERTA;
           this.ESTADO_OFERTA = ESTADO_OFERTA;
           this.ESTADO_APROB_CLIENTE = ESTADO_APROB_CLIENTE;
           this.MENSAJE = MENSAJE;
    }


    /**
     * Gets the RUT_AFILIADO value for this CrearOfertaFDRes_DT.
     * 
     * @return RUT_AFILIADO
     */
    public java.lang.String getRUT_AFILIADO() {
        return RUT_AFILIADO;
    }


    /**
     * Sets the RUT_AFILIADO value for this CrearOfertaFDRes_DT.
     * 
     * @param RUT_AFILIADO
     */
    public void setRUT_AFILIADO(java.lang.String RUT_AFILIADO) {
        this.RUT_AFILIADO = RUT_AFILIADO;
    }


    /**
     * Gets the NRO_OFERTA_GENERADA value for this CrearOfertaFDRes_DT.
     * 
     * @return NRO_OFERTA_GENERADA
     */
    public java.lang.String getNRO_OFERTA_GENERADA() {
        return NRO_OFERTA_GENERADA;
    }


    /**
     * Sets the NRO_OFERTA_GENERADA value for this CrearOfertaFDRes_DT.
     * 
     * @param NRO_OFERTA_GENERADA
     */
    public void setNRO_OFERTA_GENERADA(java.lang.String NRO_OFERTA_GENERADA) {
        this.NRO_OFERTA_GENERADA = NRO_OFERTA_GENERADA;
    }


    /**
     * Gets the FOLIO_GENERADO value for this CrearOfertaFDRes_DT.
     * 
     * @return FOLIO_GENERADO
     */
    public java.lang.String getFOLIO_GENERADO() {
        return FOLIO_GENERADO;
    }


    /**
     * Sets the FOLIO_GENERADO value for this CrearOfertaFDRes_DT.
     * 
     * @param FOLIO_GENERADO
     */
    public void setFOLIO_GENERADO(java.lang.String FOLIO_GENERADO) {
        this.FOLIO_GENERADO = FOLIO_GENERADO;
    }


    /**
     * Gets the SUCURSAL_OFERTA value for this CrearOfertaFDRes_DT.
     * 
     * @return SUCURSAL_OFERTA
     */
    public java.lang.String getSUCURSAL_OFERTA() {
        return SUCURSAL_OFERTA;
    }


    /**
     * Sets the SUCURSAL_OFERTA value for this CrearOfertaFDRes_DT.
     * 
     * @param SUCURSAL_OFERTA
     */
    public void setSUCURSAL_OFERTA(java.lang.String SUCURSAL_OFERTA) {
        this.SUCURSAL_OFERTA = SUCURSAL_OFERTA;
    }


    /**
     * Gets the ESTADO_OFERTA value for this CrearOfertaFDRes_DT.
     * 
     * @return ESTADO_OFERTA
     */
    public java.lang.String getESTADO_OFERTA() {
        return ESTADO_OFERTA;
    }


    /**
     * Sets the ESTADO_OFERTA value for this CrearOfertaFDRes_DT.
     * 
     * @param ESTADO_OFERTA
     */
    public void setESTADO_OFERTA(java.lang.String ESTADO_OFERTA) {
        this.ESTADO_OFERTA = ESTADO_OFERTA;
    }


    /**
     * Gets the ESTADO_APROB_CLIENTE value for this CrearOfertaFDRes_DT.
     * 
     * @return ESTADO_APROB_CLIENTE
     */
    public java.lang.String getESTADO_APROB_CLIENTE() {
        return ESTADO_APROB_CLIENTE;
    }


    /**
     * Sets the ESTADO_APROB_CLIENTE value for this CrearOfertaFDRes_DT.
     * 
     * @param ESTADO_APROB_CLIENTE
     */
    public void setESTADO_APROB_CLIENTE(java.lang.String ESTADO_APROB_CLIENTE) {
        this.ESTADO_APROB_CLIENTE = ESTADO_APROB_CLIENTE;
    }


    /**
     * Gets the MENSAJE value for this CrearOfertaFDRes_DT.
     * 
     * @return MENSAJE
     */
    public java.lang.String getMENSAJE() {
        return MENSAJE;
    }


    /**
     * Sets the MENSAJE value for this CrearOfertaFDRes_DT.
     * 
     * @param MENSAJE
     */
    public void setMENSAJE(java.lang.String MENSAJE) {
        this.MENSAJE = MENSAJE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CrearOfertaFDRes_DT)) return false;
        CrearOfertaFDRes_DT other = (CrearOfertaFDRes_DT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUT_AFILIADO==null && other.getRUT_AFILIADO()==null) || 
             (this.RUT_AFILIADO!=null &&
              this.RUT_AFILIADO.equals(other.getRUT_AFILIADO()))) &&
            ((this.NRO_OFERTA_GENERADA==null && other.getNRO_OFERTA_GENERADA()==null) || 
             (this.NRO_OFERTA_GENERADA!=null &&
              this.NRO_OFERTA_GENERADA.equals(other.getNRO_OFERTA_GENERADA()))) &&
            ((this.FOLIO_GENERADO==null && other.getFOLIO_GENERADO()==null) || 
             (this.FOLIO_GENERADO!=null &&
              this.FOLIO_GENERADO.equals(other.getFOLIO_GENERADO()))) &&
            ((this.SUCURSAL_OFERTA==null && other.getSUCURSAL_OFERTA()==null) || 
             (this.SUCURSAL_OFERTA!=null &&
              this.SUCURSAL_OFERTA.equals(other.getSUCURSAL_OFERTA()))) &&
            ((this.ESTADO_OFERTA==null && other.getESTADO_OFERTA()==null) || 
             (this.ESTADO_OFERTA!=null &&
              this.ESTADO_OFERTA.equals(other.getESTADO_OFERTA()))) &&
            ((this.ESTADO_APROB_CLIENTE==null && other.getESTADO_APROB_CLIENTE()==null) || 
             (this.ESTADO_APROB_CLIENTE!=null &&
              this.ESTADO_APROB_CLIENTE.equals(other.getESTADO_APROB_CLIENTE()))) &&
            ((this.MENSAJE==null && other.getMENSAJE()==null) || 
             (this.MENSAJE!=null &&
              this.MENSAJE.equals(other.getMENSAJE())));
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
        if (getRUT_AFILIADO() != null) {
            _hashCode += getRUT_AFILIADO().hashCode();
        }
        if (getNRO_OFERTA_GENERADA() != null) {
            _hashCode += getNRO_OFERTA_GENERADA().hashCode();
        }
        if (getFOLIO_GENERADO() != null) {
            _hashCode += getFOLIO_GENERADO().hashCode();
        }
        if (getSUCURSAL_OFERTA() != null) {
            _hashCode += getSUCURSAL_OFERTA().hashCode();
        }
        if (getESTADO_OFERTA() != null) {
            _hashCode += getESTADO_OFERTA().hashCode();
        }
        if (getESTADO_APROB_CLIENTE() != null) {
            _hashCode += getESTADO_APROB_CLIENTE().hashCode();
        }
        if (getMENSAJE() != null) {
            _hashCode += getMENSAJE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrearOfertaFDRes_DT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/Legacy", "CrearOfertaFDRes_DT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT_AFILIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT_AFILIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRO_OFERTA_GENERADA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NRO_OFERTA_GENERADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FOLIO_GENERADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FOLIO_GENERADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUCURSAL_OFERTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUCURSAL_OFERTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO_OFERTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO_OFERTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO_APROB_CLIENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO_APROB_CLIENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MENSAJE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MENSAJE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
