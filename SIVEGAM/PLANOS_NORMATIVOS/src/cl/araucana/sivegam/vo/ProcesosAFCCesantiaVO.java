package cl.araucana.sivegam.vo;

public class ProcesosAFCCesantiaVO {

    /** Variables de la clase */
    private int id_proceso_afc_cesantia;
    private int id_tipo_proceso;
    private String codigo_tipo_proceso;
    private String descripcion_tipo_proceso;
    private String ruta_cl_as400;
    private String ruta_cl_as400_reprocesar;
    private String ruta_cl_as400_entrada;
    private String descripcion_jasper;
    private String descripcion_jasper_errores;
    private String ruta_xls_servidor;
    private String ruta_xls_errores_servidor;
    private String ruta_txt_servidor;
    private String nomenclatura_nombre_xls;
    private String nomenclatura_nombre_xls_err;
    private String nomenclatura_nombre_txt;

    /** generacion de get and set */
    public String getCodigo_tipo_proceso() {
        return codigo_tipo_proceso;
    }

    public void setCodigo_tipo_proceso(String codigo_tipo_proceso) {
        this.codigo_tipo_proceso = codigo_tipo_proceso;
    }

    public String getDescripcion_jasper() {
        return descripcion_jasper;
    }

    public void setDescripcion_jasper(String descripcion_jasper) {
        this.descripcion_jasper = descripcion_jasper;
    }

    public String getDescripcion_jasper_errores() {
        return descripcion_jasper_errores;
    }

    public void setDescripcion_jasper_errores(String descripcion_jasper_errores) {
        this.descripcion_jasper_errores = descripcion_jasper_errores;
    }

    public String getDescripcion_tipo_proceso() {
        return descripcion_tipo_proceso;
    }

    public void setDescripcion_tipo_proceso(String descripcion_tipo_proceso) {
        this.descripcion_tipo_proceso = descripcion_tipo_proceso;
    }

    public int getId_proceso_afc_cesantia() {
        return id_proceso_afc_cesantia;
    }

    public void setId_proceso_afc_cesantia(int id_proceso_afc_cesantia) {
        this.id_proceso_afc_cesantia = id_proceso_afc_cesantia;
    }

    public int getId_tipo_proceso() {
        return id_tipo_proceso;
    }

    public void setId_tipo_proceso(int id_tipo_proceso) {
        this.id_tipo_proceso = id_tipo_proceso;
    }

    public String getNomenclatura_nombre_txt() {
        return nomenclatura_nombre_txt;
    }

    public void setNomenclatura_nombre_txt(String nomenclatura_nombre_txt) {
        this.nomenclatura_nombre_txt = nomenclatura_nombre_txt;
    }

    public String getNomenclatura_nombre_xls() {
        return nomenclatura_nombre_xls;
    }

    public void setNomenclatura_nombre_xls(String nomenclatura_nombre_xls) {
        this.nomenclatura_nombre_xls = nomenclatura_nombre_xls;
    }

    public String getNomenclatura_nombre_xls_err() {
        return nomenclatura_nombre_xls_err;
    }

    public void setNomenclatura_nombre_xls_err(String nomenclatura_nombre_xls_err) {
        this.nomenclatura_nombre_xls_err = nomenclatura_nombre_xls_err;
    }

    public String getRuta_cl_as400() {
        return ruta_cl_as400;
    }

    public void setRuta_cl_as400(String ruta_cl_as400) {
        this.ruta_cl_as400 = ruta_cl_as400;
    }

    public String getRuta_cl_as400_entrada() {
        return ruta_cl_as400_entrada;
    }

    public void setRuta_cl_as400_entrada(String ruta_cl_as400_entrada) {
        this.ruta_cl_as400_entrada = ruta_cl_as400_entrada;
    }

    public String getRuta_cl_as400_reprocesar() {
        return ruta_cl_as400_reprocesar;
    }

    public void setRuta_cl_as400_reprocesar(String ruta_cl_as400_reprocesar) {
        this.ruta_cl_as400_reprocesar = ruta_cl_as400_reprocesar;
    }

    public String getRuta_txt_servidor() {
        return ruta_txt_servidor;
    }

    public void setRuta_txt_servidor(String ruta_txt_servidor) {
        this.ruta_txt_servidor = ruta_txt_servidor;
    }

    public String getRuta_xls_errores_servidor() {
        return ruta_xls_errores_servidor;
    }

    public void setRuta_xls_errores_servidor(String ruta_xls_errores_servidor) {
        this.ruta_xls_errores_servidor = ruta_xls_errores_servidor;
    }

    public String getRuta_xls_servidor() {
        return ruta_xls_servidor;
    }

    public void setRuta_xls_servidor(String ruta_xls_servidor) {
        this.ruta_xls_servidor = ruta_xls_servidor;
    }
}
