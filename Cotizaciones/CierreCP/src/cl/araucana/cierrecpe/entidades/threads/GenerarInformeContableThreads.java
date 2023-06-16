/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.threads;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.entidades.business.ChequeEntidades;
import cl.araucana.cierrecpe.entidades.to.ContadorTO;
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
public class GenerarInformeContableThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo;
	private String emails;
	private String option;
	
	public GenerarInformeContableThreads(int periodo, String option, String emails){
		this.periodo= periodo;
		this.option= option;
		this.emails= emails;
	}
	
	public void run(){
		ChequeEntidades cheque=null;
		String inicio= Today.getTimeStamp();
		try{
			//Generando nueva instacia de ChequeEntidad
			cheque= new ChequeEntidades();
			ContadorTO contador= new ContadorTO();
			contador.setCountPrevio(cheque.countRegistrosBalance());
			int count=cheque.generarInformeContable(periodo, option);
			contador.setCountInsert(count);
			cheque.commit();
			contador.setCountPost(cheque.countRegistrosBalance());
			enviarMail(contador, Formato.split(this.emails, ";"), cheque.getMensajeError(), "Generar Informe Contable, periodo:" + periodo);

		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, 0, "INFORME", inicio, termino );
			Parametros.getInstance().getParam().delProceso("INFORME:" + periodo);
			Parametros.getInstance().getParam().delThread("INFORME:" + periodo);
			try {
				cheque.close();
			} catch (SQLException e) {
			}
		}
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(ContadorTO cantidad, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
		     StringBuffer body= new  StringBuffer();
			if(cantidad.getCountInsert()>0){
				subject= " Ejecución exitosa en proceso " + proceso ;
				body.append("Señor Usuario: su proceso ha sido ejecutado exitosamente y se han cargado " + cantidad.getCountInsert() + " registros.<BR><BR>");
				body.append("Número de registros antes del proceso: " + cantidad.getCountPrevio() + "<BR>");
				body.append("Número de registros después del proceso: " + cantidad.getCountPost() + "<BR>");
			}else{
				subject= " Aviso de Observaciones en proceso " + proceso ;
				body.append("Señor usuario: su proceso <b>no pudo ser ejecutado</b>. <BR><BR>");
				body.append("Número de registros previos al proceso: " + cantidad.getCountPrevio() + "<BR>");
				body.append("Número de registros posteriores al proceso: " + cantidad.getCountPost() + "<BR>");
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
