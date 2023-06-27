/**
 * DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa  implements java.io.Serializable {
    private java.lang.String rut_Empresa;

    private java.lang.String DV_Empresa;

    private java.lang.String razon_Social;

    private java.lang.String fecha_Afiliacion;

    private java.lang.String fecha_Ultima_Cotiza;

    public DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa() {
    }

    public DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa(
           java.lang.String rut_Empresa,
           java.lang.String DV_Empresa,
           java.lang.String razon_Social,
           java.lang.String fecha_Afiliacion,
           java.lang.String fecha_Ultima_Cotiza) {
           this.rut_Empresa = rut_Empresa;
           this.DV_Empresa = DV_Empresa;
           this.razon_Social = razon_Social;
           this.fecha_Afiliacion = fecha_Afiliacion;
           this.fecha_Ultima_Cotiza = fecha_Ultima_Cotiza;
    }


    /**
     * Gets the rut_Empresa value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @return rut_Empresa
     */
    public java.lang.String getRut_Empresa() {
        return rut_Empresa;
    }


    /**
     * Sets the rut_Empresa value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @param rut_Empresa
     */
    public void setRut_Empresa(java.lang.String rut_Empresa) {
        this.rut_Empresa = rut_Empresa;
    }


    /**
     * Gets the DV_Empresa value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @return DV_Empresa
     */
    public java.lang.String getDV_Empresa() {
        return DV_Empresa;
    }


    /**
     * Sets the DV_Empresa value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @param DV_Empresa
     */
    public void setDV_Empresa(java.lang.String DV_Empresa) {
        this.DV_Empresa = DV_Empresa;
    }


    /**
     * Gets the razon_Social value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @return razon_Social
     */
    public java.lang.String getRazon_Social() {
        return razon_Social;
    }


    /**
     * Sets the razon_Social value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @param razon_Social
     */
    public void setRazon_Social(java.lang.String razon_Social) {
        this.razon_Social = razon_Social;
    }


    /**
     * Gets the fecha_Afiliacion value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @return fecha_Afiliacion
     */
    public java.lang.String getFecha_Afiliacion() {
        return fecha_Afiliacion;
    }


    /**
     * Sets the fecha_Afiliacion value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @param fecha_Afiliacion
     */
    public void setFecha_Afiliacion(java.lang.String fecha_Afiliacion) {
        this.fecha_Afiliacion = fecha_Afiliacion;
    }


    /**
     * Gets the fecha_Ultima_Cotiza value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @return fecha_Ultima_Cotiza
     */
    public java.lang.String getFecha_Ultima_Cotiza() {
        return fecha_Ultima_Cotiza;
    }


    /**
     * Sets the fecha_Ultima_Cotiza value for this DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.
     * 
     * @param fecha_Ultima_Cotiza
     */
    public void setFecha_Ultima_Cotiza(java.lang.String fecha_Ultima_Cotiza) {
        this.fecha_Ultima_Cotiza = fecha_Ultima_Cotiza;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa)) return false;
        DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa other = (DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rut_Empresa==null && other.getRut_Empresa()==null) || 
             (this.rut_Empresa!=null &&
              this.rut_Empresa.equals(other.getRut_Empresa()))) &&
            ((this.DV_Empresa==null && other.getDV_Empresa()==null) || 
             (this.DV_Empresa!=null &&
              this.DV_Empresa.equals(other.getDV_Empresa()))) &&
            ((this.razon_Social==null && other.getRazon_Social()==null) || 
             (this.razon_Social!=null &&
              this.razon_Social.equals(other.getRazon_Social()))) &&
            ((this.fecha_Afiliacion==null && other.getFecha_Afiliacion()==null) || 
             (this.fecha_Afiliacion!=null &&
              this.fecha_Afiliacion.equals(other.getFecha_Afiliacion()))) &&
            ((this.fecha_Ultima_Cotiza==null && other.getFecha_Ultima_Cotiza()==null) || 
             (this.fecha_Ultima_Cotiza!=null &&
              this.fecha_Ultima_Cotiza.equals(other.getFecha_Ultima_Cotiza())));
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
        if (getRut_Empresa() != null) {
            _hashCode += getRut_Empresa().hashCode();
        }
        if (getDV_Empresa() != null) {
            _hashCode += getDV_Empresa().hashCode();
        }
        if (getRazon_Social() != null) {
            _hashCode += getRazon_Social().hashCode();
        }
        if (getFecha_Afiliacion() != null) {
            _hashCode += getFecha_Afiliacion().hashCode();
        }
        if (getFecha_Ultima_Cotiza() != null) {
            _hashCode += getFecha_Ultima_Cotiza().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ConsultaEmpresaAfiliadoResponseDetalle_Empresa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", ">DT_ConsultaEmpresaAfiliadoResponse>Detalle_Empresa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rut_Empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Rut_Empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DV_Empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DV_Empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razon_Social");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Razon_Social"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_Afiliacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Fecha_Afiliacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_Ultima_Cotiza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Fecha_Ultima_Cotiza"));
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
