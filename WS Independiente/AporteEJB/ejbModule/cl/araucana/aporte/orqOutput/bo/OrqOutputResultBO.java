package cl.araucana.aporte.orqOutput.bo;

import java.io.Serializable;

public class OrqOutputResultBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3443233298934924383L;
    ErrorResultBO errorBO;

    public ErrorResultBO getErrorBO() {
        return errorBO;
    }

    public void setErrorBO(ErrorResultBO errorBO) {
        this.errorBO = errorBO;
    }	
}
