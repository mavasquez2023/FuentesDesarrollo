/**
 * LMEVerLccTrabResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEVerLccTrabResponse  implements java.io.Serializable {
    private java.math.BigInteger resultado;

    private WsLMEInet_pkg.LmeTrabType[] listaLmeTrab;

    public LMEVerLccTrabResponse() {
    }

    public LMEVerLccTrabResponse(
           java.math.BigInteger resultado,
           WsLMEInet_pkg.LmeTrabType[] listaLmeTrab) {
           this.resultado = resultado;
           this.listaLmeTrab = listaLmeTrab;
    }


    /**
     * Gets the resultado value for this LMEVerLccTrabResponse.
     * 
     * @return resultado
     */
    public java.math.BigInteger getResultado() {
        return resultado;
    }


    /**
     * Sets the resultado value for this LMEVerLccTrabResponse.
     * 
     * @param resultado
     */
    public void setResultado(java.math.BigInteger resultado) {
        this.resultado = resultado;
    }


    /**
     * Gets the listaLmeTrab value for this LMEVerLccTrabResponse.
     * 
     * @return listaLmeTrab
     */
    public WsLMEInet_pkg.LmeTrabType[] getListaLmeTrab() {
        return listaLmeTrab;
    }


    /**
     * Sets the listaLmeTrab value for this LMEVerLccTrabResponse.
     * 
     * @param listaLmeTrab
     */
    public void setListaLmeTrab(WsLMEInet_pkg.LmeTrabType[] listaLmeTrab) {
        this.listaLmeTrab = listaLmeTrab;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEVerLccTrabResponse)) return false;
        LMEVerLccTrabResponse other = (LMEVerLccTrabResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultado==null && other.getResultado()==null) || 
             (this.resultado!=null &&
              this.resultado.equals(other.getResultado()))) &&
            ((this.listaLmeTrab==null && other.getListaLmeTrab()==null) || 
             (this.listaLmeTrab!=null &&
              java.util.Arrays.equals(this.listaLmeTrab, other.getListaLmeTrab())));
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
        if (getResultado() != null) {
            _hashCode += getResultado().hashCode();
        }
        if (getListaLmeTrab() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaLmeTrab());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaLmeTrab(), i);
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
        new org.apache.axis.description.TypeDesc(LMEVerLccTrabResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEVerLccTrabResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Resultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaLmeTrab");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaLmeTrab"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LmeTrabType"));
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
