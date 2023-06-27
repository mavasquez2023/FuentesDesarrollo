/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.threads;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.ArchivosImpresion;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarArchivosImpresionThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private int periodo, cierre;
	private String emails, formapago;
	
	public GenerarArchivosImpresionThreads(int periodo, int cierre, String formapago, String emails){
		this.periodo= periodo;
		this.cierre= cierre;
		this.formapago= formapago;
		this.emails= emails;
	}
	
	public void run(){
		ArchivosImpresion asicom=null;
		try{
			logger.info("Ejecutando run ASICOM, Periodo= " + periodo + ", cierre=" + cierre);
			asicom= new ArchivosImpresion();
			boolean estado= asicom.generaArchivoImpresion(periodo, cierre, formapago);
			enviarMail(estado, Formato.split(this.emails, ";"), asicom.getMensajeError(), "Generar Archivos Impresión (ASICOM), periodo:" + periodo + ", cierre:" + cierre);
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			Parametros.getInstance().getParam().delProceso("ASICOM:" + cierre);
			Parametros.getInstance().getParam().delThread("ASICOM:" + cierre);
			asicom.close();
		}
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(boolean conerror, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
		     StringBuffer body= new  StringBuffer();
			if(conerror){
				subject= " Ejecución exitosa en proceso " + proceso ;
				body.append("Señor Usuario: su proceso ha sido ejecutado exitosamente. <BR>");
			}else{
				subject= " Aviso de Observaciones en proceso " + proceso ;
				body.append("Señor usuario: su proceso <b>no pudo ser ejecutado</b>. <BR>");
				body.append("Favor solicite asistencia y posteriormente vuelva a ejecutar.<BR><BR>");
			}
			body.append(mensaje);
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
