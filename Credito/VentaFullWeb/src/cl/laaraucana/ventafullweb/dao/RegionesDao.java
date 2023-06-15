package cl.laaraucana.ventafullweb.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventafullweb.dao.config.SqlMapLocator;
import cl.laaraucana.ventafullweb.dto.RegionesDto;

public class RegionesDao {

	@SuppressWarnings("unchecked")
	public List<RegionesDto> getRegiones() throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		List<RegionesDto> resultado = (List<RegionesDto>) sqlMap.queryForList("region.selectRegion","");
		return resultado;
	}
	
}
