package cl.araucana.estasfam.web.forms;

import java.io.Serializable;

public class EstadisitcasAsfamForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String [] codigoEstadistica;
	
	private String estASI5490;
	private String estASI5491;
	private String estASI5460;
	private String estASI4580;
	private String estASI4560;
	private String estCUADRO8;
	private String estCUADRO10;
	
	private String generacion;
	
	private String mesPeriodo;
	private String anoPeriodo;
	
	public EstadisitcasAsfamForm(){
		super();
	}
	public EstadisitcasAsfamForm(String[] codigoEstadistica) {
		super();
		this.codigoEstadistica = codigoEstadistica;
	}

	public String[] getCodigoEstadistica() {
		return codigoEstadistica;
	}

	public void setCodigoEstadistica(String[] codigoEstadistica) {
		this.codigoEstadistica = codigoEstadistica;
	}
	public String getEstASI5490() {
		return estASI5490;
	}
	public void setEstASI5490(String estASI5490) {
		this.estASI5490 = estASI5490;
	}
	public String getEstASI5491() {
		return estASI5491;
	}
	public void setEstASI5491(String estASI5491) {
		this.estASI5491 = estASI5491;
	}
	public String getEstASI5460() {
		return estASI5460;
	}
	public void setEstASI5460(String estASI5460) {
		this.estASI5460 = estASI5460;
	}
	public String getEstASI4580() {
		return estASI4580;
	}
	public void setEstASI4580(String estASI4580) {
		this.estASI4580 = estASI4580;
	}
	public String getEstASI4560() {
		return estASI4560;
	}
	public void setEstASI4560(String estASI4560) {
		this.estASI4560 = estASI4560;
	}
	public String getEstCUADRO8() {
		return estCUADRO8;
	}
	public void setEstCUADRO8(String estCUADRO8) {
		this.estCUADRO8 = estCUADRO8;
	}
	public String getEstCUADRO10() {
		return estCUADRO10;
	}
	public void setEstCUADRO10(String estCUADRO10) {
		this.estCUADRO10 = estCUADRO10;
	}
	public String getGeneracion() {
		if(getEstASI5490().equals("GENERANDO") || getEstASI5491().equals("GENERANDO") ||
				getEstASI5460().equals("GENERANDO") || getEstASI4580().equals("GENERANDO") ||
				getEstASI4560().equals("GENERANDO") || getEstCUADRO8().equals("GENERANDO") ||
				getEstCUADRO10().equals("GENERANDO")){
			setGeneracion("true");
		}
		
		return generacion;
	}
	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}
	public String getMesPeriodo() {
		return mesPeriodo;
	}
	public void setMesPeriodo(String mesPeriodo) {
		this.mesPeriodo = mesPeriodo;
	}
	public String getAnoPeriodo() {
		return anoPeriodo;
	}
	public void setAnoPeriodo(String anoPeriodo) {
		this.anoPeriodo = anoPeriodo;
	}
	
}
