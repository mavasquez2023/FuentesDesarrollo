package cl.araucana.sivegam.vo;

public class LinCesantiaError041VO {

    /** Variables de la clase */
    private long id_sc041;
    private long rut_beneficiario;
    private String dv_beneficiario;
    private String nombre_beneficiario;
    private int codigo_error;
    private String descripcion_error;

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

    public long getId_sc041() {
        return id_sc041;
    }

    public void setId_sc041(long id_sc041) {
        this.id_sc041 = id_sc041;
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

}
