/**
 * 
 */
package cl.araucana.cheque.to;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;


/**
 * @author usist199
 *
 */
public class Parametros {

private static Parametros instance= new Parametros();
private static Logger log = Logger.getLogger(Parametros.class);
private String firma1;
private String firma2;
private String template;
private String server;
private String usuario;
private String password;
private String rutaPDF;
private String jndipub;

public Parametros(){
	try {
     
		Properties properties = new Properties();
		log.info(">>Cargando properties ChequeReport, ruta: /etc/config.properties");
		properties.load(Parametros.class.getResourceAsStream("/etc/config.properties"));
		this.template= properties.getProperty("template"); 
		log.info(">>Template Jasper Report=" + template);
		this.firma1= properties.getProperty("firma1");
		log.info(">>Firma 1=" + firma1);
		this.firma2= properties.getProperty("firma2");
		log.info(">>Firma 2=" + firma2);
		this.server= properties.getProperty("serveras400");
		log.info(">>Server AS400=" + server);
		this.usuario= properties.getProperty("usuarioas400");
		log.info(">>Usuario AS400=" + usuario);
		this.password= properties.getProperty("passwordas400");
		log.info(">>Password AS400=" + password);
		this.rutaPDF= properties.getProperty("rutaPDF");
		log.info(">>RutaPDF=" + rutaPDF);
		this.jndipub= properties.getProperty("jndi_publicacion");
		log.info(">>JNDI Publicacion=" + jndipub);
		
		
	} catch (IOException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}

public static Parametros getInstance(){
	return instance;
}
public String getFirma1() {
	return firma1;
}
public void setFirma1(String firma1) {
	this.firma1 = firma1;
}
public String getFirma2() {
	return firma2;
}
public void setFirma2(String firma2) {
	this.firma2 = firma2;
}
public String getTemplate() {
	return template;
}
public void setTemplate(String template) {
	this.template = template;
}

/**
 * @return el password
 */
public String getPassword() {
	return password;
}

/**
 * @param password el password a establecer
 */
public void setPassword(String password) {
	this.password = password;
}

/**
 * @return el server
 */
public String getServer() {
	return server;
}

/**
 * @param server el server a establecer
 */
public void setServer(String server) {
	this.server = server;
}

/**
 * @return el susuario
 */
public String getUsuario() {
	return usuario;
}

/**
 * @param susuario el susuario a establecer
 */
public void setUsuario(String usuario) {
	this.usuario = usuario;
}

/**
 * @return el rutaPDF
 */
public String getRutaPDF() {
	return rutaPDF;
}

/**
 * @param rutaPDF el rutaPDF a establecer
 */
public void setRutaPDF(String rutaPDF) {
	this.rutaPDF = rutaPDF;
}

/**
 * @return el jndipub
 */
public String getJndipub() {
	return jndipub;
}

/**
 * @param jndipub el jndipub a establecer
 */
public void setJndipub(String jndipub) {
	this.jndipub = jndipub;
}

}
