package cl.araucana.wssiagf;

import java.io.Serializable;
import java.util.Date;

public class TramoBusinessTO implements Serializable{
	private int year;
	private int tramo;
	private long ingresoPromedio;
	private int montoUnitario;
	private Date fechaInicioTramo;
	
	public TramoBusinessTO(){}
	
	public TramoBusinessTO(int year, Date fechaInicioTramo) {
		this.year = year;
		this.fechaInicioTramo = fechaInicioTramo;
	}
	
	public int getMontoUnitario() {
		return montoUnitario;
	}
	public void setMontoUnitario(int montoUnitario) {
		this.montoUnitario = montoUnitario;
	}
	public int getTramo() {
		return tramo;
	}
	public void setTramo(int tramo) {
		this.tramo = tramo;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getFechaInicioTramo() {
		return fechaInicioTramo;
	}
	public void setFechaInicioTramo(Date fechaInicioTramo) {
		this.fechaInicioTramo = fechaInicioTramo;
	}
	public long getIngresoPromedio() {
		return ingresoPromedio;
	}
	public void setIngresoPromedio(long ingresoPromedio) {
		this.ingresoPromedio = ingresoPromedio;
	}
}
