package cl.araucana.ctasfam.business.to;

import java.util.List;

public class AfiliadosErrorTO {
	
	
	public AfiliadosErrorTO(){
		
	}
	
	
	private String nombrearchivo;
	private int numerolinea;
	private String ruttrabajador;
	private String descripcionerror;
	private int numeroColumna;
	public String getNombrearchivo() {
		return nombrearchivo;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	public int getNumerolinea() {
		return numerolinea;
	}
	public void setNumerolinea(int numerolinea) {
		this.numerolinea = numerolinea;
	}
	public String getRuttrabajador() {
		return ruttrabajador;
	}
	public void setRuttrabajador(String ruttrabajador) {
		this.ruttrabajador = ruttrabajador;
	}
	public String getDescripcionerror() {
		return descripcionerror;
	}
	public void setDescripcionerror(String descripcionerror) {
		this.descripcionerror = descripcionerror;
	}
	public int getNumeroColumna() {
		return numeroColumna;
	}
	public void setNumeroColumna(int numeroColumna) {
		this.numeroColumna = numeroColumna;
	}
		
}
