package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class AsigFamVO extends EntidadVO
{
	private static final long serialVersionUID = -471197082693126027L;
	private float rentaMinima;
	private float rentaMaxima;
	private int valorCarga;

	public AsigFamVO()
	{
		super();
	}

	public AsigFamVO(int id, String nombre, int rentaMinima, int rentaMaxima, int valorCarga)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.rentaMinima = rentaMinima;
		this.rentaMaxima = rentaMaxima;
		this.valorCarga = valorCarga;
	}

	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public float getRentaMaxima()
	{
		return this.rentaMaxima;
	}
	public void setRentaMaxima(float rentaMaxima)
	{
		this.rentaMaxima = rentaMaxima;
	}
	public float getRentaMinima()
	{
		return this.rentaMinima;
	}
	public void setRentaMinima(float rentaMinima)
	{
		this.rentaMinima = rentaMinima;
	}
	public int getValorCarga()
	{
		return this.valorCarga;
	}
	public void setValorCarga(int valorCarga)
	{
		this.valorCarga = valorCarga;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.nombre));
		parametros.put("3", String.valueOf(this.rentaMinima));
		parametros.put("4", String.valueOf(this.rentaMaxima));
		parametros.put("5", String.valueOf(this.valorCarga));
		return parametros;
	}
	
}
