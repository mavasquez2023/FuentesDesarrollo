package cl.araucana.cp.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.DistribuidorVO;
/*
* @(#) DistribuidorDao.java 1.13 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author ccostagliola
 * 
 * @version 1.13
 */
public class DistribuidorDAO 
{
	private static Logger log = Logger.getLogger(DistribuidorDAO.class);
    Session session;

    public DistribuidorDAO(Session session) 
    {
        this.session = session;
    }
    /**
     * lista nodos
     * @return
     * @throws DaoException
     */
    public List getNodos() throws DaoException 
    {
    	try
    	{
    		return this.session.createQuery("from Nodo nodo").list();
    	} catch (Exception ex) 
    	{
    		log.error("\n\nERROR getNodos:" + ex);
    		throw new DaoException("Problemas obteniendo getNodos", ex);
    	}
    }
    /**
     * distribuidor nodo
     * @return
     * @throws DaoException
     */
    public DistribuidorVO getNodoDistribuidor() throws DaoException 
    {
    	DistribuidorVO nodo = null;
    	log.info("getNodoDistribuidor");
        try 
        {
        	Criteria crit = this.session.createCriteria(DistribuidorVO.class);
        	List lista = crit.add(Restrictions.eq("distribuidor", new Integer(1))).list();
        	if (lista.size() > 0)
        	{
        		nodo = (DistribuidorVO)lista.get(0);
        		log.info("nodo distribuidor obtenido:" + nodo.getIdNodo() + ":largo:" + lista.size() + ":");
        	} else
        		log.info("\n\no se encontro nodo distribuidor:");
        } catch (HibernateException ex) 
        {
    		log.error("\n\nERROR getNodoDistribuidor:" + ex);
            throw new DaoException("Problemas obteniendo el nodo distribuidor",ex);
        }
        return nodo;
    }
}
