/**
 * LMEValEmpCCAFRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package lme.cl.gov.lme.www;

public class LMEValEmpCCAFRequest  implements java.io.Serializable {
    private java.lang.String codigoOperador;

    private java.lang.String claveOperador;

    private java.lang.String codigoCCAF;

    private java.lang.Integer rutTrabajadorNum;

    private java.lang.String rutTrabajadorDig;

    public LMEValEmpCCAFRequest() {
    }

    public LMEValEmpCCAFRequest(
           java.lang.String codigoOperador,
           java.lang.String claveOperador,
           java.lang.String codigoCCAF,
           java.lang.Integer rutTrabajadorNum,
           java.lang.String rutTrabajadorDig) {
           this.codigoOperador = codigoOperador;
           this.claveOperador = claveOperador;
           this.codigoCCAF = codigoCCAF;
           this.rutTrabajadorNum = rutTrabajadorNum;
           this.rutTrabajadorDig = rutTrabajadorDig;
    }


    /**
     * Gets the codigoOperador value for this LMEValEmpCCAFRequest.
     * 
     * @return codigoOperador
     */
    public java.lang.String getCodigoOperador() {
        return codigoOperador;
    }


    /**
     * Sets the codigoOperador value for this LMEValEmpCCAFRequest.
     * 
     * @param codigoOperador
     */
    public void setCodigoOperador(java.lang.String codigoOperador) {
        this.codigoOperador = codigoOperador;
    }


    /**
     * Gets the claveOperador value for this LMEValEmpCCAFRequest.
     * 
     * @return claveOperador
     */
    public java.lang.String getClaveOperador() {
        return claveOperador;
    }


    /**
     * Sets the claveOperador value for this LMEValEmpCCAFRequest.
     * 
     * @param claveOperador
     */
    public void setClaveOperador(java.lang.String claveOperador) {
        this.claveOperador = claveOperador;
    }


    /**
     * Gets the codigoCCAF value for this LMEValEmpCCAFRequest.
     * 
     * @return codigoCCAF
     */
    public java.lang.String getCodigoCCAF() {
        return codigoCCAF;
    }


    /**
     * Sets the codigoCCAF value for this LMEValEmpCCAFRequest.
     * 
     * @param codigoCCAF
     */
    public void setCodigoCCAF(java.lang.String codigoCCAF) {
        this.codigoCCAF = codigoCCAF;
    }


    /**
     * Gets the rutTrabajadorNum value for this LMEValEmpCCAFRequest.
     * 
     * @return rutTrabajadorNum
     */
    public java.lang.Integer getRutTrabajadorNum() {
        return rutTrabajadorNum;
    }


    /**
     * Sets the rutTrabajadorNum value for this LMEValEmpCCAFRequest.
     * 
     * @param rutTrabajadorNum
     */
    public void setRutTrabajadorNum(java.lang.Integer rutTrabajadorNum) {
        this.rutTrabajadorNum = rutTrabajadorNum;
    }


    /**
     * Gets the rutTrabajadorDig value for this LMEValEmpCCAFRequest.
     * 
     * @return rutTrabajadorDig
     */
    public java.lang.String getRutTrabajadorDig() {
        return rutTrabajadorDig;
    }


    /**
     * Sets the rutTrabajadorDig value for this LMEValEmpCCAFRequest.
     * 
     * @param rutTrabajadorDig
     */
    public void setRutTrabajadorDig(java.lang.String rutTrabajadorDig) {
        this.rutTrabajadorDig = rutTrabajadorDig;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LMEValEmpCCAFRequest)) return false;
        LMEValEmpCCAFRequest other = (LMEValEmpCCAFRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoOperador==null && other.getCodigoOperador()==null) || 
             (this.codigoOperador!=null &&
              this.codigoOperador.equals(other.getCodigoOperador()))) &&
            ((this.claveOperador==null && other.getClaveOperador()==null) || 
             (this.claveOperador!=null &&
              this.claveOperador.equals(other.getClaveOperador()))) &&
            ((this.codigoCCAF==null && other.getCodigoCCAF()==null) || 
             (this.codigoCCAF!=null &&
              this.codigoCCAF.equals(other.getCodigoCCAF()))) &&
            ((this.rutTrabajadorNum==null && other.getRutTrabajadorNum()==null) || 
             (this.rutTrabajadorNum!=null &&
              this.rutTrabajadorNum.equals(other.getRutTrabajadorNum()))) &&
            ((this.rutTrabajadorDig==null && other.getRutTrabajadorDig()==null) || 
             (this.rutTrabajadorDig!=null &&
              this.rutTrabajadorDig.equals(other.getRutTrabajadorDig())));
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
        if (getCodigoOperador() != null) {
            _hashCode += getCodigoOperador().hashCode();
        }
        if (getClaveOperador() != null) {
            _hashCode += getClaveOperador().hashCode();
        }
        if (getCodigoCCAF() != null) {
            _hashCode += getCodigoCCAF().hashCode();
        }
        if (getRutTrabajadorNum() != null) {
            _hashCode += getRutTrabajadorNum().hashCode();
        }
        if (getRutTrabajadorDig() != null) {
            _hashCode += getRutTrabajadorDig().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LMEValEmpCCAFRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "LMEValEmpCCAFRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoOperador");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "CodigoOperador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claveOperador");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "ClaveOperador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCCAF");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "CodigoCCAF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutTrabajadorNum");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "RutTrabajadorNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutTrabajadorDig");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "RutTrabajadorDig"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
