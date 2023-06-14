/**
 * BOLETADefTypeDscRcgGlobalIndExeDR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeDscRcgGlobalIndExeDR implements java.io.Serializable {
    private org.apache.axis.types.PositiveInteger _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected BOLETADefTypeDscRcgGlobalIndExeDR(org.apache.axis.types.PositiveInteger value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final org.apache.axis.types.PositiveInteger _value1 = new org.apache.axis.types.PositiveInteger("1");
    public static final org.apache.axis.types.PositiveInteger _value2 = new org.apache.axis.types.PositiveInteger("2");
    public static final BOLETADefTypeDscRcgGlobalIndExeDR value1 = new BOLETADefTypeDscRcgGlobalIndExeDR(_value1);
    public static final BOLETADefTypeDscRcgGlobalIndExeDR value2 = new BOLETADefTypeDscRcgGlobalIndExeDR(_value2);
    public org.apache.axis.types.PositiveInteger getValue() { return _value_;}
    public static BOLETADefTypeDscRcgGlobalIndExeDR fromValue(org.apache.axis.types.PositiveInteger value)
          throws java.lang.IllegalArgumentException {
        BOLETADefTypeDscRcgGlobalIndExeDR enumeration = (BOLETADefTypeDscRcgGlobalIndExeDR)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static BOLETADefTypeDscRcgGlobalIndExeDR fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(new org.apache.axis.types.PositiveInteger(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_.toString();}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeDscRcgGlobalIndExeDR.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>DscRcgGlobal>IndExeDR"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
