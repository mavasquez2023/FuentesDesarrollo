package cl.araucana.cp.presentation.struts.forms.nomina;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ListarActionForm.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author creyes
 * 
 * @version 1.2
 */
public class ListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 3707258065857718012L;

	private String rut;
	private String nombre;
	private String paterno;
	private String materno;
	private List listaCotizantes;

	private Collection numeroFilas;

	/**
	 * materno
	 * @return
	 */
	public String getMaterno()
	{
		return this.materno;
	}
	/**
	 * materno
	 * @param materno
	 */
	public void setMaterno(String materno)
	{
		this.materno = materno;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	/**
	 * paterno
	 * @return
	 */
	public String getPaterno()
	{
		return this.paterno;
	}
	/**
	 * paterno
	 * @param paterno
	 */
	public void setPaterno(String paterno)
	{
		this.paterno = paterno;
	}
	/**
	 * rut
	 * @return
	 */
	public String getRut()
	{
		return this.rut;
	}
	/**
	 * rut
	 * @param rut
	 */
	public void setRut(String rut)
	{
		this.rut = rut;
	}
	/**
	 * lista cotizantes
	 * @return
	 */
	public List getListaCotizantes()
	{
		return this.listaCotizantes;
	}
	/**
	 * ñista cotizantes
	 * @param listaCotizantes
	 */
	public void setListaCotizantes(List listaCotizantes)
	{
		this.listaCotizantes = listaCotizantes;
	}
	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas)
	{
		this.numeroFilas = numeroFilas;
	}
	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas()
	{
		return this.numeroFilas;
	}
}
