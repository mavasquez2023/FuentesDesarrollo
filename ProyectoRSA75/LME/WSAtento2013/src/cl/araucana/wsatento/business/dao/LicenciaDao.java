package cl.araucana.wsatento.business.dao;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface LicenciaDao {
	public List getLicenciasByRut(Integer rut) throws WSAtentoException;
}
