package cl.laaraucana.recepcionsil.persistencia;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.config.ibatis.MyClassSqlConfig;
import cl.laaraucana.recepcionsil.persistencia.dao.ServIngDaoI;
import cl.laaraucana.recepcionsil.persistencia.dao.impl.ServIngDao;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DB2Factory extends DAOFactory {

	private SqlMapClient sqlMap = null;

	public DB2Factory() throws DaoException {
		sqlMap = MyClassSqlConfig.getInstance();
	}

	@Override
	public ServIngDaoI getServIngresoDao() {
		ServIngDao dao = new ServIngDao(sqlMap);
		return dao;
	}
}
