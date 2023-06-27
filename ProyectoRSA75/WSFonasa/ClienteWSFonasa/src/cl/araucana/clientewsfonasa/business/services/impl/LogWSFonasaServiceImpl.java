package cl.araucana.clientewsfonasa.business.services.impl;

import cl.araucana.clientewsfonasa.business.services.LogWSFonasaService;
import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;
import cl.araucana.clientewsfonasa.integration.dao.LogWSFonaDao;
import cl.araucana.clientewsfonasa.integration.dao.impl.LogWSFonaDaoImpl;

public class LogWSFonasaServiceImpl implements LogWSFonasaService{

	public Integer saveLogWSFona(LogWSFonasaTO logTo) throws ServiceException, DaoException{
		LogWSFonaDao logDao = new LogWSFonaDaoImpl();
		Integer idLog = logDao.saveLogWSFona(logTo);
		return idLog;
	}
}
