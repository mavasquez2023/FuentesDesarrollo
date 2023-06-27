package cl.araucana.independientes.vo;

import java.util.Date;

public class MaestroIntercajaVO {

    private long idMaestroArchivo;
    private String nombreArchivo;
    private String fechaCabecera;
    private Date fechaCabeceraDate;
    private int statusProceso;
    private String fechaProceso;
    private Date fechaProcesoDate;
    private long idTipoArchivo;
    private long idAnalista;
    private int statusSendMail;

    public int getStatusSendMail() {
        return statusSendMail;
    }
    public void setStatusSendMail(int statusSendMail) {
        this.statusSendMail = statusSendMail;
    }
    /*establecimiento de get and set.*/
    public String getFechaCabecera() {
        return fechaCabecera;
    }
    public void setFechaCabecera(String fechaCabecera) {
        this.fechaCabecera = fechaCabecera;
    }
    public String getFechaProceso() {
        return fechaProceso;
    }
    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }
    public Date getFechaProcesoDate() {
        return fechaProcesoDate;
    }
    public void setFechaProcesoDate(Date fechaProcesoDate) {
        this.fechaProcesoDate = fechaProcesoDate;
    }
    public long getIdAnalista() {
        return idAnalista;
    }
    public void setIdAnalista(long idAnalista) {
        this.idAnalista = idAnalista;
    }
    public long getIdTipoArchivo() {
        return idTipoArchivo;
    }
    public void setIdTipoArchivo(long idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }
    public long getIdMaestroArchivo() {
        return idMaestroArchivo;
    }
    public void setIdMaestroArchivo(long idMaestroArchivo) {
        this.idMaestroArchivo = idMaestroArchivo;
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
    public Date getFechaCabeceraDate() {
        return fechaCabeceraDate;
    }
    public void setFechaCabeceraDate(Date fechaCabeceraDate) {
        this.fechaCabeceraDate = fechaCabeceraDate;
    }
}
