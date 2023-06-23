package cl.araucana.spl.mgr;

import java.util.HashMap;

import cl.araucana.spl.dao.ParametrosDAO;
import cl.araucana.spl.dao.config.DaoConfig;

import com.ibatis.dao.client.DaoManager;

public class ParametrosManager {
	private ParametrosDAO parametrosDAO;
	
	public ParametrosManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		parametrosDAO = (ParametrosDAO) daoManager.getDao(ParametrosDAO.class);
	}	
	
	public HashMap getParametros(){
		return parametrosDAO.getParametrosSPLPARAM();
		
	}
	
}
