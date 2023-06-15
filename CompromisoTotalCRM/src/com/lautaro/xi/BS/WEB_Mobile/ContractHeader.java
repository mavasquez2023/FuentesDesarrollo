/**
 * ContractHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;


/**
 * Contract Header
 */
public class ContractHeader  implements java.io.Serializable {
    /* Número de cuenta */
    private java.lang.String contractNumber;

    /* Linea comercial Query Contract Header In */
    private java.lang.String comercialLine;

    /* Fecha de un evento */
    private java.util.Date contractEnd;

    /* Moneda del contrato */
    private java.lang.String contractCurrency;

    /* Status - Query Contract Header In */
    private java.lang.String status;

    /* Porcentaje */
    private java.lang.String monthlyInterestrate;

    /* Importe de la posición de importe */
    private java.lang.String lteAmount;

    /* Importe de la posición de importe */
    private java.lang.String notarialCharge;

    /* Importe de la posición de importe */
    private java.lang.String interestAmount;

    /* Importe de la posición de importe */
    private java.lang.String lifeInsurance;

    /* Importe de la posición de importe */
    private java.lang.String unemploymentinsur;

    /* Importe de la posición de importe */
    private java.lang.String phonoAsistance;

    /* Importe de la posición de importe */
    private java.lang.String unpaidinstAmount;

    /* Importe de la posición de importe */
    private java.lang.String arrearsAmount;

    /* Porcentaje */
    private java.lang.String waiverrate;

    /* Importe de la posición de importe */
    private java.lang.String unpaidcharge;

    /* Cantidad de Registros en Query Contract Header In */
    private java.lang.String quantityActiveinst;

    /* Cantidad de Registros en Query Contract Header In */
    private java.lang.String quantityUnpaidinst;

    /* Importe de la posición de importe */
    private java.lang.String remainingBalance;

    /* NIF para interlocutor comercial */
    private java.lang.String companyRut;

    public ContractHeader() {
    }

    public ContractHeader(
           java.lang.String contractNumber,
           java.lang.String comercialLine,
           java.util.Date contractEnd,
           java.lang.String contractCurrency,
           java.lang.String status,
           java.lang.String monthlyInterestrate,
           java.lang.String lteAmount,
           java.lang.String notarialCharge,
           java.lang.String interestAmount,
           java.lang.String lifeInsurance,
           java.lang.String unemploymentinsur,
           java.lang.String phonoAsistance,
           java.lang.String unpaidinstAmount,
           java.lang.String arrearsAmount,
           java.lang.String waiverrate,
           java.lang.String unpaidcharge,
           java.lang.String quantityActiveinst,
           java.lang.String quantityUnpaidinst,
           java.lang.String remainingBalance,
           java.lang.String companyRut) {
           this.contractNumber = contractNumber;
           this.comercialLine = comercialLine;
           this.contractEnd = contractEnd;
           this.contractCurrency = contractCurrency;
           this.status = status;
           this.monthlyInterestrate = monthlyInterestrate;
           this.lteAmount = lteAmount;
           this.notarialCharge = notarialCharge;
           this.interestAmount = interestAmount;
           this.lifeInsurance = lifeInsurance;
           this.unemploymentinsur = unemploymentinsur;
           this.phonoAsistance = phonoAsistance;
           this.unpaidinstAmount = unpaidinstAmount;
           this.arrearsAmount = arrearsAmount;
           this.waiverrate = waiverrate;
           this.unpaidcharge = unpaidcharge;
           this.quantityActiveinst = quantityActiveinst;
           this.quantityUnpaidinst = quantityUnpaidinst;
           this.remainingBalance = remainingBalance;
           this.companyRut = companyRut;
    }


    /**
     * Gets the contractNumber value for this ContractHeader.
     * 
     * @return contractNumber   * Número de cuenta
     */
    public java.lang.String getContractNumber() {
        return contractNumber;
    }


    /**
     * Sets the contractNumber value for this ContractHeader.
     * 
     * @param contractNumber   * Número de cuenta
     */
    public void setContractNumber(java.lang.String contractNumber) {
        this.contractNumber = contractNumber;
    }


    /**
     * Gets the comercialLine value for this ContractHeader.
     * 
     * @return comercialLine   * Linea comercial Query Contract Header In
     */
    public java.lang.String getComercialLine() {
        return comercialLine;
    }


    /**
     * Sets the comercialLine value for this ContractHeader.
     * 
     * @param comercialLine   * Linea comercial Query Contract Header In
     */
    public void setComercialLine(java.lang.String comercialLine) {
        this.comercialLine = comercialLine;
    }


    /**
     * Gets the contractEnd value for this ContractHeader.
     * 
     * @return contractEnd   * Fecha de un evento
     */
    public java.util.Date getContractEnd() {
        return contractEnd;
    }


