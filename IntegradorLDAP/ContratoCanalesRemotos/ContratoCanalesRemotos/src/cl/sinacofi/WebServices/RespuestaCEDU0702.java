/**
 * RespuestaCEDU0702.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.WebServices;

public class RespuestaCEDU0702  implements java.io.Serializable {
    private int codigoRetorno;

    private cl.sinacofi.wsdl.SDN125RES.RESUMEN RESUMEN;

    private cl.sinacofi.wsdl.SDN125RES.DETALLE DETALLE;

    public RespuestaCEDU0702() {
    }

    public RespuestaCEDU0702(
           int codigoRetorno,
           cl.sinacofi.wsdl.SDN125RES.RESUMEN RESUMEN,
           cl.sinacofi.wsdl.SDN125RES.DETALLE DETALLE) {
           this.codigoRetorno = codigoRetorno;
           this.RESUMEN = RESUMEN;
           this.DETALLE = DETALLE;
    }


    /**
     * Gets the codigoRetorno value for this RespuestaCEDU0702.
     * 
     * @return codigoRetorno
     */
    public int getCodigoRetorno() {
        return codigoRetorno;
    }


    /**
     * Sets the codigoRetorno value for this RespuestaCEDU0702.
     * 
     * @param codigoRetorno
     */
    public void setCodigoRetorno(int codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }


    /**
     * Gets the RESUMEN value for this RespuestaCEDU0702.
     * 
     * @return RESUMEN
     */
    public cl.sinacofi.wsdl.SDN125RES.RESUMEN getRESUMEN() {
        return RESUMEN;
    }


    /**
     * Sets the RESUMEN value for this RespuestaCEDU0702.
     * 
     * @param RESUMEN
     */
    public void setRESUMEN(cl.sinacofi.wsdl.SDN125RES.RESUMEN RESUMEN) {
        this.RESUMEN = RESUMEN;
    }


    /**
     * Gets the DETALLE value for this RespuestaCEDU0702.
     * 
     * @return DETALLE
     */
    public cl.sinacofi.wsdl.SDN125RES.DETALLE getDETALLE() {
        return DETALLE;
    }


    /**
     * Sets the DETALLE value for this RespuestaCEDU0702.
     * 
     * @param DETALLE
     */
    public void setDETALLE(cl.sinacofi.wsdl.SDN125RES.DETALLE DETALLE) {
        this.DETALLE = DETALLE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaCEDU0702)) return false;
        RespuestaCEDU0702 other = (RespuestaCEDU0702) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codigoRetorno == other.getCodigoRetorno() &&
            ((this.RESUMEN==null && other.getRESUMEN()==null) || 
             (this.RESUMEN!=null &&
              this.RESUMEN.equals(other.getRESUMEN()))) &&
            ((this.DETALLE==null && other.getDETALLE()==null) || 
             (this.DETALLE!=null &&
              this.DETALLE.equals(other.getDETALLE())));
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
        _hashCode += getCodigoRetorno();
        if (getRESUMEN() != null) {
            _hashCode += getRESUMEN().hashCode();
        }
        if (getDETALLE() != null) {
            _hashCode += getDETALLE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaCEDU0702.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "respuestaCEDU0702"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "codigoRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESUMEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "RESUMEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "RESUMEN"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DETALLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "DETALLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.sinacofi.cl/SDN125RES", "DETALLE"));
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
