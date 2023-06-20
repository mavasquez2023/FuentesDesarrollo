package cl.laaraucana.cuentabancaria.services;

import cl.laaraucana.cuentabancaria.dao.CredencialesDAO;
import cl.laaraucana.cuentabancaria.vo.CredencialesVO;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;

public class UsuarioServiceImpl implements UsuarioService{

	private final CredencialesDAO dao = new CredencialesDAO(); 
	
	@Override
	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaCredenciales(user);
	}

	@Override
	public String consultaCanal(UsuarioVo user) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaCanal(user);
	}

	@Override
	public int consultaCodigoCanal(UsuarioVo user) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaCodigoCanal(user);
	}

}
