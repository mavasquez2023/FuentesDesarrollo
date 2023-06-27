package cl.araucana.cp.presentation.struts.forms.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListarUsuariosActionForm.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @authorcchamblas
 * 
 * @version 1.4
 */
public class ListarUsuariosActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -5355975351170549992L;

	private String rut;
	private String nombre;
	private String apellidos;
	
	private List consulta;
	private Collection numeroFilas;

	/**
	 * validate
	 */
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) 
	{
		return super.validate(arg0, arg1);
	}
	/**
	 * reset
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
		this.consulta = new ArrayList();		
		super.reset(arg0, arg1);
	}
	/**
	 * consulta
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}
	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}
	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}
	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
	public String getApellidos()
	{
		return apellidos;
	}
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getRut()
	{
		return rut;
	}
	public void setRut(String rut)
	{
		this.rut = rut;
	}
}