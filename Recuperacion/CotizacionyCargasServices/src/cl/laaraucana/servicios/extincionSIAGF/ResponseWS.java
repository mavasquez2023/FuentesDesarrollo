/**
 * ResponseWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.extincionSIAGF;

public class ResponseWS  implements java.io.Serializable {
    private int CODIGO;

    private java.lang.String DESCRIPCION;

    private cl.laaraucana.servicios.extincionSIAGF.DataResponseWS[] RESPUESTA;

    public ResponseWS() {
    }

    public ResponseWS(
           int CODIGO,
           java.lang.String DESCRIPCION,
           cl.laaraucana.servicios.extincionSIAGF.DataResponseWS[] RESPUESTA) {
           this.CODIGO = CODIGO;
           this.DESCRIPCION = DESCRIPCION;
           this.RESPUESTA = RESPUESTA;
    }


    /**
     * Gets the CODIGO value for this ResponseWS.
     * 
     * @return CODIGO
     */
    public int getCODIGO() {
        return CODIGO;
    }


    /**
     * Sets the CODIGO value for this ResponseWS.
     * 
     * @param CODIGO
     */
    public void setCODIGO(int CODIGO) {
        this.CODIGO = CODIGO;
    }


    /**
     * Gets the DESCRIPCION value for this ResponseWS.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this ResponseWS.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the RESPUESTA value for this ResponseWS.
     * 
     * @return RESPUESTA
     */
    public cl.laaraucana.servicios.extincionSIAGF.DataResponseWS[] getRESPUESTA() {
        return RESPUESTA;
    }


    /**
     * Sets the RESPUESTA value for this ResponseWS.
     * 
     * @param RESPUESTA
     */
    public void setRESPUESTA(cl.laaraucana.servicios.extincionSIAGF.DataResponseWS[] RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    public cl.laaraucana.servicios.extincionSIAGF.DataResponseWS getRESPUESTA(int i) {
        return this.RESPUESTA[i];
    }

    public void setRESPUESTA(int i, cl.laaraucana.servicios.extincionSIAGF.DataResponseWS _value) {
        this.RESPUESTA[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResponseWS)) return false;
        ResponseWS other = (ResponseWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.CODIGO == other.getCODIGO() &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.RESPUESTA==null && other.getRESPUESTA()==null) || 
             (this.RESPUESTA!=null &&
              java.util.Arrays.equals(this.RESPUESTA, other.getRESPUESTA())));
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
        _hashCode += getCODIGO();
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getRESPUESTA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRESPUESTA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRESPUESTA(), i);
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
        new org.apache.axis.description.TypeDesc(ResponseWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/extincionSIAGF", "responseWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DESCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/extincionSIAGF", "dataResponseWS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
