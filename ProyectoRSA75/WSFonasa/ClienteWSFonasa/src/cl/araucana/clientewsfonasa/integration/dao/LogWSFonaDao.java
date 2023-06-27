package cl.araucana.clientewsfonasa.integration.dao;

import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;

public interface LogWSFonaDao {
	public Integer saveLogWSFona(LogWSFonasaTO logTo) throws DaoException;
}
