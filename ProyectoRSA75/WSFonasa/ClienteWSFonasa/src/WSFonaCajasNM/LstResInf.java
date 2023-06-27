/**
 * LstResInf.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class LstResInf  implements java.io.Serializable {
    private short tipFormulario;

    private int numFormulario;

    private short estAct;

    private java.lang.String gloEstado;

    public LstResInf() {
    }

    public LstResInf(
           short tipFormulario,
           int numFormulario,
           short estAct,
           java.lang.String gloEstado) {
           this.tipFormulario = tipFormulario;
           this.numFormulario = numFormulario;
           this.estAct = estAct;
           this.gloEstado = gloEstado;
    }


    /**
     * Gets the tipFormulario value for this LstResInf.
     * 
     * @return tipFormulario
     */
    public short getTipFormulario() {
        return tipFormulario;
    }


    /**
     * Sets the tipFormulario value for this LstResInf.
     * 
     * @param tipFormulario
     */
    public void setTipFormulario(short tipFormulario) {
        this.tipFormulario = tipFormulario;
    }


    /**
     * Gets the numFormulario value for this LstResInf.
     * 
     * @return numFormulario
     */
    public int getNumFormulario() {
        return numFormulario;
    }


    /**
     * Sets the numFormulario value for this LstResInf.
     * 
     * @param numFormulario
     */
    public void setNumFormulario(int numFormulario) {
        this.numFormulario = numFormulario;
    }


    /**
     * Gets the estAct value for this LstResInf.
     * 
     * @return estAct
     */
    public short getEstAct() {
        return estAct;
    }


    /**
     * Sets the estAct value for this LstResInf.
     * 
     * @param estAct
     */
    public void setEstAct(short estAct) {
        this.estAct = estAct;
    }


    /**
     * Gets the gloEstado value for this LstResInf.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this LstResInf.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LstResInf)) return false;
        LstResInf other = (LstResInf) obj;
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
            this.estAct == other.getEstAct() &&
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
        _hashCode += getTipFormulario();
        _hashCode += getNumFormulario();
        _hashCode += getEstAct();
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LstResInf.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf"));
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
        elemField.setFieldName("estAct");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EstAct"));
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
