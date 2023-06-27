package cl.araucana.wsatento.business.dao;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface WebServicesDao {
	public List getWebServicesByUsuario(Integer uId) throws WSAtentoException;
}
