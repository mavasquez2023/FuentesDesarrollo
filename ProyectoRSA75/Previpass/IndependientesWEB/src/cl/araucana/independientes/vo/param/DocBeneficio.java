package cl.araucana.independientes.vo.param;

public class DocBeneficio {

    private int idDocBeneficio;
    private String glosaDocBeneficio;
    private String glosaCortaDocBeneficio; 
    private int estadoDocBeneficio;
    private int isObligatorio;

    public int getIsObligatorio() {
        return isObligatorio;
    }
    public void setIsObligatorio(int isObligatorio) {
        this.isObligatorio = isObligatorio;
    }
    public int getEstadoDocBeneficio() {
        return estadoDocBeneficio;
    }
    public void setEstadoDocBeneficio(int estadoDocBeneficio) {
        this.estadoDocBeneficio = estadoDocBeneficio;
    }
    public String getGlosaCortaDocBeneficio() {
        return glosaCortaDocBeneficio;
    }
    public void setGlosaCortaDocBeneficio(String glosaCortaDocBeneficio) {
        this.glosaCortaDocBeneficio = glosaCortaDocBeneficio;
    }
    public String getGlosaDocBeneficio() {
        return glosaDocBeneficio;
    }
    public void setGlosaDocBeneficio(String glosaDocBeneficio) {
        this.glosaDocBeneficio = glosaDocBeneficio;
    }
    public int getIdDocBeneficio() {
        return idDocBeneficio;
    }
    public void setIdDocBeneficio(int idDocBeneficio) {
        this.idDocBeneficio = idDocBeneficio;
    }

}
