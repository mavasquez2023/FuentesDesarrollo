package cl.liv.cargas.masivas.util;

import cl.liv.comun.utiles.logs.MiLogUtil;
import cl.liv.comun.utiles.logs.UtilLog;

public class UtilLogCargas {

	public static void info(String texto){
		UtilLog.info(MiLogUtil.LOG_CARGAS_MASIVAS , texto);
	}
	public static void debug(String texto){
		UtilLog.debug(MiLogUtil.LOG_CARGAS_MASIVAS , texto);
	}
	public static void error(String texto){
		UtilLog.error(MiLogUtil.LOG_CARGAS_MASIVAS , texto);
	}
	public static void fatal(String texto){
		UtilLog.fatal(MiLogUtil.LOG_CARGAS_MASIVAS , texto);
	}
	
}
