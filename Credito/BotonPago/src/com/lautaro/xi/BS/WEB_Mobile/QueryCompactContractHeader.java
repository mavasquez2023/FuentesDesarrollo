/**
 * QueryCompactContractHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Compact Contract Header
 */
public class QueryCompactContractHeader  implements java.io.Serializable {
    /* Account Number */
    private java.lang.String contractNumber;

    private java.lang.String terminated;

    private java.lang.String withExtintion;

    /* BP Role */
    private java.lang.String role;

    private java.lang.String payer;

    /* Limit Amount */
    private java.lang.String contractAmount;

    private java.util.Date contractBegin;

    private java.util.Date contractEnd;

    /* Amount of the Amount Item */
    private java.lang.String installmentAmount;

    /* Counter */
    private java.lang.String installmentQuantity;

    private java.lang.String repacta;

    private java.lang.String contractCurrency;

    private java.lang.String reprogramac;

    private java.util.Date fecha_ult_pago;

    /* 8000003746 */
    private java.lang.String insolvencia;

    /* 8000003746 */
    private java.lang.String seg_Desgravamen;

    /* 8000003746 */
    private java.lang.String seg_Cesantia;

    /* 8000003746 */
    private java.lang.String castigo;

    public QueryCompactContractHeader() {
    }

    public QueryCompactContractHeader(
           java.lang.String contractNumber,
           java.lang.String terminated,
           java.lang.String withExtintion,
           java.lang.String role,
           java.lang.String payer,
           java.lang.String contractAmount,
           java.util.Date contractBegin,
           java.util.Date contractEnd,
           java.lang.String installmentAmount,
           java.lang.String installmentQuantity,
           java.lang.String repacta,
           java.lang.String contractCurrency,
           java.lang.String reprogramac,
           java.util.Date fecha_ult_pago,
           java.lang.String insolvencia,
           java.lang.String seg_Desgravamen,
           java.lang.String seg_Cesantia,
           java.lang.String castigo) {
           this.contractNumber = contractNumber;
           this.terminated = terminated;
           this.withExtintion = withExtintion;
           this.role = role;
           this.payer = payer;
           this.contractAmount = contractAmount;
           this.contractBegin = contractBegin;
           this.contractEnd = contractEnd;
           this.installmentAmount = installmentAmount;
           this.installmentQuantity = installmentQuantity;
           this.repacta = repacta;
           this.contractCurrency = contractCurrency;
           this.reprogramac = reprogramac;
           this.fecha_ult_pago = fecha_ult_pago;
           this.insolvencia = insolvencia;
           this.seg_Desgravamen = seg_Desgravamen;
           this.seg_Cesantia = seg_Cesantia;
           this.castigo = castigo;
    }


    /**
     * Gets the contractNumber value for this QueryCompactContractHeader.
     * 
     * @return contractNumber   * Account Number
     */
    public java.lang.String getContractNumber() {
        return contractNumber;
    }


    /**
     * Sets the contractNumber value for this QueryCompactContractHeader.
     * 
     * @param contractNumber   * Account Number
     */
    public void setContractNumber(java.lang.String contractNumber) {
        this.contractNumber = contractNumber;
    }


    /**
     * Gets the terminated value for this QueryCompactContractHeader.
     * 
     * @return terminated
     */
    public java.lang.String getTerminated() {
        return terminated;
    }


    /**
     * Sets the terminated value for this QueryCompactContractHeader.
     * 
     * @param terminated
     */
    public void setTerminated(java.lang.String terminated) {
        this.terminated = terminated;
    }


    /**
     * Gets the withExtintion value for this QueryCompactContractHeader.
     * 
     * @return withExtintion
     */
    public java.lang.String getWithExtintion() {
        return withExtintion;
    }


    /**
     * Sets the withExtintion value for this QueryCompactContractHeader.
     * 
     * @param withExtintion
     */
    public void setWithExtintion(java.lang.String withExtintion) {
        this.withExtintion = withExtintion;
    }


