/**
 * Ia_CargaFirmaWebFS_DT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class Ia_CargaFirmaWebFS_DT  implements java.io.Serializable {
    private java.lang.String NUM_OFERTA;

    private java.lang.String ESTADO;

    private java.lang.String FECHA;

    private java.lang.String HORA;

    public Ia_CargaFirmaWebFS_DT() {
    }

    public Ia_CargaFirmaWebFS_DT(
           java.lang.String NUM_OFERTA,
           java.lang.String ESTADO,
           java.lang.String FECHA,
           java.lang.String HORA) {
           this.NUM_OFERTA = NUM_OFERTA;
           this.ESTADO = ESTADO;
           this.FECHA = FECHA;
           this.HORA = HORA;
    }


    /**
     * Gets the NUM_OFERTA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @return NUM_OFERTA
     */
    public java.lang.String getNUM_OFERTA() {
        return NUM_OFERTA;
    }


    /**
     * Sets the NUM_OFERTA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @param NUM_OFERTA
     */
    public void setNUM_OFERTA(java.lang.String NUM_OFERTA) {
        this.NUM_OFERTA = NUM_OFERTA;
    }


    /**
     * Gets the ESTADO value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @return ESTADO
     */
    public java.lang.String getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @param ESTADO
     */
    public void setESTADO(java.lang.String ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the FECHA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @return FECHA
     */
    public java.lang.String getFECHA() {
        return FECHA;
    }


    /**
     * Sets the FECHA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @param FECHA
     */
    public void setFECHA(java.lang.String FECHA) {
        this.FECHA = FECHA;
    }


    /**
     * Gets the HORA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @return HORA
     */
    public java.lang.String getHORA() {
        return HORA;
    }


    /**
     * Sets the HORA value for this Ia_CargaFirmaWebFS_DT.
     * 
     * @param HORA
     */
    public void setHORA(java.lang.String HORA) {
        this.HORA = HORA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ia_CargaFirmaWebFS_DT)) return false;
        Ia_CargaFirmaWebFS_DT other = (Ia_CargaFirmaWebFS_DT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUM_OFERTA==null && other.getNUM_OFERTA()==null) || 
             (this.NUM_OFERTA!=null &&
              this.NUM_OFERTA.equals(other.getNUM_OFERTA()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.FECHA==null && other.getFECHA()==null) || 
             (this.FECHA!=null &&
              this.FECHA.equals(other.getFECHA()))) &&
            ((this.HORA==null && other.getHORA()==null) || 
             (this.HORA!=null &&
              this.HORA.equals(other.getHORA())));
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
        if (getNUM_OFERTA() != null) {
            _hashCode += getNUM_OFERTA().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getFECHA() != null) {
            _hashCode += getFECHA().hashCode();
        }
        if (getHORA() != null) {
            _hashCode += getHORA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ia_CargaFirmaWebFS_DT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "ia_CargaFirmaWebFS_DT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_OFERTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUM_OFERTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FECHA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HORA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HORA"));
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
