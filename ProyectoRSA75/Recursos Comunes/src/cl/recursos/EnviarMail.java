/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

/**
 * @author clillo
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
import java.io.FileInputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SentDateTerm;
import javax.activation.DataHandler;




public class EnviarMail{
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	
	private Properties 		props			= null;
	private Authenticator 	auth			= null;
	private Session 		session			= null;

//	new InternetAddress(from)
	private BodyPart 		messageBodyPart	= null;
	private Multipart 		multipart		= null;
	private Transport 		transport		= null;

	private ArrayList attachList =null;

	private String smtp_user="";
	private String smtp_password="";
	private String smtp_host=mailProperties.getString("host");
	private String smtp_port=mailProperties.getString("port");

	public EnviarMail(){
		props = new Properties();
		System.out.println("Servidor de correo:" + smtp_host + ", puerto:" + smtp_port);
		setSmtp_host(smtp_host);
		setSmtp_port(smtp_port);
		props.put("mail.smtp.auth", "false");
		session = Session.getInstance(props);
		attachList = new ArrayList();
	}

	public EnviarMail(String user, String password){
		this(null, null, user, password);

	}
	public EnviarMail(String host, String port, String user, String password){
		props = new Properties();
		if(host!=null){
			setSmtp_host(host);
		}
		if(port!=null){
			setSmtp_port(port);
		}
		setSmtp_user(user);
		setSmtp_password(password);
		props.put("mail.smtp.auth", "true" );
		props.put("mail.debug", "true" );
		auth = new SMTPAuthenticator(this.getSmtp_user(), this.getSmtp_password());
		session = Session.getInstance(props, auth);
		attachList = new ArrayList();
	}
public EnviarMail(String pathfileconfig){
	props = new Properties();
	getConfigProperties(pathfileconfig);
	if(this.getSmtp_user().length()==0){
		props.put("mail.smtp.auth", "false");
		session = Session.getInstance(props);
	}else{
//		log.println("user: '"+this.smtp_user+"', password: '"+this.smtp_password+"'");
		props.put("mail.smtp.auth", "true" );
		props.put("mail.debug", "true" );
		auth = new SMTPAuthenticator(this.getSmtp_user(), this.getSmtp_password());
		session = Session.getInstance(props, auth);
	}
	attachList = new ArrayList();
}

public boolean mailTo(String from, Vector sendto, Vector copyto, Vector blindcopyto, String subject, String body) {
	InternetAddress[] to;
	try {
		//Creando nuevo mensaje de correo
		Message msg= new MimeMessage(session);
		try{
			msg.setFrom(new InternetAddress(from));
			msg.setHeader("X-Mailer", "msgsend");
			msg.setSentDate(new java.util.Date());
			msg.setSubject(subject);
		}catch(MessagingException e){
			throw new EmailerException( "[Error] [Emailer] Falta especificar el FROM o remitente del correo.");
		}
		//Agregando los destinarios de sendto
		if(sendto !=null){
			for (int i = 0; i < sendto.size(); i++) {
				to = InternetAddress.parse(sendto.elementAt(i).toString().trim());
				try{
					msg.addRecipients(Message.RecipientType.TO, to);
				}catch(MessagingException e){
					throw new EmailerException( "[Error] [Emailer] Al especificar el o los destinatarios del correo SendTo.");
				}
			}
		}
		//Agregando los destinarios de copyto
		if(copyto !=null){
			for (int i = 0; i < copyto.size(); i++) {
				to = InternetAddress.parse(copyto.elementAt(i).toString().trim());
				try{
					msg.addRecipients(Message.RecipientType.CC, to);
				}catch(MessagingException e){
					throw new EmailerException( "[Error] [Emailer] Al especificar el o los destinatarios del correo CopyTo.");
				}
			}
		}
		//Agregando los destinarios de blindcopyto
		if(blindcopyto !=null){
			//System.out.println("EnviarMail, número mail con CCO:" + blindcopyto.size());
			for (int i = 0; i < blindcopyto.size(); i++) {
				to = InternetAddress.parse(blindcopyto.elementAt(i).toString().trim());
				try{
					msg.addRecipients(Message.RecipientType.BCC, to);
				}catch(MessagingException e){
					throw new EmailerException( "[Error] [Emailer] Al especificar el o los destinatarios del correo BlindCopyTo.");
				}
			}
		}
		//Agregando el body al mensaje
		this.multipart = new MimeMultipart();

		MimeBodyPart messageBodyPart = new MimeBodyPart();

		try{
			messageBodyPart.setText(body,"ISO-8859-1");
			messageBodyPart.setContent(body, "text/html");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
		}catch(MessagingException e) {
			throw new EmailerException( "[Error] [Emailer] Al agregar el texto al correo.");
		}

		//Agregando los attach
		if(!this.attachList.isEmpty()){
			int i=1;
			for(Iterator it=this.attachList.iterator(); it.hasNext(); ){
				messageBodyPart = (MimeBodyPart) it.next();
				try{this.multipart.addBodyPart(messageBodyPart);}catch(MessagingException e){ throw new EmailerException( "[Error] [Emailer] Al atachar el archivo "+i+" al mail."); }
				i++;
		    }
		}
		//Creando la Conexión y enviando mensaje
		Transport transport = null;
		try{
			transport = session.getTransport("smtp");
			transport.connect(getSmtp_host(), Integer.parseInt(getSmtp_port()), null, null);
		}catch(Exception e){
			System.out.println("CAI EN LIBRERIA ENVIARMAIL, Problemas creando la conexión al host:" + getSmtp_host() + " puerto:" + getSmtp_port());
			e.printStackTrace();
			throw new EmailerException( "[Error] [Emailer] Al crear la conexión al host:" + getSmtp_host() + " puerto:" + getSmtp_port());
		}
		try{
			if (sendto!=null && sendto.size()>0){
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO) );
			}
			if (copyto!=null && copyto.size()>0){
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.CC) );
			}
			if (blindcopyto!=null && blindcopyto.size()>0){
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.BCC) );
			}
		}catch(MessagingException e){
			System.out.println("CAI EN LIBRERIA ENVIARMAIL, Problemas enviando correo a uno de los destinatarios." );
			e.printStackTrace();
			throw new EmailerException( "[Error] [Emailer] Problemas enviando correo a uno de los destinatarios.");
		}
		return true;
	}catch(Exception e) {
		System.out.println("CAI EN LIBRERIA ENVIARMAIL, metodo MAILTO  " );
		e.printStackTrace();
		return false;
	}
}

public boolean mailTo(String from, String sendto, String copyto, String blindcopyto, String subject, String body) {
	Vector to= null;
	Vector copy= null;
	Vector blindcopy= null;
	if (sendto!=null && !sendto.equals("null") && !sendto.equals("")){
		to= new Vector();
		to.addElement(sendto);
	}
	if (copyto!=null && !copyto.equals("null") && !copyto.equals("")){
		copy= new Vector();
		copy.addElement(copyto);
	}
	if (blindcopyto!=null && !blindcopyto.equals("null") && !blindcopyto.equals("")){
		blindcopy= new Vector();
		blindcopy.addElement(blindcopyto);
	}
	return mailTo(from, to, copy, blindcopy, subject, body);
}

public boolean mailTo(String from, String[] sendto, String[] copyto, String[] blindcopyto, String subject, String body) {
	Vector to= null;
	Vector copy= null;
	Vector blindcopy= null;
	if(sendto!= null){
		to= new Vector();
		for (int i = 0; i < sendto.length; i++) {
			to.add(sendto[i].trim());
		}
	}
	if(copyto!= null){
		copy= new Vector();
		for (int i = 0; i < copyto.length; i++) {
			copy.add(copyto[i].trim());
		}
	}
	if(blindcopyto!= null){
		blindcopy= new Vector();
		for (int i = 0; i < blindcopyto.length; i++) {
			blindcopy.add(blindcopyto[i].trim());
		}
	}
	return mailTo(from, to, copy, blindcopy, subject, body);
}

public void closeMail(){
	//	Cerrando la conexión
	try{
		if(transport!=null){
			transport.close();
			transport=null;
		}
		this.multipart 		=null;
		this.messageBodyPart=null;
		this.session		=null;
		this.attachList 	=null;
	}catch(MessagingException e){
		System.out.println("[Error] [Emailer] Al cerrar el transport.");
	}finally{
		transport=null;
	}
}

private boolean getConfigProperties(String filename) {
	String clave, valor;
	try {
		//Carga configuración desde archivo.
		Properties config = new java.util.Properties();
		FileInputStream filein = new FileInputStream(filename);
		config.load(filein);

		// Lista de propiedades.
		Enumeration props = config.keys();

		// Se recorren las claves.
		while (props.hasMoreElements()) {

			// Se obtienen los datos de cada linea de la configuración.
			clave = props.nextElement().toString();
			valor = config.getProperty(clave);

			if (clave.equalsIgnoreCase("host")){
				setSmtp_host(valor);
			}else if (clave.equalsIgnoreCase("port")){
				setSmtp_port(valor);
			}else if (clave.equalsIgnoreCase("user")){
				setSmtp_user(valor);
			}else if (clave.equalsIgnoreCase("password")){
				setSmtp_password(valor);
			}
		}
		if (getSmtp_host().equals("") || getSmtp_port().equals("")){
			return false;
		}
		return true;
	} catch (Exception e) {
		System.out.println("Archivo configuración inicial no existe, mensaje= " + e);
		return false;
	}
}

/**
 * @return Devuelve smtp_host.
 */
