package cl.araucana.ctasfam.batch.common.dto;

import java.sql.Time;



public class PropuestaAfiliadoDto {
	
	private int periodo;
	private int rutEmpresa;
	private String dvEmpresa;
	private int rutTrabajador;
	private String dvTrabajador;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreAfiliado;
	private int remuneracionesMismoEmpleador;
	private int otrasRemuneraciones;
	private int rentaTrabajadorIndependiente;
	private int subsidio;
	private int pensiones;
	private int totalIngresos;
	private int numeroMeses;
	private int ingresoPromedio;
	private int trabajadorConSinDeclaracion;
	private int oficina;
	private int sucursal;
	private int codigoTramo;
	private int valorTramo;
	private String origen;
	private Time tiempo;
	private int afama;
	
	public PropuestaAfiliadoDto(){}
	
	public String getNombreCompleto() {
		return apellidoPaterno + " " + apellidoMaterno + " " + nombreAfiliado;
	}
	
	
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getDvEmpresa() {
		return dvEmpresa;
	}
	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}
	public int getRutTrabajador() {
		return rutTrabajador;
	}
	public void setRutTrabajador(int rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}
	public String getDvTrabajador() {
		return dvTrabajador;
	}
	public void setDvTrabajador(String dvTrabajador) {
		this.dvTrabajador = dvTrabajador;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	public int getRemuneracionesMismoEmpleador() {
		return remuneracionesMismoEmpleador;
	}
	public void setRemuneracionesMismoEmpleador(int remuneracionesMismoEmpleador) {
		this.remuneracionesMismoEmpleador = remuneracionesMismoEmpleador;
	}
	public int getOtrasRemuneraciones() {
		return otrasRemuneraciones;
	}
	public void setOtrasRemuneraciones(int otrasRemuneraciones) {
		this.otrasRemuneraciones = otrasRemuneraciones;
	}
	public int getRentaTrabajadorIndependiente() {
		return rentaTrabajadorIndependiente;
	}
	public void setRentaTrabajadorIndependiente(int rentaTrabajadorIndependiente) {
		this.rentaTrabajadorIndependiente = rentaTrabajadorIndependiente;
	}
	public int getSubsidio() {
		return subsidio;
	}
	public void setSubsidio(int subsidio) {
		this.subsidio = subsidio;
	}
	public int getPensiones() {
		return pensiones;
	}
	public void setPensiones(int pensiones) {
		this.pensiones = pensiones;
	}
	public int getTotalIngresos() {
		return totalIngresos;
	}
	public void setTotalIngresos(int totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	public int getNumeroMeses() {
		return numeroMeses;
	}
	public void setNumeroMeses(int numeroMeses) {
		this.numeroMeses = numeroMeses;
	}
	public int getIngresoPromedio() {
		return ingresoPromedio;
	}
	public void setIngresoPromedio(int ingresoPromedio) {
		this.ingresoPromedio = ingresoPromedio;
	}
	public int getTrabajadorConSinDeclaracion() {
		return trabajadorConSinDeclaracion;
	}
	public void setTrabajadorConSinDeclaracion(int trabajadorConSinDeclaracion) {
		this.trabajadorConSinDeclaracion = trabajadorConSinDeclaracion;
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
	public int getCodigoTramo() {
		return codigoTramo;
	}
	public void setCodigoTramo(int codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	public int getValorTramo() {
		return valorTramo;
	}
	public void setValorTramo(int valorTramo) {
		this.valorTramo = valorTramo;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
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
	
}
