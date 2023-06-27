package cl.laaraucana.integracion.impl;

import java.util.ArrayList;
import java.util.Arrays;


import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.laaraucana.integracion.util.UtilEmail;



//clase dedicada al sistema, envia por email el reporte de la aplicación
public class EnviadorEmail {
	private static Logger log = Logger.getLogger(EnviadorEmail.class);
	public static String enviar(ArrayList listaLog, String jndiSessionEmail, String destinatarios, String ambiente)
	{


			ArrayList listaTo = getTo(destinatarios);
			log.info("lista destinatarios="  + listaTo.toString());
			try
			{
				//extarigo la sesión de correo desde el servidor según el jndi
				Context env = null;
				env = (Context) new InitialContext();
				Session mailSession = (Session) env.lookup(jndiSessionEmail);
				//envio el email
				UtilEmail.sendEmail(mailSession, listaTo, "Reporte Error WS Direccion del trabajo - " + ambiente, getCuerpo(listaLog));

				return "El email se envio correctamente.";
			}
			catch (Exception e)
			{	
				log.warn("Error al enviar correo, mensaje:" + e.getMessage());
				e.printStackTrace();
				return "problemas con el envio de email, "+e.getMessage();
			}

	}

	//contruye lista de destinatarios , separa un String por el caracter ";"
	private static ArrayList getTo(String to)
	{
		return new ArrayList(Arrays.asList(to.split(";")));
	}

	//prepara el contenido del texto a desplegar en el email, este debe estar en formato html
	private static String getCuerpo (ArrayList listaLog)
	{
		String resp=
		"Estimados,<br><br>" +
		"Se informa error ocurrido en el Web Services de la Dirección del Trabajo<br><br>" +
		"<table border = '0' width='100%'><tr><td align='center'>" +
		"<table border = '1>" +
		"<tr><td bgcolor='#ADCCFF'><strong>MENSAJE</strong></td></tr>";
		if(listaLog==null || listaLog.size()==0)
		{
			resp+="<tr><td>-</td></tr>";
		}
		else
		{
			//agrego el texto del reporte de la aplicación, estos son filas de una tabla
			for(int i=0;i<listaLog.size(); i++)
			{
				resp+="<tr><td>"+((String)listaLog.get(i)).replaceAll("\n", "<br>")+"</td></tr>";
			}
		}
		resp+="</table>" +
				"</td></tr></table>" +
				"<br><br>";
		resp+="Saludos, J-Factory Solutions.";

		return resp;

	}

}
