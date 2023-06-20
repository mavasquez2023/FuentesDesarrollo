package cl.laaraucana.rendicionpagonomina.services;

public interface MailService {

	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
	public boolean sendEmailAdj(String to, String subject, String bodyContent) throws Exception;
	
}
