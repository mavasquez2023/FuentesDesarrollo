package cl.araucana.independientes.vo;

public class TipoDocumentoVO {

    private int codigo;
    private String glosa;
    private int obligatorio;
    private int estado;
    private int tipoSol;

    public int getTipoSol() {
        return tipoSol;
    }
    public void setTipoSol(int tipoSol) {
        this.tipoSol = tipoSol;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public int getObligatorio() {
        return obligatorio;
    }
    public void setObligatorio(int obligatorio) {
        this.obligatorio = obligatorio;
    }

}
