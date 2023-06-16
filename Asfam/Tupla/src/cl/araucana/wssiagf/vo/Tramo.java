package cl.araucana.wssiagf.vo;

import java.util.Date;

public class Tramo {
	private int numTramo;
	private long rentaDesde;
	private int montoUnitarioBeneficio;
	private Date fechaInicio;
	private Date fechaTermino;
	private int a�o;

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public int getMontoUnitarioBeneficio() {
		return montoUnitarioBeneficio;
	}

	public void setMontoUnitarioBeneficio(int montoUnitarioBeneficio) {
		this.montoUnitarioBeneficio = montoUnitarioBeneficio;
	}

	public int getNumTramo() {
		return numTramo;
	}

	public void setNumTramo(int numTramo) {
		this.numTramo = numTramo;
	}

	public long getRentaDesde() {
		return rentaDesde;
	}

	public void setRentaDesde(long rentaDesde) {
		this.rentaDesde = rentaDesde;
	}

}
