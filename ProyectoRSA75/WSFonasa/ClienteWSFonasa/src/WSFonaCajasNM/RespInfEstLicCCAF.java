/**
 * RespInfEstLicCCAF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class RespInfEstLicCCAF  implements java.io.Serializable {
    private short estado;

    private java.lang.String gloEstado;

    private WSFonaCajasNM.LstResInf[] listaResAct;

    public RespInfEstLicCCAF() {
    }

    public RespInfEstLicCCAF(
           short estado,
           java.lang.String gloEstado,
           WSFonaCajasNM.LstResInf[] listaResAct) {
           this.estado = estado;
           this.gloEstado = gloEstado;
           this.listaResAct = listaResAct;
    }


    /**
     * Gets the estado value for this RespInfEstLicCCAF.
     * 
     * @return estado
     */
    public short getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RespInfEstLicCCAF.
     * 
     * @param estado
     */
    public void setEstado(short estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this RespInfEstLicCCAF.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this RespInfEstLicCCAF.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the listaResAct value for this RespInfEstLicCCAF.
     * 
     * @return listaResAct
     */
    public WSFonaCajasNM.LstResInf[] getListaResAct() {
        return listaResAct;
    }


    /**
     * Sets the listaResAct value for this RespInfEstLicCCAF.
     * 
     * @param listaResAct
     */
    public void setListaResAct(WSFonaCajasNM.LstResInf[] listaResAct) {
        this.listaResAct = listaResAct;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespInfEstLicCCAF)) return false;
        RespInfEstLicCCAF other = (RespInfEstLicCCAF) obj;
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
            ((this.listaResAct==null && other.getListaResAct()==null) || 
             (this.listaResAct!=null &&
              java.util.Arrays.equals(this.listaResAct, other.getListaResAct())));
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
        if (getListaResAct() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaResAct());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaResAct(), i);
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
        new org.apache.axis.description.TypeDesc(RespInfEstLicCCAF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespInfEstLicCCAF"));
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
        elemField.setFieldName("listaResAct");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ListaResAct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "LstResInf"));
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
