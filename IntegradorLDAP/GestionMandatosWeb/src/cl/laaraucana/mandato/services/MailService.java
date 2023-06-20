package cl.laaraucana.mandato.services;

public interface MailService {

	public boolean sendEmail(String to, String subject, String bodyContent, String rut, String ruta) throws Exception;
	
	public boolean sendEmailAdj(String to, String subject, String bodyContent) throws Exception;
	
}
