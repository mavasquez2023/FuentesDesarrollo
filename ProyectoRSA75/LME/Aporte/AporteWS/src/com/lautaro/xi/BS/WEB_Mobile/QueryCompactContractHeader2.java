/**
 * QueryCompactContractHeader2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Query Compact Contract Header
 */
public class QueryCompactContractHeader2  implements java.io.Serializable {
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

    private java.lang.String BP_ANEXO;

    private java.lang.String NRO_INSCRIPCION;

    private java.lang.String RUT_EMPRESA;

    public QueryCompactContractHeader2() {
    }

    public QueryCompactContractHeader2(
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
           java.lang.String BP_ANEXO,
           java.lang.String NRO_INSCRIPCION,
           java.lang.String RUT_EMPRESA) {
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
           this.BP_ANEXO = BP_ANEXO;
           this.NRO_INSCRIPCION = NRO_INSCRIPCION;
           this.RUT_EMPRESA = RUT_EMPRESA;
    }


    /**
     * Gets the contractNumber value for this QueryCompactContractHeader2.
     * 
     * @return contractNumber   * Account Number
     */
    public java.lang.String getContractNumber() {
        return contractNumber;
    }


    /**
     * Sets the contractNumber value for this QueryCompactContractHeader2.
     * 
     * @param contractNumber   * Account Number
     */
    public void setContractNumber(java.lang.String contractNumber) {
        this.contractNumber = contractNumber;
    }


    /**
     * Gets the terminated value for this QueryCompactContractHeader2.
     * 
     * @return terminated
     */
    public java.lang.String getTerminated() {
        return terminated;
    }


    /**
     * Sets the terminated value for this QueryCompactContractHeader2.
     * 
     * @param terminated
     */
    public void setTerminated(java.lang.String terminated) {
        this.terminated = terminated;
    }


    /**
     * Gets the withExtintion value for this QueryCompactContractHeader2.
     * 
     * @return withExtintion
     */
    public java.lang.String getWithExtintion() {
        return withExtintion;
    }


    /**
     * Sets the withExtintion value for this QueryCompactContractHeader2.
     * 
     * @param withExtintion
     */
    public void setWithExtintion(java.lang.String withExtintion) {
        this.withExtintion = withExtintion;
    }


    /**
     * Gets the role value for this QueryCompactContractHeader2.
     * 
     * @return role   * BP Role
     */
    public java.lang.String getRole() {
        return role;
    }


    /**
     * Sets the role value for this QueryCompactContractHeader2.
     * 
     * @param role   * BP Role
     */
    public void setRole(java.lang.String role) {
        this.role = role;
    }


    /**
     * Gets the payer value for this QueryCompactContractHeader2.
     * 
     * @return payer
     */
    public java.lang.String getPayer() {
        return payer;
    }


    /**
     * Sets the payer value for this QueryCompactContractHeader2.
     * 
     * @param payer
     */
    public void setPayer(java.lang.String payer) {
        this.payer = payer;
    }


    /**
     * Gets the contractAmount value for this QueryCompactContractHeader2.
     * 
     * @return contractAmount   * Limit Amount
     */
    public java.lang.String getContractAmount() {
        return contractAmount;
    }


    /**
     * Sets the contractAmount value for this QueryCompactContractHeader2.
     * 
     * @param contractAmount   * Limit Amount
     */
    public void setContractAmount(java.lang.String contractAmount) {
        this.contractAmount = contractAmount;
    }


    /**
     * Gets the contractBegin value for this QueryCompactContractHeader2.
     * 
     * @return contractBegin
     */
    public java.util.Date getContractBegin() {
        return contractBegin;
    }


    /**
     * Sets the contractBegin value for this QueryCompactContractHeader2.
     * 
     * @param contractBegin
     */
    public void setContractBegin(java.util.Date contractBegin) {
        this.contractBegin = contractBegin;
    }


    /**
     * Gets the contractEnd value for this QueryCompactContractHeader2.
     * 
     * @return contractEnd
     */
    public java.util.Date getContractEnd() {
        return contractEnd;
    }


    /**
     * Sets the contractEnd value for this QueryCompactContractHeader2.
     * 
     * @param contractEnd
     */
    public void setContractEnd(java.util.Date contractEnd) {
        this.contractEnd = contractEnd;
    }


    /**
     * Gets the installmentAmount value for this QueryCompactContractHeader2.
     * 
     * @return installmentAmount   * Amount of the Amount Item
     */
    public java.lang.String getInstallmentAmount() {
        return installmentAmount;
    }


    /**
     * Sets the installmentAmount value for this QueryCompactContractHeader2.
     * 
     * @param installmentAmount   * Amount of the Amount Item
     */
    public void setInstallmentAmount(java.lang.String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }


