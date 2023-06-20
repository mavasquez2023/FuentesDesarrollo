package cl.laaraucana.transferencias.ibatis.dao;

import java.util.List;
import java.util.Map;

import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.UsuarioVo;
import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.CuentaVo;
import cl.laaraucana.transferencias.ibatis.vo.IdMandatoVo;
import cl.laaraucana.transferencias.ibatis.vo.RegistroGestorClaveVo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;

public interface CuentaDao {

	public UsuarioVo consultaCredenciales(CredencialesVO user) throws Exception;

	public List<RegistroGestorClaveVo> findRegisterKeyByRut(long rut) throws Exception;

	public void updateRegisterKeyByRut(Map<String, Object> param) throws Exception;


}
