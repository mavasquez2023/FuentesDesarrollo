package cl.araucana.sivegam.vo;

public class TipoReporteVO {

    /* variables de la clase. */
    private int id_tipo_reporte;
    private String descripcion_tipo_reporte;

    /* generacion de get and set */
    public String getDescripcion_tipo_reporte() {
        return descripcion_tipo_reporte;
    }

    public void setDescripcion_tipo_reporte(String descripcion_tipo_reporte) {
        this.descripcion_tipo_reporte = descripcion_tipo_reporte;
    }

    public int getId_tipo_reporte() {
        return id_tipo_reporte;
    }

    public void setId_tipo_reporte(int id_tipo_reporte) {
        this.id_tipo_reporte = id_tipo_reporte;
    }

}
