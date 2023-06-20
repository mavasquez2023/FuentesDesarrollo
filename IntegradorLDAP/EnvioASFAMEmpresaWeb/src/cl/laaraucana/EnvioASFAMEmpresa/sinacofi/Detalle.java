/**
 * Detalle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.EnvioASFAMEmpresa.sinacofi;

public class Detalle  implements java.io.Serializable {
    private java.lang.String rutConsultado;

    private java.lang.String digitoVerificador;

    private java.lang.String tipoDocumento;

    private java.lang.String numeroSerie;

    private java.lang.String razon;

    private java.lang.String fecha;

    private java.lang.String fuente;

    public Detalle() {
    }

    public Detalle(
           java.lang.String rutConsultado,
           java.lang.String digitoVerificador,
           java.lang.String tipoDocumento,
           java.lang.String numeroSerie,
           java.lang.String razon,
           java.lang.String fecha,
           java.lang.String fuente) {
           this.rutConsultado = rutConsultado;
           this.digitoVerificador = digitoVerificador;
           this.tipoDocumento = tipoDocumento;
           this.numeroSerie = numeroSerie;
           this.razon = razon;
           this.fecha = fecha;
           this.fuente = fuente;
    }


    /**
     * Gets the rutConsultado value for this Detalle.
     * 
     * @return rutConsultado
     */
    public java.lang.String getRutConsultado() {
        return rutConsultado;
    }


    /**
     * Sets the rutConsultado value for this Detalle.
     * 
     * @param rutConsultado
     */
    public void setRutConsultado(java.lang.String rutConsultado) {
        this.rutConsultado = rutConsultado;
    }


    /**
     * Gets the digitoVerificador value for this Detalle.
     * 
     * @return digitoVerificador
     */
    public java.lang.String getDigitoVerificador() {
        return digitoVerificador;
    }


    /**
     * Sets the digitoVerificador value for this Detalle.
     * 
     * @param digitoVerificador
     */
    public void setDigitoVerificador(java.lang.String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }


    /**
     * Gets the tipoDocumento value for this Detalle.
     * 
     * @return tipoDocumento
     */
    public java.lang.String getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this Detalle.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the numeroSerie value for this Detalle.
     * 
     * @return numeroSerie
     */
    public java.lang.String getNumeroSerie() {
        return numeroSerie;
    }


    /**
     * Sets the numeroSerie value for this Detalle.
     * 
     * @param numeroSerie
     */
    public void setNumeroSerie(java.lang.String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }


    /**
     * Gets the razon value for this Detalle.
     * 
     * @return razon
     */
    public java.lang.String getRazon() {
        return razon;
    }


    /**
     * Sets the razon value for this Detalle.
     * 
     * @param razon
     */
    public void setRazon(java.lang.String razon) {
        this.razon = razon;
    }


    /**
     * Gets the fecha value for this Detalle.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this Detalle.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the fuente value for this Detalle.
     * 
     * @return fuente
     */
    public java.lang.String getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this Detalle.
     * 
     * @param fuente
     */
    public void setFuente(java.lang.String fuente) {
        this.fuente = fuente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Detalle)) return false;
        Detalle other = (Detalle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rutConsultado==null && other.getRutConsultado()==null) || 
             (this.rutConsultado!=null &&
              this.rutConsultado.equals(other.getRutConsultado()))) &&
            ((this.digitoVerificador==null && other.getDigitoVerificador()==null) || 
             (this.digitoVerificador!=null &&
              this.digitoVerificador.equals(other.getDigitoVerificador()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.numeroSerie==null && other.getNumeroSerie()==null) || 
             (this.numeroSerie!=null &&
              this.numeroSerie.equals(other.getNumeroSerie()))) &&
            ((this.razon==null && other.getRazon()==null) || 
             (this.razon!=null &&
              this.razon.equals(other.getRazon()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              this.fuente.equals(other.getFuente())));
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
        if (getRutConsultado() != null) {
            _hashCode += getRutConsultado().hashCode();
        }
        if (getDigitoVerificador() != null) {
            _hashCode += getDigitoVerificador().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        if (getNumeroSerie() != null) {
            _hashCode += getNumeroSerie().hashCode();
        }
        if (getRazon() != null) {
            _hashCode += getRazon().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getFuente() != null) {
            _hashCode += getFuente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Detalle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Detalle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutConsultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "RutConsultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digitoVerificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "DigitoVerificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "TipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "NumeroSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Razon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "Fuente"));
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
