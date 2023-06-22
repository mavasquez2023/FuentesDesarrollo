package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;

public class HijoVO implements Serializable{
	private String rutHijo;
	private String nombreHijo;
	private String apellidoPaternoHijo;
	private String apellidoMaternoHijo;
	private String fecNacMenorUnAnio;
	private String hijoNacidoMuerto;
	private String folioDefuncion;
	private String sexoHijo;

	public String getSexoHijo() {
		return sexoHijo;
	}

	public void setSexoHijo(String sexoHijo) {
		this.sexoHijo = sexoHijo;
	}

	public String getRutHijo() {
		return rutHijo;
	}

	public void setRutHijo(String rutHijo) {
		this.rutHijo = rutHijo;
	}

	public String getNombreHijo() {
		return nombreHijo;
	}

	public void setNombreHijo(String nombreHijo) {
		this.nombreHijo = nombreHijo;
	}

	public String getApellidoPaternoHijo() {
		return apellidoPaternoHijo;
	}

	public void setApellidoPaternoHijo(String apellidoPaternoHijo) {
		this.apellidoPaternoHijo = apellidoPaternoHijo;
	}

	public String getApellidoMaternoHijo() {
		return apellidoMaternoHijo;
	}

	public void setApellidoMaternoHijo(String apellidoMaternoHijo) {
		this.apellidoMaternoHijo = apellidoMaternoHijo;
	}

	public String getFecNacMenorUnAnio() {
		return fecNacMenorUnAnio;
	}

	public void setFecNacMenorUnAnio(String fecNacMenorUnAnio) {
		this.fecNacMenorUnAnio = fecNacMenorUnAnio;
	}

	public String getHijoNacidoMuerto() {
		return hijoNacidoMuerto;
	}

	public void setHijoNacidoMuerto(String hijoNacidoMuerto) {
		this.hijoNacidoMuerto = hijoNacidoMuerto;
	}

	public String getFolioDefuncion() {
		return folioDefuncion;
	}

	public void setFolioDefuncion(String folioDefuncion) {
		this.folioDefuncion = folioDefuncion;
	}

}
