package cl.araucana.adminCpe.presentation.struts.forms.estructuras;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) MovPersonalAfEditarActionForm.java 1. 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.
 */
public class MovPersonalAfEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int id;
	private String nombre;
	private int fechaTerminoObligatoria;
	private int fechaInicioObligatoria;
	private List lista;
	private boolean nuevoTipo;
	/**
	 * nuevo tipo
	 * @return
	 */
	public boolean isNuevoTipo() {
		return this.nuevoTipo;
	}
	/**
	 * nuevo tipo
	 * @param nuevoTipo
	 */
	public void setNuevoTipo(boolean nuevoTipo) {
		this.nuevoTipo = nuevoTipo;
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
	/**
	 * fecha termino Obligatoria
	 * @return
	 */
	public int getFechaTerminoObligatoria() {
		return this.fechaTerminoObligatoria;
	}
	/**
	 * fecha termino obligatiria
	 * @param fechaTerminoObligatoria
	 */
	public void setFechaTerminoObligatoria(int fechaTerminoObligatoria) {
		this.fechaTerminoObligatoria = fechaTerminoObligatoria;
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
	 * lista
	 * @return
	 */
	public List getLista() {
		return this.lista;
	}
	/**
	 * lista
	 * @param lista
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}
	/**
	 * fecha inicio obligatoria
	 * @return
	 */
	public int getFechaInicioObligatoria() {
		return this.fechaInicioObligatoria;
	}
	/**
	 * fecha inicio obligatoria
	 * @param fechaInicioObligatoria
	 */
	public void setFechaInicioObligatoria(int fechaInicioObligatoria) {
		this.fechaInicioObligatoria = fechaInicioObligatoria;
	}

	}
