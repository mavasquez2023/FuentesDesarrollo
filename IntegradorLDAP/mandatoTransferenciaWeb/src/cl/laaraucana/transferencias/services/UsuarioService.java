package cl.laaraucana.transferencias.services;

import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.UsuarioVo;

public interface UsuarioService {

	public UsuarioVo consultaCredenciales(CredencialesVO user) throws Exception;

}
