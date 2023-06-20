package cl.laaraucana.transferencias.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.transferencias.ibatis.dao.CuentaDao;
import cl.laaraucana.transferencias.ibatis.dao.CuentaDaoImpl;
import cl.laaraucana.transferencias.ibatis.vo.RegistroGestorClaveVo;


@Service
public class CuentaServiceImpl implements CuentaService{

	
	CuentaDao dao = new CuentaDaoImpl();
	
	@Override
	public List<RegistroGestorClaveVo> findRegisterKeyByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findRegisterKeyByRut(rut);
	}

	@Override
	public void updateRegisterKeyByRut(Map<String, Object> param) throws Exception {
		dao.updateRegisterKeyByRut(param);
		
	}

}
