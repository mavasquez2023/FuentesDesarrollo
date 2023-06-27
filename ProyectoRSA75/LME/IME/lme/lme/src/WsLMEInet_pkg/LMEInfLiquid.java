/**
 * LMEInfLiquid.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEInfLiquid  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String tipoInstitucion;

    private java.lang.String codUsuario;

    private java.lang.String clave;

    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private java.util.Calendar fecProceso;

    private java.math.BigInteger tipoLiquidacion;

    private byte[] datosLiquidacion;

    public LMEInfLiquid() {
    }

    public LMEInfLiquid(
           java.lang.String codigoOperador,
           java.lang.String tipoInstitucion,
           java.lang.String codUsuario,
           java.lang.String clave,
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           java.util.Calendar fecProceso,
           java.math.BigInteger tipoLiquidacion,
           byte[] datosLiquidacion) {
           this.codigoOperador = codigoOperador;
           this.tipoInstitucion = tipoInstitucion;
           this.codUsuario = codUsuario;
           this.clave = clave;
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.fecProceso = fecProceso;
           this.tipoLiquidacion = tipoLiquidacion;
           this.datosLiquidacion = datosLiquidacion;
    }


    /**
     * Gets the codigoOperador value for this LMEInfLiquid.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEInfLiquid.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the tipoInstitucion value for this LMEInfLiquid.
     * 
     * @return tipoInstitucion
     */
    public java.lang.String getTipoInstitucion() {
        return tipoInstitucion;
    }


    /**
     * Sets the tipoInstitucion value for this LMEInfLiquid.
     * 
     * @param tipoInstitucion
     */
    public void setTipoInstitucion(java.lang.String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }


    /**
     * Gets the codUsuario value for this LMEInfLiquid.
     * 
     * @return codUsuario
     */
    public java.lang.String getCodUsuario() {
        return codUsuario;
    }


    /**
     * Sets the codUsuario value for this LMEInfLiquid.
     * 
     * @param codUsuario
     */
    public void setCodUsuario(java.lang.String codUsuario) {
        this.codUsuario = codUsuario;
    }


    /**
     * Gets the clave value for this LMEInfLiquid.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEInfLiquid.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the idLicencia value for this LMEInfLiquid.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this LMEInfLiquid.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this LMEInfLiquid.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this LMEInfLiquid.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the fecProceso value for this LMEInfLiquid.
     * 
     * @return fecProceso
     */
    public java.util.Calendar getFecProceso() {
        return fecProceso;
    }


    /**
     * Sets the fecProceso value for this LMEInfLiquid.
     * 
     * @param fecProceso
     */
    public void setFecProceso(java.util.Calendar fecProceso) {
        this.fecProceso = fecProceso;
    }


    /**
     * Gets the tipoLiquidacion value for this LMEInfLiquid.
     * 
     * @return tipoLiquidacion
     */
    public java.math.BigInteger getTipoLiquidacion() {
        return tipoLiquidacion;
    }


    /**
     * Sets the tipoLiquidacion value for this LMEInfLiquid.
     * 
     * @param tipoLiquidacion
     */
    public void setTipoLiquidacion(java.math.BigInteger tipoLiquidacion) {
        this.tipoLiquidacion = tipoLiquidacion;
    }


    /**
     * Gets the datosLiquidacion value for this LMEInfLiquid.
     * 
     * @return datosLiquidacion
     */
    public byte[] getDatosLiquidacion() {
        return datosLiquidacion;
    }


    /**
     * Sets the datosLiquidacion value for this LMEInfLiquid.
     * 
     * @param datosLiquidacion
     */
    public void setDatosLiquidacion(byte[] datosLiquidacion) {
        this.datosLiquidacion = datosLiquidacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfLiquid)) return false;
        LMEInfLiquid other = (LMEInfLiquid) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoOperador==null && other.getCodigoOperador()==null) || 
             (this.codigoOperador!=null &&
              this.codigoOperador.equals(other.getCodigoOperador()))) &&
            ((this.tipoInstitucion==null && other.getTipoInstitucion()==null) || 
             (this.tipoInstitucion!=null &&
              this.tipoInstitucion.equals(other.getTipoInstitucion()))) &&
            ((this.codUsuario==null && other.getCodUsuario()==null) || 
             (this.codUsuario!=null &&
              this.codUsuario.equals(other.getCodUsuario()))) &&
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave()))) &&
            ((this.idLicencia==null && other.getIdLicencia()==null) || 
             (this.idLicencia!=null &&
              this.idLicencia.equals(other.getIdLicencia()))) &&
            ((this.dvLicencia==null && other.getDvLicencia()==null) || 
             (this.dvLicencia!=null &&
              this.dvLicencia.equals(other.getDvLicencia()))) &&
            ((this.fecProceso==null && other.getFecProceso()==null) || 
             (this.fecProceso!=null &&
              this.fecProceso.equals(other.getFecProceso()))) &&
            ((this.tipoLiquidacion==null && other.getTipoLiquidacion()==null) || 
             (this.tipoLiquidacion!=null &&
              this.tipoLiquidacion.equals(other.getTipoLiquidacion()))) &&
            ((this.datosLiquidacion==null && other.getDatosLiquidacion()==null) || 
             (this.datosLiquidacion!=null &&
              java.util.Arrays.equals(this.datosLiquidacion, other.getDatosLiquidacion())));
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
        if (getCodigoOperador() != null) {
            _hashCode += getCodigoOperador().hashCode();
        }
        if (getTipoInstitucion() != null) {
            _hashCode += getTipoInstitucion().hashCode();
        }
        if (getCodUsuario() != null) {
            _hashCode += getCodUsuario().hashCode();
        }
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        if (getIdLicencia() != null) {
            _hashCode += getIdLicencia().hashCode();
        }
        if (getDvLicencia() != null) {
            _hashCode += getDvLicencia().hashCode();
        }
        if (getFecProceso() != null) {
            _hashCode += getFecProceso().hashCode();
        }
        if (getTipoLiquidacion() != null) {
            _hashCode += getTipoLiquidacion().hashCode();
        }
        if (getDatosLiquidacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosLiquidacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosLiquidacion(), i);
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
        new org.apache.axis.description.TypeDesc(LMEInfLiquid.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfLiquid"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOperador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodigoOperador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoInstitucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoInstitucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dvLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DvLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoLiquidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoLiquidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosLiquidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DatosLiquidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
