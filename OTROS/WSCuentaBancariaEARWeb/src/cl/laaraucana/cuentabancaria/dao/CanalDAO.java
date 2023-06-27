package cl.laaraucana.cuentabancaria.dao;

 

import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.cuentabancaria.config.SqlConfig;
import cl.laaraucana.cuentabancaria.vo.CanalVo;

public class CanalDAO {

	 

	public void setCanal(CanalVo canal) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		sqlMap.insert("canalService.setCanal", canal);

	}

}
