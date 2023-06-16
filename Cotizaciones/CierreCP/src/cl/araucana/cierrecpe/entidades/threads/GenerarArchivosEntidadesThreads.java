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
public class GenerarArchivosEntidadesThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo, cierre;
	private String emails, fechaDeposito, formapago;
	private List secciones;
	int numprocesosOK;
	
	public GenerarArchivosEntidadesThreads(int periodo, int cierre, String formapago, String fechaDeposito, Collection secciones, String emails){
		this.periodo= periodo;
		this.cierre= cierre;
		this.emails= emails;
		this.formapago= formapago;
		this.fechaDeposito= fechaDeposito;
		this.secciones= new ArrayList(secciones);
	}
	
	public void run(){
		ArchivoEntidades archivos=null;
		String inicio= Today.getTimeStamp();
		try{
			logger.info("Ejecutando run Archivos, Periodo= " + periodo + ", cierre=" + cierre);
			archivos= new ArchivoEntidades();
			this. numprocesosOK=archivos.generaArchivoEntidades(periodo, cierre, formapago, fechaDeposito, secciones);
			enviarMail(numprocesosOK, secciones.size(), Formato.split(this.emails, ";"), archivos.getMensajeError(), "Generar Archivo Entidades, periodo:" + periodo + ", cierre:" + cierre);
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, cierre, "ARCHIVO", inicio, termino );
			Parametros.getInstance().getParam().delProceso("ARCHIVO:" + cierre);
			Parametros.getInstance().getParam().delThread("ARCHIVO:" + cierre);
			if (archivos != null){
				archivos.close();
			}
		}
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(int numprocesosOK, int totalprocesos, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
		     
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
				
		     StringBuffer body= new  StringBuffer();
			if(numprocesosOK==totalprocesos && totalprocesos>0){
				subject= " Ejecución exitosa en proceso " + proceso ;
				body.append("Señor Usuario: su proceso ha sido ejecutado exitosamente. <BR>");
				body.append("Archivos generados: <BR>");
				body.append(mensaje);
			}else{
				subject= " Aviso de Observaciones en proceso " + proceso ;
				body.append("Señor usuario: se han ejecutado " + numprocesosOK + " de un total de " + totalprocesos + " procesos</b>. <BR>");
				body.append("Revise las casillas en link Gestión Entidades para revisar los archivos faltantes, ");
				body.append("solicite asistencia y posteriormente vuelva a ejecutar.<BR><BR>");
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
