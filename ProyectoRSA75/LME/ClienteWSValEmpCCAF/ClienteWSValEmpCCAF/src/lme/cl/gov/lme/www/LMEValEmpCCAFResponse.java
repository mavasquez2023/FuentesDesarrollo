/**
 * LMEValEmpCCAFResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package lme.cl.gov.lme.www;

public class LMEValEmpCCAFResponse  implements java.io.Serializable {
    private java.lang.Short estado;

    private java.lang.String gloEstado;

    private lme.cl.gov.lme.www.Empleador[] listaEmpleadores;

    public LMEValEmpCCAFResponse() {
    }

    public LMEValEmpCCAFResponse(
           java.lang.Short estado,
           java.lang.String gloEstado,
           lme.cl.gov.lme.www.Empleador[] listaEmpleadores) {
           this.estado = estado;
           this.gloEstado = gloEstado;
           this.listaEmpleadores = listaEmpleadores;
    }


    /**
     * Gets the estado value for this LMEValEmpCCAFResponse.
     * 
     * @return estado
     */
    public java.lang.Short getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LMEValEmpCCAFResponse.
     * 
     * @param estado
     */
    public void setEstado(java.lang.Short estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this LMEValEmpCCAFResponse.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this LMEValEmpCCAFResponse.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the listaEmpleadores value for this LMEValEmpCCAFResponse.
     * 
     * @return listaEmpleadores
     */
    public lme.cl.gov.lme.www.Empleador[] getListaEmpleadores() {
        return listaEmpleadores;
    }


    /**
     * Sets the listaEmpleadores value for this LMEValEmpCCAFResponse.
     * 
     * @param listaEmpleadores
     */
    public void setListaEmpleadores(lme.cl.gov.lme.www.Empleador[] listaEmpleadores) {
        this.listaEmpleadores = listaEmpleadores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEValEmpCCAFResponse)) return false;
        LMEValEmpCCAFResponse other = (LMEValEmpCCAFResponse) obj;
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
            ((this.listaEmpleadores==null && other.getListaEmpleadores()==null) || 
             (this.listaEmpleadores!=null &&
              java.util.Arrays.equals(this.listaEmpleadores, other.getListaEmpleadores())));
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
        if (getListaEmpleadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaEmpleadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaEmpleadores(), i);
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
        new org.apache.axis.description.TypeDesc(LMEValEmpCCAFResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "LMEValEmpCCAFResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaEmpleadores");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ListaEmpleadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "Empleador"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "Empleador"));
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
