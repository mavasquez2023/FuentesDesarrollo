package cl.araucana.adminCpe.presentation.struts.forms.errores;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;
/*
* @(#) ErroresEditarActionForm.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ErroresEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;
	
	private boolean nuevoTipo;
	private int id;
	private String descripcion;
	private int error;
	private int corregible;

	/**
	 * corregible
	 * @return
	 */
	public int getCorregible() {
		return this.corregible;
	}

	/**
	 * correglible
	 * @param corregible
	 */
	public void setCorregible(int corregible) {
		this.corregible = corregible;
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

	/**
	 * error
	 * @return
	 */
	public int getError() {
		return this.error;
	}

	/**
	 * error
	 * @param error
	 */
	public void setError(int error) {
		this.error = error;
	}

	/**
	 * id
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * nuevo tipo
	 * @return
	 */
	public boolean getNuevoTipo() {
		return this.nuevoTipo;
	}

	/**
	 * nuevo tipo
	 * @param nuevoTipo
	 */
	public void setNuevoTipo(boolean nuevoTipo) {
		this.nuevoTipo = nuevoTipo;
	}
	
	}
