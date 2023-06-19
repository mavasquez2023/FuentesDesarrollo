package cl.laaraucana.benef.dao;

import cl.laaraucana.benef.config.SqlConfig;
import cl.laaraucana.benef.vo.CredencialesVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CredencialesDAO {

	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception {
		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		CredencialesVO query = (CredencialesVO) sqlMap.queryForObject("credencialesService.consultaCredenciales", user);
		return query;

	}
	

}
