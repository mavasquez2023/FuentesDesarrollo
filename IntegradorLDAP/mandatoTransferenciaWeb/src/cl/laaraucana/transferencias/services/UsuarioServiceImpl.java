package cl.laaraucana.transferencias.services;

import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.UsuarioVo;
import cl.laaraucana.transferencias.ibatis.dao.CuentaDao;
import cl.laaraucana.transferencias.ibatis.dao.CuentaDaoImpl;

public class UsuarioServiceImpl implements UsuarioService {

	CuentaDao dao = new CuentaDaoImpl();

	@Override
	public UsuarioVo consultaCredenciales(CredencialesVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaCredenciales(user);
	}

}
