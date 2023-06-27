package cl.araucana.independientes.vo.param;

public class Parametro {

    /* Declaración de las Variables de la clase Parámetro*/
    private int codigo = 0;
    private String glosa = null;
    private int estado = 0;

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    /*Creación de Getting and Setting de clase paramétrica*/
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

}
