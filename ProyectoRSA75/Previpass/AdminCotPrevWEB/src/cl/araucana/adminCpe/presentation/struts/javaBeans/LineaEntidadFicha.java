package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
/*
* @(#) Cotizacion.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author aacuna
 * 
 * @version 1.3
 */
public class LineaEntidadFicha implements Serializable
{
	private static final long serialVersionUID = 6975037787001979305L;

	private String tipoEdicion;
	private int idEntidad;
	private String idCodigo;
	private String nombre;
	private int idEntPagadora;
	private int idFoliacion;
	private int foliosEnUso;
	private int folioInicial;
	private int folioFinal;
	private int folioActual;
	private String tasaPension;
	private String descripcion;
	
	private EntidadPagadoraVO entidadPagadora;
	
		
	public LineaEntidadFicha() {}
	/**
	 * tipo edicion
	 * @return
	 */
	public String getTipoEdicion()
	{
		return this.tipoEdicion;
	}
	/**
	 * tipo edicion
	 * @param tipoEdicion
	 */
	public void setTipoEdicion(String tipoEdicion)
	{
		this.tipoEdicion = tipoEdicion;
	}
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
	 * id entidad pagadora
	 * @return
	 */
	public int getIdEntPagadora()
	{
		return this.idEntPagadora;
	}
	/**
	 * id entidad pagadora
	 * @param idEntPagadora
	 */
	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
	}	
	/**
	 * id foliacion
	 * @return
	 */
	public int getIdFoliacion()
	{
		return this.idFoliacion;
	}
	/**
	 * id foliacion
	 * @param idFoliacion
	 */
	public void setIdFoliacion(int idFoliacion)
	{
		this.idFoliacion = idFoliacion;
	}
	/**
	 * folios en uso
	 * @return
	 */
	public int getFoliosEnUso()
	{
		return this.foliosEnUso;
	}
	/**
	 * folios en uso
	 * @param foliosEnUso
	 */
	public void setFoliosEnUso(int foliosEnUso)
	{
		this.foliosEnUso = foliosEnUso;
	}	
	/**
	 * folio inicial
	 * @return
	 */
	public int getFolioInicial()
	{
		return this.folioInicial;
	}
	/**
	 * folio inicial
	 * @param folioInicial
	 */
	public void setFolioInicial(int folioInicial)
	{
		this.folioInicial = folioInicial;
	}
	/**
	 * folio final
	 * @return
	 */
	public int getFolioFinal()
	{
		return this.folioFinal;
	}
	/**
	 * folio final
	 * @param folioFinal
	 */
	public void setFolioFinal(int folioFinal)
	{
		this.folioFinal = folioFinal;
	}	
	/**
	 * folio actual
	 * @return
	 */
	public int getFolioActual()
	{
		return this.folioActual;
	}
	/**
	 * folio actual
	 * @param folioActual
	 */
	public void setFolioActual(int folioActual)
	{
		this.folioActual = folioActual;
	}
	/**
	 * entidad pagadora vo
	 * @return
	 */
	public EntidadPagadoraVO getEntidadPagadora()
	{
		return this.entidadPagadora;
	}
	/**
	 * entidad pagadora vo
	 * @param entidadPagadora
	 */
	public void setEntidadPagadora(EntidadPagadoraVO entidadPagadora)
	{
		this.entidadPagadora = entidadPagadora;
	}
	/**
	 * parametros ordenados
	 * @return
	 */
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();	
		parametros.put("1", String.valueOf(this.idEntidad));
		parametros.put("2", String.valueOf(this.idCodigo));
		parametros.put("3", String.valueOf(this.nombre));
		parametros.put("4", String.valueOf(this.idFoliacion));
		parametros.put("5", String.valueOf(this.idEntPagadora));
		parametros.put("6", String.valueOf(this.foliosEnUso));
		parametros.put("7", String.valueOf(this.folioInicial));
		parametros.put("8", String.valueOf(this.folioFinal));
		parametros.put("9", String.valueOf(this.folioActual));
		return parametros;
	}

	/**
	 * tasa pension
	 * @return
	 */
	public String getTasaPension() {
		return this.tasaPension;
	}
	/**
	 * tasa pension
	 * @param tasaPension
	 */
	public void setTasaPension(String tasaPension) {
		this.tasaPension = tasaPension;
	}
	/**
	 * descripcion
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * descripcion
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
