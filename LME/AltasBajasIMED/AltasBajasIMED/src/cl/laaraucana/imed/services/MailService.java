package cl.laaraucana.imed.services;

public interface MailService {

	public abstract boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
}
