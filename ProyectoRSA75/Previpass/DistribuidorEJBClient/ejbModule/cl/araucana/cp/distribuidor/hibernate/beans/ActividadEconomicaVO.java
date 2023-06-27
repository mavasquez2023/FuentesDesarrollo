package cl.araucana.cp.distribuidor.hibernate.beans;

public class ActividadEconomicaVO
{
	private int idActividad;
	private String nombre;
	
	public int getIdActividad()
	{
		return this.idActividad;
	}
	
	public void setIdActividad(int idActividad)
	{
		this.idActividad = idActividad;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
}
