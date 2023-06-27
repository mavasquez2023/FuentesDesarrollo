/*
 * Created on 11-10-2011
 *
 */
package cl.araucana.lme.liq.svc;


import java.io.IOException;

import org.apache.log4j.Logger;

import cl.araucana.lme.liq.util.ConstantsLiq;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @author j-factory
 *
 */
public class BaseLmeSvc {

	//private LoggerHelper logger = new LoggerHelper();
	private static Logger log = Logger.getLogger(BaseLmeSvc.class);

	protected static SqlMapClient sqlMap_;

	//	static {
	//		try {
	//			sqlMap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(Constants.RESOURCE_MAP_CONFIG));
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	public BaseLmeSvc() {
		setInstance();
	}

	public void setInstance() {
		try {
			if (sqlMap_ == null)
				sqlMap_ = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(ConstantsLiq.RESOURCE_MAP_CONFIG_LME));
		} catch (IOException e1) {
			//			logger.logFatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
	}

}
