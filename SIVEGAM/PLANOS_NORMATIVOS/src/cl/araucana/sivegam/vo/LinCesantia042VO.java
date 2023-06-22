package cl.araucana.sivegam.vo;

public class LinCesantia042VO {

    /** Variables de la clase */
    private long id_sc042;
    private long id_maestro_sivegam;
    private int flag_modificacion;
    private int flag_eliminacion;
    private long mes_if;
    private int codigo_entidad;
    private int codigo_archivo;
    private int tipo_reintegro;
    private long rut_beneficiario;
    private String dv_beneficiario;
    private String nombre_beneficiario;
    private int comuna;
    private long monto_sub_cesantia_reintegrado;
    private long fecha_inicio_reintegro;
    private long fecha_termino_reintegro;

    /** Generacion de get and set */
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

    public int getComuna() {
        return comuna;
    }

    public void setComuna(int comuna) {
        this.comuna = comuna;
    }

    public String getDv_beneficiario() {
        return dv_beneficiario;
    }

    public void setDv_beneficiario(String dv_beneficiario) {
        this.dv_beneficiario = dv_beneficiario;
    }

    public long getFecha_inicio_reintegro() {
        return fecha_inicio_reintegro;
    }

    public void setFecha_inicio_reintegro(long fecha_inicio_reintegro) {
        this.fecha_inicio_reintegro = fecha_inicio_reintegro;
    }

    public long getFecha_termino_reintegro() {
        return fecha_termino_reintegro;
    }

    public void setFecha_termino_reintegro(long fecha_termino_reintegro) {
        this.fecha_termino_reintegro = fecha_termino_reintegro;
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

    public long getId_sc042() {
        return id_sc042;
    }

    public void setId_sc042(long id_sc042) {
        this.id_sc042 = id_sc042;
    }

    public long getMes_if() {
        return mes_if;
    }

    public void setMes_if(long mes_if) {
        this.mes_if = mes_if;
    }

    public long getMonto_sub_cesantia_reintegrado() {
        return monto_sub_cesantia_reintegrado;
    }

    public void setMonto_sub_cesantia_reintegrado(long monto_sub_cesantia_reintegrado) {
        this.monto_sub_cesantia_reintegrado = monto_sub_cesantia_reintegrado;
    }

    public String getNombre_beneficiario() {
        return nombre_beneficiario;
    }

    public void setNombre_beneficiario(String nombre_beneficiario) {
        this.nombre_beneficiario = nombre_beneficiario;
    }

    public long getRut_beneficiario() {
        return rut_beneficiario;
    }

    public void setRut_beneficiario(long rut_beneficiario) {
        this.rut_beneficiario = rut_beneficiario;
    }

    public int getTipo_reintegro() {
        return tipo_reintegro;
    }

    public void setTipo_reintegro(int tipo_reintegro) {
        this.tipo_reintegro = tipo_reintegro;
    }

}
