package cl.laaraucana.licenciascompin.services;

public interface MailService {

	public boolean sendEmailClie(String to, String subject, String bodyContent, String rut, String ruta, String folio) throws Exception;
	
	public boolean sendEmailClieDocP(String to, String subject, String bodyContent, String rut, String folio) throws Exception;
	
	public boolean sendEmailEjec(String to, String subject, String bodyContent) throws Exception;
	
}
