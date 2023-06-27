package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class LeasingResultBO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5470961914233272922L;
    private int numRegistros;
    private int monto;
    private LeasingDetalleBO leasingDetalle[];

    public LeasingDetalleBO[] getLeasingDetalle() {
        return leasingDetalle;
    }
    public void setLeasingDetalle(LeasingDetalleBO[] leasingDetalle) {
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
