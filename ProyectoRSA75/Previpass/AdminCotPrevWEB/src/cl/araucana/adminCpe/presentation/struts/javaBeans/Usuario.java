package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
/*
* @(#) Usuario.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class Usuario implements Serializable, Comparable
{
	private static final long serialVersionUID = 8275448423390826488L;
	private int rut;
	private String rutFormat;
	private String nombre;
	private String apellidos;

	List asignaciones = new ArrayList();

	public Usuario() 
	{
		super();
	}

	/**
	 * usuario
	 * @param rut
	 * @param rutFormat
	 * @param nombre
	 * @param apellidos
	 */
	public Usuario(int rut, String rutFormat, String nombre, String apellidos) 
	{
		super();
		this.rut = rut;
		this.rutFormat = rutFormat;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	/**
	 * usuario
	 * @param rut
	 * @param rutFormat
	 */
	public Usuario(int rut, String rutFormat) 
	{
		this.rut = rut;
		this.rutFormat = rutFormat;
	}
	/**
	 * apellidos
	 * @return
	 */
	public String getApellidos() 
	{
		return this.apellidos;
	}
	/**
	 * apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
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
	public int getRut() 
	{
		return this.rut;
	}
	/**
	 * rut
	 * @param rut
	 */
	public void setRut(int rut) 
	{
		this.rut = rut;
	}
	/**
	 * rut format
	 * @return
	 */
	public String getRutFormat() 
	{
		return this.rutFormat;
	}
	/**
	 * rut format
	 * @param rutFormat
	 */
	public void setRutFormat(String rutFormat) 
	{
		this.rutFormat = rutFormat;
	}
	/**
	 * asignaciones
	 * @return
	 */
	public List getAsignaciones() {
		return this.asignaciones;
	}
	/**
	 * asignaciones
	 * @param asignaciones
	 */
	public void setAsignaciones(List asignaciones) {
		this.asignaciones = asignaciones;
	}
	/**
	 * grupo convenio
	 * @param idGrupo
	 * @return
	 */
	public GrupoConvenio getGrupoConvenio(int idGrupo)
	{
		for (Iterator it = this.asignaciones.iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio)it.next();
			if (idGrupo == gc.getIdGrupo())
				return gc;
		}
		return null;
	}
	/**
	 * agrega grupo convenio
	 * @param grupo
	 */
	public void addGrupoConvenio(GrupoConvenio grupo)
	{
		this.asignaciones.add(grupo);
	}
	/**
	 * compara rut con usuario rut
	 */
	public int compareTo(Object o) 
	{
		return new Integer(this.rut).compareTo(new Integer(((Usuario)o).rut));
	}
}
