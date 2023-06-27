package cl.araucana.cp.presentation.struts.forms;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListarEmpresasActionForm.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * @author csanchez
 * 
 * @version 1.5
 */
public class ListarEmpresasActionForm extends ActionForm
{
	private static final long serialVersionUID = -5585015647754230297L;
	private String rutEmpresa;
	private String dvRutEmpresa;
	private String razonSocial;
	private String tipoProceso;

	private String opcGrupoConvenio;
	private String nombreGrupoConvenio;
	private List   gruposConvenio;

	private List consulta;
	private Collection numeroFilas;
	private boolean esAdminEmpresa;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.consulta = null;
		
		this.opcGrupoConvenio = null;
		this.gruposConvenio = null;
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
	public Collection getNumeroFilas()
	{
		return this.numeroFilas;
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
	 * es admin empresa
	 * @return
	 */
	public boolean isEsAdminEmpresa() 
	{
		return this.esAdminEmpresa;
	}
	/**
	 * es admin empresa
	 * @param esAdminEmpresa
	 */
	public void setEsAdminEmpresa(boolean esAdminEmpresa) 
	{
		this.esAdminEmpresa = esAdminEmpresa;
	}
	public String getDvRutEmpresa()
	{
		return dvRutEmpresa;
	}
	public void setDvRutEmpresa(String dvRutEmpresa)
	{
		this.dvRutEmpresa = dvRutEmpresa;
	}
	public String getRazonSocial()
	{
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}
	public String getRutEmpresa()
	{
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public String getTipoProceso()
	{
		return tipoProceso;
	}
	public void setTipoProceso(String tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}
	public List getGruposConvenio()
	{
		return gruposConvenio;
	}
	public void setGruposConvenio(List gruposConvenio)
	{
		this.gruposConvenio = gruposConvenio;
	}
	public String getNombreGrupoConvenio()
	{
		return nombreGrupoConvenio;
	}
	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}
	public String getOpcGrupoConvenio()
	{
		return opcGrupoConvenio;
	}
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

}
