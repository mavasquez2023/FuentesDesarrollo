package cl.laaraucana.mediopagosilws.core.database.dao;

import cl.laaraucana.mediopagosilws.core.database.config.MyClassSqlConfig;
import cl.laaraucana.mediopagosilws.core.database.dao.impl.MedioPagoDaoImpl;
import cl.laaraucana.mediopagosilws.core.database.exception.DaoException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DB2Factory extends DAOFactory {

	private SqlMapClient sqlMap = null;

	public DB2Factory() throws DaoException {
		sqlMap = MyClassSqlConfig.getInstance();
	}

	@Override
	public MedioPagoDaoI getMedioPagoDao() {
		return new MedioPagoDaoImpl(sqlMap);
	}

}
