package cl.araucana.independientes.vo;

public class MantTabPerfVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabPerf;
    private int paginaActualMantTabPerf;
    private LinMantTabPerfVO[] lisMantTabPerf;

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

    public int getPaginaActualMantTabPerf() {
        return paginaActualMantTabPerf;
    }
    public void setPaginaActualMantTabPerf(int paginaActualMantTabPerf) {
        this.paginaActualMantTabPerf = paginaActualMantTabPerf;
    }
    public int[] getPaginaMantTabPerf() {
        return paginaMantTabPerf;
    }
    public void setPaginaMantTabPerf(int[] paginaMantTabPerf) {
        this.paginaMantTabPerf = paginaMantTabPerf;
    }
    public LinMantTabPerfVO[] getLisMantTabPerf() {
        return lisMantTabPerf;
    }
    public void setLisMantTabPerf(LinMantTabPerfVO[] lisMantTabPerf) {
        this.lisMantTabPerf = lisMantTabPerf;
    }
}
