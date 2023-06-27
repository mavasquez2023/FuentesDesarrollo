/**
 * LMEVerTramEmpResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package conector.lme.ws.cliente.operador;

public class LMEVerTramEmpResponse  implements java.io.Serializable {
    private java.lang.String estado;

    private java.lang.String gloEstado;

    private conector.lme.ws.cliente.operador.LicenciaCompletaType[] listaLicencias;

    public LMEVerTramEmpResponse() {
    }

    public LMEVerTramEmpResponse(
           java.lang.String estado,
           java.lang.String gloEstado,
           conector.lme.ws.cliente.operador.LicenciaCompletaType[] listaLicencias) {
           this.estado = estado;
           this.gloEstado = gloEstado;
           this.listaLicencias = listaLicencias;
    }


    /**
     * Gets the estado value for this LMEVerTramEmpResponse.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LMEVerTramEmpResponse.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this LMEVerTramEmpResponse.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this LMEVerTramEmpResponse.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the listaLicencias value for this LMEVerTramEmpResponse.
     * 
     * @return listaLicencias
     */
    public conector.lme.ws.cliente.operador.LicenciaCompletaType[] getListaLicencias() {
        return listaLicencias;
    }


    /**
     * Sets the listaLicencias value for this LMEVerTramEmpResponse.
     * 
     * @param listaLicencias
     */
    public void setListaLicencias(conector.lme.ws.cliente.operador.LicenciaCompletaType[] listaLicencias) {
        this.listaLicencias = listaLicencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEVerTramEmpResponse)) return false;
        LMEVerTramEmpResponse other = (LMEVerTramEmpResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.gloEstado==null && other.getGloEstado()==null) || 
             (this.gloEstado!=null &&
              this.gloEstado.equals(other.getGloEstado()))) &&
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
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
        new org.apache.axis.description.TypeDesc(LMEVerTramEmpResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerTramEmpResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaLicencias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaLicencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaCompletaType"));
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
