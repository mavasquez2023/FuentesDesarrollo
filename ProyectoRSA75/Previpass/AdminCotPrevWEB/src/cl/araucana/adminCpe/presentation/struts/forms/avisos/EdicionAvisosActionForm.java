package cl.araucana.adminCpe.presentation.struts.forms.avisos;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionAvisosActionForm.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author vagurto
 * @author malvarez
 * 
 * @version 1.3
 */
public class EdicionAvisosActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int idAvisos;
	private String titulo;
	private String estado;
	private String contenido;
	private String link;
	private int ancho;
	private int alto;
	
	/**
	 *  contenido
	 * @return
	 */
	public String getContenido() {
		return this.contenido;
	}
	/**
	 * contenido
	 * @param contenido
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	/**
	 * estado
	 * @return
	 */
	public String getEstado() {
		return this.estado;
	}
	/**
	 * estado
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Id Avisos
	 * @return
	 */
	public int getIdAvisos() {
		return this.idAvisos;
	}
	/**
	 * Id Avisos
	 * @param idAvisos
	 */
	public void setIdAvisos(int idAvisos) {
		this.idAvisos = idAvisos;
	}
	/**
	 * link
	 * @return
	 */
	public String getLink() {
		return this.link;
	}
	/**
	 * link
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * alto
	 * @return
	 */
	public int getAlto() {
		return this.alto;
	}
	/**
	 * alto
	 * @param alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}
	/**
	 * ancho
	 * @return
	 */
	public int getAncho() {
		return this.ancho;
	}
	/**
	 * ancho
	 * @param ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	/**
	 * titulo
	 * @return
	 */
	public String getTitulo() {
		return this.titulo;
	}
	/**
	 * titulo
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
		
}
