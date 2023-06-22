package cl.araucana.sivegam.vo;

public class LinAfcAFF01VO {

    /** Declaracion de variables del sistema. */
    private long id_archivo;
    private long id_maestro_Sivegam;
    private int flag_modificacion_registro;
    private int flag_eliminacion_registro;
    private long rut_Afiliado;
    private String digito_Verificador_Afiliado;
    private String nombres_Afiliado;
    private String apellido_Paterno_Afiliado;
    private String apellido_Materno_Afiliado;
    private String tramo;
    private long rut_Causante;
    private String digito_Verificador_Causante;
    private String nombres_Causantes;
    private String apellido_Paterno_Causante;
    private String apellido_Materno_Causante;
    private long fecha_de_Nacimeinto;
    private int codigo_Tipo_Causa;
    private String tipo_Causante;
    private String tipo_Asignacion_familiar;
    private long numero_Solicitud;
    private String tipo_Solicitud;
    private String sexo;
    private long monto;
    private long renta;
    private int comuna;
    private int region;
    private int tipo_Beneficiario;
    private String tipo_de_Archivo;
    private String afc_Periodo;

    /** Generacion de get and set. */
    public String getAfc_Periodo() {
        return afc_Periodo;
    }

    public void setAfc_Periodo(String afc_Periodo) {
        this.afc_Periodo = afc_Periodo;
    }

    public String getApellido_Materno_Afiliado() {
        return apellido_Materno_Afiliado;
    }

    public void setApellido_Materno_Afiliado(String apellido_Materno_Afiliado) {
        this.apellido_Materno_Afiliado = apellido_Materno_Afiliado;
    }

    public String getApellido_Materno_Causante() {
        return apellido_Materno_Causante;
    }

    public void setApellido_Materno_Causante(String apellido_Materno_Causante) {
        this.apellido_Materno_Causante = apellido_Materno_Causante;
    }

    public String getApellido_Paterno_Afiliado() {
        return apellido_Paterno_Afiliado;
    }

    public void setApellido_Paterno_Afiliado(String apellido_Paterno_Afiliado) {
        this.apellido_Paterno_Afiliado = apellido_Paterno_Afiliado;
    }

    public String getApellido_Paterno_Causante() {
        return apellido_Paterno_Causante;
    }

    public void setApellido_Paterno_Causante(String apellido_Paterno_Causante) {
        this.apellido_Paterno_Causante = apellido_Paterno_Causante;
    }

    public int getCodigo_Tipo_Causa() {
        return codigo_Tipo_Causa;
    }

    public void setCodigo_Tipo_Causa(int codigo_Tipo_Causa) {
        this.codigo_Tipo_Causa = codigo_Tipo_Causa;
    }

    public int getComuna() {
        return comuna;
    }

    public void setComuna(int comuna) {
        this.comuna = comuna;
    }

    public String getDigito_Verificador_Afiliado() {
        return digito_Verificador_Afiliado;
    }

    public void setDigito_Verificador_Afiliado(String digito_Verificador_Afiliado) {
        this.digito_Verificador_Afiliado = digito_Verificador_Afiliado;
    }

    public String getDigito_Verificador_Causante() {
        return digito_Verificador_Causante;
    }

    public void setDigito_Verificador_Causante(String digito_Verificador_Causante) {
        this.digito_Verificador_Causante = digito_Verificador_Causante;
    }

    public long getFecha_de_Nacimeinto() {
        return fecha_de_Nacimeinto;
    }

    public void setFecha_de_Nacimeinto(long fecha_de_Nacimeinto) {
        this.fecha_de_Nacimeinto = fecha_de_Nacimeinto;
    }

    public int getFlag_eliminacion_registro() {
        return flag_eliminacion_registro;
    }

    public void setFlag_eliminacion_registro(int flag_eliminacion_registro) {
        this.flag_eliminacion_registro = flag_eliminacion_registro;
    }

    public int getFlag_modificacion_registro() {
        return flag_modificacion_registro;
    }

    public void setFlag_modificacion_registro(int flag_modificacion_registro) {
        this.flag_modificacion_registro = flag_modificacion_registro;
    }

    public long getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(long id_archivo) {
        this.id_archivo = id_archivo;
    }

    public long getId_maestro_Sivegam() {
        return id_maestro_Sivegam;
    }

    public void setId_maestro_Sivegam(long id_maestro_Sivegam) {
        this.id_maestro_Sivegam = id_maestro_Sivegam;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public String getNombres_Afiliado() {
        return nombres_Afiliado;
    }

    public void setNombres_Afiliado(String nombres_Afiliado) {
        this.nombres_Afiliado = nombres_Afiliado;
    }

    public String getNombres_Causantes() {
        return nombres_Causantes;
    }

    public void setNombres_Causantes(String nombres_Causantes) {
        this.nombres_Causantes = nombres_Causantes;
    }

    public long getNumero_Solicitud() {
        return numero_Solicitud;
    }

    public void setNumero_Solicitud(long numero_Solicitud) {
        this.numero_Solicitud = numero_Solicitud;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public long getRenta() {
        return renta;
    }

    public void setRenta(long renta) {
        this.renta = renta;
    }

    public long getRut_Afiliado() {
        return rut_Afiliado;
    }

    public void setRut_Afiliado(long rut_Afiliado) {
        this.rut_Afiliado = rut_Afiliado;
    }

    public long getRut_Causante() {
        return rut_Causante;
    }

    public void setRut_Causante(long rut_Causante) {
        this.rut_Causante = rut_Causante;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo_Asignacion_familiar() {
        return tipo_Asignacion_familiar;
    }

    public void setTipo_Asignacion_familiar(String tipo_Asignacion_familiar) {
        this.tipo_Asignacion_familiar = tipo_Asignacion_familiar;
    }

    public int getTipo_Beneficiario() {
        return tipo_Beneficiario;
    }

    public void setTipo_Beneficiario(int tipo_Beneficiario) {
        this.tipo_Beneficiario = tipo_Beneficiario;
    }

    public String getTipo_Causante() {
        return tipo_Causante;
    }

    public void setTipo_Causante(String tipo_Causante) {
        this.tipo_Causante = tipo_Causante;
    }

    public String getTipo_de_Archivo() {
        return tipo_de_Archivo;
    }

    public void setTipo_de_Archivo(String tipo_de_Archivo) {
        this.tipo_de_Archivo = tipo_de_Archivo;
    }

    public String getTipo_Solicitud() {
        return tipo_Solicitud;
    }

    public void setTipo_Solicitud(String tipo_Solicitud) {
        this.tipo_Solicitud = tipo_Solicitud;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }
}
