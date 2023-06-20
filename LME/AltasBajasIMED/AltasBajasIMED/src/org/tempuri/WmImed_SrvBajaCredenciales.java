/**
 * WmImed_SrvBajaCredenciales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class WmImed_SrvBajaCredenciales  implements java.io.Serializable {
    private int codigoEntidad;

    private java.lang.String rutUsuario;

    private int clave;

    public WmImed_SrvBajaCredenciales() {
    }

    public WmImed_SrvBajaCredenciales(
           int codigoEntidad,
           java.lang.String rutUsuario,
           int clave) {
           this.codigoEntidad = codigoEntidad;
           this.rutUsuario = rutUsuario;
           this.clave = clave;
    }


    /**
     * Gets the codigoEntidad value for this WmImed_SrvBajaCredenciales.
     * 
     * @return codigoEntidad
     */
    public int getCodigoEntidad() {
        return codigoEntidad;
    }


    /**
     * Sets the codigoEntidad value for this WmImed_SrvBajaCredenciales.
     * 
     * @param codigoEntidad
     */
    public void setCodigoEntidad(int codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }


    /**
     * Gets the rutUsuario value for this WmImed_SrvBajaCredenciales.
     * 
     * @return rutUsuario
     */
    public java.lang.String getRutUsuario() {
        return rutUsuario;
    }


    /**
     * Sets the rutUsuario value for this WmImed_SrvBajaCredenciales.
     * 
     * @param rutUsuario
     */
    public void setRutUsuario(java.lang.String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }


    /**
     * Gets the clave value for this WmImed_SrvBajaCredenciales.
     * 
     * @return clave
     */
    public int getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this WmImed_SrvBajaCredenciales.
     * 
     * @param clave
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WmImed_SrvBajaCredenciales)) return false;
        WmImed_SrvBajaCredenciales other = (WmImed_SrvBajaCredenciales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codigoEntidad == other.getCodigoEntidad() &&
            ((this.rutUsuario==null && other.getRutUsuario()==null) || 
             (this.rutUsuario!=null &&
              this.rutUsuario.equals(other.getRutUsuario()))) &&
            this.clave == other.getClave();
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
        _hashCode += getCodigoEntidad();
        if (getRutUsuario() != null) {
            _hashCode += getRutUsuario().hashCode();
        }
        _hashCode += getClave();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WmImed_SrvBajaCredenciales.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>wmImed_SrvBaja>Credenciales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEntidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoEntidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RutUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
