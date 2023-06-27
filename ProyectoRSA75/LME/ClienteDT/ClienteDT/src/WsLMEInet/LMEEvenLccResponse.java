/**
 * LMEEvenLccResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet;

import java.math.BigInteger;

public class LMEEvenLccResponse  implements java.io.Serializable {
    private BigInteger estado;

    private java.lang.String gloEstado;

    private WsLMEInet.LicenciaType[] listaLicencias;

    public LMEEvenLccResponse() {
    }

    public LMEEvenLccResponse(
           int estado,
           java.lang.String gloEstado,
           WsLMEInet.LicenciaType[] listaLicencias) {
           this.estado = new BigInteger(String.valueOf(estado));
           this.gloEstado = gloEstado;
           this.listaLicencias = listaLicencias;
    }


    /**
     * Gets the estado value for this LMEEvenLccResponse.
     * 
     * @return estado
     */
    public BigInteger getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LMEEvenLccResponse.
     * 
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = new BigInteger(String.valueOf(estado));
    }


    /**
     * Gets the gloEstado value for this LMEEvenLccResponse.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this LMEEvenLccResponse.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the listaLicencias value for this LMEEvenLccResponse.
     * 
     * @return listaLicencias
     */
    public WsLMEInet.LicenciaType[] getListaLicencias() {
        return listaLicencias;
    }


    /**
     * Sets the listaLicencias value for this LMEEvenLccResponse.
     * 
     * @param listaLicencias
     */
    public void setListaLicencias(WsLMEInet.LicenciaType[] listaLicencias) {
        this.listaLicencias = listaLicencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEEvenLccResponse)) return false;
        LMEEvenLccResponse other = (LMEEvenLccResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.estado == other.getEstado() &&
            ((this.gloEstado==null && other.getGloEstado()==null) || 
             (this.gloEstado!=null &&
              this.gloEstado.equals(other.getGloEstado()))) &&
            ((this.listaLicencias==null && other.getListaLicencias()==null) || 
             (this.listaLicencias!=null &&
              java.util.Arrays.equals(this.listaLicencias, other.getListaLicencias())));
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
        _hashCode += getEstado().intValue();
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        if (getListaLicencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaLicencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaLicencias(), i);
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
        new org.apache.axis.description.TypeDesc(LMEEvenLccResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEEvenLccResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaLicencias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaLicencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LicenciaType"));
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
