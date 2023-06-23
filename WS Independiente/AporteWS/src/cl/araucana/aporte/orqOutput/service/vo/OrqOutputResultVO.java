package cl.araucana.aporte.orqOutput.service.vo;

import java.io.Serializable;

public class OrqOutputResultVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3443233298934924383L;
    ErrorResultVO errorVO;

    public ErrorResultVO getErrorVO() {
        return errorVO;
    }
    public void setErrorVO(ErrorResultVO errorVO) {
        this.errorVO = errorVO;
    }
}
