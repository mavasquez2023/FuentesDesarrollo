package cl.araucana.cotfonasa.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


import org.apache.commons.mail.EmailException;

import org.apache.commons.mail.SimpleEmail;

import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;


public class EnviaCorreo {
	
	private static String SMTP_HOST_NAME;
	private static String SMTP_HOST_PORT;
	private static String SMTP_AUTH_USER;
	private static String SMTP_AUTH_PWD;
	
	
	
	public EnviaCorreo() {
		super();
		// TODO Apéndice de constructor generado automáticamente
		
		try {
			
			Properties props = new Properties();
			 
			
			props.load(EnviaCorreo.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			SMTP_HOST_NAME = props.getProperty("SMTP_HOST_NAME");
			SMTP_HOST_PORT = props.getProperty("SMTP_HOST_PORT");
			SMTP_AUTH_USER = props.getProperty("SMTP_AUTH_USER");
			SMTP_AUTH_PWD  = props.getProperty("SMTP_AUTH_PWD");
			
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}



	public int sendCorreo(String[] destinatarios, String to, String from,String subject, String msje)
	{
		
		 
		  try {
			  
			  SimpleEmail email = new SimpleEmail();
			  int i=0;
			  
			  //email.setTLS(true);
			  email.setHostName(SMTP_HOST_NAME);
			  email.setSentDate(new Date());
			  email.setSmtpPort(Integer.parseInt(SMTP_HOST_PORT));
			  email.addTo(SMTP_AUTH_USER, to);		
			  email.setFrom(SMTP_AUTH_USER, from);
			  email.setSubject(subject);
		      email.setMsg(msje);
		      email.setAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
		      
		      for(i=0;i<destinatarios.length;i++)
		      {
		    	  email.addTo(destinatarios[i]);
		      }
		      
		      
		      
		      
		      email.send();
		      System.out.println("correos enviados");
		      return 1;
		} catch(EmailException e){
			System.out.println("Error en envio de archivos: "+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
		
			System.out.println("Error en envio de archivos: "+e.getMessage());
			e.printStackTrace();
		}

		return 0;
		
	}
	

}
