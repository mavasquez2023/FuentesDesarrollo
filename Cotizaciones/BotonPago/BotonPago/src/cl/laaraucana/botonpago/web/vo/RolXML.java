package cl.laaraucana.botonpago.web.vo;

import java.util.ArrayList;

public class RolXML {
	private String nombre;
	private ArrayList<String> opcion;

	public RolXML() {
	}

	public RolXML(String nombre, ArrayList<String> opcion) {
		super();
		this.nombre = nombre;
		this.opcion = opcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getOpcion() {
		return opcion;
	}

	public void setOpcion(ArrayList<String> opcion) {
		this.opcion = opcion;
	}

}
