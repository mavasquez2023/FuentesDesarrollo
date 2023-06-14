/**
 * BOLETADefTypeEncabezadoTotales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeEncabezadoTotales  implements java.io.Serializable {
    private org.apache.axis.types.NonNegativeInteger mntNeto;

    private org.apache.axis.types.NonNegativeInteger mntExe;

    private org.apache.axis.types.PositiveInteger IVA;

    private org.apache.axis.types.NonNegativeInteger mntTotal;

    private java.math.BigInteger montoNF;

    private org.apache.axis.types.NonNegativeInteger totalPeriodo;

    private java.math.BigInteger saldoAnterior;

    private java.math.BigInteger vlrPagar;

    public BOLETADefTypeEncabezadoTotales() {
    }

    public BOLETADefTypeEncabezadoTotales(
           org.apache.axis.types.NonNegativeInteger mntNeto,
           org.apache.axis.types.NonNegativeInteger mntExe,
           org.apache.axis.types.PositiveInteger IVA,
           org.apache.axis.types.NonNegativeInteger mntTotal,
           java.math.BigInteger montoNF,
           org.apache.axis.types.NonNegativeInteger totalPeriodo,
           java.math.BigInteger saldoAnterior,
           java.math.BigInteger vlrPagar) {
           this.mntNeto = mntNeto;
           this.mntExe = mntExe;
           this.IVA = IVA;
           this.mntTotal = mntTotal;
           this.montoNF = montoNF;
           this.totalPeriodo = totalPeriodo;
           this.saldoAnterior = saldoAnterior;
           this.vlrPagar = vlrPagar;
    }


    /**
     * Gets the mntNeto value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return mntNeto
     */
    public org.apache.axis.types.NonNegativeInteger getMntNeto() {
        return mntNeto;
    }


    /**
     * Sets the mntNeto value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param mntNeto
     */
    public void setMntNeto(org.apache.axis.types.NonNegativeInteger mntNeto) {
        this.mntNeto = mntNeto;
    }


    /**
     * Gets the mntExe value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return mntExe
     */
    public org.apache.axis.types.NonNegativeInteger getMntExe() {
        return mntExe;
    }


    /**
     * Sets the mntExe value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param mntExe
     */
    public void setMntExe(org.apache.axis.types.NonNegativeInteger mntExe) {
        this.mntExe = mntExe;
    }


    /**
     * Gets the IVA value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return IVA
     */
    public org.apache.axis.types.PositiveInteger getIVA() {
        return IVA;
    }


    /**
     * Sets the IVA value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param IVA
     */
    public void setIVA(org.apache.axis.types.PositiveInteger IVA) {
        this.IVA = IVA;
    }


    /**
     * Gets the mntTotal value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return mntTotal
     */
    public org.apache.axis.types.NonNegativeInteger getMntTotal() {
        return mntTotal;
    }


    /**
     * Sets the mntTotal value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param mntTotal
     */
    public void setMntTotal(org.apache.axis.types.NonNegativeInteger mntTotal) {
        this.mntTotal = mntTotal;
    }


    /**
     * Gets the montoNF value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return montoNF
     */
    public java.math.BigInteger getMontoNF() {
        return montoNF;
    }


    /**
     * Sets the montoNF value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param montoNF
     */
    public void setMontoNF(java.math.BigInteger montoNF) {
        this.montoNF = montoNF;
    }


    /**
     * Gets the totalPeriodo value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return totalPeriodo
     */
    public org.apache.axis.types.NonNegativeInteger getTotalPeriodo() {
        return totalPeriodo;
    }


    /**
     * Sets the totalPeriodo value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param totalPeriodo
     */
    public void setTotalPeriodo(org.apache.axis.types.NonNegativeInteger totalPeriodo) {
        this.totalPeriodo = totalPeriodo;
    }


    /**
     * Gets the saldoAnterior value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return saldoAnterior
     */
    public java.math.BigInteger getSaldoAnterior() {
        return saldoAnterior;
    }


    /**
     * Sets the saldoAnterior value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param saldoAnterior
     */
    public void setSaldoAnterior(java.math.BigInteger saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }


    /**
     * Gets the vlrPagar value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @return vlrPagar
     */
    public java.math.BigInteger getVlrPagar() {
        return vlrPagar;
    }


    /**
     * Sets the vlrPagar value for this BOLETADefTypeEncabezadoTotales.
     * 
     * @param vlrPagar
     */
    public void setVlrPagar(java.math.BigInteger vlrPagar) {
        this.vlrPagar = vlrPagar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeEncabezadoTotales)) return false;
        BOLETADefTypeEncabezadoTotales other = (BOLETADefTypeEncabezadoTotales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mntNeto==null && other.getMntNeto()==null) || 
             (this.mntNeto!=null &&
              this.mntNeto.equals(other.getMntNeto()))) &&
            ((this.mntExe==null && other.getMntExe()==null) || 
             (this.mntExe!=null &&
              this.mntExe.equals(other.getMntExe()))) &&
            ((this.IVA==null && other.getIVA()==null) || 
             (this.IVA!=null &&
              this.IVA.equals(other.getIVA()))) &&
            ((this.mntTotal==null && other.getMntTotal()==null) || 
             (this.mntTotal!=null &&
              this.mntTotal.equals(other.getMntTotal()))) &&
            ((this.montoNF==null && other.getMontoNF()==null) || 
             (this.montoNF!=null &&
              this.montoNF.equals(other.getMontoNF()))) &&
            ((this.totalPeriodo==null && other.getTotalPeriodo()==null) || 
             (this.totalPeriodo!=null &&
              this.totalPeriodo.equals(other.getTotalPeriodo()))) &&
            ((this.saldoAnterior==null && other.getSaldoAnterior()==null) || 
             (this.saldoAnterior!=null &&
              this.saldoAnterior.equals(other.getSaldoAnterior()))) &&
            ((this.vlrPagar==null && other.getVlrPagar()==null) || 
             (this.vlrPagar!=null &&
              this.vlrPagar.equals(other.getVlrPagar())));
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
        if (getMntNeto() != null) {
            _hashCode += getMntNeto().hashCode();
        }
        if (getMntExe() != null) {
            _hashCode += getMntExe().hashCode();
        }
        if (getIVA() != null) {
            _hashCode += getIVA().hashCode();
        }
        if (getMntTotal() != null) {
            _hashCode += getMntTotal().hashCode();
        }
        if (getMontoNF() != null) {
            _hashCode += getMontoNF().hashCode();
        }
        if (getTotalPeriodo() != null) {
            _hashCode += getTotalPeriodo().hashCode();
        }
        if (getSaldoAnterior() != null) {
            _hashCode += getSaldoAnterior().hashCode();
        }
        if (getVlrPagar() != null) {
            _hashCode += getVlrPagar().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeEncabezadoTotales.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>Totales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mntNeto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MntNeto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mntExe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MntExe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mntTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MntTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoNF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MontoNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalPeriodo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TotalPeriodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saldoAnterior");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SaldoAnterior"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlrPagar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VlrPagar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
