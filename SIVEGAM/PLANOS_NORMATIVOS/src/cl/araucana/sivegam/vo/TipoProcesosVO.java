package cl.araucana.sivegam.vo;

public class TipoProcesosVO {

    /* variables de la clase */
    private int id_tipo_proceso;
    private String codigo_tipo_proceso;
    private String descripcion_tipo_proceso;
    private String desc_estruct_carpetas_as400;
    private String desc_estruct_carpetas_reporte;
    private String desc_carpeta_xls;
    private String desc_carpeta_txt;
    private String desc_nombre_archivo_xls;
    private int id_tipo_archivo;
    private int id_tipo_reporte;

    /* generacion de get and set */
    public String getCodigo_tipo_proceso() {
        return codigo_tipo_proceso;
    }

    public void setCodigo_tipo_proceso(String codigo_tipo_proceso) {
        this.codigo_tipo_proceso = codigo_tipo_proceso;
    }

    public String getDesc_estruct_carpetas_as400() {
        return desc_estruct_carpetas_as400;
    }

    public void setDesc_estruct_carpetas_as400(String desc_estruct_carpetas_as400) {
        this.desc_estruct_carpetas_as400 = desc_estruct_carpetas_as400;
    }

    public String getDesc_estruct_carpetas_reporte() {
        return desc_estruct_carpetas_reporte;
    }

    public void setDesc_estruct_carpetas_reporte(String desc_estruct_carpetas_reporte) {
        this.desc_estruct_carpetas_reporte = desc_estruct_carpetas_reporte;
    }

    public String getDescripcion_tipo_proceso() {
        return descripcion_tipo_proceso;
    }

    public void setDescripcion_tipo_proceso(String descripcion_tipo_proceso) {
        this.descripcion_tipo_proceso = descripcion_tipo_proceso;
    }

    public int getId_tipo_proceso() {
        return id_tipo_proceso;
    }

    public void setId_tipo_proceso(int id_tipo_proceso) {
        this.id_tipo_proceso = id_tipo_proceso;
    }

    public int getId_tipo_reporte() {
        return id_tipo_reporte;
    }

    public void setId_tipo_reporte(int id_tipo_reporte) {
        this.id_tipo_reporte = id_tipo_reporte;
    }

    public String getDesc_nombre_archivo_xls() {
        return desc_nombre_archivo_xls;
    }

    public void setDesc_nombre_archivo_xls(String desc_nombre_archivo_xls) {
        this.desc_nombre_archivo_xls = desc_nombre_archivo_xls;
    }

    public String getDesc_carpeta_txt() {
        return desc_carpeta_txt;
    }

    public void setDesc_carpeta_txt(String desc_carpeta_txt) {
        this.desc_carpeta_txt = desc_carpeta_txt;
    }

    public String getDesc_carpeta_xls() {
        return desc_carpeta_xls;
    }

    public void setDesc_carpeta_xls(String desc_carpeta_xls) {
        this.desc_carpeta_xls = desc_carpeta_xls;
    }

    public int getId_tipo_archivo() {
        return id_tipo_archivo;
    }

    public void setId_tipo_archivo(int id_tipo_archivo) {
        this.id_tipo_archivo = id_tipo_archivo;
    }
}
