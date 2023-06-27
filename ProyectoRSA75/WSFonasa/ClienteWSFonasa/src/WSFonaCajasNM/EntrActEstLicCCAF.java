/**
 * EntrActEstLicCCAF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class EntrActEstLicCCAF  implements java.io.Serializable {
    private short tipFormulario;

    private int numFormulario;

    private java.lang.String fecEvento;

    private short tipoEvento;

    private java.lang.String comentario;

    public EntrActEstLicCCAF() {
    }

    public EntrActEstLicCCAF(
           short tipFormulario,
           int numFormulario,
           java.lang.String fecEvento,
           short tipoEvento,
           java.lang.String comentario) {
           this.tipFormulario = tipFormulario;
           this.numFormulario = numFormulario;
           this.fecEvento = fecEvento;
           this.tipoEvento = tipoEvento;
           this.comentario = comentario;
    }


    /**
     * Gets the tipFormulario value for this EntrActEstLicCCAF.
     * 
     * @return tipFormulario
     */
    public short getTipFormulario() {
        return tipFormulario;
    }


    /**
     * Sets the tipFormulario value for this EntrActEstLicCCAF.
     * 
     * @param tipFormulario
     */
    public void setTipFormulario(short tipFormulario) {
        this.tipFormulario = tipFormulario;
    }


    /**
     * Gets the numFormulario value for this EntrActEstLicCCAF.
     * 
     * @return numFormulario
     */
    public int getNumFormulario() {
        return numFormulario;
    }


    /**
     * Sets the numFormulario value for this EntrActEstLicCCAF.
     * 
     * @param numFormulario
     */
    public void setNumFormulario(int numFormulario) {
        this.numFormulario = numFormulario;
    }


    /**
     * Gets the fecEvento value for this EntrActEstLicCCAF.
     * 
     * @return fecEvento
     */
    public java.lang.String getFecEvento() {
        return fecEvento;
    }


    /**
     * Sets the fecEvento value for this EntrActEstLicCCAF.
     * 
     * @param fecEvento
     */
    public void setFecEvento(java.lang.String fecEvento) {
        this.fecEvento = fecEvento;
    }


    /**
     * Gets the tipoEvento value for this EntrActEstLicCCAF.
     * 
     * @return tipoEvento
     */
    public short getTipoEvento() {
        return tipoEvento;
    }


    /**
     * Sets the tipoEvento value for this EntrActEstLicCCAF.
     * 
     * @param tipoEvento
     */
    public void setTipoEvento(short tipoEvento) {
        this.tipoEvento = tipoEvento;
    }


    /**
     * Gets the comentario value for this EntrActEstLicCCAF.
     * 
     * @return comentario
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this EntrActEstLicCCAF.
     * 
     * @param comentario
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntrActEstLicCCAF)) return false;
        EntrActEstLicCCAF other = (EntrActEstLicCCAF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.tipFormulario == other.getTipFormulario() &&
            this.numFormulario == other.getNumFormulario() &&
            ((this.fecEvento==null && other.getFecEvento()==null) || 
             (this.fecEvento!=null &&
              this.fecEvento.equals(other.getFecEvento()))) &&
            this.tipoEvento == other.getTipoEvento() &&
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
        _hashCode += getTipFormulario();
        _hashCode += getNumFormulario();
        if (getFecEvento() != null) {
            _hashCode += getFecEvento().hashCode();
        }
        _hashCode += getTipoEvento();
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntrActEstLicCCAF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrActEstLicCCAF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "NumFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "FecEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipoEvento"));
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
