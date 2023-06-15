package cl.araucana.sivegam.vo;

public class LinSif018VO {

    /* variables de la clase LinSif018VO. */
    public long id_sif018;
    public long id_maestro_sivegam;
    public int flag_reg_modificado;
    public int flag_reg_eliminado;
    public int fecha_proceso;
    public int codigo_entidad;
    public int codigo_archivo;
    public long rut_empleador;
    public String dv_empleador;
    public String nombre_empleador;
    public int mod_pago;
    public long monto_documento;
    public String numero_serie;
    public String numero_documento;
    public long fecha_emision_documento;
    public int codigo_banco;
    public long fecha_cobro;
    public String rut_modificado;
    public String fechaEmisionDocumentoMod;

    /* Manejo de fechas. */
    private String fechaEmisionDocumentoDate;
    private String fechaRendicionDate;

    /* manejo de separador de miles para cantidades */
    private String montoDocumentoMiles;

    /* Establecimiento de get and set. */
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

    public String getDv_empleador() {
        return dv_empleador;
    }

    public void setDv_empleador(String dv_empleador) {
        this.dv_empleador = dv_empleador;
    }

    public long getFecha_cobro() {
        return fecha_cobro;
    }

    public void setFecha_cobro(long fecha_cobro) {
        if (fecha_cobro != 0) {
            this.fecha_cobro = fecha_cobro;
        }
    }

    public long getFecha_emision_documento() {
        return fecha_emision_documento;
    }

    public void setFecha_emision_documento(long fecha_emision_documento) {
        this.fecha_emision_documento = fecha_emision_documento;
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

    public long getId_sif018() {
        return id_sif018;
    }

    public void setId_sif018(long id_sif018) {
        this.id_sif018 = id_sif018;
    }

    public int getMod_pago() {
        return mod_pago;
    }

    public void setMod_pago(int mod_pago) {
        this.mod_pago = mod_pago;
    }

    public long getMonto_documento() {
        return monto_documento;
    }

    public void setMonto_documento(long monto_documento) {
        this.monto_documento = monto_documento;
    }

    public String getNombre_empleador() {
        return nombre_empleador;
    }

    public void setNombre_empleador(String nombre_empleador) {
        this.nombre_empleador = nombre_empleador;
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

    public long getRut_empleador() {
        return rut_empleador;
    }

    public void setRut_empleador(long rut_empleador) {
        this.rut_empleador = rut_empleador;
    }

    public String getRut_modificado() {
        return rut_modificado;
    }

    public void setRut_modificado(String rut_modificado) {
        this.rut_modificado = rut_modificado;
    }

    public int getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(int fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public String getFechaEmisionDocumentoMod() {
        return fechaEmisionDocumentoMod;
    }

    public void setFechaEmisionDocumentoMod(String fechaEmisionDocumentoMod) {
        this.fechaEmisionDocumentoMod = fechaEmisionDocumentoMod;
    }

    public String getFechaEmisionDocumentoDate() {
        return fechaEmisionDocumentoDate;
    }

    public void setFechaEmisionDocumentoDate(String fechaEmisionDocumentoDate) {
        this.fechaEmisionDocumentoDate = fechaEmisionDocumentoDate;
    }

    public String getFechaRendicionDate() {
        return fechaRendicionDate;
    }

    public void setFechaRendicionDate(String fechaRendicionDate) {
        this.fechaRendicionDate = fechaRendicionDate;
    }

    public String getMontoDocumentoMiles() {
        return montoDocumentoMiles;
    }

    public void setMontoDocumentoMiles(String montoDocumentoMiles) {
        this.montoDocumentoMiles = montoDocumentoMiles;
    }

}
