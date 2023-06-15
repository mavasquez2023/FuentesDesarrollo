package cl.liv.comun.utiles.logs;

public class UtilLogGeneracion {

	public static void info(String texto){
		UtilLog.info(MiLogUtil.LOG_GENERACION , texto);
	}
	public static void debug(String texto){
		UtilLog.debug(MiLogUtil.LOG_GENERACION , texto);
	}
	public static void error(String texto){
		UtilLog.error(MiLogUtil.LOG_GENERACION, texto);
	}
	public static void fatal(String texto){
		UtilLog.fatal(MiLogUtil.LOG_GENERACION , texto);
	}
	
	public static void error(Throwable e){
		UtilLog.error(MiLogUtil.LOG_GENERACION, e);
	}
	public static void fatal(Throwable e){
		UtilLog.fatal(MiLogUtil.LOG_GENERACION , e);
	}
	
}
