package cl.araucana.ctasfam.business.to;

import java.sql.Time;
import java.util.Date;

public class EstadoTO {
	
	public EstadoTO(){
		
	}
	
	private String empresa;
	private String etapa;
	private Date fecha;
	private Time hora;
	 
	
	
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	 
	
	

}
