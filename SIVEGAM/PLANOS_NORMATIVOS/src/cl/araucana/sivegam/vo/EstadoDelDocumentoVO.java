package cl.araucana.sivegam.vo;

public class EstadoDelDocumentoVO {

    /* campos de la clase */
    private String estado_documento;
    private int id_estado_documento;
    private String desc_estado_documento;

    /* generacion de get and set */
    public String getDesc_estado_documento() {
        return desc_estado_documento;
    }

    public void setDesc_estado_documento(String desc_estado_documento) {
        this.desc_estado_documento = desc_estado_documento;
    }

    public String getEstado_documento() {
        return estado_documento;
    }

    public void setEstado_documento(String estado_documento) {
        this.estado_documento = estado_documento;
    }

    public int getId_estado_documento() {
        return id_estado_documento;
    }

    public void setId_estado_documento(int id_estado_documento) {
        this.id_estado_documento = id_estado_documento;
    }

}
