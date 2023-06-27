package cl.araucana.wslme.business.service.impl;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.MailService;
import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.integration.mail.MailConexion;
import cl.araucana.wslme.integration.mail.impl.MailConexionImpl;

public class MailServiceImpl implements MailService{
	private Logger log = Logger.getLogger(MailServiceImpl.class);
	
	public Boolean enviaMail(MailTO mail) throws WSLMEException{
		log.info("Enviado mail a: " + mail.getPara());
		
		MailConexion conMail = new MailConexionImpl();
		return conMail.sendMail(mail);
	}
}