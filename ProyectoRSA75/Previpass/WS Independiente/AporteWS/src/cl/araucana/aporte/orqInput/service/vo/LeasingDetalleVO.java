package cl.araucana.aporte.orqInput.service.vo;

import java.io.Serializable;

public class LeasingDetalleVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8992075988756291212L;
    private int codigoOficina;
    private String numCuenta;
    private long montoUF;
    private int monto;
    private int fechaVencimiento;

    public int getCodigoOficina() {
        return codigoOficina;
    }
    public void setCodigoOficina(int codigoOficina) {
        this.codigoOficina = codigoOficina;
    }
    public int getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(int fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public long getMontoUF() {
        return montoUF;
    }
    public void setMontoUF(long montoUF) {
        this.montoUF = montoUF;
    }
    public String getNumCuenta() {
        return numCuenta;
    }
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }	
}
