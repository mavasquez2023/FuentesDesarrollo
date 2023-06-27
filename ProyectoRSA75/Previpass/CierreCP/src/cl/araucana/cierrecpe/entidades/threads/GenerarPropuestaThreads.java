

/*
 * @(#) GenerarPropuestaThreads.java    1.0 05/07/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.threads;


import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
import cl.recursos.Today;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 05/07/2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class GenerarPropuestaThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo;
	private int cierre;
	private String opcion;
	private String formapago;
	private String deposito;
	private String tipoEmpresa;
	private String emails;
	boolean procesoOK;
	public GenerarPropuestaThreads(int periodo, int cierre, String formapago, String deposito, String tipoEmpresa, String opcion, String emails){
		logger.finer("Instanciando GenerarPropuestaThreads");
		this.periodo= periodo;
		this.cierre= cierre;
		this.opcion= opcion;
		this.formapago= formapago;
		this.deposito= deposito;
		this.tipoEmpresa= tipoEmpresa;
		this.emails= emails;
	}
	public void run(){
		PropuestaPagoEntidades propuesta=null;
		String inicio= Today.getTimeStamp();
		try{
			//System.out.println("OK"); 
			propuesta= new PropuestaPagoEntidades(false);
			//Se verifica si se debe eliminar Propuesta anterior
			if (this.opcion.equals("del")){
				propuesta.eliminarPropuestaPago(this.periodo, this.cierre);
			}
			//Se crea propuesta para periodo y cierre especificado
			logger.info("Generar Propuesta, creando propuesta para periodo:" + this.periodo + ", cierre:" + this.cierre);
			this.procesoOK= propuesta.crearPropuestaPago(this.periodo, this.cierre, formapago, deposito, tipoEmpresa);
			//Se invoca resumen de procesos de cierre para comprobantes asociados a la propuesta
			if(isProcesoOK()){
				logger.info("Generar Propuesta, insertar Resumen periodo:" + this.periodo + ", cierre:" + this.cierre);
				this.procesoOK= propuesta.insertarResumenProcesoCierre(periodo, cierre);
			}
			logger.info("Generar Propuesta, commit");
			propuesta.commit();
			enviarMail(isProcesoOK(), propuesta.getMontoTotalPropuesta(),  Formato.split(this.emails, ";"), propuesta.getMensajeError(), "Generar Propuesta, periodo:" + periodo + ", cierre:" + cierre);
			logger.info("Generar Propuesta, estado proceso:" + isProcesoOK());
		}catch(Exception e) {
			System.out.println("GenerarPropuesta, Error (Exception): " +  e.getMessage());
		} finally {
			System.out.println("GenerarPropuesta, Desconectando...");
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, cierre, "PROPUESTA", inicio, termino );
			Parametros.getInstance().getParam().delProceso("PROPUESTA:" + cierre);
			Parametros.getInstance().getParam().delThread("PROPUESTA:" + cierre);
			//Cerrando conexión
			logger.info("Generar Propuesta, cerrando conexión");
			try {
				if(propuesta!=null){
					propuesta.close();
					logger.info("Generar Propuesta, conexión cerrada");
				}
			} catch (Exception e) {
			}
		}
	}

	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(boolean conerror, long montoPropuesta, String[] mailEncargados, String mensaje, String proceso) {
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
				body.append("Señor Usuario: se ha generado la Propuesta por un total de " + Formato.enPesos(montoPropuesta)  + " <BR>");
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

