package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) PermisoEncargado.java 1.1 10/05/2009
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
public class PermisosEncargado implements Serializable, Comparable
{
	private static final long serialVersionUID = -7907875427325665773L;

	private int idConvenio;
	private int idNivel;
	private String nombre;

	public PermisosEncargado() {}

	/**
	 * permisos encargado
	 * @param idConvenio
	 * @param idNivel
	 */
	public PermisosEncargado(int idConvenio, int idNivel) 
	{
		super();
		this.idConvenio = idConvenio;
		this.idNivel = idNivel;
	}
	/**
	 * permisos encargado
	 * @param idConvenio
	 * @param idNivel
	 * @param nombre
	 */
	public PermisosEncargado(int idConvenio, int idNivel, String nombre) 
	{
		super();
		this.idConvenio = idConvenio;
		this.idNivel = idNivel;
		this.nombre = nombre;
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
	public int getIdNivel() {
		return this.idNivel;
	}
	/**
	 * id nivel
	 * @param idNivel
	 */
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	/**
	 * permiso encargado id convenio
	 */
	public String toString() 
	{
		return "PermisosEncargado[idConvenio:" + this.idConvenio + ", Nivel:" + this.idNivel + ":"; 
	}
	/**
	 * compara id convenio
	 */
	public int compareTo(Object o) 
	{
		return new Integer(this.idConvenio).compareTo(new Integer(((PermisosEncargado) o).idConvenio));
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
