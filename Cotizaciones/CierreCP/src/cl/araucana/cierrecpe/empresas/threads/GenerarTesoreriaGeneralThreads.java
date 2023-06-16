/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.threads;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.TesoreriaGeneral;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 * 
 * Clase CADUCADA por REQ-7435 Nuevo Formato Servicio TGR que apunta directo a tablas de Centralización
 */
public class GenerarTesoreriaGeneralThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo, cierre;
	private String emails;
	
	public GenerarTesoreriaGeneralThreads(int periodo, int cierre, String emails){
		this.periodo= periodo;
		this.cierre= cierre;
		this.emails= emails;
	}
	
	public void run(){	
		TesoreriaGeneral tgr=null;
		String inicio= Today.getTimeStamp();
		try{
			logger.info("Ejecutando run TGR, Periodo= " + periodo + ", cierre=" + cierre);
			tgr= new TesoreriaGeneral();
			boolean estado= tgr.generarTesoriaGeneral(periodo, cierre);
			enviarMail(estado, Formato.split(this.emails, ";"), tgr.getMensajeError(), "Generar Tesorería General de la Republica,  Periodo= " + periodo + ", Cierre=" + cierre);
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, cierre, "TGR", inicio, termino );
			Parametros.getInstance().getParam().delProceso("TGR:" + cierre);
			Parametros.getInstance().getParam().delThread("TGR:" + cierre);
			if(tgr!=null){
				tgr.close();
			}
		}
	}
	
//	Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
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
