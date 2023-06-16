package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;

import cl.laaraucana.simat.dao.Count_Lic_AutorizadasDAO;
import cl.laaraucana.simat.entidades.CountVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2Count_Lic_autorizadasDAO implements Count_Lic_AutorizadasDAO {

	public CountVO getNum_Lic_Autorizadas_Pre_natal(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getNum_Lic_Autorizadas_Pre_natal", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getNum_Lic_Autorizadas_Post_natal_Madre(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getNum_Lic_Autorizadas_Post_natal_Madre", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getNum_Lic_Autorizadas_Post_natal_Padre(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getNum_Lic_Autorizadas_Post_natal_Padre", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	/*
	 public CountVO num_pers_subs_parental_Madre(CountVO qid) throws Exception {
			String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			
			try {
				qid = (CountVO) sqlMap.queryForObject("getNum_pers_subs_Parental_Madre",qid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return qid;
		}

		public CountVO num_pers_subs_parental_Padre(CountVO qid) throws Exception {
			String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			
			try {
				qid = (CountVO) sqlMap.queryForObject("getNum_pers_subs_Parental_Padre",qid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return qid;
		}
	 
	 **/

	public CountVO getNum_Lic_Autorizadas_Enf_menor_Madre(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getNum_Lic_Autorizadas_Enf_menor_Madre", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO getNum_Lic_Autorizadas_Enf_menor_Padre(CountVO qid) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			qid = (CountVO) sqlMap.queryForObject("getNum_Lic_Autorizadas_Enf_menor_Padre", qid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qid;
	}

}
