package cl.laaraucana.pagoenexceso.persistencia.dao;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExceso;

public interface PagoEnExcesoDaoI {
	public abstract PagoEnExceso obtenerPagoEnExcesoAfiPen(String rut) throws DaoException;
	public abstract PagoEnExceso obtenerPagoEnExcesoEmpresa(String rut) throws DaoException;
	public abstract PagoEnExceso obtenerPagoEnExcesoEntPag(String rut) throws DaoException;
	
	//Consultas asociadas a pago en exceso
}
