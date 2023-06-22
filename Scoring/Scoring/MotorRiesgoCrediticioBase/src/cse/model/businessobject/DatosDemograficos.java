package cse.model.businessobject;

public class DatosDemograficos {
	
	private String fechaNacimiento; //aaaammdd
	private String genero;
	private String estadoCivil;
	private double remuneracion;
	private String fechaMorosidad; //aaaammdd
	private short creditosAnteriores;
	
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public double getRemuneracion() {
		return remuneracion;
	}
	public void setRemuneracion(double remuneracion) {
		this.remuneracion = remuneracion;
	}
	public String getFechaMorosidad() {
		return fechaMorosidad;
	}
	public void setFechaMorosidad(String fechaMorosidad) {
		this.fechaMorosidad = fechaMorosidad;
	}
	public short getCreditosAnteriores() {
		return creditosAnteriores;
	}
	public void setCreditosAnteriores(short creditosAnteriores) {
		this.creditosAnteriores = creditosAnteriores;
	}
	
	
		
	
}