    /**
     * Sets the contractEnd value for this ContractHeader.
     * 
     * @param contractEnd   * Fecha de un evento
     */
    public void setContractEnd(java.util.Date contractEnd) {
        this.contractEnd = contractEnd;
    }


    /**
     * Gets the contractCurrency value for this ContractHeader.
     * 
     * @return contractCurrency   * Moneda del contrato
     */
    public java.lang.String getContractCurrency() {
        return contractCurrency;
    }


    /**
     * Sets the contractCurrency value for this ContractHeader.
     * 
     * @param contractCurrency   * Moneda del contrato
     */
    public void setContractCurrency(java.lang.String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }


    /**
     * Gets the status value for this ContractHeader.
     * 
     * @return status   * Status - Query Contract Header In
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ContractHeader.
     * 
     * @param status   * Status - Query Contract Header In
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the monthlyInterestrate value for this ContractHeader.
     * 
     * @return monthlyInterestrate   * Porcentaje
     */
    public java.lang.String getMonthlyInterestrate() {
        return monthlyInterestrate;
    }


    /**
     * Sets the monthlyInterestrate value for this ContractHeader.
     * 
     * @param monthlyInterestrate   * Porcentaje
     */
    public void setMonthlyInterestrate(java.lang.String monthlyInterestrate) {
        this.monthlyInterestrate = monthlyInterestrate;
    }


    /**
     * Gets the lteAmount value for this ContractHeader.
     * 
     * @return lteAmount   * Importe de la posición de importe
     */
    public java.lang.String getLteAmount() {
        return lteAmount;
    }


    /**
     * Sets the lteAmount value for this ContractHeader.
     * 
     * @param lteAmount   * Importe de la posición de importe
     */
    public void setLteAmount(java.lang.String lteAmount) {
        this.lteAmount = lteAmount;
    }


    /**
     * Gets the notarialCharge value for this ContractHeader.
     * 
     * @return notarialCharge   * Importe de la posición de importe
     */
    public java.lang.String getNotarialCharge() {
        return notarialCharge;
    }


    /**
     * Sets the notarialCharge value for this ContractHeader.
     * 
     * @param notarialCharge   * Importe de la posición de importe
     */
    public void setNotarialCharge(java.lang.String notarialCharge) {
        this.notarialCharge = notarialCharge;
    }


    /**
     * Gets the interestAmount value for this ContractHeader.
     * 
     * @return interestAmount   * Importe de la posición de importe
     */
    public java.lang.String getInterestAmount() {
        return interestAmount;
    }


    /**
     * Sets the interestAmount value for this ContractHeader.
     * 
     * @param interestAmount   * Importe de la posición de importe
     */
    public void setInterestAmount(java.lang.String interestAmount) {
        this.interestAmount = interestAmount;
    }


    /**
     * Gets the lifeInsurance value for this ContractHeader.
     * 
     * @return lifeInsurance   * Importe de la posición de importe
     */
    public java.lang.String getLifeInsurance() {
        return lifeInsurance;
    }


    /**
     * Sets the lifeInsurance value for this ContractHeader.
     * 
     * @param lifeInsurance   * Importe de la posición de importe
     */
    public void setLifeInsurance(java.lang.String lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }


    /**
     * Gets the unemploymentinsur value for this ContractHeader.
     * 
     * @return unemploymentinsur   * Importe de la posición de importe
     */
    public java.lang.String getUnemploymentinsur() {
        return unemploymentinsur;
    }


    /**
     * Sets the unemploymentinsur value for this ContractHeader.
     * 
     * @param unemploymentinsur   * Importe de la posición de importe
     */
    public void setUnemploymentinsur(java.lang.String unemploymentinsur) {
        this.unemploymentinsur = unemploymentinsur;
    }


    /**
     * Gets the phonoAsistance value for this ContractHeader.
     * 
     * @return phonoAsistance   * Importe de la posición de importe
     */
    public java.lang.String getPhonoAsistance() {
        return phonoAsistance;
    }


    /**
     * Sets the phonoAsistance value for this ContractHeader.
     * 
     * @param phonoAsistance   * Importe de la posición de importe
     */
    public void setPhonoAsistance(java.lang.String phonoAsistance) {
        this.phonoAsistance = phonoAsistance;
    }


    /**
     * Gets the unpaidinstAmount value for this ContractHeader.
     * 
     * @return unpaidinstAmount   * Importe de la posición de importe
     */
    public java.lang.String getUnpaidinstAmount() {
        return unpaidinstAmount;
    }


    /**
     * Sets the unpaidinstAmount value for this ContractHeader.
     * 
     * @param unpaidinstAmount   * Importe de la posición de importe
     */
    public void setUnpaidinstAmount(java.lang.String unpaidinstAmount) {
        this.unpaidinstAmount = unpaidinstAmount;
    }


