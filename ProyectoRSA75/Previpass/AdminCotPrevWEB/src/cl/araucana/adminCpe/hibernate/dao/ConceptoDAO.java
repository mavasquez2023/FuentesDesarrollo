package cl.araucana.adminCpe.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#)ConceptoDao.java 1.5 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author ccostagliola
 * @author aacuña
 * 
 * @version 1.5
 */
public class ConceptoDAO
{
	private static Logger logger = Logger.getLogger(ConceptoDAO.class);
	private Session session;

	public ConceptoDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public HashMap getMapaConceptos(String tipoNomina) throws DaoException {

		try 
        {
        	//logger.info("ConceptoDAO:getMapaConceptos: tipoNomina:" + tipoNomina + "::");

        	List listaConcepto = this.session.createCriteria(ConceptoVO.class)
        		.add(Restrictions.eq("tipoProceso", tipoNomina))
        		.list();
        	HashMap mapConcepto = new HashMap();
        	ConceptoVO concepto;
        	for (Iterator it = listaConcepto.iterator(); it.hasNext();) {
        		concepto = (ConceptoVO) it.next(); 
        		mapConcepto.put(new Integer(concepto.getId()), concepto);
        	}
        	return mapConcepto;
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:getMapaConceptos: tipoNomina:" + tipoNomina + "::", ex);
        }
		
	}

	/**
	 * 
	 * @param tipoProceso
	 * @param idMapaNom
	 * @return
	 * @throws DaoException
	 */
    public List getListaMapeo(String tipoProceso, int idMapaNom) throws DaoException 
    {
        try 
        {
        	logger.info("ConceptoDAO:getListaMapeo");
        	Criteria crit = this.session.createCriteria(MapeoConceptoVO.class);
        	return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).add(Restrictions.eq("idMapa", new Integer(idMapaNom))).list();
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:getListaMapeo:", ex);
        }
    }

    /**
     * 
     * @param lista
     * @throws DaoException
     */
	public void guardaMapeosConcep(List lista) throws DaoException {
		
        try 
        {
        	logger.info("ConceptoDAO:guardaMapeosConcep::");
        	
        	MapeoConceptoVO mapeo;
        	for (Iterator it = lista.iterator(); it.hasNext();) {
        		mapeo = (MapeoConceptoVO) it.next();
        		this.session.merge(mapeo);
        	}
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:guardaMapeosConcep:", ex);
        }
	}
	/**
	 * 
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
    public List getListaConceptos(String tipoProceso) throws DaoException 
    {
        try 
        {
        	logger.info("ConceptoDAO:getListaConceptos:" + tipoProceso);
        	Criteria crit = this.session.createCriteria(ConceptoVO.class);
        	return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:getListaConceptos:" + tipoProceso + ":", ex);
        }
    }
    /**
     * 
     * @param idMapaNom
     * @param tipoNomina
     * @return
     * @throws DaoException
     */
	public List getListaMapeosConcepto(int idMapaNom, String tipoNomina) throws DaoException 
	{
        try 
        {
        	//logger.info("ConceptoDAO:getListaMapeosConcepto: idMapaNom:" + idMapaNom + ":, tipoNomina:" + tipoNomina + "::");
        	List listaMapeo = this.session.createCriteria(MapeoConceptoVO.class)
        		.add(Restrictions.eq("idMapa", new Integer(idMapaNom)))
        		.add(Restrictions.eq("tipoProceso", tipoNomina))
        		.addOrder(Order.asc("idConcepto"))
        		.list();
        	
        	Map mapConcepto = this.getMapaConceptos(tipoNomina);
        	MapeoConceptoVO mapeo;
        	for (Iterator it = listaMapeo.iterator(); it.hasNext();)
        	{
        		mapeo = (MapeoConceptoVO) it.next(); 
        		mapeo.setConcepto((ConceptoVO) mapConcepto.get(new Integer(mapeo.getIdConcepto())));
        	}

        	return listaMapeo; 
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:getListaMapeosConcepto: idMapaNom:" + idMapaNom + ":, tipoNomina:" + tipoNomina + "::", ex);
     
        }
	}
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	  public List getLstValidaMovPers() throws DaoException 
	    {
	        try 
	        {
	        	logger.info("ConceptoDAO:getLstValidaMovPers");
	        	Criteria crit = this.session.createCriteria(ValidacionVO.class);
	        	return crit.add(Restrictions.eq("ejecutarEn", "M")).list();
	        } catch (HibernateException ex) 
	        {
	            throw new DaoException("ERROR ConceptoDAO:getLstValidaMovPers:", ex);
	        }
	    }
	  /**
	   * 
	   * @return
	   * @throws DaoException
	   */
	public List getLstValidaAPVs() throws DaoException 
    {
        try 
        {
        	logger.info("ConceptoDAO:getLstValidaAPVs");
        	Criteria crit = this.session.createCriteria(ValidacionVO.class);
        	return crit.add(Restrictions.eq("ejecutarEn", "A")).list();
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ConceptoDAO:getLstValidaAPVs:", ex);
        }
    }
}
