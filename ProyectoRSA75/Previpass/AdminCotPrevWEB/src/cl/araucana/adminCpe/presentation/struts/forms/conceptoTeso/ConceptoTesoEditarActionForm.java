package cl.araucana.adminCpe.presentation.struts.forms.conceptoTeso;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;
/*
* @(#) ConceptoTesoEditarActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ConceptoTesoEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int id;
	private String descripcion;
	
	private int idActual;
	private String descripcionActual;
	
	private String accion;

	/**
	 * accion
	 * @return
	 */
	public String getAccion() {
		return this.accion;
	}

	/**
	 * accion
	 * @param accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	 * descripcion actual
	 * @return
	 */
	public String getDescripcionActual() {
		return this.descripcionActual;
	}

	/**
	 * descripcion actual
	 * @param descripcionActual
	 */
	public void setDescripcionActual(String descripcionActual) {
		this.descripcionActual = descripcionActual;
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
	 * id actual
	 * @return
	 */
	public int getIdActual() {
		return this.idActual;
	}

	/**
	 * id actual
	 * @param idActual
	 */
	public void setIdActual(int idActual) {
		this.idActual = idActual;
	}

	
}
