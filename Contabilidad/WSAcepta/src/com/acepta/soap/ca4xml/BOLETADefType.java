/**
 * BOLETADefType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefType  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezado encabezado;

    private com.acepta.soap.ca4xml.BOLETADefTypeDetalle[] detalle;

    private com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo[] subTotInfo;

    private com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal[] dscRcgGlobal;

    private com.acepta.soap.ca4xml.BOLETADefTypeReferencia[] referencia;

    private java.util.Calendar tmstFirma;

    private com.acepta.soap.ca4xml.BOLETADefTypeAdjuntos adjuntos;

    public BOLETADefType() {
    }

    public BOLETADefType(
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezado encabezado,
           com.acepta.soap.ca4xml.BOLETADefTypeDetalle[] detalle,
           com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo[] subTotInfo,
           com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal[] dscRcgGlobal,
           com.acepta.soap.ca4xml.BOLETADefTypeReferencia[] referencia,
           java.util.Calendar tmstFirma,
           com.acepta.soap.ca4xml.BOLETADefTypeAdjuntos adjuntos) {
           this.encabezado = encabezado;
           this.detalle = detalle;
           this.subTotInfo = subTotInfo;
           this.dscRcgGlobal = dscRcgGlobal;
           this.referencia = referencia;
           this.tmstFirma = tmstFirma;
           this.adjuntos = adjuntos;
    }


    /**
     * Gets the encabezado value for this BOLETADefType.
     * 
     * @return encabezado
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezado getEncabezado() {
        return encabezado;
    }


    /**
     * Sets the encabezado value for this BOLETADefType.
     * 
     * @param encabezado
     */
    public void setEncabezado(com.acepta.soap.ca4xml.BOLETADefTypeEncabezado encabezado) {
        this.encabezado = encabezado;
    }


    /**
     * Gets the detalle value for this BOLETADefType.
     * 
     * @return detalle
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDetalle[] getDetalle() {
        return detalle;
    }


    /**
     * Sets the detalle value for this BOLETADefType.
     * 
     * @param detalle
     */
    public void setDetalle(com.acepta.soap.ca4xml.BOLETADefTypeDetalle[] detalle) {
        this.detalle = detalle;
    }

    public com.acepta.soap.ca4xml.BOLETADefTypeDetalle getDetalle(int i) {
        return this.detalle[i];
    }

    public void setDetalle(int i, com.acepta.soap.ca4xml.BOLETADefTypeDetalle _value) {
        this.detalle[i] = _value;
    }


    /**
     * Gets the subTotInfo value for this BOLETADefType.
     * 
     * @return subTotInfo
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo[] getSubTotInfo() {
        return subTotInfo;
    }


    /**
     * Sets the subTotInfo value for this BOLETADefType.
     * 
     * @param subTotInfo
     */
    public void setSubTotInfo(com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo[] subTotInfo) {
        this.subTotInfo = subTotInfo;
    }

    public com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo getSubTotInfo(int i) {
        return this.subTotInfo[i];
    }

    public void setSubTotInfo(int i, com.acepta.soap.ca4xml.BOLETADefTypeSubTotInfo _value) {
        this.subTotInfo[i] = _value;
    }


    /**
     * Gets the dscRcgGlobal value for this BOLETADefType.
     * 
     * @return dscRcgGlobal
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal[] getDscRcgGlobal() {
        return dscRcgGlobal;
    }


    /**
     * Sets the dscRcgGlobal value for this BOLETADefType.
     * 
     * @param dscRcgGlobal
     */
    public void setDscRcgGlobal(com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal[] dscRcgGlobal) {
        this.dscRcgGlobal = dscRcgGlobal;
    }

    public com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal getDscRcgGlobal(int i) {
        return this.dscRcgGlobal[i];
    }

    public void setDscRcgGlobal(int i, com.acepta.soap.ca4xml.BOLETADefTypeDscRcgGlobal _value) {
        this.dscRcgGlobal[i] = _value;
    }


    /**
     * Gets the referencia value for this BOLETADefType.
     * 
     * @return referencia
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeReferencia[] getReferencia() {
        return referencia;
    }


    /**
     * Sets the referencia value for this BOLETADefType.
     * 
     * @param referencia
     */
    public void setReferencia(com.acepta.soap.ca4xml.BOLETADefTypeReferencia[] referencia) {
        this.referencia = referencia;
    }

    public com.acepta.soap.ca4xml.BOLETADefTypeReferencia getReferencia(int i) {
        return this.referencia[i];
    }

    public void setReferencia(int i, com.acepta.soap.ca4xml.BOLETADefTypeReferencia _value) {
        this.referencia[i] = _value;
    }


    /**
     * Gets the tmstFirma value for this BOLETADefType.
     * 
     * @return tmstFirma
     */
    public java.util.Calendar getTmstFirma() {
        return tmstFirma;
    }


    /**
     * Sets the tmstFirma value for this BOLETADefType.
     * 
     * @param tmstFirma
     */
    public void setTmstFirma(java.util.Calendar tmstFirma) {
        this.tmstFirma = tmstFirma;
    }


    /**
     * Gets the adjuntos value for this BOLETADefType.
     * 
     * @return adjuntos
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeAdjuntos getAdjuntos() {
        return adjuntos;
    }


    /**
     * Sets the adjuntos value for this BOLETADefType.
     * 
     * @param adjuntos
     */
    public void setAdjuntos(com.acepta.soap.ca4xml.BOLETADefTypeAdjuntos adjuntos) {
        this.adjuntos = adjuntos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefType)) return false;
        BOLETADefType other = (BOLETADefType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.encabezado==null && other.getEncabezado()==null) || 
             (this.encabezado!=null &&
              this.encabezado.equals(other.getEncabezado()))) &&
            ((this.detalle==null && other.getDetalle()==null) || 
             (this.detalle!=null &&
              java.util.Arrays.equals(this.detalle, other.getDetalle()))) &&
            ((this.subTotInfo==null && other.getSubTotInfo()==null) || 
             (this.subTotInfo!=null &&
              java.util.Arrays.equals(this.subTotInfo, other.getSubTotInfo()))) &&
            ((this.dscRcgGlobal==null && other.getDscRcgGlobal()==null) || 
             (this.dscRcgGlobal!=null &&
              java.util.Arrays.equals(this.dscRcgGlobal, other.getDscRcgGlobal()))) &&
            ((this.referencia==null && other.getReferencia()==null) || 
             (this.referencia!=null &&
              java.util.Arrays.equals(this.referencia, other.getReferencia()))) &&
            ((this.tmstFirma==null && other.getTmstFirma()==null) || 
             (this.tmstFirma!=null &&
              this.tmstFirma.equals(other.getTmstFirma()))) &&
            ((this.adjuntos==null && other.getAdjuntos()==null) || 
             (this.adjuntos!=null &&
              this.adjuntos.equals(other.getAdjuntos())));
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
        if (getEncabezado() != null) {
            _hashCode += getEncabezado().hashCode();
        }
        if (getDetalle() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalle());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalle(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubTotInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubTotInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubTotInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDscRcgGlobal() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDscRcgGlobal());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDscRcgGlobal(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReferencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReferencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReferencia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTmstFirma() != null) {
            _hashCode += getTmstFirma().hashCode();
        }
        if (getAdjuntos() != null) {
            _hashCode += getAdjuntos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "BOLETADefType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encabezado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Encabezado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Encabezado"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Detalle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Detalle"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTotInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubTotInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>SubTotInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscRcgGlobal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DscRcgGlobal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>DscRcgGlobal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Referencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Referencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tmstFirma");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TmstFirma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjuntos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Adjuntos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Adjuntos"));
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
