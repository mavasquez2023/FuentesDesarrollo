package cl.laaraucana.cuentabancaria.dao;

import cl.laaraucana.cuentabancaria.config.SqlConfig;
import cl.laaraucana.cuentabancaria.vo.CredencialesVo;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CredencialesDAO {

	public CredencialesVo consultaCredenciales(CredencialesVo user) throws Exception {
		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		CredencialesVo query = (CredencialesVo) sqlMap.queryForObject("cuentasService.consultaCredenciales", user);
		return query;

	}

	public String consultaCanal(UsuarioVo user) throws Exception {
		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		String query = (String) sqlMap.queryForObject("cuentasService.getCanal", user);

		return query;

	}
	
	public int consultaCodigoCanal(UsuarioVo user) throws Exception {
		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		Integer query = (Integer) sqlMap.queryForObject("cuentasService.getCodCanal", user);

		return query;

	}
	
	

}
