package cl.laaraucana.muvu.services;

public interface MailService {

	public boolean sendEmailClie(String to, String subject, String bodyContent, String rut, String ruta) throws Exception;
	
	public boolean sendEmailEjec(String to, String subject, String bodyContent) throws Exception;
	
}
