package cl.laaraucana.apofam.services;

public interface MailService {

	public boolean sendEmailClie(String to, String subject, String bodyContent, String rut, String ruta) throws Exception;
	
	
}
