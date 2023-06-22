package cl.laaraucana.simulacion.ibatis.dao;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.ibatis.vo.ValorUFVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UtilesDAO {
	public static ValorUFVO consultaValorUF(String fecha) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectarse al datasource");
		}
		try {
			Object uf = sqlMap.queryForObject("consultaValorUF", fecha);
			if (uf != null) {
				return (ValorUFVO) uf;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar el valor UF");
		}
		return null;
	}

	public static ParametroVO consultaParametro(int codigoSimulador, int codigoParametro) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectarse al datasource");
		}
		try {
			ParametroVO parametroVO = new ParametroVO();
			parametroVO.setCodigoSimulador(codigoSimulador);
			parametroVO.setCodigoParametro(codigoParametro);			
			Object parametro = (ParametroVO) sqlMap.queryForObject("consultaParametro", parametroVO);
			if (parametro != null) {
				return (ParametroVO) parametro;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar parametro de simulacion");
		}
		return null;
	}
}
