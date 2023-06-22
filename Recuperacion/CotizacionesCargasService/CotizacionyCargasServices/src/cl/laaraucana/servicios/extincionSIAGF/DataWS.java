/**
 * DataWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.extincionSIAGF;

public class DataWS  implements java.io.Serializable {
    private java.lang.String RUT_TRABAJADOR;

    private java.lang.String RUT_EMPRESA;

    private java.lang.String FECHA_EMISION;

    private java.lang.String FECHA_EXTINCION;

    public DataWS() {
    }

    public DataWS(
           java.lang.String RUT_TRABAJADOR,
           java.lang.String RUT_EMPRESA,
           java.lang.String FECHA_EMISION,
           java.lang.String FECHA_EXTINCION) {
           this.RUT_TRABAJADOR = RUT_TRABAJADOR;
           this.RUT_EMPRESA = RUT_EMPRESA;
           this.FECHA_EMISION = FECHA_EMISION;
           this.FECHA_EXTINCION = FECHA_EXTINCION;
    }


    /**
     * Gets the RUT_TRABAJADOR value for this DataWS.
     * 
     * @return RUT_TRABAJADOR
     */
    public java.lang.String getRUT_TRABAJADOR() {
        return RUT_TRABAJADOR;
    }


    /**
     * Sets the RUT_TRABAJADOR value for this DataWS.
     * 
     * @param RUT_TRABAJADOR
     */
    public void setRUT_TRABAJADOR(java.lang.String RUT_TRABAJADOR) {
        this.RUT_TRABAJADOR = RUT_TRABAJADOR;
    }


    /**
     * Gets the RUT_EMPRESA value for this DataWS.
     * 
     * @return RUT_EMPRESA
     */
    public java.lang.String getRUT_EMPRESA() {
        return RUT_EMPRESA;
    }


    /**
     * Sets the RUT_EMPRESA value for this DataWS.
     * 
     * @param RUT_EMPRESA
     */
    public void setRUT_EMPRESA(java.lang.String RUT_EMPRESA) {
        this.RUT_EMPRESA = RUT_EMPRESA;
    }


    /**
     * Gets the FECHA_EMISION value for this DataWS.
     * 
     * @return FECHA_EMISION
     */
    public java.lang.String getFECHA_EMISION() {
        return FECHA_EMISION;
    }


    /**
     * Sets the FECHA_EMISION value for this DataWS.
     * 
     * @param FECHA_EMISION
     */
    public void setFECHA_EMISION(java.lang.String FECHA_EMISION) {
        this.FECHA_EMISION = FECHA_EMISION;
    }


    /**
     * Gets the FECHA_EXTINCION value for this DataWS.
     * 
     * @return FECHA_EXTINCION
     */
    public java.lang.String getFECHA_EXTINCION() {
        return FECHA_EXTINCION;
    }


    /**
     * Sets the FECHA_EXTINCION value for this DataWS.
     * 
     * @param FECHA_EXTINCION
     */
    public void setFECHA_EXTINCION(java.lang.String FECHA_EXTINCION) {
        this.FECHA_EXTINCION = FECHA_EXTINCION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataWS)) return false;
        DataWS other = (DataWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUT_TRABAJADOR==null && other.getRUT_TRABAJADOR()==null) || 
             (this.RUT_TRABAJADOR!=null &&
              this.RUT_TRABAJADOR.equals(other.getRUT_TRABAJADOR()))) &&
            ((this.RUT_EMPRESA==null && other.getRUT_EMPRESA()==null) || 
             (this.RUT_EMPRESA!=null &&
              this.RUT_EMPRESA.equals(other.getRUT_EMPRESA()))) &&
            ((this.FECHA_EMISION==null && other.getFECHA_EMISION()==null) || 
             (this.FECHA_EMISION!=null &&
              this.FECHA_EMISION.equals(other.getFECHA_EMISION()))) &&
            ((this.FECHA_EXTINCION==null && other.getFECHA_EXTINCION()==null) || 
             (this.FECHA_EXTINCION!=null &&
              this.FECHA_EXTINCION.equals(other.getFECHA_EXTINCION())));
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
        if (getRUT_TRABAJADOR() != null) {
            _hashCode += getRUT_TRABAJADOR().hashCode();
        }
        if (getRUT_EMPRESA() != null) {
            _hashCode += getRUT_EMPRESA().hashCode();
        }
        if (getFECHA_EMISION() != null) {
            _hashCode += getFECHA_EMISION().hashCode();
        }
        if (getFECHA_EXTINCION() != null) {
            _hashCode += getFECHA_EXTINCION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataWS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/extincionSIAGF", "dataWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT_TRABAJADOR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT_TRABAJADOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT_EMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT_EMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_EMISION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_EMISION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_EXTINCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA_EXTINCION"));
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
