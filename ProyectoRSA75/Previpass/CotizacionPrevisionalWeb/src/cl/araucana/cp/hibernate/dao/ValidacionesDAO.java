package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) ValidacionesDao.java 1.12 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.12
 */
public class ValidacionesDAO 
{
	private static Logger log = Logger.getLogger(ValidacionesDAO.class);
    Session session;

    public ValidacionesDAO(Session session) 
    {
        this.session = session;
    }
    /**
     * lista validaciones
     * @param tipoProceso
     * @return
     * @throws DaoException
     */
	public List getListaValidaciones(String tipoProceso) throws DaoException
	{
    	log.info("ValidacionesDAO:getListaValidaciones:" + tipoProceso);
    	try 
        {
    		Criteria crit = this.session.createCriteria(ValidacionVO.class);
        	crit.add(Restrictions.eq("ejecutarEn", "C"));
        	return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
        } catch (Exception ex) 
        {
        	log.error("\n\nERROR getListaValidaciones:" + ex);
            throw new DaoException("Problemas obteniendo getListaValidaciones", ex);
        }
	}
	/**
	 * lista conceptos
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getListaConceptos(String tipoProceso) throws DaoException
	{
    	log.info("ValidacionesDAO:getListaConceptos:" + tipoProceso);
    	try 
        {
    		Criteria crit = this.session.createCriteria(ConceptoVO.class);
        	return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
        } catch (Exception ex) 
        {
        	log.error("\n\nERROR getListaConceptos:" + ex);
            throw new DaoException("Problemas obteniendo getListaConceptos", ex);
        }
	}
	/**
	 * lista mapeos
	 * @param lstGrupos
	 * @return
	 * @throws DaoException
	 */
	public List getListaMapeo(List lstGrupos) throws DaoException
	{
    	log.info("ValidacionesDAO:getListaMapeo:nGrupos:" + lstGrupos.size() + "::");
    	try 
        {
    		/*List lstGrupos = this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.in("idGrupoConvenio", listaGrupos)).list();*/
    		Set listaIds = new HashSet();
    		for (Iterator it = lstGrupos.iterator(); it.hasNext();)
    		{
    			GrupoConvenioVO grupo = (GrupoConvenioVO)it.next();
    			listaIds.add(new Integer(grupo.getIdMapaNomDep()));
    			listaIds.add(new Integer(grupo.getIdMapaNomGra()));
    			listaIds.add(new Integer(grupo.getIdMapaNomRel()));
    			listaIds.add(new Integer(grupo.getIdMapaNomRem()));
    		}
    		if (listaIds.isEmpty())
    			return Collections.EMPTY_LIST;
    		
    		return this.session.createCriteria(MapeoConceptoVO.class)
    			.add(Restrictions.in("idMapa", listaIds))
    			.list();
        } catch (Exception ex) 
        {
        	log.error("\n\nERROR getListaConceptos:" + ex);
            throw new DaoException("Problemas obteniendo getListaMapeos", ex);
        }
	}
	/**
	 * lista grupos
	 * @param lstGrupos
	 * @return
	 * @throws DaoException
	 */
	public List getListaGrupos(Set lstGrupos) throws DaoException
	{
    	log.info("ValidacionesDAO:getListaGrupos:nGrupos:" + lstGrupos.size() + "::");
    	try 
        {
    		if (lstGrupos.size() > 0)
    			return this.session.createCriteria(GrupoConvenioVO.class).add(Restrictions.in("idGrupoConvenio", lstGrupos)).list();
    		return new ArrayList();
        } catch (Exception ex) 
        {
        	log.error("\n\nERROR getListaGrupos:" + ex);
            throw new DaoException("Problemas obteniendo getListaGrupos", ex);
        }
	}
	/**
	 * parametro
	 * @param nombre
	 * @return
	 * @throws DaoException
	 */
    public ParametroVO getParametro(int id) throws DaoException 
    {
        try 
        {
        	log.debug("ValidacionesDAO:getParametro");
        	ParametroVO param = (ParametroVO)this.session.get(ParametroVO.class, new Integer(id));
        	if (param != null)
        		return param;
        	throw new DaoException("ERROR ValidacionesDAO:getParametro:parametro no encontrado:" + id + "::");
        } catch (HibernateException ex) 
        {
        	log.error("\n\nERROR getParametro:" + ex);
            throw new DaoException("ERROR ValidacionesDAO:getParametro:", ex);
        }
    }
    public HashMap getTiposCausasErr() throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			List lista = this.session.createCriteria(TipoCausaVO.class).add(Restrictions.eq("error", new Integer(Constants.NIVEL_VAL_ERROR))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoCausaVO tc = (TipoCausaVO) it.next();
				result.put("" + tc.getId(), tc);
			}
			return result;
		} catch (Exception e)
		{
			log.error("\n\nERROR getTiposCausasErr::", e);
			throw new DaoException("ERROR ValidacionDAO:getTiposCausasErr::", e);
		}
	}

}
