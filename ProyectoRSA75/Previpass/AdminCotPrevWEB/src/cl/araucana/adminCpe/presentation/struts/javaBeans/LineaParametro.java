package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) LineaParametro.java 1.1 10/05/2009
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
public class LineaParametro implements Serializable
{
	private static final long serialVersionUID = -1;
	
	private int id;
	private String nombre;
	private String descripcion;
	private String valor;
	private String tipoParametro;
	private String tipoValor;
	
	public LineaParametro() {
		super();
	}

	/**
	 * @return this.el descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion el descripcion a establecer
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return this.el id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id el id a establecer
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return this.el nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre el nombre a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return this.el tipoParametro
	 */
	public String getTipoParametro() {
		return this.tipoParametro;
	}

	/**
	 * @param tipoParametro el tipoParametro a establecer
	 */
	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	/**
	 * @return this.el tipoValor
	 */
	public String getTipoValor() {
		return this.tipoValor;
	}

	/**
	 * @param tipoValor el tipoValor a establecer
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	/**
	 * @return this.el valor
	 */
	public String getValor() {
		return this.valor;
	}

	/**
	 * @param valor el valor a establecer
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
