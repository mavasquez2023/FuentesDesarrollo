package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class LoteSMSLog {
    private int id;
    private String sistema;
    private String codigoLoteSMS;
    private String estado;
    private int mensajesTotal;
    private int mensajesEnviados;
    private int mensajesInvalidos;
    private int mensajesErroneos;
    private Timestamp fechaInicio;
    private Timestamp fechaTermino;

    public LoteSMSLog() {
    }

    public LoteSMSLog(int id, String sistema, String codigoLoteSMS, String estado, int mensajesTotal, int mensajesEnviados, int mensajesInvalidos, int mensajesErroneos, Timestamp fechaInicio, Timestamp fechaTermino) {
        this.id = id;
        this.sistema = sistema;
        this.codigoLoteSMS = codigoLoteSMS;
        this.estado = estado;
        this.mensajesTotal = mensajesTotal;
        this.mensajesEnviados = mensajesEnviados;
        this.mensajesInvalidos = mensajesInvalidos;
        this.mensajesErroneos = mensajesErroneos;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
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

    public String getCodigoLoteSMS() {
        return codigoLoteSMS;
    }

    public void setCodigoLoteSMS(String codigoLoteSMS) {
        this.codigoLoteSMS = codigoLoteSMS;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMensajesTotal() {
        return mensajesTotal;
    }

    public void setMensajesTotal(int mensajesTotal) {
        this.mensajesTotal = mensajesTotal;
    }

    public int getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(int mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    public int getMensajesInvalidos() {
        return mensajesInvalidos;
    }

    public void setMensajesInvalidos(int mensajesInvalidos) {
        this.mensajesInvalidos = mensajesInvalidos;
    }

    public int getMensajesErroneos() {
        return mensajesErroneos;
    }

    public void setMensajesErroneos(int mensajesErroneos) {
        this.mensajesErroneos = mensajesErroneos;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Timestamp fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}