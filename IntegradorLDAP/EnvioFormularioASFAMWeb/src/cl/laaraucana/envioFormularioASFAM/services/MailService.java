package cl.laaraucana.envioFormularioASFAM.services;

import java.util.List;

public interface MailService {

	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception;

	public boolean sendEmailEjec(String to, String subject, String bodyContent) throws Exception;

	public boolean sendEmailEjec(List<String> to, List<String> cc, String subject, String bodyContent) throws Exception;

}
