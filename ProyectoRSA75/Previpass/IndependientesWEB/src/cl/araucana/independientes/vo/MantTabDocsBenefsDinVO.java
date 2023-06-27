package cl.araucana.independientes.vo;

public class MantTabDocsBenefsDinVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabDocsBenefsDin;
    private int paginaActualMantTabDocsBenefsDin;
    private LinMantTabDocsBenefsDinVO[] lisMantTabDocsBenefsDin;

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

    public int getPaginaActualMantTabDocsBenefsDin() {
        return paginaActualMantTabDocsBenefsDin;
    }
    public void setPaginaActualMantTabDocsBenefsDin(int paginaActualMantTabDocsBenefsDin) {
        this.paginaActualMantTabDocsBenefsDin = paginaActualMantTabDocsBenefsDin;
    }
    public int[] getPaginaMantTabDocsBenefsDin() {
        return paginaMantTabDocsBenefsDin;
    }
    public void setPaginaMantTabDocsBenefsDin(int[] paginaMantTabDocsBenefsDin) {
        this.paginaMantTabDocsBenefsDin = paginaMantTabDocsBenefsDin;
    }
    public LinMantTabDocsBenefsDinVO[] getLisMantTabDocsBenefsDin() {
        return lisMantTabDocsBenefsDin;
    }
    public void setLisMantTabDocsBenefsDin(LinMantTabDocsBenefsDinVO[] lisMantTabDocsBenefsDin) {
        this.lisMantTabDocsBenefsDin = lisMantTabDocsBenefsDin;
    }
}
