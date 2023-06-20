package cl.laaraucana.wssinacofi.services;

import java.util.Map;

import cl.laaraucana.wssinacofi.ibatis.dao.AdministracionDAO;


public class UsuarioServiceImpl implements UsuarioService{

	private final AdministracionDAO dao = new AdministracionDAO(); 
	
	@Override
	public Map<String, String> consultaCredenciales(String usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.validaUsuarioWS(usuario);
	}


	

}
