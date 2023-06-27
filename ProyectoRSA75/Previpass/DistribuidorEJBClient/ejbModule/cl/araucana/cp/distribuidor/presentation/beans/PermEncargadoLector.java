package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
/*
* @(#) PermEncargadoLector.java 1.1 10/05/2009
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
public class PermEncargadoLector implements Serializable, Comparable
{
	private static final long serialVersionUID = -7907875427325665773L;

	private int idConvenio;
	private int idNivel; //nivel acceso a convenio: depende de valores tabla
	private String nombreNivel;
	//permisos lector: si o no
	private boolean convenioLector;
	private String sucursalLector;
	/**
	 * Permiso Encargado lector
	 * @param idConvenio
	 * @param idNivel
	 * @param nombreNivel
	 * @param convenioLector
	 * @param sucursalLector
	 */
	public PermEncargadoLector(int idConvenio, int idNivel, String nombreNivel, boolean convenioLector, String sucursalLector) 
	{
		super();
		this.idConvenio = idConvenio;
		this.idNivel = idNivel;
		this.nombreNivel = nombreNivel;
		this.convenioLector = convenioLector;
		this.sucursalLector = sucursalLector;
	}

	public PermEncargadoLector() {}

	/**
	 * permiso encargado lector
	 * @param idConvenio
	 * @param idNivel
	 */
	public PermEncargadoLector(int idConvenio, int idNivel) 
	{
		super();
		this.idConvenio = idConvenio;
		this.idNivel = idNivel;
	}
	/**
	 * permiso encargado lector
	 * @param idConvenio
	 * @param idNivel
	 * @param nombre
	 */
	public PermEncargadoLector(int idConvenio, int idNivel, String nombre) 
	{
		super();
		this.idConvenio = idConvenio;
		this.idNivel = idNivel;
		this.nombreNivel = nombre;
	}
	/**
	 * id convenio
	 * @return
	 */
	public int getIdConvenio()
	{
		return this.idConvenio;
	}
	/**
	 * id convenio
	 * @param idConvenio
	 */
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	/**
	 * id nivel
	 * @return
	 */
	public int getIdNivel() 
	{
		return this.idNivel;
	}
	/**
	 * id nivel
	 * @param idNivel
	 */
	public void setIdNivel(int idNivel) 
	{
		this.idNivel = idNivel;
	}
	/**
	 * nombre nivel
	 * @return
	 */
	public String getNombreNivel() 
	{
		return this.nombreNivel;
	}
	/**
	 * nombre nivel
	 * @param nombre
	 */
	public void setNombreNivel(String nombre) 
	{
		this.nombreNivel = nombre;
	}
	/**
	 * convenio lector
	 * @return
	 */
	public boolean isConvenioLector() 
	{
		return this.convenioLector;
	}
	/**
	 * convenio lector
	 * @param convenioLector
	 */
	public void setConvenioLector(boolean convenioLector) 
	{
		this.convenioLector = convenioLector;
	}
	/**
	 * sucursal lector
	 * @return
	 */
	public String getSucursalLector() 
	{
		return this.sucursalLector;
	}
	/**
	 * sucursal lector
	 * @param sucursalLector
	 */
	public void setSucursalLector(String sucursalLector) 
	{
		this.sucursalLector = sucursalLector;
	}
	/**
	 * permiso id convenio
	 */
	public String toString() 
	{
		return "Permisos idConvenio:" + this.idConvenio + ", Nivel:" + this.idNivel + 
			":convenio:" + this.convenioLector + ":sucursales:" + this.sucursalLector + "::"; 
	}
	/**
	 * compara id convenio
	 */
	public int compareTo(Object o) 
	{
		return new Integer(this.idConvenio).compareTo(new Integer(((PermEncargadoLector) o).idConvenio));
	}
}
