package cl.laaraucana.wssinacofi.services;

import java.util.Map;

public interface UsuarioService {
	
	public Map<String, String> consultaCredenciales(String usuario) throws Exception;

}
