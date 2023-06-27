package cl.araucana.aporte.orqInput.bo;

import java.io.Serializable;

public class AporteDetalleBO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 6223630440947218569L;
    private int FechaVencimiento;
    private int Monto;
    private int InteresesReajuste;
    private int RentaPromedio;

    public int getFechaVencimiento() {
        return FechaVencimiento;
    }
    public void setFechaVencimiento(int fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }
    public int getInteresesReajuste() {
        return InteresesReajuste;
    }
    public void setInteresesReajuste(int interesesReajuste) {
        InteresesReajuste = interesesReajuste;
    }
    public int getMonto() {
        return Monto;
    }
    public void setMonto(int monto) {
        Monto = monto;
    }
    public int getRentaPromedio() {
        return RentaPromedio;
    }
    public void setRentaPromedio(int rentaPromedio) {
        RentaPromedio = rentaPromedio;
    }	
}
