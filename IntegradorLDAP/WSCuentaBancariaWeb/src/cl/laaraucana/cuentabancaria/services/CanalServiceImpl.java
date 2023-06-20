package cl.laaraucana.cuentabancaria.services;

import cl.laaraucana.cuentabancaria.dao.CanalDAO;
import cl.laaraucana.cuentabancaria.vo.CanalVO;

public class CanalServiceImpl implements CanalService{

	private final CanalDAO dao = new CanalDAO();
	
	@Override
	public void setCanal(CanalVO canal) throws Exception {
		 
		dao.setCanal(canal);
		
	}

}
