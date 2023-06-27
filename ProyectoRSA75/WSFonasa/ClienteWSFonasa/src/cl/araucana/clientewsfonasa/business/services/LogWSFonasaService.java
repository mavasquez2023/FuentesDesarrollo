package cl.araucana.clientewsfonasa.business.services;

import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;

public interface LogWSFonasaService {

	public Integer saveLogWSFona(LogWSFonasaTO log) throws ServiceException, DaoException;
}
