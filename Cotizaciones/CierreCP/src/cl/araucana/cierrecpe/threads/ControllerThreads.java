

/*
 * @(#) ControllerThreads.java    1.0 30/07/2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.threads.CentralizacionBibliotecaCierreThreads;
import cl.araucana.cierrecpe.empresas.threads.GenerarArchivosImpresionThreads;
import cl.araucana.cierrecpe.empresas.threads.GenerarPlanillasThreads;
import cl.araucana.cierrecpe.empresas.threads.GenerarTesoreriaGeneralThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarArchivosEntidadesThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarChequesThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 30/07/2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZOR�N <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
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
 * @author CLAUDIO LILLO AZOR�N (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ControllerThreads extends Thread {
	private static Logger logger = LogManager.getLogger();
	private int periodo;
	private int cierre;
	private String fechaDeposito, fechaPago, centralizacion;
	private String formapago; 
	private String emails;
	private String optionTE;
	private List secciones;
	
	public ControllerThreads(int periodo, int cierre, String formapago, String fechaDeposito, String fechaPago, String centralizacion, String optionTE, List secciones, String emails){
		logger.finer("Instanciando GenerarPropuestaThreads");
		this.periodo= periodo;
		this.cierre= cierre;
		this.formapago= formapago;
		this.fechaDeposito= fechaDeposito;
		this.fechaPago= fechaPago;
		this.centralizacion= centralizacion;
		this.optionTE= optionTE;
		this.emails= emails;
		this.secciones= new ArrayList(secciones);
	}

	
	public void run(){
		ParametrosCPTO paramTO= Parametros.getInstance().getParam();

		//Invocando Cheques Tesorer�a
		GenerarChequesThreads chequeThreads= new GenerarChequesThreads(periodo, cierre, optionTE, emails);
		paramTO.addProceso("CHEQUE:" + cierre, Today.getFecha_Hora());
		chequeThreads.start();
		try {
			chequeThreads.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(chequeThreads.isProcesoOK()){
			//Invocando Planillas PWF
			GenerarPlanillasThreads planillaThreads= new GenerarPlanillasThreads(periodo, cierre, fechaPago, centralizacion, emails);
			paramTO.addProceso("PLANILLA:" + cierre, Today.getFecha_Hora());
			planillaThreads.start();
			try {

				planillaThreads.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(planillaThreads.isProcesoOK()){
				//Invocando Archivo Entidades
				GenerarArchivosEntidadesThreads archivoThreads= new GenerarArchivosEntidadesThreads(periodo, cierre, formapago, fechaDeposito, secciones, emails);
				paramTO.addProceso("ARCHIVO:" + cierre, Today.getFecha_Hora());
				archivoThreads.start();

				/* No se est� utilizando en 2013
				//Invocando Archivos Impresi�n (ASICOM)
				GenerarArchivosImpresionThreads asicomThreads= new GenerarArchivosImpresionThreads(periodo, cierre, formapago, emails);
				paramTO.addProceso("ASICOM:" + cierre, Today.getFecha_Hora());
				asicomThreads.start();
				*/
				
				/* No se centraliza ya que opci�n se gatilla por v�a de planillas
				//Invocando Centralizaci�n Biblioteca de Cierre
				CentralizacionBibliotecaCierreThreads centralizaThreads= new CentralizacionBibliotecaCierreThreads(periodo, cierre, 0, emails);
				paramTO.addProceso("CENTRALIZAR:" + cierre, Today.getFecha_Hora());
				centralizaThreads.start();
				*/

				//Invocando Tesorer�a General de la Repl�blica
				GenerarTesoreriaGeneralThreads tgrThreads= new GenerarTesoreriaGeneralThreads(periodo, cierre, emails);
				paramTO.addProceso("TGR:" + cierre, Today.getFecha_Hora());
				tgrThreads.start();

				try {
					archivoThreads.join();
					//asicomThreads.join();
					//centralizaThreads.join();
					tgrThreads.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		paramTO.delProceso("CIERRE:" + cierre);
	}
}

