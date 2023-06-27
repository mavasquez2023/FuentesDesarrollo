package cl.araucana.independientes.vo;

public class RepNominaSolAfiVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaRepNominaSolAfi;
    private int paginaActualRepNominaSolAfi;
    private LinRepNominaSolAfiVO[] lisRepNominaSolAfi;

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
    public LinRepNominaSolAfiVO[] getLisRepNominaSolAfi() {
        return lisRepNominaSolAfi;
    }
    public void setLisRepNominaSolAfi(LinRepNominaSolAfiVO[] lisRepNominaSolAfi) {
        this.lisRepNominaSolAfi = lisRepNominaSolAfi;
    }
    public int getPaginaActualRepNominaSolAfi() {
        return paginaActualRepNominaSolAfi;
    }
    public void setPaginaActualRepNominaSolAfi(int paginaActualRepNominaSolAfi) {
        this.paginaActualRepNominaSolAfi = paginaActualRepNominaSolAfi;
    }
    public int[] getPaginaRepNominaSolAfi() {
        return paginaRepNominaSolAfi;
    }
    public void setPaginaRepNominaSolAfi(int[] paginaRepNominaSolAfi) {
        this.paginaRepNominaSolAfi = paginaRepNominaSolAfi;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
