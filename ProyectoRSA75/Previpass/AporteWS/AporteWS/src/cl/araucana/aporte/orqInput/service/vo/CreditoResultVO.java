package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class CreditoResultVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7288194840939066563L;
    private int numRegistros;
    private int monto;
    private CreditoDetalleVO creditoDetalle[];

    public CreditoDetalleVO[] getCreditoDetalle() {
        return creditoDetalle;
    }
    public void setCreditoDetalle(CreditoDetalleVO[] creditoDetalle) {
        this.creditoDetalle = creditoDetalle;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public int getNumRegistros() {
        return numRegistros;
    }
    public void setNumRegistros(int numRegistros) {
        this.numRegistros = numRegistros;
    }	
}
