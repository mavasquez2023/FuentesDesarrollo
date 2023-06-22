package cl.araucana.sivegam.vo;

public class ModalidadDePagoVO {

    /* campos de la clase. */
    private String modalidad_pago;
    private int id_modalidad_pago;
    private String desc_modalidad_pago;

    /* generacion de get and set */
    public String getDesc_modalidad_pago() {
        return desc_modalidad_pago;
    }

    public void setDesc_modalidad_pago(String desc_modalidad_pago) {
        this.desc_modalidad_pago = desc_modalidad_pago;
    }

    public int getId_modalidad_pago() {
        return id_modalidad_pago;
    }

    public void setId_modalidad_pago(int id_modalidad_pago) {
        this.id_modalidad_pago = id_modalidad_pago;
    }

    public String getModalidad_pago() {
        return modalidad_pago;
    }

    public void setModalidad_pago(String modalidad_pago) {
        this.modalidad_pago = modalidad_pago;
    }

}
