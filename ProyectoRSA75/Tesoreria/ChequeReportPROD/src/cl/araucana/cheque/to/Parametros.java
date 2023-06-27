/**
 * 
 */
package cl.araucana.cheque.to;

import java.io.IOException;
import java.util.Properties;


/**
 * @author usist199
 *
 */
public class Parametros {

private static Parametros instance= new Parametros();
private String firma1;
private String firma2;
private String template;
private String server;
private String usuario;
private String password;
private String rutaPDF;

public Parametros(){
	try {
     
		Properties properties = new Properties();
		System.out.println(">>Cargando properties ChequeReport, ruta: /etc/config.properties");
		properties.load(Parametros.class.getResourceAsStream("/etc/config.properties"));
		this.template= properties.getProperty("template"); 
		System.out.println(">>Template Jasper Report=" + template);
		this.firma1= properties.getProperty("firma1");
		System.out.println(">>Firma 1=" + firma1);
		this.firma2= properties.getProperty("firma2");
		System.out.println(">>Firma 2=" + firma2);
		this.server= properties.getProperty("serveras400");
		System.out.println(">>Server AS400=" + server);
		this.usuario= properties.getProperty("usuarioas400");
		System.out.println(">>Usuario AS400=" + usuario);
		this.password= properties.getProperty("passwordas400");
		System.out.println(">>Password AS400=" + password);
		this.rutaPDF= properties.getProperty("rutaPDF");
		System.out.println(">>RutaPDF=" + rutaPDF);
		
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

}
