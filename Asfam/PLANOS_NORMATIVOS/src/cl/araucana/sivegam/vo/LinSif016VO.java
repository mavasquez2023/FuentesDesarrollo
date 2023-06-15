package cl.araucana.sivegam.vo;

public class LinSif016VO {

    /* variables de la clase */
    private long id_sif016;
    private long id_maestro_sivegam;
    private int flag_reg_modificado;
    private int flag_reg_eliminado;
    private int fecha_proceso;
    private int codigo_entidad;
    private int codigo_archivo;
    private int mes_recaudacion;
    private int mes_remuneracion;
    private int cod_tipo_declaracion;
    private String numero_declaracion;
    private long fech_declaracion;
    private long rut_empresa;
    private String dv_empresa;
    private String nombre_empresa;
    private int num_total_trabajador;
    private int num_total_cargas;
    private int cargas_retroactivas;
    private long mto_asfam_mes;
    private long mto_asfam_mes_retro;
    private long mto_reintegros_mes;
    private long total_pago_asigfam;
    private long total_de_cotizacion;
    private long otros_descuentos;
    private long resultado_neto;
    private int tipo_de_saldo;
    private int modalidad_pago;
    private long monto_documento;
    private String numero_serie;
    private String numero_documento;
    private long fech_emision_doc;
    private int codigo_banco;
    private long saldo_caja_planilla;
    private long campo_contingencia;
    private int fuente_de_origen;

    /* Campos de montos para separacion de miles. */
    private String montoAsfamMesMiles;
    private String montoAsfamMesRetroMiles;
    private String montoReintegroMesMiles;
    private String totalPagoAsigFamMiles;
    private String totalCotizacionesMiles;
    private String otrosDescuentosMiles;
    private String resultadoNetoMiles;

    /* Para manipulacion de datos numericos con separacion de puntos. */
    private String montoDocumentoEnMiles;
    
    /* Variables para manejo de fechas */
    private String fechaEmision;
    private String fechaDeclaracion;
    

    /* generacion de get and set */

    public String getMontoDocumentoEnMiles() {
        return montoDocumentoEnMiles;
    }

    public void setMontoDocumentoEnMiles(String montoDocumentoEnMiles) {
        this.montoDocumentoEnMiles = montoDocumentoEnMiles;
    }

    public long getCampo_contingencia() {
        return campo_contingencia;
    }

    public void setCampo_contingencia(long campo_contingencia) {
        this.campo_contingencia = campo_contingencia;
    }

    public int getFuente_de_origen() {
        return fuente_de_origen;
    }

    public void setFuente_de_origen(int fuente_de_origen) {
        this.fuente_de_origen = fuente_de_origen;
    }

    public int getCargas_retroactivas() {
        return cargas_retroactivas;
    }

    public void setCargas_retroactivas(int cargas_retroactivas) {
        this.cargas_retroactivas = cargas_retroactivas;
    }

    public int getCod_tipo_declaracion() {
        return cod_tipo_declaracion;
    }

