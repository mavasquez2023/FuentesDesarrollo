package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.Sum_Monto_Subs_PagadosDAO;
import cl.laaraucana.simat.entidades.SumVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Sum_Monto_Subs_PagadosDAO implements Sum_Monto_Subs_PagadosDAO {
	public SumVO getSum_Monto_SubsPagado_Pre_natal(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Pre_natal", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Post_natal_Madre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Post_natal_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Post_natal_Padre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Post_natal_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Parental_Madre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Parental_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Parental_Padre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Parental_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Madre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Enf_menor_Madre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Padre(SumVO countSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			countSQL = (SumVO) sqlMap.queryForObject("getSum_Monto_SubsPagado_Enf_menor_Padre", countSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countSQL;
	}

}
