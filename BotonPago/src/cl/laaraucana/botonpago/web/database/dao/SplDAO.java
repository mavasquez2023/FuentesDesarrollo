package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.config.SqlConfigSPL;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Convenio;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Detpago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Mediopago;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Pago;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SplDAO {

	private static Logger logger = Logger.getLogger(SplDAO.class);

	static SqlMapClient sqlMap = null;

	public static SqlMapClient getConn() throws Exception {
		if (sqlMap == null) {
			logger.debug("Se obtiene nueva SqlMapInstance");
			try {
				sqlMap = SqlConfigSPL.getSqlMapInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Falló la conexión al Datasource: " + e.getMessage());
			}
		}
		return sqlMap;
	}

	public Pago getInfoPago(String IdPago) throws Exception {
		Pago pago = new Pago();

		SqlMapClient sqlMap = getConn();

		try {
			pago = (Pago) sqlMap.queryForObject("selectPagoById", IdPago);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error en getInfoPago");
		}
		return pago;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Detpago> getInfoDetallePago(String IdPago) throws Exception {
		ArrayList<Detpago> detalles = new ArrayList<Detpago>();

		SqlMapClient sqlMap = getConn();

		try {
			detalles = (ArrayList<Detpago>) sqlMap.queryForList("selectDetalleByIdPago", IdPago);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error en getInfoDetallePago");
		}
		return detalles;
	}

	public Convenio getConvenioById(String convenioId) throws Exception {
		Convenio convenio = new Convenio();

		SqlMapClient sqlMap = getConn();

		try {
			convenio = (Convenio) sqlMap.queryForObject("getConvenioById", convenioId);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error en getInfoPago");
		}
		return convenio;
	}

	public Mediopago getMedioPagoById(String medioPagoId) throws Exception {
		Mediopago medioPago = new Mediopago();

		SqlMapClient sqlMap = getConn();

		try {
			medioPago = (Mediopago) sqlMap.queryForObject("getMedioPagoById", medioPagoId);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error en getInfoPago");
		}
		return medioPago;
	}

}
