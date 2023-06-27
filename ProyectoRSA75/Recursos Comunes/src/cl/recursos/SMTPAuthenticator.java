/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cl.recursos;

import javax.mail.PasswordAuthentication;

/**
 * @author clillo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SMTPAuthenticator extends javax.mail.Authenticator
{
	String username = "";
	String password = "";
	
	public SMTPAuthenticator(String user, String password)
		{
		this.username = user;
		this.password = password;
		}

	public void setPasswordAuthentication(String user, String password)
		{
		this.username = user;
		this.password = password;
		}
	
	public PasswordAuthentication getPasswordAuthentication()
		{
		return new PasswordAuthentication(username, password);
		}
}