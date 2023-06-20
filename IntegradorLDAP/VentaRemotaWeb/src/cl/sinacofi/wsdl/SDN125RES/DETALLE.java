/**
 * DETALLE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.wsdl.SDN125RES;

public class DETALLE  implements java.io.Serializable {
    private cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTAS[] DESAFIO;

    public DETALLE() {
    }

    public DETALLE(
           cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTAS[] DESAFIO) {
           this.DESAFIO = DESAFIO;
    }


    /**
     * Gets the DESAFIO value for this DETALLE.
     * 
     * @return DESAFIO
     */
    public cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTAS[] getDESAFIO() {
        return DESAFIO;
    }


    /**
     * Sets the DESAFIO value for this DETALLE.
     * 
     * @param DESAFIO
     */
    public void setDESAFIO(cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTAS[] DESAFIO) {
        this.DESAFIO = DESAFIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DETALLE)) return false;
        DETALLE other = (DETALLE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DESAFIO==null && other.getDESAFIO()==null) || 
             (this.DESAFIO!=null &&
              java.util.Arrays.equals(this.DESAFIO, other.getDESAFIO())));
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
        if (getDESAFIO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDESAFIO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDESAFIO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DETALLE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "DETALLE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESAFIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "DESAFIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", ">ArrayOfPREGUNTAS>PREGUNTAS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "PREGUNTAS"));
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
