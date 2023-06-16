/**
 * LstEstados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class LstEstados  implements java.io.Serializable {
    private java.lang.String fecEvento;

    private short codEstado;

    private java.lang.String comentario;

    public LstEstados() {
    }

    public LstEstados(
           java.lang.String fecEvento,
           short codEstado,
           java.lang.String comentario) {
           this.fecEvento = fecEvento;
           this.codEstado = codEstado;
           this.comentario = comentario;
    }


    /**
     * Gets the fecEvento value for this LstEstados.
     * 
     * @return fecEvento
     */
    public java.lang.String getFecEvento() {
        return fecEvento;
    }


    /**
     * Sets the fecEvento value for this LstEstados.
     * 
     * @param fecEvento
     */
    public void setFecEvento(java.lang.String fecEvento) {
        this.fecEvento = fecEvento;
    }


    /**
     * Gets the codEstado value for this LstEstados.
     * 
     * @return codEstado
     */
    public short getCodEstado() {
        return codEstado;
    }


    /**
     * Sets the codEstado value for this LstEstados.
     * 
     * @param codEstado
     */
    public void setCodEstado(short codEstado) {
        this.codEstado = codEstado;
    }


    /**
     * Gets the comentario value for this LstEstados.
     * 
     * @return comentario
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this LstEstados.
     * 
     * @param comentario
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LstEstados)) return false;
        LstEstados other = (LstEstados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fecEvento==null && other.getFecEvento()==null) || 
             (this.fecEvento!=null &&
              this.fecEvento.equals(other.getFecEvento()))) &&
            this.codEstado == other.getCodEstado() &&
            ((this.comentario==null && other.getComentario()==null) || 
             (this.comentario!=null &&
              this.comentario.equals(other.getComentario())));
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
        if (getFecEvento() != null) {
            _hashCode += getFecEvento().hashCode();
        }
        _hashCode += getCodEstado();
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LstEstados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstEstados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "FecEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "CodEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "Comentario"));
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
