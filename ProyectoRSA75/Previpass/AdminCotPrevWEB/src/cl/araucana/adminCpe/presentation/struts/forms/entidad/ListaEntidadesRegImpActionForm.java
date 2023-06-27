package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) ListaEntidadesRegImpActionForm.java 1.1 10/05/2009
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
public class ListaEntidadesRegImpActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;
	private String opcGrupoConvenio;
	private List gruposConvenio;
	private List listaEntidadRegimenImpositivo;
	private int idMapaCod;
	private String origen;
	private String origenAfp;
	private int idEntidad;
	private int idEntPagadora;
	private int idExCaja;
	
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
	 * lista entidad regimen impositivo
	 * @return
	 */
	public List getListaEntidadRegimenImpositivo() {
		return this.listaEntidadRegimenImpositivo;
	}

	/**
	 * lista entidad regimen impositivo
	 * @param listaEntidadRegimenImpositivo
	 */
	public void setListaEntidadRegimenImpositivo(List listaEntidadRegimenImpositivo) {
		this.listaEntidadRegimenImpositivo = listaEntidadRegimenImpositivo;
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

	/**
	 * origen afp
	 * @return
	 */
	public String getOrigenAfp() {
		return this.origenAfp;
	}

	/**
	 * origen afp
	 * @param origenAfp
	 */
	public void setOrigenAfp(String origenAfp) {
		this.origenAfp = origenAfp;
	}

	/**
	 * id entidad
	 * @return
	 */
	public int getIdEntidad() {
		return this.idEntidad;
	}

	/**
	 * id entidad
	 * @param idEntidad
	 */
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * id entidad pagadora
	 * @return
	 */
	public int getIdEntPagadora() {
		return this.idEntPagadora;
	}

	/**
	 * id entidad pagadora
	 * @param idEntPagadora
	 */
	public void setIdEntPagadora(int idEntPagadora) {
		this.idEntPagadora = idEntPagadora;
	}

	/**
	 * id ex caja
	 * @return
	 */
	public int getIdExCaja() {
		return this.idExCaja;
	}

	/**
	 * id entidad ex caja
	 * @param idExCaja
	 */
	public void setIdExCaja(int idExCaja) {
		this.idExCaja = idExCaja;
	}

}
