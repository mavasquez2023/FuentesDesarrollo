package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class OrqInputResultBO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8183800872132977753L;
    private AporteResultBO aporteBO;
    private CreditoResultBO creditoBO;
    private LeasingResultBO leasingBO;
    private ErrorResultBO errorBO;

    public AporteResultBO getAporteBO() {
        return aporteBO;
    }
    public void setAporteBO(AporteResultBO aporteBO) {
        this.aporteBO = aporteBO;
    }
    public CreditoResultBO getCreditoBO() {
        return creditoBO;
    }
    public void setCreditoBO(CreditoResultBO creditoBO) {
        this.creditoBO = creditoBO;
    }
    public LeasingResultBO getLeasingBO() {
        return leasingBO;
    }
    public void setLeasingBO(LeasingResultBO leasingBO) {
        this.leasingBO = leasingBO;
    }
    public ErrorResultBO getErrorBO() {
        return errorBO;
    }
    public void setErrorBO(ErrorResultBO errorBO) {
        this.errorBO = errorBO;
    }
}
