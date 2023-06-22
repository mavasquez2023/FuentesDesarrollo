package cl.araucana.sivegam.vo;

public class LinSif012VO {

    /* variables de la clase. */
    private long id_sif012;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private int fecha_proceso;
    private int codigo_entidad;
    private int codigo_archivo;
    private int mes_recaudacion;
    private int mes_remuneracion;
    private int tipo_declaracion;
    private String numero_declaracion;
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
    private int causal_reliquidacion;
    private int tipo_emision;
    private int cod_tipo_egreso;
    private long monto_documento;
    private int modalidad_pago;
    private long fech_emision_doc;
    private String numero_serie;
    private String numero_documento;
    private long codigo_banco;
    private String referncia_origen;
    private int fuente_origen;

    /** Variables para manejo de fechas */
    private String fechaInicioBeneficioDate;
    private String fechaTerminoBeneficioDate;

    /*--- nuevo ---*/
    private String fechaEmisionDocumentoDate;

    /* variable para almancenar montos separados en miles */
    private String montoBeneficioMiles;

    /* Generacion de get and set. */
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

    public int getCausal_reliquidacion() {
        return causal_reliquidacion;
    }

    public void setCausal_reliquidacion(int causal_reliquidacion) {
        this.causal_reliquidacion = causal_reliquidacion;
    }

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

    public int getCodigo_entidad() {
        return codigo_entidad;
    }

    public void setCodigo_entidad(int codigo_entidad) {
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

    public long getFech_emision_doc() {
        return fech_emision_doc;
    }

    public void setFech_emision_doc(long fech_emision_doc) {
        if (fech_emision_doc != 0) {
            this.fech_emision_doc = fech_emision_doc;
        }
    }

    public long getFecha_inicio_benef() {
        return fecha_inicio_benef;
    }

    public void setFecha_inicio_benef(long fecha_inicio_benef) {
        this.fecha_inicio_benef = fecha_inicio_benef;
    }

    public int getFecha_proceso() {
        return fecha_proceso;
    }

    public void setFecha_proceso(int fecha_proceso) {
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

    public long getId_sif012() {
        return id_sif012;
    }

    public void setId_sif012(long id_sif012) {
        this.id_sif012 = id_sif012;
    }

    public int getMes_recaudacion() {
        return mes_recaudacion;
    }

    public void setMes_recaudacion(int mes_recaudacion) {
        this.mes_recaudacion = mes_recaudacion;
    }

    public int getMes_remuneracion() {
        return mes_remuneracion;
    }

    public void setMes_remuneracion(int mes_remuneracion) {
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

    public String getNumero_declaracion() {
        return numero_declaracion;
    }

    public void setNumero_declaracion(String numero_declaracion) {
        this.numero_declaracion = numero_declaracion;
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

    public String getReferncia_origen() {
        return referncia_origen;
    }

    public void setReferncia_origen(String referncia_origen) {
        this.referncia_origen = referncia_origen;
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
