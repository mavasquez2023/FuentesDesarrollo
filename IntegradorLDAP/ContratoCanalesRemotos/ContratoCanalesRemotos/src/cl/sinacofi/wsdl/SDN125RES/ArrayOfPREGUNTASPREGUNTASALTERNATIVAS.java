/**
 * ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.wsdl.SDN125RES;

public class ArrayOfPREGUNTASPREGUNTASALTERNATIVAS  implements java.io.Serializable {
    private java.lang.String CODIGO_RESPUESTA;

    private java.lang.String RESPUESTA;

    public ArrayOfPREGUNTASPREGUNTASALTERNATIVAS() {
    }

    public ArrayOfPREGUNTASPREGUNTASALTERNATIVAS(
           java.lang.String CODIGO_RESPUESTA,
           java.lang.String RESPUESTA) {
           this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
           this.RESPUESTA = RESPUESTA;
    }


    /**
     * Gets the CODIGO_RESPUESTA value for this ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.
     * 
     * @return CODIGO_RESPUESTA
     */
    public java.lang.String getCODIGO_RESPUESTA() {
        return CODIGO_RESPUESTA;
    }


    /**
     * Sets the CODIGO_RESPUESTA value for this ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.
     * 
     * @param CODIGO_RESPUESTA
     */
    public void setCODIGO_RESPUESTA(java.lang.String CODIGO_RESPUESTA) {
        this.CODIGO_RESPUESTA = CODIGO_RESPUESTA;
    }


    /**
     * Gets the RESPUESTA value for this ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.
     * 
     * @return RESPUESTA
     */
    public java.lang.String getRESPUESTA() {
        return RESPUESTA;
    }


    /**
     * Sets the RESPUESTA value for this ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.
     * 
     * @param RESPUESTA
     */
    public void setRESPUESTA(java.lang.String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfPREGUNTASPREGUNTASALTERNATIVAS)) return false;
        ArrayOfPREGUNTASPREGUNTASALTERNATIVAS other = (ArrayOfPREGUNTASPREGUNTASALTERNATIVAS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODIGO_RESPUESTA==null && other.getCODIGO_RESPUESTA()==null) || 
             (this.CODIGO_RESPUESTA!=null &&
              this.CODIGO_RESPUESTA.equals(other.getCODIGO_RESPUESTA()))) &&
            ((this.RESPUESTA==null && other.getRESPUESTA()==null) || 
             (this.RESPUESTA!=null &&
              this.RESPUESTA.equals(other.getRESPUESTA())));
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
        if (getCODIGO_RESPUESTA() != null) {
            _hashCode += getCODIGO_RESPUESTA().hashCode();
        }
        if (getRESPUESTA() != null) {
            _hashCode += getRESPUESTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArrayOfPREGUNTASPREGUNTASALTERNATIVAS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", ">>ArrayOfPREGUNTAS>PREGUNTAS>ALTERNATIVAS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "CODIGO_RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "RESPUESTA"));
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
