/**
 * PLATINUMJustificacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMJustificacion  implements java.io.Serializable {
    private java.lang.String fechaJustificacion;

    private java.lang.String textoJustificacion;

    public PLATINUMJustificacion() {
    }

    public PLATINUMJustificacion(
           java.lang.String fechaJustificacion,
           java.lang.String textoJustificacion) {
           this.fechaJustificacion = fechaJustificacion;
           this.textoJustificacion = textoJustificacion;
    }


    /**
     * Gets the fechaJustificacion value for this PLATINUMJustificacion.
     * 
     * @return fechaJustificacion
     */
    public java.lang.String getFechaJustificacion() {
        return fechaJustificacion;
    }


    /**
     * Sets the fechaJustificacion value for this PLATINUMJustificacion.
     * 
     * @param fechaJustificacion
     */
    public void setFechaJustificacion(java.lang.String fechaJustificacion) {
        this.fechaJustificacion = fechaJustificacion;
    }


    /**
     * Gets the textoJustificacion value for this PLATINUMJustificacion.
     * 
     * @return textoJustificacion
     */
    public java.lang.String getTextoJustificacion() {
        return textoJustificacion;
    }


    /**
     * Sets the textoJustificacion value for this PLATINUMJustificacion.
     * 
     * @param textoJustificacion
     */
    public void setTextoJustificacion(java.lang.String textoJustificacion) {
        this.textoJustificacion = textoJustificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMJustificacion)) return false;
        PLATINUMJustificacion other = (PLATINUMJustificacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaJustificacion==null && other.getFechaJustificacion()==null) || 
             (this.fechaJustificacion!=null &&
              this.fechaJustificacion.equals(other.getFechaJustificacion()))) &&
            ((this.textoJustificacion==null && other.getTextoJustificacion()==null) || 
             (this.textoJustificacion!=null &&
              this.textoJustificacion.equals(other.getTextoJustificacion())));
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
        if (getFechaJustificacion() != null) {
            _hashCode += getFechaJustificacion().hashCode();
        }
        if (getTextoJustificacion() != null) {
            _hashCode += getTextoJustificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMJustificacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMJustificacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaJustificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaJustificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textoJustificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "textoJustificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
