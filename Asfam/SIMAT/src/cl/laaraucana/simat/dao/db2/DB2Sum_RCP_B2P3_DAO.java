package cl.laaraucana.simat.dao.db2;

import java.io.Reader;

import cl.laaraucana.simat.dao.Sum_RCP_B2P3_DAO;
import cl.laaraucana.simat.entidades.SumVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Sum_RCP_B2P3_DAO implements Sum_RCP_B2P3_DAO {

	public SumVO getSumB2P3_AFP_CAPITAL_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CAPITAL_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CAPITAL_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CAPITAL_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CAPITAL_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CAPITAL_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CAPITAL_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CAPITAL_prenatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CUPRUM_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CUPRUM_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CUPRUM_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CUPRUM_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CUPRUM_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CUPRUM_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_CUPRUM_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_CUPRUM_prenatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_HABITAT_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_HABITAT_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_HABITAT_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_HABITAT_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_HABITAT_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_HABITAT_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_HABITAT_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_HABITAT_prenatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_MODELO_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_MODELO_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_MODELO_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_MODELO_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_MODELO_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_MODELO_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_MODELO_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_MODELO_prenatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PLANVITAL_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PLANVITAL_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PLANVITAL_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PLANVITAL_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PLANVITAL_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PLANVITAL_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PLANVITAL_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PLANVITAL_prenatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PROVIDA_EnfGraveNM(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PROVIDA_EnfGraveNM", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PROVIDA_Parental(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PROVIDA_Parental", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PROVIDA_postNatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PROVIDA_postNatal", sumSQL);
	}

	public SumVO getSumB2P3_AFP_PROVIDA_prenatal(SumVO sumSQL) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		return (SumVO) sqlMap.queryForObject("getSumB2P3_AFP_PROVIDA_prenatal", sumSQL);
	}

}
