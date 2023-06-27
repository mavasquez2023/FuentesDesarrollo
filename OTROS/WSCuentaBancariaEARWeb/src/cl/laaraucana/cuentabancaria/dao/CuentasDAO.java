package cl.laaraucana.cuentabancaria.dao;

import java.util.List;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.cuentabancaria.config.SqlConfig;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVo;
import cl.laaraucana.cuentabancaria.vo.CuentaVo;

public class CuentasDAO {

	protected static Logger logger = Logger.getLogger(CuentasDAO.class);

	public void setCuenta(CuentaVo cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		sqlMap.insert("cuentasService.setCuentas", cuenta);

	}

	public boolean getCuentaByNumCuenta(CuentaVo cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		CuentaVo cuentaRes = (CuentaVo) sqlMap.queryForObject("cuentasService.getCuentasByNumCuenta", cuenta);

		return cuentaRes == null ? false : true;

	}

	public void editStatusCuenta(CuentaVo cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		sqlMap.update("cuentasService.editStatusCuenta", cuenta);

	}
	
	public List<CuentaDescripcionVo> getConsultaCuenta(CuentaVo cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		@SuppressWarnings("unchecked")
		List<CuentaDescripcionVo> cuentaRes = (List<CuentaDescripcionVo>) sqlMap.queryForList("cuentasService.getConsultaCuenta", cuenta);

		return cuentaRes;

	}
	
	

}
