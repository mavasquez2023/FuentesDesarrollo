package cl.laaraucana.mediopagosilws.core.database.dao;

import java.util.Map;

import cl.laaraucana.mediopagosilws.core.database.exception.DaoException;

public interface MedioPagoDaoI {
	public abstract Map<String, String> consultaMedioPago(Map<String, String> map) throws DaoException;
}