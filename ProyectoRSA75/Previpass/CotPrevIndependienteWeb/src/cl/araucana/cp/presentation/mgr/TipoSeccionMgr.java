package cl.araucana.cp.presentation.mgr;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.TipoSeccionDAO;

/*
 * @(#) TipoSeccionMgr.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
