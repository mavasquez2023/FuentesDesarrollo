package cl.araucana.independientes.vo;

public class MantTabRelBenefsDinDocsBenefsVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabRelBenefsDinDocsBenefs;
    private int paginaActualMantTabRelBenefsDinDocsBenefs;
    private LinMantTabRelBenefsDinDocsBenefsVO[] lisMantTabRelBenefsDinDocsBenefs;

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

    public int getPaginaActualMantTabRelBenefsDinDocsBenefs() {
        return paginaActualMantTabRelBenefsDinDocsBenefs;
    }
    public void setPaginaActualMantTabRelBenefsDinDocsBenefs(int paginaActualMantTabRelBenefsDinDocsBenefs) {
        this.paginaActualMantTabRelBenefsDinDocsBenefs = paginaActualMantTabRelBenefsDinDocsBenefs;
    }
    public int[] getPaginaMantTabDocsBenefsDin() {
        return paginaMantTabRelBenefsDinDocsBenefs;
    }
    public void setPaginaMantTabRelBenefsDinDocsBenefs(int[] paginaMantTabRelBenefsDinDocsBenefs) {
        this.paginaMantTabRelBenefsDinDocsBenefs = paginaMantTabRelBenefsDinDocsBenefs;
    }
    public LinMantTabRelBenefsDinDocsBenefsVO[] getLisMantTabRelBenefsDinDocsBenefs() {
        return lisMantTabRelBenefsDinDocsBenefs;
    }
    public void setLisMantTabRelBenefsDinDocsBenefs(LinMantTabRelBenefsDinDocsBenefsVO[] lisMantTabRelBenefsDinDocsBenefs) {
        this.lisMantTabRelBenefsDinDocsBenefs = lisMantTabRelBenefsDinDocsBenefs;
    }
}
