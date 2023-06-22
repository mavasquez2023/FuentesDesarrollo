/**
 * PLATINUMPorMercado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output.sub09;

public class PLATINUMPorMercado  implements java.io.Serializable {
    private java.lang.String codigo;

    private java.lang.String descripcion;

    private java.lang.String montoImpagos;

    private java.lang.String nroImpagos;

    public PLATINUMPorMercado() {
    }

    public PLATINUMPorMercado(
           java.lang.String codigo,
           java.lang.String descripcion,
           java.lang.String montoImpagos,
           java.lang.String nroImpagos) {
           this.codigo = codigo;
           this.descripcion = descripcion;
           this.montoImpagos = montoImpagos;
           this.nroImpagos = nroImpagos;
    }


    /**
     * Gets the codigo value for this PLATINUMPorMercado.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this PLATINUMPorMercado.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descripcion value for this PLATINUMPorMercado.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this PLATINUMPorMercado.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the montoImpagos value for this PLATINUMPorMercado.
     * 
     * @return montoImpagos
     */
    public java.lang.String getMontoImpagos() {
        return montoImpagos;
    }


    /**
     * Sets the montoImpagos value for this PLATINUMPorMercado.
     * 
     * @param montoImpagos
     */
    public void setMontoImpagos(java.lang.String montoImpagos) {
        this.montoImpagos = montoImpagos;
    }


    /**
     * Gets the nroImpagos value for this PLATINUMPorMercado.
     * 
     * @return nroImpagos
     */
    public java.lang.String getNroImpagos() {
        return nroImpagos;
    }


    /**
     * Sets the nroImpagos value for this PLATINUMPorMercado.
     * 
     * @param nroImpagos
     */
    public void setNroImpagos(java.lang.String nroImpagos) {
        this.nroImpagos = nroImpagos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMPorMercado)) return false;
        PLATINUMPorMercado other = (PLATINUMPorMercado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.montoImpagos==null && other.getMontoImpagos()==null) || 
             (this.montoImpagos!=null &&
              this.montoImpagos.equals(other.getMontoImpagos()))) &&
            ((this.nroImpagos==null && other.getNroImpagos()==null) || 
             (this.nroImpagos!=null &&
              this.nroImpagos.equals(other.getNroImpagos())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getMontoImpagos() != null) {
            _hashCode += getMontoImpagos().hashCode();
        }
        if (getNroImpagos() != null) {
            _hashCode += getNroImpagos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMPorMercado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sub09.output.platinum.gru01.wse.equifax.cl", "PLATINUMPorMercado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoImpagos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoImpagos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroImpagos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroImpagos"));
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
