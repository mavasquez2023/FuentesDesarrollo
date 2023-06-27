package cl.araucana.independientes.vo;

public class TablaDocBenDinVO {

    /*Declaracion de variables*/
    private long idDocBenDin;
    private String glosaCorta;
    private String glosa;
    private int estado;

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    public String getGlosaCorta() {
        return glosaCorta;
    }
    public void setGlosaCorta(String glosaCorta) {
        this.glosaCorta = glosaCorta;
    }
    public long getIdDocBenDin() {
        return idDocBenDin;
    }
    public void setIdDocBenDin(long idDocBenDin) {
        this.idDocBenDin = idDocBenDin;
    }

}
