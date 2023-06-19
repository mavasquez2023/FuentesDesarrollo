package cl.laaraucana.benef.services;

import cl.laaraucana.benef.vo.CredencialesVO;

public interface UsuarioService {
	
	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception;
	

}
