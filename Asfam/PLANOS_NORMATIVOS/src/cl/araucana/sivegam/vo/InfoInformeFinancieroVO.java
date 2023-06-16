package cl.araucana.sivegam.vo;

import java.util.Date;

public class InfoInformeFinancieroVO {

    /* variables de la clase */
    private long idInformeFinanciero;
    private String periodo;
    private int codigoEntidad;
    private String nombreEntidad;
    private String fecDepositoExcedente;
    private Date fecDepositoExcedenteDate;
    private long totalSuperAvitDeficit;
    private long totalSuperAvitDeficitFinal;

    /* Generacion de get and set */

    public String getFecDepositoExcedente() {
        return fecDepositoExcedente;
    }

    public void setFecDepositoExcedente(String fecDepositoExcedente) {
        this.fecDepositoExcedente = fecDepositoExcedente;
    }

    public long getIdInformeFinanciero() {
        return idInformeFinanciero;
    }

    public void setIdInformeFinanciero(long idInformeFinanciero) {
        this.idInformeFinanciero = idInformeFinanciero;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public long getTotalSuperAvitDeficit() {
        return totalSuperAvitDeficit;
    }

    public void setTotalSuperAvitDeficit(long totalSuperAvitDeficit) {
        this.totalSuperAvitDeficit = totalSuperAvitDeficit;
    }

    public long getTotalSuperAvitDeficitFinal() {
        return totalSuperAvitDeficitFinal;
    }

    public void setTotalSuperAvitDeficitFinal(long totalSuperAvitDeficitFinal) {
        this.totalSuperAvitDeficitFinal = totalSuperAvitDeficitFinal;
    }

    public Date getFecDepositoExcedenteDate() {
        return fecDepositoExcedenteDate;
    }

    public void setFecDepositoExcedenteDate(Date fecDepositoExcedenteDate) {
        this.fecDepositoExcedenteDate = fecDepositoExcedenteDate;
    }

    public int getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(int codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }
}
