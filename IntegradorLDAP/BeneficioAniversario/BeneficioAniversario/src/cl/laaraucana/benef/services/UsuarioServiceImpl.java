package cl.laaraucana.benef.services;

import cl.laaraucana.benef.dao.CredencialesDAO;
import cl.laaraucana.benef.vo.CredencialesVO;

public class UsuarioServiceImpl implements UsuarioService{

	private final CredencialesDAO dao = new CredencialesDAO(); 
	
	@Override
	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaCredenciales(user);
	}

}
