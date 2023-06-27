package cl.araucana.wsccaf.mail;

import java.util.ResourceBundle;

public class FormatoMail {

	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");


	public static String obtenerTextoMailWSError(){
		String html = mailProperties.getString("mail.formato.cronta.wsccaf.error");
		//html = html.replaceAll("#mensaje#", mensaje);

		return html;
	}
	public static String obtenerTextoMailWSDB2Error(){
		String html = mailProperties.getString("mail.formato.cronta.wsccaf.db2error");
		//html = html.replaceAll("#mensaje#", mensaje);

		return html;
	}

	public static void main(String[] args) {
	}
}
