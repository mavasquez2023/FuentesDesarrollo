

/*
 * @(#) GenerarPlanillasAction.java    1.0 13-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.threads;


import java.util.ResourceBundle;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.GenerarPlanillas;
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
 *            <TD> 13-08-2009 </TD>
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
public class GenerarPlanillasThreads extends Thread {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	private LogCierre logcierre= LogCierre.getInstance();
	private int periodo, cierre;
	//fechaPago en formato aaaammdd
	private String emails, fechaPago, centralizacion;
	boolean procesoOK;
	
	public GenerarPlanillasThreads(int periodo, int cierre, String fechaPago, String centralizacion, String emails ){
		this.periodo= periodo;
		this.cierre= cierre;
		this.emails= emails;
		this.fechaPago= fechaPago;
		this.centralizacion= centralizacion;
	}
	
	public void run(){
		GenerarPlanillas generar=null;
		String inicio= Today.getTimeStamp();
		try{
			logger.info("Ejecutando run Planillas, Periodo= " + periodo + ", cierre=" + cierre);
			generar= new GenerarPlanillas();
			if(generar.sincronizaFoliosDomino("inicio")){
				this.procesoOK= generar.principal(periodo, cierre, fechaPago, centralizacion, this.emails);
				generar.sincronizaFoliosDomino("fin");
			}
			enviarMail(isProcesoOK(), generar.getNumeroTotalComprobantes(), generar.getNumeroComprobantesConPlanillas(), Formato.split(this.emails, ";"), generar.getMensajeError(), "Generar Planillas (PWF), periodo:" + periodo + ", cierre:" + cierre);
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			String termino= Today.getTimeStamp();
			logcierre.grabarLog(periodo, cierre, "PLANILLA", inicio, termino );
			Parametros.getInstance().getParam().delProceso("PLANILLA:" + cierre);
			Parametros.getInstance().getParam().delThread("PLANILLA:" + cierre);
			generar.close();
		}
	}
		
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(boolean procesoOK, int numComprobantes, int numComprobantesConPlanillas, String[] mailEncargados, String mensaje, String proceso) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
		     StringBuffer body= new  StringBuffer();
			if(procesoOK){
				if(numComprobantesConPlanillas<numComprobantes){
					subject= " Ejecución incompleta en proceso " + proceso ;
					body.append("Señor Usuario: se han generado planillas para " + numComprobantesConPlanillas + " comprobantes de un total de " + numComprobantes + ".<BR>");
				}else{
					subject= " Ejecución exitosa en proceso " + proceso ;
					body.append("Señor Usuario: se han generado exitosamente planillas para " + numComprobantes + " comprobantes<BR>");
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

