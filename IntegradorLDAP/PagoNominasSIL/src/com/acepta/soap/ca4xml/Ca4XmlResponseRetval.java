/**
 * Ca4XmlResponseRetval.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class Ca4XmlResponseRetval  implements java.io.Serializable {
    /* 0=OK| 1|Nok */
    private java.lang.String estado;

    /* Numero SAP */
    private java.lang.String cdgIntRecep;

    /* Identicador del documento de acuerdo a tabla dell SII */
    private java.lang.String tipoDte;

    /* Folio Legal del DTE */
    private java.lang.String folio;

    /* Descripcion en caso de Error */
    private java.lang.String glosa;

    /* Url del DTE */
    private java.lang.String urlDte;

    /* Url del Adjunto */
    private java.lang.String urlAdj;

    /* Rut empresa emisora del DTE */
    private java.lang.String rutEmis;

    public Ca4XmlResponseRetval() {
    }

    public Ca4XmlResponseRetval(
           java.lang.String estado,
           java.lang.String cdgIntRecep,
           java.lang.String tipoDte,
           java.lang.String folio,
           java.lang.String glosa,
           java.lang.String urlDte,
           java.lang.String urlAdj,
           java.lang.String rutEmis) {
           this.estado = estado;
           this.cdgIntRecep = cdgIntRecep;
           this.tipoDte = tipoDte;
           this.folio = folio;
           this.glosa = glosa;
           this.urlDte = urlDte;
           this.urlAdj = urlAdj;
           this.rutEmis = rutEmis;
    }


    /**
     * Gets the estado value for this Ca4XmlResponseRetval.
     * 
     * @return estado   * 0=OK| 1|Nok
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Ca4XmlResponseRetval.
     * 
     * @param estado   * 0=OK| 1|Nok
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the cdgIntRecep value for this Ca4XmlResponseRetval.
     * 
     * @return cdgIntRecep   * Numero SAP
     */
    public java.lang.String getCdgIntRecep() {
        return cdgIntRecep;
    }


    /**
     * Sets the cdgIntRecep value for this Ca4XmlResponseRetval.
     * 
     * @param cdgIntRecep   * Numero SAP
     */
    public void setCdgIntRecep(java.lang.String cdgIntRecep) {
        this.cdgIntRecep = cdgIntRecep;
    }


    /**
     * Gets the tipoDte value for this Ca4XmlResponseRetval.
     * 
     * @return tipoDte   * Identicador del documento de acuerdo a tabla dell SII
     */
    public java.lang.String getTipoDte() {
        return tipoDte;
    }


    /**
     * Sets the tipoDte value for this Ca4XmlResponseRetval.
     * 
     * @param tipoDte   * Identicador del documento de acuerdo a tabla dell SII
     */
    public void setTipoDte(java.lang.String tipoDte) {
        this.tipoDte = tipoDte;
    }


    /**
     * Gets the folio value for this Ca4XmlResponseRetval.
     * 
     * @return folio   * Folio Legal del DTE
     */
    public java.lang.String getFolio() {
        return folio;
    }


    /**
     * Sets the folio value for this Ca4XmlResponseRetval.
     * 
     * @param folio   * Folio Legal del DTE
     */
    public void setFolio(java.lang.String folio) {
        this.folio = folio;
    }


    /**
     * Gets the glosa value for this Ca4XmlResponseRetval.
     * 
     * @return glosa   * Descripcion en caso de Error
     */
    public java.lang.String getGlosa() {
        return glosa;
    }


    /**
     * Sets the glosa value for this Ca4XmlResponseRetval.
     * 
     * @param glosa   * Descripcion en caso de Error
     */
    public void setGlosa(java.lang.String glosa) {
        this.glosa = glosa;
    }


    /**
     * Gets the urlDte value for this Ca4XmlResponseRetval.
     * 
     * @return urlDte   * Url del DTE
     */
    public java.lang.String getUrlDte() {
        return urlDte;
    }


    /**
     * Sets the urlDte value for this Ca4XmlResponseRetval.
     * 
     * @param urlDte   * Url del DTE
     */
    public void setUrlDte(java.lang.String urlDte) {
        this.urlDte = urlDte;
    }


    /**
     * Gets the urlAdj value for this Ca4XmlResponseRetval.
     * 
     * @return urlAdj   * Url del Adjunto
     */
    public java.lang.String getUrlAdj() {
        return urlAdj;
    }


    /**
     * Sets the urlAdj value for this Ca4XmlResponseRetval.
     * 
     * @param urlAdj   * Url del Adjunto
     */
    public void setUrlAdj(java.lang.String urlAdj) {
        this.urlAdj = urlAdj;
    }


    /**
     * Gets the rutEmis value for this Ca4XmlResponseRetval.
     * 
     * @return rutEmis   * Rut empresa emisora del DTE
     */
    public java.lang.String getRutEmis() {
        return rutEmis;
    }


    /**
     * Sets the rutEmis value for this Ca4XmlResponseRetval.
     * 
     * @param rutEmis   * Rut empresa emisora del DTE
     */
    public void setRutEmis(java.lang.String rutEmis) {
        this.rutEmis = rutEmis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ca4XmlResponseRetval)) return false;
        Ca4XmlResponseRetval other = (Ca4XmlResponseRetval) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.cdgIntRecep==null && other.getCdgIntRecep()==null) || 
             (this.cdgIntRecep!=null &&
              this.cdgIntRecep.equals(other.getCdgIntRecep()))) &&
            ((this.tipoDte==null && other.getTipoDte()==null) || 
             (this.tipoDte!=null &&
              this.tipoDte.equals(other.getTipoDte()))) &&
            ((this.folio==null && other.getFolio()==null) || 
             (this.folio!=null &&
              this.folio.equals(other.getFolio()))) &&
            ((this.glosa==null && other.getGlosa()==null) || 
             (this.glosa!=null &&
              this.glosa.equals(other.getGlosa()))) &&
            ((this.urlDte==null && other.getUrlDte()==null) || 
             (this.urlDte!=null &&
              this.urlDte.equals(other.getUrlDte()))) &&
            ((this.urlAdj==null && other.getUrlAdj()==null) || 
             (this.urlAdj!=null &&
              this.urlAdj.equals(other.getUrlAdj()))) &&
            ((this.rutEmis==null && other.getRutEmis()==null) || 
             (this.rutEmis!=null &&
              this.rutEmis.equals(other.getRutEmis())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getCdgIntRecep() != null) {
            _hashCode += getCdgIntRecep().hashCode();
        }
        if (getTipoDte() != null) {
            _hashCode += getTipoDte().hashCode();
        }
        if (getFolio() != null) {
            _hashCode += getFolio().hashCode();
        }
        if (getGlosa() != null) {
            _hashCode += getGlosa().hashCode();
        }
        if (getUrlDte() != null) {
            _hashCode += getUrlDte().hashCode();
        }
        if (getUrlAdj() != null) {
            _hashCode += getUrlAdj().hashCode();
        }
        if (getRutEmis() != null) {
            _hashCode += getRutEmis().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ca4XmlResponseRetval.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">ca4xmlResponse>retval"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdgIntRecep");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CdgIntRecep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoDte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Folio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("glosa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Glosa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlDte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UrlDte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlAdj");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UrlAdj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutEmis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RutEmis"));
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