    /**
     * Gets the installmentQuantity value for this QueryCompactContractHeader2.
     * 
     * @return installmentQuantity   * Counter
     */
    public java.lang.String getInstallmentQuantity() {
        return installmentQuantity;
    }


    /**
     * Sets the installmentQuantity value for this QueryCompactContractHeader2.
     * 
     * @param installmentQuantity   * Counter
     */
    public void setInstallmentQuantity(java.lang.String installmentQuantity) {
        this.installmentQuantity = installmentQuantity;
    }


    /**
     * Gets the repacta value for this QueryCompactContractHeader2.
     * 
     * @return repacta
     */
    public java.lang.String getRepacta() {
        return repacta;
    }


    /**
     * Sets the repacta value for this QueryCompactContractHeader2.
     * 
     * @param repacta
     */
    public void setRepacta(java.lang.String repacta) {
        this.repacta = repacta;
    }


    /**
     * Gets the contractCurrency value for this QueryCompactContractHeader2.
     * 
     * @return contractCurrency
     */
    public java.lang.String getContractCurrency() {
        return contractCurrency;
    }


    /**
     * Sets the contractCurrency value for this QueryCompactContractHeader2.
     * 
     * @param contractCurrency
     */
    public void setContractCurrency(java.lang.String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }


    /**
     * Gets the reprogramac value for this QueryCompactContractHeader2.
     * 
     * @return reprogramac
     */
    public java.lang.String getReprogramac() {
        return reprogramac;
    }


    /**
     * Sets the reprogramac value for this QueryCompactContractHeader2.
     * 
     * @param reprogramac
     */
    public void setReprogramac(java.lang.String reprogramac) {
        this.reprogramac = reprogramac;
    }


    /**
     * Gets the BP_ANEXO value for this QueryCompactContractHeader2.
     * 
     * @return BP_ANEXO
     */
    public java.lang.String getBP_ANEXO() {
        return BP_ANEXO;
    }


    /**
     * Sets the BP_ANEXO value for this QueryCompactContractHeader2.
     * 
     * @param BP_ANEXO
     */
    public void setBP_ANEXO(java.lang.String BP_ANEXO) {
        this.BP_ANEXO = BP_ANEXO;
    }


    /**
     * Gets the NRO_INSCRIPCION value for this QueryCompactContractHeader2.
     * 
     * @return NRO_INSCRIPCION
     */
    public java.lang.String getNRO_INSCRIPCION() {
        return NRO_INSCRIPCION;
    }


    /**
     * Sets the NRO_INSCRIPCION value for this QueryCompactContractHeader2.
     * 
     * @param NRO_INSCRIPCION
     */
    public void setNRO_INSCRIPCION(java.lang.String NRO_INSCRIPCION) {
        this.NRO_INSCRIPCION = NRO_INSCRIPCION;
    }


    /**
     * Gets the RUT_EMPRESA value for this QueryCompactContractHeader2.
     * 
     * @return RUT_EMPRESA
     */
    public java.lang.String getRUT_EMPRESA() {
        return RUT_EMPRESA;
    }


    /**
     * Sets the RUT_EMPRESA value for this QueryCompactContractHeader2.
     * 
     * @param RUT_EMPRESA
     */
    public void setRUT_EMPRESA(java.lang.String RUT_EMPRESA) {
        this.RUT_EMPRESA = RUT_EMPRESA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryCompactContractHeader2)) return false;
        QueryCompactContractHeader2 other = (QueryCompactContractHeader2) obj;
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
            ((this.BP_ANEXO==null && other.getBP_ANEXO()==null) || 
             (this.BP_ANEXO!=null &&
              this.BP_ANEXO.equals(other.getBP_ANEXO()))) &&
            ((this.NRO_INSCRIPCION==null && other.getNRO_INSCRIPCION()==null) || 
             (this.NRO_INSCRIPCION!=null &&
              this.NRO_INSCRIPCION.equals(other.getNRO_INSCRIPCION()))) &&
            ((this.RUT_EMPRESA==null && other.getRUT_EMPRESA()==null) || 
             (this.RUT_EMPRESA!=null &&
              this.RUT_EMPRESA.equals(other.getRUT_EMPRESA())));
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
        if (getBP_ANEXO() != null) {
            _hashCode += getBP_ANEXO().hashCode();
        }
        if (getNRO_INSCRIPCION() != null) {
            _hashCode += getNRO_INSCRIPCION().hashCode();
        }
        if (getRUT_EMPRESA() != null) {
            _hashCode += getRUT_EMPRESA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryCompactContractHeader2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "QueryCompactContractHeader2"));
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
        elemField.setFieldName("BP_ANEXO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BP_ANEXO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRO_INSCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NRO_INSCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUT_EMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RUT_EMPRESA"));
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
