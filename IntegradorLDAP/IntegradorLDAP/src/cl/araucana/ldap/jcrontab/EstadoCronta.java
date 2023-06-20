/**
 * 
 */
package cl.araucana.ldap.jcrontab;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;

/**
 * @author usist24
 *
 */
public class EstadoCronta {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger log = Logger.getLogger(EstadoCronta.class);
	static public String getEstado(){
		
		String salida="";
		String archivo_cronta="etc/estado_cronta.properties";
		Properties propObj = new Properties();
		InputStream input=null;
		try{
			//String path = new File(".").getAbsolutePath();
			//String dir= System.getProperty("user.dir");
			//anteriores llegan a carpeta properties general

			String location= EstadoCronta.class.getProtectionDomain().getCodeSource().getLocation().toString();
			//location viene al inicio con 'file:/'
			String[] path= location.split("file:/");
			String pathoutput= path[1] + archivo_cronta;
			pathoutput= pathoutput.replaceAll("%20", " ");
			
			System.out.println("Leyendo ESTADO cronta desde:" + pathoutput);
			log.info("Leyendo ESTADO cronta desde:" + pathoutput);
			input = new FileInputStream(pathoutput);
			if(input!=null){
				propObj.load(input);
				salida= propObj.getProperty("app.crontra.onoff");
			}
		} catch (Exception ioe)
		{
			System.out.println(">>>Error leyendo estado Cronta");
			ioe.printStackTrace();
		}
		finally{
			try {
				if(input!= null){
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	static public boolean setEstado(String estado, String ipremota){
		boolean salida=false;
		String archivo_cronta="etc/estado_cronta.properties";
		Properties propObj = new Properties();
		OutputStream output=null;
		try{
			String listamail = mailProperties.getString("app.mail.soporte.error");
			//String path = new File(".").getAbsolutePath();
			//String dir= System.getProperty("user.dir");
			//anteriores llegan a carpeta properties general

			String location= EstadoCronta.class.getProtectionDomain().getCodeSource().getLocation().toString();
			//location viene al inicio con 'file:/'
			String[] path= location.split("file:/");
			String pathoutput= path[1] + archivo_cronta;
			pathoutput= pathoutput.replaceAll("%20", " ");
			System.out.println("Salvando ESTADO cronta en:" + pathoutput);
			log.info("Salvando ESTADO cronta en:" + pathoutput);
			output = new FileOutputStream(pathoutput);
			propObj.setProperty("app.crontra.onoff", estado);
			propObj.store(output, "Estado ejecución Cronta Integrador LDAP");
			salida= true;
			log.info("Cambio Estado Cronta Integrador LDAP: " + estado);
			String mensajeLDAP= "Estado ejecución Cronta Integrador LDAP: " + estado;
			EnviaMail.enviarMail("Cambio estado Cronta LDAP. ",listamail, null,FormatoMail.obtenerTextoMailLdapCronta(estado, ipremota));
		} catch (Exception ioe)
		{	log.error("EstadoCronta, error en obtener-grabar Estado Cronta Integrador LDAP, mensaje:" + ioe.getMessage());
			System.out.println(">>>Error grabando Estado Cronta");
			ioe.printStackTrace();
		}
		finally{
			try {
				if(output!= null){
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
}
