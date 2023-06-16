package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F1;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F2;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TesoreriaDAO extends DaoParent {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Realiza el ingreso de tesoreria en la base de datos.
	 * @param cupon
	 * @throws Exception
	 */

	public void ingresaTesoreria(TE07F1 teso) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.insert("ingresaTesoreria", teso);
			logger.debug("Se ingresa cupon en tesoreria");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al ingresar el cupon en tesoreria");
			throw new Exception("Error al registrar tesoreria");
		}
	}

	public void cambiaEstTesoDeGenerado(TE07F1 tes, String estadoNuevo) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			TE07F1 tesoreria = tes;
			tesoreria.setTe3ya(estadoNuevo);
			//EL UPDATE SOLO ANULA LOS QUE SE ENCUENTREN CON ESTADO (IMPRESO)
			sqlMap.update("cambiaEstTesoDeGenerado", tesoreria);
			logger.debug("Anula cupones generados en tesoreria.");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al anular cupones generados en tesoreria.");
			throw new Exception("Error en anulaCuponByOfiproCrefolTesFolEst");
		}
	}

	

	/**
	 * ingresa el detalle de la tesoreria
	 * @param detTeso
	 * @throws Exception
	 */
	public void ingresaDetalleTesoreria(TE07F2 detTeso) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.insert("ingresaDetalleTesoreria", detTeso);
			logger.debug("Se ingresa el detalle en tesoreria.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al ingresar el detalle de tesoreria");
			throw new Exception("Error al registrar detalle de tesoreria");
		}
	}
	
	public String getCodAreaNegocio(String value) throws Exception {
		String dato;
		SqlMapClient sqlMap = getConn();
		try {
			dato = (String)sqlMap.queryForObject("getCodAreaNegocio", value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar getCodAreaNegocio");
		}
//		logger.debug("Salida: " + datos);

		return dato;
	}

}
