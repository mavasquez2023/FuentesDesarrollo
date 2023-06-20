package cl.araucana.ldap.mail;

import java.util.ResourceBundle;

public class FormatoMail {

	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
		
	public static String obtenerTextoMailLdapOK(String rutUsuario, String clave, String nombre, String apellido){
		String html = mailProperties.getString("mail.formato.registro.ldap.ok");
		html = html.replaceAll("#rut_ldap#", rutUsuario);
		html = html.replaceAll("#clave_ldap#", clave);
		html = html.replaceAll("#nombre#", nombre);
		html = html.replaceAll("#apellido#", apellido);
		
		return html;
	}
	public static String obtenerTextoMailCambioLdapOK(String rutUsuario, String clave, String nombre, String apellido){
		String html = mailProperties.getString("mail.formato.cambio.ldap.ok");
		html = html.replaceAll("#rut_ldap#", rutUsuario);
		html = html.replaceAll("#clave_ldap#", clave);
		html = html.replaceAll("#nombre#", nombre);
		html = html.replaceAll("#apellido#", apellido);
		
		return html;
	}
	public static String obtenerTextoMailLdapError(String registro, String mensajeError){
		String html = mailProperties.getString("mail.formato.registro.ldap.error");
		html = html.replaceAll("#rut_ldap#", registro);
		if(mensajeError==null) mensajeError="";
		html = html.replaceAll("#error_ldap#", mensajeError);
		
		return html;
	}
	public static String obtenerTextoMailLdapUsuarioError(String rutUsuario, String mensajeError){
		String html = mailProperties.getString("mail.formato.registro.ldap.error");
		html = html.replaceAll("#rut_ldap#", rutUsuario);
		if(mensajeError==null) mensajeError="";
		html = html.replaceAll("#error_ldap#", mensajeError);
		
		return html;
	}
	public static String obtenerTextoMailLdapCronta(String estado, String ipremota){
		String html = mailProperties.getString("mail.formato.cronta.ldap");
		html = html.replaceAll("#estado#", estado);
		html = html.replaceAll("#ip#", ipremota);
		
		return html;
	}
	public static String obtenerTextoMailLdapCrontaError(String mensaje){
		String html = mailProperties.getString("mail.formato.cronta.ldap.error");
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	public static String obtenerTextoMailSMSError(String username, String mensaje){
		String html = mailProperties.getString("mail.formato.usuario.sms.error");
		html = html.replaceAll("#username#", username);
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	public static void main(String[] args) {
	}
}
