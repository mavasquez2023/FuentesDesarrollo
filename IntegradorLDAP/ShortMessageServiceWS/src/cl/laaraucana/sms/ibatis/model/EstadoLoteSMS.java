package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class EstadoLoteSMS {
    private int id;
    private String sistema;
    private String codigoSMS;
    private String codigoLoteSMS;
    private int codigoLoteSMSLog;
    private String estado;
    private String rut;
    private String digitoValidador;
    private String celular;
    private String mensaje;
    private Timestamp fechaCarga;
    private Timestamp fechaProceso;
    private Timestamp fechaEnvio;
    private Timestamp fechaRecepcion;
    private String referencia;
    private String estadoCodigo;
    private String estadoDescripcion;

    public EstadoLoteSMS() {
    }

    public EstadoLoteSMS(int id, String sistema, String codigoSMS, String codigoLoteSMS, int codigoLoteSMSLog, String estado, String rut, String digitoValidador, String celular, String mensaje, Timestamp fechaCarga, Timestamp fechaProceso, Timestamp fechaEnvio, Timestamp fechaRecepcion, String referencia, String estadoCodigo, String estadoDescripcion) {
        this.id = id;
        this.sistema = sistema;
        this.codigoSMS = codigoSMS;
        this.codigoLoteSMS = codigoLoteSMS;
        this.codigoLoteSMSLog = codigoLoteSMSLog;
        this.estado = estado;
        this.rut = rut;
        this.digitoValidador = digitoValidador;
        this.celular = celular;
        this.mensaje = mensaje;
        this.fechaCarga = fechaCarga;
        this.fechaProceso = fechaProceso;
        this.fechaEnvio = fechaEnvio;
        this.fechaRecepcion = fechaRecepcion;
        this.referencia = referencia;
        this.estadoCodigo = estadoCodigo;
        this.estadoDescripcion = estadoDescripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getCodigoSMS() {
        return codigoSMS;
    }

    public void setCodigoSMS(String codigoSMS) {
        this.codigoSMS = codigoSMS;
    }

    public String getCodigoLoteSMS() {
        return codigoLoteSMS;
    }

    public void setCodigoLoteSMS(String codigoLoteSMS) {
        this.codigoLoteSMS = codigoLoteSMS;
    }

    public int getCodigoLoteSMSLog() {
        return codigoLoteSMSLog;
    }

    public void setCodigoLoteSMSLog(int codigoLoteSMSLog) {
        this.codigoLoteSMSLog = codigoLoteSMSLog;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDigitoValidador() {
        return digitoValidador;
    }

    public void setDigitoValidador(String digitoValidador) {
        this.digitoValidador = digitoValidador;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Timestamp fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Timestamp getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Timestamp fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public Timestamp getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Timestamp fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Timestamp getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Timestamp fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEstadoCodigo() {
        return estadoCodigo;
    }

    public void setEstadoCodigo(String estadoCodigo) {
        this.estadoCodigo = estadoCodigo;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }
}