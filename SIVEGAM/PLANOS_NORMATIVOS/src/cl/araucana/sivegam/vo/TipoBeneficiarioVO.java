package cl.araucana.sivegam.vo;

public class TipoBeneficiarioVO {

    /* declaracion de campos de la clase. */
    private int id_tipo_beneficiario;
    private String desc_tipo_beneficiario;

    /* generacion de get and set */
    public String getDesc_tipo_beneficiario() {
        return desc_tipo_beneficiario;
    }

    public void setDesc_tipo_beneficiario(String desc_tipo_beneficiario) {
        this.desc_tipo_beneficiario = desc_tipo_beneficiario;
    }

    public int getId_tipo_beneficiario() {
        return id_tipo_beneficiario;
    }

    public void setId_tipo_beneficiario(int id_tipo_beneficiario) {
        this.id_tipo_beneficiario = id_tipo_beneficiario;
    }

}
