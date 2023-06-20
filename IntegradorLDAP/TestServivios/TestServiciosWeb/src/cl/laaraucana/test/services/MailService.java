package cl.laaraucana.test.services;

import javax.naming.NamingException;

public interface MailService {
	
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
	public void setSession(String jndiSessionEmail) throws NamingException;
}
