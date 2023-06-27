package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListaEntidadesExCajaActionForm.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.2
 */
public class ListaEntidadesExCajaActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;
	private String opcGrupoConvenio;
	private List gruposConvenio;
	private List listaEntidadExCaja;
	private int idMapaCod;
	private String origen;
	
	private Boolean puedeEditar;
	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.opcGrupoConvenio = null;
		this.gruposConvenio = null;
	}

	/**
	 * grupos convenio
	 * @return
	 */
	public List getGruposConvenio()
	{
		return this.gruposConvenio;
	}
	
	/**
	 * grupos convenio
	 * @param gruposConvenio
	 */
	public void setGruposConvenio(List gruposConvenio)
	{
		this.gruposConvenio = gruposConvenio;
	}

	/**
	 * id mapa codigo
	 * @return
	 */
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	/**
	 * id mapa codigo
	 * @param idMapaCod
	 */
	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	/**
	 * opc grupo convenio
	 * @return
	 */
	public String getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}

	/**
	 * opc grupo convenio
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	/**
	 * puede editar
	 * @return
	 */
	public Boolean getPuedeEditar()
	{
		return this.puedeEditar;
	}

	/**
	 * puede editar
	 * @param puedeEditar
	 */
	public void setPuedeEditar(Boolean puedeEditar)
	{
		this.puedeEditar = puedeEditar;
	}

	/**
	 * lista entidad ex caja
	 * @return
	 */
	public List getListaEntidadExCaja() {
		return this.listaEntidadExCaja;
	}

	/**
	 * lista entidad ex caja
	 * @param listaEntidadExCaja
	 */
	public void setListaEntidadExCaja(List listaEntidadExCaja) {
		this.listaEntidadExCaja = listaEntidadExCaja;
	}

	/**
	 * origen
	 * @return
	 */
	public String getOrigen() {
		return this.origen;
	}

	/**
	 * origen
	 * @param origen
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

}
