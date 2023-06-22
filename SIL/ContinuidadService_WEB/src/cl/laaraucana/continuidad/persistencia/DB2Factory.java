package cl.laaraucana.continuidad.persistencia;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.config.ibatis.MyClassSqlConfig;
import cl.laaraucana.continuidad.persistencia.dao.ContinuidadRentasDaoI;
import cl.laaraucana.continuidad.persistencia.dao.impl.ContinuidadRentasDao;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DB2Factory extends DAOFactory {

	private SqlMapClient sqlMap = null;

	public DB2Factory() throws DaoException {
		sqlMap = MyClassSqlConfig.getInstance();
	}

	@Override
	public ContinuidadRentasDaoI getContinuidadRentasDao() {
		return new ContinuidadRentasDao(sqlMap);
	}

}
