package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.TipoEventoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) TipoEventoDao.java 1.4 10/06/2009
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
public class TipoEventoDAO
{
	private static Logger log = Logger.getLogger(TipoEventoDAO.class);
	private Session session;

	public TipoEventoDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param nombreEvento
	 * @return retorna tipo de evento
	 * @throws DaoException
	 */
	public TipoEventoVO getTipoEvento(String nombreEvento) throws DaoException
	{
		try
		{
			log.info("TipoEventoDAO:getTipoEvento:" + nombreEvento + "::");
			List listaTipoEvento = this.session.createCriteria(TipoEventoVO.class).add(Restrictions.eq("nombre", nombreEvento.trim().toUpperCase())).list();

			TipoEventoVO tipoEvento = null;
			if (listaTipoEvento != null && listaTipoEvento.size() > 0)
			{
				tipoEvento = (TipoEventoVO) listaTipoEvento.get(0);

			} else
			{
				throw new DaoException("Problemas en TipoEventoVO.getTipoEvento, no se encontro el registro pala el tipo de devento " + nombreEvento);
			}

			return tipoEvento;
		} catch (Exception ex)
		{
			log.error("getTipoEvento:", ex);
			throw new DaoException("Problemas en TipoEventoVO.getTipoEvento", ex);
		}
	}

}
