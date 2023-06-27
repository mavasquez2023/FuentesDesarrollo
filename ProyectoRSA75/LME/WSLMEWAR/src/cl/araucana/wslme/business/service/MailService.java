package cl.araucana.wslme.business.service;

import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;

public interface MailService {
	
	public Boolean enviaMail(MailTO mail) throws WSLMEException;
}
