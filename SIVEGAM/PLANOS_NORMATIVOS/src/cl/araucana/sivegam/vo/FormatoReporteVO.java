package cl.araucana.sivegam.vo;

public class FormatoReporteVO {

    /* variables de la clase */
    private long formato_reporte;
    private String descripcion_formato_reporte;

    /* Generacion de get and set */
    public String getDescripcion_formato_reporte() {
        return descripcion_formato_reporte;
    }

    public void setDescripcion_formato_reporte(String descripcion_formato_reporte) {
        this.descripcion_formato_reporte = descripcion_formato_reporte;
    }

    public long getFormato_reporte() {
        return formato_reporte;
    }

    public void setFormato_reporte(long formato_reporte) {
        this.formato_reporte = formato_reporte;
    }
}
