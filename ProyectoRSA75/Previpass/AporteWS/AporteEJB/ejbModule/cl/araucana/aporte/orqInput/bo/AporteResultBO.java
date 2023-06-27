package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class AporteResultBO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 6223630440947218569L;
    private int numRegistros;
    private int monto;
    private AporteDetalleBO aporteDetalle[];


    public AporteDetalleBO[] getAporteDetalle() {
        return aporteDetalle;
    }
    public void setAporteDetalle(AporteDetalleBO[] aporteDetalle) {
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
