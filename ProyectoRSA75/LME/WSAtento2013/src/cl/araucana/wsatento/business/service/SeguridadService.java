package cl.araucana.wsatento.business.service;

import cl.araucana.wsatento.business.to.UsuarioTO;
import cl.araucana.wsatento.integration.exception.WSAtentoException;


public interface SeguridadService {
	
	public void login(String usuario, String clave, String nomWS) throws WSAtentoException;
	
	public boolean authentication(UsuarioTO user);
}
