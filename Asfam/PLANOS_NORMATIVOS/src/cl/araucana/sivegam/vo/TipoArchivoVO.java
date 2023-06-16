package cl.araucana.sivegam.vo;

public class TipoArchivoVO {

    /* variables de la clase */
    private int id_tipo_archivo;
    private String desc_tipo_archivo;

    /* Generacion de get and set */
    public String getDesc_tipo_archivo() {
        return desc_tipo_archivo;
    }

    public void setDesc_tipo_archivo(String desc_tipo_archivo) {
        this.desc_tipo_archivo = desc_tipo_archivo;
    }

    public int getId_tipo_archivo() {
        return id_tipo_archivo;
    }

    public void setId_tipo_archivo(int id_tipo_archivo) {
        this.id_tipo_archivo = id_tipo_archivo;
    }
}
