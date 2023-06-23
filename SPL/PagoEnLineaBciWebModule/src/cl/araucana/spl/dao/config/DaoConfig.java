package cl.araucana.spl.dao.config;

import java.io.Reader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibatis.common.logging.LogFactory;
import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

public class DaoConfig {
	
	private static final Logger logger = Logger.getLogger(DaoConfig.class);
	
	private static final String resource = "cl/araucana/spl/dao/config/dao.xml";

	private static DaoManager daoManager;

	public static DaoManager getDaoManager() {
		if (daoManager == null) {
			LogFactory.selectLog4JLogging(); // Se fuerza uso de log4j para ibatis logging
			
			daoManager = newDaoManager(null);
		}
		return daoManager;
	}

	private static DaoManager newDaoManager(Properties props) {
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			return DaoManagerBuilder.buildDaoManager(reader, props);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not initialize DaoConfig.  Cause: " + e, e);
		}
	}
	
	public static void startTransaction() {
		getDaoManager().startTransaction();
	}
	public static void commitTransaction() {
		getDaoManager().commitTransaction();
	}
	public static void endTransaction() {
		try {
			getDaoManager().endTransaction();
		} catch (Exception e) {
			// Se ignora.
			logger.error("Ha ocurrido un error al terminar la transaccion (rollback o... después de commit)", e);
		}
	}
}
