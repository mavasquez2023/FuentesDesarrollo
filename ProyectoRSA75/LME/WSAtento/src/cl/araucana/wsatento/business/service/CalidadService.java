package cl.araucana.wsatento.business.service;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface CalidadService {
	
	public Boolean valCalidad(String rut, Integer calidad) throws WSAtentoException;
}
