package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.Sum_InformeFinancieroDAO;
import cl.laaraucana.simat.entidades.SumVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Sum_InformeFinancieroDAO implements Sum_InformeFinancieroDAO {

	public SumVO getSum_A_4_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_A_4_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_A_4_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_A_4_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_6_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_6_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_6_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_6_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_6_3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_6_3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_6_4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_6_4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_7_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_7_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_7_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_7_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_7_3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_7_3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_7_4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_7_4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_8_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_8_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_8_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_8_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_8_3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_8_3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_8_4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_8_4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_9_1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_9_1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_9_2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_9_2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_9_3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_9_3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_C_9_4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_C_9_4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_E1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_E1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_E2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_E2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_E3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_E3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_E4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_E4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_F1(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_F1", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_F2(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_F2", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_F3(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_F3", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

	public SumVO getSum_F4(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			sumSQL = (SumVO) sqlMap.queryForObject("getSum_F4", sumSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumSQL;
	}

}
