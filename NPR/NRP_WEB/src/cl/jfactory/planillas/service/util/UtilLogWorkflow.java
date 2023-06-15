package cl.jfactory.planillas.service.util;

import cl.liv.comun.utiles.logs.MiLogUtil;
import cl.liv.comun.utiles.logs.UtilLog;

public class UtilLogWorkflow {

	public static void info(String texto){
		UtilLog.info(MiLogUtil.LOG_WORKFLOW , texto);
	}
	public static void debug(String texto){
		UtilLog.debug(MiLogUtil.LOG_WORKFLOW , texto);
	}
	public static void error(String texto){
		UtilLog.error(MiLogUtil.LOG_WORKFLOW , texto);
	}
	public static void fatal(String texto){
		UtilLog.fatal(MiLogUtil.LOG_WORKFLOW , texto);
	}
	
}
