package cl.laaraucana.sms.assembly;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.domain.exchange.MessageInput;

public class ExchangeDebugger {

	private static final Logger logger = Logger.getLogger(ExchangeDebugger.class);

	public static void debugMessageInput(MessageInput input) {
		logger.info("username : " + input.getUsername());
		logger.info("password : " + input.getPassword());
		logger.info("rut      : " + input.getRut());
		logger.info("dv       : " + input.getDv());
		logger.info("phone    : " + input.getPhone());
		logger.info("message  : " + input.getMessage());
		logger.info("url bool : " + input.getUrlCondition());
		logger.info("url text : " + input.getUrlText());
	}
}