package cl.araucana.independientes.vo;

import java.util.Date;

public class TablaPromotoresVO {

    private long idPromotor;
    private int idCaptador;

    private String digVerificador;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String tipoOrgan;
    private String tipoCargo;
    private String codArea;
    private String nroTelefono;
    private String fechaInicio;
    private Date fechaInicioDate;
    private String fechaBaja;
    private Date fechaBajaDate;
    private int estPromot;
    private String glosaCalle;

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getCodArea() {
        return codArea;
    }
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }
    public String getDigVerificador() {
        return digVerificador;
    }
    public void setDigVerificador(String digVerificador) {
        this.digVerificador = digVerificador;
    }
    public int getEstPromot() {
        return estPromot;
    }
    public void setEstPromot(int estPromot) {
        this.estPromot = estPromot;
    }
    public String getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    public Date getFechaBajaDate() {
        return fechaBajaDate;
    }
    public void setFechaBajaDate(Date fechaBajaDate) {
        this.fechaBajaDate = fechaBajaDate;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getGlosaCalle() {
        return glosaCalle;
    }
    public void setGlosaCalle(String glosaCalle) {
        this.glosaCalle = glosaCalle;
    }
    public int getIdCaptador() {
        return idCaptador;
    }
    public void setIdCaptador(int idCaptador) {
        this.idCaptador = idCaptador;
    }
    public long getIdPromotor() {
        return idPromotor;
    }
    public void setIdPromotor(long idPromotor) {
        this.idPromotor = idPromotor;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getNroTelefono() {
        return nroTelefono;
    }
    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }
    public String getTipoCargo() {
        return tipoCargo;
    }
    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }
    public String getTipoOrgan() {
        return tipoOrgan;
    }
    public void setTipoOrgan(String tipoOrgan) {
        this.tipoOrgan = tipoOrgan;
    }
    public Date getFechaInicioDate() {
        return fechaInicioDate;
    }
    public void setFechaInicioDate(Date fechaInicioDate) {
        this.fechaInicioDate = fechaInicioDate;
    }

}