    /**
     * Gets the role value for this QueryCompactContractHeader.
     * 
     * @return role   * BP Role
     */
    public java.lang.String getRole() {
        return role;
    }


    /**
     * Sets the role value for this QueryCompactContractHeader.
     * 
     * @param role   * BP Role
     */
    public void setRole(java.lang.String role) {
        this.role = role;
    }


    /**
     * Gets the payer value for this QueryCompactContractHeader.
     * 
     * @return payer
     */
    public java.lang.String getPayer() {
        return payer;
    }


    /**
     * Sets the payer value for this QueryCompactContractHeader.
     * 
     * @param payer
     */
    public void setPayer(java.lang.String payer) {
        this.payer = payer;
    }


    /**
     * Gets the contractAmount value for this QueryCompactContractHeader.
     * 
     * @return contractAmount   * Limit Amount
     */
    public java.lang.String getContractAmount() {
        return contractAmount;
    }


    /**
     * Sets the contractAmount value for this QueryCompactContractHeader.
     * 
     * @param contractAmount   * Limit Amount
     */
    public void setContractAmount(java.lang.String contractAmount) {
        this.contractAmount = contractAmount;
    }


    /**
     * Gets the contractBegin value for this QueryCompactContractHeader.
     * 
     * @return contractBegin
     */
    public java.util.Date getContractBegin() {
        return contractBegin;
    }


    /**
     * Sets the contractBegin value for this QueryCompactContractHeader.
     * 
     * @param contractBegin
     */
    public void setContractBegin(java.util.Date contractBegin) {
        this.contractBegin = contractBegin;
    }


    /**
     * Gets the contractEnd value for this QueryCompactContractHeader.
     * 
     * @return contractEnd
     */
    public java.util.Date getContractEnd() {
        return contractEnd;
    }


    /**
     * Sets the contractEnd value for this QueryCompactContractHeader.
     * 
     * @param contractEnd
     */
    public void setContractEnd(java.util.Date contractEnd) {
        this.contractEnd = contractEnd;
    }


    /**
     * Gets the installmentAmount value for this QueryCompactContractHeader.
     * 
     * @return installmentAmount   * Amount of the Amount Item
     */
    public java.lang.String getInstallmentAmount() {
        return installmentAmount;
    }


    /**
     * Sets the installmentAmount value for this QueryCompactContractHeader.
     * 
     * @param installmentAmount   * Amount of the Amount Item
     */
    public void setInstallmentAmount(java.lang.String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }


    /**
     * Gets the installmentQuantity value for this QueryCompactContractHeader.
     * 
     * @return installmentQuantity   * Counter
     */
    public java.lang.String getInstallmentQuantity() {
        return installmentQuantity;
    }


    /**
     * Sets the installmentQuantity value for this QueryCompactContractHeader.
     * 
     * @param installmentQuantity   * Counter
     */
    public void setInstallmentQuantity(java.lang.String installmentQuantity) {
        this.installmentQuantity = installmentQuantity;
    }


    /**
     * Gets the repacta value for this QueryCompactContractHeader.
     * 
     * @return repacta
     */
    public java.lang.String getRepacta() {
        return repacta;
    }


    /**
     * Sets the repacta value for this QueryCompactContractHeader.
     * 
     * @param repacta
     */
    public void setRepacta(java.lang.String repacta) {
        this.repacta = repacta;
    }


    /**
     * Gets the contractCurrency value for this QueryCompactContractHeader.
     * 
     * @return contractCurrency
     */
    public java.lang.String getContractCurrency() {
        return contractCurrency;
    }


    /**
     * Sets the contractCurrency value for this QueryCompactContractHeader.
     * 
     * @param contractCurrency
     */
    public void setContractCurrency(java.lang.String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }


    /**
     * Gets the reprogramac value for this QueryCompactContractHeader.
     * 
     * @return reprogramac
     */
    public java.lang.String getReprogramac() {
        return reprogramac;
    }


    /**
     * Sets the reprogramac value for this QueryCompactContractHeader.
     * 
     * @param reprogramac
     */
    public void setReprogramac(java.lang.String reprogramac) {
        this.reprogramac = reprogramac;
    }


