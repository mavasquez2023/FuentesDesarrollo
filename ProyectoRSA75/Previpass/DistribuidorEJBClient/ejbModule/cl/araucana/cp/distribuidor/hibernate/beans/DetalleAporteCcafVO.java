package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class DetalleAporteCcafVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAporte;
	private long idCodigoBarra; 
	private int fechaVencimiento;
    private long interesReajuste;
    private long monto;
    private long rentaPromedio;
    private long montoTotal;
    
     public DetalleAporteCcafVO(long idCodigoBarra){
     	this.idCodigoBarra=idCodigoBarra;
     }
     public DetalleAporteCcafVO(){
     	
     }
     
	public int getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(int fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public int getIdAporte() {
		return idAporte;
	}
	public void setIdAporte(int idAporte) {
		this.idAporte = idAporte;
	}
	public long getIdCodigoBarra() {
		return idCodigoBarra;
	}
	public void setIdCodigoBarra(long idCodigoBarra) {
		this.idCodigoBarra = idCodigoBarra;
	}
	public long getInteresReajuste() {
		return interesReajuste;
	}
	public void setInteresReajuste(long interesReajuste) {
		this.interesReajuste = interesReajuste;
	}
	public long getMonto() {
		return monto;
	}
	public void setMonto(long monto) {
		this.monto = monto;
	}
	public long getRentaPromedio() {
		return rentaPromedio;
	}
	public void setRentaPromedio(long rentaPromedio) {
		this.rentaPromedio = rentaPromedio;
	}
	public long getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(long montoTotal) {
		this.montoTotal = montoTotal;
	}
   }
