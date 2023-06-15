package cl.laaraucana.botonpago.web.database.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DatosDeudorDAO extends DaoParent {

	private Logger logger = Logger.getLogger(this.getClass());

	public Bpagf001 getDatoDeudorByRut(String rut, String dv) throws Exception {
		SqlMapClient sqlMap = getConn();
		Bpagf001 deudor = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("rut", rut);
			map.put("dv", dv);
			deudor = (Bpagf001) sqlMap.queryForObject("selectDeudor", map);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar deudor directo");
			//			throw new Exception("Error al consultar deudor directo");
		}
		return deudor;
	}
	
	public String getFechaPagoFuturo(String folio) throws Exception {
		SqlMapClient sqlMap = getConn();
		String fecha_pago_futuro = null;
		try {
			fecha_pago_futuro = (String) sqlMap.queryForObject("selectFechaFutura", folio);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar deudor directo");
			//			throw new Exception("Error al consultar deudor directo");
		}
		return fecha_pago_futuro;
	}
	
	public boolean insertFechaPagoFuturo(HashMap<String, String> param) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.delete("deleteFechaFutura", param);
			sqlMap.insert("insertFechaFutura", param);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al insertar fecha pago futuro");
			return false;
		}
	}

	public void insertDeudor(Bpagf001 datos) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.insert("insert", datos);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al registrar deudor directo");
			throw new Exception("Error al registrar deudor directo");
		}
	}
	
}
