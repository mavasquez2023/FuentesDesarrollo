/**
 * ResultadoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.autoconsulta.ws.to;

public class ResultadoTO  implements java.io.Serializable {
    private cl.araucana.autoconsulta.ws.to.CertificadoTO certificadoTO;

    private int codigo;

    private java.lang.String error;

    public ResultadoTO() {
    }

    public ResultadoTO(
           cl.araucana.autoconsulta.ws.to.CertificadoTO certificadoTO,
           int codigo,
           java.lang.String error) {
           this.certificadoTO = certificadoTO;
           this.codigo = codigo;
           this.error = error;
    }


    /**
     * Gets the certificadoTO value for this ResultadoTO.
     * 
     * @return certificadoTO
     */
    public cl.araucana.autoconsulta.ws.to.CertificadoTO getCertificadoTO() {
        return certificadoTO;
    }


    /**
     * Sets the certificadoTO value for this ResultadoTO.
     * 
     * @param certificadoTO
     */
    public void setCertificadoTO(cl.araucana.autoconsulta.ws.to.CertificadoTO certificadoTO) {
        this.certificadoTO = certificadoTO;
    }


    /**
     * Gets the codigo value for this ResultadoTO.
     * 
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this ResultadoTO.
     * 
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the error value for this ResultadoTO.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this ResultadoTO.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoTO)) return false;
        ResultadoTO other = (ResultadoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.certificadoTO==null && other.getCertificadoTO()==null) || 
             (this.certificadoTO!=null &&
              this.certificadoTO.equals(other.getCertificadoTO()))) &&
            this.codigo == other.getCodigo() &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError())));
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
        if (getCertificadoTO() != null) {
            _hashCode += getCertificadoTO().hashCode();
        }
        _hashCode += getCodigo();
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.ws.araucana.cl", "ResultadoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificadoTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificadoTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.ws.araucana.cl", "CertificadoTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
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
