package cl.araucana.sivegam.vo;

public class LinSif014VO {

    /* variables de la clase */
    private long id_sif014;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private int fecha_proceso;
    private int codigo_entidad;
    private int codigo_archivo;
    private long rut_empresa;
    private String dv_empresa;
    private String nombre_empresa;
    private long rut_beneficiario;
    private String dv_beneficiario;
    private String nombre_beneficiario;
    private int tipo_beneficio;
    private int tipo_beneficiario;
    private long rut_causante;
    private String dv_causante;
    private String nombre_causante;
    private int tipo_causante;
    private long inicio_period_reinte;
    private long final_period_reinte;
    private int tipo_reintegro;
    private long monto_total_reintegro;
    private long monto_reintegro_mes;
    private long monto_total_deuda;
    private String referencia_origen;
    private int fuente_origen;

    /* Variables para manejo de fechas */
    private String inicioPeriodoReintegroDate;
    private String finalPeriodoReintegro;

    /* Variables para manejo de montos. */
    private String montoTotalReintegro;
    private String montoReintegroMes;
    private String montoTotalDeuda;

    /* generacion de get and set */
    public int getCodigo_archivo() {
        return codigo_archivo;
    }

    public void setCodigo_archivo(int codigo_archivo) {
        this.codigo_archivo = codigo_archivo;
    }

    public int getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(int codigo_entidad) {
        this.codigo_entidad = codigo_entidad;
    }

    public String getDv_beneficiario() {
        return dv_beneficiario;
    }

    public void setDv_beneficiario(String dv_beneficiario) {
        this.dv_beneficiario = dv_beneficiario;
    }

    public String getDv_causante() {
        return dv_causante;
    }

    public void setDv_causante(String dv_causante) {
        this.dv_causante = dv_causante;
    }

    public String getDv_empresa() {
        return dv_empresa;
    }

    public void setDv_empresa(String dv_empresa) {
        this.dv_empresa = dv_empresa;
    }

    public int getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(int fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public long getFinal_period_reinte() {
        return final_period_reinte;
    }

    public void setFinal_period_reinte(long final_period_reinte) {
        this.final_period_reinte = final_period_reinte;
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

    public int getFuente_origen() {
        return fuente_origen;
    }

    public void setFuente_origen(int fuente_origen) {
        this.fuente_origen = fuente_origen;
    }

    public long getId_maestro_sivegam() {
        return id_maestro_sivegam;
    }

    public void setId_maestro_sivegam(long id_maestro_sivegam) {
        this.id_maestro_sivegam = id_maestro_sivegam;
    }

    public long getId_sif014() {
        return id_sif014;
    }

    public void setId_sif014(long id_sif014) {
        this.id_sif014 = id_sif014;
    }

    public long getInicio_period_reinte() {
        return inicio_period_reinte;
    }

    public void setInicio_period_reinte(long inicio_period_reinte) {
        this.inicio_period_reinte = inicio_period_reinte;
    }

    public long getMonto_reintegro_mes() {
        return monto_reintegro_mes;
    }

    public void setMonto_reintegro_mes(long monto_reintegro_mes) {
        this.monto_reintegro_mes = monto_reintegro_mes;
    }

    public long getMonto_total_deuda() {
        return monto_total_deuda;
    }

    public void setMonto_total_deuda(long monto_total_deuda) {
        this.monto_total_deuda = monto_total_deuda;
    }

    public long getMonto_total_reintegro() {
        return monto_total_reintegro;
    }

    public void setMonto_total_reintegro(long monto_total_reintegro) {
        this.monto_total_reintegro = monto_total_reintegro;
    }

    public String getNombre_beneficiario() {
        return nombre_beneficiario;
    }

    public void setNombre_beneficiario(String nombre_beneficiario) {
        this.nombre_beneficiario = nombre_beneficiario;
    }

    public String getNombre_causante() {
        return nombre_causante;
    }

    public void setNombre_causante(String nombre_causante) {
        this.nombre_causante = nombre_causante;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getReferencia_origen() {
        return referencia_origen;
    }

    public void setReferencia_origen(String referencia_origen) {
        this.referencia_origen = referencia_origen;
    }

    public long getRut_beneficiario() {
        return rut_beneficiario;
    }

    public void setRut_beneficiario(long rut_beneficiario) {
        this.rut_beneficiario = rut_beneficiario;
    }

    public long getRut_causante() {
        return rut_causante;
    }

    public void setRut_causante(long rut_causante) {
        this.rut_causante = rut_causante;
    }

    public long getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(long rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    public int getTipo_beneficiario() {
        return tipo_beneficiario;
    }

    public void setTipo_beneficiario(int tipo_beneficiario) {
        this.tipo_beneficiario = tipo_beneficiario;
    }

    public int getTipo_beneficio() {
        return tipo_beneficio;
    }

    public void setTipo_beneficio(int tipo_beneficio) {
        this.tipo_beneficio = tipo_beneficio;
    }

    public int getTipo_causante() {
        return tipo_causante;
    }

    public void setTipo_causante(int tipo_causante) {
        this.tipo_causante = tipo_causante;
    }

    public int getTipo_reintegro() {
        return tipo_reintegro;
    }

    public void setTipo_reintegro(int tipo_reintegro) {
        this.tipo_reintegro = tipo_reintegro;
    }

    public String getFinalPeriodoReintegro() {
        return finalPeriodoReintegro;
    }

    public void setFinalPeriodoReintegro(String finalPeriodoReintegro) {
        this.finalPeriodoReintegro = finalPeriodoReintegro;
    }

    public String getInicioPeriodoReintegroDate() {
        return inicioPeriodoReintegroDate;
    }

    public void setInicioPeriodoReintegroDate(String inicioPeriodoReintegroDate) {
        this.inicioPeriodoReintegroDate = inicioPeriodoReintegroDate;
    }

    public String getMontoReintegroMes() {
        return montoReintegroMes;
    }

    public void setMontoReintegroMes(String montoReintegroMes) {
        this.montoReintegroMes = montoReintegroMes;
    }

    public String getMontoTotalDeuda() {
        return montoTotalDeuda;
    }

    public void setMontoTotalDeuda(String montoTotalDeuda) {
        this.montoTotalDeuda = montoTotalDeuda;
    }

    public String getMontoTotalReintegro() {
        return montoTotalReintegro;
    }

    public void setMontoTotalReintegro(String montoTotalReintegro) {
        this.montoTotalReintegro = montoTotalReintegro;
    }

}
