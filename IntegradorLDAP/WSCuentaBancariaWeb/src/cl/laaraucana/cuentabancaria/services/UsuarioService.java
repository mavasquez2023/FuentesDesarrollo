package cl.laaraucana.cuentabancaria.services;

import cl.laaraucana.cuentabancaria.vo.CredencialesVO;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;

public interface UsuarioService {
	
	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception;
	
	public String consultaCanal(UsuarioVo user) throws Exception;
	
	public int consultaCodigoCanal(UsuarioVo user) throws Exception;

}