    /**
     * Gets the fecha_ult_pago value for this QueryCompactContractHeader.
     * 
     * @return fecha_ult_pago
     */
    public java.util.Date getFecha_ult_pago() {
        return fecha_ult_pago;
    }


    /**
     * Sets the fecha_ult_pago value for this QueryCompactContractHeader.
     * 
     * @param fecha_ult_pago
     */
    public void setFecha_ult_pago(java.util.Date fecha_ult_pago) {
        this.fecha_ult_pago = fecha_ult_pago;
    }


    /**
     * Gets the insolvencia value for this QueryCompactContractHeader.
     * 
     * @return insolvencia   * 8000003746
     */
    public java.lang.String getInsolvencia() {
        return insolvencia;
    }


    /**
     * Sets the insolvencia value for this QueryCompactContractHeader.
     * 
     * @param insolvencia   * 8000003746
     */
    public void setInsolvencia(java.lang.String insolvencia) {
        this.insolvencia = insolvencia;
    }


    /**
     * Gets the seg_Desgravamen value for this QueryCompactContractHeader.
     * 
     * @return seg_Desgravamen   * 8000003746
     */
    public java.lang.String getSeg_Desgravamen() {
        return seg_Desgravamen;
    }


    /**
     * Sets the seg_Desgravamen value for this QueryCompactContractHeader.
     * 
     * @param seg_Desgravamen   * 8000003746
     */
    public void setSeg_Desgravamen(java.lang.String seg_Desgravamen) {
        this.seg_Desgravamen = seg_Desgravamen;
    }


    /**
     * Gets the seg_Cesantia value for this QueryCompactContractHeader.
     * 
     * @return seg_Cesantia   * 8000003746
     */
    public java.lang.String getSeg_Cesantia() {
        return seg_Cesantia;
    }


    /**
     * Sets the seg_Cesantia value for this QueryCompactContractHeader.
     * 
     * @param seg_Cesantia   * 8000003746
     */
    public void setSeg_Cesantia(java.lang.String seg_Cesantia) {
        this.seg_Cesantia = seg_Cesantia;
    }


    /**
     * Gets the castigo value for this QueryCompactContractHeader.
     * 
     * @return castigo   * 8000003746
     */
    public java.lang.String getCastigo() {
        return castigo;
    }


