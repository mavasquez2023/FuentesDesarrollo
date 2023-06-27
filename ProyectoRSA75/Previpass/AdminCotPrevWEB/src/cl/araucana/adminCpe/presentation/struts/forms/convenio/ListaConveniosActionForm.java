package cl.araucana.adminCpe.presentation.struts.forms.convenio;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ListaConveniosActionForm.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
 */
public class ListaConveniosActionForm extends ActionForm
{
	private static final long serialVersionUID = -1932471197406340879L;

	private String rutEmpresaFmt;
	private int rutEmpresa;
	private String nombreEmpresa;
	private List consulta;

	private Collection numeroFilas; 
	
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
	 * nombre empresa
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}
	/**
	 * nombre empresa
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * rut empresa
	 * @return
	 */
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * rut empresa fmt
	 * @return
	 */
	public String getRutEmpresaFmt()
	{
		return this.rutEmpresaFmt;
	}
	/**
	 * rut empresa fmt
	 * @param rutEmpresaFmt
	 */
	public void setRutEmpresaFmt(String rutEmpresaFmt)
	{
		this.rutEmpresaFmt = rutEmpresaFmt;
	}
	/**
	 * collection numero filas
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
}
