/**
 * CuentaSearch.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.transferencias;

public class CuentaSearch  implements java.io.Serializable {
    private int RUT;

    private java.lang.String DV;

    public CuentaSearch() {
    }

    public CuentaSearch(
           int RUT,
           java.lang.String DV) {
           this.RUT = RUT;
           this.DV = DV;
    }


    /**
     * Gets the RUT value for this CuentaSearch.
     * 
     * @return RUT
     */
    public int getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this CuentaSearch.
     * 
     * @param RUT
     */
    public void setRUT(int RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the DV value for this CuentaSearch.
     * 
     * @return DV
     */
    public java.lang.String getDV() {
        return DV;
    }


    /**
     * Sets the DV value for this CuentaSearch.
     * 
     * @param DV
     */
    public void setDV(java.lang.String DV) {
        this.DV = DV;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CuentaSearch)) return false;
        CuentaSearch other = (CuentaSearch) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.RUT == other.getRUT() &&
            ((this.DV==null && other.getDV()==null) || 
             (this.DV!=null &&
              this.DV.equals(other.getDV())));
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
        _hashCode += getRUT();
        if (getDV() != null) {
            _hashCode += getDV().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CuentaSearch.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/cuentabancaria", "cuentaSearch"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DV"));
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
