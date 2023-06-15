package cl.laaraucana.simulacion.ibatis.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.SucursalesVO;

public class SucursalesDAO {

	public List<SucursalesVO> consultaSucursales() throws Exception {

		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();

		} catch (Exception e) {
			throw new Exception("Error al conectarse al datasource");
		}
		try {

			@SuppressWarnings("unchecked")
			List<SucursalesVO> result = sqlMap.queryForList("sucursales", null);

			return result;

		} catch (Exception e) {
			throw new Exception("Error al consultar la sucursal" + e.getMessage());
		}

	}

}
