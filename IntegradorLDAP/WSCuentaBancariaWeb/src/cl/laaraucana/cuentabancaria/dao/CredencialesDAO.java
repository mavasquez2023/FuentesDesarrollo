package cl.laaraucana.cuentabancaria.dao;

import cl.laaraucana.cuentabancaria.config.SqlConfig;
import cl.laaraucana.cuentabancaria.vo.CredencialesVO;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CredencialesDAO {

	public CredencialesVO consultaCredenciales(CredencialesVO user) throws Exception {
		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		CredencialesVO query = (CredencialesVO) sqlMap.queryForObject("cuentasService.consultaCredenciales", user);
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
