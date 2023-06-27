package cl.araucana.cp.distribuidor.logger.beans;

import java.util.List;

/**
 * Esta clase fue generada solo con el proposito de almacenar al inicio de la aplicacion los valores 
 * de las tablas de log (parametro, tipoParametro, etc) para la generacion del log. No pretendase usar para
 * otr cosa.
 * @author caroca
 *
 */
public class TipoEvento 
{
	private int id;
	private String nombre;
	private List Parametros;

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
	public List getParametros() 
	{
		return this.Parametros;
	}
	public void setParametros(List parametros) 
	{
		this.Parametros = parametros;
	}	
}
