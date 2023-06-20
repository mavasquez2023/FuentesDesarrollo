/**
 * UsuariosLDAP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.usuariosLDAP;

public class UsuariosLDAP  implements java.io.Serializable {
    private int CODIGO_RESPUESTA;

    private int ESTADO;

    public UsuariosLDAP() {
    }

    public UsuariosLDAP(
           int CODIGO_RESPUESTA,
           int ESTADO) {
           this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
           this.ESTADO = ESTADO;
    }


    /**
     * Gets the CODIGO_RESPUESTA value for this UsuariosLDAP.
     * 
     * @return CODIGO_RESPUESTA
     */
    public int getCODIGO_RESPUESTA() {
        return CODIGO_RESPUESTA;
    }


    /**
     * Sets the CODIGO_RESPUESTA value for this UsuariosLDAP.
     * 
     * @param CODIGO_RESPUESTA
     */
    public void setCODIGO_RESPUESTA(int CODIGO_RESPUESTA) {
        this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
    }


    /**
     * Gets the ESTADO value for this UsuariosLDAP.
     * 
     * @return ESTADO
     */
    public int getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this UsuariosLDAP.
     * 
     * @param ESTADO
     */
    public void setESTADO(int ESTADO) {
        this.ESTADO = ESTADO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsuariosLDAP)) return false;
        UsuariosLDAP other = (UsuariosLDAP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.CODIGO_RESPUESTA == other.getCODIGO_RESPUESTA() &&
            this.ESTADO == other.getESTADO();
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
        _hashCode += getCODIGO_RESPUESTA();
        _hashCode += getESTADO();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UsuariosLDAP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/usuariosLDAP", "usuariosLDAP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO"));
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