private String getSmtp_host() {
	return smtp_host;
}
/**
 * @param smtp_host El smtp_host a establecer.
 */
private void setSmtp_host(String smtp_host) {
	if (smtp_host!= null && !smtp_host.equals("")){
		this.smtp_host = smtp_host;
		props.put("mail.smtp.host", smtp_host);
	}
	props.put("mail.smtp.host", this.smtp_host);
}
/**
 * @return Devuelve smtp_port.
 */
private String getSmtp_port() {
	return smtp_port;
}
/**
 * @param smtp_port El smtp_port a establecer.
 */
private void setSmtp_port(String smtp_port) {
	if (smtp_port!= null && !smtp_port.equals("")){
		this.smtp_port = smtp_port;
	}
	props.put("mail.smtp.port", this.smtp_port);
}
/**
 * @return Devuelve smtp_user.
 */
private String getSmtp_user() {
	return smtp_user;
}
/**
 * @param smtp_user El smtp_user a establecer.
 */
private void setSmtp_user(String smtp_user) {
	this.smtp_user = smtp_user;
}
/**
 * @return Devuelve smtp_password.
 */
private String getSmtp_password() {
	return smtp_password;
}
/**
 * @param smtp_password El smtp_password a establecer.
 */
private void setSmtp_password(String smtp_password) {
	this.smtp_password = smtp_password;
}
private boolean attachTextoPlano(String textoAttach, String nombreAttach) throws EmailerException{
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	try{
		messageBodyPart.setDataHandler(new DataHandler(textoAttach, "text/plain"));
		messageBodyPart.setHeader("Content-Transfer-Encoding","base64");
		messageBodyPart.setFileName(nombreAttach);
		this.attachList.add( messageBodyPart );
	}catch(MessagingException e){ throw new EmailerException( "[Error] [Emailer] Al especificar el nombre o el contenido del archivo de texto plano que llevara atachado el mail."); }
	finally{ messageBodyPart = null; }
	return true;
}

