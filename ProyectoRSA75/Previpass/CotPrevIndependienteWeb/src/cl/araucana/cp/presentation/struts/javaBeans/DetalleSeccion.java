package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
import java.util.List;
/*
* @(#) DetalleSeccion.java 1.11 10/05/2009
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
public class DetalleSeccion implements Serializable
{
	private static final long serialVersionUID = 4582998694572479080L;
	private String idDetalleSeccion;
	private String nombre;
	private String total;
	private List listaDetalles;

	public DetalleSeccion()
	{
		super();
	}
	/**
	 * detalle seccion
	 * @param idDetalleSeccion
	 * @param nombre
	 */
	public DetalleSeccion(String idDetalleSeccion, String nombre)
	{
		super();
		this.idDetalleSeccion = idDetalleSeccion;
		this.nombre = nombre;
	}
	/**
	 * detalle seccion
	 * @param idDetalleSeccion
	 * @param nombre
	 * @param total
	 * @param detalles
	 */
	public DetalleSeccion(String idDetalleSeccion, String nombre, String total, List detalles)
	{
		super();
		this.idDetalleSeccion = idDetalleSeccion;
		this.nombre = nombre;
		this.total = total;
		this.listaDetalles = detalles;
	}
	/**
	 * lista detalles
	 * @return
	 */
	public List getListaDetalles()
	{
		return this.listaDetalles;
	}
	/**
	 * lista detalles
	 * @param detalles
	 */
	public void setListaDetalles(List detalles)
	{
		this.listaDetalles = detalles;
	}
	/**
	 * id detalle seccion
	 * @return
	 */
	public String getIdDetalleSeccion()
	{
		return this.idDetalleSeccion;
	}
	/**
	 * id detalle seccion
	 * @param idDetalleSeccion
	 */
	public void setIdDetalleSeccion(String idDetalleSeccion)
	{
		this.idDetalleSeccion = idDetalleSeccion;
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
