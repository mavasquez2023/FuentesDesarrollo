package cl.araucana.sivegam.vo;

public class PeriodoProcesoVO {

    /* variables de la clase */
    private long periodo_proceso;
    private String descripcion_periodo_proceso;

    /* generacion de get and set */
    public String getDescripcion_periodo_proceso() {
        return descripcion_periodo_proceso;
    }

    public void setDescripcion_periodo_proceso(String descripcion_periodo_proceso) {
        this.descripcion_periodo_proceso = descripcion_periodo_proceso;
    }

    public long getPeriodo_proceso() {
        return periodo_proceso;
    }

    public void setPeriodo_proceso(long periodo_proceso) {
        this.periodo_proceso = periodo_proceso;
    }

}
