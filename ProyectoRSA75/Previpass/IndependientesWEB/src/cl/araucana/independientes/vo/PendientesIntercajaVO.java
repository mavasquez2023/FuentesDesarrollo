package cl.araucana.independientes.vo;

import java.util.Date;

public class PendientesIntercajaVO {

    /*declaracion de variables de la clase.*/
    private String resultado;
    private int codResultado;

    //datos de sistema
    private String usuarioLogueado;
    private String fechaSistena;

    //Datos de filtro
    private String fechaInferior;
    private Date fechaInferiorDate;
    private String fechaSuperior;
    private Date fechaSuperiorDate;
    private int tipoArchivo;
    private long numeroRegistro;
    private long idMaestroArchivo;

    //arreglo para la grilla
    private LinPendientesIntercajaVO[] lisPendientesIntercaja;
    private long correlCasePendiente;

    /*Establecimiento de get and set.*/
    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public String getFechaInferior() {
        return fechaInferior;
    }

    public void setFechaInferior(String fechaInferior) {
        this.fechaInferior = fechaInferior;
    }

    public Date getFechaInferiorDate() {
        return fechaInferiorDate;
    }

    public void setFechaInferiorDate(Date fechaInferiorDate) {
        this.fechaInferiorDate = fechaInferiorDate;
    }

    public String getFechaSistena() {
        return fechaSistena;
    }

    public void setFechaSistena(String fechaSistena) {
        this.fechaSistena = fechaSistena;
    }

    public String getFechaSuperior() {
        return fechaSuperior;
    }

    public void setFechaSuperior(String fechaSuperior) {
        this.fechaSuperior = fechaSuperior;
    }

    public Date getFechaSuperiorDate() {
        return fechaSuperiorDate;
    }

    public void setFechaSuperiorDate(Date fechaSuperiorDate) {
        this.fechaSuperiorDate = fechaSuperiorDate;
    }

    public LinPendientesIntercajaVO[] getLisPendientesIntercaja() {
        return lisPendientesIntercaja;
    }

    public void setLisPendientesIntercaja(
            LinPendientesIntercajaVO[] lisPendientesIntercaja) {
        this.lisPendientesIntercaja = lisPendientesIntercaja;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(String usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public long getCorrelCasePendiente() {
        return correlCasePendiente;
    }

    public void setCorrelCasePendiente(long correlCasePendiente) {
        this.correlCasePendiente = correlCasePendiente;
    }

    public long getIdMaestroArchivo() {
        return idMaestroArchivo;
    }

    public void setIdMaestroArchivo(long idMaestroArchivo) {
        this.idMaestroArchivo = idMaestroArchivo;
    }




}
