package cl.araucana.clientewsfonasa.business.services;

import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.business.to.ResponseWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.exception.ServiceException;

public interface CallWSFonasaService {

	public Integer saveCallWSFona(CallWSFonasaTO callTo) throws DaoException;
	
//	public void updCallWSFonaEstadoEnPreoceso(CallWSFonasaTO callTo) throws ServiceException, DaoException;
	
	public void updCallWSFonaEstadoProcesado(CallWSFonasaTO callTo) throws ServiceException, DaoException;
	
	public ResponseWSFonasaTO consultarRutFonasa(CallWSFonasaTO call) throws ServiceException, DaoException;
}
