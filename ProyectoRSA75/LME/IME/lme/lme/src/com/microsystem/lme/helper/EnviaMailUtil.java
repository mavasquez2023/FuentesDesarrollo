package com.microsystem.lme.helper;

import java.util.Date;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMailUtil {

	public static boolean EnviarMail(String mailToNomina, String mailSubject, String mailContent, String filePath, String fileName, int flag) {
		Context env = null;
		int bandera;

		try {
			env = new InitialContext();

			Session mailSession = (Session) env.lookup("jndi/independientesmail");
			String from = "independiente@laaraucana.cl";

			StringTokenizer tokens = new StringTokenizer(mailToNomina, "#");
			int nDatos = tokens.countTokens();
			String[] datos = new String[nDatos];

			String[] to = new String[datos.length];
			String[] copia = new String[1];

			int i = 0;
			while (tokens.hasMoreTokens()) {
				String toTokens = tokens.nextToken();
				if (Helper.isEmail(toTokens)) {
					to[i] = toTokens;
					i++;
				} else {
					System.out.println("Email incorrecto");
				}

			}
			copia[0] = "";
			String subject = mailSubject;
			String emailContent = mailContent;
			bandera = flag;
			try {
				return sendMessage(mailSession, formatearMails(to), formatearMails(copia), from, subject, emailContent, filePath, fileName, bandera);
			} catch (Exception exc) {
				exc.printStackTrace();
				return false;
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean sendMessage(Session mailSession, String to, String cc, String from, String subject, String bodyContent, String filePath, String fileName, int flag) throws Exception {

		String detalleCuerpo = "";

		if (flag == 0) {
			detalleCuerpo = "text/plain";
		} else {
			if (flag == 1) {
				detalleCuerpo = "text/html";
			}
		}

		Message msg = new MimeMessage(mailSession);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
		msg.setText(bodyContent);

		if (!filePath.equals("") && !fileName.equals("")) {//Esta validacion es para señalar que no hubo solicitudes por lo que no se envia archivo

			msg.setFileName(fileName);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();

			htmlPart.setContent(bodyContent, detalleCuerpo);//cuerpo del email en texto plano

			MimeBodyPart filePart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			filePart.setDataHandler(new DataHandler(source));
			filePart.setFileName(fileName);

			multipart.addBodyPart(htmlPart);
			multipart.addBodyPart(filePart);

			msg.setContent(multipart);
		}

		Transport.send(msg);

		return true;
	}

	private static String formatearMails(String[] mails) {
		String retorno = "";

		for (int i = 0; i < mails.length; i++) {
			retorno = retorno + mails[i];
			if (i + 1 < mails.length)
				retorno = retorno + ",";
		}
		return retorno;
	}
}