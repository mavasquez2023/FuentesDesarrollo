package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class LoteSMS {
    private int id;
    private String estado;
    private String sistema;
    private String rut;
    private String digitoValidador;
    private String celular;
    private String mensaje;
    private Timestamp fechaCarga;
    private String referencia;

    public LoteSMS() {
    }

    public LoteSMS(int id, String estado, String sistema, String rut, String digitoValidador, String celular, String mensaje, Timestamp fechaCarga, String referencia) {
        this.id = id;
        this.estado = estado;
        this.sistema = sistema;
        this.rut = rut;
        this.digitoValidador = digitoValidador;
        this.celular = celular;
        this.mensaje = mensaje;
        this.fechaCarga = fechaCarga;
        this.referencia = referencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
