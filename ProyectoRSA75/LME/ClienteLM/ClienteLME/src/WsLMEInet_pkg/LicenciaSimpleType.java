/**
 * LicenciaSimpleType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LicenciaSimpleType  implements java.io.Serializable {
    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private java.lang.String estadoVal;

    public LicenciaSimpleType() {
    }

    public LicenciaSimpleType(
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           java.lang.String estadoVal) {
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.estadoVal = estadoVal;
    }


    /**
     * Gets the idLicencia value for this LicenciaSimpleType.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this LicenciaSimpleType.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this LicenciaSimpleType.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this LicenciaSimpleType.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the estadoVal value for this LicenciaSimpleType.
     * 
     * @return estadoVal
     */
    public java.lang.String getEstadoVal() {
        return estadoVal;
    }


    /**
     * Sets the estadoVal value for this LicenciaSimpleType.
     * 
     * @param estadoVal
     */
    public void setEstadoVal(java.lang.String estadoVal) {
        this.estadoVal = estadoVal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LicenciaSimpleType)) return false;
        LicenciaSimpleType other = (LicenciaSimpleType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idLicencia==null && other.getIdLicencia()==null) || 
             (this.idLicencia!=null &&
              this.idLicencia.equals(other.getIdLicencia()))) &&
            ((this.dvLicencia==null && other.getDvLicencia()==null) || 
             (this.dvLicencia!=null &&
              this.dvLicencia.equals(other.getDvLicencia()))) &&
            ((this.estadoVal==null && other.getEstadoVal()==null) || 
             (this.estadoVal!=null &&
              this.estadoVal.equals(other.getEstadoVal())));
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
        if (getIdLicencia() != null) {
            _hashCode += getIdLicencia().hashCode();
        }
        if (getDvLicencia() != null) {
            _hashCode += getDvLicencia().hashCode();
        }
        if (getEstadoVal() != null) {
            _hashCode += getEstadoVal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LicenciaSimpleType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaSimpleType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dvLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DvLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoVal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EstadoVal"));
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
