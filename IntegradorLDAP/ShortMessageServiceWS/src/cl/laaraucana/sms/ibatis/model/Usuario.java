package cl.laaraucana.sms.ibatis.model;

import java.sql.Timestamp;

public class Usuario {
    private String usuario;
    private String clave;
    private String descripcion;
    private String rol;
    private String estado;
    private Timestamp fechaCreacion;
    private Timestamp fechaActualizacion;

    public Usuario() {
    }

    public Usuario(String usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public Usuario(String usuario, String clave, String descripcion, String rol, String estado, Timestamp fechaCreacion, Timestamp fechaActualizacion) {
        this.usuario = usuario;
        this.clave = clave;
        this.descripcion = descripcion;
        this.rol = rol;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
