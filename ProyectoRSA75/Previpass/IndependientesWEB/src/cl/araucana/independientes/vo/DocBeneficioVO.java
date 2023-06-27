package cl.araucana.independientes.vo;

public class DocBeneficioVO {

    private long idDocumentoBenAfi;
    private long idBeneficioAfiliado;
    private int idDocBeneficio;
    private int estadoDocBeneficio;

    public int getEstadoDocBeneficio() {
        return estadoDocBeneficio;
    }
    public void setEstadoDocBeneficio(int estadoDocBeneficio) {
        this.estadoDocBeneficio = estadoDocBeneficio;
    }
    public long getIdBeneficioAfiliado() {
        return idBeneficioAfiliado;
    }
    public void setIdBeneficioAfiliado(long idBeneficioAfiliado) {
        this.idBeneficioAfiliado = idBeneficioAfiliado;
    }
    public int getIdDocBeneficio() {
        return idDocBeneficio;
    }
    public void setIdDocBeneficio(int idDocBeneficio) {
        this.idDocBeneficio = idDocBeneficio;
    }
    public long getIdDocumentoBenAfi() {
        return idDocumentoBenAfi;
    }
    public void setIdDocumentoBenAfi(long idDocumentoBenAfi) {
        this.idDocumentoBenAfi = idDocumentoBenAfi;
    }

}
