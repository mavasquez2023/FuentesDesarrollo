package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.EstadoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstadosDao.java 1.1 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class EstadosDAO {
	private static Logger log = Logger.getLogger(EstadosDAO.class);
	Session session;

	public EstadosDAO(Session session) {
		this.session = session;
	}

	/**
	 * 
	 * @param idEstado
	 * @return estados
	 * @throws DaoException
	 */
	public EstadoVO getEstado(int idEstado) throws DaoException
	{
		try 
		{
			return (EstadoVO) this.session.load(EstadoNominaVO.class, new Integer(idEstado));
		} catch (Exception ex) 
		{
			log.error("Error en EstadosDAO.getEstado");
			throw new DaoException("Error en EstadosDAO.getEstado", ex);
		}
	}

	/**
	 * 
	 * @return lista estados
	 * @throws DaoException
	 */
	public List getEstados() throws DaoException 
	{
		try 
		{
			return this.session.createCriteria(EstadoNominaVO.class).list();
		} catch (Exception ex) 
		{
			log.error("Error en EstadosDAO.getEstados()");
			throw new DaoException("Error en EstadosDAO.getEstados()", ex);
		}
	}
}

