package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.hibernate.dao.ValidacionesDAO;
/*
* @(#) DispatcherMgr.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.5
 */
public class DispatcherMgr
{
	Session session;
	ValidacionesDAO validacionesDao;
	private NominaDAO nominaDao;

	public DispatcherMgr(Session session)
	{
		this.session = session;
		this.validacionesDao = new ValidacionesDAO(session);
		this.nominaDao = new NominaDAO(session);
	}
	/**
	 * lista validaciones
	 * @param tipoProceso
	 * @return
	 */
	public List getListaValidaciones(String tipoProceso)
	{
		try
		{
			return this.validacionesDao.getListaValidaciones(tipoProceso);
		} catch (Exception e)
		{
		}
		return new ArrayList();
	}
	/**
	 * lista conceptos
	 * @param tipoProceso
	 * @return
	 */
	public List getListaConceptos(String tipoProceso)
	{
		try
		{
			return this.validacionesDao.getListaConceptos(tipoProceso);
		} catch (Exception e)
		{
		}
		return new ArrayList();
	}
	/**
	 * lista mapeo
	 * @param listaGrupos
	 * @return
	 * @throws DaoException
	 */
	public List getListaMapeo(List listaGrupos) throws DaoException
	{
		return this.validacionesDao.getListaMapeo(listaGrupos);
	}
	/**
	 * lista grupos
	 * @param listaGrupos
	 * @return
	 * @throws DaoException
	 */
	public List getListaGrupos(Set listaGrupos) throws DaoException
	{
		return this.validacionesDao.getListaGrupos(listaGrupos);
	}
	/**
	 * parametro
	 * @param nombre
	 * @return
	 */
	public ParametroVO getParametro(int id)
	{
		try
		{
			return this.validacionesDao.getParametro(id);
		} catch (Exception e)
		{
		}
		return new ParametroVO();
	}

	public Collection getTiposNominas() throws DaoException 
	{
		return this.nominaDao.getTiposNominas();
	}
}
