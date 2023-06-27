package cl.araucana.aporte.dispDatos.bo;

import java.io.Serializable;

public class DispDatosResultBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4879679841921888093L;
    private ErrorResultBO error;
    private AfiliadoResultBO afiliado;

    public AfiliadoResultBO getAfiliado() {
        return afiliado;
    }
    public void setAfiliado(AfiliadoResultBO afiliado) {
        this.afiliado = afiliado;
    }
    public ErrorResultBO getError() {
        return error;
    }
    public void setError(ErrorResultBO error) {
        this.error = error;
    }
}
