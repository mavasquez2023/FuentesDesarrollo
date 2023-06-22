/**
 * PLATINUMDetalleConsultaRut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output.sub;

public class PLATINUMDetalleConsultaRut  implements java.io.Serializable {
    private java.lang.String fechaConsulta;

    private java.lang.String informe;

    private java.lang.String nombre;

    public PLATINUMDetalleConsultaRut() {
    }

    public PLATINUMDetalleConsultaRut(
           java.lang.String fechaConsulta,
           java.lang.String informe,
           java.lang.String nombre) {
           this.fechaConsulta = fechaConsulta;
           this.informe = informe;
           this.nombre = nombre;
    }


    /**
     * Gets the fechaConsulta value for this PLATINUMDetalleConsultaRut.
     * 
     * @return fechaConsulta
     */
    public java.lang.String getFechaConsulta() {
        return fechaConsulta;
    }


    /**
     * Sets the fechaConsulta value for this PLATINUMDetalleConsultaRut.
     * 
     * @param fechaConsulta
     */
    public void setFechaConsulta(java.lang.String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }


    /**
     * Gets the informe value for this PLATINUMDetalleConsultaRut.
     * 
     * @return informe
     */
    public java.lang.String getInforme() {
        return informe;
    }


    /**
     * Sets the informe value for this PLATINUMDetalleConsultaRut.
     * 
     * @param informe
     */
    public void setInforme(java.lang.String informe) {
        this.informe = informe;
    }


    /**
     * Gets the nombre value for this PLATINUMDetalleConsultaRut.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PLATINUMDetalleConsultaRut.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMDetalleConsultaRut)) return false;
        PLATINUMDetalleConsultaRut other = (PLATINUMDetalleConsultaRut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaConsulta==null && other.getFechaConsulta()==null) || 
             (this.fechaConsulta!=null &&
              this.fechaConsulta.equals(other.getFechaConsulta()))) &&
            ((this.informe==null && other.getInforme()==null) || 
             (this.informe!=null &&
              this.informe.equals(other.getInforme()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre())));
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
        if (getFechaConsulta() != null) {
            _hashCode += getFechaConsulta().hashCode();
        }
        if (getInforme() != null) {
            _hashCode += getInforme().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMDetalleConsultaRut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleConsultaRut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "informe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
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
