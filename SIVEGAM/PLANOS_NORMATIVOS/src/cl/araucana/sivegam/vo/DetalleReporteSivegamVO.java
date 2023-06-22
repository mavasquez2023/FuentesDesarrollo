package cl.araucana.sivegam.vo;

import java.util.Date;

public class DetalleReporteSivegamVO {

    /* Variables de la clase */
    private long detalle_reporte;
    private long maestro_sivegam;
    private String nombre_reporte;
    private String fecha_reporte;
    private Date fechaReporteDate;
    private int status_proceso;
    private int tipo_proceso;
    private int periodo_proceso;
    private int fomato_reporte;
    private long usuario_sivegam;

    /* Generacion de get and set */
    public long getDetalle_reporte() {
        return detalle_reporte;
    }

    public void setDetalle_reporte(long detalle_reporte) {
        this.detalle_reporte = detalle_reporte;
    }

    public String getFecha_reporte() {
        return fecha_reporte;
    }

    public void setFecha_reporte(String fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }

    public Date getFechaReporteDate() {
        return fechaReporteDate;
    }

    public void setFechaReporteDate(Date fechaReporteDate) {
        this.fechaReporteDate = fechaReporteDate;
    }

    public int getFomato_reporte() {
        return fomato_reporte;
    }

    public void setFomato_reporte(int fomato_reporte) {
        this.fomato_reporte = fomato_reporte;
    }

    public long getMaestro_sivegam() {
        return maestro_sivegam;
    }

    public void setMaestro_sivegam(long maestro_sivegam) {
        this.maestro_sivegam = maestro_sivegam;
    }

    public String getNombre_reporte() {
        return nombre_reporte;
    }

    public void setNombre_reporte(String nombre_reporte) {
        this.nombre_reporte = nombre_reporte;
    }

    public int getPeriodo_proceso() {
        return periodo_proceso;
    }

    public void setPeriodo_proceso(int periodo_proceso) {
        this.periodo_proceso = periodo_proceso;
    }

    public int getStatus_proceso() {
        return status_proceso;
    }

    public void setStatus_proceso(int status_proceso) {
        this.status_proceso = status_proceso;
    }

    public int getTipo_proceso() {
        return tipo_proceso;
    }

    public void setTipo_proceso(int tipo_proceso) {
        this.tipo_proceso = tipo_proceso;
    }

    public long getUsuario_sivegam() {
        return usuario_sivegam;
    }

    public void setUsuario_sivegam(long usuario_sivegam) {
        this.usuario_sivegam = usuario_sivegam;
    }
}