/**
 * Permite adjuntar un archivo a un correo, se pasan por parametros el nombre y la ruta al archivo a adjuntar
 * @param nombrePdfAttach Nombre del pdf que será desplegado al usuario.
 * @param pathAlArchivoPdf Ruta al archivo pdf a enviar.
 * @return El estado de haber agregado el attach.
 * @throws EmailerException En caso de Error se arrojará una Excepcion de tipo EmailerException.
 */
private boolean attachBinario(String pathArchivo, String nombreAttach, String tipo) throws EmailerException{
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	try{
		messageBodyPart.setDataHandler(new DataHandler( new javax.activation.FileDataSource(pathArchivo) ) );
		messageBodyPart.setHeader("Content-Type","application/" + tipo);
		messageBodyPart.setHeader("Content-Transfer-Encoding","base64");
		messageBodyPart.setFileName(nombreAttach);
		this.attachList.add( messageBodyPart );
	}catch(MessagingException e){ throw new EmailerException( "[Error] [Emailer] Al especificar el nombre o el contenido del archivo de texto plano que llevara atachado el mail."); }
	finally{ messageBodyPart = null; }
	return true;
}

/**
 * Permite adjuntar un archivo a un correo, a partir de pasar por parametros la ruta del archivo
 * @param pathfile Ruta del Archivo a ser desplegado al usuario.
 * @return El estado de haber agregado el attach.
 * @throws EmailerException En caso de Error se arrojará una Excepcion de tipo EmailerException.
 */
public boolean attach(String pathfile) throws EmailerException{
	String contentType="";
	String planos= "txt, xml, inf, java, html";
	try{
		String nombreAttach= pathfile.substring(pathfile.lastIndexOf("/")+1);
		if (nombreAttach.indexOf(".")>0){
			contentType=nombreAttach.substring(nombreAttach.lastIndexOf(".")+1);
		}
		if (planos.indexOf(contentType)>-1){
			System.out.println("********>>>>Enviando archivo plano");
			attachTextoPlano(pathfile, nombreAttach);
		}else{
			System.out.println("*********>>>>Enviando archivo binario");
			attachBinario(pathfile, nombreAttach, contentType);
		}
	}catch(Exception e){
		throw new EmailerException( "[Error] [Emailer] Al especificar el nombre o el contenido del archivo que llevara atachado el mail.");
	}
	finally{ messageBodyPart = null; }
	return true;
}

public static void main(String [] args){
	final int SIZEGRUPO=30;
	//EnviarMail mail = new EnviarMail(args[0]);
	EnviarMail mail = new EnviarMail("portal", "portal08");
	//args[0]="/planos/configmail.txt";

	mail.mailTo(args[0], args[1], args[2], args[3], args[4], args[5]);
	

}

}


