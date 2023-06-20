/**
 * BOLETADefTypeSubTotInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeSubTotInfo  implements java.io.Serializable {
    /* Número de Subtotal */
    private org.apache.axis.types.PositiveInteger nroSTI;

    /* Glosa */
    private java.lang.String glosaSTI;

    /* Ubicación para Impresión */
    private org.apache.axis.types.PositiveInteger ordenSTI;

    /* Valor Neto del Subtotal */
    private java.math.BigDecimal subTotNetoSTI;

    /* Valor del IVA del Subtotal */
    private java.math.BigDecimal subTotIVASTI;

    /* Valor de los Impuestos adicionales o específicos del Subtotal */
    private java.math.BigDecimal subTotAdicSTI;

    /* Valor no Afecto o Exento del Subtotal */
    private java.math.BigDecimal subTotExeSTI;

    /* Valor de la línea de subtotal */
    private java.math.BigDecimal valSubtotSTI;

    /* TABLA de  Líneas de Detalle que se agrupan en el Subtotal */
    private org.apache.axis.types.PositiveInteger[] lineasDeta;

    public BOLETADefTypeSubTotInfo() {
    }

    public BOLETADefTypeSubTotInfo(
           org.apache.axis.types.PositiveInteger nroSTI,
           java.lang.String glosaSTI,
           org.apache.axis.types.PositiveInteger ordenSTI,
           java.math.BigDecimal subTotNetoSTI,
           java.math.BigDecimal subTotIVASTI,
           java.math.BigDecimal subTotAdicSTI,
           java.math.BigDecimal subTotExeSTI,
           java.math.BigDecimal valSubtotSTI,
           org.apache.axis.types.PositiveInteger[] lineasDeta) {
           this.nroSTI = nroSTI;
           this.glosaSTI = glosaSTI;
           this.ordenSTI = ordenSTI;
           this.subTotNetoSTI = subTotNetoSTI;
           this.subTotIVASTI = subTotIVASTI;
           this.subTotAdicSTI = subTotAdicSTI;
           this.subTotExeSTI = subTotExeSTI;
           this.valSubtotSTI = valSubtotSTI;
           this.lineasDeta = lineasDeta;
    }


    /**
     * Gets the nroSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return nroSTI   * Número de Subtotal
     */
    public org.apache.axis.types.PositiveInteger getNroSTI() {
        return nroSTI;
    }


    /**
     * Sets the nroSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param nroSTI   * Número de Subtotal
     */
    public void setNroSTI(org.apache.axis.types.PositiveInteger nroSTI) {
        this.nroSTI = nroSTI;
    }


    /**
     * Gets the glosaSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return glosaSTI   * Glosa
     */
    public java.lang.String getGlosaSTI() {
        return glosaSTI;
    }


    /**
     * Sets the glosaSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param glosaSTI   * Glosa
     */
    public void setGlosaSTI(java.lang.String glosaSTI) {
        this.glosaSTI = glosaSTI;
    }


    /**
     * Gets the ordenSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return ordenSTI   * Ubicación para Impresión
     */
    public org.apache.axis.types.PositiveInteger getOrdenSTI() {
        return ordenSTI;
    }


    /**
     * Sets the ordenSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param ordenSTI   * Ubicación para Impresión
     */
    public void setOrdenSTI(org.apache.axis.types.PositiveInteger ordenSTI) {
        this.ordenSTI = ordenSTI;
    }


    /**
     * Gets the subTotNetoSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return subTotNetoSTI   * Valor Neto del Subtotal
     */
    public java.math.BigDecimal getSubTotNetoSTI() {
        return subTotNetoSTI;
    }


    /**
     * Sets the subTotNetoSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param subTotNetoSTI   * Valor Neto del Subtotal
     */
    public void setSubTotNetoSTI(java.math.BigDecimal subTotNetoSTI) {
        this.subTotNetoSTI = subTotNetoSTI;
    }


    /**
     * Gets the subTotIVASTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return subTotIVASTI   * Valor del IVA del Subtotal
     */
    public java.math.BigDecimal getSubTotIVASTI() {
        return subTotIVASTI;
    }


    /**
     * Sets the subTotIVASTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param subTotIVASTI   * Valor del IVA del Subtotal
     */
    public void setSubTotIVASTI(java.math.BigDecimal subTotIVASTI) {
        this.subTotIVASTI = subTotIVASTI;
    }


    /**
     * Gets the subTotAdicSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return subTotAdicSTI   * Valor de los Impuestos adicionales o específicos del Subtotal
     */
    public java.math.BigDecimal getSubTotAdicSTI() {
        return subTotAdicSTI;
    }


    /**
     * Sets the subTotAdicSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param subTotAdicSTI   * Valor de los Impuestos adicionales o específicos del Subtotal
     */
    public void setSubTotAdicSTI(java.math.BigDecimal subTotAdicSTI) {
        this.subTotAdicSTI = subTotAdicSTI;
    }


    /**
     * Gets the subTotExeSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return subTotExeSTI   * Valor no Afecto o Exento del Subtotal
     */
    public java.math.BigDecimal getSubTotExeSTI() {
        return subTotExeSTI;
    }


    /**
     * Sets the subTotExeSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param subTotExeSTI   * Valor no Afecto o Exento del Subtotal
     */
    public void setSubTotExeSTI(java.math.BigDecimal subTotExeSTI) {
        this.subTotExeSTI = subTotExeSTI;
    }


    /**
     * Gets the valSubtotSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @return valSubtotSTI   * Valor de la línea de subtotal
     */
    public java.math.BigDecimal getValSubtotSTI() {
        return valSubtotSTI;
    }


    /**
     * Sets the valSubtotSTI value for this BOLETADefTypeSubTotInfo.
     * 
     * @param valSubtotSTI   * Valor de la línea de subtotal
     */
    public void setValSubtotSTI(java.math.BigDecimal valSubtotSTI) {
        this.valSubtotSTI = valSubtotSTI;
    }


    /**
     * Gets the lineasDeta value for this BOLETADefTypeSubTotInfo.
     * 
     * @return lineasDeta   * TABLA de  Líneas de Detalle que se agrupan en el Subtotal
     */
    public org.apache.axis.types.PositiveInteger[] getLineasDeta() {
        return lineasDeta;
    }


    /**
     * Sets the lineasDeta value for this BOLETADefTypeSubTotInfo.
     * 
     * @param lineasDeta   * TABLA de  Líneas de Detalle que se agrupan en el Subtotal
     */
    public void setLineasDeta(org.apache.axis.types.PositiveInteger[] lineasDeta) {
        this.lineasDeta = lineasDeta;
    }

    public org.apache.axis.types.PositiveInteger getLineasDeta(int i) {
        return this.lineasDeta[i];
    }

    public void setLineasDeta(int i, org.apache.axis.types.PositiveInteger _value) {
        this.lineasDeta[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeSubTotInfo)) return false;
        BOLETADefTypeSubTotInfo other = (BOLETADefTypeSubTotInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nroSTI==null && other.getNroSTI()==null) || 
             (this.nroSTI!=null &&
              this.nroSTI.equals(other.getNroSTI()))) &&
            ((this.glosaSTI==null && other.getGlosaSTI()==null) || 
             (this.glosaSTI!=null &&
              this.glosaSTI.equals(other.getGlosaSTI()))) &&
            ((this.ordenSTI==null && other.getOrdenSTI()==null) || 
             (this.ordenSTI!=null &&
              this.ordenSTI.equals(other.getOrdenSTI()))) &&
            ((this.subTotNetoSTI==null && other.getSubTotNetoSTI()==null) || 
             (this.subTotNetoSTI!=null &&
              this.subTotNetoSTI.equals(other.getSubTotNetoSTI()))) &&
            ((this.subTotIVASTI==null && other.getSubTotIVASTI()==null) || 
             (this.subTotIVASTI!=null &&
              this.subTotIVASTI.equals(other.getSubTotIVASTI()))) &&
            ((this.subTotAdicSTI==null && other.getSubTotAdicSTI()==null) || 
             (this.subTotAdicSTI!=null &&
              this.subTotAdicSTI.equals(other.getSubTotAdicSTI()))) &&
            ((this.subTotExeSTI==null && other.getSubTotExeSTI()==null) || 
             (this.subTotExeSTI!=null &&
              this.subTotExeSTI.equals(other.getSubTotExeSTI()))) &&
            ((this.valSubtotSTI==null && other.getValSubtotSTI()==null) || 
             (this.valSubtotSTI!=null &&
              this.valSubtotSTI.equals(other.getValSubtotSTI()))) &&
            ((this.lineasDeta==null && other.getLineasDeta()==null) || 
             (this.lineasDeta!=null &&
              java.util.Arrays.equals(this.lineasDeta, other.getLineasDeta())));
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
        if (getNroSTI() != null) {
            _hashCode += getNroSTI().hashCode();
        }
        if (getGlosaSTI() != null) {
            _hashCode += getGlosaSTI().hashCode();
        }
        if (getOrdenSTI() != null) {
            _hashCode += getOrdenSTI().hashCode();
        }
        if (getSubTotNetoSTI() != null) {
            _hashCode += getSubTotNetoSTI().hashCode();
        }
        if (getSubTotIVASTI() != null) {
            _hashCode += getSubTotIVASTI().hashCode();
        }
        if (getSubTotAdicSTI() != null) {
            _hashCode += getSubTotAdicSTI().hashCode();
        }
        if (getSubTotExeSTI() != null) {
            _hashCode += getSubTotExeSTI().hashCode();
        }
        if (getValSubtotSTI() != null) {
            _hashCode += getValSubtotSTI().hashCode();
        }
        if (getLineasDeta() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLineasDeta());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLineasDeta(), i);
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
        new org.apache.axis.description.TypeDesc(BOLETADefTypeSubTotInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>SubTotInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NroSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("glosaSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GlosaSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrdenSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTotNetoSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubTotNetoSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTotIVASTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubTotIVASTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTotAdicSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubTotAdicSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subTotExeSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SubTotExeSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valSubtotSTI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ValSubtotSTI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineasDeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineasDeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>SubTotInfo>LineasDeta"));
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
