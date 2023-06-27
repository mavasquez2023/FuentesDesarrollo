package cl.araucana.adminCpe.logger.beans;
import java.util.List;
/*
* @(#) TipoEvento.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * Esta clase fue generada solo con el proposito de almacenar al inicio de la aplicaci�n los valores 
 * de las tablas de log (parametro, tipoParametro, etc) para la generacion del log. No pretendase usar para
 * otr cosa.
 * @author caroca
 * @author cllanos
 * 
 * @version 1.1
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
