/**
 * OrqOutputResultVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.aporte.orqOutput.cliente.service.vo;

public class OrqOutputResultVO  implements java.io.Serializable {
    private cl.araucana.aporte.orqOutput.cliente.service.vo.ErrorResultVO errorVO;

    public OrqOutputResultVO() {
    }

    public OrqOutputResultVO(
           cl.araucana.aporte.orqOutput.cliente.service.vo.ErrorResultVO errorVO) {
           this.errorVO = errorVO;
    }


    /**
     * Gets the errorVO value for this OrqOutputResultVO.
     * 
     * @return errorVO
     */
    public cl.araucana.aporte.orqOutput.cliente.service.vo.ErrorResultVO getErrorVO() {
        return errorVO;
    }


    /**
     * Sets the errorVO value for this OrqOutputResultVO.
     * 
     * @param errorVO
     */
    public void setErrorVO(cl.araucana.aporte.orqOutput.cliente.service.vo.ErrorResultVO errorVO) {
        this.errorVO = errorVO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrqOutputResultVO)) return false;
        OrqOutputResultVO other = (OrqOutputResultVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorVO==null && other.getErrorVO()==null) || 
             (this.errorVO!=null &&
              this.errorVO.equals(other.getErrorVO())));
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
        if (getErrorVO() != null) {
            _hashCode += getErrorVO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrqOutputResultVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "OrqOutputResultVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "errorVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.service.orqOutput.aporte.araucana.cl", "ErrorResultVO"));
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
