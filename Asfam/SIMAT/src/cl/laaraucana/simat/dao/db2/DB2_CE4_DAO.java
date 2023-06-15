package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.CE4_DAO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2_CE4_DAO implements CE4_DAO {

	public CountVO getCE4_NumSubs_JornadaCompleta_Iniciados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaCompleta_Iniciados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE4_NumSubs_JornadaCompleta_Pagados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaCompleta_Pagados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE4_NumSubs_JornadaCompleta_Traspasados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaCompleta_Traspasados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Iniciados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaParcial_Iniciados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Pagados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaParcial_Pagados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Traspasados_Parental(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE4_NumSubs_JornadaParcial_Traspasados_Parental", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

}
