package cl.araucana.independientes.vo;

public class MantTabPromVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabProm;
    private int paginaActualMantTabProm;
    private LinMantTabPromVO[] lisMantTabProm;

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

    public int getPaginaActualMantTabProm() {
        return paginaActualMantTabProm;
    }
    public void setPaginaActualMantTabProm(int paginaActualMantTabProm) {
        this.paginaActualMantTabProm = paginaActualMantTabProm;
    }
    public int[] getPaginaMantTabProm() {
        return paginaMantTabProm;
    }
    public void setPaginaMantTabProm(int[] paginaMantTabProm) {
        this.paginaMantTabProm = paginaMantTabProm;
    }
    public LinMantTabPromVO[] getLisMantTabProm() {
        return lisMantTabProm;
    }
    public void setLisMantTabProm(LinMantTabPromVO[] lisMantTabProm) {
        this.lisMantTabProm = lisMantTabProm;
    }
}
