package cse.email.client;

import java.util.Properties;

public interface EmailClient {
	
	public boolean sendMail(
			String message,
			String subject,
			String from,
			String to,
			Properties p);
	
}
