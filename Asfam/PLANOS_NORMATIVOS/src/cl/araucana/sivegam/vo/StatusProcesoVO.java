package cl.araucana.sivegam.vo;

public class StatusProcesoVO {

    /* variables de la clase */
    private long status_proceso;
    private String descripcion_status_proceso;

    /* generacion de get and set */
    public String getDescripcion_status_proceso() {
        return descripcion_status_proceso;
    }

    public void setDescripcion_status_proceso(String descripcion_status_proceso) {
        this.descripcion_status_proceso = descripcion_status_proceso;
    }

    public long getStatus_proceso() {
        return status_proceso;
    }

    public void setStatus_proceso(long status_proceso) {
        this.status_proceso = status_proceso;
    }

}
