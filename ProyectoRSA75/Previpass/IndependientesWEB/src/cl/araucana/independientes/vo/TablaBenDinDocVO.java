package cl.araucana.independientes.vo;

public class TablaBenDinDocVO {

    /*Declaracion de variables*/
    private long idRelBeneficioDoc;
    private long idBeneficio;
    private long idDocBeneficio;
    private int isObligatorio;
    private int estado;

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public long getIdBeneficio() {
        return idBeneficio;
    }
    public void setIdBeneficio(long idBeneficio) {
        this.idBeneficio = idBeneficio;
    }
    public long getIdDocBeneficio() {
        return idDocBeneficio;
    }
    public void setIdDocBeneficio(long idDocBeneficio) {
        this.idDocBeneficio = idDocBeneficio;
    }
    public long getIdRelBeneficioDoc() {
        return idRelBeneficioDoc;
    }
    public void setIdRelBeneficioDoc(long idRelBeneficioDoc) {
        this.idRelBeneficioDoc = idRelBeneficioDoc;
    }
    public int getIsObligatorio() {
        return isObligatorio;
    }
    public void setIsObligatorio(int isObligatorio) {
        this.isObligatorio = isObligatorio;
    }

}
