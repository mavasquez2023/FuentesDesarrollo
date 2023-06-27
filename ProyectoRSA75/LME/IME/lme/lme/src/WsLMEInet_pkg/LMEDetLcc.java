/**
 * LMEDetLcc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEDetLcc  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String tipoInstitucion;

    private java.lang.String codUsuario;

    private java.lang.String clave;

    private java.math.BigInteger numLicencia;

    private java.lang.String digLicencia;

    public LMEDetLcc() {
    }

    public LMEDetLcc(
           java.lang.String codigoOperador,
           java.lang.String tipoInstitucion,
           java.lang.String codUsuario,
           java.lang.String clave,
           java.math.BigInteger numLicencia,
           java.lang.String digLicencia) {
           this.codigoOperador = codigoOperador;
           this.tipoInstitucion = tipoInstitucion;
           this.codUsuario = codUsuario;
           this.clave = clave;
           this.numLicencia = numLicencia;
           this.digLicencia = digLicencia;
    }


    /**
     * Gets the codigoOperador value for this LMEDetLcc.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEDetLcc.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the tipoInstitucion value for this LMEDetLcc.
     * 
     * @return tipoInstitucion
     */
    public java.lang.String getTipoInstitucion() {
        return tipoInstitucion;
    }


    /**
     * Sets the tipoInstitucion value for this LMEDetLcc.
     * 
     * @param tipoInstitucion
     */
    public void setTipoInstitucion(java.lang.String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }


    /**
     * Gets the codUsuario value for this LMEDetLcc.
     * 
     * @return codUsuario
     */
    public java.lang.String getCodUsuario() {
        return codUsuario;
    }


    /**
     * Sets the codUsuario value for this LMEDetLcc.
     * 
     * @param codUsuario
     */
    public void setCodUsuario(java.lang.String codUsuario) {
        this.codUsuario = codUsuario;
    }


    /**
     * Gets the clave value for this LMEDetLcc.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEDetLcc.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the numLicencia value for this LMEDetLcc.
     * 
     * @return numLicencia
     */
    public java.math.BigInteger getNumLicencia() {
        return numLicencia;
    }


    /**
     * Sets the numLicencia value for this LMEDetLcc.
     * 
     * @param numLicencia
     */
    public void setNumLicencia(java.math.BigInteger numLicencia) {
        this.numLicencia = numLicencia;
    }


    /**
     * Gets the digLicencia value for this LMEDetLcc.
     * 
     * @return digLicencia
     */
    public java.lang.String getDigLicencia() {
        return digLicencia;
    }


    /**
     * Sets the digLicencia value for this LMEDetLcc.
     * 
     * @param digLicencia
     */
    public void setDigLicencia(java.lang.String digLicencia) {
        this.digLicencia = digLicencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEDetLcc)) return false;
        LMEDetLcc other = (LMEDetLcc) obj;
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
            ((this.numLicencia==null && other.getNumLicencia()==null) || 
             (this.numLicencia!=null &&
              this.numLicencia.equals(other.getNumLicencia()))) &&
            ((this.digLicencia==null && other.getDigLicencia()==null) || 
             (this.digLicencia!=null &&
              this.digLicencia.equals(other.getDigLicencia())));
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
        if (getNumLicencia() != null) {
            _hashCode += getNumLicencia().hashCode();
        }
        if (getDigLicencia() != null) {
            _hashCode += getDigLicencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEDetLcc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEDetLcc"));
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
        elemField.setFieldName("numLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NumLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DigLicencia"));
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
