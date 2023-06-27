package cl.araucana.independientes.vo;

public class ConsMasivaApoVO {
    private String resultado;
    private int codResultado;

    private String archivoInforme;	
    private int[] paginaConsMasivaApo;
    private int paginaActualConsModMasivaSol;
    private LinConsMasivaApoVO [] lisConsMasivaApoVO;

    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public LinConsMasivaApoVO[] getLisConsMasivaApoVO() {
        return lisConsMasivaApoVO;
    }
    public void setLisConsMasivaApoVO(LinConsMasivaApoVO[] lisConsMasivaApoVO) {
        this.lisConsMasivaApoVO = lisConsMasivaApoVO;
    }
    public int getPaginaActualConsModMasivaSol() {
        return paginaActualConsModMasivaSol;
    }
    public void setPaginaActualConsModMasivaSol(int paginaActualConsModMasivaSol) {
        this.paginaActualConsModMasivaSol = paginaActualConsModMasivaSol;
    }
    public int[] getPaginaConsMasivaApo() {
        return paginaConsMasivaApo;
    }
    public void setPaginaConsMasivaApo(int[] paginaConsMasivaApo) {
        this.paginaConsMasivaApo = paginaConsMasivaApo;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getArchivoInforme() {
        return archivoInforme;
    }
    public void setArchivoInforme(String archivoInforme) {
        this.archivoInforme = archivoInforme;
    }

}
