/**
 * RespuestaInsertMandato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class RespuestaInsertMandato  implements java.io.Serializable {
    private java.lang.String ID_MANDATO;

    private cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA;

    public RespuestaInsertMandato() {
    }

    public RespuestaInsertMandato(
           java.lang.String ID_MANDATO,
           cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA) {
           this.ID_MANDATO = ID_MANDATO;
           this.LOG_RESPUESTA = LOG_RESPUESTA;
    }


    /**
     * Gets the ID_MANDATO value for this RespuestaInsertMandato.
     * 
     * @return ID_MANDATO
     */
    public java.lang.String getID_MANDATO() {
        return ID_MANDATO;
    }


    /**
     * Sets the ID_MANDATO value for this RespuestaInsertMandato.
     * 
     * @param ID_MANDATO
     */
    public void setID_MANDATO(java.lang.String ID_MANDATO) {
        this.ID_MANDATO = ID_MANDATO;
    }


    /**
     * Gets the LOG_RESPUESTA value for this RespuestaInsertMandato.
     * 
     * @return LOG_RESPUESTA
     */
    public cl.laaraucana.servicio.mandato.LOG_RESPUESTA getLOG_RESPUESTA() {
        return LOG_RESPUESTA;
    }


    /**
     * Sets the LOG_RESPUESTA value for this RespuestaInsertMandato.
     * 
     * @param LOG_RESPUESTA
     */
    public void setLOG_RESPUESTA(cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA) {
        this.LOG_RESPUESTA = LOG_RESPUESTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaInsertMandato)) return false;
        RespuestaInsertMandato other = (RespuestaInsertMandato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID_MANDATO==null && other.getID_MANDATO()==null) || 
             (this.ID_MANDATO!=null &&
              this.ID_MANDATO.equals(other.getID_MANDATO()))) &&
            ((this.LOG_RESPUESTA==null && other.getLOG_RESPUESTA()==null) || 
             (this.LOG_RESPUESTA!=null &&
              this.LOG_RESPUESTA.equals(other.getLOG_RESPUESTA())));
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
        if (getID_MANDATO() != null) {
            _hashCode += getID_MANDATO().hashCode();
        }
        if (getLOG_RESPUESTA() != null) {
            _hashCode += getLOG_RESPUESTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaInsertMandato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "RespuestaInsertMandato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID_MANDATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID_MANDATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG_RESPUESTA "));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "LOG_RESPUESTA"));
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
