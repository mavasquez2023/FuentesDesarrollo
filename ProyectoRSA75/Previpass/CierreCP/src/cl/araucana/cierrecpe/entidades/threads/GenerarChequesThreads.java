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
public class GenerarChequesThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo, cierre;
	private String option, emails;
	boolean procesoOK;
	
	public GenerarChequesThreads(int periodo, int cierre, String option, String emails){
		this.periodo= periodo;
		this.cierre= cierre;
		this.option= option;
		this.emails= emails;
	}

	public void run(){
		ChequeEntidades cheque=null;
		String inicio= Today.getTimeStamp();
		try{
			//Generando nueva instacia de ChequeEntidad
			logger.info("Ejecutando run Cheques, Periodo= " + periodo + ", cierre=" + cierre);
			cheque= new ChequeEntidades();
			this.procesoOK= cheque.generarCheques(periodo, cierre, option);
			cheque.commit();
			enviarMail(isProcesoOK(), option, cheque.getNumChequesGenerados(), cheque.getTotalMontoCheques(),  Formato.split(this.emails, ";"), cheque.getMensajeError(), "Generar Cheques Tesorería, periodo:" + periodo + ", cierre:" + cierre);
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, cierre, "CHEQUE", inicio, termino );
			Parametros.getInstance().getParam().delProceso("CHEQUE:" + cierre);
			Parametros.getInstance().getParam().delThread("CHEQUE:" + cierre);
			try {
				cheque.close();
			} catch (SQLException e) {
			}
		}
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(boolean procesoOK, String optionCarga, int numCheques, long montoTotalCheques, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
		     StringBuffer body= new  StringBuffer();
			if(procesoOK){
				subject= " Ejecución exitosa en proceso " + proceso ;
				if(!optionCarga.equals("paso")){
					body.append("Señor Usuario: se han cargado " + numCheques + " cheques en Tesorería por un total de " + Formato.enPesos(montoTotalCheques) + "<BR>");
				}
				if(optionCarga.equals("del")){
					body.append("Además la tabla de Paso de Cheques (RE50F1) se ha borrado y se ha cargado la misma cantidad de cheques.<BR>");
				}else if(optionCarga.equals("add")){
					body.append("Además se ha agregado la misma cantidad de cheques en tabla de Paso de Cheques (RE50F1) <BR>");
				}else if(optionCarga.equals("paso")){
					body.append("Señor Usuario: se han cargado " + numCheques + " cheques en tabla Paso de Cheques  por un total de " + Formato.enPesos(montoTotalCheques) + "<BR>");
				}
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

	/**
	 * @return el procesoOK
	 */
	public boolean isProcesoOK() {
		return procesoOK;
	}

	/**
	 * @param procesoOK el procesoOK a establecer
	 */
	public void setProcesoOK(boolean procesoOK) {
		this.procesoOK = procesoOK;
	}
}
