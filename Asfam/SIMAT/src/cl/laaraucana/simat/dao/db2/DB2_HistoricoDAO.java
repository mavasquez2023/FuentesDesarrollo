package cl.laaraucana.simat.dao.db2;

import java.io.Reader;

import cl.laaraucana.simat.dao.HistoricoDAO;
import cl.laaraucana.simat.entidades.Control_Historico_VO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2_HistoricoDAO implements HistoricoDAO {

	public CountVO getCountPeriodos(String tabla) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		Integer i = null;
		return ((CountVO) sqlMap.queryForObject("getCount" + tabla + "", i));
	}

	public Control_Historico_VO getMenorPeriodo(String tabla) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		Integer i = null;
		return ((Control_Historico_VO) sqlMap.queryForObject("getMenorPeriodo" + tabla + "", i));
	}

	public void delPeriodo(String tabla, Control_Historico_VO chVO) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("delPeriodo" + tabla + "", chVO);
	}

}
