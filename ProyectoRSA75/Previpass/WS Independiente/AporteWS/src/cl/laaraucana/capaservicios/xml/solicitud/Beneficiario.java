package cl.laaraucana.capaservicios.xml.solicitud;

public class Beneficiario {
	private String rut;
	private String rutDv;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String glosaCorreo;
	private String celular;

	public Beneficiario(){}
	
	public Beneficiario (String rut, String rutDv, String nombres, String apellidoPaterno,
			String apellidoMaterno, String correo, String glosaCorreo, 
			String celular){
		
		this.rut=rut;
		this.rutDv=rutDv;
		this.nombres=nombres;
		this.apellidoPaterno=apellidoPaterno;
		this.apellidoMaterno=apellidoMaterno;
		this.correo=correo;
		this.glosaCorreo=glosaCorreo;
		this.celular=celular;

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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getGlosaCorreo() {
		return glosaCorreo;
	}
	public void setGlosaCorreo(String glosaCorreo) {
		this.glosaCorreo = glosaCorreo;
	}
	
}
