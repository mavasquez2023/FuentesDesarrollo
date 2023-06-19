package cl.araucana.spring.services;

public interface MailService {

	public  boolean sendEmail(String rut, String to, String bodyContent, String ruta) throws Exception;

}
