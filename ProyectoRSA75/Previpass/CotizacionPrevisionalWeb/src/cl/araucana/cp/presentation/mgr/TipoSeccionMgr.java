package cl.araucana.cp.presentation.mgr;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.TipoSeccionDAO;

/*
 * @(#) TipoSeccionMgr.java 1.6 10/05/2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.6
 */
public class TipoSeccionMgr
{
	private TipoSeccionDAO tipoSeccionDao;
	static Logger logger = Logger.getLogger(TipoSeccionMgr.class);

	public TipoSeccionMgr(Session session)
	{
		this.tipoSeccionDao = new TipoSeccionDAO(session);
	}

	/**
	 * tipos secciones
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposSecciones() throws DaoException
	{
		try
		{
			return this.tipoSeccionDao.getTiposSecciones();
		} catch (Exception ex)
		{
			logger.error("Error en TipoSeccionDAO.getTipoSeccion");
			throw new DaoException("Error en TipoSeccionDAO.getTipoSeccion", ex);
		}
	}
}
