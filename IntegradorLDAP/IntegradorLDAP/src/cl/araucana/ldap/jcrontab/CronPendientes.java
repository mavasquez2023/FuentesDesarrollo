package cl.araucana.ldap.jcrontab;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;


public class CronPendientes {
	private static Logger log = Logger.getLogger(CronPendientes.class);
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	public static void main(String[] args) throws Exception {
		String mailerror="";
		try {
			//Se revisa si está activa la ejecución de cronta
			String estado_cronta= EstadoCronta.getEstado();
			if(estado_cronta.equals("off")){
				System.out.println("Warning: Estado de cronta:" + estado_cronta);
			}
			log.info("Estado de cronta:" + estado_cronta);
			if(estado_cronta.equals("on")){
				//Se rescata variable para identificar si se debe enviar correo a cliente luego de crear cuenta LDAP
				String sendmail = mailProperties.getString("app.envio.mail.cliente");
				//Se rescata lista de correo usuarios en caso de error
				mailerror = mailProperties.getString("app.mail.soporte.error");

				log.info("Ejecutando Cronta Usuarios Pendientes");
				HashMap dataUsuarios = IngresaUsuarioLDAP.procesarUsuarios(sendmail, mailerror);
				log.info("Ejecutando Cronta Empresas Pendientes");
				HashMap dataEmpresas = IngresaUsuarioLDAP.procesarEmpresas(mailerror);
				log.info("Ejecutando Cronta Roles Empresas Pendientes");
				HashMap dataRoles = IngresaUsuarioLDAP.procesarRolesEnterprise(mailerror);
				log.info("Ejecutando Cronta Aplicaciones Pendientes");
				HashMap dataAppRoles = IngresaUsuarioLDAP.procesarAppRoles(mailerror);
			}
		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronPendientes, mensaje:", e);
			EnviaMail.enviarMail("Error en creación de cuenta LDAP. ",mailerror, null,FormatoMail.obtenerTextoMailLdapCrontaError(e.getMessage()));
		}
	}
}