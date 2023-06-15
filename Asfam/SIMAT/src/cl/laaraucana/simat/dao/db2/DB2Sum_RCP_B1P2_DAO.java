package cl.laaraucana.simat.dao.db2;

import java.io.Reader;

import cl.laaraucana.simat.dao.Sum_RCP_B1P2_DAO;
import cl.laaraucana.simat.entidades.SumVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Sum_RCP_B1P2_DAO implements Sum_RCP_B1P2_DAO {

	public SumVO getSumB1P2_CCAF_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_CCAF_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB1P2_CCAF_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_CCAF_Parental", sumSQL);
	}

	public SumVO getSumB1P2_CCAF_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_CCAF_postNatal", sumSQL);
	}

	public SumVO getSumB1P2_CCAF_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_CCAF_prenatal", sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_EntidadEmpleadora_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_EntidadEmpleadora_Parental", sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_EntidadEmpleadora_postNatal", sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_EntidadEmpleadora_prenatal", sumSQL);
	}

	public SumVO getSumB1P2_ISP_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_ISP_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB1P2_ISP_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_ISP_Parental", sumSQL);
	}

	public SumVO getSumB1P2_ISP_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_ISP_postNatal", sumSQL);
	}

	public SumVO getSumB1P2_ISP_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB1P2_ISP_prenatal", sumSQL);
	}
}
