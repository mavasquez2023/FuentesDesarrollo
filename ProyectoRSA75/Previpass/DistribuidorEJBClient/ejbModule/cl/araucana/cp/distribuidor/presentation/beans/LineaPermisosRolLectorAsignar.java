package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
/*
* @(#) LineaPermisosRolLectorAsignar.java 1.1 10/05/2009
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
public class LineaPermisosRolLectorAsignar implements Serializable
{
	private static final long serialVersionUID = -7907875427325665773L;

	private String tipo;
	private String codigo;
	private String descripcion;
	private String existe;
	private String tieneSucursal;
	/**
	 * tiene sucursal
	 * @return
	 */
	public String getTieneSucursal() {
		return this.tieneSucursal;
	}
	/**
	 * tiene sucursal
	 * @param tieneSucursal
	 */
	public void setTieneSucursal(String tieneSucursal) {
		this.tieneSucursal = tieneSucursal;
	}
	/**
	 * existe
	 * @return
	 */
	public String getExiste() {
		return this.existe;
	}
	/**
	 * existe
	 * @param existe
	 */
	public void setExiste(String existe) {
		this.existe = existe;
	}
	/**
	 * codigo
	 * @return
	 */
	public String getCodigo() {
		return this.codigo;
	}
	/**
	 * codigo
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * tipo
	 * @return
	 */
	public String getTipo() {
		return this.tipo;
	}
	/**
	 * tipo
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
