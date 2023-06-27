/*
 * Created on 11-10-2011
 *
 */
package com.microsystem.lme.svc;

//import com.microsystem.lme.helper.LoggerHelper;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.microsystem.lme.util.Constants;

/**
 * @author microsystem
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
				sqlMap_ = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(Constants.RESOURCE_MAP_CONFIG));
		} catch (IOException e1) {
			//			logger.logFatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n" + e1);
		}
	}

}
