package cl.araucana.sivegam.vo;

public class AfcErrorVO {

    /** Variables de la clase. */
    private int codRespuesta;
    private int codigoError;
    private String glosaError;

    /** Generacion de get and set. */
    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    public int getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(int codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public String getGlosaError() {
        return glosaError;
    }

    public void setGlosaError(String glosaError) {
        this.glosaError = glosaError;
    }

}
