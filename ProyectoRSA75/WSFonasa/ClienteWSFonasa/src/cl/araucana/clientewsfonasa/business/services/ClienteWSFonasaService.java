package cl.araucana.clientewsfonasa.business.services;

import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;

public interface ClienteWSFonasaService {

	public ResponseWSFonasaTO consultarRutFonasa(CallWSFonasaTO call) throws ServiceException, DaoException;
}
