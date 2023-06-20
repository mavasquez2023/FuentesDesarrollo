package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class EstadoSMS {
    private int id;
    private String usuario;
    private String rut;
    private String digitoValidador;
    private String celular;
    private String codigoSMS;
    private String codigoURL;
    private String estado;
    private Timestamp fechaEnvio;
    private Timestamp fechaRecepcion;

    public EstadoSMS() {
    }

    public EstadoSMS(int id, String usuario, String rut, String digitoValidador, String celular, String codigoSMS, String codigoURL, String estado, Timestamp fechaEnvio, Timestamp fechaRecepcion) {
        this.id = id;
        this.usuario = usuario;
        this.rut = rut;
        this.digitoValidador = digitoValidador;
        this.celular = celular;
        this.codigoSMS = codigoSMS;
        this.codigoURL = codigoURL;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.fechaRecepcion = fechaRecepcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getCodigoSMS() {
        return codigoSMS;
    }

    public void setCodigoSMS(String codigoSMS) {
        this.codigoSMS = codigoSMS;
    }

    public String getCodigoURL() {
        return codigoURL;
    }

    public void setCodigoURL(String codigoURL) {
        this.codigoURL = codigoURL;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
}
