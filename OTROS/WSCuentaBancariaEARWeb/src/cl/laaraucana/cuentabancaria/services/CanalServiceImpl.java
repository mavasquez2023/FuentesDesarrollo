package cl.laaraucana.cuentabancaria.services;

import cl.laaraucana.cuentabancaria.dao.CanalDAO;
import cl.laaraucana.cuentabancaria.vo.CanalVo;

public class CanalServiceImpl implements CanalService{

	private final CanalDAO dao = new CanalDAO();
	
	@Override
	public void setCanal(CanalVo canal) throws Exception {
		 
		dao.setCanal(canal);
		
	}

}
