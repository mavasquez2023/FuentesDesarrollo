package cl.araucana.independientes.vo.param;

public class Documento {

    private int tipoDocumento;
    private String glosaTipoDocumento;
    private int alta;
    private int obligatorio;

    public int getAlta() {
        return alta;
    }
    public void setAlta(int alta) {
        this.alta = alta;
    }
    public String getGlosaTipoDocumento() {
        return glosaTipoDocumento;
    }
    public void setGlosaTipoDocumento(String glosaTipoDocumento) {
        this.glosaTipoDocumento = glosaTipoDocumento;
    }
    public int getObligatorio() {
        return obligatorio;
    }
    public void setObligatorio(int obligatorio) {
        this.obligatorio = obligatorio;
    }
    public int getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
