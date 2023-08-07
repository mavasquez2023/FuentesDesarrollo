package cl.laaraucana.pagoenexceso.persistencia;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.config.ibatis.MyClassSqlConfig;
import cl.laaraucana.pagoenexceso.persistencia.dao.AfiliadoDaoI;
import cl.laaraucana.pagoenexceso.persistencia.dao.PagoEnExcesoDaoI;
import cl.laaraucana.pagoenexceso.persistencia.dao.impl.AfiliadoDao;
import cl.laaraucana.pagoenexceso.persistencia.dao.impl.PagoEnExcesoDao;


public class DB2Factory extends DAOFactory {
	
	private SqlMapClient sqlMap = null;

	
	public DB2Factory() throws DaoException{
		sqlMap = MyClassSqlConfig.getInstance();
	}
	
	@Override
	public PagoEnExcesoDaoI getPagoEnExcesoDao() {
		PagoEnExcesoDao dao = new PagoEnExcesoDao(sqlMap);
		return dao;
	}

	@Override
	public AfiliadoDaoI getAfiliadoDao() {
		AfiliadoDao dao = new AfiliadoDao(sqlMap);
		return dao;
	}

}
