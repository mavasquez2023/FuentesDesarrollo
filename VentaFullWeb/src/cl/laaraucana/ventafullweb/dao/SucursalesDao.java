package cl.laaraucana.ventafullweb.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventafullweb.dao.config.SqlMapLocator;
import cl.laaraucana.ventafullweb.dto.SucursalesDto;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;

public class SucursalesDao {

	@SuppressWarnings("unchecked")
	public List<SucursalesDto> getSucursales() throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		List<SucursalesDto> resultado = (List<SucursalesDto>) sqlMap.queryForList("sucursal.selectSucursal","");
		return resultado;
	}
	
	public void insertBitacoraSimulacion(AfiliadoVo data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraSimulacion", data);

	}
	
	public void insertBitacoraGenesys(AfiliadoVo data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraSimulacion", data);

	}
}
