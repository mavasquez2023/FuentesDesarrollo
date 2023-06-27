package cl.araucana.independientes.vo;

import java.util.Date;

public class LinEntradaIntercajaVO {

    /*Declaracion de variables*/
    private String nombreArchivo;
    private int tipoArchivo;
    private String tipoArchivoGlosa;
    private String fechaCarga;
    private Date fechaCargaDate;
    private String statusProcesoGlosa;
    private int statusProceso;

    /*Generacion de get and set*/
    public String getFechaCarga() {
        return fechaCarga;
    }
    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
    public Date getFechaCargaDate() {
        return fechaCargaDate;
    }
    public void setFechaCargaDate(Date fechaCargaDate) {
        this.fechaCargaDate = fechaCargaDate;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public int getStatusProceso() {
        return statusProceso;
    }
    public void setStatusProceso(int statusProceso) {
        this.statusProceso = statusProceso;
    }
    public String getStatusProcesoGlosa() {
        return statusProcesoGlosa;
    }
    public void setStatusProcesoGlosa(String statusProcesoGlosa) {
        this.statusProcesoGlosa = statusProcesoGlosa;
    }
    public int getTipoArchivo() {
        return tipoArchivo;
    }
    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    public String getTipoArchivoGlosa() {
        return tipoArchivoGlosa;
    }
    public void setTipoArchivoGlosa(String tipoArchivoGlosa) {
        this.tipoArchivoGlosa = tipoArchivoGlosa;
    }

}
