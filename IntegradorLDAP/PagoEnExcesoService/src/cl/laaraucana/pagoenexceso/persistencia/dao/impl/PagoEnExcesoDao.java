package cl.laaraucana.pagoenexceso.persistencia.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.pagoenexceso.persistencia.dao.PagoEnExcesoDaoI;
import cl.laaraucana.pagoenexceso.persistencia.vo.PagoEnExceso;

public class PagoEnExcesoDao implements PagoEnExcesoDaoI {

	private static SqlMapClient sqlMap;
	
	public PagoEnExcesoDao(SqlMapClient sqlMapIn) {
		sqlMap = sqlMapIn;
	}
	
	@Override
	public PagoEnExceso obtenerPagoEnExcesoAfiPen(String rut) throws DaoException {
		PagoEnExceso pago = null;
		try {
			pago = (PagoEnExceso) sqlMap.queryForObject("obtenerPagoEnExcesoAfiPen", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener pago en exceso Pensionado",e);
		}
		return pago;
	}

	@Override
	public PagoEnExceso obtenerPagoEnExcesoEmpresa(String rut) throws DaoException {
		PagoEnExceso pago = null;
		try {
			pago = (PagoEnExceso) sqlMap.queryForObject("obtenerPagoEnExcesoEmpresa", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener pago en exceso empresa",e);
		}
		return pago;
	}

	@Override
	public PagoEnExceso obtenerPagoEnExcesoEntPag(String rut) throws DaoException {
		PagoEnExceso pago = null;
		try {
			pago = (PagoEnExceso) sqlMap.queryForObject("obtenerPagoEnExcesoEntPag", rut);
		} catch (Exception e) {
			throw new DaoException("Error al obtener pago en exceso Entidad pagadora",e);
		}
		return pago;
	}

}
