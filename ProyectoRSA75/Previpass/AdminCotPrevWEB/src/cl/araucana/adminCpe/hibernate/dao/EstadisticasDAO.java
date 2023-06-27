
package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstadisticasDao.java 1.3 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author cchamblas
 * 
 * @version 1.3
 */
public class EstadisticasDAO 
{
	private static Logger log = Logger.getLogger(EstadisticasDAO.class);
    Session session;

    public EstadisticasDAO(Session session) 
    {
        this.session = session;
    }

    /**
     * 
     * @return lista nodos
     * @throws DaoException
     */
    public List getNodos() throws DaoException 
    {
    	log.info("EstadisticasDAO:getNodos");
    	try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	return crit.add(Restrictions.eq("habilitado", new Integer(1))).list();
        } catch (Exception ex) 
        {
        	log.info("ERROR:" + ex);
            throw new DaoException("Problemas obteniendo getNodos:",ex);
        }
    }

    /**
     * 
     * @param idNodo
     * @return nodos
     * @throws DaoException
     */
    public NodoVO getNodo(int idNodo) throws DaoException 
    {
    	log.info("EstadisticasDAO:getNodo");
    	try 
        {
        	return (NodoVO)this.session.load(NodoVO.class, new Integer(idNodo));
        } catch (Exception ex) 
        {
        	log.info("ERROR:" + ex);
            throw new DaoException("Problemas obteniendo getNodo:",ex);
        }
    }
}
