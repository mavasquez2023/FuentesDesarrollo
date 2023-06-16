package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F1;
import cl.laaraucana.botonpago.web.database.vo.EntradaTesoCuponVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CuponYTesoreriaDAO extends DaoParent {
	private Logger logger = Logger.getLogger(this.getClass());

	/********************DEMONIO UNA VEZ AL MES**************************/

	/**
	 * obtiene los datos de tesoreria que se se encuentran en la tabla de 
	 * cupones y en estado generaods
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TE07F1> getTesoreriaGeneradoEnCupones(EntradaTesoCuponVO estados) throws Exception {
		List<TE07F1> tesoreriaList = new ArrayList<TE07F1>();
		SqlMapClient sqlMap = getConn();
		try {
			tesoreriaList = (List<TE07F1>) sqlMap.queryForList("getTesoreriaGeneradoEnCupones", estados);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar cupones pagados");
			throw new Exception("Error en el getTesoreriaGeneradoEnCupones");
		}
		return tesoreriaList;
	}

	/**
	 * cambia el estado de tesoreria segun el estado que se encuentra en tesoreria 
	 * y en la tabla de cupones.
	 * @throws Exception
	 */
	//	public int cambiaEstadoTesoreriaSegunEstadoEnCupones(EntradaTesoCuponVO estados) throws Exception {
	//		int n=0;
	//		SqlMapClient sqlMap = getConn();
	//		try {
	//			n = sqlMap.update("cambiaEstadoTesoreriaSegunEstadoEnCupones", estados);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw new Exception("Error al editar el anulaTesoreriaConCuponesGenerados");
	//		}
	//		return n;
	//	}
	/**
	 * cambia el estado de tesoreria segun el estado que se encuentra en tesoreria 
	 * y en la tabla de cupones.
	 * @throws Exception
	 */
	public int anulaTesoreriaDemonio(EntradaTesoCuponVO estados) throws Exception {
		int n = 0;
		SqlMapClient sqlMap = getConn();
		try {
			n = sqlMap.update("anulaTesoreriaPorDemonio", estados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al editar el anulaTesoreriaDemonio");
		}
		return n;
	}

	/**
	 * cambia el estado del cupon segun otro estado.
	 * @throws Exception
	 */
	public int cambiaEstadoCuponSegunEstado(EntradaTesoCuponVO estados) throws Exception {
		SqlMapClient sqlMap = getConn();
		int n = 0;
		try {
			n = sqlMap.update("cambiaEstadoCuponSegunEstado", estados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al editar el anulaCuponesGenerados");
		}
		return n;
	}

	/********************FIN DEMONIO UNA VEZ AL MES***********************/

	/********************DEMONIO A CADA UNA HORA***********************/

	/**
	 * obtiene los cupones generados por caja y que se encuentren cursados en tesoreria
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BpagF002> getCuponesGeneradosPorCajaVsTesoreria(EntradaTesoCuponVO estados) throws Exception {
		List<BpagF002> tesoreriaList = new ArrayList<BpagF002>();
		SqlMapClient sqlMap = getConn();

		try {
			tesoreriaList = (List<BpagF002>) sqlMap.queryForList("getCuponesGeneradosPorCajaVsTesoreria", estados);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error al consultar cupones pagados");
			throw new Exception("Error en el getCuponesGeneradosPorCajaVsTesoreria");
		}
		return tesoreriaList;
	}

	/**
	 * actualiza los cupones generados por caja y que se encuentren cursados en tesoreria
	 * @throws Exception
	 */
	public int cursaCuponesPorCajaDesdeTesoreria(EntradaTesoCuponVO estados) throws Exception {
		SqlMapClient sqlMap = getConn();
		int u = 0;
		try {
			u = sqlMap.update("cursaCuponesPorCajaDesdeTesoreria", estados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al editar el cursaCuponesPorCajaDesdeTesoreria");
		}
		return u;
	}

	/********************FIN DEMONIO A CADA UNA HORA***********************/

}
