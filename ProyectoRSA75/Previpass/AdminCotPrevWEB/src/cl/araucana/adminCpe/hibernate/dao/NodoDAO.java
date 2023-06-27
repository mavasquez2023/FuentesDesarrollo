package cl.araucana.adminCpe.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.BalanceoCargaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) NodoDao.java 1.6 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author creyes
 * @author cchamblas
 * 
 * @version 1.6
 */
public class NodoDAO
{
	private static Logger logger = Logger.getLogger(NodoDAO.class);
	private Session session;

	public NodoDAO(Session session)
	{
		this.session = session;
	}
	
	/**
	 * lista nodos
	 * @return
	 * @throws DaoException
	 */
    public List getListaNodos() throws DaoException 
    {
        try 
        {
        	logger.info("NODO:getListaNodo");
        	return this.session.createCriteria(NodoVO.class).add(Restrictions.ne("idNodo", new Integer(0))).list();
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR NODO:getListaNodos:", ex);
        }
    }

    /**
     * lista nodos activos
     * @return
     * @throws DaoException
     */
    public List getListaNodosActivos() throws DaoException 
    {
        try 
        {
        	logger.info("NODO:getListaNodo");
        	return this.session.createCriteria(NodoVO.class).add(Restrictions.ne("idNodo", new Integer(0)))
        		.add(Restrictions.eq("habilitado", new Integer(1))).list();
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR NODO:getListaNodos:", ex);
        }
    }
    
    /**
     * nodo
     * @param idNodo
     * @return
     * @throws DaoException
     */
    public NodoVO getNodo(int idNodo) throws DaoException 
    {
        try 
        {
        	return (NodoVO) this.session.get(NodoVO.class, new Integer(idNodo));
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR NODO:getNodo:", ex);
        }
    }
    
    /**
     * elimina nodo
     * @param idNodo
     * @throws DaoException
     */
    public void eliminaNodo(int idNodo) throws DaoException 
    {
        try 
        {
        	this.session.delete(getNodo(idNodo));
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR NODO:eliminaNodo:", ex);
        }
    }
    
    /**
     * nodo
     * @param nodo
     * @throws DaoException
     */
    public int saveNodo(NodoVO nodo) throws DaoException 
    {        
        try 
        {
           	if (nodo.getIdNodo() == 0)
        		this.session.save(nodo);
        	else
        		this.session.merge(nodo);

        	this.session.flush();
           	return nodo.getIdNodo();
		} catch (Exception ex) {
			logger.error("Ha ocurrido la siguiente excepcion en NodoDAO.setNodo: " + ex);
			throw new DaoException("Error al guardar el nodo: ", ex);
		}
    }
    
    /**
     * actualiza balanceo carga
     * @param factoresCarga
     * @return
     * @throws DaoException
     */
    public boolean actualizaBalanceoCarga(List factoresCarga) throws DaoException 
    {
        try 
        {
        	Criteria crit = this.session.createCriteria(BalanceoCargaVO.class);
        	crit.setFetchMode("descriptores", FetchMode.SELECT);
        	List lista = crit.list();
        	if (lista != null && lista.size() > 0)
        	{
        		HashMap factCarga = new HashMap();
        		for (Iterator it = factoresCarga.iterator(); it.hasNext();)
        		{
        			ParametroVO p = (ParametroVO)it.next();
        			String nombre = p.getNombre().trim();
        			String tp = p.getNombre().substring(nombre.length() - 1, nombre.length());
        			logger.debug("PUT:" + tp + ":" + p.getValor().trim() + "::");
        			factCarga.put(tp, new Float(p.getValor().trim()));
        		}
        		
        		for (Iterator it = lista.iterator(); it.hasNext();)
        		{
        			BalanceoCargaVO bc = (BalanceoCargaVO)it.next();
        			bc.setCargaPromedio(((bc.getCargaPromedio() * bc.getNumPeriodos()) + (bc.getNumLineas() * ((Float)factCarga.get("" + bc.getTipoProceso())).floatValue())) / (bc.getNumPeriodos() + 1));
        			bc.sumNumPeriodos();
        			bc.setNumLineas(0);
        			logger.info("a guargar: carga:" + bc.getCargaPromedio() + ":sumNumPeriodos:" + bc.getNumPeriodos() + ":tNumLineas:" + bc.getNumLineas() + "::");
        			this.session.save(bc);
        			this.session.flush();
        		}
        	}
        } catch (HibernateException ex) 
        {
            throw new DaoException("Problemas actualizaBalanceoCarga",ex);
        }
        return true;
    }
}
