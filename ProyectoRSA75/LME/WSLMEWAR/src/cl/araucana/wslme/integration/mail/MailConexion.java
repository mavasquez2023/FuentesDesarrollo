package cl.araucana.wslme.integration.mail;

import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;

public interface MailConexion {
	public Boolean sendMail(MailTO mail) throws WSLMEException;
}
