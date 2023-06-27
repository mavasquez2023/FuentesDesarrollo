package cl.araucana.spl.dao.sqlmap;

import java.util.HashMap;

import cl.araucana.spl.dao.ParametrosDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class ParametrosSqlMapDAO extends SqlMapDaoTemplate implements ParametrosDAO {


	private static final String SQL_GET_PARAM = "getParametros";

	//private static final Logger logger = Logger.getLogger(ParametrosSqlMapDAO.class);

	public ParametrosSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	
	/**
	 * Retorna los parametros de la tabla SPLPARAM
	 */
	public HashMap getParametrosSPLPARAM() {
		HashMap map = new HashMap();
		map = (HashMap) queryForMap(SQL_GET_PARAM, null, "key", "value");
		return (map);
	}

}
