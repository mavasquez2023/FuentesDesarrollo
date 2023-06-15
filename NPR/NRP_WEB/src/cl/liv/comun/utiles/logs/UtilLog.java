package cl.liv.comun.utiles.logs;

import org.apache.commons.logging.impl.Log4JLogger;

public class UtilLog {
	
	public static void debug(String logger, String texto) {
		Log4JLogger log = new Log4JLogger(logger);
		log.debug( texto);
	}

	public static void info(String logger, String texto) {
		Log4JLogger log = new Log4JLogger(logger);
		log.info( texto);
	}

	public static void error(String logger, String texto) {
		Log4JLogger log = new Log4JLogger(logger);
		log.error( texto);
	}
	public static void error(String logger, Throwable e) {
		Log4JLogger log = new Log4JLogger(logger);
		log.error("error", e);
	}
	public static void fatal(String logger, String texto) {
		Log4JLogger log = new Log4JLogger(logger);
		log.fatal( texto);
	}
	public static void fatal(String logger, Throwable e) {
		Log4JLogger log = new Log4JLogger(logger);
		log.fatal("fatal", e);
	}

}
