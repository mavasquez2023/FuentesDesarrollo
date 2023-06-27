/**
 * DT_ConsultaEmpresaAfiliadoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class DT_ConsultaEmpresaAfiliadoResponse  implements java.io.Serializable {
    private java.math.BigInteger respuesta;

    private java.lang.String rut_Afiliado;

    private com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa[] detalle_Empresa;

    public DT_ConsultaEmpresaAfiliadoResponse() {
    }

    public DT_ConsultaEmpresaAfiliadoResponse(
           java.math.BigInteger respuesta,
           java.lang.String rut_Afiliado,
           com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa[] detalle_Empresa) {
           this.respuesta = respuesta;
           this.rut_Afiliado = rut_Afiliado;
           this.detalle_Empresa = detalle_Empresa;
    }


    /**
     * Gets the respuesta value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @return respuesta
     */
    public java.math.BigInteger getRespuesta() {
        return respuesta;
    }


    /**
     * Sets the respuesta value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @param respuesta
     */
    public void setRespuesta(java.math.BigInteger respuesta) {
        this.respuesta = respuesta;
    }


    /**
     * Gets the rut_Afiliado value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @return rut_Afiliado
     */
    public java.lang.String getRut_Afiliado() {
        return rut_Afiliado;
    }


    /**
     * Sets the rut_Afiliado value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @param rut_Afiliado
     */
    public void setRut_Afiliado(java.lang.String rut_Afiliado) {
        this.rut_Afiliado = rut_Afiliado;
    }


    /**
     * Gets the detalle_Empresa value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @return detalle_Empresa
     */
    public com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa[] getDetalle_Empresa() {
        return detalle_Empresa;
    }


    /**
     * Sets the detalle_Empresa value for this DT_ConsultaEmpresaAfiliadoResponse.
     * 
     * @param detalle_Empresa
     */
    public void setDetalle_Empresa(com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa[] detalle_Empresa) {
        this.detalle_Empresa = detalle_Empresa;
    }

    public com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa getDetalle_Empresa(int i) {
        return this.detalle_Empresa[i];
    }

    public void setDetalle_Empresa(int i, com.lautaro.xi.CRM.WEB_Mobile.DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa _value) {
        this.detalle_Empresa[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ConsultaEmpresaAfiliadoResponse)) return false;
        DT_ConsultaEmpresaAfiliadoResponse other = (DT_ConsultaEmpresaAfiliadoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.respuesta==null && other.getRespuesta()==null) || 
             (this.respuesta!=null &&
              this.respuesta.equals(other.getRespuesta()))) &&
            ((this.rut_Afiliado==null && other.getRut_Afiliado()==null) || 
             (this.rut_Afiliado!=null &&
              this.rut_Afiliado.equals(other.getRut_Afiliado()))) &&
            ((this.detalle_Empresa==null && other.getDetalle_Empresa()==null) || 
             (this.detalle_Empresa!=null &&
              java.util.Arrays.equals(this.detalle_Empresa, other.getDetalle_Empresa())));
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
        if (getRespuesta() != null) {
            _hashCode += getRespuesta().hashCode();
        }
        if (getRut_Afiliado() != null) {
            _hashCode += getRut_Afiliado().hashCode();
        }
        if (getDetalle_Empresa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalle_Empresa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalle_Empresa(), i);
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
        new org.apache.axis.description.TypeDesc(DT_ConsultaEmpresaAfiliadoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "DT_ConsultaEmpresaAfiliadoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rut_Afiliado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Rut_Afiliado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalle_Empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Detalle_Empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_ConsultaEmpresaAfiliadoResponse>Detalle_Empresa"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
