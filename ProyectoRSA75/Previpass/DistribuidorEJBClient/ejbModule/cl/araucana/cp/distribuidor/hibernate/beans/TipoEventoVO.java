package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class TipoEventoVO implements Serializable{
	
	private static final long serialVersionUID = 5539286601115753558L;
	private int idTipoEvento;
	private int idCategoria;
	private String nombre;
	private String descripcion;
	

	public TipoEventoVO()
	{
		super();
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdTipoEvento() {
		return this.idTipoEvento;
	}

	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		
		return "TipoEventoVO[idTipoEvento: " + this.getIdTipoEvento() +"idCategoria: " + this.getIdCategoria() + ", descripcion: \"" + this.getDescripcion() + "\"]";
	}

}
