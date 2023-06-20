package cl.laaraucana.transferencias.services;

import java.util.List;
import java.util.Map;

import cl.laaraucana.transferencias.ibatis.vo.RegistroGestorClaveVo;

public interface CuentaService {


	public List<RegistroGestorClaveVo> findRegisterKeyByRut(long rut) throws Exception;

	public void updateRegisterKeyByRut(Map<String, Object> param) throws Exception;


}
