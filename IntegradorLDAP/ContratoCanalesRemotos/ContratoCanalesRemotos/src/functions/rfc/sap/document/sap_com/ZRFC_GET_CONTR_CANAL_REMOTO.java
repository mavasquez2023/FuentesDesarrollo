/**
 * ZRFC_GET_CONTR_CANAL_REMOTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package functions.rfc.sap.document.sap_com;

public class ZRFC_GET_CONTR_CANAL_REMOTO  implements java.io.Serializable {
    private java.lang.String IV_RUT;

    public ZRFC_GET_CONTR_CANAL_REMOTO() {
    }

    public ZRFC_GET_CONTR_CANAL_REMOTO(
           java.lang.String IV_RUT) {
           this.IV_RUT = IV_RUT;
    }


    /**
     * Gets the IV_RUT value for this ZRFC_GET_CONTR_CANAL_REMOTO.
     * 
     * @return IV_RUT
     */
    public java.lang.String getIV_RUT() {
        return IV_RUT;
    }


    /**
     * Sets the IV_RUT value for this ZRFC_GET_CONTR_CANAL_REMOTO.
     * 
     * @param IV_RUT
     */
    public void setIV_RUT(java.lang.String IV_RUT) {
        this.IV_RUT = IV_RUT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZRFC_GET_CONTR_CANAL_REMOTO)) return false;
        ZRFC_GET_CONTR_CANAL_REMOTO other = (ZRFC_GET_CONTR_CANAL_REMOTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IV_RUT==null && other.getIV_RUT()==null) || 
             (this.IV_RUT!=null &&
              this.IV_RUT.equals(other.getIV_RUT())));
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
        if (getIV_RUT() != null) {
            _hashCode += getIV_RUT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZRFC_GET_CONTR_CANAL_REMOTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", ">ZRFC_GET_CONTR_CANAL_REMOTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IV_RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IV_RUT"));
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
