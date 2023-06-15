package cl.laaraucana.simulacion.ibatis.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.CuotasVO;

public class CuotasDAO {

	
	public List<CuotasVO> consultaCuotas() throws Exception {

		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();

		} catch (Exception e) {
			throw new Exception("Error al conectarse al datasource");
		}
		try {

			@SuppressWarnings("unchecked")
			List<CuotasVO> result = sqlMap.queryForList("cuotas", null);

			return result;

		} catch (Exception e) {
			throw new Exception("Error al consultar la cuotas" + e.getMessage());
		}

	}
}
