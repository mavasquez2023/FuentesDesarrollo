package cl.ccaf.previpass.mail;

import java.util.ResourceBundle;

public class FormatoMail {

	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/config");
	
	static String FORMATO_MAIL_CLAVE = "<html>"+
"<body>"+
"<table>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/templates/rt_panacea_j15/images/logo/light/logo.png'></img><br><br></td>"+
"</tr>"+
"<tr>"+
"<td>"+
"Estimad@,<br><br>"+
"Su clave de Acceso para el registro de empresa en Previpass es: <strong>1234</strong><br><br>"+
"Para cualquier consulta se puede comunicar con un Ejecutivo de La Araucana al tel&eacute;fono - 02-23456789 <br><br><br>"+
"</td>"+
"</tr>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/images/stories/footer_logo_araucana.jpg'></img></td>"+
"</tr>"+
"</table>"+
"</body>"+
"</html>";
	
	static String FORMATO_MAIL_REGISTRO = "<html>"+
"<body>"+
"<table>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/templates/rt_panacea_j15/images/logo/light/logo.png'></img><br></td>"+
"</tr>"+
"<tr>"+
"<td>"+
"Estimad@,<br><br>"+
"Se ha registrado un nuevo usuario en el sistema <br><br>"+ 
"Datos de Ingreso:<br> <br> "+
"Email: <strong></strong> <br>"+ 
"Rut empresa : <strong></strong><br><br>"+
"</td>"+
"</tr>"+
"<tr>"+
"<td>&nbsp;</td>"+
"</tr>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/images/stories/footer_logo_araucana.jpg'></img></td>"+
"</tr>"+
"</table>"+
"</body>"+
"</html>";
	
	static String FORMATO_MAIL_USUARIO_LDAP_OK = "<html>"+
"<body>"+
"<table>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/templates/rt_panacea_j15/images/logo/light/logo.png'></img><br></td>"+
"</tr>"+
"<tr>"+
"<td>"+
"Estimad@,<br><br>"+
"Se ha creado su usuario para los sistema en el portal  <a href='www.previpass.cl'>www.previpass.cl</a> <br><br><br>"+
"Datos de Cuenta de Usuario :<br><br>"+
"Rut: <strong>15057836-1</strong> <br>"+
"Clave : <strong>1234</strong><br><br>"+
"</td>"+
"</tr>"+
"<tr>"+
"<td><img src='http://www.previpass.cl/images/stories/footer_logo_araucana.jpg'></img></td>"+
"</tr>"+
"</table>"+
"</body>"+
"</html>";
	
	
	public static String obtenerTextoMailClave(String clave,String rutEmpresa){
		String html = mailProperties.getString("mail.formato.envio.clave");
		html = html.replaceAll("#clave_generada#", clave);
		html = html.replaceAll("#rut_empresa#", rutEmpresa);
		html = html.replaceAll("#telefono_ejecutivo#", mailProperties.getString("app.telefono.ejecutivo"));
		html = html.replaceAll("#url_manual#", mailProperties.getString("app.url.manual"));
		return html;
	}
	public static String obtenerTextoMailRegistro(String emailEmpresa,String rutEmpresa){
		String html = mailProperties.getString("mail.formato.registro");
		html = html.replaceAll("#email_empresa#", emailEmpresa);
		html = html.replaceAll("#rut_empresa#", rutEmpresa);
		html = html.replaceAll("#server_ip#", mailProperties.getString("app.server.ip"));
		html = html.replaceAll("#server_port#", mailProperties.getString("app.server.port"));

		return html;
	}
	public static String obtenerTextoMailLdapOK(String rutUsuario, String clave){
		String html = mailProperties.getString("mail.formato.registro.ldap.ok");
		html = html.replaceAll("#rut_ldap#", rutUsuario);
		html = html.replaceAll("#clave_ldap#", clave);
		html = html.replaceAll("#url_previpass#", mailProperties.getString("app.url.previpass"));
		
		return html;
	}
	public static String obtenerTextoMailRegistroOKCopiaCaja(String nombreEmpresa, String rutEmpresa, String nombreAdmin, String email){
		String html = mailProperties.getString("mail.formato.registro.copia.caja");
		html = html.replaceAll("#nombre_empresa#", nombreEmpresa);
		html = html.replaceAll("#rut_empresa#", rutEmpresa);
		html = html.replaceAll("#nombre_admin#", nombreAdmin);
		html = html.replaceAll("#email#", email);
		
		return html;
	}
	
	public static void main(String[] args) {
		//EnviaMail.enviarMail("Template Mail Clave", "mvasquezm@laaraucana.cl", "luisibacache@gmail.com;clillo007@gmail.com", obtenerTextoMailClave("4321"));
		//EnviaMail.enviarMail("Template Mail Registro Usuario", "mvasquezm@laaraucana.cl", "luisibacache@gmail.com;clillo007@gmail.com", obtenerTextoMailRegistro("luisibacache@gmail.com","222323-2"));
		//EnviaMail.enviarMail("Template Mail Registro Cuenta LDAP", "mvasquezm@laaraucana.cl", "luisibacache@gmail.com;clillo007@gmail.com", obtenerTextoMailLdapOK("1523423","2323"));
		//EnviaMail.enviarMail("Template Mail Clave", "luisibacache@gmail.com", "", obtenerTextoMailClave("4321"));
		//EnviaMail.enviarMail("Template Mail Registro Usuario", "luisibacache@gmail.com", "", obtenerTextoMailRegistro("luisibacache@gmail.com","222323-2"));
		//EnviaMail.enviarMail("Template Mail Registro Cuenta LDAP", "luisibacache@gmail.com", "", obtenerTextoMailLdapOK("1523423","2323"));
	}
}
