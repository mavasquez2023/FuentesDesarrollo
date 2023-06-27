package cl.araucana.adminCpe.presentation.struts.forms.banco;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ListaBancoActionForm.java 1.1 10/05/2009
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
public class ListaBancoActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private int idBanco;
	private String rutBanco;
	private String nombre;
	private String codSpl;
	private int estado;
	private List lista;
	
	/**
	 * codigo spl
	 * @return
	 */
	public String getCodSpl() {
		return this.codSpl;
	}
	/**
	 * codigo spl
	 * @param codSpl
	 */
	public void setCodSpl(String codSpl) {
		this.codSpl = codSpl;
	}
	/**
	 * estado
	 * @return
	 */
	public int getEstado() {
		return this.estado;
	}
	/**
	 * estado
	 * @param estado
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * id banco
	 * @return
	 */
	public int getIdBanco() {
		return this.idBanco;
	}
	/**
	 * id banco
	 * @param idBanco
	 */
	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
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
	 * rut banco
	 * @return
	 */
	public String getRutBanco() {
		return this.rutBanco;
	}
	/**
	 * rut banco
	 * @param rutBanco
	 */
	public void setRutBanco(String rutBanco) {
		this.rutBanco = rutBanco;
	}
	
}
