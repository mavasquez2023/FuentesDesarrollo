package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
import java.util.List;
/*
* @(#) Seccion.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class Seccion implements Serializable
{
	private static final long serialVersionUID = -5929454768925138184L;
	private String idTipoSeccion;
	private String nombre;
	private String total;
	private List detalles;

	public Seccion()
	{
		super();
	}
	/**
	 * seccion
	 * @param idTipoSeccion
	 * @param nombre
	 */
	public Seccion(String idTipoSeccion, String nombre)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.nombre = nombre;
	}
	/**
	 * seccion
	 * @param idTipoSeccion
	 * @param nombre
	 * @param total
	 * @param detalles
	 */
	public Seccion(String idTipoSeccion, String nombre, String total, List detalles)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.nombre = nombre;
		this.total = total;
		this.detalles = detalles;
	}
	/**
	 * detalles
	 * @return
	 */
	public List getDetalles()
	{
		return this.detalles;
	}
	/**
	 * detalles
	 * @param detalles
	 */
	public void setDetalles(List detalles)
	{
		this.detalles = detalles;
	}
	/**
	 * id tipo seccion
	 * @return
	 */
	public String getIdTipoSeccion()
	{
		return this.idTipoSeccion;
	}
	/**
	 * id tipo seccion
	 * @param idTipoSeccion
	 */
	public void setIdTipoSeccion(String idTipoSeccion)
	{
		this.idTipoSeccion = idTipoSeccion;
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
	 * total
	 * @return
	 */
	public String getTotal()
	{
		return this.total;
	}
	/**
	 * total
	 * @param total
	 */
	public void setTotal(String total)
	{
		this.total = total;
	}
}
