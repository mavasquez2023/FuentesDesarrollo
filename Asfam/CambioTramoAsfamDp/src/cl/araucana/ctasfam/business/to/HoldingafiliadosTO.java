package cl.araucana.ctasfam.business.to;

import java.sql.Time;

public class HoldingafiliadosTO {
	
	 //--alexis advise 15-06-2012--//
	public HoldingafiliadosTO(){
		
	}
	
	private int periodo;
	private int rutempresa;
	private int oficina;
	private int sucursal;
	private String dvempresa;
	private int ruttrabajador;
	private String dvtrabajador;
	private String nombre;
	private int remuneracionesmismoempleador;
	private int otrasremuneraciones;
	private int rentatrabajadorindependiente;
	private int subsidio;
	private int pensiones;
	private int totalingresos;
	private int numeromeses;
	private int ingresopromedio;
	private int trabajadorconsindeclaracion;
	private int codigotramo;
	private int valortramo;
	private String origen;
	private Time tiempo;
	private int afama;
	
	public Time getTiempo() {
		return tiempo;
	}
	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}
	public int getAfama() {
		return afama;
	}
	public void setAfama(int afama) {
		this.afama = afama;
	}
	public String getDvempresa() {
		return dvempresa;
	}
	public void setDvempresa(String dvempresa) {
		this.dvempresa = dvempresa;
	}
	public String getDvtrabajador() {
		return dvtrabajador;
	}
	public void setDvtrabajador(String dvtrabajador) {
		this.dvtrabajador = dvtrabajador;
	}
	public int getIngresopromedio() {
		return ingresopromedio;
	}
	public void setIngresopromedio(int ingresopromedio) {
		this.ingresopromedio = ingresopromedio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumeromeses() {
		return numeromeses;
	}
	public void setNumeromeses(int numeromeses) {
		this.numeromeses = numeromeses;
	}
	public int getOtrasremuneraciones() {
		return otrasremuneraciones;
	}
	public void setOtrasremuneraciones(int otrasremuneraciones) {
		this.otrasremuneraciones = otrasremuneraciones;
	}
	public int getPensiones() {
		return pensiones;
	}
	public void setPensiones(int pensiones) {
		this.pensiones = pensiones;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getRemuneracionesmismoempleador() {
		return remuneracionesmismoempleador;
	}
	public void setRemuneracionesmismoempleador(int remuneracionesmismoempleador) {
		this.remuneracionesmismoempleador = remuneracionesmismoempleador;
	}
	public int getRentatrabajadorindependiente() {
		return rentatrabajadorindependiente;
	}
	public void setRentatrabajadorindependiente(int rentatrabajadorindependiente) {
		this.rentatrabajadorindependiente = rentatrabajadorindependiente;
	}
	public int getRutempresa() {
		return rutempresa;
	}
	public void setRutempresa(int rutempresa) {
		this.rutempresa = rutempresa;
	}
	public int getRuttrabajador() {
		return ruttrabajador;
	}
	public void setRuttrabajador(int ruttrabajador) {
		this.ruttrabajador = ruttrabajador;
	}
	public int getSubsidio() {
		return subsidio;
	}
	public void setSubsidio(int subsidio) {
		this.subsidio = subsidio;
	}
	public int getTotalingresos() {
		return totalingresos;
	}
	public void setTotalingresos(int totalingresos) {
		this.totalingresos = totalingresos;
	}
	public int getTrabajadorconsindeclaracion() {
		return trabajadorconsindeclaracion;
	}
	public void setTrabajadorconsindeclaracion(int trabajadorconsindeclaracion) {
		this.trabajadorconsindeclaracion = trabajadorconsindeclaracion;
	}
	public int getCodigotramo() {
		return codigotramo;
	}
	public void setCodigotramo(int codigotramo) {
		this.codigotramo = codigotramo;
	}
	public int getOficina() {
		return oficina;
	}
	public void setOficina(int oficina) {
		this.oficina = oficina;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public int getValortramo() {
		return valortramo;
	}
	public void setValortramo(int valortramo) {
		this.valortramo = valortramo;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	
	

}
