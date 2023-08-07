package cl.laaraucana.pagoenexceso.persistencia.dao;

import cl.laaraucana.config.ibatis.DaoException;

public interface AfiliadoDaoI {
	public abstract String obtenerNombrePensionado(String rut) throws DaoException;
	public abstract String obtenerNombreAfiliado(String rut) throws DaoException;
	public abstract String obtenerNombreEmpresa(String rut) throws DaoException;
	public abstract String obtenerNombreEmpresaCMDTA(String rut) throws DaoException;
	public abstract String obtenerNombreEntidadPagadora(String rut) throws DaoException;

}
