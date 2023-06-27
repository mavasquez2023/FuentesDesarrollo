package cl.araucana.independientes.vo;

public class TablasGlobalesVO {

    private int codigo;
    private String glosa;
    private int entidad;
    private int estado;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getEntidad() {
        return entidad;
    }
    public void setEntidad(int entidad) {
        this.entidad = entidad;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
}
