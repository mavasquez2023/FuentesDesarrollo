package cl.araucana.sivegam.vo;

public class LinSif019VO {

    /* variables de la clase */
    private long id_sif019;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private int fecha_proceso;
    private int codigo_entidad;
    private int codigo_archivo;
    private long rut_empresa;
    private String dv_empresa;
    private String nombre_empresa;
    private long mes_origen_gasto;
    private int estado_doc_orig;
    private int modo_pago_orig;
    private String num_serie_orig;
    private long num_docum_orig;
    private long monto_docum_orig;
    private long fecha_emision_orig;
    private int codigo_banco_orig;
    private int modo_pago_nuevo;
    private String num_serie_nuevo;
    private long num_docum_nuevo;
    private long monto_docum_nuevo;
    private long fecha_emision_nuevo;
    private int codigo_banco_nuevo;

    /* para manejo de fechas */
    private String fechaEmisionOrigenDate;
    private String fechaEmisionNuevoDate;
    private String mesOrigenGastoDate;

    /* generacion de get and set */
    public int getCodigo_archivo() {
        return codigo_archivo;
    }

    public void setCodigo_archivo(int codigo_archivo) {
        this.codigo_archivo = codigo_archivo;
    }

    public int getCodigo_banco_nuevo() {
        return codigo_banco_nuevo;
    }

    public void setCodigo_banco_nuevo(int codigo_banco_nuevo) {
        this.codigo_banco_nuevo = codigo_banco_nuevo;
    }

    public int getCodigo_banco_orig() {
        return codigo_banco_orig;
    }

    public void setCodigo_banco_orig(int codigo_banco_orig) {
        this.codigo_banco_orig = codigo_banco_orig;
    }

    public int getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(int codigo_entidad) {
        this.codigo_entidad = codigo_entidad;
    }

    public String getDv_empresa() {
        return dv_empresa;
    }

    public void setDv_empresa(String dv_empresa) {
        this.dv_empresa = dv_empresa;
    }

    public int getEstado_doc_orig() {
        return estado_doc_orig;
    }

    public void setEstado_doc_orig(int estado_doc_orig) {
        this.estado_doc_orig = estado_doc_orig;
    }

    public long getFecha_emision_nuevo() {
        return fecha_emision_nuevo;
    }

    public void setFecha_emision_nuevo(long fecha_emision_nuevo) {
        this.fecha_emision_nuevo = fecha_emision_nuevo;
    }

    public long getFecha_emision_orig() {
        return fecha_emision_orig;
    }

    public void setFecha_emision_orig(long fecha_emision_orig) {
        this.fecha_emision_orig = fecha_emision_orig;
    }

    public int getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(int fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public int getFlag_reg_eliminado() {
        return flag_reg_eliminado;
    }

    public void setFlag_reg_eliminado(int flag_reg_eliminado) {
        this.flag_reg_eliminado = flag_reg_eliminado;
    }

    public int getFlag_reg_modificado() {
        return flag_reg_modificado;
    }

    public void setFlag_reg_modificado(int flag_reg_modificado) {
        this.flag_reg_modificado = flag_reg_modificado;
    }

    public long getId_maestro_sivegam() {
        return id_maestro_sivegam;
    }

    public void setId_maestro_sivegam(long id_maestro_sivegam) {
        this.id_maestro_sivegam = id_maestro_sivegam;
    }

    public long getId_sif019() {
        return id_sif019;
    }

    public void setId_sif019(long id_sif019) {
        this.id_sif019 = id_sif019;
    }

    public long getMes_origen_gasto() {
        return mes_origen_gasto;
    }

    public void setMes_origen_gasto(long mes_origen_gasto) {
        this.mes_origen_gasto = mes_origen_gasto;
    }

    public int getModo_pago_nuevo() {
        return modo_pago_nuevo;
    }

    public void setModo_pago_nuevo(int modo_pago_nuevo) {
        this.modo_pago_nuevo = modo_pago_nuevo;
    }

    public int getModo_pago_orig() {
        return modo_pago_orig;
    }

    public void setModo_pago_orig(int modo_pago_orig) {
        this.modo_pago_orig = modo_pago_orig;
    }

    public long getMonto_docum_nuevo() {
        return monto_docum_nuevo;
    }

    public void setMonto_docum_nuevo(long monto_docum_nuevo) {
        this.monto_docum_nuevo = monto_docum_nuevo;
    }

    public long getMonto_docum_orig() {
        return monto_docum_orig;
    }

    public void setMonto_docum_orig(long monto_docum_orig) {
        this.monto_docum_orig = monto_docum_orig;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public long getNum_docum_nuevo() {
        return num_docum_nuevo;
    }

    public void setNum_docum_nuevo(long num_docum_nuevo) {
        this.num_docum_nuevo = num_docum_nuevo;
    }

    public long getNum_docum_orig() {
        return num_docum_orig;
    }

    public void setNum_docum_orig(long num_docum_orig) {
        this.num_docum_orig = num_docum_orig;
    }

    public String getNum_serie_nuevo() {
        return num_serie_nuevo;
    }

    public void setNum_serie_nuevo(String num_serie_nuevo) {
        this.num_serie_nuevo = num_serie_nuevo;
    }

    public String getNum_serie_orig() {
        return num_serie_orig;
    }

    public void setNum_serie_orig(String num_serie_orig) {
        this.num_serie_orig = num_serie_orig;
    }

    public long getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(long rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    public String getFechaEmisionNuevoDate() {
        return fechaEmisionNuevoDate;
    }

    public void setFechaEmisionNuevoDate(String fechaEmisionNuevoDate) {
        this.fechaEmisionNuevoDate = fechaEmisionNuevoDate;
    }

    public String getFechaEmisionOrigenDate() {
        return fechaEmisionOrigenDate;
    }

    public void setFechaEmisionOrigenDate(String fechaEmisionOrigenDate) {
        this.fechaEmisionOrigenDate = fechaEmisionOrigenDate;
    }

    public String getMesOrigenGastoDate() {
        return mesOrigenGastoDate;
    }

    public void setMesOrigenGastoDate(String mesOrigenGastoDate) {
        this.mesOrigenGastoDate = mesOrigenGastoDate;
    }

}
