/**
 * PLATINUMIdentificacionEmpresa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.equifax.wse.gru01.platinum.output;

public class PLATINUMIdentificacionEmpresa  implements java.io.Serializable {
    private java.lang.String codigoActividadEconomica;

    private java.lang.String descripcionActividadEconomica;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica[] detalleActividadesEconomicas;

    private cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo[] detalleEjecutivos;

    private java.lang.String razonSocial;

    public PLATINUMIdentificacionEmpresa() {
    }

    public PLATINUMIdentificacionEmpresa(
           java.lang.String codigoActividadEconomica,
           java.lang.String descripcionActividadEconomica,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica[] detalleActividadesEconomicas,
           cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo[] detalleEjecutivos,
           java.lang.String razonSocial) {
           this.codigoActividadEconomica = codigoActividadEconomica;
           this.descripcionActividadEconomica = descripcionActividadEconomica;
           this.detalleActividadesEconomicas = detalleActividadesEconomicas;
           this.detalleEjecutivos = detalleEjecutivos;
           this.razonSocial = razonSocial;
    }


    /**
     * Gets the codigoActividadEconomica value for this PLATINUMIdentificacionEmpresa.
     * 
     * @return codigoActividadEconomica
     */
    public java.lang.String getCodigoActividadEconomica() {
        return codigoActividadEconomica;
    }


    /**
     * Sets the codigoActividadEconomica value for this PLATINUMIdentificacionEmpresa.
     * 
     * @param codigoActividadEconomica
     */
    public void setCodigoActividadEconomica(java.lang.String codigoActividadEconomica) {
        this.codigoActividadEconomica = codigoActividadEconomica;
    }


    /**
     * Gets the descripcionActividadEconomica value for this PLATINUMIdentificacionEmpresa.
     * 
     * @return descripcionActividadEconomica
     */
    public java.lang.String getDescripcionActividadEconomica() {
        return descripcionActividadEconomica;
    }


    /**
     * Sets the descripcionActividadEconomica value for this PLATINUMIdentificacionEmpresa.
     * 
     * @param descripcionActividadEconomica
     */
    public void setDescripcionActividadEconomica(java.lang.String descripcionActividadEconomica) {
        this.descripcionActividadEconomica = descripcionActividadEconomica;
    }


    /**
     * Gets the detalleActividadesEconomicas value for this PLATINUMIdentificacionEmpresa.
     * 
     * @return detalleActividadesEconomicas
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica[] getDetalleActividadesEconomicas() {
        return detalleActividadesEconomicas;
    }


    /**
     * Sets the detalleActividadesEconomicas value for this PLATINUMIdentificacionEmpresa.
     * 
     * @param detalleActividadesEconomicas
     */
    public void setDetalleActividadesEconomicas(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleActividadEconomica[] detalleActividadesEconomicas) {
        this.detalleActividadesEconomicas = detalleActividadesEconomicas;
    }


    /**
     * Gets the detalleEjecutivos value for this PLATINUMIdentificacionEmpresa.
     * 
     * @return detalleEjecutivos
     */
    public cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo[] getDetalleEjecutivos() {
        return detalleEjecutivos;
    }


    /**
     * Sets the detalleEjecutivos value for this PLATINUMIdentificacionEmpresa.
     * 
     * @param detalleEjecutivos
     */
    public void setDetalleEjecutivos(cl.equifax.wse.gru01.platinum.output.sub.PLATINUMDetalleEjecutivo[] detalleEjecutivos) {
        this.detalleEjecutivos = detalleEjecutivos;
    }


    /**
     * Gets the razonSocial value for this PLATINUMIdentificacionEmpresa.
     * 
     * @return razonSocial
     */
    public java.lang.String getRazonSocial() {
        return razonSocial;
    }


    /**
     * Sets the razonSocial value for this PLATINUMIdentificacionEmpresa.
     * 
     * @param razonSocial
     */
    public void setRazonSocial(java.lang.String razonSocial) {
        this.razonSocial = razonSocial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PLATINUMIdentificacionEmpresa)) return false;
        PLATINUMIdentificacionEmpresa other = (PLATINUMIdentificacionEmpresa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoActividadEconomica==null && other.getCodigoActividadEconomica()==null) || 
             (this.codigoActividadEconomica!=null &&
              this.codigoActividadEconomica.equals(other.getCodigoActividadEconomica()))) &&
            ((this.descripcionActividadEconomica==null && other.getDescripcionActividadEconomica()==null) || 
             (this.descripcionActividadEconomica!=null &&
              this.descripcionActividadEconomica.equals(other.getDescripcionActividadEconomica()))) &&
            ((this.detalleActividadesEconomicas==null && other.getDetalleActividadesEconomicas()==null) || 
             (this.detalleActividadesEconomicas!=null &&
              java.util.Arrays.equals(this.detalleActividadesEconomicas, other.getDetalleActividadesEconomicas()))) &&
            ((this.detalleEjecutivos==null && other.getDetalleEjecutivos()==null) || 
             (this.detalleEjecutivos!=null &&
              java.util.Arrays.equals(this.detalleEjecutivos, other.getDetalleEjecutivos()))) &&
            ((this.razonSocial==null && other.getRazonSocial()==null) || 
             (this.razonSocial!=null &&
              this.razonSocial.equals(other.getRazonSocial())));
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
        if (getCodigoActividadEconomica() != null) {
            _hashCode += getCodigoActividadEconomica().hashCode();
        }
        if (getDescripcionActividadEconomica() != null) {
            _hashCode += getDescripcionActividadEconomica().hashCode();
        }
        if (getDetalleActividadesEconomicas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleActividadesEconomicas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleActividadesEconomicas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDetalleEjecutivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleEjecutivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleEjecutivos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRazonSocial() != null) {
            _hashCode += getRazonSocial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PLATINUMIdentificacionEmpresa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://output.platinum.gru01.wse.equifax.cl", "PLATINUMIdentificacionEmpresa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoActividadEconomica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoActividadEconomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionActividadEconomica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionActividadEconomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleActividadesEconomicas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalleActividadesEconomicas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleActividadEconomica"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleEjecutivos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalleEjecutivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sub.output.platinum.gru01.wse.equifax.cl", "PLATINUMDetalleEjecutivo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razonSocial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "razonSocial"));
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
