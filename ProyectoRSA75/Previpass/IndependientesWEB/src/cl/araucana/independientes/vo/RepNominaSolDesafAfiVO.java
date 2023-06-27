package cl.araucana.independientes.vo;

public class RepNominaSolDesafAfiVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaRepNominaSolDesafAfi;
    private int paginaActualRepNominaSolDesafAfi;
    private LinRepNominaSolDesafAfiVO[] lisRepNominaSolDesafAfi;

    public String getArchivoInforme() {
        return archivoInforme;
    }
    public void setArchivoInforme(String archivoInforme) {
        this.archivoInforme = archivoInforme;
    }
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public LinRepNominaSolDesafAfiVO[] getLisRepNominaSolDesafAfi() {
        return lisRepNominaSolDesafAfi;
    }
    public void setLisRepNominaSolDesafAfi(
            LinRepNominaSolDesafAfiVO[] lisRepNominaSolDesafAfi) {
        this.lisRepNominaSolDesafAfi = lisRepNominaSolDesafAfi;
    }
    public int getPaginaActualRepNominaSolDesafAfi() {
        return paginaActualRepNominaSolDesafAfi;
    }
    public void setPaginaActualRepNominaSolDesafAfi(
            int paginaActualRepNominaSolDesafAfi) {
        this.paginaActualRepNominaSolDesafAfi = paginaActualRepNominaSolDesafAfi;
    }
    public int[] getPaginaRepNominaSolDesafAfi() {
        return paginaRepNominaSolDesafAfi;
    }
    public void setPaginaRepNominaSolDesafAfi(int[] paginaRepNominaSolDesafAfi) {
        this.paginaRepNominaSolDesafAfi = paginaRepNominaSolDesafAfi;
    }

}
