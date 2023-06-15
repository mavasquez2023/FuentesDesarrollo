/**
 * PayoffContract2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.BS.WEB_Mobile;

public class PayoffContract2  implements java.io.Serializable {
    private java.util.Date CUOTA_HASTA;

    private java.lang.String GASTO_COBRANZA;

    private java.lang.String MONTO_CUOTA_INF;

    private java.lang.String MONTO_DIFERIDO;

    private java.lang.String SEGURO_DESGRAV;

    private java.lang.String SEGURO_CESANT;

    private java.lang.String MONTO_COM_PREP;

    private java.lang.String MONTO_INT_MOROSO;

    private java.lang.String MONTO_INT_DVG;

    private java.lang.String CUOTAS_MOROSAS;

    private java.lang.String TIPO_BP;

    private java.lang.String LINEA_CREDITO;

    private java.lang.String MOROSO;

    private java.util.Date CUOTA_DESDE;

    private java.lang.String CUOT_TRANSITO;

    private java.lang.String NUM_CUOT_TRANSITO;

    private java.lang.String CUOTA_INFORM;

    private java.lang.String MONTOCUOTA;

    private java.lang.String HONORARIOS;

    private java.lang.String MONTO_SEG_DVG;

    private java.lang.String MONTO_SEG_MOROSO;

    private com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2[] detalleCuotas;

    private java.lang.String ARREARS_AMOUNT;

    private java.lang.String CONTRACT_ID;

    private java.lang.String MONTO_EPO;

    private java.util.Date PAYMENT_DATE;

    private java.lang.String REMAINING_BALANCE;

    private java.lang.String UNPAID_CHARGE;

    private java.lang.String WAIVER_RATE;

    private java.lang.String ESTADO;

    private java.lang.String MONTO_CAP_COND;

    private java.lang.String SALDO_ADEUDADO;

    private java.lang.String SALDO_K_CUOTAS_FUTURAS;

    private java.lang.String TOTAL_A_PAGAR;

    private java.lang.String MONTO_FINAL_ADEUDADO;

    private java.lang.String TASA_INTERES;

    public PayoffContract2() {
    }

    public PayoffContract2(
           java.util.Date CUOTA_HASTA,
           java.lang.String GASTO_COBRANZA,
           java.lang.String MONTO_CUOTA_INF,
           java.lang.String MONTO_DIFERIDO,
           java.lang.String SEGURO_DESGRAV,
           java.lang.String SEGURO_CESANT,
           java.lang.String MONTO_COM_PREP,
           java.lang.String MONTO_INT_MOROSO,
           java.lang.String MONTO_INT_DVG,
           java.lang.String CUOTAS_MOROSAS,
           java.lang.String TIPO_BP,
           java.lang.String LINEA_CREDITO,
           java.lang.String MOROSO,
           java.util.Date CUOTA_DESDE,
           java.lang.String CUOT_TRANSITO,
           java.lang.String NUM_CUOT_TRANSITO,
           java.lang.String CUOTA_INFORM,
           java.lang.String MONTOCUOTA,
           java.lang.String HONORARIOS,
           java.lang.String MONTO_SEG_DVG,
           java.lang.String MONTO_SEG_MOROSO,
           com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2[] detalleCuotas,
           java.lang.String ARREARS_AMOUNT,
           java.lang.String CONTRACT_ID,
           java.lang.String MONTO_EPO,
           java.util.Date PAYMENT_DATE,
           java.lang.String REMAINING_BALANCE,
           java.lang.String UNPAID_CHARGE,
           java.lang.String WAIVER_RATE,
           java.lang.String ESTADO,
           java.lang.String MONTO_CAP_COND,
           java.lang.String SALDO_ADEUDADO,
           java.lang.String SALDO_K_CUOTAS_FUTURAS,
           java.lang.String TOTAL_A_PAGAR,
           java.lang.String MONTO_FINAL_ADEUDADO,
           java.lang.String TASA_INTERES) {
           this.CUOTA_HASTA = CUOTA_HASTA;
           this.GASTO_COBRANZA = GASTO_COBRANZA;
           this.MONTO_CUOTA_INF = MONTO_CUOTA_INF;
           this.MONTO_DIFERIDO = MONTO_DIFERIDO;
           this.SEGURO_DESGRAV = SEGURO_DESGRAV;
           this.SEGURO_CESANT = SEGURO_CESANT;
           this.MONTO_COM_PREP = MONTO_COM_PREP;
           this.MONTO_INT_MOROSO = MONTO_INT_MOROSO;
           this.MONTO_INT_DVG = MONTO_INT_DVG;
           this.CUOTAS_MOROSAS = CUOTAS_MOROSAS;
           this.TIPO_BP = TIPO_BP;
           this.LINEA_CREDITO = LINEA_CREDITO;
           this.MOROSO = MOROSO;
           this.CUOTA_DESDE = CUOTA_DESDE;
           this.CUOT_TRANSITO = CUOT_TRANSITO;
           this.NUM_CUOT_TRANSITO = NUM_CUOT_TRANSITO;
           this.CUOTA_INFORM = CUOTA_INFORM;
           this.MONTOCUOTA = MONTOCUOTA;
           this.HONORARIOS = HONORARIOS;
           this.MONTO_SEG_DVG = MONTO_SEG_DVG;
           this.MONTO_SEG_MOROSO = MONTO_SEG_MOROSO;
           this.detalleCuotas = detalleCuotas;
           this.ARREARS_AMOUNT = ARREARS_AMOUNT;
           this.CONTRACT_ID = CONTRACT_ID;
           this.MONTO_EPO = MONTO_EPO;
           this.PAYMENT_DATE = PAYMENT_DATE;
           this.REMAINING_BALANCE = REMAINING_BALANCE;
           this.UNPAID_CHARGE = UNPAID_CHARGE;
           this.WAIVER_RATE = WAIVER_RATE;
           this.ESTADO = ESTADO;
           this.MONTO_CAP_COND = MONTO_CAP_COND;
           this.SALDO_ADEUDADO = SALDO_ADEUDADO;
           this.SALDO_K_CUOTAS_FUTURAS = SALDO_K_CUOTAS_FUTURAS;
           this.TOTAL_A_PAGAR = TOTAL_A_PAGAR;
           this.MONTO_FINAL_ADEUDADO = MONTO_FINAL_ADEUDADO;
           this.TASA_INTERES = TASA_INTERES;
    }


    /**
     * Gets the CUOTA_HASTA value for this PayoffContract2.
     * 
     * @return CUOTA_HASTA
     */
    public java.util.Date getCUOTA_HASTA() {
        return CUOTA_HASTA;
    }


    /**
     * Sets the CUOTA_HASTA value for this PayoffContract2.
     * 
     * @param CUOTA_HASTA
     */
    public void setCUOTA_HASTA(java.util.Date CUOTA_HASTA) {
        this.CUOTA_HASTA = CUOTA_HASTA;
    }


    /**
     * Gets the GASTO_COBRANZA value for this PayoffContract2.
     * 
     * @return GASTO_COBRANZA
     */
    public java.lang.String getGASTO_COBRANZA() {
        return GASTO_COBRANZA;
    }


    /**
     * Sets the GASTO_COBRANZA value for this PayoffContract2.
     * 
     * @param GASTO_COBRANZA
     */
    public void setGASTO_COBRANZA(java.lang.String GASTO_COBRANZA) {
        this.GASTO_COBRANZA = GASTO_COBRANZA;
    }


    /**
     * Gets the MONTO_CUOTA_INF value for this PayoffContract2.
     * 
     * @return MONTO_CUOTA_INF
     */
    public java.lang.String getMONTO_CUOTA_INF() {
        return MONTO_CUOTA_INF;
    }


    /**
     * Sets the MONTO_CUOTA_INF value for this PayoffContract2.
     * 
     * @param MONTO_CUOTA_INF
     */
    public void setMONTO_CUOTA_INF(java.lang.String MONTO_CUOTA_INF) {
        this.MONTO_CUOTA_INF = MONTO_CUOTA_INF;
    }


    /**
     * Gets the MONTO_DIFERIDO value for this PayoffContract2.
     * 
     * @return MONTO_DIFERIDO
     */
    public java.lang.String getMONTO_DIFERIDO() {
        return MONTO_DIFERIDO;
    }


    /**
     * Sets the MONTO_DIFERIDO value for this PayoffContract2.
     * 
     * @param MONTO_DIFERIDO
     */
    public void setMONTO_DIFERIDO(java.lang.String MONTO_DIFERIDO) {
        this.MONTO_DIFERIDO = MONTO_DIFERIDO;
    }


    /**
     * Gets the SEGURO_DESGRAV value for this PayoffContract2.
     * 
     * @return SEGURO_DESGRAV
     */
    public java.lang.String getSEGURO_DESGRAV() {
        return SEGURO_DESGRAV;
    }


    /**
     * Sets the SEGURO_DESGRAV value for this PayoffContract2.
     * 
     * @param SEGURO_DESGRAV
     */
    public void setSEGURO_DESGRAV(java.lang.String SEGURO_DESGRAV) {
        this.SEGURO_DESGRAV = SEGURO_DESGRAV;
    }


    /**
     * Gets the SEGURO_CESANT value for this PayoffContract2.
     * 
     * @return SEGURO_CESANT
     */
    public java.lang.String getSEGURO_CESANT() {
        return SEGURO_CESANT;
    }


    /**
     * Sets the SEGURO_CESANT value for this PayoffContract2.
     * 
     * @param SEGURO_CESANT
     */
    public void setSEGURO_CESANT(java.lang.String SEGURO_CESANT) {
        this.SEGURO_CESANT = SEGURO_CESANT;
    }


    /**
     * Gets the MONTO_COM_PREP value for this PayoffContract2.
     * 
     * @return MONTO_COM_PREP
     */
    public java.lang.String getMONTO_COM_PREP() {
        return MONTO_COM_PREP;
    }


    /**
     * Sets the MONTO_COM_PREP value for this PayoffContract2.
     * 
     * @param MONTO_COM_PREP
     */
    public void setMONTO_COM_PREP(java.lang.String MONTO_COM_PREP) {
        this.MONTO_COM_PREP = MONTO_COM_PREP;
    }


    /**
     * Gets the MONTO_INT_MOROSO value for this PayoffContract2.
     * 
     * @return MONTO_INT_MOROSO
     */
    public java.lang.String getMONTO_INT_MOROSO() {
        return MONTO_INT_MOROSO;
    }


    /**
     * Sets the MONTO_INT_MOROSO value for this PayoffContract2.
     * 
     * @param MONTO_INT_MOROSO
     */
    public void setMONTO_INT_MOROSO(java.lang.String MONTO_INT_MOROSO) {
        this.MONTO_INT_MOROSO = MONTO_INT_MOROSO;
    }


    /**
     * Gets the MONTO_INT_DVG value for this PayoffContract2.
     * 
     * @return MONTO_INT_DVG
     */
    public java.lang.String getMONTO_INT_DVG() {
        return MONTO_INT_DVG;
    }


    /**
     * Sets the MONTO_INT_DVG value for this PayoffContract2.
     * 
     * @param MONTO_INT_DVG
     */
    public void setMONTO_INT_DVG(java.lang.String MONTO_INT_DVG) {
        this.MONTO_INT_DVG = MONTO_INT_DVG;
    }


    /**
     * Gets the CUOTAS_MOROSAS value for this PayoffContract2.
     * 
     * @return CUOTAS_MOROSAS
     */
    public java.lang.String getCUOTAS_MOROSAS() {
        return CUOTAS_MOROSAS;
    }


    /**
     * Sets the CUOTAS_MOROSAS value for this PayoffContract2.
     * 
     * @param CUOTAS_MOROSAS
     */
    public void setCUOTAS_MOROSAS(java.lang.String CUOTAS_MOROSAS) {
        this.CUOTAS_MOROSAS = CUOTAS_MOROSAS;
    }


    /**
     * Gets the TIPO_BP value for this PayoffContract2.
     * 
     * @return TIPO_BP
     */
    public java.lang.String getTIPO_BP() {
        return TIPO_BP;
    }


    /**
     * Sets the TIPO_BP value for this PayoffContract2.
     * 
     * @param TIPO_BP
     */
    public void setTIPO_BP(java.lang.String TIPO_BP) {
        this.TIPO_BP = TIPO_BP;
    }


    /**
     * Gets the LINEA_CREDITO value for this PayoffContract2.
     * 
     * @return LINEA_CREDITO
     */
    public java.lang.String getLINEA_CREDITO() {
        return LINEA_CREDITO;
    }


    /**
     * Sets the LINEA_CREDITO value for this PayoffContract2.
     * 
     * @param LINEA_CREDITO
     */
    public void setLINEA_CREDITO(java.lang.String LINEA_CREDITO) {
        this.LINEA_CREDITO = LINEA_CREDITO;
    }


    /**
     * Gets the MOROSO value for this PayoffContract2.
     * 
     * @return MOROSO
     */
    public java.lang.String getMOROSO() {
        return MOROSO;
    }


    /**
     * Sets the MOROSO value for this PayoffContract2.
     * 
     * @param MOROSO
     */
    public void setMOROSO(java.lang.String MOROSO) {
        this.MOROSO = MOROSO;
    }


    /**
     * Gets the CUOTA_DESDE value for this PayoffContract2.
     * 
     * @return CUOTA_DESDE
     */
    public java.util.Date getCUOTA_DESDE() {
        return CUOTA_DESDE;
    }


    /**
     * Sets the CUOTA_DESDE value for this PayoffContract2.
     * 
     * @param CUOTA_DESDE
     */
    public void setCUOTA_DESDE(java.util.Date CUOTA_DESDE) {
        this.CUOTA_DESDE = CUOTA_DESDE;
    }


    /**
     * Gets the CUOT_TRANSITO value for this PayoffContract2.
     * 
     * @return CUOT_TRANSITO
     */
    public java.lang.String getCUOT_TRANSITO() {
        return CUOT_TRANSITO;
    }


    /**
     * Sets the CUOT_TRANSITO value for this PayoffContract2.
     * 
     * @param CUOT_TRANSITO
     */
    public void setCUOT_TRANSITO(java.lang.String CUOT_TRANSITO) {
        this.CUOT_TRANSITO = CUOT_TRANSITO;
    }


    /**
     * Gets the NUM_CUOT_TRANSITO value for this PayoffContract2.
     * 
     * @return NUM_CUOT_TRANSITO
     */
    public java.lang.String getNUM_CUOT_TRANSITO() {
        return NUM_CUOT_TRANSITO;
    }


    /**
     * Sets the NUM_CUOT_TRANSITO value for this PayoffContract2.
     * 
     * @param NUM_CUOT_TRANSITO
     */
    public void setNUM_CUOT_TRANSITO(java.lang.String NUM_CUOT_TRANSITO) {
        this.NUM_CUOT_TRANSITO = NUM_CUOT_TRANSITO;
    }


    /**
     * Gets the CUOTA_INFORM value for this PayoffContract2.
     * 
     * @return CUOTA_INFORM
     */
    public java.lang.String getCUOTA_INFORM() {
        return CUOTA_INFORM;
    }


    /**
     * Sets the CUOTA_INFORM value for this PayoffContract2.
     * 
     * @param CUOTA_INFORM
     */
    public void setCUOTA_INFORM(java.lang.String CUOTA_INFORM) {
        this.CUOTA_INFORM = CUOTA_INFORM;
    }


    /**
     * Gets the MONTOCUOTA value for this PayoffContract2.
     * 
     * @return MONTOCUOTA
     */
    public java.lang.String getMONTOCUOTA() {
        return MONTOCUOTA;
    }


    /**
     * Sets the MONTOCUOTA value for this PayoffContract2.
     * 
     * @param MONTOCUOTA
     */
    public void setMONTOCUOTA(java.lang.String MONTOCUOTA) {
        this.MONTOCUOTA = MONTOCUOTA;
    }


    /**
     * Gets the HONORARIOS value for this PayoffContract2.
     * 
     * @return HONORARIOS
     */
    public java.lang.String getHONORARIOS() {
        return HONORARIOS;
    }


    /**
     * Sets the HONORARIOS value for this PayoffContract2.
     * 
     * @param HONORARIOS
     */
    public void setHONORARIOS(java.lang.String HONORARIOS) {
        this.HONORARIOS = HONORARIOS;
    }


    /**
     * Gets the MONTO_SEG_DVG value for this PayoffContract2.
     * 
     * @return MONTO_SEG_DVG
     */
    public java.lang.String getMONTO_SEG_DVG() {
        return MONTO_SEG_DVG;
    }


    /**
     * Sets the MONTO_SEG_DVG value for this PayoffContract2.
     * 
     * @param MONTO_SEG_DVG
     */
    public void setMONTO_SEG_DVG(java.lang.String MONTO_SEG_DVG) {
        this.MONTO_SEG_DVG = MONTO_SEG_DVG;
    }


    /**
     * Gets the MONTO_SEG_MOROSO value for this PayoffContract2.
     * 
     * @return MONTO_SEG_MOROSO
     */
    public java.lang.String getMONTO_SEG_MOROSO() {
        return MONTO_SEG_MOROSO;
    }


    /**
     * Sets the MONTO_SEG_MOROSO value for this PayoffContract2.
     * 
     * @param MONTO_SEG_MOROSO
     */
    public void setMONTO_SEG_MOROSO(java.lang.String MONTO_SEG_MOROSO) {
        this.MONTO_SEG_MOROSO = MONTO_SEG_MOROSO;
    }


    /**
     * Gets the detalleCuotas value for this PayoffContract2.
     * 
     * @return detalleCuotas
     */
    public com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2[] getDetalleCuotas() {
        return detalleCuotas;
    }


    /**
     * Sets the detalleCuotas value for this PayoffContract2.
     * 
     * @param detalleCuotas
     */
    public void setDetalleCuotas(com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2[] detalleCuotas) {
        this.detalleCuotas = detalleCuotas;
    }

    public com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2 getDetalleCuotas(int i) {
        return this.detalleCuotas[i];
    }

    public void setDetalleCuotas(int i, com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2 _value) {
        this.detalleCuotas[i] = _value;
    }


    /**
     * Gets the ARREARS_AMOUNT value for this PayoffContract2.
     * 
     * @return ARREARS_AMOUNT
     */
    public java.lang.String getARREARS_AMOUNT() {
        return ARREARS_AMOUNT;
    }


    /**
     * Sets the ARREARS_AMOUNT value for this PayoffContract2.
     * 
     * @param ARREARS_AMOUNT
     */
    public void setARREARS_AMOUNT(java.lang.String ARREARS_AMOUNT) {
        this.ARREARS_AMOUNT = ARREARS_AMOUNT;
    }


    /**
     * Gets the CONTRACT_ID value for this PayoffContract2.
     * 
     * @return CONTRACT_ID
     */
    public java.lang.String getCONTRACT_ID() {
        return CONTRACT_ID;
    }


    /**
     * Sets the CONTRACT_ID value for this PayoffContract2.
     * 
     * @param CONTRACT_ID
     */
    public void setCONTRACT_ID(java.lang.String CONTRACT_ID) {
        this.CONTRACT_ID = CONTRACT_ID;
    }


    /**
     * Gets the MONTO_EPO value for this PayoffContract2.
     * 
     * @return MONTO_EPO
     */
    public java.lang.String getMONTO_EPO() {
        return MONTO_EPO;
    }


    /**
     * Sets the MONTO_EPO value for this PayoffContract2.
     * 
     * @param MONTO_EPO
     */
    public void setMONTO_EPO(java.lang.String MONTO_EPO) {
        this.MONTO_EPO = MONTO_EPO;
    }


    /**
     * Gets the PAYMENT_DATE value for this PayoffContract2.
     * 
     * @return PAYMENT_DATE
     */
    public java.util.Date getPAYMENT_DATE() {
        return PAYMENT_DATE;
    }


    /**
     * Sets the PAYMENT_DATE value for this PayoffContract2.
     * 
     * @param PAYMENT_DATE
     */
    public void setPAYMENT_DATE(java.util.Date PAYMENT_DATE) {
        this.PAYMENT_DATE = PAYMENT_DATE;
    }


    /**
     * Gets the REMAINING_BALANCE value for this PayoffContract2.
     * 
     * @return REMAINING_BALANCE
     */
    public java.lang.String getREMAINING_BALANCE() {
        return REMAINING_BALANCE;
    }


    /**
     * Sets the REMAINING_BALANCE value for this PayoffContract2.
     * 
     * @param REMAINING_BALANCE
     */
    public void setREMAINING_BALANCE(java.lang.String REMAINING_BALANCE) {
        this.REMAINING_BALANCE = REMAINING_BALANCE;
    }


    /**
     * Gets the UNPAID_CHARGE value for this PayoffContract2.
     * 
     * @return UNPAID_CHARGE
     */
    public java.lang.String getUNPAID_CHARGE() {
        return UNPAID_CHARGE;
    }


    /**
     * Sets the UNPAID_CHARGE value for this PayoffContract2.
     * 
     * @param UNPAID_CHARGE
     */
    public void setUNPAID_CHARGE(java.lang.String UNPAID_CHARGE) {
        this.UNPAID_CHARGE = UNPAID_CHARGE;
    }


    /**
     * Gets the WAIVER_RATE value for this PayoffContract2.
     * 
     * @return WAIVER_RATE
     */
    public java.lang.String getWAIVER_RATE() {
        return WAIVER_RATE;
    }


    /**
     * Sets the WAIVER_RATE value for this PayoffContract2.
     * 
     * @param WAIVER_RATE
     */
    public void setWAIVER_RATE(java.lang.String WAIVER_RATE) {
        this.WAIVER_RATE = WAIVER_RATE;
    }


    /**
     * Gets the ESTADO value for this PayoffContract2.
     * 
     * @return ESTADO
     */
    public java.lang.String getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this PayoffContract2.
     * 
     * @param ESTADO
     */
    public void setESTADO(java.lang.String ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the MONTO_CAP_COND value for this PayoffContract2.
     * 
     * @return MONTO_CAP_COND
     */
    public java.lang.String getMONTO_CAP_COND() {
        return MONTO_CAP_COND;
    }


    /**
     * Sets the MONTO_CAP_COND value for this PayoffContract2.
     * 
     * @param MONTO_CAP_COND
     */
    public void setMONTO_CAP_COND(java.lang.String MONTO_CAP_COND) {
        this.MONTO_CAP_COND = MONTO_CAP_COND;
    }


    /**
     * Gets the SALDO_ADEUDADO value for this PayoffContract2.
     * 
     * @return SALDO_ADEUDADO
     */
    public java.lang.String getSALDO_ADEUDADO() {
        return SALDO_ADEUDADO;
    }


    /**
     * Sets the SALDO_ADEUDADO value for this PayoffContract2.
     * 
     * @param SALDO_ADEUDADO
     */
    public void setSALDO_ADEUDADO(java.lang.String SALDO_ADEUDADO) {
        this.SALDO_ADEUDADO = SALDO_ADEUDADO;
    }


    /**
     * Gets the SALDO_K_CUOTAS_FUTURAS value for this PayoffContract2.
     * 
     * @return SALDO_K_CUOTAS_FUTURAS
     */
    public java.lang.String getSALDO_K_CUOTAS_FUTURAS() {
        return SALDO_K_CUOTAS_FUTURAS;
    }


    /**
     * Sets the SALDO_K_CUOTAS_FUTURAS value for this PayoffContract2.
     * 
     * @param SALDO_K_CUOTAS_FUTURAS
     */
    public void setSALDO_K_CUOTAS_FUTURAS(java.lang.String SALDO_K_CUOTAS_FUTURAS) {
        this.SALDO_K_CUOTAS_FUTURAS = SALDO_K_CUOTAS_FUTURAS;
    }


    /**
     * Gets the TOTAL_A_PAGAR value for this PayoffContract2.
     * 
     * @return TOTAL_A_PAGAR
     */
    public java.lang.String getTOTAL_A_PAGAR() {
        return TOTAL_A_PAGAR;
    }


    /**
     * Sets the TOTAL_A_PAGAR value for this PayoffContract2.
     * 
     * @param TOTAL_A_PAGAR
     */
    public void setTOTAL_A_PAGAR(java.lang.String TOTAL_A_PAGAR) {
        this.TOTAL_A_PAGAR = TOTAL_A_PAGAR;
    }


    /**
     * Gets the MONTO_FINAL_ADEUDADO value for this PayoffContract2.
     * 
     * @return MONTO_FINAL_ADEUDADO
     */
    public java.lang.String getMONTO_FINAL_ADEUDADO() {
        return MONTO_FINAL_ADEUDADO;
    }


    /**
     * Sets the MONTO_FINAL_ADEUDADO value for this PayoffContract2.
     * 
     * @param MONTO_FINAL_ADEUDADO
     */
    public void setMONTO_FINAL_ADEUDADO(java.lang.String MONTO_FINAL_ADEUDADO) {
        this.MONTO_FINAL_ADEUDADO = MONTO_FINAL_ADEUDADO;
    }


    /**
     * Gets the TASA_INTERES value for this PayoffContract2.
     * 
     * @return TASA_INTERES
     */
    public java.lang.String getTASA_INTERES() {
        return TASA_INTERES;
    }


    /**
     * Sets the TASA_INTERES value for this PayoffContract2.
     * 
     * @param TASA_INTERES
     */
    public void setTASA_INTERES(java.lang.String TASA_INTERES) {
        this.TASA_INTERES = TASA_INTERES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayoffContract2)) return false;
        PayoffContract2 other = (PayoffContract2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CUOTA_HASTA==null && other.getCUOTA_HASTA()==null) || 
             (this.CUOTA_HASTA!=null &&
              this.CUOTA_HASTA.equals(other.getCUOTA_HASTA()))) &&
            ((this.GASTO_COBRANZA==null && other.getGASTO_COBRANZA()==null) || 
             (this.GASTO_COBRANZA!=null &&
              this.GASTO_COBRANZA.equals(other.getGASTO_COBRANZA()))) &&
            ((this.MONTO_CUOTA_INF==null && other.getMONTO_CUOTA_INF()==null) || 
             (this.MONTO_CUOTA_INF!=null &&
              this.MONTO_CUOTA_INF.equals(other.getMONTO_CUOTA_INF()))) &&
            ((this.MONTO_DIFERIDO==null && other.getMONTO_DIFERIDO()==null) || 
             (this.MONTO_DIFERIDO!=null &&
              this.MONTO_DIFERIDO.equals(other.getMONTO_DIFERIDO()))) &&
            ((this.SEGURO_DESGRAV==null && other.getSEGURO_DESGRAV()==null) || 
             (this.SEGURO_DESGRAV!=null &&
              this.SEGURO_DESGRAV.equals(other.getSEGURO_DESGRAV()))) &&
            ((this.SEGURO_CESANT==null && other.getSEGURO_CESANT()==null) || 
             (this.SEGURO_CESANT!=null &&
              this.SEGURO_CESANT.equals(other.getSEGURO_CESANT()))) &&
            ((this.MONTO_COM_PREP==null && other.getMONTO_COM_PREP()==null) || 
             (this.MONTO_COM_PREP!=null &&
              this.MONTO_COM_PREP.equals(other.getMONTO_COM_PREP()))) &&
            ((this.MONTO_INT_MOROSO==null && other.getMONTO_INT_MOROSO()==null) || 
             (this.MONTO_INT_MOROSO!=null &&
              this.MONTO_INT_MOROSO.equals(other.getMONTO_INT_MOROSO()))) &&
            ((this.MONTO_INT_DVG==null && other.getMONTO_INT_DVG()==null) || 
             (this.MONTO_INT_DVG!=null &&
              this.MONTO_INT_DVG.equals(other.getMONTO_INT_DVG()))) &&
            ((this.CUOTAS_MOROSAS==null && other.getCUOTAS_MOROSAS()==null) || 
             (this.CUOTAS_MOROSAS!=null &&
              this.CUOTAS_MOROSAS.equals(other.getCUOTAS_MOROSAS()))) &&
            ((this.TIPO_BP==null && other.getTIPO_BP()==null) || 
             (this.TIPO_BP!=null &&
              this.TIPO_BP.equals(other.getTIPO_BP()))) &&
            ((this.LINEA_CREDITO==null && other.getLINEA_CREDITO()==null) || 
             (this.LINEA_CREDITO!=null &&
              this.LINEA_CREDITO.equals(other.getLINEA_CREDITO()))) &&
            ((this.MOROSO==null && other.getMOROSO()==null) || 
             (this.MOROSO!=null &&
              this.MOROSO.equals(other.getMOROSO()))) &&
            ((this.CUOTA_DESDE==null && other.getCUOTA_DESDE()==null) || 
             (this.CUOTA_DESDE!=null &&
              this.CUOTA_DESDE.equals(other.getCUOTA_DESDE()))) &&
            ((this.CUOT_TRANSITO==null && other.getCUOT_TRANSITO()==null) || 
             (this.CUOT_TRANSITO!=null &&
              this.CUOT_TRANSITO.equals(other.getCUOT_TRANSITO()))) &&
            ((this.NUM_CUOT_TRANSITO==null && other.getNUM_CUOT_TRANSITO()==null) || 
             (this.NUM_CUOT_TRANSITO!=null &&
              this.NUM_CUOT_TRANSITO.equals(other.getNUM_CUOT_TRANSITO()))) &&
            ((this.CUOTA_INFORM==null && other.getCUOTA_INFORM()==null) || 
             (this.CUOTA_INFORM!=null &&
              this.CUOTA_INFORM.equals(other.getCUOTA_INFORM()))) &&
            ((this.MONTOCUOTA==null && other.getMONTOCUOTA()==null) || 
             (this.MONTOCUOTA!=null &&
              this.MONTOCUOTA.equals(other.getMONTOCUOTA()))) &&
            ((this.HONORARIOS==null && other.getHONORARIOS()==null) || 
             (this.HONORARIOS!=null &&
              this.HONORARIOS.equals(other.getHONORARIOS()))) &&
            ((this.MONTO_SEG_DVG==null && other.getMONTO_SEG_DVG()==null) || 
             (this.MONTO_SEG_DVG!=null &&
              this.MONTO_SEG_DVG.equals(other.getMONTO_SEG_DVG()))) &&
            ((this.MONTO_SEG_MOROSO==null && other.getMONTO_SEG_MOROSO()==null) || 
             (this.MONTO_SEG_MOROSO!=null &&
              this.MONTO_SEG_MOROSO.equals(other.getMONTO_SEG_MOROSO()))) &&
            ((this.detalleCuotas==null && other.getDetalleCuotas()==null) || 
             (this.detalleCuotas!=null &&
              java.util.Arrays.equals(this.detalleCuotas, other.getDetalleCuotas()))) &&
            ((this.ARREARS_AMOUNT==null && other.getARREARS_AMOUNT()==null) || 
             (this.ARREARS_AMOUNT!=null &&
              this.ARREARS_AMOUNT.equals(other.getARREARS_AMOUNT()))) &&
            ((this.CONTRACT_ID==null && other.getCONTRACT_ID()==null) || 
             (this.CONTRACT_ID!=null &&
              this.CONTRACT_ID.equals(other.getCONTRACT_ID()))) &&
            ((this.MONTO_EPO==null && other.getMONTO_EPO()==null) || 
             (this.MONTO_EPO!=null &&
              this.MONTO_EPO.equals(other.getMONTO_EPO()))) &&
            ((this.PAYMENT_DATE==null && other.getPAYMENT_DATE()==null) || 
             (this.PAYMENT_DATE!=null &&
              this.PAYMENT_DATE.equals(other.getPAYMENT_DATE()))) &&
            ((this.REMAINING_BALANCE==null && other.getREMAINING_BALANCE()==null) || 
             (this.REMAINING_BALANCE!=null &&
              this.REMAINING_BALANCE.equals(other.getREMAINING_BALANCE()))) &&
            ((this.UNPAID_CHARGE==null && other.getUNPAID_CHARGE()==null) || 
             (this.UNPAID_CHARGE!=null &&
              this.UNPAID_CHARGE.equals(other.getUNPAID_CHARGE()))) &&
            ((this.WAIVER_RATE==null && other.getWAIVER_RATE()==null) || 
             (this.WAIVER_RATE!=null &&
              this.WAIVER_RATE.equals(other.getWAIVER_RATE()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.MONTO_CAP_COND==null && other.getMONTO_CAP_COND()==null) || 
             (this.MONTO_CAP_COND!=null &&
              this.MONTO_CAP_COND.equals(other.getMONTO_CAP_COND()))) &&
            ((this.SALDO_ADEUDADO==null && other.getSALDO_ADEUDADO()==null) || 
             (this.SALDO_ADEUDADO!=null &&
              this.SALDO_ADEUDADO.equals(other.getSALDO_ADEUDADO()))) &&
            ((this.SALDO_K_CUOTAS_FUTURAS==null && other.getSALDO_K_CUOTAS_FUTURAS()==null) || 
             (this.SALDO_K_CUOTAS_FUTURAS!=null &&
              this.SALDO_K_CUOTAS_FUTURAS.equals(other.getSALDO_K_CUOTAS_FUTURAS()))) &&
            ((this.TOTAL_A_PAGAR==null && other.getTOTAL_A_PAGAR()==null) || 
             (this.TOTAL_A_PAGAR!=null &&
              this.TOTAL_A_PAGAR.equals(other.getTOTAL_A_PAGAR()))) &&
            ((this.MONTO_FINAL_ADEUDADO==null && other.getMONTO_FINAL_ADEUDADO()==null) || 
             (this.MONTO_FINAL_ADEUDADO!=null &&
              this.MONTO_FINAL_ADEUDADO.equals(other.getMONTO_FINAL_ADEUDADO()))) &&
            ((this.TASA_INTERES==null && other.getTASA_INTERES()==null) || 
             (this.TASA_INTERES!=null &&
              this.TASA_INTERES.equals(other.getTASA_INTERES())));
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
        if (getCUOTA_HASTA() != null) {
            _hashCode += getCUOTA_HASTA().hashCode();
        }
        if (getGASTO_COBRANZA() != null) {
            _hashCode += getGASTO_COBRANZA().hashCode();
        }
        if (getMONTO_CUOTA_INF() != null) {
            _hashCode += getMONTO_CUOTA_INF().hashCode();
        }
        if (getMONTO_DIFERIDO() != null) {
            _hashCode += getMONTO_DIFERIDO().hashCode();
        }
        if (getSEGURO_DESGRAV() != null) {
            _hashCode += getSEGURO_DESGRAV().hashCode();
        }
        if (getSEGURO_CESANT() != null) {
            _hashCode += getSEGURO_CESANT().hashCode();
        }
        if (getMONTO_COM_PREP() != null) {
            _hashCode += getMONTO_COM_PREP().hashCode();
        }
        if (getMONTO_INT_MOROSO() != null) {
            _hashCode += getMONTO_INT_MOROSO().hashCode();
        }
        if (getMONTO_INT_DVG() != null) {
            _hashCode += getMONTO_INT_DVG().hashCode();
        }
        if (getCUOTAS_MOROSAS() != null) {
            _hashCode += getCUOTAS_MOROSAS().hashCode();
        }
        if (getTIPO_BP() != null) {
            _hashCode += getTIPO_BP().hashCode();
        }
        if (getLINEA_CREDITO() != null) {
            _hashCode += getLINEA_CREDITO().hashCode();
        }
        if (getMOROSO() != null) {
            _hashCode += getMOROSO().hashCode();
        }
        if (getCUOTA_DESDE() != null) {
            _hashCode += getCUOTA_DESDE().hashCode();
        }
        if (getCUOT_TRANSITO() != null) {
            _hashCode += getCUOT_TRANSITO().hashCode();
        }
        if (getNUM_CUOT_TRANSITO() != null) {
            _hashCode += getNUM_CUOT_TRANSITO().hashCode();
        }
        if (getCUOTA_INFORM() != null) {
            _hashCode += getCUOTA_INFORM().hashCode();
        }
        if (getMONTOCUOTA() != null) {
            _hashCode += getMONTOCUOTA().hashCode();
        }
        if (getHONORARIOS() != null) {
            _hashCode += getHONORARIOS().hashCode();
        }
        if (getMONTO_SEG_DVG() != null) {
            _hashCode += getMONTO_SEG_DVG().hashCode();
        }
        if (getMONTO_SEG_MOROSO() != null) {
            _hashCode += getMONTO_SEG_MOROSO().hashCode();
        }
        if (getDetalleCuotas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleCuotas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleCuotas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getARREARS_AMOUNT() != null) {
            _hashCode += getARREARS_AMOUNT().hashCode();
        }
        if (getCONTRACT_ID() != null) {
            _hashCode += getCONTRACT_ID().hashCode();
        }
        if (getMONTO_EPO() != null) {
            _hashCode += getMONTO_EPO().hashCode();
        }
        if (getPAYMENT_DATE() != null) {
            _hashCode += getPAYMENT_DATE().hashCode();
        }
        if (getREMAINING_BALANCE() != null) {
            _hashCode += getREMAINING_BALANCE().hashCode();
        }
        if (getUNPAID_CHARGE() != null) {
            _hashCode += getUNPAID_CHARGE().hashCode();
        }
        if (getWAIVER_RATE() != null) {
            _hashCode += getWAIVER_RATE().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getMONTO_CAP_COND() != null) {
            _hashCode += getMONTO_CAP_COND().hashCode();
        }
        if (getSALDO_ADEUDADO() != null) {
            _hashCode += getSALDO_ADEUDADO().hashCode();
        }
        if (getSALDO_K_CUOTAS_FUTURAS() != null) {
            _hashCode += getSALDO_K_CUOTAS_FUTURAS().hashCode();
        }
        if (getTOTAL_A_PAGAR() != null) {
            _hashCode += getTOTAL_A_PAGAR().hashCode();
        }
        if (getMONTO_FINAL_ADEUDADO() != null) {
            _hashCode += getMONTO_FINAL_ADEUDADO().hashCode();
        }
        if (getTASA_INTERES() != null) {
            _hashCode += getTASA_INTERES().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayoffContract2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "PayoffContract2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTA_HASTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTA_HASTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GASTO_COBRANZA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GASTO_COBRANZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CUOTA_INF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CUOTA_INF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_DIFERIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_DIFERIDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_DESGRAV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_DESGRAV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGURO_CESANT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEGURO_CESANT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_COM_PREP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_COM_PREP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_INT_MOROSO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_INT_MOROSO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_INT_DVG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_INT_DVG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTAS_MOROSAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTAS_MOROSAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_BP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIPO_BP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LINEA_CREDITO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINEA_CREDITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOROSO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MOROSO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTA_DESDE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTA_DESDE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOT_TRANSITO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOT_TRANSITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_CUOT_TRANSITO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NUM_CUOT_TRANSITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUOTA_INFORM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUOTA_INFORM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTOCUOTA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTOCUOTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HONORARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HONORARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_SEG_DVG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_SEG_DVG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_SEG_MOROSO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_SEG_MOROSO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleCuotas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DetalleCuotas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://lautaro.com/xi/BS/WEB-Mobile", "DetalleCuotasSIMULATION_IN_2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ARREARS_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ARREARS_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTRACT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_EPO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_EPO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENT_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENT_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMAINING_BALANCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMAINING_BALANCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNPAID_CHARGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UNPAID_CHARGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WAIVER_RATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WAIVER_RATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_CAP_COND");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_CAP_COND"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDO_ADEUDADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SALDO_ADEUDADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDO_K_CUOTAS_FUTURAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SALDO_K_CUOTAS_FUTURAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_A_PAGAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TOTAL_A_PAGAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONTO_FINAL_ADEUDADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MONTO_FINAL_ADEUDADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASA_INTERES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TASA_INTERES"));
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
