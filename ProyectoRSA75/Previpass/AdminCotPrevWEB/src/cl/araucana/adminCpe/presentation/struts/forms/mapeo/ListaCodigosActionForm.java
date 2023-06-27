package cl.araucana.adminCpe.presentation.struts.forms.mapeo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#)ListaCodigosActionForm.java 1.3 10/05/2009
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
public class ListaCodigosActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;
	private String opcGrupoConvenio;
	private List gruposConvenio;
	private List listaAFP, listaISAPRE, listaAPV, listMvtoPer, listMvtoPerAF, listaGenero, listaTramo;
	private int idMapaCod;
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
	 * lista afp
	 * @return
	 */
	public List getListaAFP()
	{
		return this.listaAFP;
	}

	/**
	 * lista afp
	 * @param listaAFP
	 */
	public void setListaAFP(List listaAFP)
	{
		this.listaAFP = listaAFP;
	}

	/** 
	 * lista apv
	 * @return
	 */
	public List getListaAPV()
	{
		return this.listaAPV;
	}

	/**
	 * lista apv
	 * @param listaAPV
	 */
	public void setListaAPV(List listaAPV)
	{
		this.listaAPV = listaAPV;
	}

	/**
	 * lista genero
	 * @return
	 */
	public List getListaGenero()
	{
		return this.listaGenero;
	}

	/**
	 * lista genero
	 * @param listaGenero
	 */
	public void setListaGenero(List listaGenero)
	{
		this.listaGenero = listaGenero;
	}

	/**
	 * lista isapre
	 * @return
	 */
	public List getListaISAPRE()
	{
		return this.listaISAPRE;
	}

	/**
	 * lista isapre
	 * @param listaISAPRE
	 */
	public void setListaISAPRE(List listaISAPRE)
	{
		this.listaISAPRE = listaISAPRE;
	}

	/**
	 * lista tramo
	 * @return
	 */
	public List getListaTramo()
	{
		return this.listaTramo;
	}

	/**
	 * lista tramo
	 * @param listaTramo
	 */
	public void setListaTramo(List listaTramo)
	{
		this.listaTramo = listaTramo;
	}

	/**
	 * lista movimiento personas
	 * @return
	 */
	public List getListMvtoPer()
	{
		return this.listMvtoPer;
	}

	/**
	 * lista movimiento personas
	 * @param listMvtoPer
	 */
	public void setListMvtoPer(List listMvtoPer)
	{
		this.listMvtoPer = listMvtoPer;
	}

	/**
	 * id mapa cod
	 * @return
	 */
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	/**
	 * id mapa cod
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
	 * lista movimiento personas af
	 * @return
	 */
	public List getListMvtoPerAF() {
		return this.listMvtoPerAF;
	}

	/**
	 * lista movimiento personas af
	 * @param listMvtoPerAF
	 */
	public void setListMvtoPerAF(List listMvtoPerAF) {
		this.listMvtoPerAF = listMvtoPerAF;
	}

}
