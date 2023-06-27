/**
 * LMEInfValCCAF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEInfValCCAF  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String tipoInstitucion;

    private java.lang.String codUsuario;

    private java.lang.String clave;

    private WsLMEInet_pkg.LicenciaSimpleType[] listaLicencias;

    public LMEInfValCCAF() {
    }

    public LMEInfValCCAF(
           java.lang.String codigoOperador,
           java.lang.String tipoInstitucion,
           java.lang.String codUsuario,
           java.lang.String clave,
           WsLMEInet_pkg.LicenciaSimpleType[] listaLicencias) {
           this.codigoOperador = codigoOperador;
           this.tipoInstitucion = tipoInstitucion;
           this.codUsuario = codUsuario;
           this.clave = clave;
           this.listaLicencias = listaLicencias;
    }


    /**
     * Gets the codigoOperador value for this LMEInfValCCAF.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEInfValCCAF.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the tipoInstitucion value for this LMEInfValCCAF.
     * 
     * @return tipoInstitucion
     */
    public java.lang.String getTipoInstitucion() {
        return tipoInstitucion;
    }


    /**
     * Sets the tipoInstitucion value for this LMEInfValCCAF.
     * 
     * @param tipoInstitucion
     */
    public void setTipoInstitucion(java.lang.String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }


    /**
     * Gets the codUsuario value for this LMEInfValCCAF.
     * 
     * @return codUsuario
     */
    public java.lang.String getCodUsuario() {
        return codUsuario;
    }


    /**
     * Sets the codUsuario value for this LMEInfValCCAF.
     * 
     * @param codUsuario
     */
    public void setCodUsuario(java.lang.String codUsuario) {
        this.codUsuario = codUsuario;
    }


    /**
     * Gets the clave value for this LMEInfValCCAF.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEInfValCCAF.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the listaLicencias value for this LMEInfValCCAF.
     * 
     * @return listaLicencias
     */
    public WsLMEInet_pkg.LicenciaSimpleType[] getListaLicencias() {
        return listaLicencias;
    }


    /**
     * Sets the listaLicencias value for this LMEInfValCCAF.
     * 
     * @param listaLicencias
     */
    public void setListaLicencias(WsLMEInet_pkg.LicenciaSimpleType[] listaLicencias) {
        this.listaLicencias = listaLicencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfValCCAF)) return false;
        LMEInfValCCAF other = (LMEInfValCCAF) obj;
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
            ((this.listaLicencias==null && other.getListaLicencias()==null) || 
             (this.listaLicencias!=null &&
              java.util.Arrays.equals(this.listaLicencias, other.getListaLicencias())));
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
        if (getListaLicencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaLicencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaLicencias(), i);
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
        new org.apache.axis.description.TypeDesc(LMEInfValCCAF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAF"));
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
        elemField.setFieldName("listaLicencias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaLicencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaSimpleType"));
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
