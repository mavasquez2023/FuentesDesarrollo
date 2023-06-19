package cl.laaraucana.benef.services;

public interface MailService {

	public boolean sendEmailClie(String to, String subject, String bodyContent) throws Exception;
	
}
