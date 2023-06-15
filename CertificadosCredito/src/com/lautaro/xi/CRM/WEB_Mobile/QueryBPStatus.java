/**
 * QueryBPStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatus  implements java.io.Serializable {
    private java.lang.String rut;

    private java.lang.String nombreCompleto;

    private java.lang.String fechaAfiliacion;

    private java.lang.String estadoAfiliacion;

    private java.lang.String rol;

    private java.lang.String razonSocial;

    public QueryBPStatus() {
    }

    public QueryBPStatus(
           java.lang.String rut,
           java.lang.String nombreCompleto,
           java.lang.String fechaAfiliacion,
           java.lang.String estadoAfiliacion,
           java.lang.String rol,
           java.lang.String razonSocial) {
           this.rut = rut;
           this.nombreCompleto = nombreCompleto;
           this.fechaAfiliacion = fechaAfiliacion;
           this.estadoAfiliacion = estadoAfiliacion;
           this.rol = rol;
           this.razonSocial = razonSocial;
    }


    /**
     * Gets the rut value for this QueryBPStatus.
     * 
     * @return rut
     */
    public java.lang.String getRut() {
        return rut;
    }


    /**
     * Sets the rut value for this QueryBPStatus.
     * 
     * @param rut
     */
    public void setRut(java.lang.String rut) {
        this.rut = rut;
    }


    /**
     * Gets the nombreCompleto value for this QueryBPStatus.
     * 
     * @return nombreCompleto
     */
    public java.lang.String getNombreCompleto() {
        return nombreCompleto;
    }


    /**
     * Sets the nombreCompleto value for this QueryBPStatus.
     * 
     * @param nombreCompleto
     */
    public void setNombreCompleto(java.lang.String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    /**
     * Gets the fechaAfiliacion value for this QueryBPStatus.
     * 
     * @return fechaAfiliacion
     */
    public java.lang.String getFechaAfiliacion() {
        return fechaAfiliacion;
    }


    /**
     * Sets the fechaAfiliacion value for this QueryBPStatus.
     * 
     * @param fechaAfiliacion
     */
    public void setFechaAfiliacion(java.lang.String fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }


    /**
     * Gets the estadoAfiliacion value for this QueryBPStatus.
     * 
     * @return estadoAfiliacion
     */
    public java.lang.String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }


    /**
     * Sets the estadoAfiliacion value for this QueryBPStatus.
     * 
     * @param estadoAfiliacion
     */
    public void setEstadoAfiliacion(java.lang.String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }


    /**
     * Gets the rol value for this QueryBPStatus.
     * 
     * @return rol
     */
    public java.lang.String getRol() {
        return rol;
    }


    /**
     * Sets the rol value for this QueryBPStatus.
     * 
     * @param rol
     */
    public void setRol(java.lang.String rol) {
        this.rol = rol;
    }


    /**
     * Gets the razonSocial value for this QueryBPStatus.
     * 
     * @return razonSocial
     */
    public java.lang.String getRazonSocial() {
        return razonSocial;
    }


    /**
     * Sets the razonSocial value for this QueryBPStatus.
     * 
     * @param razonSocial
     */
    public void setRazonSocial(java.lang.String razonSocial) {
        this.razonSocial = razonSocial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryBPStatus)) return false;
        QueryBPStatus other = (QueryBPStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rut==null && other.getRut()==null) || 
             (this.rut!=null &&
              this.rut.equals(other.getRut()))) &&
            ((this.nombreCompleto==null && other.getNombreCompleto()==null) || 
             (this.nombreCompleto!=null &&
              this.nombreCompleto.equals(other.getNombreCompleto()))) &&
            ((this.fechaAfiliacion==null && other.getFechaAfiliacion()==null) || 
             (this.fechaAfiliacion!=null &&
              this.fechaAfiliacion.equals(other.getFechaAfiliacion()))) &&
            ((this.estadoAfiliacion==null && other.getEstadoAfiliacion()==null) || 
             (this.estadoAfiliacion!=null &&
              this.estadoAfiliacion.equals(other.getEstadoAfiliacion()))) &&
            ((this.rol==null && other.getRol()==null) || 
             (this.rol!=null &&
              this.rol.equals(other.getRol()))) &&
            ((this.razonSocial==null && other.getRazonSocial()==null) || 
             (this.razonSocial!=null &&
              this.razonSocial.equals(other.getRazonSocial())));
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
        if (getRut() != null) {
            _hashCode += getRut().hashCode();
        }
        if (getNombreCompleto() != null) {
            _hashCode += getNombreCompleto().hashCode();
        }
        if (getFechaAfiliacion() != null) {
            _hashCode += getFechaAfiliacion().hashCode();
        }
        if (getEstadoAfiliacion() != null) {
            _hashCode += getEstadoAfiliacion().hashCode();
        }
        if (getRol() != null) {
            _hashCode += getRol().hashCode();
        }
        if (getRazonSocial() != null) {
            _hashCode += getRazonSocial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryBPStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Rut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCompleto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NombreCompleto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAfiliacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaAfiliacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoAfiliacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EstadoAfiliacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Rol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razonSocial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RazonSocial"));
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
