package cl.laaraucana.mediopagosilws.core.database.dao.impl;

import java.util.Map;

import cl.laaraucana.mediopagosilws.core.database.dao.MedioPagoDaoI;
import cl.laaraucana.mediopagosilws.core.database.exception.DaoException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MedioPagoDaoImpl implements MedioPagoDaoI {
	private SqlMapClient sqlMap;

	public MedioPagoDaoImpl(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> consultaMedioPago(Map<String, String> map) throws DaoException {
		try {
			map = (Map<String, String>) sqlMap.queryForObject("SERVPAGSP", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Error", e);
		}
		return map;
	}
}