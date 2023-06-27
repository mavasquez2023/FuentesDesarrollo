package cl.araucana.cp.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) MapeoTesoreriaDao.java 1.7 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.7
 */
public class TipoSeccionDAO
{
	private static Logger log = Logger.getLogger(ParametrosDAO.class);
	private Session session;

	public TipoSeccionDAO(Session session)
	{
		this.session = session;
	}
	/**
	 * tipo seccion
	 * @param tipoSeccion
	 * @return
	 * @throws DaoException
	 */
	public TipoSeccionVO getTipoSeccion(int tipoSeccion) throws DaoException
	{
		try
		{
			return (TipoSeccionVO) this.session.load(TipoSeccionVO.class, new Integer(tipoSeccion));
		} catch (Exception ex)
		{
			log.error("Error en TipoSeccionDAO.getTipoSeccion");
			throw new DaoException("Error en TipoSeccionDAO.getTipoSeccion", ex);
		}
	}
	/**
	 * tipos secciones
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposSecciones() throws DaoException
	{
		try
		{
			List result = this.session.createCriteria(TipoSeccionVO.class).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					TipoSeccionVO ts = (TipoSeccionVO)it.next();
					resultHM.put("" + ts.getId(), ts);
				}
				return resultHM;
			}
			throw new DaoException("TipoSeccionDAO:getTiposSecciones: no se pudo cargar/no se encontro ninguna seccion");
		} catch (Exception ex)
		{
			log.error("Error en TipoSeccionDAO.getTipoSeccion");
			throw new DaoException("Error en TipoSeccionDAO.getTipoSeccion", ex);
		}
	}
}
