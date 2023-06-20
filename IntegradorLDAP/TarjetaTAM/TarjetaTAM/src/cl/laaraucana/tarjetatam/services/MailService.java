package cl.laaraucana.tarjetatam.services;

public interface MailService {

	public boolean sendEmailClie(String to, String subject, String bodyContent, String rut) throws Exception;
	
	public boolean sendEmailEjec(String to, String subject, String bodyContent) throws Exception;
	
}
