package cl.araucana.wsatento.business.service;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface LicenciaService {
	
	public List getLicenicas(String rut) throws WSAtentoException;
}
