package cl.araucana.independientes.vo;


public class TablaBeneficiosVO {

    /*Declaracion de variables*/
    private long idBeneficio;
    private String glosaCorta;
    private String glosa;
    private String codigoContable;
    private int estado;
    private String tipoPago;
    private String valorPago;
    private String montoPagarMax;
    private String carencia;
    private String recurrencia;
    private String plazoCobro;
    private String fechaIniValidez;
    private String fechaFinValidez;
    private String isCausanteUnico;

    public String getCarencia() {
        return carencia;
    }
    public void setCarencia(String carencia) {
        this.carencia = carencia;
    }
    public String getCodigoContable() {
        return codigoContable;
    }
    public void setCodigoContable(String codigoContable) {
        this.codigoContable = codigoContable;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getFechaFinValidez() {
        return fechaFinValidez;
    }
    public void setFechaFinValidez(String fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }
    public String getFechaIniValidez() {
        return fechaIniValidez;
    }
    public void setFechaIniValidez(String fechaIniValidez) {
        this.fechaIniValidez = fechaIniValidez;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    public String getGlosaCorta() {
        return glosaCorta;
    }
    public void setGlosaCorta(String glosaCorta) {
        this.glosaCorta = glosaCorta;
    }
    public long getIdBeneficio() {
        return idBeneficio;
    }
    public void setIdBeneficio(long idBeneficio) {
        this.idBeneficio = idBeneficio;
    }
    public String getIsCausanteUnico() {
        return isCausanteUnico;
    }
    public void setIsCausanteUnico(String isCausanteUnico) {
        this.isCausanteUnico = isCausanteUnico;
    }
    public String getMontoPagarMax() {
        return montoPagarMax;
    }
    public void setMontoPagarMax(String montoPagarMax) {
        this.montoPagarMax = montoPagarMax;
    }
    public String getPlazoCobro() {
        return plazoCobro;
    }
    public void setPlazoCobro(String plazoCobro) {
        this.plazoCobro = plazoCobro;
    }
    public String getRecurrencia() {
        return recurrencia;
    }
    public void setRecurrencia(String recurrencia) {
        this.recurrencia = recurrencia;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public String getValorPago() {
        return valorPago;
    }
    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

}
