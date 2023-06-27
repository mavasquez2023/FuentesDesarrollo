package cl.araucana.cp.presentation.struts.forms.mapeo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.presentation.beans.LineaCodigoFicha;

/*
 * @(#) EdicionCodigosActionForm.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class EdicionCodigosActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -686638105844688820L;

	private List gruposConvenio;
	private String opcGrupoConvenio;
	private List tiposEdicion;
	private String opcTipoEdicion;
	private int idMapaCod;
	private List lista;
	private int numCodigos;

	/**
	 * numero codigos
	 * 
	 * @return
	 */
	public int getNumCodigos()
	{
		return this.numCodigos;
	}

	/**
	 * numero codigos
	 * 
	 * @param numCodigos
	 */
	public void setNumCodigos(int numCodigos)
	{
		this.numCodigos = numCodigos;
	}

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);

		this.gruposConvenio = null;
		this.tiposEdicion = null;
		this.opcGrupoConvenio = null;
		this.opcTipoEdicion = null;
		this.lista = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new LineaCodigoFicha();
			}
		});
	}

	/**
	 * grupos convenio
	 * 
	 * @return
	 */
	public List getGruposConvenio()
	{
		return this.gruposConvenio;
	}

	/**
	 * grupos convenio
	 * 
	 * @param gruposConvenio
	 */
	public void setGruposConvenio(List gruposConvenio)
	{
		this.gruposConvenio = gruposConvenio;
	}

	/**
	 * tipos edicion
	 * 
	 * @return
	 */
	public List getTiposEdicion()
	{
		return this.tiposEdicion;
	}

	/**
	 * tipos edicion
	 * 
	 * @param tiposEdicion
	 */
	public void setTiposEdicion(List tiposEdicion)
	{
		this.tiposEdicion = tiposEdicion;
	}

	/**
	 * lista
	 * 
	 * @return
	 */
	public List getLista()
	{
		return this.lista;
	}

	/**
	 * lista
	 * 
	 * @param lista
	 */
	public void setLista(List lista)
	{
		this.lista = lista;
	}

	/**
	 * id mapa codigo
	 * 
	 * @return
	 */
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	/**
	 * id mapa codigo
	 * 
	 * @param idMapaCod
	 */
	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	/**
	 * opc grupo convenio
	 * 
	 * @return
	 */
	public String getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}

	/**
	 * opc grupo convenio
	 * 
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	/**
	 * opc tipo edicion
	 * 
	 * @return
	 */
	public String getOpcTipoEdicion()
	{
		return this.opcTipoEdicion;
	}

	/**
	 * opc tipo edicion
	 * 
	 * @param opcTipoEdicion
	 */
	public void setOpcTipoEdicion(String opcTipoEdicion)
	{
		this.opcTipoEdicion = opcTipoEdicion;
	}
}
