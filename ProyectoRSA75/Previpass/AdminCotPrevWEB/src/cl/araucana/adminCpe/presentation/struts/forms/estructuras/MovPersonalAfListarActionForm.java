package cl.araucana.adminCpe.presentation.struts.forms.estructuras;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) MovPersonalAfListarActionForm.java 1.1 10/05/2009
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
public class MovPersonalAfListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private int id;
	private String descripcion;
	private int fechaTerminoObligatoria;
	private int fechaInicioObligatoria;
	private List lista;
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
