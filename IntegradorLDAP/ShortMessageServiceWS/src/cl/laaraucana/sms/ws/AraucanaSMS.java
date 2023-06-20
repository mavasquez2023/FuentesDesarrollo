package cl.laaraucana.sms.ws;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.domain.exchange.MessageOutput;
import cl.laaraucana.sms.domain.exchange.StatusSMSInput;
import cl.laaraucana.sms.domain.exchange.StatusSMSOutput;
import cl.laaraucana.sms.service.remote.SendSMSService;
import cl.laaraucana.sms.service.remote.StatusSMSService;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class AraucanaSMS {

    private static final Logger logger = Logger.getLogger(AraucanaSMS.class);

    @WebMethod
    public MessageOutput sendSMS(MessageInput input) {
	try {
	    // Service
	    SendSMSService sendSMSService = new SendSMSService();
	    MessageOutput output = sendSMSService.sendSMS(input);

	    // Response
	    return output;
	} catch (Exception e) {
	    logger.error("Error processing sendSMS call", e);
	}
	return null;
    }

    @WebMethod
    public StatusSMSOutput statusSMS(StatusSMSInput input) {
	try {
	    // Service
	    StatusSMSService statusSMSService = new StatusSMSService();
	    StatusSMSOutput output = statusSMSService.statusSMS(input);

	    // Response
	    return output;
	} catch (Exception e) {
	    logger.error("Error processing statusSMS call", e);
	}
	return null;
    }
}