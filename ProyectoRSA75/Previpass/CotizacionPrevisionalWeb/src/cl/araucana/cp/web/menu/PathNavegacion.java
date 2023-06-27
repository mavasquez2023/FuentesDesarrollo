package cl.araucana.cp.web.menu;

import java.io.Serializable;

public class PathNavegacion implements Serializable 
{
	private static final long serialVersionUID = 6809861335312869424L;
	private String nombre;
	private String url;
	private String clave;
	private boolean actual;

	public PathNavegacion(String nombre, String url, String clave, boolean actual) 
	{
		super();
		this.nombre = nombre.trim();
		this.url = url;
		this.clave = clave;
		this.actual = actual;
	}
	
	public PathNavegacion() 
	{
		super();
	}

	public boolean isActual() 
	{
		return this.actual;
	}

	public void setActual(boolean actual) 
	{
		this.actual = actual;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getUrl() 
	{
		return this.url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String toString()
	{
		return "nombre:" + this.nombre + ":url:" + this.url + ":clave:" + this.clave + ":actual:" + this.actual + ":";
	}
}
