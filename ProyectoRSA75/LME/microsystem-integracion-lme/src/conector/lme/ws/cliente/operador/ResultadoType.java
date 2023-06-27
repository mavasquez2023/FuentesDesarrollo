/**
 * ResultadoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package conector.lme.ws.cliente.operador;

public class ResultadoType  implements java.io.Serializable {
    private java.math.BigInteger idLicencia;

    private java.lang.String dvLicencia;

    private java.math.BigInteger codEstado;

    private java.lang.String gloEstado;

    public ResultadoType() {
    }

    public ResultadoType(
           java.math.BigInteger idLicencia,
           java.lang.String dvLicencia,
           java.math.BigInteger codEstado,
           java.lang.String gloEstado) {
           this.idLicencia = idLicencia;
           this.dvLicencia = dvLicencia;
           this.codEstado = codEstado;
           this.gloEstado = gloEstado;
    }


    /**
     * Gets the idLicencia value for this ResultadoType.
     * 
     * @return idLicencia
     */
    public java.math.BigInteger getIdLicencia() {
        return idLicencia;
    }


    /**
     * Sets the idLicencia value for this ResultadoType.
     * 
     * @param idLicencia
     */
    public void setIdLicencia(java.math.BigInteger idLicencia) {
        this.idLicencia = idLicencia;
    }


    /**
     * Gets the dvLicencia value for this ResultadoType.
     * 
     * @return dvLicencia
     */
    public java.lang.String getDvLicencia() {
        return dvLicencia;
    }


    /**
     * Sets the dvLicencia value for this ResultadoType.
     * 
     * @param dvLicencia
     */
    public void setDvLicencia(java.lang.String dvLicencia) {
        this.dvLicencia = dvLicencia;
    }


    /**
     * Gets the codEstado value for this ResultadoType.
     * 
     * @return codEstado
     */
    public java.math.BigInteger getCodEstado() {
        return codEstado;
    }


    /**
     * Sets the codEstado value for this ResultadoType.
     * 
     * @param codEstado
     */
    public void setCodEstado(java.math.BigInteger codEstado) {
        this.codEstado = codEstado;
    }


    /**
     * Gets the gloEstado value for this ResultadoType.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this ResultadoType.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoType)) return false;
        ResultadoType other = (ResultadoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idLicencia==null && other.getIdLicencia()==null) || 
             (this.idLicencia!=null &&
              this.idLicencia.equals(other.getIdLicencia()))) &&
            ((this.dvLicencia==null && other.getDvLicencia()==null) || 
             (this.dvLicencia!=null &&
              this.dvLicencia.equals(other.getDvLicencia()))) &&
            ((this.codEstado==null && other.getCodEstado()==null) || 
             (this.codEstado!=null &&
              this.codEstado.equals(other.getCodEstado()))) &&
            ((this.gloEstado==null && other.getGloEstado()==null) || 
             (this.gloEstado!=null &&
              this.gloEstado.equals(other.getGloEstado())));
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
        if (getIdLicencia() != null) {
            _hashCode += getIdLicencia().hashCode();
        }
        if (getDvLicencia() != null) {
            _hashCode += getDvLicencia().hashCode();
        }
        if (getCodEstado() != null) {
            _hashCode += getCodEstado().hashCode();
        }
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "ResultadoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("codEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
