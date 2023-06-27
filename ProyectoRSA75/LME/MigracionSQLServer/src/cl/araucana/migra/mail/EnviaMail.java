package cl.araucana.migra.mail;

import cl.recursos.EnviarMail;


public class EnviaMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		enviarMail("Test","clillo007@gmail.com", "","Clave de autorización para el registro en Previpass:.<BR>");
		//System.out.println("dataMail:"+ PrevipassDAO.obtenerRegistro("obtenerDataMail",null));
	}

	public static void enviarMail(String subject, String destinatario,String copia,String texto) {

		try {
			EnviarMail mail = new EnviarMail("aplica", "aplica");
			String[] lista_to = destinatario.split(";");

			String[] lista_copy = null;
			if(copia != null && copia.trim().length()>5){
				lista_copy = copia.split(";");
			}
			StringBuffer body = new StringBuffer();
			body.append(texto);

			mail.mailTo("aplica@laaraucana.cl", lista_to, lista_copy, null,
					subject, body.toString());

			System.out.println(".............. EMAIL GENERADO .................... ");

		} catch (Exception e) {
			System.out.println("CAI EN MAIL  ");
			e.printStackTrace();
		}	}
}
