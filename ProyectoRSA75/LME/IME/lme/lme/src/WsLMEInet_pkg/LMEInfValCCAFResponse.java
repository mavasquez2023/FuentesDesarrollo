/**
 * LMEInfValCCAFResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WsLMEInet_pkg;

public class LMEInfValCCAFResponse  implements java.io.Serializable {
    private java.math.BigInteger estado;

    private java.math.BigInteger codResultado;

    private WsLMEInet_pkg.ResultadoType[] listaResultado;

    public LMEInfValCCAFResponse() {
    }

    public LMEInfValCCAFResponse(
           java.math.BigInteger estado,
           java.math.BigInteger codResultado,
           WsLMEInet_pkg.ResultadoType[] listaResultado) {
           this.estado = estado;
           this.codResultado = codResultado;
           this.listaResultado = listaResultado;
    }


    /**
     * Gets the estado value for this LMEInfValCCAFResponse.
     * 
     * @return estado
     */
    public java.math.BigInteger getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this LMEInfValCCAFResponse.
     * 
     * @param estado
     */
    public void setEstado(java.math.BigInteger estado) {
        this.estado = estado;
    }


    /**
     * Gets the codResultado value for this LMEInfValCCAFResponse.
     * 
     * @return codResultado
     */
    public java.math.BigInteger getCodResultado() {
        return codResultado;
    }


    /**
     * Sets the codResultado value for this LMEInfValCCAFResponse.
     * 
     * @param codResultado
     */
    public void setCodResultado(java.math.BigInteger codResultado) {
        this.codResultado = codResultado;
    }


    /**
     * Gets the listaResultado value for this LMEInfValCCAFResponse.
     * 
     * @return listaResultado
     */
    public WsLMEInet_pkg.ResultadoType[] getListaResultado() {
        return listaResultado;
    }


    /**
     * Sets the listaResultado value for this LMEInfValCCAFResponse.
     * 
     * @param listaResultado
     */
    public void setListaResultado(WsLMEInet_pkg.ResultadoType[] listaResultado) {
        this.listaResultado = listaResultado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEInfValCCAFResponse)) return false;
        LMEInfValCCAFResponse other = (LMEInfValCCAFResponse) obj;
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
            ((this.codResultado==null && other.getCodResultado()==null) || 
             (this.codResultado!=null &&
              this.codResultado.equals(other.getCodResultado()))) &&
            ((this.listaResultado==null && other.getListaResultado()==null) || 
             (this.listaResultado!=null &&
              java.util.Arrays.equals(this.listaResultado, other.getListaResultado())));
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
        if (getCodResultado() != null) {
            _hashCode += getCodResultado().hashCode();
        }
        if (getListaResultado() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaResultado());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaResultado(), i);
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
        new org.apache.axis.description.TypeDesc(LMEInfValCCAFResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "LMEInfValCCAFResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CodResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:WsLMEInet", "ResultadoType"));
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
