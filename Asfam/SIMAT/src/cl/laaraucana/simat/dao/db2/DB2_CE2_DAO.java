package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.CE2_DAO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2_CE2_DAO implements CE2_DAO {

	public CountVO getCE2_Num_subs_Iniciados_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE2_Num_subs_Iniciados_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE2_Num_subs_Iniciados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE2_Num_subs_Iniciados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE2_Num_subs_Iniciados_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE2_Num_subs_Iniciados_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE2_Num_subs_Iniciados_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE2_Num_subs_Iniciados_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

}
