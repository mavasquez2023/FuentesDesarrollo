package cl.araucana.adminCpe.presentation.struts.forms.banco;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionBancoActionForm.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado	
 * @author aacuña
 * 
 * @version 1.2
 */
public class EdicionBancoActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private int idBanco;
	private String rutBanco;
	private String digitoRut;
	private String nombre;
	private String codSpl;
	private int estado;
	private List listaSpl;
	private boolean nuevoTipo;
	private List lista;
	
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
	 * lista spl
	 * @return
	 */
	public List getListaSpl() {
		return this.listaSpl;
	}
	/**
	 * lista spl
	 * @param listaSpl
	 */
	public void setListaSpl(List listaSpl) {
		this.listaSpl = listaSpl;
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
	 * digito verificador
	 * @return
	 */
	public String getDigitoRut() 
	{
		if(this.digitoRut.length() > 0)
			return "-" + this.digitoRut;
		return "";
	}
	/**
	 * digito verificador
	 * @param digitoRut
	 */
	public void setDigitoRut(String digitoRut) {
		this.digitoRut = digitoRut;
	}
		
}
