/**
 * BOLETADefTypeEncabezadoIdDocIndServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeEncabezadoIdDocIndServicio implements java.io.Serializable {
    private org.apache.axis.types.PositiveInteger _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected BOLETADefTypeEncabezadoIdDocIndServicio(org.apache.axis.types.PositiveInteger value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final org.apache.axis.types.PositiveInteger _value1 = new org.apache.axis.types.PositiveInteger("1");
    public static final org.apache.axis.types.PositiveInteger _value2 = new org.apache.axis.types.PositiveInteger("2");
    public static final org.apache.axis.types.PositiveInteger _value3 = new org.apache.axis.types.PositiveInteger("3");
    public static final org.apache.axis.types.PositiveInteger _value4 = new org.apache.axis.types.PositiveInteger("4");
    public static final BOLETADefTypeEncabezadoIdDocIndServicio value1 = new BOLETADefTypeEncabezadoIdDocIndServicio(_value1);
    public static final BOLETADefTypeEncabezadoIdDocIndServicio value2 = new BOLETADefTypeEncabezadoIdDocIndServicio(_value2);
    public static final BOLETADefTypeEncabezadoIdDocIndServicio value3 = new BOLETADefTypeEncabezadoIdDocIndServicio(_value3);
    public static final BOLETADefTypeEncabezadoIdDocIndServicio value4 = new BOLETADefTypeEncabezadoIdDocIndServicio(_value4);
    public org.apache.axis.types.PositiveInteger getValue() { return _value_;}
    public static BOLETADefTypeEncabezadoIdDocIndServicio fromValue(org.apache.axis.types.PositiveInteger value)
          throws java.lang.IllegalArgumentException {
        BOLETADefTypeEncabezadoIdDocIndServicio enumeration = (BOLETADefTypeEncabezadoIdDocIndServicio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static BOLETADefTypeEncabezadoIdDocIndServicio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(BOLETADefTypeEncabezadoIdDocIndServicio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>>BOLETADefType>Encabezado>IdDoc>IndServicio"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
