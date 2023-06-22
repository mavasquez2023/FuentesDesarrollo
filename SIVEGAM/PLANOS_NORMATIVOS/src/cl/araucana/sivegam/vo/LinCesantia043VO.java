package cl.araucana.sivegam.vo;

public class LinCesantia043VO {

    /** Variables de la clase */
    private long id_sc043;
    private long id_maestro_sivegam;
    private int flag_modificacion;
    private int flag_eliminacion;
    private long mes_if;
    private int codigo_entidad;
    private int codigo_archivo;
    private long mes_emision;
    private long rut_beneficiario;
    private String dv_beneficiario;
    private String nombre_beneficiario;
    private int tipo_egreso;
    private int mod_pago;
    private String serie_documento;
    private String numero_documento;
    private long monto_subsidio_cesantia;
    private long monto_documento;
    private long fecha_emision_documento;
    private int codigo_banco;
    private long fecha_cambio_estado_documento;
    private String numero_cartola;

    /** Generacion de get and set */
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

    public String getDv_beneficiario() {
        return dv_beneficiario;
    }

    public void setDv_beneficiario(String dv_beneficiario) {
        this.dv_beneficiario = dv_beneficiario;
    }

    public long getFecha_cambio_estado_documento() {
        return fecha_cambio_estado_documento;
    }

    public void setFecha_cambio_estado_documento(long fecha_cambio_estado_documento) {
        this.fecha_cambio_estado_documento = fecha_cambio_estado_documento;
    }

    public long getFecha_emision_documento() {
        return fecha_emision_documento;
    }

    public void setFecha_emision_documento(long fecha_emision_documento) {
        this.fecha_emision_documento = fecha_emision_documento;
    }

    public int getFlag_eliminacion() {
        return flag_eliminacion;
    }

    public void setFlag_eliminacion(int flag_eliminacion) {
        this.flag_eliminacion = flag_eliminacion;
    }

    public int getFlag_modificacion() {
        return flag_modificacion;
    }

    public void setFlag_modificacion(int flag_modificacion) {
        this.flag_modificacion = flag_modificacion;
    }

    public long getId_maestro_sivegam() {
        return id_maestro_sivegam;
    }

    public void setId_maestro_sivegam(long id_maestro_sivegam) {
        this.id_maestro_sivegam = id_maestro_sivegam;
    }

    public long getId_sc043() {
        return id_sc043;
    }

    public void setId_sc043(long id_sc043) {
        this.id_sc043 = id_sc043;
    }

    public long getMes_emision() {
        return mes_emision;
    }

    public void setMes_emision(long mes_emision) {
        this.mes_emision = mes_emision;
    }

    public long getMes_if() {
        return mes_if;
    }

    public void setMes_if(long mes_if) {
        this.mes_if = mes_if;
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

    public long getMonto_subsidio_cesantia() {
        return monto_subsidio_cesantia;
    }

    public void setMonto_subsidio_cesantia(long monto_subsidio_cesantia) {
        this.monto_subsidio_cesantia = monto_subsidio_cesantia;
    }

    public String getNombre_beneficiario() {
        return nombre_beneficiario;
    }

    public void setNombre_beneficiario(String nombre_beneficiario) {
        this.nombre_beneficiario = nombre_beneficiario;
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

    public long getRut_beneficiario() {
        return rut_beneficiario;
    }

    public void setRut_beneficiario(long rut_beneficiario) {
        this.rut_beneficiario = rut_beneficiario;
    }

    public String getSerie_documento() {
        return serie_documento;
    }

    public void setSerie_documento(String serie_documento) {
        this.serie_documento = serie_documento;
    }

    public int getTipo_egreso() {
        return tipo_egreso;
    }

    public void setTipo_egreso(int tipo_egreso) {
        this.tipo_egreso = tipo_egreso;
    }

}
