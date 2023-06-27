package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionEntidadesRegImpActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class EdicionEntidadesRegImpActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private List lista;
	private String nombre;
	private int codigoEntidadAntiguo;
	private int idEntPagadora;
	private String codigoEntidad;
	private boolean nuevaEntidad;
	private String origen;
	private String origenAfp;
	private int idEntidadExCajaSeleccionada;
	
	private String descripcion;
	private float tasaPension;
	private int idEntExCaja;
	/**
	 * codigo entidad
	 * @return
	 */
	public String getCodigoEntidad() {
		return this.codigoEntidad;
	}
	/**
	 * codigo entidad
	 * @param codigoEntidad
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}
	/**
	 * codigo entidad antiguo
	 * @return
	 */
	public int getCodigoEntidadAntiguo() {
		return this.codigoEntidadAntiguo;
	}
	/**
	 * codigo entidad antiguo
	 * @param codigoEntidadAntiguo
	 */
	public void setCodigoEntidadAntiguo(int codigoEntidadAntiguo) {
		this.codigoEntidadAntiguo = codigoEntidadAntiguo;
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
	 * id entidad ex caja
	 * @return
	 */
	public int getIdEntExCaja() {
		return this.idEntExCaja;
	}
	/**
	 * id entidad ex caja
	 * @param idEntExCaja
	 */
	public void setIdEntExCaja(int idEntExCaja) {
		this.idEntExCaja = idEntExCaja;
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
	/**
	 * nueva entidad
	 * @return
	 */
	public boolean isNuevaEntidad() {
		return this.nuevaEntidad;
	}
	/**
	 * nueva entidad
	 * @param nuevaEntidad
	 */
	public void setNuevaEntidad(boolean nuevaEntidad) {
		this.nuevaEntidad = nuevaEntidad;
	}
	/**
	 * origen
	 * @return
	 */
	public String getOrigen() {
		return this.origen;
	}
	/**
	 * origen
	 * @param origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * origen afp
	 * @return
	 */
	public String getOrigenAfp() {
		return this.origenAfp;
	}
	/**
	 * origen afp
	 * @param origenAfp
	 */
	public void setOrigenAfp(String origenAfp) {
		this.origenAfp = origenAfp;
	}
	/**
	 * tasa pension
	 * @return
	 */
	public float getTasaPension() {
		return this.tasaPension;
	}
	/**
	 * tasa pension
	 * @param tasaPension
	 */
	public void setTasaPension(float tasaPension) {
		this.tasaPension = tasaPension;
	}	
	/**
	 * is entidad pagadora
	 * @return
	 */
	public int getIdEntPagadora() {
		return this.idEntPagadora;
	}
	/**
	 * id entidad pagadora
	 * @param idEntPagadora
	 */
	public void setIdEntPagadora(int idEntPagadora) {
		this.idEntPagadora = idEntPagadora;
	}
	/**
	 * id entidad pagadora ex caja seleccionada
	 * @return
	 */
	public int getIdEntidadExCajaSeleccionada() {
		return this.idEntidadExCajaSeleccionada;
	}
	/**
	 * id entidad pagadora es caja seleccionada
	 * @param idEntidadExCajaSeleccionada
	 */
	public void setIdEntidadExCajaSeleccionada(int idEntidadExCajaSeleccionada) {
		this.idEntidadExCajaSeleccionada = idEntidadExCajaSeleccionada;
	}
	
	
	}
