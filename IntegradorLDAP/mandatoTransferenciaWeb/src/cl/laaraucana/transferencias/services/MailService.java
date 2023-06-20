package cl.laaraucana.transferencias.services;

public interface MailService {

	public boolean sendEmailMandato(String to, String subject, String bodyContent, String rut, String ruta) throws Exception;
	
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
	
	public boolean sendEmailAdj(String to, String subject, String bodyContent) throws Exception;
	
}
