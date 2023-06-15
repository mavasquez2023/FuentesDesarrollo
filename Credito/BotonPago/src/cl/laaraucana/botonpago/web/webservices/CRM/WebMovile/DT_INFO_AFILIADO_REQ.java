/**
 * DT_INFO_AFILIADO_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.botonpago.web.webservices.CRM.WebMovile;


/**
 * 8000003746
 */
public class DT_INFO_AFILIADO_REQ  implements java.io.Serializable {
    private java.lang.String RUT_BP;

    public DT_INFO_AFILIADO_REQ() {
    }

    public DT_INFO_AFILIADO_REQ(
           java.lang.String RUT_BP) {
           this.RUT_BP = RUT_BP;
    }


    /**
     * Gets the RUT_BP value for this DT_INFO_AFILIADO_REQ.
     * 
     * @return RUT_BP
     */
    public java.lang.String getRUT_BP() {
        return RUT_BP;
    }


    /**
     * Sets the RUT_BP value for this DT_INFO_AFILIADO_REQ.
     * 
     * @param RUT_BP
     */
    public void setRUT_BP(java.lang.String RUT_BP) {
        this.RUT_BP = RUT_BP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_INFO_AFILIADO_REQ)) return false;
        DT_INFO_AFILIADO_REQ other = (DT_INFO_AFILIADO_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUT_BP==null && other.getRUT_BP()==null) || 
             (this.RUT_BP!=null &&
              this.RUT_BP.equals(other.getRUT_BP())));
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
        if (getRUT_BP() != null) {
            _hashCode += getRUT_BP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_INFO_AFILIADO_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "DT_INFO_AFILIADO_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT_BP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT_BP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
