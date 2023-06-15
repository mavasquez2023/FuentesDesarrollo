package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CuponDAO extends DaoParent {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * retorna los cupones segun el estado y el rut
	 * @param cupon
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BpagF002> cuponesByEstadoAndRut(BpagF002 cupon) throws Exception {
		List<BpagF002> cupones = new ArrayList<BpagF002>();
		SqlMapClient sqlMap = getConn();

		try {
			cupones = (List<BpagF002>) sqlMap.queryForList("cuponesByEstadoAndRut", cupon);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cupones: cuponesByEstadoAndRut");
			throw new Exception("Error en cuponesByEstadoAndRut");
		}
		return cupones;
	}
		/**
	 * retorna los cupones segun el folio, el estado y el rut.
	 * @param cupon
	 * @return
	 * @throws Exception
	 */

	public BpagF002 cuponByEstadoAndFolioAndRut(BpagF002 cuponEntrada) throws Exception {
		SqlMapClient sqlMap = getConn();
		BpagF002 cuponSalida;
		try {
			cuponSalida = (BpagF002) sqlMap.queryForObject("cuponByEstadoAndFolioAndRut", cuponEntrada);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error en la consulta de cupones :cuponByEstadoAndFolioAndRut");
			throw new Exception("Error al obtener el Certificado de pago");
		}
		return cuponSalida;
	}

	/**
	 * 
	 * @param cupon
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BpagF002> cuponesByEstado(String estadoProceso) throws Exception {
		SqlMapClient sqlMap = getConn();
		List<BpagF002> cupones = new ArrayList<BpagF002>();
		try {
			cupones = (List<BpagF002>) sqlMap.queryForList("cuponesByEstado", estadoProceso);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cupones :cuponesByEstado");
			throw new Exception("Error en cuponesByEstado");
		}
		return cupones;
	}

	@SuppressWarnings("unchecked")
	public List<BpagF002> cuponesEstadoAndTipoPago(BpagF002 cupon) throws Exception {
		SqlMapClient sqlMap = getConn();
		List<BpagF002> cupones = new ArrayList<BpagF002>();
		try {
			cupones = (List<BpagF002>) sqlMap.queryForList("cuponesEstadoAndTipoPago", cupon);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cupones :cuponesEstadoAndTipoPago");
			throw new Exception("Error en cuponesEstadoAndTipoPago");
		}
		return cupones;
	}

	@SuppressWarnings("unchecked")
	public List<BpagF002> cuponesGeneradosOfiproCrefol(BpagF002 cupon) throws Exception {
		SqlMapClient sqlMap = getConn();
		List<BpagF002> cupones = new ArrayList<BpagF002>();
		try {
			cupones = (List<BpagF002>) sqlMap.queryForList("cuponesGeneradoOfiproCrefol", cupon);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cupones: cuponesGeneradosOfiproCrefol");
			throw new Exception("Error en cuponesGenerados");
		}
		return cupones;
	}

	/**
	 * Realiza el ingreso de cupon en la base de datos.
	 * @param cupon
	 * @throws Exception
	 */
	public String ingresaCupon(BpagF002 cupon) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			String codBarra = (String) sqlMap.insert("ingresaCupon", cupon);
			logger.debug("Se ingresa el cupon en BPAGF002, el codigo de barra ingresado es " + codBarra);
			return codBarra;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al registrar el cupon en tabla del sistema");
			throw new Exception("Error al registrar cupon");
		}
	}

	/**
	 * cambia el estado de un cupon segun el rut, folio y estado
	 * y se cambia por el valor estadoParaActualizar de BpagF002
	 * @param cupon
	 * @throws Exception
	 */
	public void cambiaEstadoCuponByRutTesFolEsta(BpagF002 cupon) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.update("cambiaEstadoCuponByRutTesFolEsta", cupon);
			logger.debug("Anula cupon en tabla del sistema.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al Anular cupon en tabla del sistema.");
			throw new Exception("Error en cambiaEstadoCuponByRutTesFolProce");
		}
	}

	public void cursaCuponByRutTesFolEsta(BpagF002 cupon) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.update("cursaCuponByRutTesFolEsta", cupon);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al cursar el cupon en tabla del sistema.");
			throw new Exception("Error en cursaCuponByRutTesFolEsta");
		}
	}

	public BpagF002 cuponByTesFol(String tesfol) throws Exception {
		SqlMapClient sqlMap = getConn();
		BpagF002 cup = new BpagF002();
		try {
			cup = (BpagF002) sqlMap.queryForObject("cuponByTesFol", tesfol);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta del cupon: cuponByTesFol");
			throw new Exception("Error en cuponByTesFol");
		}
		return cup;
	}

	public String getCorrelativo() throws Exception {
		String medioPago = "";
		SqlMapClient sqlMap = getConn();
		try {
			medioPago = (String) sqlMap.queryForObject("obtenerCorrelativoSPL", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener correlativo para SPL");
			throw new Exception("Error en getCorrelativoSpl");
		}
		return medioPago;
	}
	

	/**
	 * retorna los cupones segun el estado y el rut
	 * @param cupon
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BpagF002> getPagosDelDiaByRut(String afirut) throws Exception {
		List<BpagF002> cupones = new ArrayList<BpagF002>();
		SqlMapClient sqlMap = getConn();

		try {
			cupones = (List<BpagF002>) sqlMap.queryForList("obtenerPagosDia", afirut);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cupones: cuponesByEstadoAndRut");
			throw new Exception("Error en cuponesByEstadoAndRut");
		}
		return cupones;
	}

}
