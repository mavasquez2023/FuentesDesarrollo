/**
 * QueryBPStatusRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatusRol  implements java.io.Serializable {
    private java.lang.String rutBP;

    private java.lang.String rol;

    private java.util.Date fechaInicioRol;

    private java.util.Date fechaFinRol;

    public QueryBPStatusRol() {
    }

    public QueryBPStatusRol(
           java.lang.String rutBP,
           java.lang.String rol,
           java.util.Date fechaInicioRol,
           java.util.Date fechaFinRol) {
           this.rutBP = rutBP;
           this.rol = rol;
           this.fechaInicioRol = fechaInicioRol;
           this.fechaFinRol = fechaFinRol;
    }


    /**
     * Gets the rutBP value for this QueryBPStatusRol.
     * 
     * @return rutBP
     */
    public java.lang.String getRutBP() {
        return rutBP;
    }


    /**
     * Sets the rutBP value for this QueryBPStatusRol.
     * 
     * @param rutBP
     */
    public void setRutBP(java.lang.String rutBP) {
        this.rutBP = rutBP;
    }


    /**
     * Gets the rol value for this QueryBPStatusRol.
     * 
     * @return rol
     */
    public java.lang.String getRol() {
        return rol;
    }


    /**
     * Sets the rol value for this QueryBPStatusRol.
     * 
     * @param rol
     */
    public void setRol(java.lang.String rol) {
        this.rol = rol;
    }


    /**
     * Gets the fechaInicioRol value for this QueryBPStatusRol.
     * 
     * @return fechaInicioRol
     */
    public java.util.Date getFechaInicioRol() {
        return fechaInicioRol;
    }


    /**
     * Sets the fechaInicioRol value for this QueryBPStatusRol.
     * 
     * @param fechaInicioRol
     */
    public void setFechaInicioRol(java.util.Date fechaInicioRol) {
        this.fechaInicioRol = fechaInicioRol;
    }


    /**
     * Gets the fechaFinRol value for this QueryBPStatusRol.
     * 
     * @return fechaFinRol
     */
    public java.util.Date getFechaFinRol() {
        return fechaFinRol;
    }


    /**
     * Sets the fechaFinRol value for this QueryBPStatusRol.
     * 
     * @param fechaFinRol
     */
    public void setFechaFinRol(java.util.Date fechaFinRol) {
        this.fechaFinRol = fechaFinRol;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryBPStatusRol)) return false;
        QueryBPStatusRol other = (QueryBPStatusRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rutBP==null && other.getRutBP()==null) || 
             (this.rutBP!=null &&
              this.rutBP.equals(other.getRutBP()))) &&
            ((this.rol==null && other.getRol()==null) || 
             (this.rol!=null &&
              this.rol.equals(other.getRol()))) &&
            ((this.fechaInicioRol==null && other.getFechaInicioRol()==null) || 
             (this.fechaInicioRol!=null &&
              this.fechaInicioRol.equals(other.getFechaInicioRol()))) &&
            ((this.fechaFinRol==null && other.getFechaFinRol()==null) || 
             (this.fechaFinRol!=null &&
              this.fechaFinRol.equals(other.getFechaFinRol())));
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
        if (getRutBP() != null) {
            _hashCode += getRutBP().hashCode();
        }
        if (getRol() != null) {
            _hashCode += getRol().hashCode();
        }
        if (getFechaInicioRol() != null) {
            _hashCode += getFechaInicioRol().hashCode();
        }
        if (getFechaFinRol() != null) {
            _hashCode += getFechaFinRol().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryBPStatusRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatusRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutBP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RutBP"));
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
        elemField.setFieldName("fechaInicioRol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaInicioRol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinRol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaFinRol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
