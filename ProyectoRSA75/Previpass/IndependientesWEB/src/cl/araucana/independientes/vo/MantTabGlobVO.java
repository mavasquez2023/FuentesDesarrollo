package cl.araucana.independientes.vo;

public class MantTabGlobVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabGlob;
    private int paginaActualMantTabGlob;
    private LinMantTabGlobVO[] lisMantTabGlob;

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
    public int getPaginaActualMantTabGlob() {
        return paginaActualMantTabGlob;
    }
    public void setPaginaActualMantTabGlob(int paginaActualMantTabGlob) {
        this.paginaActualMantTabGlob = paginaActualMantTabGlob;
    }
    public int[] getPaginaMantTabGlob() {
        return paginaMantTabGlob;
    }
    public void setPaginaMantTabGlob(int[] paginaMantTabGlob) {
        this.paginaMantTabGlob = paginaMantTabGlob;
    }
    public LinMantTabGlobVO[] getLisMantTabGlob() {
        return lisMantTabGlob;
    }
    public void setLisMantTabGlob(LinMantTabGlobVO[] lisMantTabGlob) {
        this.lisMantTabGlob = lisMantTabGlob;
    }
}
