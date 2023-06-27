package cl.araucana.adminCpe.presentation.struts.forms.estructuras;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#)AsigFamiliarEditarActionForm.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.2
 */
public class AsigFamiliarEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int id;
	private String nombre;
	private String rentaMinima;
	private String rentaMaxima;
	private String valorCarga;
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
	 * renta maxima
	 * @return
	 */
	public String getRentaMaxima() {
		return this.rentaMaxima;
	}
	/**
	 * renta maxima
	 * @param rentaMaxima
	 */
	public void setRentaMaxima(String rentaMaxima) {
		this.rentaMaxima = rentaMaxima;
	}
	/**
	 * renta minima
	 * @return
	 */
	public String getRentaMinima() {
		return this.rentaMinima;
	}
	/**
	 * rrenta minima
	 * @param rentaMinima
	 */
	public void setRentaMinima(String rentaMinima) {
		this.rentaMinima = rentaMinima;
	}
	/**
	 * valor carga
	 * @return
	 */
	public String getValorCarga() {
		return this.valorCarga;
	}
	/**
	 * valor carga
	 * @param valorCarga
	 */
	public void setValorCarga(String valorCarga) {
		this.valorCarga = valorCarga;
	}

	}
