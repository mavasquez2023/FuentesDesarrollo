package cl.laaraucana.simat.dao.db2;

import java.io.Reader;

import cl.laaraucana.simat.comun.conx.DB2DAOFactory;
import cl.laaraucana.simat.dao.SMF09_DAO;
import cl.laaraucana.simat.entidades.SMF09_VO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2_SMF09_DAO implements SMF09_DAO {

	public void delEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("delEstadoPeriodoByProceso", smf09);
	}

	public SMF09_VO getEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		return (SMF09_VO) sqlMap.queryForObject("getEstadoPeriodoByProceso", smf09);
	}

	public void setEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.insert("setEstadoPeriodoByProceso", smf09);
	}

	public void upEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.update("upEstadoPeriodoByProceso", smf09);

	}

}
