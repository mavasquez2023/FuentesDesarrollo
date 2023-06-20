package cl.laaraucana.licenciascompinemp.services;

public interface MailService {
	
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
	
}
