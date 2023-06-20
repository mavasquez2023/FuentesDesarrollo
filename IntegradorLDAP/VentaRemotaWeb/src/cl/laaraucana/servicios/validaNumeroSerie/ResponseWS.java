/**
 * ResponseWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaNumeroSerie;

public class ResponseWS  implements java.io.Serializable {
    private java.lang.String codigoRetorno;

    private java.lang.String mensaje;

    private java.lang.String existeDetalle;

    private java.lang.String cedulaVigente;

    private java.lang.String numeroRegistros;

    public ResponseWS() {
    }

    public ResponseWS(
           java.lang.String codigoRetorno,
           java.lang.String mensaje,
           java.lang.String existeDetalle,
           java.lang.String cedulaVigente,
           java.lang.String numeroRegistros) {
           this.codigoRetorno = codigoRetorno;
           this.mensaje = mensaje;
           this.existeDetalle = existeDetalle;
           this.cedulaVigente = cedulaVigente;
           this.numeroRegistros = numeroRegistros;
    }


    /**
     * Gets the codigoRetorno value for this ResponseWS.
     * 
     * @return codigoRetorno
     */
    public java.lang.String getCodigoRetorno() {
        return codigoRetorno;
    }


    /**
     * Sets the codigoRetorno value for this ResponseWS.
     * 
     * @param codigoRetorno
     */
    public void setCodigoRetorno(java.lang.String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }


    /**
     * Gets the mensaje value for this ResponseWS.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ResponseWS.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the existeDetalle value for this ResponseWS.
     * 
     * @return existeDetalle
     */
    public java.lang.String getExisteDetalle() {
        return existeDetalle;
    }


    /**
     * Sets the existeDetalle value for this ResponseWS.
     * 
     * @param existeDetalle
     */
    public void setExisteDetalle(java.lang.String existeDetalle) {
        this.existeDetalle = existeDetalle;
    }


    /**
     * Gets the cedulaVigente value for this ResponseWS.
     * 
     * @return cedulaVigente
     */
    public java.lang.String getCedulaVigente() {
        return cedulaVigente;
    }


    /**
     * Sets the cedulaVigente value for this ResponseWS.
     * 
     * @param cedulaVigente
     */
    public void setCedulaVigente(java.lang.String cedulaVigente) {
        this.cedulaVigente = cedulaVigente;
    }


    /**
     * Gets the numeroRegistros value for this ResponseWS.
     * 
     * @return numeroRegistros
     */
    public java.lang.String getNumeroRegistros() {
        return numeroRegistros;
    }


    /**
     * Sets the numeroRegistros value for this ResponseWS.
     * 
     * @param numeroRegistros
     */
    public void setNumeroRegistros(java.lang.String numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResponseWS)) return false;
        ResponseWS other = (ResponseWS) obj;
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
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            ((this.existeDetalle==null && other.getExisteDetalle()==null) || 
             (this.existeDetalle!=null &&
              this.existeDetalle.equals(other.getExisteDetalle()))) &&
            ((this.cedulaVigente==null && other.getCedulaVigente()==null) || 
             (this.cedulaVigente!=null &&
              this.cedulaVigente.equals(other.getCedulaVigente()))) &&
            ((this.numeroRegistros==null && other.getNumeroRegistros()==null) || 
             (this.numeroRegistros!=null &&
              this.numeroRegistros.equals(other.getNumeroRegistros())));
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
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaNumeroSerie", "responseWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existeDetalle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "existeDetalle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRegistros");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroRegistros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
