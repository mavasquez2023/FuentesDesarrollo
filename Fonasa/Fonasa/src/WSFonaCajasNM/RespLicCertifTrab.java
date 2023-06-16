/**
 * RespLicCertifTrab.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class RespLicCertifTrab  implements java.io.Serializable {
    private short estado;

    private java.lang.String gloEstado;

    private java.lang.String extApellidoPat;

    private java.lang.String extApellidoMat;

    private java.lang.String extNombres;

    private java.lang.String extSexo;

    private java.lang.String extFechaNacimi;

    private java.lang.String extCodEstBen;

    private java.lang.String extDescEstado;

    public RespLicCertifTrab() {
    }

    public RespLicCertifTrab(
           short estado,
           java.lang.String gloEstado,
           java.lang.String extApellidoPat,
           java.lang.String extApellidoMat,
           java.lang.String extNombres,
           java.lang.String extSexo,
           java.lang.String extFechaNacimi,
           java.lang.String extCodEstBen,
           java.lang.String extDescEstado) {
           this.estado = estado;
           this.gloEstado = gloEstado;
           this.extApellidoPat = extApellidoPat;
           this.extApellidoMat = extApellidoMat;
           this.extNombres = extNombres;
           this.extSexo = extSexo;
           this.extFechaNacimi = extFechaNacimi;
           this.extCodEstBen = extCodEstBen;
           this.extDescEstado = extDescEstado;
    }


    /**
     * Gets the estado value for this RespLicCertifTrab.
     * 
     * @return estado
     */
    public short getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RespLicCertifTrab.
     * 
     * @param estado
     */
    public void setEstado(short estado) {
        this.estado = estado;
    }


    /**
     * Gets the gloEstado value for this RespLicCertifTrab.
     * 
     * @return gloEstado
     */
    public java.lang.String getGloEstado() {
        return gloEstado;
    }


    /**
     * Sets the gloEstado value for this RespLicCertifTrab.
     * 
     * @param gloEstado
     */
    public void setGloEstado(java.lang.String gloEstado) {
        this.gloEstado = gloEstado;
    }


    /**
     * Gets the extApellidoPat value for this RespLicCertifTrab.
     * 
     * @return extApellidoPat
     */
    public java.lang.String getExtApellidoPat() {
        return extApellidoPat;
    }


    /**
     * Sets the extApellidoPat value for this RespLicCertifTrab.
     * 
     * @param extApellidoPat
     */
    public void setExtApellidoPat(java.lang.String extApellidoPat) {
        this.extApellidoPat = extApellidoPat;
    }


    /**
     * Gets the extApellidoMat value for this RespLicCertifTrab.
     * 
     * @return extApellidoMat
     */
    public java.lang.String getExtApellidoMat() {
        return extApellidoMat;
    }


    /**
     * Sets the extApellidoMat value for this RespLicCertifTrab.
     * 
     * @param extApellidoMat
     */
    public void setExtApellidoMat(java.lang.String extApellidoMat) {
        this.extApellidoMat = extApellidoMat;
    }


    /**
     * Gets the extNombres value for this RespLicCertifTrab.
     * 
     * @return extNombres
     */
    public java.lang.String getExtNombres() {
        return extNombres;
    }


    /**
     * Sets the extNombres value for this RespLicCertifTrab.
     * 
     * @param extNombres
     */
    public void setExtNombres(java.lang.String extNombres) {
        this.extNombres = extNombres;
    }


    /**
     * Gets the extSexo value for this RespLicCertifTrab.
     * 
     * @return extSexo
     */
    public java.lang.String getExtSexo() {
        return extSexo;
    }


    /**
     * Sets the extSexo value for this RespLicCertifTrab.
     * 
     * @param extSexo
     */
    public void setExtSexo(java.lang.String extSexo) {
        this.extSexo = extSexo;
    }


    /**
     * Gets the extFechaNacimi value for this RespLicCertifTrab.
     * 
     * @return extFechaNacimi
     */
    public java.lang.String getExtFechaNacimi() {
        return extFechaNacimi;
    }


    /**
     * Sets the extFechaNacimi value for this RespLicCertifTrab.
     * 
     * @param extFechaNacimi
     */
    public void setExtFechaNacimi(java.lang.String extFechaNacimi) {
        this.extFechaNacimi = extFechaNacimi;
    }


    /**
     * Gets the extCodEstBen value for this RespLicCertifTrab.
     * 
     * @return extCodEstBen
     */
    public java.lang.String getExtCodEstBen() {
        return extCodEstBen;
    }


    /**
     * Sets the extCodEstBen value for this RespLicCertifTrab.
     * 
     * @param extCodEstBen
     */
    public void setExtCodEstBen(java.lang.String extCodEstBen) {
        this.extCodEstBen = extCodEstBen;
    }


    /**
     * Gets the extDescEstado value for this RespLicCertifTrab.
     * 
     * @return extDescEstado
     */
    public java.lang.String getExtDescEstado() {
        return extDescEstado;
    }


    /**
     * Sets the extDescEstado value for this RespLicCertifTrab.
     * 
     * @param extDescEstado
     */
    public void setExtDescEstado(java.lang.String extDescEstado) {
        this.extDescEstado = extDescEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespLicCertifTrab)) return false;
        RespLicCertifTrab other = (RespLicCertifTrab) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.estado == other.getEstado() &&
            ((this.gloEstado==null && other.getGloEstado()==null) || 
             (this.gloEstado!=null &&
              this.gloEstado.equals(other.getGloEstado()))) &&
            ((this.extApellidoPat==null && other.getExtApellidoPat()==null) || 
             (this.extApellidoPat!=null &&
              this.extApellidoPat.equals(other.getExtApellidoPat()))) &&
            ((this.extApellidoMat==null && other.getExtApellidoMat()==null) || 
             (this.extApellidoMat!=null &&
              this.extApellidoMat.equals(other.getExtApellidoMat()))) &&
            ((this.extNombres==null && other.getExtNombres()==null) || 
             (this.extNombres!=null &&
              this.extNombres.equals(other.getExtNombres()))) &&
            ((this.extSexo==null && other.getExtSexo()==null) || 
             (this.extSexo!=null &&
              this.extSexo.equals(other.getExtSexo()))) &&
            ((this.extFechaNacimi==null && other.getExtFechaNacimi()==null) || 
             (this.extFechaNacimi!=null &&
              this.extFechaNacimi.equals(other.getExtFechaNacimi()))) &&
            ((this.extCodEstBen==null && other.getExtCodEstBen()==null) || 
             (this.extCodEstBen!=null &&
              this.extCodEstBen.equals(other.getExtCodEstBen()))) &&
            ((this.extDescEstado==null && other.getExtDescEstado()==null) || 
             (this.extDescEstado!=null &&
              this.extDescEstado.equals(other.getExtDescEstado())));
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
        _hashCode += getEstado();
        if (getGloEstado() != null) {
            _hashCode += getGloEstado().hashCode();
        }
        if (getExtApellidoPat() != null) {
            _hashCode += getExtApellidoPat().hashCode();
        }
        if (getExtApellidoMat() != null) {
            _hashCode += getExtApellidoMat().hashCode();
        }
        if (getExtNombres() != null) {
            _hashCode += getExtNombres().hashCode();
        }
        if (getExtSexo() != null) {
            _hashCode += getExtSexo().hashCode();
        }
        if (getExtFechaNacimi() != null) {
            _hashCode += getExtFechaNacimi().hashCode();
        }
        if (getExtCodEstBen() != null) {
            _hashCode += getExtCodEstBen().hashCode();
        }
        if (getExtDescEstado() != null) {
            _hashCode += getExtDescEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespLicCertifTrab.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "RespLicCertifTrab"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gloEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "GloEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extApellidoPat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtApellidoPat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extApellidoMat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtApellidoMat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extNombres");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtNombres"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extSexo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtSexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extFechaNacimi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtFechaNacimi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extCodEstBen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtCodEstBen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extDescEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "ExtDescEstado"));
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
