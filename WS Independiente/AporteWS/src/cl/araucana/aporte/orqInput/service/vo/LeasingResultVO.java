package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class LeasingResultVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5470961914233272922L;
    private int numRegistros;
    private int monto;
    private LeasingDetalleVO leasingDetalle[];

    public LeasingDetalleVO[] getLeasingDetalle() {
        return leasingDetalle;
    }
    public void setLeasingDetalle(LeasingDetalleVO[] leasingDetalle) {
        this.leasingDetalle = leasingDetalle;
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
