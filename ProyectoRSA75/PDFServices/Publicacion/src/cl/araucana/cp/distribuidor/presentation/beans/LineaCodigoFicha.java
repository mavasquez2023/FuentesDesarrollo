package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
/*
* @(#) LineaCodigoFicha.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
 */
public class LineaCodigoFicha implements Serializable, Comparable
{
	private static final long serialVersionUID = 6975037787001979305L;

	private int idMapaCod;
	private int idEntidad;
	private String idCodigo;
	private String idCodigoNew;
	private String nombre;
	
	public LineaCodigoFicha() {}
	
	/**
	 * id codigo
	 * @return
	 */
	public String getIdCodigo()
	{
		return this.idCodigo;
	}
	/**
	 * id codigo
	 * @param idCodigo
	 */
	public void setIdCodigo(String idCodigo)
	{
		this.idCodigo = idCodigo;
	}
	/**
	 * id codigo nuevo
	 * @return
	 */
	public String getIdCodigoNew()
	{
		return this.idCodigoNew;
	}
	/**
	 * id codigo nuevo
	 * @param idCodigoNew
	 */
	public void setIdCodigoNew(String idCodigoNew)
	{
		this.idCodigoNew = idCodigoNew;
	}
	/**
	 * id entidad
	 * @return
	 */
	public int getIdEntidad()
	{
		return this.idEntidad;
	}
	/**
	 * id entidad
	 * @param idEntidad
	 */
	public void setIdEntidad(int idEntidad)
	{
		this.idEntidad = idEntidad;
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	/**
	 * id mapa codigo
	 * @return
	 */
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}
	/**
	 * id mapa codigo
	 * @param idMapaCod
	 */
	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}
	/**
	 * devuelve linea codigo ficha
	 */
	public String toString() 
	{
		return "LineaCodigoFicha[idCodigo: \"" + this.idCodigo + "\", nombre: \"" + this.nombre + "\", idEntidad: " + this.idEntidad
			+ ", idMapaCod: " + this.idMapaCod + ", idCodigoNew: " + this.idCodigoNew + "]";
	}
	/**
	 * compara entidad id linea con codigo id linea
	 */
	public int compareTo(Object o) 
	{
		return this.nombre.compareToIgnoreCase(((LineaCodigoFicha) o).nombre);
	}
}
