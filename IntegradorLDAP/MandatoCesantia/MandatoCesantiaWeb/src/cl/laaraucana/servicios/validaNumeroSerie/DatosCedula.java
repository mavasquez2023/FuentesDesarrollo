/**
 * DatosCedula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaNumeroSerie;

public class DatosCedula  implements java.io.Serializable {
    private java.lang.String RUT;

    private java.lang.String NUMERO_SERIE;

    public DatosCedula() {
    }

    public DatosCedula(
           java.lang.String RUT,
           java.lang.String NUMERO_SERIE) {
           this.RUT = RUT;
           this.NUMERO_SERIE = NUMERO_SERIE;
    }


    /**
     * Gets the RUT value for this DatosCedula.
     * 
     * @return RUT
     */
    public java.lang.String getRUT() {
        return RUT;
    }


    /**
     * Sets the RUT value for this DatosCedula.
     * 
     * @param RUT
     */
    public void setRUT(java.lang.String RUT) {
        this.RUT = RUT;
    }


    /**
     * Gets the NUMERO_SERIE value for this DatosCedula.
     * 
     * @return NUMERO_SERIE
     */
    public java.lang.String getNUMERO_SERIE() {
        return NUMERO_SERIE;
    }


    /**
     * Sets the NUMERO_SERIE value for this DatosCedula.
     * 
     * @param NUMERO_SERIE
     */
    public void setNUMERO_SERIE(java.lang.String NUMERO_SERIE) {
        this.NUMERO_SERIE = NUMERO_SERIE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosCedula)) return false;
        DatosCedula other = (DatosCedula) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUT==null && other.getRUT()==null) || 
             (this.RUT!=null &&
              this.RUT.equals(other.getRUT()))) &&
            ((this.NUMERO_SERIE==null && other.getNUMERO_SERIE()==null) || 
             (this.NUMERO_SERIE!=null &&
              this.NUMERO_SERIE.equals(other.getNUMERO_SERIE())));
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
        if (getRUT() != null) {
            _hashCode += getRUT().hashCode();
        }
        if (getNUMERO_SERIE() != null) {
            _hashCode += getNUMERO_SERIE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosCedula.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaNumeroSerie", "DatosCedula"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO_SERIE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUMERO_SERIE"));
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
