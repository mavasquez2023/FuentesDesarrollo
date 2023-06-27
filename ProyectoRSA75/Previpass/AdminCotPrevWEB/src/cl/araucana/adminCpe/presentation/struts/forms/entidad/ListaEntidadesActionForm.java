package cl.araucana.adminCpe.presentation.struts.forms.entidad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) listaEntidadesActionForm.java 1.1 10/05/2009
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
public class ListaEntidadesActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;
	private String opcGrupoConvenio;
	private List gruposConvenio;
	private List listaEntidadSalud, listaEntidadSil, listaEntidadFondoPension;
	private List listaEntidadApv, listaEntidadCcaf, listaEntidadAfc, listaEntidadExCaja, listaEntidadMutual;
	
	private int idMapaCod;
	
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
	 * puede esitar
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
	 * lista entidad afc
	 * @return
	 */
	public List getListaEntidadAfc() {
		return this.listaEntidadAfc;
	}

	/**
	 * lista entidad afc
	 * @param listaEntidadAfc
	 */
	public void setListaEntidadAfc(List listaEntidadAfc) {
		this.listaEntidadAfc = listaEntidadAfc;
	}

	/**
	 * lista entidad apv
	 * @return
	 */
	public List getListaEntidadApv() {
		return this.listaEntidadApv;
	}

	/**
	 * lista entidad apv
	 * @param listaEntidadApv
	 */
	public void setListaEntidadApv(List listaEntidadApv) {
		this.listaEntidadApv = listaEntidadApv;
	}

	/**
	 * lista entidad ccaf
	 * @return
	 */
	public List getListaEntidadCcaf() {
		return this.listaEntidadCcaf;
	}

	/**
	 * lista entidad ccaf
	 * @param listaEntidadCcaf
	 */
	public void setListaEntidadCcaf(List listaEntidadCcaf) {
		this.listaEntidadCcaf = listaEntidadCcaf;
	}

	/**
	 * lista entidad fondo pension
	 * @return
	 */
	public List getListaEntidadFondoPension() {
		return this.listaEntidadFondoPension;
	}

	/**
	 * lista entidad fondo pension
	 * @param listaEntidadFondoPension
	 */
	public void setListaEntidadFondoPension(List listaEntidadFondoPension) {
		this.listaEntidadFondoPension = listaEntidadFondoPension;
	}

	/**
	 * lista entidad mutual
	 * @return
	 */
	public List getListaEntidadMutual() {
		return this.listaEntidadMutual;
	}

	/**
	 * lista entidad mutual
	 * @param listaEntidadMutual
	 */
	public void setListaEntidadMutual(List listaEntidadMutual) {
		this.listaEntidadMutual = listaEntidadMutual;
	}

	/**
	 * lista entidad salud
	 * @return
	 */
	public List getListaEntidadSalud() {
		return this.listaEntidadSalud;
	}

	/**
	 * lista entidad salud
	 * @param listaEntidadSalud
	 */
	public void setListaEntidadSalud(List listaEntidadSalud) {
		this.listaEntidadSalud = listaEntidadSalud;
	}

	/**
	 * lista entidad sil
	 * @return
	 */
	public List getListaEntidadSil() {
		return this.listaEntidadSil;
	}

	/**
	 * lista entidad sil
	 * @param listaEntidadSil
	 */
	public void setListaEntidadSil(List listaEntidadSil) {
		this.listaEntidadSil = listaEntidadSil;
	}
}
