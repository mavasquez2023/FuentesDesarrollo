/**
 * ErrorResultVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.aporte.orqOutput.cliente.service.vo;

public class ErrorResultVO  implements java.io.Serializable {
    private int codError;

    private java.lang.String glsError;

    public ErrorResultVO() {
    }

    public ErrorResultVO(
           int codError,
           java.lang.String glsError) {
           this.codError = codError;
           this.glsError = glsError;
    }


    /**
     * Gets the codError value for this ErrorResultVO.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this ErrorResultVO.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the glsError value for this ErrorResultVO.
     * 
     * @return glsError
     */
    public java.lang.String getGlsError() {
        return glsError;
    }


    /**
     * Sets the glsError value for this ErrorResultVO.
     * 
     * @param glsError
     */
    public void setGlsError(java.lang.String glsError) {
        this.glsError = glsError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorResultVO)) return false;
        ErrorResultVO other = (ErrorResultVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codError == other.getCodError() &&
            ((this.glsError==null && other.getGlsError()==null) || 
             (this.glsError!=null &&
              this.glsError.equals(other.getGlsError())));
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
        _hashCode += getCodError();
        if (getGlsError() != null) {
            _hashCode += getGlsError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorResultVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "ErrorResultVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "codError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("glsError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "glsError"));
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
