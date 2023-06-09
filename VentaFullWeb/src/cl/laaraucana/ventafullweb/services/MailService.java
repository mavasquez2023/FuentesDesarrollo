package cl.laaraucana.ventafullweb.services;

public interface MailService {
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;
}
