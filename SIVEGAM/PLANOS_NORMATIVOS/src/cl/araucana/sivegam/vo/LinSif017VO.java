package cl.araucana.sivegam.vo;

public class LinSif017VO {

    /* variables de la clase */
    private long id_sif017;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private int fecha_proceso;
    private int codigo_entidad;
    private int codigo_archivo;
    private long rut_empresa;
    private String dv_empresa;
    private String nombre_empresa;
    private int cod_tipo_egreso;
    private int modalidad_de_pago;
    private long monto_documento;
    private String numero_serie;
    private String numero_documento;
    private long fech_emision_doc;
    private int codigo_banco;
    private String numero_cartola;
    private int estado_documento;
    private long fecha_rendicion;

    /* variables auxiliares para manejo de fechas */
    private String fechaProcesoDate;
    private String fechaEmisionDocumentoDate;
    private String fechaRendicionDate;

    /* Para manipulacion de datos numericos con separacion de puntos. */
    private String montoDocumentoEnMiles;

    /* generacion de get and set */

    public String getMontoDocumentoEnMiles() {
        return montoDocumentoEnMiles;
    }

    public void setMontoDocumentoEnMiles(String montoDocumentoEnMiles) {
        this.montoDocumentoEnMiles = montoDocumentoEnMiles;
    }

    public String getFechaEmisionDocumentoDate() {
        return fechaEmisionDocumentoDate;
    }

    public void setFechaEmisionDocumentoDate(String fechaEmisionDocumentoDate) {
        this.fechaEmisionDocumentoDate = fechaEmisionDocumentoDate;
    }

    public String getFechaProcesoDate() {
        return fechaProcesoDate;
    }

    public void setFechaProcesoDate(String fechaProcesoDate) {
        this.fechaProcesoDate = fechaProcesoDate;
    }

    public String getFechaRendicionDate() {
        return fechaRendicionDate;
    }

    public void setFechaRendicionDate(String fechaRendicionDate) {
        this.fechaRendicionDate = fechaRendicionDate;
    }

    public int getCod_tipo_egreso() {
        return cod_tipo_egreso;
    }

    public void setCod_tipo_egreso(int cod_tipo_egreso) {
        this.cod_tipo_egreso = cod_tipo_egreso;
    }

    public int getCodigo_archivo() {
        return codigo_archivo;
    }

    public void setCodigo_archivo(int codigo_archivo) {
        this.codigo_archivo = codigo_archivo;
    }

    public int getCodigo_banco() {
        return codigo_banco;
    }

    public void setCodigo_banco(int codigo_banco) {
        this.codigo_banco = codigo_banco;
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

    public int getEstado_documento() {
        return estado_documento;
    }

    public void setEstado_documento(int estado_documento) {
        this.estado_documento = estado_documento;
    }

    public long getFech_emision_doc() {
        return fech_emision_doc;
    }

    public void setFech_emision_doc(long fech_emision_doc) {
        this.fech_emision_doc = fech_emision_doc;
    }

    public int getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(int fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public long getFecha_rendicion() {
        return fecha_rendicion;
    }

    public void setFecha_rendicion(long fecha_rendicion) {
        this.fecha_rendicion = fecha_rendicion;
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

    public long getId_sif017() {
        return id_sif017;
    }

    public void setId_sif017(long id_sif017) {
        this.id_sif017 = id_sif017;
    }

    public int getModalidad_de_pago() {
        return modalidad_de_pago;
    }

    public void setModalidad_de_pago(int modalidad_de_pago) {
        this.modalidad_de_pago = modalidad_de_pago;
    }

    public long getMonto_documento() {
        return monto_documento;
    }

    public void setMonto_documento(long monto_documento) {
        this.monto_documento = monto_documento;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getNumero_cartola() {
        return numero_cartola;
    }

    public void setNumero_cartola(String numero_cartola) {
        this.numero_cartola = numero_cartola;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public long getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(long rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

}
