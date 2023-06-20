package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class Sistema {
    private String sistema;
    private String descripcion;
    private String estado;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    public Sistema() {
    }

    public Sistema(String sistema, String descripcion, String estado, Timestamp fechaCreacion, Timestamp fechaActualizacion) {
        this.sistema = sistema;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
