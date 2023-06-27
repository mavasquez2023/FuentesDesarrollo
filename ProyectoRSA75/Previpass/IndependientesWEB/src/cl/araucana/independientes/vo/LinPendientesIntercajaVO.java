package cl.araucana.independientes.vo;

import java.util.Date;

public class LinPendientesIntercajaVO {

    /*Declaracion de variables para GRILLA.*/
    private String rutAfiliado;
    private String nombreAfiliado;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String movimientoRequerido;
    private String descripcionError;
    private String nombreArchivo;
    private String tipoArchivo;
    private int tipoArchivoCod;
    private String fechaProcesamiento;
    private Date fechaProcesamientoDate;
    private String nombreCompleto;
    private long numeroRegistroTabla;

    /*Declaracion de variables para DETALLE POR REGISTRO*/
    private String fechaSolicitud;
    private String fechaInicio;

    /*variables añadidas a tabla logcasospendientes.*/
    private long correlCasePendiente;
    private int statusResolPendiente;
    private int idTipoError;
    private long ccafCajaOrigen;
    private long ccafCajaDestino;
    private String ccafCajaOrigenGlosa;
    private String ccafCajaDestinoGlosa;

    private long id_ccaf;
    private String glosaCaja;
    private int flagRecibe;
    private String recibeAfiliado;

    /*Establecimiento de get and set.*/	
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
    public long getCcafCajaDestino() {
        return ccafCajaDestino;
    }
    public void setCcafCajaDestino(long ccafCajaDestino) {
        this.ccafCajaDestino = ccafCajaDestino;
    }
    public String getCcafCajaDestinoGlosa() {
        return ccafCajaDestinoGlosa;
    }
    public void setCcafCajaDestinoGlosa(String ccafCajaDestinoGlosa) {
        this.ccafCajaDestinoGlosa = ccafCajaDestinoGlosa;
    }
    public long getCcafCajaOrigen() {
        return ccafCajaOrigen;
    }
    public void setCcafCajaOrigen(long ccafCajaOrigen) {
        this.ccafCajaOrigen = ccafCajaOrigen;
    }
    public String getCcafCajaOrigenGlosa() {
        return ccafCajaOrigenGlosa;
    }
    public void setCcafCajaOrigenGlosa(String ccafCajaOrigenGlosa) {
        this.ccafCajaOrigenGlosa = ccafCajaOrigenGlosa;
    }
    public long getCorrelCasePendiente() {
        return correlCasePendiente;
    }
    public void setCorrelCasePendiente(long correlCasePendiente) {
        this.correlCasePendiente = correlCasePendiente;
    }
    public String getDescripcionError() {
        return descripcionError;
    }
    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaProcesamiento() {
        return fechaProcesamiento;
    }
    public void setFechaProcesamiento(String fechaProcesamiento) {
        this.fechaProcesamiento = fechaProcesamiento;
    }
    public Date getFechaProcesamientoDate() {
        return fechaProcesamientoDate;
    }
    public void setFechaProcesamientoDate(Date fechaProcesamientoDate) {
        this.fechaProcesamientoDate = fechaProcesamientoDate;
    }
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public int getFlagRecibe() {
        return flagRecibe;
    }
    public void setFlagRecibe(int flagRecibe) {
        this.flagRecibe = flagRecibe;
    }
    public String getGlosaCaja() {
        return glosaCaja;
    }
    public void setGlosaCaja(String glosaCaja) {
        this.glosaCaja = glosaCaja;
    }
    public long getId_ccaf() {
        return id_ccaf;
    }
    public void setId_ccaf(long id_ccaf) {
        this.id_ccaf = id_ccaf;
    }
    public int getIdTipoError() {
        return idTipoError;
    }
    public void setIdTipoError(int idTipoError) {
        this.idTipoError = idTipoError;
    }
    public String getMovimientoRequerido() {
        return movimientoRequerido;
    }
    public void setMovimientoRequerido(String movimientoRequerido) {
        this.movimientoRequerido = movimientoRequerido;
    }
    public String getNombreAfiliado() {
        return nombreAfiliado;
    }
    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public long getNumeroRegistroTabla() {
        return numeroRegistroTabla;
    }
    public void setNumeroRegistroTabla(long numeroRegistroTabla) {
        this.numeroRegistroTabla = numeroRegistroTabla;
    }
    public String getRecibeAfiliado() {
        return recibeAfiliado;
    }
    public void setRecibeAfiliado(String recibeAfiliado) {
        this.recibeAfiliado = recibeAfiliado;
    }
    public String getRutAfiliado() {
        return rutAfiliado;
    }
    public void setRutAfiliado(String rutAfiliado) {
        this.rutAfiliado = rutAfiliado;
    }
    public int getStatusResolPendiente() {
        return statusResolPendiente;
    }
    public void setStatusResolPendiente(int statusResolPendiente) {
        this.statusResolPendiente = statusResolPendiente;
    }
    public String getTipoArchivo() {
        return tipoArchivo;
    }
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    public int getTipoArchivoCod() {
        return tipoArchivoCod;
    }
    public void setTipoArchivoCod(int tipoArchivoCod) {
        this.tipoArchivoCod = tipoArchivoCod;
    }
}
