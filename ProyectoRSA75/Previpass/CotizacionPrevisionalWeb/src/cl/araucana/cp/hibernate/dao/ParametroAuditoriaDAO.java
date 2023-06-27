package cl.araucana.cp.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.ParametroAuditoriaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) ParametroAuditoriaDao.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jsalazar
 * @author cchamblas
 * 
 * @version 1.3
 */
public class ParametroAuditoriaDAO {
	
	private static Logger log = Logger.getLogger(ParametroAuditoriaDAO.class);
	private Session session;

	public ParametroAuditoriaDAO(Session session)
	{
		this.session = session;
	}
	/**
	 * lista parametros
	 * @param idTipoEvento
	 * @return
	 * @throws DaoException
	 */
	public List getListaParametros(int idTipoEvento) throws DaoException {
		
        try 
        {
        	log.info("ParametroAuditoriaDAO:getListaParametros:idTipoEvento:" + idTipoEvento + "::");
        	List listaParametros = this.session.createCriteria(ParametroAuditoriaVO.class)
        		.add(Restrictions.eq("idTipoEvento", new Integer(idTipoEvento))).list();
        	
        	return listaParametros; 
        } catch (HibernateException ex) 
        {
            throw new DaoException("ERROR ParametroAuditoriaDAO:getListaParametros: idTipoEvento:" + idTipoEvento , ex);
        }
	}
}
