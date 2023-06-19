/**
 * RESUMEN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.wsdl.SDN122RES;

public class RESUMEN  implements java.io.Serializable {
    private java.lang.String IDCHALLENGE;

    private java.lang.String USUARIO;

    private java.lang.String RESULTADO;

    public RESUMEN() {
    }

    public RESUMEN(
           java.lang.String IDCHALLENGE,
           java.lang.String USUARIO,
           java.lang.String RESULTADO) {
           this.IDCHALLENGE = IDCHALLENGE;
           this.USUARIO = USUARIO;
           this.RESULTADO = RESULTADO;
    }


    /**
     * Gets the IDCHALLENGE value for this RESUMEN.
     * 
     * @return IDCHALLENGE
     */
    public java.lang.String getIDCHALLENGE() {
        return IDCHALLENGE;
    }


    /**
     * Sets the IDCHALLENGE value for this RESUMEN.
     * 
     * @param IDCHALLENGE
     */
    public void setIDCHALLENGE(java.lang.String IDCHALLENGE) {
        this.IDCHALLENGE = IDCHALLENGE;
    }


    /**
     * Gets the USUARIO value for this RESUMEN.
     * 
     * @return USUARIO
     */
    public java.lang.String getUSUARIO() {
        return USUARIO;
    }


    /**
     * Sets the USUARIO value for this RESUMEN.
     * 
     * @param USUARIO
     */
    public void setUSUARIO(java.lang.String USUARIO) {
        this.USUARIO = USUARIO;
    }


    /**
     * Gets the RESULTADO value for this RESUMEN.
     * 
     * @return RESULTADO
     */
    public java.lang.String getRESULTADO() {
        return RESULTADO;
    }


    /**
     * Sets the RESULTADO value for this RESUMEN.
     * 
     * @param RESULTADO
     */
    public void setRESULTADO(java.lang.String RESULTADO) {
        this.RESULTADO = RESULTADO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RESUMEN)) return false;
        RESUMEN other = (RESUMEN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IDCHALLENGE==null && other.getIDCHALLENGE()==null) || 
             (this.IDCHALLENGE!=null &&
              this.IDCHALLENGE.equals(other.getIDCHALLENGE()))) &&
            ((this.USUARIO==null && other.getUSUARIO()==null) || 
             (this.USUARIO!=null &&
              this.USUARIO.equals(other.getUSUARIO()))) &&
            ((this.RESULTADO==null && other.getRESULTADO()==null) || 
             (this.RESULTADO!=null &&
              this.RESULTADO.equals(other.getRESULTADO())));
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
        if (getIDCHALLENGE() != null) {
            _hashCode += getIDCHALLENGE().hashCode();
        }
        if (getUSUARIO() != null) {
            _hashCode += getUSUARIO().hashCode();
        }
        if (getRESULTADO() != null) {
            _hashCode += getRESULTADO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RESUMEN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122RES", "RESUMEN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDCHALLENGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122RES", "IDCHALLENGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122RES", "USUARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESULTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN122RES", "RESULTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
