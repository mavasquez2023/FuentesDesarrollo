package cl.araucana.template.ws.persistencia.vo;

public class PersonaVO {
	private int rut = 0;
	private String nombre = "";
	private String apellidoPaterno = "";
	private String apellidoMaterno = "";
	
	public PersonaVO(){}
	public PersonaVO(int _rut, String _nombre, String _apellidoPaterno, String _apellidoMaterno){
		rut = _rut;
		nombre = _nombre;
		apellidoPaterno = _apellidoPaterno;
		apellidoMaterno = _apellidoMaterno;
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRut() {
		return rut;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	
	public String toString(){
		return "Rut: "+rut +
		"\n Nombre: "+nombre +
		"\n ApellidoPaterno: "+apellidoPaterno +
		"\n ApellidoMaterno: "+apellidoMaterno; 
		
	}
	
	
}
