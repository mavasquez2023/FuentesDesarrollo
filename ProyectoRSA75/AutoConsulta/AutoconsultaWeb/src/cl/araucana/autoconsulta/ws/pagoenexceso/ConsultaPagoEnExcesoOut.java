/**
 * ConsultaPagoEnExcesoOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.araucana.autoconsulta.ws.pagoenexceso;

public class ConsultaPagoEnExcesoOut  implements java.io.Serializable {
    private java.lang.String codRespuesta;

    private java.lang.String mensaje;

    private cl.araucana.autoconsulta.ws.pagoenexceso.PagoEnExcesoDTO pagoEnExceso;

    public ConsultaPagoEnExcesoOut() {
    }

    public ConsultaPagoEnExcesoOut(
           java.lang.String codRespuesta,
           java.lang.String mensaje,
           cl.araucana.autoconsulta.ws.pagoenexceso.PagoEnExcesoDTO pagoEnExceso) {
           this.codRespuesta = codRespuesta;
           this.mensaje = mensaje;
           this.pagoEnExceso = pagoEnExceso;
    }


    /**
     * Gets the codRespuesta value for this ConsultaPagoEnExcesoOut.
     * 
     * @return codRespuesta
     */
    public java.lang.String getCodRespuesta() {
        return codRespuesta;
    }


    /**
     * Sets the codRespuesta value for this ConsultaPagoEnExcesoOut.
     * 
     * @param codRespuesta
     */
    public void setCodRespuesta(java.lang.String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }


    /**
     * Gets the mensaje value for this ConsultaPagoEnExcesoOut.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ConsultaPagoEnExcesoOut.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the pagoEnExceso value for this ConsultaPagoEnExcesoOut.
     * 
     * @return pagoEnExceso
     */
    public cl.araucana.autoconsulta.ws.pagoenexceso.PagoEnExcesoDTO getPagoEnExceso() {
        return pagoEnExceso;
    }


    /**
     * Sets the pagoEnExceso value for this ConsultaPagoEnExcesoOut.
     * 
     * @param pagoEnExceso
     */
    public void setPagoEnExceso(cl.araucana.autoconsulta.ws.pagoenexceso.PagoEnExcesoDTO pagoEnExceso) {
        this.pagoEnExceso = pagoEnExceso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaPagoEnExcesoOut)) return false;
        ConsultaPagoEnExcesoOut other = (ConsultaPagoEnExcesoOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codRespuesta==null && other.getCodRespuesta()==null) || 
             (this.codRespuesta!=null &&
              this.codRespuesta.equals(other.getCodRespuesta()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            ((this.pagoEnExceso==null && other.getPagoEnExceso()==null) || 
             (this.pagoEnExceso!=null &&
              this.pagoEnExceso.equals(other.getPagoEnExceso())));
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
        if (getCodRespuesta() != null) {
            _hashCode += getCodRespuesta().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        if (getPagoEnExceso() != null) {
            _hashCode += getPagoEnExceso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaPagoEnExcesoOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://delegate.pagoenexceso.laaraucana.cl/", "consultaPagoEnExcesoOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pagoEnExceso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pagoEnExceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://delegate.pagoenexceso.laaraucana.cl/", "pagoEnExcesoDTO"));
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
