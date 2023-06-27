package cl.araucana.clientewsfonasa.integration.dao;

import java.util.List;

import cl.araucana.clientewsfonasa.business.to.CallWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;

public interface CallWSFonaDao {
	public Integer saveCallWSFona(CallWSFonasaTO callTo) throws DaoException;
	
	public void updateCallWSFonaStep1(CallWSFonasaTO callTo) throws DaoException;
	
	public void updateCallWSFonaStep2(CallWSFonasaTO callTo) throws DaoException;
	
	public List getCallWSFona(CallWSFonasaTO callTo, Integer maxRows) throws DaoException;
	
	public int getCallExisteLicSIL(int numLicencia, int rutAfiliado) throws DaoException;
	
}
