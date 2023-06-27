package cl.araucana.wsatento.business.dao;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface CalidadDao {
	public List getCalidadesByRut(Integer rut) throws WSAtentoException;
}
