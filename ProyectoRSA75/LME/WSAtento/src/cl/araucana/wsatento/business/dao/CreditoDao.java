package cl.araucana.wsatento.business.dao;

import java.util.List;

import cl.araucana.wsatento.integration.exception.WSAtentoException;

public interface CreditoDao {
	public List getCreditosByRut(Integer rut) throws WSAtentoException;
}
