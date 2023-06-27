package cl.laaraucana.capaservicios.database.dao;

import java.util.ArrayList;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.capaservicios.database.vo.CuentaCorrienteVO;
import cl.laaraucana.capaservicios.util.RutUtil;


public class CuentaCorrienteDAO extends DaoParent {
	
	/**
	 * Consulta cuentas corrientes de cliente en AS400
	 * @param rut
	 * @return Lista de cuentas corrientes
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CuentaCorrienteVO> getCtasCorrientesByRut(String rut)
			throws Exception {
		SqlMapClient sqlMap = getConn();
		List<CuentaCorrienteVO> cuentas = new ArrayList<CuentaCorrienteVO>();
		CuentaCorrienteVO entrada = new CuentaCorrienteVO(RutUtil.getLongRut(rut),RutUtil.getDv(rut));
		try {
			cuentas = (List<CuentaCorrienteVO>) sqlMap.queryForList("getCtasCorrientesByRut", entrada);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return cuentas;
	}
	
	/**
	 * Agrega cuenta corriente en tabla LCDTA.LC10CTAAFIL, verifica primero si ya existe.
	 * @param cuenta
	 * @throws Exception
	 */
	public void agregarCtaCorriente(CuentaCorrienteVO cuenta)
			throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			
			int cant = (Integer) sqlMap.queryForObject("verificarExistenciaCta", cuenta);
			if(cant > 0) throw new Exception("La cuenta corriente ya existe");//Error de clave duplicada
			sqlMap.insert("insertarCtaCorriente", cuenta);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 	Elimina cuenta corriente de usuario desde tabla
	 * @param rut
	 * @param nroCuenta
	 * @return true si eliminó la cuenta y false si no existe
	 * @throws Exception
	 */
	public boolean eliminarCtaCorriente(String rut, String nroCuenta) throws Exception{
		SqlMapClient sqlMap = getConn();
		boolean eliminada = false;
		CuentaCorrienteVO entrada = new CuentaCorrienteVO(RutUtil.getLongRut(rut),RutUtil.getDv(rut));
		entrada.setNroCuenta(nroCuenta);
		try {
			eliminada = (sqlMap.delete("eliminarCtaCorriente", entrada) == 1) ? true : false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return eliminada;
	}
}
