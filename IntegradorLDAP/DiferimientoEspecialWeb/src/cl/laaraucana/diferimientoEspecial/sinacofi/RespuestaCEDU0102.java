/**
 * RespuestaCEDU0102.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.diferimientoEspecial.sinacofi;

public class RespuestaCEDU0102  implements java.io.Serializable {
    private java.lang.String codigoRetorno;

    private java.lang.String existeDetalle;

    private java.lang.String cedulaVigente;

    private java.lang.String numeroRegistros;

    private cl.laaraucana.diferimientoEspecial.sinacofi.Detalle[] detalles;

    public RespuestaCEDU0102() {
    }

    public RespuestaCEDU0102(
           java.lang.String codigoRetorno,
           java.lang.String existeDetalle,
           java.lang.String cedulaVigente,
           java.lang.String numeroRegistros,
           cl.laaraucana.diferimientoEspecial.sinacofi.Detalle[] detalles) {
           this.codigoRetorno = codigoRetorno;
           this.existeDetalle = existeDetalle;
           this.cedulaVigente = cedulaVigente;
           this.numeroRegistros = numeroRegistros;
           this.detalles = detalles;
    }


    /**
     * Gets the codigoRetorno value for this RespuestaCEDU0102.
     * 
     * @return codigoRetorno
     */
    public java.lang.String getCodigoRetorno() {
        return codigoRetorno;
    }


    /**
     * Sets the codigoRetorno value for this RespuestaCEDU0102.
     * 
     * @param codigoRetorno
     */
    public void setCodigoRetorno(java.lang.String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }


    /**
     * Gets the existeDetalle value for this RespuestaCEDU0102.
     * 
     * @return existeDetalle
     */
    public java.lang.String getExisteDetalle() {
        return existeDetalle;
    }


    /**
     * Sets the existeDetalle value for this RespuestaCEDU0102.
     * 
     * @param existeDetalle
     */
    public void setExisteDetalle(java.lang.String existeDetalle) {
        this.existeDetalle = existeDetalle;
    }


    /**
     * Gets the cedulaVigente value for this RespuestaCEDU0102.
     * 
     * @return cedulaVigente
     */
    public java.lang.String getCedulaVigente() {
        return cedulaVigente;
    }


    /**
     * Sets the cedulaVigente value for this RespuestaCEDU0102.
     * 
     * @param cedulaVigente
     */
    public void setCedulaVigente(java.lang.String cedulaVigente) {
        this.cedulaVigente = cedulaVigente;
    }


    /**
     * Gets the numeroRegistros value for this RespuestaCEDU0102.
     * 
     * @return numeroRegistros
     */
    public java.lang.String getNumeroRegistros() {
        return numeroRegistros;
    }


    /**
     * Sets the numeroRegistros value for this RespuestaCEDU0102.
     * 
     * @param numeroRegistros
     */
    public void setNumeroRegistros(java.lang.String numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }


    /**
     * Gets the detalles value for this RespuestaCEDU0102.
     * 
     * @return detalles
     */
    public cl.laaraucana.diferimientoEspecial.sinacofi.Detalle[] getDetalles() {
        return detalles;
    }


    /**
     * Sets the detalles value for this RespuestaCEDU0102.
     * 
     * @param detalles
     */
    public void setDetalles(cl.laaraucana.diferimientoEspecial.sinacofi.Detalle[] detalles) {
        this.detalles = detalles;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaCEDU0102)) return false;
        RespuestaCEDU0102 other = (RespuestaCEDU0102) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoRetorno==null && other.getCodigoRetorno()==null) || 
             (this.codigoRetorno!=null &&
              this.codigoRetorno.equals(other.getCodigoRetorno()))) &&
            ((this.existeDetalle==null && other.getExisteDetalle()==null) || 
             (this.existeDetalle!=null &&
              this.existeDetalle.equals(other.getExisteDetalle()))) &&
            ((this.cedulaVigente==null && other.getCedulaVigente()==null) || 
             (this.cedulaVigente!=null &&
              this.cedulaVigente.equals(other.getCedulaVigente()))) &&
            ((this.numeroRegistros==null && other.getNumeroRegistros()==null) || 
             (this.numeroRegistros!=null &&
              this.numeroRegistros.equals(other.getNumeroRegistros()))) &&
            ((this.detalles==null && other.getDetalles()==null) || 
             (this.detalles!=null &&
              java.util.Arrays.equals(this.detalles, other.getDetalles())));
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
        if (getCodigoRetorno() != null) {
            _hashCode += getCodigoRetorno().hashCode();
        }
        if (getExisteDetalle() != null) {
            _hashCode += getExisteDetalle().hashCode();
        }
        if (getCedulaVigente() != null) {
            _hashCode += getCedulaVigente().hashCode();
        }
        if (getNumeroRegistros() != null) {
            _hashCode += getNumeroRegistros().hashCode();
        }
        if (getDetalles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaCEDU0102.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "RespuestaCEDU0102"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CodigoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existeDetalle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "ExisteDetalle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CedulaVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRegistros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "NumeroRegistros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalles");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Detalles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Detalle"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Detalle"));
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
