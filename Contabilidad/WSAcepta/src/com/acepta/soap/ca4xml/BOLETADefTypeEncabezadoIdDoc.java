/**
 * BOLETADefTypeEncabezadoIdDoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeEncabezadoIdDoc  implements java.io.Serializable {
    private com.acepta.soap.ca4xml.DTEType tipoDTE;

    private org.apache.axis.types.PositiveInteger folio;

    private java.util.Date fchEmis;

    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio indServicio;

    private com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndMntNeto indMntNeto;

    private java.util.Date periodoDesde;

    private java.util.Date periodoHasta;

    private java.util.Date fchVenc;

    public BOLETADefTypeEncabezadoIdDoc() {
    }

    public BOLETADefTypeEncabezadoIdDoc(
           com.acepta.soap.ca4xml.DTEType tipoDTE,
           org.apache.axis.types.PositiveInteger folio,
           java.util.Date fchEmis,
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio indServicio,
           com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndMntNeto indMntNeto,
           java.util.Date periodoDesde,
           java.util.Date periodoHasta,
           java.util.Date fchVenc) {
           this.tipoDTE = tipoDTE;
           this.folio = folio;
           this.fchEmis = fchEmis;
           this.indServicio = indServicio;
           this.indMntNeto = indMntNeto;
           this.periodoDesde = periodoDesde;
           this.periodoHasta = periodoHasta;
           this.fchVenc = fchVenc;
    }


    /**
     * Gets the tipoDTE value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return tipoDTE
     */
    public com.acepta.soap.ca4xml.DTEType getTipoDTE() {
        return tipoDTE;
    }


    /**
     * Sets the tipoDTE value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param tipoDTE
     */
    public void setTipoDTE(com.acepta.soap.ca4xml.DTEType tipoDTE) {
        this.tipoDTE = tipoDTE;
    }


    /**
     * Gets the folio value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return folio
     */
    public org.apache.axis.types.PositiveInteger getFolio() {
        return folio;
    }


    /**
     * Sets the folio value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param folio
     */
    public void setFolio(org.apache.axis.types.PositiveInteger folio) {
        this.folio = folio;
    }


    /**
     * Gets the fchEmis value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return fchEmis
     */
    public java.util.Date getFchEmis() {
        return fchEmis;
    }


    /**
     * Sets the fchEmis value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param fchEmis
     */
    public void setFchEmis(java.util.Date fchEmis) {
        this.fchEmis = fchEmis;
    }


    /**
     * Gets the indServicio value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return indServicio
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio getIndServicio() {
        return indServicio;
    }


    /**
     * Sets the indServicio value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param indServicio
     */
    public void setIndServicio(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio indServicio) {
        this.indServicio = indServicio;
    }


    /**
     * Gets the indMntNeto value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return indMntNeto
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndMntNeto getIndMntNeto() {
        return indMntNeto;
    }


    /**
     * Sets the indMntNeto value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param indMntNeto
     */
    public void setIndMntNeto(com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndMntNeto indMntNeto) {
        this.indMntNeto = indMntNeto;
    }


    /**
     * Gets the periodoDesde value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return periodoDesde
     */
    public java.util.Date getPeriodoDesde() {
        return periodoDesde;
    }


    /**
     * Sets the periodoDesde value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param periodoDesde
     */
    public void setPeriodoDesde(java.util.Date periodoDesde) {
        this.periodoDesde = periodoDesde;
    }


    /**
     * Gets the periodoHasta value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return periodoHasta
     */
    public java.util.Date getPeriodoHasta() {
        return periodoHasta;
    }


    /**
     * Sets the periodoHasta value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param periodoHasta
     */
    public void setPeriodoHasta(java.util.Date periodoHasta) {
        this.periodoHasta = periodoHasta;
    }


    /**
     * Gets the fchVenc value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @return fchVenc
     */
    public java.util.Date getFchVenc() {
        return fchVenc;
    }


    /**
     * Sets the fchVenc value for this BOLETADefTypeEncabezadoIdDoc.
     * 
     * @param fchVenc
     */
    public void setFchVenc(java.util.Date fchVenc) {
        this.fchVenc = fchVenc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeEncabezadoIdDoc)) return false;
        BOLETADefTypeEncabezadoIdDoc other = (BOLETADefTypeEncabezadoIdDoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDTE==null && other.getTipoDTE()==null) || 
             (this.tipoDTE!=null &&
              this.tipoDTE.equals(other.getTipoDTE()))) &&
            ((this.folio==null && other.getFolio()==null) || 
             (this.folio!=null &&
              this.folio.equals(other.getFolio()))) &&
            ((this.fchEmis==null && other.getFchEmis()==null) || 
             (this.fchEmis!=null &&
              this.fchEmis.equals(other.getFchEmis()))) &&
            ((this.indServicio==null && other.getIndServicio()==null) || 
             (this.indServicio!=null &&
              this.indServicio.equals(other.getIndServicio()))) &&
            ((this.indMntNeto==null && other.getIndMntNeto()==null) || 
             (this.indMntNeto!=null &&
              this.indMntNeto.equals(other.getIndMntNeto()))) &&
            ((this.periodoDesde==null && other.getPeriodoDesde()==null) || 
             (this.periodoDesde!=null &&
              this.periodoDesde.equals(other.getPeriodoDesde()))) &&
            ((this.periodoHasta==null && other.getPeriodoHasta()==null) || 
             (this.periodoHasta!=null &&
              this.periodoHasta.equals(other.getPeriodoHasta()))) &&
            ((this.fchVenc==null && other.getFchVenc()==null) || 
             (this.fchVenc!=null &&
              this.fchVenc.equals(other.getFchVenc())));
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
        if (getTipoDTE() != null) {
            _hashCode += getTipoDTE().hashCode();
        }
        if (getFolio() != null) {
            _hashCode += getFolio().hashCode();
        }
        if (getFchEmis() != null) {
            _hashCode += getFchEmis().hashCode();
        }
        if (getIndServicio() != null) {
            _hashCode += getIndServicio().hashCode();
        }
        if (getIndMntNeto() != null) {
            _hashCode += getIndMntNeto().hashCode();
        }
        if (getPeriodoDesde() != null) {
            _hashCode += getPeriodoDesde().hashCode();
        }
        if (getPeriodoHasta() != null) {
            _hashCode += getPeriodoHasta().hashCode();
        }
        if (getFchVenc() != null) {
            _hashCode += getFchVenc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeEncabezadoIdDoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>IdDoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoDTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "DTEType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Folio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fchEmis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FchEmis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IndServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>>BOLETADefType>Encabezado>IdDoc>IndServicio"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indMntNeto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IndMntNeto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>>BOLETADefType>Encabezado>IdDoc>IndMntNeto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PeriodoDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodoHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PeriodoHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fchVenc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FchVenc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
