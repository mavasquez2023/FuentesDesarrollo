package cl.araucana.wsatento.business.service;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface CreditoService {
	
	public List getCreditos(String rut) throws WSAtentoException;
}
