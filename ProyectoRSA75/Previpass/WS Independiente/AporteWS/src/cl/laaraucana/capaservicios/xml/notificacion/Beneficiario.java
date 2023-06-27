package cl.laaraucana.capaservicios.xml.notificacion;

public class Beneficiario {

	private String rut;
	private String rutDv;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	public Beneficiario (){}
	
	public Beneficiario (String rut, String rutDv, String nombres, String apellidoPaterno,
			String apellidoMaterno){
		this.rut=rut;
		this.rutDv=rutDv;
		this.nombres=nombres;
		this.apellidoPaterno=apellidoPaterno;
		this.apellidoMaterno=apellidoMaterno;
	}
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRutDv() {
		return rutDv;
	}
	public void setRutDv(String rutDv) {
		this.rutDv = rutDv;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
}
