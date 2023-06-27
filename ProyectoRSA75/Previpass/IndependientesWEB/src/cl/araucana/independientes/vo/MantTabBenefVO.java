package cl.araucana.independientes.vo;

public class MantTabBenefVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabBenef;
    private int paginaActualMantTabBenef;
    private LinMantTabBenefVO[] lisMantTabBenef;

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

    public int getPaginaActualMantTabBenef() {
        return paginaActualMantTabBenef;
    }
    public void setPaginaActualMantTabBenef(int paginaActualMantTabBenef) {
        this.paginaActualMantTabBenef = paginaActualMantTabBenef;
    }
    public int[] getPaginaMantTabBenef() {
        return paginaMantTabBenef;
    }
    public void setPaginaMantTabBenef(int[] paginaMantTabBenef) {
        this.paginaMantTabBenef = paginaMantTabBenef;
    }
    public LinMantTabBenefVO[] getLisMantTabBenef() {
        return lisMantTabBenef;
    }
    public void setLisMantTabBenef(LinMantTabBenefVO[] lisMantTabBenef) {
        this.lisMantTabBenef = lisMantTabBenef;
    }
}