    /**
     * Sets the castigo value for this QueryCompactContractHeader.
     * 
     * @param castigo   * 8000003746
     */
    public void setCastigo(java.lang.String castigo) {
        this.castigo = castigo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryCompactContractHeader)) return false;
        QueryCompactContractHeader other = (QueryCompactContractHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contractNumber==null && other.getContractNumber()==null) || 
             (this.contractNumber!=null &&
              this.contractNumber.equals(other.getContractNumber()))) &&
            ((this.terminated==null && other.getTerminated()==null) || 
             (this.terminated!=null &&
              this.terminated.equals(other.getTerminated()))) &&
            ((this.withExtintion==null && other.getWithExtintion()==null) || 
             (this.withExtintion!=null &&
              this.withExtintion.equals(other.getWithExtintion()))) &&
            ((this.role==null && other.getRole()==null) || 
             (this.role!=null &&
              this.role.equals(other.getRole()))) &&
            ((this.payer==null && other.getPayer()==null) || 
             (this.payer!=null &&
              this.payer.equals(other.getPayer()))) &&
            ((this.contractAmount==null && other.getContractAmount()==null) || 
             (this.contractAmount!=null &&
              this.contractAmount.equals(other.getContractAmount()))) &&
            ((this.contractBegin==null && other.getContractBegin()==null) || 
             (this.contractBegin!=null &&
              this.contractBegin.equals(other.getContractBegin()))) &&
            ((this.contractEnd==null && other.getContractEnd()==null) || 
             (this.contractEnd!=null &&
              this.contractEnd.equals(other.getContractEnd()))) &&
            ((this.installmentAmount==null && other.getInstallmentAmount()==null) || 
             (this.installmentAmount!=null &&
              this.installmentAmount.equals(other.getInstallmentAmount()))) &&
            ((this.installmentQuantity==null && other.getInstallmentQuantity()==null) || 
             (this.installmentQuantity!=null &&
              this.installmentQuantity.equals(other.getInstallmentQuantity()))) &&
            ((this.repacta==null && other.getRepacta()==null) || 
             (this.repacta!=null &&
              this.repacta.equals(other.getRepacta()))) &&
            ((this.contractCurrency==null && other.getContractCurrency()==null) || 
             (this.contractCurrency!=null &&
              this.contractCurrency.equals(other.getContractCurrency()))) &&
            ((this.reprogramac==null && other.getReprogramac()==null) || 
             (this.reprogramac!=null &&
              this.reprogramac.equals(other.getReprogramac()))) &&
            ((this.fecha_ult_pago==null && other.getFecha_ult_pago()==null) || 
             (this.fecha_ult_pago!=null &&
              this.fecha_ult_pago.equals(other.getFecha_ult_pago()))) &&
            ((this.insolvencia==null && other.getInsolvencia()==null) || 
             (this.insolvencia!=null &&
              this.insolvencia.equals(other.getInsolvencia()))) &&
            ((this.seg_Desgravamen==null && other.getSeg_Desgravamen()==null) || 
             (this.seg_Desgravamen!=null &&
              this.seg_Desgravamen.equals(other.getSeg_Desgravamen()))) &&
            ((this.seg_Cesantia==null && other.getSeg_Cesantia()==null) || 
             (this.seg_Cesantia!=null &&
              this.seg_Cesantia.equals(other.getSeg_Cesantia()))) &&
            ((this.castigo==null && other.getCastigo()==null) || 
             (this.castigo!=null &&
              this.castigo.equals(other.getCastigo())));
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
        if (getContractNumber() != null) {
            _hashCode += getContractNumber().hashCode();
        }
        if (getTerminated() != null) {
            _hashCode += getTerminated().hashCode();
        }
        if (getWithExtintion() != null) {
            _hashCode += getWithExtintion().hashCode();
        }
        if (getRole() != null) {
            _hashCode += getRole().hashCode();
        }
        if (getPayer() != null) {
            _hashCode += getPayer().hashCode();
        }
        if (getContractAmount() != null) {
            _hashCode += getContractAmount().hashCode();
        }
        if (getContractBegin() != null) {
            _hashCode += getContractBegin().hashCode();
        }
        if (getContractEnd() != null) {
            _hashCode += getContractEnd().hashCode();
        }
        if (getInstallmentAmount() != null) {
            _hashCode += getInstallmentAmount().hashCode();
        }
        if (getInstallmentQuantity() != null) {
            _hashCode += getInstallmentQuantity().hashCode();
        }
        if (getRepacta() != null) {
            _hashCode += getRepacta().hashCode();
        }
        if (getContractCurrency() != null) {
            _hashCode += getContractCurrency().hashCode();
        }
        if (getReprogramac() != null) {
            _hashCode += getReprogramac().hashCode();
        }
        if (getFecha_ult_pago() != null) {
            _hashCode += getFecha_ult_pago().hashCode();
        }
        if (getInsolvencia() != null) {
            _hashCode += getInsolvencia().hashCode();
        }
        if (getSeg_Desgravamen() != null) {
            _hashCode += getSeg_Desgravamen().hashCode();
        }
        if (getSeg_Cesantia() != null) {
            _hashCode += getSeg_Cesantia().hashCode();
        }
        if (getCastigo() != null) {
            _hashCode += getCastigo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryCompactContractHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContractHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Terminated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withExtintion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WithExtintion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Role"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Payer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractBegin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractBegin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installmentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstallmentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installmentQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstallmentQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repacta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Repacta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reprogramac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Reprogramac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_ult_pago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Fecha_ult_pago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insolvencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Insolvencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seg_Desgravamen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Seg_Desgravamen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seg_Cesantia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Seg_Cesantia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("castigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Castigo"));
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
