/**
 * LMEInfResol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEInfResol  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.math.BigInteger codigoAsegurador;

    private java.lang.String clave;

    private java.util.Calendar fechaInforma;

    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private byte[] datosZonaB;

    private java.math.BigInteger entidadPago;

    public LMEInfResol() {
    }

    public LMEInfResol(
           java.lang.String codigoOperador,
           java.math.BigInteger codigoAsegurador,
           java.lang.String clave,
           java.util.Calendar fechaInforma,
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           byte[] datosZonaB,
           java.math.BigInteger entidadPago) {
           this.codigoOperador = codigoOperador;
           this.codigoAsegurador = codigoAsegurador;
           this.clave = clave;
           this.fechaInforma = fechaInforma;
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.datosZonaB = datosZonaB;
           this.entidadPago = entidadPago;
    }


    /**
     * Gets the codigoOperador value for this LMEInfResol.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEInfResol.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the codigoAsegurador value for this LMEInfResol.
     * 
     * @return codigoAsegurador
     */
    public java.math.BigInteger getCodigoAsegurador() {
        return codigoAsegurador;
    }


    /**
     * Sets the codigoAsegurador value for this LMEInfResol.
     * 
     * @param codigoAsegurador
     */
    public void setCodigoAsegurador(java.math.BigInteger codigoAsegurador) {
        this.codigoAsegurador = codigoAsegurador;
    }


    /**
     * Gets the clave value for this LMEInfResol.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this LMEInfResol.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the fechaInforma value for this LMEInfResol.
     * 
     * @return fechaInforma
     */
    public java.util.Calendar getFechaInforma() {
        return fechaInforma;
    }


    /**
     * Sets the fechaInforma value for this LMEInfResol.
     * 
     * @param fechaInforma
     */
    public void setFechaInforma(java.util.Calendar fechaInforma) {
        this.fechaInforma = fechaInforma;
    }


    /**
     * Gets the idLicencia value for this LMEInfResol.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this LMEInfResol.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this LMEInfResol.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this LMEInfResol.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the datosZonaB value for this LMEInfResol.
     * 
     * @return datosZonaB
     */
    public byte[] getDatosZonaB() {
        return datosZonaB;
    }


    /**
     * Sets the datosZonaB value for this LMEInfResol.
     * 
     * @param datosZonaB
     */
    public void setDatosZonaB(byte[] datosZonaB) {
        this.datosZonaB = datosZonaB;
    }


    /**
     * Gets the entidadPago value for this LMEInfResol.
     * 
     * @return entidadPago
     */
    public java.math.BigInteger getEntidadPago() {
        return entidadPago;
    }


    /**
     * Sets the entidadPago value for this LMEInfResol.
     * 
     * @param entidadPago
     */
    public void setEntidadPago(java.math.BigInteger entidadPago) {
        this.entidadPago = entidadPago;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfResol)) return false;
        LMEInfResol other = (LMEInfResol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoOperador==null && other.getCodigoOperador()==null) || 
             (this.codigoOperador!=null &&
              this.codigoOperador.equals(other.getCodigoOperador()))) &&
            ((this.codigoAsegurador==null && other.getCodigoAsegurador()==null) || 
             (this.codigoAsegurador!=null &&
              this.codigoAsegurador.equals(other.getCodigoAsegurador()))) &&
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave()))) &&
            ((this.fechaInforma==null && other.getFechaInforma()==null) || 
             (this.fechaInforma!=null &&
              this.fechaInforma.equals(other.getFechaInforma()))) &&
            ((this.idLicencia==null && other.getIdLicencia()==null) || 
             (this.idLicencia!=null &&
              this.idLicencia.equals(other.getIdLicencia()))) &&
            ((this.dvLicencia==null && other.getDvLicencia()==null) || 
             (this.dvLicencia!=null &&
              this.dvLicencia.equals(other.getDvLicencia()))) &&
            ((this.datosZonaB==null && other.getDatosZonaB()==null) || 
             (this.datosZonaB!=null &&
              java.util.Arrays.equals(this.datosZonaB, other.getDatosZonaB()))) &&
            ((this.entidadPago==null && other.getEntidadPago()==null) || 
             (this.entidadPago!=null &&
              this.entidadPago.equals(other.getEntidadPago())));
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
        if (getCodigoOperador() != null) {
            _hashCode += getCodigoOperador().hashCode();
        }
        if (getCodigoAsegurador() != null) {
            _hashCode += getCodigoAsegurador().hashCode();
        }
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        if (getFechaInforma() != null) {
            _hashCode += getFechaInforma().hashCode();
        }
        if (getIdLicencia() != null) {
            _hashCode += getIdLicencia().hashCode();
        }
        if (getDvLicencia() != null) {
            _hashCode += getDvLicencia().hashCode();
        }
        if (getDatosZonaB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosZonaB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosZonaB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntidadPago() != null) {
            _hashCode += getEntidadPago().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEInfResol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfResol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOperador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodigoOperador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAsegurador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodigoAsegurador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInforma");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaInforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dvLicencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DvLicencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosZonaB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DatosZonaB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidadPago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EntidadPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
