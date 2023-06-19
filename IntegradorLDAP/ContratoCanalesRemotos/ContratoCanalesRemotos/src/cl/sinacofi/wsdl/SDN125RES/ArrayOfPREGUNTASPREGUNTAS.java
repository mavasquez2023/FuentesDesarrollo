/**
 * ArrayOfPREGUNTASPREGUNTAS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.wsdl.SDN125RES;

public class ArrayOfPREGUNTASPREGUNTAS  implements java.io.Serializable {
    private java.lang.String CODIGO_PREGUNTA;

    private java.lang.String PREGUNTA;

    private cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS[] ALTERNATIVAS;

    public ArrayOfPREGUNTASPREGUNTAS() {
    }

    public ArrayOfPREGUNTASPREGUNTAS(
           java.lang.String CODIGO_PREGUNTA,
           java.lang.String PREGUNTA,
           cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS[] ALTERNATIVAS) {
           this.CODIGO_PREGUNTA = CODIGO_PREGUNTA;
           this.PREGUNTA = PREGUNTA;
           this.ALTERNATIVAS = ALTERNATIVAS;
    }


    /**
     * Gets the CODIGO_PREGUNTA value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @return CODIGO_PREGUNTA
     */
    public java.lang.String getCODIGO_PREGUNTA() {
        return CODIGO_PREGUNTA;
    }


    /**
     * Sets the CODIGO_PREGUNTA value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @param CODIGO_PREGUNTA
     */
    public void setCODIGO_PREGUNTA(java.lang.String CODIGO_PREGUNTA) {
        this.CODIGO_PREGUNTA = CODIGO_PREGUNTA;
    }


    /**
     * Gets the PREGUNTA value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @return PREGUNTA
     */
    public java.lang.String getPREGUNTA() {
        return PREGUNTA;
    }


    /**
     * Sets the PREGUNTA value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @param PREGUNTA
     */
    public void setPREGUNTA(java.lang.String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }


    /**
     * Gets the ALTERNATIVAS value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @return ALTERNATIVAS
     */
    public cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS[] getALTERNATIVAS() {
        return ALTERNATIVAS;
    }


    /**
     * Sets the ALTERNATIVAS value for this ArrayOfPREGUNTASPREGUNTAS.
     * 
     * @param ALTERNATIVAS
     */
    public void setALTERNATIVAS(cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS[] ALTERNATIVAS) {
        this.ALTERNATIVAS = ALTERNATIVAS;
    }

    public cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS getALTERNATIVAS(int i) {
        return this.ALTERNATIVAS[i];
    }

    public void setALTERNATIVAS(int i, cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS _value) {
        this.ALTERNATIVAS[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfPREGUNTASPREGUNTAS)) return false;
        ArrayOfPREGUNTASPREGUNTAS other = (ArrayOfPREGUNTASPREGUNTAS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODIGO_PREGUNTA==null && other.getCODIGO_PREGUNTA()==null) || 
             (this.CODIGO_PREGUNTA!=null &&
              this.CODIGO_PREGUNTA.equals(other.getCODIGO_PREGUNTA()))) &&
            ((this.PREGUNTA==null && other.getPREGUNTA()==null) || 
             (this.PREGUNTA!=null &&
              this.PREGUNTA.equals(other.getPREGUNTA()))) &&
            ((this.ALTERNATIVAS==null && other.getALTERNATIVAS()==null) || 
             (this.ALTERNATIVAS!=null &&
              java.util.Arrays.equals(this.ALTERNATIVAS, other.getALTERNATIVAS())));
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
        if (getCODIGO_PREGUNTA() != null) {
            _hashCode += getCODIGO_PREGUNTA().hashCode();
        }
        if (getPREGUNTA() != null) {
            _hashCode += getPREGUNTA().hashCode();
        }
        if (getALTERNATIVAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getALTERNATIVAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getALTERNATIVAS(), i);
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
        new org.apache.axis.description.TypeDesc(ArrayOfPREGUNTASPREGUNTAS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", ">ArrayOfPREGUNTAS>PREGUNTAS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_PREGUNTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "CODIGO_PREGUNTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PREGUNTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "PREGUNTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALTERNATIVAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "ALTERNATIVAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", ">>ArrayOfPREGUNTAS>PREGUNTAS>ALTERNATIVAS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
