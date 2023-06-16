package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.Sum_Dias_Subs_PagadosDAO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Sum_Dias_Subs_PagadosDAO implements Sum_Dias_Subs_PagadosDAO {

	public CountVO getSum_Dias_Pagados_Pre_natal(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Pre_natal", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Post_natal_Madre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Post_natal_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Post_natal_Padre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Post_natal_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Parental_Madre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Parental_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Parental_Padre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Parental_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Enf_menor_Madre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Enf_menor_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Enf_menor_Padre(CountVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			countSQL = (CountVO) sqlMap.queryForObject("getSum_Dias_Pagados_Enf_menor_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}
}
