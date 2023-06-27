/**
 * RespConFormLCC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class RespConFormLCC  implements java.io.Serializable {
    private short estado;

    private java.lang.String gloEstado;

    private short codResultado;

    private java.lang.String gloResultado;

    public RespConFormLCC() {
    }

    public RespConFormLCC(
           short estado,
           java.lang.String gloEstado,
           short codResultado,
           java.lang.String gloResultado) {
           this.estado = estado;
           this.gloEstado = gloEstado;
           this.codResultado = codResultado;
           this.gloResultado = gloResultado;
    }


    /**
     * Gets the estado value for this RespConFormLCC.
     * 
     * @return estado
     */
    public short getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RespConFormLCC.
     * 
     * @param estado
     */
    public void setEstado(short estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this RespConFormLCC.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this RespConFormLCC.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the codResultado value for this RespConFormLCC.
     * 
     * @return codResultado
     */
    public short getCodResultado() {
        return codResultado;
    }


    /**
     * Sets the codResultado value for this RespConFormLCC.
     * 
     * @param codResultado
     */
    public void setCodResultado(short codResultado) {
        this.codResultado = codResultado;
    }


    /**
     * Gets the gloResultado value for this RespConFormLCC.
     * 
     * @return gloResultado
     */
    public java.lang.String getGloResultado() {
        return gloResultado;
    }


    /**
     * Sets the gloResultado value for this RespConFormLCC.
     * 
     * @param gloResultado
     */
    public void setGloResultado(java.lang.String gloResultado) {
        this.gloResultado = gloResultado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespConFormLCC)) return false;
        RespConFormLCC other = (RespConFormLCC) obj;
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
            this.codResultado == other.getCodResultado() &&
            ((this.gloResultado==null && other.getGloResultado()==null) || 
             (this.gloResultado!=null &&
              this.gloResultado.equals(other.getGloResultado())));
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
        _hashCode += getEstado();
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        _hashCode += getCodResultado();
        if (getGloResultado() != null) {
            _hashCode += getGloResultado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespConFormLCC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespConFormLCC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "GloResultado"));
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
