

/*
 * @(#) ParametrosCPTO.java    1.0 21-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.to;


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
 *            <TD> 21-08-2009 </TD>
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
import java.util.HashMap;
import java.util.Map;

import cl.araucana.cierrecpe.empresas.threads.CentralizacionBibliotecaCierreThreads;
import cl.araucana.cierrecpe.empresas.threads.GenerarArchivosImpresionThreads;
import cl.araucana.cierrecpe.empresas.threads.GenerarTesoreriaGeneralThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarArchivosEntidadesThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarChequesThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarInformeContableThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarPropuestaThreads;
import cl.araucana.core.util.AbsoluteDate;

public class ParametrosCPTO{
	private int periodoSistema;
	private AbsoluteDate fechaCierre;
	private int periodoSistemaIndependiente;
	private AbsoluteDate fechaCierreIndependiente;
	private String emailUsuario;
	private Map procesosActivos;
	private Map threads;
	private String codigoBarra;
	private String urlPDFServices;
	private AbsoluteDate fechaApertura;
	
	public ParametrosCPTO(){
		procesosActivos= new HashMap();
		threads= new HashMap();
	}

	public int getPeriodoSistema(){
		return periodoSistema;
	}

	public void setPeriodoSistema(int periodoSistema){
		this.periodoSistema = periodoSistema;
	}

	public AbsoluteDate getFechaCierre(){
		return fechaCierre;
	}

	public void setFechaCierre(AbsoluteDate fechaCierre){
		this.fechaCierre = fechaCierre;
	}

	public int getCompareFechaCierre_Today()
	    {
	        AbsoluteDate today = new AbsoluteDate();
	        int dif = fechaCierre.compareTo(today);
	        return dif;
	}

	/**
	 * @return el emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @param emailUsuario el emailUsuario a establecer
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	/**
	 * @return el procesosActivos
	 */
	public Map getProcesosActivos() {
		return procesosActivos;
	}

	/**
	 * @param procesosActivos el procesosActivos a establecer
	 */
	public void setProcesosActivos(Map procesosActivos) {
		this.procesosActivos = procesosActivos;
	}

	public void addProceso(String proceso, String observacion){
		this.procesosActivos.put(proceso, observacion);
	}
	
	public void delProceso(String proceso){
		this.procesosActivos.remove(proceso);
	}

	/**
	 * @return el threads
	 */
	public Map getThreads() {
		return threads;
	}

	/**
	 * @param threads el threads a establecer
	 */
	public void setThreads(Map threads) {
		this.threads = threads;
	}
	
	public void addThread(String proceso, Thread thread){
		this.threads.put(proceso, thread);
	}
	
	public void delThread(String clave){
		this.threads.remove(clave);
	}
	
	public void killThread(String clave){

		try {
			String proceso= clave.substring(0, clave.indexOf(":"));
			if(proceso.equals("ASICOM")){
				GenerarArchivosImpresionThreads thread = (GenerarArchivosImpresionThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("CENTRALIZAR")){
				CentralizacionBibliotecaCierreThreads thread = (CentralizacionBibliotecaCierreThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("TGR")){
				GenerarTesoreriaGeneralThreads thread = (GenerarTesoreriaGeneralThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("ARCHIVO")){
				GenerarArchivosEntidadesThreads thread = (GenerarArchivosEntidadesThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("CHEQUE")){
				GenerarChequesThreads thread = (GenerarChequesThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("INFORME")){
				GenerarInformeContableThreads thread = (GenerarInformeContableThreads) this.threads.get(clave);
				thread.destroy();
			}
			if(proceso.equals("PROPUESTA")){
				GenerarPropuestaThreads thread = (GenerarPropuestaThreads) this.threads.get(clave);
				thread.destroy();
			}

			this.threads.remove(clave);
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/**
	 * @return el codigoBarra
	 */
	public String getCodigoBarra() {
		return codigoBarra;
	}

	/**
	 * @param codigoBarra el codigoBarra a establecer
	 */
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	/**
	 * @return the urlPDFServices
	 */
	public String getUrlPDFServices() {
		return urlPDFServices;
	}

	/**
	 * @param urlPDFServices the urlPDFServices to set
	 */
	public void setUrlPDFServices(String urlPDFServices) {
		this.urlPDFServices = urlPDFServices;
	}

	/**
	 * @return el fechaCierreIndependiente
	 */
	public AbsoluteDate getFechaCierreIndependiente() {
		return fechaCierreIndependiente;
	}

	/**
	 * @param fechaCierreIndependiente el fechaCierreIndependiente a establecer
	 */
	public void setFechaCierreIndependiente(AbsoluteDate fechaCierreIndependiente) {
		this.fechaCierreIndependiente = fechaCierreIndependiente;
	}

	/**
	 * @return el periodoSistemaIndependiente
	 */
	public int getPeriodoSistemaIndependiente() {
		return periodoSistemaIndependiente;
	}

	/**
	 * @param periodoSistemaIndependiente el periodoSistemaIndependiente a establecer
	 */
	public void setPeriodoSistemaIndependiente(int periodoSistemaIndependiente) {
		this.periodoSistemaIndependiente = periodoSistemaIndependiente;
	}

	/**
	 * @return el fechaApertura
	 */
	public AbsoluteDate getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * @param fechaApertura el fechaApertura a establecer
	 */
	public void setFechaApertura(AbsoluteDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
}

