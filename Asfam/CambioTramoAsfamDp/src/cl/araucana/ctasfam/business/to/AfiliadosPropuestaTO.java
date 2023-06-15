package cl.araucana.ctasfam.business.to;

import java.io.Serializable;

import cl.araucana.ctasfam.resources.util.Padder;

public class AfiliadosPropuestaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int periodo;
	private int oficina;
	private int rutEmpresa;
	private String dvRutEmpresa;
	private int sucursal;
	private int rutAfiliado;
	private String dvRutAfiliado;
	private String nombreAfiliado;
	private int remuneracionEmpleador;
	private int remuneracionOtroEmpleador;
	private int remuneracionIndependiente;
	private int rentaSubsidio;
	private int rentaPensiones;
	private int totalIngresos;
	private int numeroMeses;
	private int ingresoPromedio;
	private int declaracion;
	private int tramo;
	private int valorTramo;
	private String origen;
	private String nombres;
	private String apaterno;
	private String amaterno;

	public AfiliadosPropuestaTO() {
		super();
	}

	public AfiliadosPropuestaTO(int oficina, int rutEmpresa,
			String dvRutEmpresa, int sucursal) {
		super();
		this.oficina = oficina;
		this.rutEmpresa = rutEmpresa;
		this.dvRutEmpresa = dvRutEmpresa;
		this.sucursal = sucursal;
	}
	
	public String getFormatedRutAfiliado() {
		return Padder.padSeparators(getRutAfiliado(), '.') + "-" + getDvRutAfiliado();
	}

	public int getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(int declaracion) {
		this.declaracion = declaracion;
	}

	public String getDvRutAfiliado() {
		return dvRutAfiliado;
	}

	public void setDvRutAfiliado(String dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}

	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}

	public int getIngresoPromedio() {
		return ingresoPromedio;
	}

	public void setIngresoPromedio(int ingresoPromedio) {
		this.ingresoPromedio = ingresoPromedio;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public int getNumeroMeses() {
		return numeroMeses;
	}

	public void setNumeroMeses(int numeroMeses) {
		this.numeroMeses = numeroMeses;
	}

	public int getOficina() {
		return oficina;
	}

	public void setOficina(int oficina) {
		this.oficina = oficina;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getRemuneracionEmpleador() {
		return remuneracionEmpleador;
	}

	public void setRemuneracionEmpleador(int remuneracionEmpleador) {
		this.remuneracionEmpleador = remuneracionEmpleador;
	}

	public int getRemuneracionIndependiente() {
		return remuneracionIndependiente;
	}

	public void setRemuneracionIndependiente(int remuneracionIndependiente) {
		this.remuneracionIndependiente = remuneracionIndependiente;
	}

	public int getRemuneracionOtroEmpleador() {
		return remuneracionOtroEmpleador;
	}

	public void setRemuneracionOtroEmpleador(int remuneracionOtroEmpleador) {
		this.remuneracionOtroEmpleador = remuneracionOtroEmpleador;
	}

	public int getRentaPensiones() {
		return rentaPensiones;
	}

	public void setRentaPensiones(int rentaPensiones) {
		this.rentaPensiones = rentaPensiones;
	}

	public int getRentaSubsidio() {
		return rentaSubsidio;
	}

	public void setRentaSubsidio(int rentaSubsidio) {
		this.rentaSubsidio = rentaSubsidio;
	}

	public int getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public int getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	public int getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(int totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public int getTramo() {
		return tramo;
	}

	public void setTramo(int tramo) {
		this.tramo = tramo;
	}

	public int getValorTramo() {
		return valorTramo;
	}

	public void setValorTramo(int valorTramo) {
		this.valorTramo = valorTramo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getAmaterno() {
		return amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getApaterno() {
		return apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	
	
}
