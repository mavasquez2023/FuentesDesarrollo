package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EstadosDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.util.vo.DocumentoVO;

/*
 * @(#) NominaMgr.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author cchamblas
 * 
 * @version 1.7
 */
public class NominaMgr
{
	private EstadosDAO estadosDAO;
	private NominaDAO nominaDAO;

	public NominaMgr(Session session)
	{
		this.estadosDAO = new EstadosDAO(session);
		this.nominaDAO = new NominaDAO(session);
	}

	/**
	 * Retorna todos los estados de la nomina.
	 * 
	 * @return
	 * @throws DaoException
	 * @author malvarez
	 */
	public List getEstadosNomina() throws DaoException
	{
		List estados = this.estadosDAO.getEstados();
		return estados;
	}

	/**
	 * Devuelve Lista LabelValueBean para combo html.
	 * 
	 * @param estados
	 * @return
	 * @author malvarez
	 */
	public List getComboEstadosNomina(List estados)
	{
		List result = new ArrayList();

		// Combo grupos de convenios
		EstadoNominaVO estadoNomina;
		for (Iterator it = estados.iterator(); it.hasNext();)
		{
			estadoNomina = (EstadoNominaVO) it.next();
			result.add(new LabelValueBean(estadoNomina.getDescripcion(), Integer.toString(estadoNomina.getId())));
		}
		Collections.sort(result, LabelValueBean.CASE_INSENSITIVE_ORDER);
		return result;
	}

	/**
	 * lista combo estados nominas ordenados
	 * 
	 * @param estados
	 * @return
	 */
	public List getComboEstadosNominaOrdenados(List estados)
	{
		List result = new ArrayList();

		// Combo grupos de convenios
		EstadoNominaVO estadoNomina;
		for (Iterator it = estados.iterator(); it.hasNext();)
		{
			estadoNomina = (EstadoNominaVO) it.next();
			result.add(new LabelValueBean(estadoNomina.getDescripcion(), Integer.toString(estadoNomina.getId())));
		}
		return result;
	}

	/**
	 * Entrega los tipos de nomina
	 * 
	 * @return
	 * @throws DaoException
	 * @author malvarez
	 */
	public List getTiposNomina() throws DaoException
	{
		return this.nominaDAO.getTiposNominas();
	}

	/**
	 * Devuelve Lista de LabelValueBean para los TiposNomina
	 * 
	 * @param tipos
	 * @return
	 * @author malvarez
	 */
	public List getComboTiposNomina(List tipos)
	{
		List result = new ArrayList();

		// Combo grupos de convenios
		TipoNominaVO tipoNomina;
		for (Iterator it = tipos.iterator(); it.hasNext();)
		{
			tipoNomina = (TipoNominaVO) it.next();
			result.add(new LabelValueBean(tipoNomina.getDescripcion(), tipoNomina.getIdTipoNomina()));
		}
		Collections.sort(result, LabelValueBean.CASE_INSENSITIVE_ORDER);
		return result;
	}

	/**
	 * lista combos tipos de nominas ordenados
	 * 
	 * @param tipos
	 * @return
	 */
	public List getComboTiposNominaOrdenados(List tipos)
	{
		List result = new ArrayList();

		// Combo grupos de convenios
		TipoNominaVO tipoNomina;
		for (Iterator it = tipos.iterator(); it.hasNext();)
		{
			tipoNomina = (TipoNominaVO) it.next();
			result.add(new LabelValueBean(tipoNomina.getDescripcion(), tipoNomina.getIdTipoNomina()));
		}
		return result;
	}

	/**
	 * Devuelve las nominas correspondientes
	 * 
	 * @param tipoNomina
	 * @param rutEmpresa
	 * @param idGrupoConvenio
	 * @param estadoId
	 * @return
	 * @throws DaoException
	 */
	public List getNominasAdminNominas(String tipoNomina, int rutEmpresa, int idGrupoConvenio, int estadoId)
	{
		try
		{
			return this.nominaDAO.getNominaPorGrupo(rutEmpresa, idGrupoConvenio, estadoId, tipoNomina);
		} catch (Exception e)
		{
		}
		return new ArrayList();
	}

	/**
	 * documento extraer
	 * 
	 * @param empresa
	 * @param tipo
	 * @param convenio
	 * @return
	 * @throws DaoException
	 */
	public DocumentoVO extraeDocumento(int idEmpresa, String tipo, int idConvenio) throws DaoException
	{
		return this.nominaDAO.getLastDocumento(idEmpresa, tipo, idConvenio);
	}

	public NominaVO getNomina(String tipoNomina, long idEmpresa, int idConvenio) throws DaoException
	{
		return this.nominaDAO.getNomina(tipoNomina, idEmpresa, idConvenio);
	}

	public NominaVO getNomina(String tipoNomina, long idCodBarras) throws DaoException
	{
		return this.nominaDAO.getNomina(tipoNomina, idCodBarras);
	}
	
	public void borraCRCporGrupoConvenio(int idGrupoConvenio) throws DaoException {
		
		this.nominaDAO.borraCRCporGrupoConvenio(idGrupoConvenio);
	}
	
	/**
	 * Obtienen las nominas segun el tipo de empresa (EMPRESA o INDEPENDINTE)
	 * 
	 * @author gmallea
	 * 
	 * @param String tipoEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getTipoNomina(String tipoEmpresa)throws DaoException
	{
		return this.nominaDAO.getTipoNomina(tipoEmpresa);
	}
	
	/**
	 * Obtienen los codigos de barra segun el tipo de empresa (EMPRESA o INDEPENDINTE)
	 * 
	 * @author gmallea
	 * 
	 * @param String tipoEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getCodigoBarraByTipoEmpresa(String tipoEmpresa)throws DaoException
		{
			return this.nominaDAO.getCodigoBarraByTipoEmpresa(tipoEmpresa);
		}
}
