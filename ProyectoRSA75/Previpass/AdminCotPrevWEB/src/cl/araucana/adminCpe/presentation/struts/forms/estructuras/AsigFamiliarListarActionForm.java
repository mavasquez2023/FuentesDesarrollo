package cl.araucana.adminCpe.presentation.struts.forms.estructuras;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) AsigFamilarListarActionForm.java 1. 10/05/2009
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
public class AsigFamiliarListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private int id;
	private String nombre;
	private float rentaMinima;
	private float rentaMaxima;
	private int valorCarga;
	private List lista;
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
	public float getRentaMaxima() {
		return this.rentaMaxima;
	}
	/**
	 * renta maxima
	 * @param rentaMaxima
	 */
	public void setRentaMaxima(float rentaMaxima) {
		this.rentaMaxima = rentaMaxima;
	}
	/**
	 * renta minima
	 * @return
	 */
	public float getRentaMinima() {
		return this.rentaMinima;
	}
	/**
	 * renta minima
	 * @param rentaMinima
	 */
	public void setRentaMinima(float rentaMinima) {
		this.rentaMinima = rentaMinima;
	}
	/**
	 * valor carga
	 * @return
	 */
	public int getValorCarga() {
		return this.valorCarga;
	}
	/**
	 * valor carga
	 * @param valorCarga
	 */
	public void setValorCarga(int valorCarga) {
		this.valorCarga = valorCarga;
	}
	
	
}
