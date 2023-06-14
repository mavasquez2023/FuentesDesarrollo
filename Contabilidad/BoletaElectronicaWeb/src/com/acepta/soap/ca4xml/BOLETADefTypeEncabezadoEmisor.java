/**
 * BOLETADefTypeEncabezadoEmisor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeEncabezadoEmisor  implements java.io.Serializable {
    private java.lang.String RUTEmisor;

    private java.lang.String rznSocEmisor;

    private java.lang.String giroEmisor;

    private org.apache.axis.types.PositiveInteger cdgSIISucur;

    private java.lang.String dirOrigen;

    private java.lang.String cmnaOrigen;

    private java.lang.String ciudadOrigen;

    public BOLETADefTypeEncabezadoEmisor() {
    }

    public BOLETADefTypeEncabezadoEmisor(
           java.lang.String RUTEmisor,
           java.lang.String rznSocEmisor,
           java.lang.String giroEmisor,
           org.apache.axis.types.PositiveInteger cdgSIISucur,
           java.lang.String dirOrigen,
           java.lang.String cmnaOrigen,
           java.lang.String ciudadOrigen) {
           this.RUTEmisor = RUTEmisor;
           this.rznSocEmisor = rznSocEmisor;
           this.giroEmisor = giroEmisor;
           this.cdgSIISucur = cdgSIISucur;
           this.dirOrigen = dirOrigen;
           this.cmnaOrigen = cmnaOrigen;
           this.ciudadOrigen = ciudadOrigen;
    }


    /**
     * Gets the RUTEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return RUTEmisor
     */
    public java.lang.String getRUTEmisor() {
        return RUTEmisor;
    }


    /**
     * Sets the RUTEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param RUTEmisor
     */
    public void setRUTEmisor(java.lang.String RUTEmisor) {
        this.RUTEmisor = RUTEmisor;
    }


    /**
     * Gets the rznSocEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return rznSocEmisor
     */
    public java.lang.String getRznSocEmisor() {
        return rznSocEmisor;
    }


    /**
     * Sets the rznSocEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param rznSocEmisor
     */
    public void setRznSocEmisor(java.lang.String rznSocEmisor) {
        this.rznSocEmisor = rznSocEmisor;
    }


    /**
     * Gets the giroEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return giroEmisor
     */
    public java.lang.String getGiroEmisor() {
        return giroEmisor;
    }


    /**
     * Sets the giroEmisor value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param giroEmisor
     */
    public void setGiroEmisor(java.lang.String giroEmisor) {
        this.giroEmisor = giroEmisor;
    }


    /**
     * Gets the cdgSIISucur value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return cdgSIISucur
     */
    public org.apache.axis.types.PositiveInteger getCdgSIISucur() {
        return cdgSIISucur;
    }


    /**
     * Sets the cdgSIISucur value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param cdgSIISucur
     */
    public void setCdgSIISucur(org.apache.axis.types.PositiveInteger cdgSIISucur) {
        this.cdgSIISucur = cdgSIISucur;
    }


    /**
     * Gets the dirOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return dirOrigen
     */
    public java.lang.String getDirOrigen() {
        return dirOrigen;
    }


    /**
     * Sets the dirOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param dirOrigen
     */
    public void setDirOrigen(java.lang.String dirOrigen) {
        this.dirOrigen = dirOrigen;
    }


    /**
     * Gets the cmnaOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return cmnaOrigen
     */
    public java.lang.String getCmnaOrigen() {
        return cmnaOrigen;
    }


    /**
     * Sets the cmnaOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param cmnaOrigen
     */
    public void setCmnaOrigen(java.lang.String cmnaOrigen) {
        this.cmnaOrigen = cmnaOrigen;
    }


    /**
     * Gets the ciudadOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @return ciudadOrigen
     */
    public java.lang.String getCiudadOrigen() {
        return ciudadOrigen;
    }


    /**
     * Sets the ciudadOrigen value for this BOLETADefTypeEncabezadoEmisor.
     * 
     * @param ciudadOrigen
     */
    public void setCiudadOrigen(java.lang.String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeEncabezadoEmisor)) return false;
        BOLETADefTypeEncabezadoEmisor other = (BOLETADefTypeEncabezadoEmisor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RUTEmisor==null && other.getRUTEmisor()==null) || 
             (this.RUTEmisor!=null &&
              this.RUTEmisor.equals(other.getRUTEmisor()))) &&
            ((this.rznSocEmisor==null && other.getRznSocEmisor()==null) || 
             (this.rznSocEmisor!=null &&
              this.rznSocEmisor.equals(other.getRznSocEmisor()))) &&
            ((this.giroEmisor==null && other.getGiroEmisor()==null) || 
             (this.giroEmisor!=null &&
              this.giroEmisor.equals(other.getGiroEmisor()))) &&
            ((this.cdgSIISucur==null && other.getCdgSIISucur()==null) || 
             (this.cdgSIISucur!=null &&
              this.cdgSIISucur.equals(other.getCdgSIISucur()))) &&
            ((this.dirOrigen==null && other.getDirOrigen()==null) || 
             (this.dirOrigen!=null &&
              this.dirOrigen.equals(other.getDirOrigen()))) &&
            ((this.cmnaOrigen==null && other.getCmnaOrigen()==null) || 
             (this.cmnaOrigen!=null &&
              this.cmnaOrigen.equals(other.getCmnaOrigen()))) &&
            ((this.ciudadOrigen==null && other.getCiudadOrigen()==null) || 
             (this.ciudadOrigen!=null &&
              this.ciudadOrigen.equals(other.getCiudadOrigen())));
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
        if (getRUTEmisor() != null) {
            _hashCode += getRUTEmisor().hashCode();
        }
        if (getRznSocEmisor() != null) {
            _hashCode += getRznSocEmisor().hashCode();
        }
        if (getGiroEmisor() != null) {
            _hashCode += getGiroEmisor().hashCode();
        }
        if (getCdgSIISucur() != null) {
            _hashCode += getCdgSIISucur().hashCode();
        }
        if (getDirOrigen() != null) {
            _hashCode += getDirOrigen().hashCode();
        }
        if (getCmnaOrigen() != null) {
            _hashCode += getCmnaOrigen().hashCode();
        }
        if (getCiudadOrigen() != null) {
            _hashCode += getCiudadOrigen().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeEncabezadoEmisor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Encabezado>Emisor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUTEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUTEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rznSocEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RznSocEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("giroEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GiroEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdgSIISucur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CdgSIISucur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dirOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DirOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmnaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CmnaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudadOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CiudadOrigen"));
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
