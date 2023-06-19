/**
 * RESPUESTAS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.wsdl.SDN122REQ;

public class RESPUESTAS  implements java.io.Serializable {
    private java.lang.String CODIGO_PREGUNTA;

    private java.lang.String CODIGO_RESPUESTA;

    public RESPUESTAS() {
    }

    public RESPUESTAS(
           java.lang.String CODIGO_PREGUNTA,
           java.lang.String CODIGO_RESPUESTA) {
           this.CODIGO_PREGUNTA = CODIGO_PREGUNTA;
           this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
    }


    /**
     * Gets the CODIGO_PREGUNTA value for this RESPUESTAS.
     * 
     * @return CODIGO_PREGUNTA
     */
    public java.lang.String getCODIGO_PREGUNTA() {
        return CODIGO_PREGUNTA;
    }


    /**
     * Sets the CODIGO_PREGUNTA value for this RESPUESTAS.
     * 
     * @param CODIGO_PREGUNTA
     */
    public void setCODIGO_PREGUNTA(java.lang.String CODIGO_PREGUNTA) {
        this.CODIGO_PREGUNTA = CODIGO_PREGUNTA;
    }


    /**
     * Gets the CODIGO_RESPUESTA value for this RESPUESTAS.
     * 
     * @return CODIGO_RESPUESTA
     */
    public java.lang.String getCODIGO_RESPUESTA() {
        return CODIGO_RESPUESTA;
    }


    /**
     * Sets the CODIGO_RESPUESTA value for this RESPUESTAS.
     * 
     * @param CODIGO_RESPUESTA
     */
    public void setCODIGO_RESPUESTA(java.lang.String CODIGO_RESPUESTA) {
        this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RESPUESTAS)) return false;
        RESPUESTAS other = (RESPUESTAS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODIGO_PREGUNTA==null && other.getCODIGO_PREGUNTA()==null) || 
             (this.CODIGO_PREGUNTA!=null &&
              this.CODIGO_PREGUNTA.equals(other.getCODIGO_PREGUNTA()))) &&
            ((this.CODIGO_RESPUESTA==null && other.getCODIGO_RESPUESTA()==null) || 
             (this.CODIGO_RESPUESTA!=null &&
              this.CODIGO_RESPUESTA.equals(other.getCODIGO_RESPUESTA())));
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
        if (getCODIGO_PREGUNTA() != null) {
            _hashCode += getCODIGO_PREGUNTA().hashCode();
        }
        if (getCODIGO_RESPUESTA() != null) {
            _hashCode += getCODIGO_RESPUESTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RESPUESTAS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122REQ", "RESPUESTAS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_PREGUNTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122REQ", "CODIGO_PREGUNTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122REQ", "CODIGO_RESPUESTA"));
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
