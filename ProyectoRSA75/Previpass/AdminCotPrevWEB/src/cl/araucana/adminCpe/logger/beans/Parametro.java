package cl.araucana.adminCpe.logger.beans;

/*
* @(#) Parametro.java 1.1 10/05/2009
*
* Este c&oacute;digo fuente pertenece a la Caja de Compensaci&oacute;n de Asignaci&oacute;n Familiar
* La Araucana (C.C.A.F.). Su utilizaci&oacute;n y reproducci&oacute;n es confidencial y está
* restringido a los sistemas de informaci&oacute;n propios de la instituci&oacute;n.
*/
/**
 * Esta clase fue generada solo con el proposito de almacenar al inicio de la aplicaci&oacute;n los valores 
 * de las tablas de log (parametro, tipoParametro, etc) para la generacion del log. No pretendase usar para
 * otra cosa.
 * @author caroca
 * @author cchamblas
 *
 *@version 1.1
 */
public class Parametro
{
	private String id;
	
	public String getId() 
	{
		return this.id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	private String nombre;
	private int largo;
	private int tipo;

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
