package cl.araucana.aporte.dispDatos.service.vo;

import java.io.Serializable;
import cl.araucana.aporte.dispDatos.service.vo.AfiliadoResultVO;
import cl.araucana.aporte.dispDatos.service.vo.ErrorResultVO;

public class DispDatosResultVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4737441262534325850L;
    private ErrorResultVO error;
    private AfiliadoResultVO afiliado;

    public AfiliadoResultVO getAfiliado() {
        return afiliado;
    }
    public void setAfiliado(AfiliadoResultVO afiliado) {
        this.afiliado = afiliado;
    }
    public ErrorResultVO getError() {
        return error;
    }
    public void setError(ErrorResultVO error) {
        this.error = error;
    }

}
