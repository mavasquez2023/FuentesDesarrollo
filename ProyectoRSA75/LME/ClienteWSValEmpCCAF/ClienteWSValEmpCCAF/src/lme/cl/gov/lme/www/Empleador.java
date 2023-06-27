/**
 * Empleador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package lme.cl.gov.lme.www;

public class Empleador  implements java.io.Serializable {
    private java.lang.String nomRazSoc;

    private java.lang.String rutEmpleador;

    private java.lang.Integer ultimaCotizacion;

    public Empleador() {
    }

    public Empleador(
           java.lang.String nomRazSoc,
           java.lang.String rutEmpleador,
           java.lang.Integer ultimaCotizacion) {
           this.nomRazSoc = nomRazSoc;
           this.rutEmpleador = rutEmpleador;
           this.ultimaCotizacion = ultimaCotizacion;
    }


    /**
     * Gets the nomRazSoc value for this Empleador.
     * 
     * @return nomRazSoc
     */
    public java.lang.String getNomRazSoc() {
        return nomRazSoc;
    }


    /**
     * Sets the nomRazSoc value for this Empleador.
     * 
     * @param nomRazSoc
     */
    public void setNomRazSoc(java.lang.String nomRazSoc) {
        this.nomRazSoc = nomRazSoc;
    }


    /**
     * Gets the rutEmpleador value for this Empleador.
     * 
     * @return rutEmpleador
     */
    public java.lang.String getRutEmpleador() {
        return rutEmpleador;
    }


    /**
     * Sets the rutEmpleador value for this Empleador.
     * 
     * @param rutEmpleador
     */
    public void setRutEmpleador(java.lang.String rutEmpleador) {
        this.rutEmpleador = rutEmpleador;
    }


    /**
     * Gets the ultimaCotizacion value for this Empleador.
     * 
     * @return ultimaCotizacion
     */
    public java.lang.Integer getUltimaCotizacion() {
        return ultimaCotizacion;
    }


    /**
     * Sets the ultimaCotizacion value for this Empleador.
     * 
     * @param ultimaCotizacion
     */
    public void setUltimaCotizacion(java.lang.Integer ultimaCotizacion) {
        this.ultimaCotizacion = ultimaCotizacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Empleador)) return false;
        Empleador other = (Empleador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomRazSoc==null && other.getNomRazSoc()==null) || 
             (this.nomRazSoc!=null &&
              this.nomRazSoc.equals(other.getNomRazSoc()))) &&
            ((this.rutEmpleador==null && other.getRutEmpleador()==null) || 
             (this.rutEmpleador!=null &&
              this.rutEmpleador.equals(other.getRutEmpleador()))) &&
            ((this.ultimaCotizacion==null && other.getUltimaCotizacion()==null) || 
             (this.ultimaCotizacion!=null &&
              this.ultimaCotizacion.equals(other.getUltimaCotizacion())));
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
        if (getNomRazSoc() != null) {
            _hashCode += getNomRazSoc().hashCode();
        }
        if (getRutEmpleador() != null) {
            _hashCode += getRutEmpleador().hashCode();
        }
        if (getUltimaCotizacion() != null) {
            _hashCode += getUltimaCotizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Empleador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "Empleador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomRazSoc");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "NomRazSoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "RutEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ultimaCotizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "UltimaCotizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
