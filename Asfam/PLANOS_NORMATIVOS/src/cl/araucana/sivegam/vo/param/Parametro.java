package cl.araucana.sivegam.vo.param;

public class Parametro {

    /* Declaraci�n de las Variables de la clase Par�metro */
    private int codigo = 0;
    private String glosa = null;

    /* Creaci�n de Getting and Setting de clase param�trica */
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