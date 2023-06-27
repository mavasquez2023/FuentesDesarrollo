/**
 * LicenciaType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package conector.lme.ws.cliente.operador;

public class LicenciaType  implements java.io.Serializable {
    private java.math.BigInteger numLicencia;

    private java.lang.String digLicencia;

    private java.math.BigInteger estado;

    private java.util.Calendar fecha;

    public LicenciaType() {
    }

    public LicenciaType(
           java.math.BigInteger numLicencia,
           java.lang.String digLicencia,
           java.math.BigInteger estado,
           java.util.Calendar fecha) {
           this.numLicencia = numLicencia;
           this.digLicencia = digLicencia;
           this.estado = estado;
           this.fecha = fecha;
    }


    /**
     * Gets the numLicencia value for this LicenciaType.
     * 
     * @return numLicencia
     */
    public java.math.BigInteger getNumLicencia() {
        return numLicencia;
    }


    /**
     * Sets the numLicencia value for this LicenciaType.
     * 
     * @param numLicencia
     */
    public void setNumLicencia(java.math.BigInteger numLicencia) {
        this.numLicencia = numLicencia;
    }


    /**
     * Gets the digLicencia value for this LicenciaType.
     * 
     * @return digLicencia
     */
    public java.lang.String getDigLicencia() {
        return digLicencia;
    }


    /**
     * Sets the digLicencia value for this LicenciaType.
     * 
     * @param digLicencia
     */
    public void setDigLicencia(java.lang.String digLicencia) {
        this.digLicencia = digLicencia;
    }


    /**
     * Gets the estado value for this LicenciaType.
     * 
     * @return estado
     */
    public java.math.BigInteger getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LicenciaType.
     * 
     * @param estado
     */
    public void setEstado(java.math.BigInteger estado) {
        this.estado = estado;
    }


    /**
     * Gets the fecha value for this LicenciaType.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this LicenciaType.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LicenciaType)) return false;
        LicenciaType other = (LicenciaType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numLicencia==null && other.getNumLicencia()==null) || 
             (this.numLicencia!=null &&
              this.numLicencia.equals(other.getNumLicencia()))) &&
            ((this.digLicencia==null && other.getDigLicencia()==null) || 
             (this.digLicencia!=null &&
              this.digLicencia.equals(other.getDigLicencia()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha())));
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
        if (getNumLicencia() != null) {
            _hashCode += getNumLicencia().hashCode();
        }
        if (getDigLicencia() != null) {
            _hashCode += getDigLicencia().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LicenciaType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
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
