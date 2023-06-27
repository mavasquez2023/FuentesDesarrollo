package cl.araucana.independientes.vo;

public class ConsModMasivaSolVO {

    //declaración de variables de la clase.
    private String resultado;
    private int codResultado;

    private int[] paginaConsModMasivaSol;
    private int paginaActualConsModMasivaSol;
    private LinConsModMasivaSolVO[] lisConsModMasivaSol;

    /*Generación de getting and setting*/
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public LinConsModMasivaSolVO[] getLisConsModMasivaSol() {
        return lisConsModMasivaSol;
    }
    public void setLisConsModMasivaSol(LinConsModMasivaSolVO[] lisConsModMasivaSol) {
        this.lisConsModMasivaSol = lisConsModMasivaSol;
    }
    public int getPaginaActualConsModMasivaSol() {
        return paginaActualConsModMasivaSol;
    }
    public void setPaginaActualConsModMasivaSol(int paginaActualConsModMasivaSol) {
        this.paginaActualConsModMasivaSol = paginaActualConsModMasivaSol;
    }
    public int[] getPaginaConsModMasivaSol() {
        return paginaConsModMasivaSol;
    }
    public void setPaginaConsModMasivaSol(int[] paginaConsModMasivaSol) {
        this.paginaConsModMasivaSol = paginaConsModMasivaSol;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


}
