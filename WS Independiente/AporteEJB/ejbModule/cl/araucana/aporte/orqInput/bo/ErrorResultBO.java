package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class ErrorResultBO implements Serializable{	
    /**
     * 
     */
    private static final long serialVersionUID = -1392633231480678701L;
    private int codError;
    private String glsError;

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
}