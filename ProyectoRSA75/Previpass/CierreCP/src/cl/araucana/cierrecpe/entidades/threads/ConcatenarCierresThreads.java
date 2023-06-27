/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.entidades.business.ArchivoEntidades;
import cl.araucana.cierrecpe.entidades.business.ConcatenarCierres;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConcatenarCierresThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo;
	private String emails;
	private String[] cierres;
	boolean procesosOK;
	
	public ConcatenarCierresThreads(int periodo, String[] cierres, String emails){
		this.periodo= periodo;
		this.emails= emails;
		this.cierres= cierres;
	}
	
	public void run(){
		ConcatenarCierres concatenar=null;
		String inicio= Today.getTimeStamp();
		try{
			logger.info("Ejecutando run Concatenar Cierres, Periodo= " + periodo);
			concatenar= new ConcatenarCierres();
			this.procesosOK=concatenar.concatenarCierres(periodo, cierres);
			enviarMail(procesosOK, Formato.split(this.emails, ";"), concatenar.getMensajeError(), "Concatenar Cierres, periodo:" + periodo );
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, 999, "CONCATENAR", inicio, termino );
			Parametros.getInstance().getParam().delProceso("CONCATENAR:" + periodo);
			Parametros.getInstance().getParam().delThread("CONCATENAR:" + periodo);
			if (concatenar != null){
				//concatenar.close();
			}
		}
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(boolean procesosOK, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
		     StringBuffer body= new  StringBuffer();
			if(procesosOK){
				subject= " Ejecución exitosa en proceso " + proceso ;
				body.append("Señor Usuario: su proceso ha sido ejecutado exitosamente. <BR>");
				body.append("Cierres concatenados: <BR>");
				body.append(mensaje);
			}else{
				subject= " Aviso de Observaciones en proceso " + proceso ;
				body.append("Solicite asistencia y posteriormente vuelva a ejecutar.<BR><BR>");
				body.append(mensaje);
			}
			body.append("<br><br>");
			body.append("Saluda atte. a Ud. "+"<BR>");
			body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("aplica@laaraucana.cl", mailEncargados, null, null , subject, body.toString());
		  	
		  	System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }

}
