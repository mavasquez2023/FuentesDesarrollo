package cl.araucana.independientes.vo;

import java.util.Date;

public class SesionDirectorioVO {

    private long idSesionDirectorio;
    private long numeroSesion;
    private String fechaSesion;
    private Date fechaSesionDate;
    private String fechaInicioMov;
    private Date fechaInicioMovDate;
    private String fechaCorteMov;
    private Date fechaCorteMovDate;

    /*Establecimiento de get and set*/
    public String getFechaCorteMov() {
        return fechaCorteMov;
    }
    public void setFechaCorteMov(String fechaCorteMov) {
        this.fechaCorteMov = fechaCorteMov;
    }
    public Date getFechaCorteMovDate() {
        return fechaCorteMovDate;
    }
    public void setFechaCorteMovDate(Date fechaCorteMovDate) {
        this.fechaCorteMovDate = fechaCorteMovDate;
    }
    public String getFechaInicioMov() {
        return fechaInicioMov;
    }
    public void setFechaInicioMov(String fechaInicioMov) {
        this.fechaInicioMov = fechaInicioMov;
    }
    public Date getFechaInicioMovDate() {
        return fechaInicioMovDate;
    }
    public void setFechaInicioMovDate(Date fechaInicioMovDate) {
        this.fechaInicioMovDate = fechaInicioMovDate;
    }
    public String getFechaSesion() {
        return fechaSesion;
    }
    public void setFechaSesion(String fechaSesion) {
        this.fechaSesion = fechaSesion;
    }
    public Date getFechaSesionDate() {
        return fechaSesionDate;
    }
    public void setFechaSesionDate(Date fechaSesionDate) {
        this.fechaSesionDate = fechaSesionDate;
    }
    public long getIdSesionDirectorio() {
        return idSesionDirectorio;
    }
    public void setIdSesionDirectorio(long idSesionDirectorio) {
        this.idSesionDirectorio = idSesionDirectorio;
    }
    public long getNumeroSesion() {
        return numeroSesion;
    }
    public void setNumeroSesion(long numeroSesion) {
        this.numeroSesion = numeroSesion;
    }
}
