package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class AporteResultVO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 6223630440947218569L;
    private int numRegistros;
    private int monto;
    private AporteDetalleVO aporteDetalle[];


    public AporteDetalleVO[] getAporteDetalle() {
        return aporteDetalle;
    }
    public void setAporteDetalle(AporteDetalleVO[] aporteDetalle) {
        this.aporteDetalle = aporteDetalle;
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
