package main.java.com.pruebas.clases;

public class Persona {
	
	private String nombre;
	private String edad;
	
	public Persona(String nombre,String edad){
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public Persona(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.nombre + " Edad: " + this.edad;
	}

}
