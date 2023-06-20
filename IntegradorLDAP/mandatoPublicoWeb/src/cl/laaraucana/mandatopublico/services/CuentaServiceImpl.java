package cl.laaraucana.mandatopublico.services;



import org.springframework.stereotype.Service;

import cl.laaraucana.mandatopublico.ibatis.dao.CuentaDao;
import cl.laaraucana.mandatopublico.ibatis.dao.CuentaDaoImpl;
import cl.laaraucana.mandatopublico.ibatis.vo.RegMandatoPublicoVo;



@Service
public class CuentaServiceImpl implements CuentaService{

	
	CuentaDao dao = new CuentaDaoImpl();
	

	@Override
	public void insertMandatoPublico(RegMandatoPublicoVo reg) throws Exception {
		// TODO Auto-generated method stub
		dao.insertMandatoPublico(reg);
	}

}
