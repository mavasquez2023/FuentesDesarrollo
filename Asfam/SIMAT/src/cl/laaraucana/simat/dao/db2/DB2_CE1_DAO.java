package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.CE1_DAO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2_CE1_DAO implements CE1_DAO {

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Autorizados_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Autorizados_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Autorizados_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Rechazados_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Rechazados_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Rechazados_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Reconsiderados_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_Modificadas_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_Modificadas_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_Modificadas_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_Reconsideradas_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_SinModificar_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_SinModificar_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Aut_SinModificar_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Rechazadas_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Post_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Rechazadas_Post_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Rechazadas_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getCE1_Num_Lic_Reconsideradas_Enf_menor(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getCE1_Num_Lic_Reconsideradas_Enf_menor", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}
}
