/**
 * LMEInfTramEmpResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package conector.lme.ws.cliente.operador;

public class LMEInfTramEmpResponse  implements java.io.Serializable {
    private java.math.BigInteger estado;

    private java.lang.String gloEstado;

    public LMEInfTramEmpResponse() {
    }

    public LMEInfTramEmpResponse(
           java.math.BigInteger estado,
           java.lang.String gloEstado) {
           this.estado = estado;
           this.gloEstado = gloEstado;
    }


    /**
     * Gets the estado value for this LMEInfTramEmpResponse.
     * 
     * @return estado
     */
    public java.math.BigInteger getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LMEInfTramEmpResponse.
     * 
     * @param estado
     */
    public void setEstado(java.math.BigInteger estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this LMEInfTramEmpResponse.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this LMEInfTramEmpResponse.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfTramEmpResponse)) return false;
        LMEInfTramEmpResponse other = (LMEInfTramEmpResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEInfTramEmpResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfTramEmpResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Estado"));
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
