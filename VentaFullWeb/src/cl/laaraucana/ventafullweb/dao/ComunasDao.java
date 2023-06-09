package cl.laaraucana.ventafullweb.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventafullweb.dao.config.SqlMapLocator;
import cl.laaraucana.ventafullweb.dto.ComunasDto;

public class ComunasDao {

	@SuppressWarnings("unchecked")
	public List<ComunasDto> getComunas() throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		List<ComunasDto> resultado = (List<ComunasDto>) sqlMap.queryForList("comuna.selectComuna","");
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComunasDto> getComunasByRegion(ComunasDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		List<ComunasDto> resultado = (List<ComunasDto>) sqlMap.queryForList("comuna.selectComunaByRegion", data);
		return resultado;
	}
	
}
