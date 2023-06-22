/**
 * PLATINUMDetalleEjecutivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output.sub;

public class PLATINUMDetalleEjecutivo  implements java.io.Serializable {
    private java.lang.String cargo;

    private java.lang.String nombre;

    private java.lang.String rutDigito;

    private java.lang.String rutNumero;

    public PLATINUMDetalleEjecutivo() {
    }

    public PLATINUMDetalleEjecutivo(
           java.lang.String cargo,
           java.lang.String nombre,
           java.lang.String rutDigito,
           java.lang.String rutNumero) {
           this.cargo = cargo;
           this.nombre = nombre;
           this.rutDigito = rutDigito;
           this.rutNumero = rutNumero;
    }


    /**
     * Gets the cargo value for this PLATINUMDetalleEjecutivo.
     * 
     * @return cargo
     */
    public java.lang.String getCargo() {
        return cargo;
    }


    /**
     * Sets the cargo value for this PLATINUMDetalleEjecutivo.
     * 
     * @param cargo
     */
    public void setCargo(java.lang.String cargo) {
        this.cargo = cargo;
    }


    /**
     * Gets the nombre value for this PLATINUMDetalleEjecutivo.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PLATINUMDetalleEjecutivo.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the rutDigito value for this PLATINUMDetalleEjecutivo.
     * 
     * @return rutDigito
     */
    public java.lang.String getRutDigito() {
        return rutDigito;
    }


    /**
     * Sets the rutDigito value for this PLATINUMDetalleEjecutivo.
     * 
     * @param rutDigito
     */
    public void setRutDigito(java.lang.String rutDigito) {
        this.rutDigito = rutDigito;
    }


    /**
     * Gets the rutNumero value for this PLATINUMDetalleEjecutivo.
     * 
     * @return rutNumero
     */
    public java.lang.String getRutNumero() {
        return rutNumero;
    }


    /**
     * Sets the rutNumero value for this PLATINUMDetalleEjecutivo.
     * 
     * @param rutNumero
     */
    public void setRutNumero(java.lang.String rutNumero) {
        this.rutNumero = rutNumero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMDetalleEjecutivo)) return false;
        PLATINUMDetalleEjecutivo other = (PLATINUMDetalleEjecutivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cargo==null && other.getCargo()==null) || 
             (this.cargo!=null &&
              this.cargo.equals(other.getCargo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.rutDigito==null && other.getRutDigito()==null) || 
             (this.rutDigito!=null &&
              this.rutDigito.equals(other.getRutDigito()))) &&
            ((this.rutNumero==null && other.getRutNumero()==null) || 
             (this.rutNumero!=null &&
              this.rutNumero.equals(other.getRutNumero())));
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
        if (getCargo() != null) {
            _hashCode += getCargo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getRutDigito() != null) {
            _hashCode += getRutDigito().hashCode();
        }
        if (getRutNumero() != null) {
            _hashCode += getRutNumero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMDetalleEjecutivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleEjecutivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutDigito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rutDigito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rutNumero"));
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
