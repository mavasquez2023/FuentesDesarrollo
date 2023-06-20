/**
 * CuentaVigente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class CuentaVigente  implements java.io.Serializable {
    private cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA;

    private cl.laaraucana.servicio.mandato.CuentaDescripcion CUENTA;

    public CuentaVigente() {
    }

    public CuentaVigente(
           cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA,
           cl.laaraucana.servicio.mandato.CuentaDescripcion CUENTA) {
           this.LOG_RESPUESTA = LOG_RESPUESTA;
           this.CUENTA = CUENTA;
    }


    /**
     * Gets the LOG_RESPUESTA value for this CuentaVigente.
     * 
     * @return LOG_RESPUESTA
     */
    public cl.laaraucana.servicio.mandato.LOG_RESPUESTA getLOG_RESPUESTA() {
        return LOG_RESPUESTA;
    }


    /**
     * Sets the LOG_RESPUESTA value for this CuentaVigente.
     * 
     * @param LOG_RESPUESTA
     */
    public void setLOG_RESPUESTA(cl.laaraucana.servicio.mandato.LOG_RESPUESTA LOG_RESPUESTA) {
        this.LOG_RESPUESTA = LOG_RESPUESTA;
    }


    /**
     * Gets the CUENTA value for this CuentaVigente.
     * 
     * @return CUENTA
     */
    public cl.laaraucana.servicio.mandato.CuentaDescripcion getCUENTA() {
        return CUENTA;
    }


    /**
     * Sets the CUENTA value for this CuentaVigente.
     * 
     * @param CUENTA
     */
    public void setCUENTA(cl.laaraucana.servicio.mandato.CuentaDescripcion CUENTA) {
        this.CUENTA = CUENTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CuentaVigente)) return false;
        CuentaVigente other = (CuentaVigente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LOG_RESPUESTA==null && other.getLOG_RESPUESTA()==null) || 
             (this.LOG_RESPUESTA!=null &&
              this.LOG_RESPUESTA.equals(other.getLOG_RESPUESTA()))) &&
            ((this.CUENTA==null && other.getCUENTA()==null) || 
             (this.CUENTA!=null &&
              this.CUENTA.equals(other.getCUENTA())));
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
        if (getLOG_RESPUESTA() != null) {
            _hashCode += getLOG_RESPUESTA().hashCode();
        }
        if (getCUENTA() != null) {
            _hashCode += getCUENTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CuentaVigente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "cuentaVigente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG_RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG_RESPUESTA "));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "LOG_RESPUESTA"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "cuentaDescripcion"));
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
