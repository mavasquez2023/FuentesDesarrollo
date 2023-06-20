package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class EstadoURL {
    private int id;
    private String usuario;
    private String codigoURL;
    private String clicks;
    private String navegador;
    private String sistemaOperativo;
    private Timestamp fechaApertura;

    public EstadoURL() {
    }

    public EstadoURL(int id, String usuario, String codigoURL, String clicks, String navegador, String sistemaOperativo, Timestamp fechaApertura) {
        this.id = id;
        this.usuario = usuario;
        this.codigoURL = codigoURL;
        this.clicks = clicks;
        this.navegador = navegador;
        this.sistemaOperativo = sistemaOperativo;
        this.fechaApertura = fechaApertura;
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

    public String getCodigoURL() {
        return codigoURL;
    }

    public void setCodigoURL(String codigoURL) {
        this.codigoURL = codigoURL;
    }

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public Timestamp getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Timestamp fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