    public void setCod_tipo_declaracion(int cod_tipo_declaracion) {
        this.cod_tipo_declaracion = cod_tipo_declaracion;
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

    public long getFech_declaracion() {
        return fech_declaracion;
    }

    public void setFech_declaracion(long fech_declaracion) {
        this.fech_declaracion = fech_declaracion;
    }

    public long getFech_emision_doc() {
        return fech_emision_doc;
    }

    public void setFech_emision_doc(long fech_emision_doc) {
        if (fech_emision_doc != 0) {
            this.fech_emision_doc = fech_emision_doc;
        }
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

    public long getId_sif016() {
        return id_sif016;
    }

    public void setId_sif016(long id_sif016) {
        this.id_sif016 = id_sif016;
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

    public long getMonto_documento() {
        return monto_documento;
    }

    public void setMonto_documento(long monto_documento) {
        this.monto_documento = monto_documento;
    }

    public long getMto_asfam_mes() {
        return mto_asfam_mes;
    }

    public void setMto_asfam_mes(long mto_asfam_mes) {
        this.mto_asfam_mes = mto_asfam_mes;
    }

    public long getMto_asfam_mes_retro() {
        return mto_asfam_mes_retro;
    }

    public void setMto_asfam_mes_retro(long mto_asfam_mes_retro) {
        this.mto_asfam_mes_retro = mto_asfam_mes_retro;
    }

    public long getMto_reintegros_mes() {
        return mto_reintegros_mes;
    }

    public void setMto_reintegros_mes(long mto_reintegros_mes) {
        this.mto_reintegros_mes = mto_reintegros_mes;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public int getNum_total_cargas() {
        return num_total_cargas;
    }

    public void setNum_total_cargas(int num_total_cargas) {
        this.num_total_cargas = num_total_cargas;
    }

    public int getNum_total_trabajador() {
        return num_total_trabajador;
    }

    public void setNum_total_trabajador(int num_total_trabajador) {
        this.num_total_trabajador = num_total_trabajador;
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

    public long getOtros_descuentos() {
        return otros_descuentos;
    }

    public void setOtros_descuentos(long otros_descuentos) {
        this.otros_descuentos = otros_descuentos;
    }

    public long getResultado_neto() {
        return resultado_neto;
    }

    public void setResultado_neto(long resultado_neto) {
        this.resultado_neto = resultado_neto;
    }

    public long getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(long rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    public long getSaldo_caja_planilla() {
        return saldo_caja_planilla;
    }

    public void setSaldo_caja_planilla(long saldo_caja_planilla) {
        this.saldo_caja_planilla = saldo_caja_planilla;
    }

    public int getTipo_de_saldo() {
        return tipo_de_saldo;
    }

    public void setTipo_de_saldo(int tipo_de_saldo) {
        this.tipo_de_saldo = tipo_de_saldo;
    }

    public long getTotal_de_cotizacion() {
        return total_de_cotizacion;
    }

    public void setTotal_de_cotizacion(long total_de_cotizacion) {
        this.total_de_cotizacion = total_de_cotizacion;
    }

    public long getTotal_pago_asigfam() {
        return total_pago_asigfam;
    }

    public void setTotal_pago_asigfam(long total_pago_asigfam) {
        this.total_pago_asigfam = total_pago_asigfam;
    }

    public String getMontoAsfamMesMiles() {
        return montoAsfamMesMiles;
    }

    public void setMontoAsfamMesMiles(String montoAsfamMesMiles) {
        this.montoAsfamMesMiles = montoAsfamMesMiles;
    }

    public String getMontoAsfamMesRetroMiles() {
        return montoAsfamMesRetroMiles;
    }

    public void setMontoAsfamMesRetroMiles(String montoAsfamMesRetroMiles) {
        this.montoAsfamMesRetroMiles = montoAsfamMesRetroMiles;
    }

    public String getMontoReintegroMesMiles() {
        return montoReintegroMesMiles;
    }

    public void setMontoReintegroMesMiles(String montoReintegroMesMiles) {
        this.montoReintegroMesMiles = montoReintegroMesMiles;
    }

    public String getOtrosDescuentosMiles() {
        return otrosDescuentosMiles;
    }

    public void setOtrosDescuentosMiles(String otrosDescuentosMiles) {
        this.otrosDescuentosMiles = otrosDescuentosMiles;
    }

    public String getResultadoNetoMiles() {
        return resultadoNetoMiles;
    }

    public void setResultadoNetoMiles(String resultadoNetoMiles) {
        this.resultadoNetoMiles = resultadoNetoMiles;
    }

    public String getTotalCotizacionesMiles() {
        return totalCotizacionesMiles;
    }

    public void setTotalCotizacionesMiles(String totalCotizacionesMiles) {
        this.totalCotizacionesMiles = totalCotizacionesMiles;
    }

    public String getTotalPagoAsigFamMiles() {
        return totalPagoAsigFamMiles;
    }

    public void setTotalPagoAsigFamMiles(String totalPagoAsigFamMiles) {
        this.totalPagoAsigFamMiles = totalPagoAsigFamMiles;
    }

    public String getFechaDeclaracion() {
        return fechaDeclaracion;
    }

    public void setFechaDeclaracion(String fechaDeclaracion) {
        this.fechaDeclaracion = fechaDeclaracion;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }


}
