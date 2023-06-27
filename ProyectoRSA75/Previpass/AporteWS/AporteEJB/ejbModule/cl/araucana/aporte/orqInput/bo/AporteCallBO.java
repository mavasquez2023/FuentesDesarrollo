package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class AporteCallBO implements Serializable{	
    /**
     * 
     */
    private static final long serialVersionUID = -1392633231480678701L;
    private int codError;
    private String glsError;
    private AporteResultBO aporte;

    public int getCodError() {
        return codError;
    }
    public void setCodError(int codError) {
        this.codError = codError;
    }
    public String getGlsError() {
        return glsError;
    }
    public void setGlsError(String glsError) {
        this.glsError = glsError;
    }
    public AporteResultBO getAporte() {
        return aporte;
    }
    public void setAporte(AporteResultBO aporte) {
        this.aporte = aporte;
    }	
}

