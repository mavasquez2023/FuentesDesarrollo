package cl.araucana.spl.dao.sqlmap;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.dao.ParametroDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class ParametroSqlMapDAO extends SqlMapDaoTemplate implements
		ParametroDAO {

	private static final String SQL_FIND_SP_SPL_PRIVADA = "sqlIPPrivadaSPL";

	private static final String SQL_FIND_SRVREC = "sqlSrvrecPago";
	private static final String SQL_GET_PARAM = "getParametros";

	private static final Logger logger = Logger
			.getLogger(ParametroSqlMapDAO.class);

	public ParametroSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	/**
	 * Retorna el srvrec de la tabla parametro, biblioteca cpedta
	 */
	public String getSrvrecPago() {
		String valor = (String) queryForObject(SQL_FIND_SRVREC,
				Constants.ID_PARAMETRO_SRVREC);
		if (logger.isDebugEnabled()) {
			logger.debug("Parametro " + Constants.ID_PARAMETRO_SRVREC
					+ ": valor SRVREC " + valor);
		}
		return valor;
	}

	/**
	 * Retorna la IP privada de SPL
	 */
	public String getIpSPLPrivada() {
		String valor = (String) queryForObject(SQL_FIND_SP_SPL_PRIVADA,
				Constants.ID_PARAMETRO_IP_SPL_PRIVADA);
		if (logger.isDebugEnabled()) {
			logger.debug("Parametro " + Constants.ID_PARAMETRO_IP_SPL_PRIVADA
					+ ": valor IP PRIVADA SPL " + valor);
		}
		return valor;
	}
	/**
	 * Retorna los parametros de la tabla SPLPARAM
	 */
	public HashMap getParametrosSPLPARAM() {
		HashMap map = new HashMap();
		map = (HashMap) queryForMap(SQL_GET_PARAM, null, "key", "value");
		return (map);
	}

}
