
package cl.araucana.adminCpe.hibernate.dao;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) AvisosDao.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author vagurto
 * @author cchamblas
 * 
 * @version 1.4
 */
public class AvisosDAO
{
	private static Logger log = Logger.getLogger(AvisosDAO.class);
	private Session session;
	
	public AvisosDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @return lista avisos
	 * @throws DaoException
	 */
	public List getAvisos() throws DaoException
	{
		try
		{
			log.info("AvisoDAO:getAviso:");
			return this.session.createCriteria(AvisosVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en AvisosDAO.getAviso");
			throw new DaoException("BancoDAO:getBancos:", ex);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return aviso
	 * @throws DaoException
	 */
	public AvisosVO getAviso(int id) throws DaoException 
	{
		try 
		{
			log.info("AvisosDAO:getAviso:");
			this.session.flush();
			return (AvisosVO)this.session.createCriteria(AvisosVO.class)
				.add(Restrictions.eq("idAvisos", new Integer(id)))				
				.uniqueResult();
		} catch (Exception ex) 
		{
			log.error("Error en AvisosDAO:getAviso");
			throw new DaoException("AvisosDAO:getAviso:", ex);
		}
	}
	
	/**
	 * 
	 * @param avisoVO
	 * @throws DaoException
	 */
	public void guardarAviso(AvisosVO avisoVO) throws DaoException
	{
		try 
		{
			Object e = this.session.get(AvisosVO.class, new Integer(avisoVO.getIdAvisos()));
			if (e == null)
				this.session.save(avisoVO);
			else
				this.session.merge(avisoVO);
		} catch (Exception ex) 
		{
			log.error("Ha ocurrido la siguiente excepcion en AvisoDAO.guardaAviso: " + ex);
			throw new DaoException("Error al guardar el aviso: ", ex);
		}
	}
}
