/**
 * PLATINUMAntecedentesLaborales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMAntecedentesLaborales  implements java.io.Serializable {
    private java.lang.String caargoEmpresa;

    private java.lang.String ciudadDireccionLaboral;

    private java.lang.String comunaDireccionLaboral;

    private java.lang.String direccionLaboral;

    private java.lang.String fechaVerificacionDatos;

    private java.lang.String fuenteVerificacion;

    private java.lang.String nombreEmpleador;

    private java.lang.String telefono;

    public PLATINUMAntecedentesLaborales() {
    }

    public PLATINUMAntecedentesLaborales(
           java.lang.String caargoEmpresa,
           java.lang.String ciudadDireccionLaboral,
           java.lang.String comunaDireccionLaboral,
           java.lang.String direccionLaboral,
           java.lang.String fechaVerificacionDatos,
           java.lang.String fuenteVerificacion,
           java.lang.String nombreEmpleador,
           java.lang.String telefono) {
           this.caargoEmpresa = caargoEmpresa;
           this.ciudadDireccionLaboral = ciudadDireccionLaboral;
           this.comunaDireccionLaboral = comunaDireccionLaboral;
           this.direccionLaboral = direccionLaboral;
           this.fechaVerificacionDatos = fechaVerificacionDatos;
           this.fuenteVerificacion = fuenteVerificacion;
           this.nombreEmpleador = nombreEmpleador;
           this.telefono = telefono;
    }


    /**
     * Gets the caargoEmpresa value for this PLATINUMAntecedentesLaborales.
     * 
     * @return caargoEmpresa
     */
    public java.lang.String getCaargoEmpresa() {
        return caargoEmpresa;
    }


    /**
     * Sets the caargoEmpresa value for this PLATINUMAntecedentesLaborales.
     * 
     * @param caargoEmpresa
     */
    public void setCaargoEmpresa(java.lang.String caargoEmpresa) {
        this.caargoEmpresa = caargoEmpresa;
    }


    /**
     * Gets the ciudadDireccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @return ciudadDireccionLaboral
     */
    public java.lang.String getCiudadDireccionLaboral() {
        return ciudadDireccionLaboral;
    }


    /**
     * Sets the ciudadDireccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @param ciudadDireccionLaboral
     */
    public void setCiudadDireccionLaboral(java.lang.String ciudadDireccionLaboral) {
        this.ciudadDireccionLaboral = ciudadDireccionLaboral;
    }


    /**
     * Gets the comunaDireccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @return comunaDireccionLaboral
     */
    public java.lang.String getComunaDireccionLaboral() {
        return comunaDireccionLaboral;
    }


    /**
     * Sets the comunaDireccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @param comunaDireccionLaboral
     */
    public void setComunaDireccionLaboral(java.lang.String comunaDireccionLaboral) {
        this.comunaDireccionLaboral = comunaDireccionLaboral;
    }


    /**
     * Gets the direccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @return direccionLaboral
     */
    public java.lang.String getDireccionLaboral() {
        return direccionLaboral;
    }


    /**
     * Sets the direccionLaboral value for this PLATINUMAntecedentesLaborales.
     * 
     * @param direccionLaboral
     */
    public void setDireccionLaboral(java.lang.String direccionLaboral) {
        this.direccionLaboral = direccionLaboral;
    }


    /**
     * Gets the fechaVerificacionDatos value for this PLATINUMAntecedentesLaborales.
     * 
     * @return fechaVerificacionDatos
     */
    public java.lang.String getFechaVerificacionDatos() {
        return fechaVerificacionDatos;
    }


    /**
     * Sets the fechaVerificacionDatos value for this PLATINUMAntecedentesLaborales.
     * 
     * @param fechaVerificacionDatos
     */
    public void setFechaVerificacionDatos(java.lang.String fechaVerificacionDatos) {
        this.fechaVerificacionDatos = fechaVerificacionDatos;
    }


    /**
     * Gets the fuenteVerificacion value for this PLATINUMAntecedentesLaborales.
     * 
     * @return fuenteVerificacion
     */
    public java.lang.String getFuenteVerificacion() {
        return fuenteVerificacion;
    }


    /**
     * Sets the fuenteVerificacion value for this PLATINUMAntecedentesLaborales.
     * 
     * @param fuenteVerificacion
     */
    public void setFuenteVerificacion(java.lang.String fuenteVerificacion) {
        this.fuenteVerificacion = fuenteVerificacion;
    }


    /**
     * Gets the nombreEmpleador value for this PLATINUMAntecedentesLaborales.
     * 
     * @return nombreEmpleador
     */
    public java.lang.String getNombreEmpleador() {
        return nombreEmpleador;
    }


    /**
     * Sets the nombreEmpleador value for this PLATINUMAntecedentesLaborales.
     * 
     * @param nombreEmpleador
     */
    public void setNombreEmpleador(java.lang.String nombreEmpleador) {
        this.nombreEmpleador = nombreEmpleador;
    }


    /**
     * Gets the telefono value for this PLATINUMAntecedentesLaborales.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this PLATINUMAntecedentesLaborales.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMAntecedentesLaborales)) return false;
        PLATINUMAntecedentesLaborales other = (PLATINUMAntecedentesLaborales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.caargoEmpresa==null && other.getCaargoEmpresa()==null) || 
             (this.caargoEmpresa!=null &&
              this.caargoEmpresa.equals(other.getCaargoEmpresa()))) &&
            ((this.ciudadDireccionLaboral==null && other.getCiudadDireccionLaboral()==null) || 
             (this.ciudadDireccionLaboral!=null &&
              this.ciudadDireccionLaboral.equals(other.getCiudadDireccionLaboral()))) &&
            ((this.comunaDireccionLaboral==null && other.getComunaDireccionLaboral()==null) || 
             (this.comunaDireccionLaboral!=null &&
              this.comunaDireccionLaboral.equals(other.getComunaDireccionLaboral()))) &&
            ((this.direccionLaboral==null && other.getDireccionLaboral()==null) || 
             (this.direccionLaboral!=null &&
              this.direccionLaboral.equals(other.getDireccionLaboral()))) &&
            ((this.fechaVerificacionDatos==null && other.getFechaVerificacionDatos()==null) || 
             (this.fechaVerificacionDatos!=null &&
              this.fechaVerificacionDatos.equals(other.getFechaVerificacionDatos()))) &&
            ((this.fuenteVerificacion==null && other.getFuenteVerificacion()==null) || 
             (this.fuenteVerificacion!=null &&
              this.fuenteVerificacion.equals(other.getFuenteVerificacion()))) &&
            ((this.nombreEmpleador==null && other.getNombreEmpleador()==null) || 
             (this.nombreEmpleador!=null &&
              this.nombreEmpleador.equals(other.getNombreEmpleador()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono())));
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
        if (getCaargoEmpresa() != null) {
            _hashCode += getCaargoEmpresa().hashCode();
        }
        if (getCiudadDireccionLaboral() != null) {
            _hashCode += getCiudadDireccionLaboral().hashCode();
        }
        if (getComunaDireccionLaboral() != null) {
            _hashCode += getComunaDireccionLaboral().hashCode();
        }
        if (getDireccionLaboral() != null) {
            _hashCode += getDireccionLaboral().hashCode();
        }
        if (getFechaVerificacionDatos() != null) {
            _hashCode += getFechaVerificacionDatos().hashCode();
        }
        if (getFuenteVerificacion() != null) {
            _hashCode += getFuenteVerificacion().hashCode();
        }
        if (getNombreEmpleador() != null) {
            _hashCode += getNombreEmpleador().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMAntecedentesLaborales.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMAntecedentesLaborales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caargoEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "caargoEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudadDireccionLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ciudadDireccionLaboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunaDireccionLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comunaDireccionLaboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccionLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccionLaboral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVerificacionDatos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaVerificacionDatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuenteVerificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fuenteVerificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEmpleador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreEmpleador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
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
