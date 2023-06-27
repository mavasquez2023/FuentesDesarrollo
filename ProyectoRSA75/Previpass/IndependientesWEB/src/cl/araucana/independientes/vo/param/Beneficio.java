package cl.araucana.independientes.vo.param;

import java.util.Date;

public class Beneficio {

    private int idBeneficio;
    private String glosaBeneficio;
    private String glosaCortaBeneficio;
    private String codigoContable;
    private int estadoBeneficio;
    private int tipoBeneficio; // int
    private String strTipoBeneficio; // String
    private String valorPorTipo;  // long
    private long montoMaximo;
    private int carencia;
    private int maxSolicitudes;
    private int plazoCobro;
    private Date fechaIniValidez;
    private String strFechaIniValidez;
    private Date fechaFinValidez;
    private String strFechaFinValidez;
    private int isCausanteUnico;

    private DocBeneficio[] listaDocumentosBeneficio;

    public DocBeneficio[] getListaDocumentosBeneficio() {
        return listaDocumentosBeneficio;
    }
    public void setListaDocumentosBeneficio(DocBeneficio[] listaDocumentosBeneficio) {
        this.listaDocumentosBeneficio = listaDocumentosBeneficio;
    }
    public int getCarencia() {
        return carencia;
    }
    public void setCarencia(int carencia) {
        this.carencia = carencia;
    }
    public String getCodigoContable() {
        return codigoContable;
    }
    public void setCodigoContable(String codigoContable) {
        this.codigoContable = codigoContable;
    }
    public int getEstadoBeneficio() {
        return estadoBeneficio;
    }
    public void setEstadoBeneficio(int estadoBeneficio) {
        this.estadoBeneficio = estadoBeneficio;
    }
    public Date getFechaFinValidez() {
        return fechaFinValidez;
    }
    public void setFechaFinValidez(Date fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }
    public Date getFechaIniValidez() {
        return fechaIniValidez;
    }
    public void setFechaIniValidez(Date fechaIniValidez) {
        this.fechaIniValidez = fechaIniValidez;
    }
    public String getGlosaBeneficio() {
        return glosaBeneficio;
    }
    public void setGlosaBeneficio(String glosaBeneficio) {
        this.glosaBeneficio = glosaBeneficio;
    }
    public String getGlosaCortaBeneficio() {
        return glosaCortaBeneficio;
    }
    public void setGlosaCortaBeneficio(String glosaCortaBeneficio) {
        this.glosaCortaBeneficio = glosaCortaBeneficio;
    }
    public int getIdBeneficio() {
        return idBeneficio;
    }
    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }
    public int getIsCausanteUnico() {
        return isCausanteUnico;
    }
    public void setIsCausanteUnico(int isCausanteUnico) {
        this.isCausanteUnico = isCausanteUnico;
    }
    public int getMaxSolicitudes() {
        return maxSolicitudes;
    }
    public void setMaxSolicitudes(int maxSolicitudes) {
        this.maxSolicitudes = maxSolicitudes;
    }
    public long getMontoMaximo() {
        return montoMaximo;
    }
    public void setMontoMaximo(long montoMaximo) {
        this.montoMaximo = montoMaximo;
    }
    public int getPlazoCobro() {
        return plazoCobro;
    }
    public void setPlazoCobro(int plazoCobro) {
        this.plazoCobro = plazoCobro;
    }
    public String getStrFechaFinValidez() {
        return strFechaFinValidez;
    }
    public void setStrFechaFinValidez(String strFechaFinValidez) {
        this.strFechaFinValidez = strFechaFinValidez;
    }
    public String getStrFechaIniValidez() {
        return strFechaIniValidez;
    }
    public void setStrFechaIniValidez(String strFechaIniValidez) {
        this.strFechaIniValidez = strFechaIniValidez;
    }
    public String getStrTipoBeneficio() {
        return strTipoBeneficio;
    }
    public void setStrTipoBeneficio(String strTipoBeneficio) {
        this.strTipoBeneficio = strTipoBeneficio;
    }
    public int getTipoBeneficio() {
        return tipoBeneficio;
    }
    public void setTipoBeneficio(int tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }
    public String getValorPorTipo() {
        return valorPorTipo;
    }
    public void setValorPorTipo(String valorPorTipo) {
        this.valorPorTipo = valorPorTipo;
    }

}
