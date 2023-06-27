package cl.araucana.adminCpe.presentation.struts.forms.estructuras;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) MovPersonalEditarActionForm.java 1. 10/05/2009
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
public class MovPersonalEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int id;
	private String nombre;
	private int fechaTerminoObligatoria;
	private int fechaInicoObligatoria;
	private List lista;
	private boolean nuevoTipo;
	
	public int getFechaInicoObligatoria() {
		return fechaInicoObligatoria;
	}
	public void setFechaInicoObligatoria(int fechaInicoObligatoria) {
		this.fechaInicoObligatoria = fechaInicoObligatoria;
	}
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
	 * fecha termino obligatoria
	 * @return
	 */
	public int getFechaTerminoObligatoria() {
		return this.fechaTerminoObligatoria;
	}
	/**
	 * fecha termino obligatoria
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
