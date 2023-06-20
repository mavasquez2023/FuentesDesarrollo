/**
 * AUTENTICACION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class AUTENTICACION  implements java.io.Serializable {
    private java.lang.String USUARIO;

    private java.lang.String CLAVE;

    public AUTENTICACION() {
    }

    public AUTENTICACION(
           java.lang.String USUARIO,
           java.lang.String CLAVE) {
           this.USUARIO = USUARIO;
           this.CLAVE = CLAVE;
    }


    /**
     * Gets the USUARIO value for this AUTENTICACION.
     * 
     * @return USUARIO
     */
    public java.lang.String getUSUARIO() {
        return USUARIO;
    }


    /**
     * Sets the USUARIO value for this AUTENTICACION.
     * 
     * @param USUARIO
     */
    public void setUSUARIO(java.lang.String USUARIO) {
        this.USUARIO = USUARIO;
    }


    /**
     * Gets the CLAVE value for this AUTENTICACION.
     * 
     * @return CLAVE
     */
    public java.lang.String getCLAVE() {
        return CLAVE;
    }


    /**
     * Sets the CLAVE value for this AUTENTICACION.
     * 
     * @param CLAVE
     */
    public void setCLAVE(java.lang.String CLAVE) {
        this.CLAVE = CLAVE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AUTENTICACION)) return false;
        AUTENTICACION other = (AUTENTICACION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.USUARIO==null && other.getUSUARIO()==null) || 
             (this.USUARIO!=null &&
              this.USUARIO.equals(other.getUSUARIO()))) &&
            ((this.CLAVE==null && other.getCLAVE()==null) || 
             (this.CLAVE!=null &&
              this.CLAVE.equals(other.getCLAVE())));
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
        if (getUSUARIO() != null) {
            _hashCode += getUSUARIO().hashCode();
        }
        if (getCLAVE() != null) {
            _hashCode += getCLAVE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AUTENTICACION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "AUTENTICACION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USUARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLAVE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLAVE"));
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
