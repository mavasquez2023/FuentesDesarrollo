package cl.araucana.sivegam.vo;

public class LinSif011VO {

    /* variables de la clase. */
    private long id_sif011;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private long fecha_proceso;
    private long codigo_entidad;
    private int codigo_archivo;
    private long mes_cotizaciones;
    private long mes_remuneracion;
    private int tipo_declaracion;
    private String codigo_barra;
    private long rut_empresa;
    private String dv_empresa;
    private String nombre_empresa;
    private long rut_afiliado;
    private String dv_afiliado;
    private String nombre_afiliado;
    private int cod_tipo_beneficio;
    private int tipo_beneficiario;
    private long rut_causante;
    private String dv_causante;
    private String nombre_causante;
    private int cod_tipo_causante;
    private long fecha_inicio_benef;
    private long fecha_termino_benef;
    private int dias_asfam;
    private int codigo_tramo;
    private long monto_beneficio;
    private int tipo_emision;
    private int cod_tipo_egreso;
    private int modalidad_pago;
    private long monto_documento;
    private String numero_serie;
    private String numero_documento;
    private long fecha_emision_documento;
    private long codigo_banco;
    private int fuente_origen;

    /* variables para administrar los datos de fechas. */
    private String fechaInicioBeneficioDate;
    private String fechaTerminoBeneficioDate;

    /*---nuevo ------*/
    private String fechaEmisionDocumentoDate;

    /* Variable que almacena montos separados en miles */
    private String montoBeneficioMiles;

    /* generacion de get and set */
    public int getCod_tipo_beneficio() {
        return cod_tipo_beneficio;
    }

    public void setCod_tipo_beneficio(int cod_tipo_beneficio) {
        this.cod_tipo_beneficio = cod_tipo_beneficio;
    }

    public int getCod_tipo_causante() {
        return cod_tipo_causante;
    }

    public void setCod_tipo_causante(int cod_tipo_causante) {
        this.cod_tipo_causante = cod_tipo_causante;
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

    public long getCodigo_banco() {
        return codigo_banco;
    }

    public void setCodigo_banco(long codigo_banco) {
        this.codigo_banco = codigo_banco;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public long getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(long codigo_entidad) {
        this.codigo_entidad = codigo_entidad;
    }

    public int getCodigo_tramo() {
        return codigo_tramo;
    }

    public void setCodigo_tramo(int codigo_tramo) {
        this.codigo_tramo = codigo_tramo;
    }

    public int getDias_asfam() {
        return dias_asfam;
    }

    public void setDias_asfam(int dias_asfam) {
        this.dias_asfam = dias_asfam;
    }

    public String getDv_afiliado() {
        return dv_afiliado;
    }

    public void setDv_afiliado(String dv_afiliado) {
        this.dv_afiliado = dv_afiliado;
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

    public long getFecha_emision_documento() {
        return fecha_emision_documento;
    }

    public void setFecha_emision_documento(long fecha_emision_documento) {
        if (fecha_emision_documento != 0) {
            this.fecha_emision_documento = fecha_emision_documento;
        }
        this.fecha_emision_documento = fecha_emision_documento;
    }

    public long getFecha_inicio_benef() {
        return fecha_inicio_benef;
    }

    public void setFecha_inicio_benef(long fecha_inicio_benef) {
        this.fecha_inicio_benef = fecha_inicio_benef;
    }

    public long getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(long fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public long getFecha_termino_benef() {
        return fecha_termino_benef;
    }

    public void setFecha_termino_benef(long fecha_termino_benef) {
        this.fecha_termino_benef = fecha_termino_benef;
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

    public long getId_sif011() {
        return id_sif011;
    }

    public void setId_sif011(long id_sif011) {
        this.id_sif011 = id_sif011;
    }

    public long getMes_cotizaciones() {
        return mes_cotizaciones;
    }

    public void setMes_cotizaciones(long mes_cotizaciones) {
        this.mes_cotizaciones = mes_cotizaciones;
    }

    public long getMes_remuneracion() {
        return mes_remuneracion;
    }

    public void setMes_remuneracion(long mes_remuneracion) {
        this.mes_remuneracion = mes_remuneracion;
    }

    public int getModalidad_pago() {
        return modalidad_pago;
    }

    public void setModalidad_pago(int modalidad_pago) {
        this.modalidad_pago = modalidad_pago;
    }

    public long getMonto_beneficio() {
        return monto_beneficio;
    }

    public void setMonto_beneficio(long monto_beneficio) {
        this.monto_beneficio = monto_beneficio;
    }

    public long getMonto_documento() {
        return monto_documento;
    }

    public void setMonto_documento(long monto_documento) {
        if (monto_documento != 0) {
            this.monto_documento = monto_documento;
        }
    }

    public String getNombre_afiliado() {
        return nombre_afiliado;
    }

    public void setNombre_afiliado(String nombre_afiliado) {
        this.nombre_afiliado = nombre_afiliado;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
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

    public long getRut_afiliado() {
        return rut_afiliado;
    }

    public void setRut_afiliado(long rut_afiliado) {
        this.rut_afiliado = rut_afiliado;
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

    public int getTipo_declaracion() {
        return tipo_declaracion;
    }

    public void setTipo_declaracion(int tipo_declaracion) {
        this.tipo_declaracion = tipo_declaracion;
    }

    public int getTipo_emision() {
        return tipo_emision;
    }

    public void setTipo_emision(int tipo_emision) {
        this.tipo_emision = tipo_emision;
    }

    public String getNombre_causante() {
        return nombre_causante;
    }

    public void setNombre_causante(String nombre_causante) {
        this.nombre_causante = nombre_causante;
    }

    public String getFechaInicioBeneficioDate() {
        return fechaInicioBeneficioDate;
    }

    public void setFechaInicioBeneficioDate(String fechaInicioBeneficioDate) {
        this.fechaInicioBeneficioDate = fechaInicioBeneficioDate;
    }

    public String getFechaTerminoBeneficioDate() {
        return fechaTerminoBeneficioDate;
    }

    public void setFechaTerminoBeneficioDate(String fechaTerminoBeneficioDate) {
        this.fechaTerminoBeneficioDate = fechaTerminoBeneficioDate;
    }

    public String getMontoBeneficioMiles() {
        return montoBeneficioMiles;
    }

    public void setMontoBeneficioMiles(String montoBeneficioMiles) {
        this.montoBeneficioMiles = montoBeneficioMiles;
    }

    public String getFechaEmisionDocumentoDate() {
        return fechaEmisionDocumentoDate;
    }

    public void setFechaEmisionDocumentoDate(String fechaEmisionDocumentoDate) {
        this.fechaEmisionDocumentoDate = fechaEmisionDocumentoDate;
    }
}
