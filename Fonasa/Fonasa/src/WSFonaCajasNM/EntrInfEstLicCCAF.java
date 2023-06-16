/**
 * EntrInfEstLicCCAF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class EntrInfEstLicCCAF  implements java.io.Serializable {
    private short tipFormulario;

    private int numFormulario;

    private java.lang.String fecEvento;

    private short tipoEvento;

    private java.lang.String comentario;

    private java.lang.String derechoSil;

    private int diasSilPag;

    private long mtoSilPag;

    private int diasCotPag;

    private long mtoCotPag;

    public EntrInfEstLicCCAF() {
    }

    public EntrInfEstLicCCAF(
           short tipFormulario,
           int numFormulario,
           java.lang.String fecEvento,
           short tipoEvento,
           java.lang.String comentario,
           java.lang.String derechoSil,
           int diasSilPag,
           long mtoSilPag,
           int diasCotPag,
           long mtoCotPag) {
           this.tipFormulario = tipFormulario;
           this.numFormulario = numFormulario;
           this.fecEvento = fecEvento;
           this.tipoEvento = tipoEvento;
           this.comentario = comentario;
           this.derechoSil = derechoSil;
           this.diasSilPag = diasSilPag;
           this.mtoSilPag = mtoSilPag;
           this.diasCotPag = diasCotPag;
           this.mtoCotPag = mtoCotPag;
    }


    /**
     * Gets the tipFormulario value for this EntrInfEstLicCCAF.
     * 
     * @return tipFormulario
     */
    public short getTipFormulario() {
        return tipFormulario;
    }


    /**
     * Sets the tipFormulario value for this EntrInfEstLicCCAF.
     * 
     * @param tipFormulario
     */
    public void setTipFormulario(short tipFormulario) {
        this.tipFormulario = tipFormulario;
    }


    /**
     * Gets the numFormulario value for this EntrInfEstLicCCAF.
     * 
     * @return numFormulario
     */
    public int getNumFormulario() {
        return numFormulario;
    }


    /**
     * Sets the numFormulario value for this EntrInfEstLicCCAF.
     * 
     * @param numFormulario
     */
    public void setNumFormulario(int numFormulario) {
        this.numFormulario = numFormulario;
    }


    /**
     * Gets the fecEvento value for this EntrInfEstLicCCAF.
     * 
     * @return fecEvento
     */
    public java.lang.String getFecEvento() {
        return fecEvento;
    }


    /**
     * Sets the fecEvento value for this EntrInfEstLicCCAF.
     * 
     * @param fecEvento
     */
    public void setFecEvento(java.lang.String fecEvento) {
        this.fecEvento = fecEvento;
    }


    /**
     * Gets the tipoEvento value for this EntrInfEstLicCCAF.
     * 
     * @return tipoEvento
     */
    public short getTipoEvento() {
        return tipoEvento;
    }


    /**
     * Sets the tipoEvento value for this EntrInfEstLicCCAF.
     * 
     * @param tipoEvento
     */
    public void setTipoEvento(short tipoEvento) {
        this.tipoEvento = tipoEvento;
    }


    /**
     * Gets the comentario value for this EntrInfEstLicCCAF.
     * 
     * @return comentario
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this EntrInfEstLicCCAF.
     * 
     * @param comentario
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }


    /**
     * Gets the derechoSil value for this EntrInfEstLicCCAF.
     * 
     * @return derechoSil
     */
    public java.lang.String getDerechoSil() {
        return derechoSil;
    }


    /**
     * Sets the derechoSil value for this EntrInfEstLicCCAF.
     * 
     * @param derechoSil
     */
    public void setDerechoSil(java.lang.String derechoSil) {
        this.derechoSil = derechoSil;
    }


    /**
     * Gets the diasSilPag value for this EntrInfEstLicCCAF.
     * 
     * @return diasSilPag
     */
    public int getDiasSilPag() {
        return diasSilPag;
    }


    /**
     * Sets the diasSilPag value for this EntrInfEstLicCCAF.
     * 
     * @param diasSilPag
     */
    public void setDiasSilPag(int diasSilPag) {
        this.diasSilPag = diasSilPag;
    }


    /**
     * Gets the mtoSilPag value for this EntrInfEstLicCCAF.
     * 
     * @return mtoSilPag
     */
    public long getMtoSilPag() {
        return mtoSilPag;
    }


    /**
     * Sets the mtoSilPag value for this EntrInfEstLicCCAF.
     * 
     * @param mtoSilPag
     */
    public void setMtoSilPag(long mtoSilPag) {
        this.mtoSilPag = mtoSilPag;
    }


    /**
     * Gets the diasCotPag value for this EntrInfEstLicCCAF.
     * 
     * @return diasCotPag
     */
    public int getDiasCotPag() {
        return diasCotPag;
    }


    /**
     * Sets the diasCotPag value for this EntrInfEstLicCCAF.
     * 
     * @param diasCotPag
     */
    public void setDiasCotPag(int diasCotPag) {
        this.diasCotPag = diasCotPag;
    }


    /**
     * Gets the mtoCotPag value for this EntrInfEstLicCCAF.
     * 
     * @return mtoCotPag
     */
    public long getMtoCotPag() {
        return mtoCotPag;
    }


    /**
     * Sets the mtoCotPag value for this EntrInfEstLicCCAF.
     * 
     * @param mtoCotPag
     */
    public void setMtoCotPag(long mtoCotPag) {
        this.mtoCotPag = mtoCotPag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntrInfEstLicCCAF)) return false;
        EntrInfEstLicCCAF other = (EntrInfEstLicCCAF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.tipFormulario == other.getTipFormulario() &&
            this.numFormulario == other.getNumFormulario() &&
            ((this.fecEvento==null && other.getFecEvento()==null) || 
             (this.fecEvento!=null &&
              this.fecEvento.equals(other.getFecEvento()))) &&
            this.tipoEvento == other.getTipoEvento() &&
            ((this.comentario==null && other.getComentario()==null) || 
             (this.comentario!=null &&
              this.comentario.equals(other.getComentario()))) &&
            ((this.derechoSil==null && other.getDerechoSil()==null) || 
             (this.derechoSil!=null &&
              this.derechoSil.equals(other.getDerechoSil()))) &&
            this.diasSilPag == other.getDiasSilPag() &&
            this.mtoSilPag == other.getMtoSilPag() &&
            this.diasCotPag == other.getDiasCotPag() &&
            this.mtoCotPag == other.getMtoCotPag();
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
        _hashCode += getTipFormulario();
        _hashCode += getNumFormulario();
        if (getFecEvento() != null) {
            _hashCode += getFecEvento().hashCode();
        }
        _hashCode += getTipoEvento();
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        if (getDerechoSil() != null) {
            _hashCode += getDerechoSil().hashCode();
        }
        _hashCode += getDiasSilPag();
        _hashCode += new Long(getMtoSilPag()).hashCode();
        _hashCode += getDiasCotPag();
        _hashCode += new Long(getMtoCotPag()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntrInfEstLicCCAF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "EntrInfEstLicCCAF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "NumFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "FecEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "TipoEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "Comentario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechoSil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "DerechoSil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasSilPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "DiasSilPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoSilPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "MtoSilPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasCotPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "DiasCotPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtoCotPag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "MtoCotPag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