    /**
     * Gets the arrearsAmount value for this ContractHeader.
     * 
     * @return arrearsAmount   * Importe de la posición de importe
     */
    public java.lang.String getArrearsAmount() {
        return arrearsAmount;
    }


    /**
     * Sets the arrearsAmount value for this ContractHeader.
     * 
     * @param arrearsAmount   * Importe de la posición de importe
     */
    public void setArrearsAmount(java.lang.String arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }


    /**
     * Gets the waiverrate value for this ContractHeader.
     * 
     * @return waiverrate   * Porcentaje
     */
    public java.lang.String getWaiverrate() {
        return waiverrate;
    }


    /**
     * Sets the waiverrate value for this ContractHeader.
     * 
     * @param waiverrate   * Porcentaje
     */
    public void setWaiverrate(java.lang.String waiverrate) {
        this.waiverrate = waiverrate;
    }


    /**
     * Gets the unpaidcharge value for this ContractHeader.
     * 
     * @return unpaidcharge   * Importe de la posición de importe
     */
    public java.lang.String getUnpaidcharge() {
        return unpaidcharge;
    }


    /**
     * Sets the unpaidcharge value for this ContractHeader.
     * 
     * @param unpaidcharge   * Importe de la posición de importe
     */
    public void setUnpaidcharge(java.lang.String unpaidcharge) {
        this.unpaidcharge = unpaidcharge;
    }


    /**
     * Gets the quantityActiveinst value for this ContractHeader.
     * 
     * @return quantityActiveinst   * Cantidad de Registros en Query Contract Header In
     */
    public java.lang.String getQuantityActiveinst() {
        return quantityActiveinst;
    }


    /**
     * Sets the quantityActiveinst value for this ContractHeader.
     * 
     * @param quantityActiveinst   * Cantidad de Registros en Query Contract Header In
     */
    public void setQuantityActiveinst(java.lang.String quantityActiveinst) {
        this.quantityActiveinst = quantityActiveinst;
    }


    /**
     * Gets the quantityUnpaidinst value for this ContractHeader.
     * 
     * @return quantityUnpaidinst   * Cantidad de Registros en Query Contract Header In
     */
    public java.lang.String getQuantityUnpaidinst() {
        return quantityUnpaidinst;
    }


    /**
     * Sets the quantityUnpaidinst value for this ContractHeader.
     * 
     * @param quantityUnpaidinst   * Cantidad de Registros en Query Contract Header In
     */
    public void setQuantityUnpaidinst(java.lang.String quantityUnpaidinst) {
        this.quantityUnpaidinst = quantityUnpaidinst;
    }


    /**
     * Gets the remainingBalance value for this ContractHeader.
     * 
     * @return remainingBalance   * Importe de la posición de importe
     */
    public java.lang.String getRemainingBalance() {
        return remainingBalance;
    }


    /**
     * Sets the remainingBalance value for this ContractHeader.
     * 
     * @param remainingBalance   * Importe de la posición de importe
     */
    public void setRemainingBalance(java.lang.String remainingBalance) {
        this.remainingBalance = remainingBalance;
    }


    /**
     * Gets the companyRut value for this ContractHeader.
     * 
     * @return companyRut   * NIF para interlocutor comercial
     */
    public java.lang.String getCompanyRut() {
        return companyRut;
    }


