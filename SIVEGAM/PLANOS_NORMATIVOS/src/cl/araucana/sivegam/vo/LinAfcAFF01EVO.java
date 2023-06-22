package cl.araucana.sivegam.vo;

public class LinAfcAFF01EVO {

    /** Variables de la clase. */
    private long iD_Registro_AFC;
    private String rut_beneficiario;
    private String dv_beneficiario;
    private String nombre_beneficiario;
    private int codigo_error;
    private String descripcion_error;
    private String tipArch;
    private String periodo;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTipArch() {
        return tipArch;
    }

    public void setTipArch(String tipArch) {
        this.tipArch = tipArch;
    }

    /** Generacion de get and set */
    public int getCodigo_error() {
        return codigo_error;
    }

    public void setCodigo_error(int codigo_error) {
        this.codigo_error = codigo_error;
    }

    public String getDescripcion_error() {
        return descripcion_error;
    }

    public void setDescripcion_error(String descripcion_error) {
        this.descripcion_error = descripcion_error;
    }

    public String getDv_beneficiario() {
        return dv_beneficiario;
    }

    public void setDv_beneficiario(String dv_beneficiario) {
        this.dv_beneficiario = dv_beneficiario;
    }

    public long getID_Registro_AFC() {
        return iD_Registro_AFC;
    }

    public void setID_Registro_AFC(long registro_AFC) {
        iD_Registro_AFC = registro_AFC;
    }

    public String getNombre_beneficiario() {
        return nombre_beneficiario;
    }

    public void setNombre_beneficiario(String nombre_beneficiario) {
        this.nombre_beneficiario = nombre_beneficiario;
    }

    public String getRut_beneficiario() {
        return rut_beneficiario;
    }

    public void setRut_beneficiario(String rut_beneficiario) {
        this.rut_beneficiario = rut_beneficiario;
    }

}
