package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.cp.distribuidor.base.Utils;

/*
* @(#) Trabajador.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class Trabajador implements Serializable, Comparable
{
	private static final long serialVersionUID = 5592741801352349560L;
	private String idCotizante;
	private String rut;
	private String nombre;
	private int idCaja;
	
	List empresas = new ArrayList();

	public Trabajador()
	{
		super();
	}
	/**
	 * trabajador
	 * @param idCotizante
	 */
	public Trabajador(String idCotizante)
	{
		super();
		this.idCotizante = idCotizante;
	}
	/**
	 * trabajador
	 * @param idCotizante
	 * @param rut
	 * @param nombre
	 * @param empresas
	 */
	public Trabajador(String idCotizante, String rut, String nombre, List empresas)
	{
		super();
		this.idCotizante = idCotizante;
		this.rut = rut;
		this.nombre = nombre;
		this.empresas = empresas;
	}
	/**
	 * empresas
	 * @return
	 */
	public List getEmpresas()
	{
		return this.empresas;
	}
	/**
	 * empresas
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}
	/**
	 * id cotizante
	 * @return
	 */
	public String getIdCotizante()
	{
		return this.idCotizante;
	}
	/**
	 * id cotizante
	 * @param idCotizante
	 */
	public void setIdCotizante(String idCotizante)
	{
		this.idCotizante = idCotizante;
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
	 * rut
	 * @return
	 */
	public String getRut()
	{
		return this.rut;
	}
	/**
	 * rut
	 * @param rut
	 */
	public void setRut(String rut)
	{
		this.rut = rut;
	}
	/**
	 * compara rut
	 */
	public int compareTo(Object o)
	{
		return (new Integer(Utils.desFormatRut(this.rut)).compareTo(new Integer(Utils.desFormatRut(((Trabajador) o).getRut()))));
	}
	public int getIdCaja()
	{
		return this.idCaja;
	}
	public void setIdCaja(int idCaja)
	{
		this.idCaja = idCaja;
	}
}