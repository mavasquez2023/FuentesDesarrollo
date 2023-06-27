package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class DetalleLeasingCcafVO implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idLeasing;
     private long idCodigoBarra;
     private int codigoOficina;
     private int fechaVencimiento;
     private long monto;
     private long montoUF;
     private String numCuenta;
     
     public DetalleLeasingCcafVO(long idCodigoBarra){
     	this.idCodigoBarra=idCodigoBarra;
     }
     public DetalleLeasingCcafVO(){
     	
     }
     
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
	public long getIdCodigoBarra() {
		return idCodigoBarra;
	}
	public void setIdCodigoBarra(long idCodigoBarra) {
		this.idCodigoBarra = idCodigoBarra;
	}
	public int getIdLeasing() {
		return idLeasing;
	}
	public void setIdLeasing(int idLeasing) {
		this.idLeasing = idLeasing;
	}
	public long getMonto() {
		return monto;
	}
	public void setMonto(long monto) {
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
