package cl.laaraucana.mandato.ibatis.dao;

import java.util.List;
import java.util.Map;

import cl.laaraucana.mandato.ibatis.vo.RegistroGestorClaveVo;


public interface CuentaDao {

	public List<RegistroGestorClaveVo> findRegisterKeyByRut(long rut) throws Exception;

	public void updateRegisterKeyByRut(Map<String, Object> param) throws Exception;


}
