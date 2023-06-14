/**
 * BOLETADefTypeDetalle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.acepta.soap.ca4xml;

public class BOLETADefTypeDetalle  implements java.io.Serializable {
    private org.apache.axis.types.PositiveInteger nroLinDet;

    private com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem[] cdgItem;

    private com.acepta.soap.ca4xml.BOLETADefTypeDetalleIndExe indExe;

    private com.acepta.soap.ca4xml.BOLETADefTypeDetalleItemEspectaculo itemEspectaculo;

    private java.lang.String RUTMandante;

    private java.lang.String nmbItem;

    private com.acepta.soap.ca4xml.BOLETADefTypeDetalleInfoTicket infoTicket;

    private java.lang.String dscItem;

    private java.math.BigDecimal qtyItem;

    private java.lang.String unmdItem;

    private java.math.BigDecimal prcItem;

    private java.math.BigDecimal descuentoPct;

    private org.apache.axis.types.NonNegativeInteger descuentoMonto;

    private java.math.BigDecimal recargoPct;

    private org.apache.axis.types.NonNegativeInteger recargoMonto;

    private org.apache.axis.types.NonNegativeInteger montoItem;

    public BOLETADefTypeDetalle() {
    }

    public BOLETADefTypeDetalle(
           org.apache.axis.types.PositiveInteger nroLinDet,
           com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem[] cdgItem,
           com.acepta.soap.ca4xml.BOLETADefTypeDetalleIndExe indExe,
           com.acepta.soap.ca4xml.BOLETADefTypeDetalleItemEspectaculo itemEspectaculo,
           java.lang.String RUTMandante,
           java.lang.String nmbItem,
           com.acepta.soap.ca4xml.BOLETADefTypeDetalleInfoTicket infoTicket,
           java.lang.String dscItem,
           java.math.BigDecimal qtyItem,
           java.lang.String unmdItem,
           java.math.BigDecimal prcItem,
           java.math.BigDecimal descuentoPct,
           org.apache.axis.types.NonNegativeInteger descuentoMonto,
           java.math.BigDecimal recargoPct,
           org.apache.axis.types.NonNegativeInteger recargoMonto,
           org.apache.axis.types.NonNegativeInteger montoItem) {
           this.nroLinDet = nroLinDet;
           this.cdgItem = cdgItem;
           this.indExe = indExe;
           this.itemEspectaculo = itemEspectaculo;
           this.RUTMandante = RUTMandante;
           this.nmbItem = nmbItem;
           this.infoTicket = infoTicket;
           this.dscItem = dscItem;
           this.qtyItem = qtyItem;
           this.unmdItem = unmdItem;
           this.prcItem = prcItem;
           this.descuentoPct = descuentoPct;
           this.descuentoMonto = descuentoMonto;
           this.recargoPct = recargoPct;
           this.recargoMonto = recargoMonto;
           this.montoItem = montoItem;
    }


    /**
     * Gets the nroLinDet value for this BOLETADefTypeDetalle.
     * 
     * @return nroLinDet
     */
    public org.apache.axis.types.PositiveInteger getNroLinDet() {
        return nroLinDet;
    }


    /**
     * Sets the nroLinDet value for this BOLETADefTypeDetalle.
     * 
     * @param nroLinDet
     */
    public void setNroLinDet(org.apache.axis.types.PositiveInteger nroLinDet) {
        this.nroLinDet = nroLinDet;
    }


    /**
     * Gets the cdgItem value for this BOLETADefTypeDetalle.
     * 
     * @return cdgItem
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem[] getCdgItem() {
        return cdgItem;
    }


    /**
     * Sets the cdgItem value for this BOLETADefTypeDetalle.
     * 
     * @param cdgItem
     */
    public void setCdgItem(com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem[] cdgItem) {
        this.cdgItem = cdgItem;
    }

    public com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem getCdgItem(int i) {
        return this.cdgItem[i];
    }

    public void setCdgItem(int i, com.acepta.soap.ca4xml.BOLETADefTypeDetalleCdgItem _value) {
        this.cdgItem[i] = _value;
    }


    /**
     * Gets the indExe value for this BOLETADefTypeDetalle.
     * 
     * @return indExe
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDetalleIndExe getIndExe() {
        return indExe;
    }


    /**
     * Sets the indExe value for this BOLETADefTypeDetalle.
     * 
     * @param indExe
     */
    public void setIndExe(com.acepta.soap.ca4xml.BOLETADefTypeDetalleIndExe indExe) {
        this.indExe = indExe;
    }


    /**
     * Gets the itemEspectaculo value for this BOLETADefTypeDetalle.
     * 
     * @return itemEspectaculo
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDetalleItemEspectaculo getItemEspectaculo() {
        return itemEspectaculo;
    }


    /**
     * Sets the itemEspectaculo value for this BOLETADefTypeDetalle.
     * 
     * @param itemEspectaculo
     */
    public void setItemEspectaculo(com.acepta.soap.ca4xml.BOLETADefTypeDetalleItemEspectaculo itemEspectaculo) {
        this.itemEspectaculo = itemEspectaculo;
    }


    /**
     * Gets the RUTMandante value for this BOLETADefTypeDetalle.
     * 
     * @return RUTMandante
     */
    public java.lang.String getRUTMandante() {
        return RUTMandante;
    }


    /**
     * Sets the RUTMandante value for this BOLETADefTypeDetalle.
     * 
     * @param RUTMandante
     */
    public void setRUTMandante(java.lang.String RUTMandante) {
        this.RUTMandante = RUTMandante;
    }


    /**
     * Gets the nmbItem value for this BOLETADefTypeDetalle.
     * 
     * @return nmbItem
     */
    public java.lang.String getNmbItem() {
        return nmbItem;
    }


    /**
     * Sets the nmbItem value for this BOLETADefTypeDetalle.
     * 
     * @param nmbItem
     */
    public void setNmbItem(java.lang.String nmbItem) {
        this.nmbItem = nmbItem;
    }


    /**
     * Gets the infoTicket value for this BOLETADefTypeDetalle.
     * 
     * @return infoTicket
     */
    public com.acepta.soap.ca4xml.BOLETADefTypeDetalleInfoTicket getInfoTicket() {
        return infoTicket;
    }


    /**
     * Sets the infoTicket value for this BOLETADefTypeDetalle.
     * 
     * @param infoTicket
     */
    public void setInfoTicket(com.acepta.soap.ca4xml.BOLETADefTypeDetalleInfoTicket infoTicket) {
        this.infoTicket = infoTicket;
    }


    /**
     * Gets the dscItem value for this BOLETADefTypeDetalle.
     * 
     * @return dscItem
     */
    public java.lang.String getDscItem() {
        return dscItem;
    }


    /**
     * Sets the dscItem value for this BOLETADefTypeDetalle.
     * 
     * @param dscItem
     */
    public void setDscItem(java.lang.String dscItem) {
        this.dscItem = dscItem;
    }


    /**
     * Gets the qtyItem value for this BOLETADefTypeDetalle.
     * 
     * @return qtyItem
     */
    public java.math.BigDecimal getQtyItem() {
        return qtyItem;
    }


    /**
     * Sets the qtyItem value for this BOLETADefTypeDetalle.
     * 
     * @param qtyItem
     */
    public void setQtyItem(java.math.BigDecimal qtyItem) {
        this.qtyItem = qtyItem;
    }


    /**
     * Gets the unmdItem value for this BOLETADefTypeDetalle.
     * 
     * @return unmdItem
     */
    public java.lang.String getUnmdItem() {
        return unmdItem;
    }


    /**
     * Sets the unmdItem value for this BOLETADefTypeDetalle.
     * 
     * @param unmdItem
     */
    public void setUnmdItem(java.lang.String unmdItem) {
        this.unmdItem = unmdItem;
    }


    /**
     * Gets the prcItem value for this BOLETADefTypeDetalle.
     * 
     * @return prcItem
     */
    public java.math.BigDecimal getPrcItem() {
        return prcItem;
    }


    /**
     * Sets the prcItem value for this BOLETADefTypeDetalle.
     * 
     * @param prcItem
     */
    public void setPrcItem(java.math.BigDecimal prcItem) {
        this.prcItem = prcItem;
    }


    /**
     * Gets the descuentoPct value for this BOLETADefTypeDetalle.
     * 
     * @return descuentoPct
     */
    public java.math.BigDecimal getDescuentoPct() {
        return descuentoPct;
    }


    /**
     * Sets the descuentoPct value for this BOLETADefTypeDetalle.
     * 
     * @param descuentoPct
     */
    public void setDescuentoPct(java.math.BigDecimal descuentoPct) {
        this.descuentoPct = descuentoPct;
    }


    /**
     * Gets the descuentoMonto value for this BOLETADefTypeDetalle.
     * 
     * @return descuentoMonto
     */
    public org.apache.axis.types.NonNegativeInteger getDescuentoMonto() {
        return descuentoMonto;
    }


    /**
     * Sets the descuentoMonto value for this BOLETADefTypeDetalle.
     * 
     * @param descuentoMonto
     */
    public void setDescuentoMonto(org.apache.axis.types.NonNegativeInteger descuentoMonto) {
        this.descuentoMonto = descuentoMonto;
    }


    /**
     * Gets the recargoPct value for this BOLETADefTypeDetalle.
     * 
     * @return recargoPct
     */
    public java.math.BigDecimal getRecargoPct() {
        return recargoPct;
    }


    /**
     * Sets the recargoPct value for this BOLETADefTypeDetalle.
     * 
     * @param recargoPct
     */
    public void setRecargoPct(java.math.BigDecimal recargoPct) {
        this.recargoPct = recargoPct;
    }


    /**
     * Gets the recargoMonto value for this BOLETADefTypeDetalle.
     * 
     * @return recargoMonto
     */
    public org.apache.axis.types.NonNegativeInteger getRecargoMonto() {
        return recargoMonto;
    }


    /**
     * Sets the recargoMonto value for this BOLETADefTypeDetalle.
     * 
     * @param recargoMonto
     */
    public void setRecargoMonto(org.apache.axis.types.NonNegativeInteger recargoMonto) {
        this.recargoMonto = recargoMonto;
    }


    /**
     * Gets the montoItem value for this BOLETADefTypeDetalle.
     * 
     * @return montoItem
     */
    public org.apache.axis.types.NonNegativeInteger getMontoItem() {
        return montoItem;
    }


    /**
     * Sets the montoItem value for this BOLETADefTypeDetalle.
     * 
     * @param montoItem
     */
    public void setMontoItem(org.apache.axis.types.NonNegativeInteger montoItem) {
        this.montoItem = montoItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BOLETADefTypeDetalle)) return false;
        BOLETADefTypeDetalle other = (BOLETADefTypeDetalle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nroLinDet==null && other.getNroLinDet()==null) || 
             (this.nroLinDet!=null &&
              this.nroLinDet.equals(other.getNroLinDet()))) &&
            ((this.cdgItem==null && other.getCdgItem()==null) || 
             (this.cdgItem!=null &&
              java.util.Arrays.equals(this.cdgItem, other.getCdgItem()))) &&
            ((this.indExe==null && other.getIndExe()==null) || 
             (this.indExe!=null &&
              this.indExe.equals(other.getIndExe()))) &&
            ((this.itemEspectaculo==null && other.getItemEspectaculo()==null) || 
             (this.itemEspectaculo!=null &&
              this.itemEspectaculo.equals(other.getItemEspectaculo()))) &&
            ((this.RUTMandante==null && other.getRUTMandante()==null) || 
             (this.RUTMandante!=null &&
              this.RUTMandante.equals(other.getRUTMandante()))) &&
            ((this.nmbItem==null && other.getNmbItem()==null) || 
             (this.nmbItem!=null &&
              this.nmbItem.equals(other.getNmbItem()))) &&
            ((this.infoTicket==null && other.getInfoTicket()==null) || 
             (this.infoTicket!=null &&
              this.infoTicket.equals(other.getInfoTicket()))) &&
            ((this.dscItem==null && other.getDscItem()==null) || 
             (this.dscItem!=null &&
              this.dscItem.equals(other.getDscItem()))) &&
            ((this.qtyItem==null && other.getQtyItem()==null) || 
             (this.qtyItem!=null &&
              this.qtyItem.equals(other.getQtyItem()))) &&
            ((this.unmdItem==null && other.getUnmdItem()==null) || 
             (this.unmdItem!=null &&
              this.unmdItem.equals(other.getUnmdItem()))) &&
            ((this.prcItem==null && other.getPrcItem()==null) || 
             (this.prcItem!=null &&
              this.prcItem.equals(other.getPrcItem()))) &&
            ((this.descuentoPct==null && other.getDescuentoPct()==null) || 
             (this.descuentoPct!=null &&
              this.descuentoPct.equals(other.getDescuentoPct()))) &&
            ((this.descuentoMonto==null && other.getDescuentoMonto()==null) || 
             (this.descuentoMonto!=null &&
              this.descuentoMonto.equals(other.getDescuentoMonto()))) &&
            ((this.recargoPct==null && other.getRecargoPct()==null) || 
             (this.recargoPct!=null &&
              this.recargoPct.equals(other.getRecargoPct()))) &&
            ((this.recargoMonto==null && other.getRecargoMonto()==null) || 
             (this.recargoMonto!=null &&
              this.recargoMonto.equals(other.getRecargoMonto()))) &&
            ((this.montoItem==null && other.getMontoItem()==null) || 
             (this.montoItem!=null &&
              this.montoItem.equals(other.getMontoItem())));
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
        if (getNroLinDet() != null) {
            _hashCode += getNroLinDet().hashCode();
        }
        if (getCdgItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCdgItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCdgItem(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIndExe() != null) {
            _hashCode += getIndExe().hashCode();
        }
        if (getItemEspectaculo() != null) {
            _hashCode += getItemEspectaculo().hashCode();
        }
        if (getRUTMandante() != null) {
            _hashCode += getRUTMandante().hashCode();
        }
        if (getNmbItem() != null) {
            _hashCode += getNmbItem().hashCode();
        }
        if (getInfoTicket() != null) {
            _hashCode += getInfoTicket().hashCode();
        }
        if (getDscItem() != null) {
            _hashCode += getDscItem().hashCode();
        }
        if (getQtyItem() != null) {
            _hashCode += getQtyItem().hashCode();
        }
        if (getUnmdItem() != null) {
            _hashCode += getUnmdItem().hashCode();
        }
        if (getPrcItem() != null) {
            _hashCode += getPrcItem().hashCode();
        }
        if (getDescuentoPct() != null) {
            _hashCode += getDescuentoPct().hashCode();
        }
        if (getDescuentoMonto() != null) {
            _hashCode += getDescuentoMonto().hashCode();
        }
        if (getRecargoPct() != null) {
            _hashCode += getRecargoPct().hashCode();
        }
        if (getRecargoMonto() != null) {
            _hashCode += getRecargoMonto().hashCode();
        }
        if (getMontoItem() != null) {
            _hashCode += getMontoItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BOLETADefTypeDetalle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">BOLETADefType>Detalle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroLinDet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NroLinDet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "positiveInteger"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdgItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CdgItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Detalle>CdgItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indExe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IndExe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Detalle>IndExe"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemEspectaculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ItemEspectaculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Detalle>ItemEspectaculo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUTMandante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUTMandante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmbItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NmbItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoTicket");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InfoTicket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", ">>BOLETADefType>Detalle>InfoTicket"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DscItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtyItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QtyItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unmdItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UnmdItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prcItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrcItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descuentoPct");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DescuentoPct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "PctType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descuentoMonto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DescuentoMonto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recargoPct");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecargoPct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://acepta.com/soap/ca4xml", "PctType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recargoMonto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecargoMonto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoItem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MontoItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "nonNegativeInteger"));
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
