package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class OrqInputResultVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8183800872132977753L;
    private AporteResultVO aporteVO;
    private CreditoResultVO creditoVO;
    private LeasingResultVO leasingVO;
    private ErrorResultVO errorVO;

    public AporteResultVO getAporteVO() {
        return aporteVO;
    }
    public void setAporteVO(AporteResultVO aporteVO) {
        this.aporteVO = aporteVO;
    }
    public CreditoResultVO getCreditoVO() {
        return creditoVO;
    }
    public void setCreditoVO(CreditoResultVO creditoVO) {
        this.creditoVO = creditoVO;
    }
    public LeasingResultVO getLeasingBO() {
        return leasingVO;
    }
    public void setLeasingVO(LeasingResultVO leasingVO) {
        this.leasingVO = leasingVO;
    }
    public ErrorResultVO getErrorVO() {
        return errorVO;
    }
    public void setErrorVO(ErrorResultVO errorVO) {
        this.errorVO = errorVO;
    }
}
