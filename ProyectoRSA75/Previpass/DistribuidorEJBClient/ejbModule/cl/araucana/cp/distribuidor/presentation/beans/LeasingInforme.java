package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

public class LeasingInforme implements Serializable{
	
	 private LeasingDetalleInforme[] leasingDetalleInformes;

	 private int monto;

	 private int numRegistros;


	public LeasingDetalleInforme[] getLeasingDetalleInformes() {
		return leasingDetalleInformes;
	}

	public void setLeasingDetalleInformes(
			LeasingDetalleInforme[] leasingDetalleInformes) {
		this.leasingDetalleInformes = leasingDetalleInformes;
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
