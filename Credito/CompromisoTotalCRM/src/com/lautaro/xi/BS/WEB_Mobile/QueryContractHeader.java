/**
 * QueryContractHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Contract Header
 */
public class QueryContractHeader  implements java.io.Serializable {
    private java.lang.String ACNUM_EXT;

    private java.lang.String RUT;

    public QueryContractHeader() {
    }

    public QueryContractHeader(
           java.lang.String ACNUM_EXT,
           java.lang.String RUT) {
           this.ACNUM_EXT = ACNUM_EXT;
           this.RUT = RUT;
    }


    /**
     * Gets the ACNUM_EXT value for this QueryContractHeader.
     * 
     * @return ACNUM_EXT
     */
    public java.lang.String getACNUM_EXT() {
        return ACNUM_EXT;
    }


    /**
     * Sets the ACNUM_EXT value for this QueryContractHeader.
     * 
     * @param ACNUM_EXT
     */
    public void setACNUM_EXT(java.lang.String ACNUM_EXT) {
        this.ACNUM_EXT = ACNUM_EXT;
    }


    /**
     * Gets the RUT value for this QueryContractHeader.
     * 
     * @return RUT
     */
    public java.lang.String getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this QueryContractHeader.
     * 
     * @param RUT
     */
    public void setRUT(java.lang.String RUT) {
        this.RUT = RUT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryContractHeader)) return false;
        QueryContractHeader other = (QueryContractHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ACNUM_EXT==null && other.getACNUM_EXT()==null) || 
             (this.ACNUM_EXT!=null &&
              this.ACNUM_EXT.equals(other.getACNUM_EXT()))) &&
            ((this.RUT==null && other.getRUT()==null) || 
             (this.RUT!=null &&
              this.RUT.equals(other.getRUT())));
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
        if (getACNUM_EXT() != null) {
            _hashCode += getACNUM_EXT().hashCode();
        }
        if (getRUT() != null) {
            _hashCode += getRUT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryContractHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryContractHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACNUM_EXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACNUM_EXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
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
