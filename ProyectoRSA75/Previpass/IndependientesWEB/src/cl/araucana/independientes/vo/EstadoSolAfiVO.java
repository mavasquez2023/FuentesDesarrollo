package cl.araucana.independientes.vo;

import java.util.Date;

public class EstadoSolAfiVO {

    /*Declaracion de variables*/
    //Para Solicitud
    private int tipoSolicitud;
    private int tipoEstadoSolicitud;
    private String fechaVigencia;
    private Date fechaVigenciaDate;
    //Para Afiliado
    private int tipoEstadoAfiliado;

    public String getFechaVigencia() {
        return fechaVigencia;
    }
    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
    public Date getFechaVigenciaDate() {
        return fechaVigenciaDate;
    }
    public void setFechaVigenciaDate(Date fechaVigenciaDate) {
        this.fechaVigenciaDate = fechaVigenciaDate;
    }
    public int getTipoEstadoAfiliado() {
        return tipoEstadoAfiliado;
    }
    public void setTipoEstadoAfiliado(int tipoEstadoAfiliado) {
        this.tipoEstadoAfiliado = tipoEstadoAfiliado;
    }
    public int getTipoEstadoSolicitud() {
        return tipoEstadoSolicitud;
    }
    public void setTipoEstadoSolicitud(int tipoEstadoSolicitud) {
        this.tipoEstadoSolicitud = tipoEstadoSolicitud;
    }
    public int getTipoSolicitud() {
        return tipoSolicitud;
    }
    public void setTipoSolicitud(int tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
}
