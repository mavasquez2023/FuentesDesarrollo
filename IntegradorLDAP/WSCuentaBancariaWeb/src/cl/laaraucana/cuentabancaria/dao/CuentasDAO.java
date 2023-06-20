package cl.laaraucana.cuentabancaria.dao;

import java.util.List;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.cuentabancaria.config.SqlConfig;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRevocaVO;
import cl.laaraucana.cuentabancaria.vo.CuentaSearchVO;
import cl.laaraucana.cuentabancaria.vo.CuentaVO;;

public class CuentasDAO {

	protected static Logger logger = Logger.getLogger(CuentasDAO.class);

	public void setCuenta(CuentaVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		sqlMap.insert("cuentasService.setCuentas", cuenta);

	}

	public boolean getCuentaByNumCuenta(CuentaVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		String cuentaRes = (String) sqlMap.queryForObject("cuentasService.getCuentasByNumCuenta", cuenta);

		return cuentaRes == null ? false : true;

	}
	
	public boolean getCuentaVigente(CuentaRevocaVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		String cuentaRes = (String) sqlMap.queryForObject("cuentasService.getCuentaVigente", cuenta);

		return cuentaRes == null ? false : true;

	}
	
	public boolean getCuentaByNumCuenta(CuentaRevocaVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		CuentaVO cuentaRes = (CuentaVO) sqlMap.queryForObject("cuentasService.getCuentasByNumCuenta", cuenta);

		return cuentaRes == null ? false : true;

	}
	
	
	public void revocarCuenta(CuentaRevocaVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		sqlMap.update("cuentasService.editStatusCuenta", cuenta);

	}
	
	public List<CuentaDescripcionVO> getConsultaCuenta(CuentaSearchVO cuenta) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		@SuppressWarnings("unchecked")
		List<CuentaDescripcionVO> cuentaRes = (List<CuentaDescripcionVO>) sqlMap.queryForList("cuentasService.getConsultaCuenta", cuenta);

		return cuentaRes;

	}
	
	

}
