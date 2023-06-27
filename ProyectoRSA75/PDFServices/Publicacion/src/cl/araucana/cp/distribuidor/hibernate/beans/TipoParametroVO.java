package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class TipoParametroVO implements Serializable {
	
	private static final long serialVersionUID = -5539286601115753558L;
	private int idTipoParametro;
	private String nombre;
	private String descripcion;
	
	public TipoParametroVO()
	{
		super();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdTipoParametro() {
		return this.idTipoParametro;
	}

	public void setIdTipoParametro(int idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		
		return "TipoParametroVO[idTipoParametro: " + this.getIdTipoParametro() +", idNombre: " + this.getNombre() + ", Descripcion: " + this.getDescripcion()+ "\"]";
	}	

}
