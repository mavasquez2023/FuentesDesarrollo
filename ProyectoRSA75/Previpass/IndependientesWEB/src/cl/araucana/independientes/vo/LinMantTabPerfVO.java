package cl.araucana.independientes.vo;

public class LinMantTabPerfVO {

    /*Declaracion de variables*/
    private String codigo;
    private String rut;
    private String perfil;
    private String glosa;
    private String entidad;
    private String estado;

    public String getEntidad() {
        return entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
}
