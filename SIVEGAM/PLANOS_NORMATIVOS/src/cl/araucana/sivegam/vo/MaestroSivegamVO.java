package cl.araucana.sivegam.vo;

import java.util.Date;

public class MaestroSivegamVO {

    /* variables de la clase */
    private long maestro_sivegam;
    private String fecha_proceso;
    private Date fechaProcesoDate;
    private int periodo_proceso;
    private int status_proceso;
    private int tipo_archivo;
    private long usuario_sivegam;

    /** Para obtencion de glosas. */
    private String glosaPeriodoProcesoMes;
    private String glosaStatusProceso;

    /* Generacion de Get and Set */
    public String getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(String fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public Date getFechaProcesoDate() {
        return fechaProcesoDate;
    }

    public void setFechaProcesoDate(Date fechaProcesoDate) {
        this.fechaProcesoDate = fechaProcesoDate;
    }

    public long getMaestro_sivegam() {
        return maestro_sivegam;
    }

    public void setMaestro_sivegam(long maestro_sivegam) {
        this.maestro_sivegam = maestro_sivegam;
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

    public int getTipo_archivo() {
        return tipo_archivo;
    }

    public void setTipo_archivo(int tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
    }

    public long getUsuario_sivegam() {
        return usuario_sivegam;
    }

    public void setUsuario_sivegam(long usuario_sivegam) {
        this.usuario_sivegam = usuario_sivegam;
    }

    public String getGlosaPeriodoProcesoMes() {
        return glosaPeriodoProcesoMes;
    }

    public void setGlosaPeriodoProcesoMes(String glosaPeriodoProcesoMes) {
        this.glosaPeriodoProcesoMes = glosaPeriodoProcesoMes;
    }

    public String getGlosaStatusProceso() {
        return glosaStatusProceso;
    }

    public void setGlosaStatusProceso(String glosaStatusProceso) {
        this.glosaStatusProceso = glosaStatusProceso;
    }

}
