package cl.laaraucana.simat.dao.db2;

import java.io.Reader;

import cl.laaraucana.simat.dao.QueryIdDAO;
import cl.laaraucana.simat.entidades.PeriodoVO;
import cl.laaraucana.simat.entidades.QueryIdVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2QueryIdDAO implements QueryIdDAO {

	public PeriodoVO BuscarById(PeriodoVO periodo) throws Exception {
		return periodo;
	}

	public QueryIdVO ObtenerMinMaxId(QueryIdVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		qid = (QueryIdVO) sqlMap.queryForObject("getMinMax" + qid.getTabla(), qid);
		return qid;
	}

}
