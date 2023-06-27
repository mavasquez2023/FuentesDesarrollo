/**
 * LicenciaCompletaType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LicenciaCompletaType  implements java.io.Serializable {
    private java.math.BigInteger numLicencia;

    private java.lang.String digLicencia;

    private byte[] datosZonaA;

    public LicenciaCompletaType() {
    }

    public LicenciaCompletaType(
           java.math.BigInteger numLicencia,
           java.lang.String digLicencia,
           byte[] datosZonaA) {
           this.numLicencia = numLicencia;
           this.digLicencia = digLicencia;
           this.datosZonaA = datosZonaA;
    }


    /**
     * Gets the numLicencia value for this LicenciaCompletaType.
     * 
     * @return numLicencia
     */
    public java.math.BigInteger getNumLicencia() {
        return numLicencia;
    }


    /**
     * Sets the numLicencia value for this LicenciaCompletaType.
     * 
     * @param numLicencia
     */
    public void setNumLicencia(java.math.BigInteger numLicencia) {
        this.numLicencia = numLicencia;
    }


    /**
     * Gets the digLicencia value for this LicenciaCompletaType.
     * 
     * @return digLicencia
     */
    public java.lang.String getDigLicencia() {
        return digLicencia;
    }


    /**
     * Sets the digLicencia value for this LicenciaCompletaType.
     * 
     * @param digLicencia
     */
    public void setDigLicencia(java.lang.String digLicencia) {
        this.digLicencia = digLicencia;
    }


    /**
     * Gets the datosZonaA value for this LicenciaCompletaType.
     * 
     * @return datosZonaA
     */
    public byte[] getDatosZonaA() {
        return datosZonaA;
    }


    /**
     * Sets the datosZonaA value for this LicenciaCompletaType.
     * 
     * @param datosZonaA
     */
    public void setDatosZonaA(byte[] datosZonaA) {
        this.datosZonaA = datosZonaA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LicenciaCompletaType)) return false;
        LicenciaCompletaType other = (LicenciaCompletaType) obj;
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
            ((this.datosZonaA==null && other.getDatosZonaA()==null) || 
             (this.datosZonaA!=null &&
              java.util.Arrays.equals(this.datosZonaA, other.getDatosZonaA())));
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
        if (getDatosZonaA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosZonaA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosZonaA(), i);
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
        new org.apache.axis.description.TypeDesc(LicenciaCompletaType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaCompletaType"));
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
        elemField.setFieldName("datosZonaA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DatosZonaA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
