package cl.laaraucana.monitoreoLogLME.services;

public interface MailService {

	public abstract boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
}
