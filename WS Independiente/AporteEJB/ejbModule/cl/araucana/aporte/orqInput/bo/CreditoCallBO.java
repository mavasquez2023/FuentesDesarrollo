package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class CreditoCallBO implements Serializable{	
    /**
     * 
     */
    private static final long serialVersionUID = -1392633231480678701L;
    private int codError;
    private String glsError;
    private CreditoResultBO credito;
    public int getCodError() {
        return codError;
    }
    public void setCodError(int codError) {
        this.codError = codError;
    }
    public CreditoResultBO getCredito() {
        return credito;
    }
    public void setCredito(CreditoResultBO credito) {
        this.credito = credito;
    }
    public String getGlsError() {
        return glsError;
    }
    public void setGlsError(String glsError) {
        this.glsError = glsError;
    }

}
