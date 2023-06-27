package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
* @(#) TipoCausaDao.java 1.4 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author jdelgado
 * @author malvarez
 * 
 * @version 1.4
 */
public class TipoCausaDAO
{
	private static Logger log = Logger.getLogger(TipoCausaDAO.class);
	private Session session;
	private boolean loggear = true;

	public TipoCausaDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * @param String descripcion con la descripcion de los errores a buscar
	 * @return retorna lista descripcion
	 * @throws DaoException
	 */
	public List getErrores(String descripcion) throws DaoException
	{
		try
		{
			Class tipo = TipoCausaVO.class;
			
			String select = "select tipo from TipoCausaVO tipo ";
			
			if (this.loggear) log.info("TipoCausaDAO:getErrores:");
			
			if (descripcion == null || descripcion.equals("")){
				
			}else{
				select += "where tipo.descripcion like '%"+descripcion+"%' ";
			}
			select += " order by tipo.descripcion";
			
			StringBuffer hqlQuery = new StringBuffer(select);
			
			Query query = this.session.createQuery(hqlQuery.toString());

			return query.list();
			
		} catch (Exception ex)
		{
			log.error("Error en TipoCausaDAO:getErrores:");
			throw new DaoException("TipoCausaDAO:getErrores:", ex);
		}
	}
	/**
	 * 
	 * @param id
	 * @return retorna lista errores
	 * @throws DaoException
	 */
	public List getErrores(int id) throws DaoException
	{
		try
		{
			Class tipo = TipoCausaVO.class;
			if (this.loggear) log.info("TipoCausaDAO:getErrores:");
			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("id", new Integer(id))).list();						
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en TipoCausaDAO:getErrores:");
			throw new DaoException("TipoCausaDAO:getErrores:", ex);
		}
	}
	/**
	 * 
	 * @param TipoCausaVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void save(TipoCausaVO TipoCausaVO) throws DaoException 
	{
		try 
		{
			log.info("TipoCausaDAO:save");
			this.session.save(TipoCausaVO);
		} catch(Exception ex) 
		{
			log.error("TipoCausaDAO:save error: " + ex.getCause().toString());
			throw new DaoException("Problemas en TipoCausaDAO:save", ex);
		}
	}
	/**
	 * 
	 * @param TipoCausaVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void update(TipoCausaVO TipoCausaVO) throws DaoException 
	{
		try 
		{
			log.info("TipoCausaDAO:update");
			
			this.session.update(TipoCausaVO);
		} catch(Exception ex) {
			log.error("TipoCausaDAO:update error: " + ex.getCause().toString());
			throw new DaoException("Problemas en TipoCausaDAO:update", ex);
		}
	}

	/**
	 * Entrega un TipoCausa segun el identificador.
	 * @param id
	 * @return
	 * @throws DaoException
	 * @author malvarez
	 */
	public TipoCausaVO getTipoCausa(int id) throws DaoException 
	{
		try {
			log.info("TipoCausaDAO:getTipoCausa");
			return (TipoCausaVO) this.session.get(TipoCausaVO.class, new Integer(id));
			
		} catch(Exception ex) {
			log.error("TipoCausaDAO:getTipoCausa error: " + ex.getCause().toString());
			throw new DaoException("Problemas en TipoCausaDAO:getTipoCausa", ex);
		}
	}	
	
	/**
	 * Elimina un TipoCausa.
	 * @param objeto
	 * @return
	 * @throws Exception
	 * @author malvarez
	 */
	public boolean delete(TipoCausaVO objeto) throws Exception {
		boolean result = false;
		try{
			log.info("TipoCausaDAO:delete");
			this.session.delete(objeto);
			result = true;
			return result;
		} catch(Exception ex) {
			log.error("TipoCausaDAO:delete error: " + ex.getCause().toString());
			throw new DaoException("Problemas en TipoCausaDAO:delete", ex);
		}
	}
}
