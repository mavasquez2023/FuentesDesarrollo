package cl.araucana.migra.mail;

import java.util.ResourceBundle;

public class FormatoMail {

	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");


	public static String obtenerTextoMailInfoMigra(String mensaje){
		String html = mailProperties.getString("mail.formato.cronta.migra.info");
		html = html.replaceAll("#mensaje#", mensaje);

		return html;
	}

	public static void main(String[] args) {
	}
}
