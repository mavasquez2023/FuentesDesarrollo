package cl.liv.comun.utiles.logs;

public class UtilLogErrores {

	public static void info(String texto){
		UtilLog.info(MiLogUtil.LOG_ERRORES , texto);
	}
	public static void debug(String texto){
		UtilLog.debug(MiLogUtil.LOG_ERRORES , texto);
	}
	public static void error(String texto){
		UtilLog.error(MiLogUtil.LOG_ERRORES, texto);
	}
	public static void fatal(String texto){
		UtilLog.fatal(MiLogUtil.LOG_ERRORES , texto);
	}
	
	public static void error(Throwable e){
		UtilLog.error(MiLogUtil.LOG_ERRORES, e);
	}
	public static void fatal(Throwable e){
		UtilLog.fatal(MiLogUtil.LOG_ERRORES , e);
	}
	
}