    /**
     * Sets the companyRut value for this ContractHeader.
     * 
     * @param companyRut   * NIF para interlocutor comercial
     */
    public void setCompanyRut(java.lang.String companyRut) {
        this.companyRut = companyRut;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContractHeader)) return false;
        ContractHeader other = (ContractHeader) obj;
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
            ((this.comercialLine==null && other.getComercialLine()==null) || 
             (this.comercialLine!=null &&
              this.comercialLine.equals(other.getComercialLine()))) &&
            ((this.contractEnd==null && other.getContractEnd()==null) || 
             (this.contractEnd!=null &&
              this.contractEnd.equals(other.getContractEnd()))) &&
            ((this.contractCurrency==null && other.getContractCurrency()==null) || 
             (this.contractCurrency!=null &&
              this.contractCurrency.equals(other.getContractCurrency()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.monthlyInterestrate==null && other.getMonthlyInterestrate()==null) || 
             (this.monthlyInterestrate!=null &&
              this.monthlyInterestrate.equals(other.getMonthlyInterestrate()))) &&
            ((this.lteAmount==null && other.getLteAmount()==null) || 
             (this.lteAmount!=null &&
              this.lteAmount.equals(other.getLteAmount()))) &&
            ((this.notarialCharge==null && other.getNotarialCharge()==null) || 
             (this.notarialCharge!=null &&
              this.notarialCharge.equals(other.getNotarialCharge()))) &&
            ((this.interestAmount==null && other.getInterestAmount()==null) || 
             (this.interestAmount!=null &&
              this.interestAmount.equals(other.getInterestAmount()))) &&
            ((this.lifeInsurance==null && other.getLifeInsurance()==null) || 
             (this.lifeInsurance!=null &&
              this.lifeInsurance.equals(other.getLifeInsurance()))) &&
            ((this.unemploymentinsur==null && other.getUnemploymentinsur()==null) || 
             (this.unemploymentinsur!=null &&
              this.unemploymentinsur.equals(other.getUnemploymentinsur()))) &&
            ((this.phonoAsistance==null && other.getPhonoAsistance()==null) || 
             (this.phonoAsistance!=null &&
              this.phonoAsistance.equals(other.getPhonoAsistance()))) &&
            ((this.unpaidinstAmount==null && other.getUnpaidinstAmount()==null) || 
             (this.unpaidinstAmount!=null &&
              this.unpaidinstAmount.equals(other.getUnpaidinstAmount()))) &&
            ((this.arrearsAmount==null && other.getArrearsAmount()==null) || 
             (this.arrearsAmount!=null &&
              this.arrearsAmount.equals(other.getArrearsAmount()))) &&
            ((this.waiverrate==null && other.getWaiverrate()==null) || 
             (this.waiverrate!=null &&
              this.waiverrate.equals(other.getWaiverrate()))) &&
            ((this.unpaidcharge==null && other.getUnpaidcharge()==null) || 
             (this.unpaidcharge!=null &&
              this.unpaidcharge.equals(other.getUnpaidcharge()))) &&
            ((this.quantityActiveinst==null && other.getQuantityActiveinst()==null) || 
             (this.quantityActiveinst!=null &&
              this.quantityActiveinst.equals(other.getQuantityActiveinst()))) &&
            ((this.quantityUnpaidinst==null && other.getQuantityUnpaidinst()==null) || 
             (this.quantityUnpaidinst!=null &&
              this.quantityUnpaidinst.equals(other.getQuantityUnpaidinst()))) &&
            ((this.remainingBalance==null && other.getRemainingBalance()==null) || 
             (this.remainingBalance!=null &&
              this.remainingBalance.equals(other.getRemainingBalance()))) &&
            ((this.companyRut==null && other.getCompanyRut()==null) || 
             (this.companyRut!=null &&
              this.companyRut.equals(other.getCompanyRut())));
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
        if (getComercialLine() != null) {
            _hashCode += getComercialLine().hashCode();
        }
        if (getContractEnd() != null) {
            _hashCode += getContractEnd().hashCode();
        }
        if (getContractCurrency() != null) {
            _hashCode += getContractCurrency().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getMonthlyInterestrate() != null) {
            _hashCode += getMonthlyInterestrate().hashCode();
        }
        if (getLteAmount() != null) {
            _hashCode += getLteAmount().hashCode();
        }
        if (getNotarialCharge() != null) {
            _hashCode += getNotarialCharge().hashCode();
        }
        if (getInterestAmount() != null) {
            _hashCode += getInterestAmount().hashCode();
        }
        if (getLifeInsurance() != null) {
            _hashCode += getLifeInsurance().hashCode();
        }
        if (getUnemploymentinsur() != null) {
            _hashCode += getUnemploymentinsur().hashCode();
        }
        if (getPhonoAsistance() != null) {
            _hashCode += getPhonoAsistance().hashCode();
        }
        if (getUnpaidinstAmount() != null) {
            _hashCode += getUnpaidinstAmount().hashCode();
        }
        if (getArrearsAmount() != null) {
            _hashCode += getArrearsAmount().hashCode();
        }
        if (getWaiverrate() != null) {
            _hashCode += getWaiverrate().hashCode();
        }
        if (getUnpaidcharge() != null) {
            _hashCode += getUnpaidcharge().hashCode();
        }
        if (getQuantityActiveinst() != null) {
            _hashCode += getQuantityActiveinst().hashCode();
        }
        if (getQuantityUnpaidinst() != null) {
            _hashCode += getQuantityUnpaidinst().hashCode();
        }
        if (getRemainingBalance() != null) {
            _hashCode += getRemainingBalance().hashCode();
        }
        if (getCompanyRut() != null) {
            _hashCode += getCompanyRut().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContractHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "ContractHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comercialLine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ComercialLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContractCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthlyInterestrate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MonthlyInterestrate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lteAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LteAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notarialCharge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NotarialCharge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interestAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InterestAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifeInsurance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LifeInsurance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unemploymentinsur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Unemploymentinsur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phonoAsistance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PhonoAsistance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unpaidinstAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UnpaidinstAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrearsAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ArrearsAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("waiverrate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Waiverrate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unpaidcharge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Unpaidcharge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantityActiveinst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QuantityActiveinst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantityUnpaidinst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QuantityUnpaidinst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remainingBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RemainingBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyRut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CompanyRut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
