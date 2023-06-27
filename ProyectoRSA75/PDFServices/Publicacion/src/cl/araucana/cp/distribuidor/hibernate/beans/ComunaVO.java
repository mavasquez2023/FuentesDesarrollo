package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class ComunaVO extends AuditableVO
{
	private static final long serialVersionUID = 3127079531660258762L;

	private int idComuna;
	private CiudadVO ciudad;
	private String nombre;
	
	public ComunaVO() {}
	
	public ComunaVO(int idComuna)
	{
		super();
		this.idComuna = idComuna;
	}

	public CiudadVO getCiudad()
	{
		return this.ciudad;
	}
	
	public void setCiudad(CiudadVO ciudad)
	{
		this.ciudad = ciudad;
	}
	
	public int getIdComuna()
	{
		return this.idComuna;
	}
	
	public void setIdComuna(int idComuna)
	{
		this.idComuna = idComuna;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String toString() {
		return "ComunaVO[idComuna: " + this.idComuna + ", nombre: \"" + this.nombre + "\"]";
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		
		parametros.put("1", String.valueOf(this.idComuna));
		parametros.put("2", String.valueOf(this.ciudad.getIdCiudad()));
		parametros.put("3", String.valueOf(this.nombre));
		
		return parametros;
		
	}
}
