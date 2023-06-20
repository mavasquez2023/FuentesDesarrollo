package cl.laaraucana.transferencias.ibatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.UsuarioVo;
import cl.laaraucana.transferencias.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.transferencias.ibatis.vo.RegistroGestorClaveVo;

public class CuentaDaoImpl implements CuentaDao {

	private static final Logger logger = Logger.getLogger(CuentaDaoImpl.class);

	@Override
	public UsuarioVo consultaCredenciales(CredencialesVO user) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			UsuarioVo query = (UsuarioVo) sqlMap.queryForObject("cuentas.consultaCredenciales", user);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroGestorClaveVo> findRegisterKeyByRut(long rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<RegistroGestorClaveVo> queryForList = sqlMap.queryForList("cuentas.getRegistroGestor", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	

	@Override
	public void updateRegisterKeyByRut(Map<String, Object> param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			sqlMap.update("cuentas.getreggestionClaveByRut", param);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}



}
