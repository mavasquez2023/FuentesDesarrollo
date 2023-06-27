package cl.araucana.cp.distribuidor.logger.beans;

/**
 * Esta clase fue generada solo con el proposito de almacenar al inicio de la aplicacion los valores 
 * de las tablas de log (parametro, tipoParametro, etc) para la generacion del log. No pretendase usar para
 * otr cosa.
 * @author caroca
 *
 */
public class Parametro
{
	private String id;
	private String nombre;
	private int largo;
	private int tipo;

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public int getTipo() 
	{
		return this.tipo;
	}
	public void setTipo(int tipo) 
	{
		this.tipo = tipo;
	}
	public String getNombre() 
	{
		return this.nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public int getLargo() 
	{
		return this.largo;
	}
	public void setLargo(int largo) 
	{
		this.largo = largo;
	}
}
