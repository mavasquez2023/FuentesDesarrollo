package cl.laaraucana.simat.dao.db2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.simat.comun.conx.DB2DAOFactory;
import cl.laaraucana.simat.dao.PaseContableDAO;
import cl.laaraucana.simat.entidades.CuentaPaseContableVO;

public class DB2PaseContableDAO implements PaseContableDAO {

	/**
	 * Retorna la sumatoria de los montos para generar pase contable
	 */
	public List<CuentaPaseContableVO> getCuentasPaseContable() throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		@SuppressWarnings("unchecked")
		List<CuentaPaseContableVO> cuentas = (List<CuentaPaseContableVO>) sqlMap.queryForList("getCuentasPaseContable", null);
		return cuentas;
	}

	/**
	 * Invoca al programa AS400 que crea el pase contable
	 */
	public boolean crearPaseContableAs400(String periodo) throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		Map<String, String> entrada = new HashMap<String, String>();
		entrada.put("periodo", periodo);
		sqlMap.queryForObject("creaPaseContableAs400", entrada);
		
		return true;
	}

	/**
	 * Ingresa registro de pases contables
	 */
	public boolean ingresarPaseContable(List<CuentaPaseContableVO> cuentas, String periodo) throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		int cant=0;
		for (CuentaPaseContableVO cuentaPaseContableVO : cuentas) {
			cuentaPaseContableVO.setPeriodo(periodo);
			sqlMap.insert("ingresarPaseContable", cuentaPaseContableVO);
		}
		return true;
	}

	/**
	 * Vacía periodo de tabla SMF11 para generar nuevo pase contable
	 */
	public int borrarPaseContable(String periodo) throws Exception {
		SqlMapClient sqlMap = DB2DAOFactory.getDB2Connection();
		int cant = sqlMap.delete("borrarPeriodoPaseContable", periodo);
		return cant;
	}

}
