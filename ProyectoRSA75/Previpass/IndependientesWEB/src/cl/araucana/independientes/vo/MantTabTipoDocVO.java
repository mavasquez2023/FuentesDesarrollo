package cl.araucana.independientes.vo;

public class MantTabTipoDocVO {

    private String resultado;
    private int codResultado;

    private String archivoInforme;
    private int[] paginaMantTabTipoDoc;
    private int paginaActualMantTabTipoDoc;
    private LinMantTabTipoDocVO[] lisMantTabTipoDoc;

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

    public int getPaginaActualMantTabTipoDoc() {
        return paginaActualMantTabTipoDoc;
    }
    public void setPaginaActualMantTabTipoDoc(int paginaActualMantTabTipoDoc) {
        this.paginaActualMantTabTipoDoc = paginaActualMantTabTipoDoc;
    }
    public int[] getPaginaMantTabTipoDoc() {
        return paginaMantTabTipoDoc;
    }
    public void setPaginaMantTabTipoDoc(int[] paginaMantTabTipoDoc) {
        this.paginaMantTabTipoDoc = paginaMantTabTipoDoc;
    }
    public LinMantTabTipoDocVO[] getLisMantTabTipoDoc() {
        return lisMantTabTipoDoc;
    }
    public void setLisMantTabTipoDoc(LinMantTabTipoDocVO[] lisMantTabTipoDoc) {
        this.lisMantTabTipoDoc = lisMantTabTipoDoc;
    }
}
