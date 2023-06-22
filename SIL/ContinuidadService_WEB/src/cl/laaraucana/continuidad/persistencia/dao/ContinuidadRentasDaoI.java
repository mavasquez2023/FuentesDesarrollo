package cl.laaraucana.continuidad.persistencia.dao;

import cl.laaraucana.config.ibatis.DaoException;

public interface ContinuidadRentasDaoI {
	public abstract String consultaContinuidadRentas(String rut, String cantidadRentas) throws DaoException;
}
