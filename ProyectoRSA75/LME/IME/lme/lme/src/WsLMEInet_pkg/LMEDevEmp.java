/**
 * LMEDevEmp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEDevEmp  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String tipoInstitucion;

    private java.lang.String codUsuario;

    private java.lang.String clave;

    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private java.math.BigInteger motivoDevolucion;

    private java.util.Calendar fecDevolucion;

    public LMEDevEmp() {
    }

    public LMEDevEmp(
           java.lang.String codigoOperador,
           java.lang.String tipoInstitucion,
           java.lang.String codUsuario,
           java.lang.String clave,
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           java.math.BigInteger motivoDevolucion,
           java.util.Calendar fecDevolucion) {
           this.codigoOperador = codigoOperador;
           this.tipoInstitucion = tipoInstitucion;
           this.codUsuario = codUsuario;
           this.clave = clave;
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.motivoDevolucion = motivoDevolucion;
           this.fecDevolucion = fecDevolucion;
    }


    /**
     * Gets the codigoOperador value for this LMEDevEmp.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEDevEmp.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the tipoInstitucion value for this LMEDevEmp.
     * 
     * @return tipoInstitucion
     */
    public java.lang.String getTipoInstitucion() {
        return tipoInstitucion;
    }


    /**
     * Sets the tipoInstitucion value for this LMEDevEmp.
     * 
     * @param tipoInstitucion
     */
    public void setTipoInstitucion(java.lang.String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }


    /**
     * Gets the codUsuario value for this LMEDevEmp.
     * 
     * @return codUsuario
     */
    public java.lang.String getCodUsuario() {
        return codUsuario;
    }


    /**
     * Sets the codUsuario value for this LMEDevEmp.
     * 
     * @param codUsuario
     */
    public void setCodUsuario(java.lang.String codUsuario) {
        this.codUsuario = codUsuario;
    }


    /**
     * Gets the clave value for this LMEDevEmp.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEDevEmp.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the idLicencia value for this LMEDevEmp.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this LMEDevEmp.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this LMEDevEmp.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this LMEDevEmp.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the motivoDevolucion value for this LMEDevEmp.
     * 
     * @return motivoDevolucion
     */
    public java.math.BigInteger getMotivoDevolucion() {
        return motivoDevolucion;
    }


    /**
     * Sets the motivoDevolucion value for this LMEDevEmp.
     * 
     * @param motivoDevolucion
     */
    public void setMotivoDevolucion(java.math.BigInteger motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }


    /**
     * Gets the fecDevolucion value for this LMEDevEmp.
     * 
     * @return fecDevolucion
     */
    public java.util.Calendar getFecDevolucion() {
        return fecDevolucion;
    }


    /**
     * Sets the fecDevolucion value for this LMEDevEmp.
     * 
     * @param fecDevolucion
     */
    public void setFecDevolucion(java.util.Calendar fecDevolucion) {
        this.fecDevolucion = fecDevolucion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEDevEmp)) return false;
        LMEDevEmp other = (LMEDevEmp) obj;
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
            ((this.motivoDevolucion==null && other.getMotivoDevolucion()==null) || 
             (this.motivoDevolucion!=null &&
              this.motivoDevolucion.equals(other.getMotivoDevolucion()))) &&
            ((this.fecDevolucion==null && other.getFecDevolucion()==null) || 
             (this.fecDevolucion!=null &&
              this.fecDevolucion.equals(other.getFecDevolucion())));
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
        if (getMotivoDevolucion() != null) {
            _hashCode += getMotivoDevolucion().hashCode();
        }
        if (getFecDevolucion() != null) {
            _hashCode += getFecDevolucion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEDevEmp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDevEmp"));
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
        elemField.setFieldName("motivoDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MotivoDevolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FecDevolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
