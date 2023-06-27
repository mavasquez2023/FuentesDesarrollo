

/*
 * @(#) DuplicarEsquemaPW.java    1.0 12-11-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;

import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.empresas.dao.EsquemaPublicacionDAO;
import cl.araucana.cierrecpe.dao.PubDAO;
import cl.araucana.core.util.logging.LogManager;

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
 *            <TD> 12-11-2009 </TD>
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
public class DuplicarEsquemaPW implements Constants{
	private PubDAO pubDAO=null;
	private AS400 system=null;
	private static Logger logger = LogManager.getLogger();
	
	public DuplicarEsquemaPW(String ipAS400, String usuarioAS400, String passwordAS400){
		pubDAO= new PubDAO();
		//Se crea sesión en el AS400
		system= new AS400(ipAS400, usuarioAS400, passwordAS400);
	}
	public Collection getComandos(String source, String destination, String option){
		List comandos= new ArrayList();
		String comando;
		for (int i = 0; i < tablasPublicacion.length; i++) {
			comando= "CPYF FROMFILE(" + source + "/" + tablasPublicacion[i] + ") TOFILE("+ destination +"/" + tablasPublicacion[i] + ")  MBROPT(*"+ option +")";
			comandos.add(comando);
		}
		return comandos;
	}
	
	public Collection getComandosDNP(String source, String destination, String option){
		List comandos= new ArrayList();
		String comando;
		for (int i = 0; i < tablasPublicacionDNP_FROM.length; i++) {
			comando= "CPYF FROMFILE(" + source + "/" + tablasPublicacionDNP_FROM[i] + ") TOFILE("+ destination +"/" + tablasPublicacionDNP_TO[i] + ")  MBROPT(*"+ option +")";
			comandos.add(comando);
		}
		return comandos;
	}
	public boolean copyLib(String source, String nueva){
		boolean ejecuta=true;
		String comando;
		try {
			//se abre EsquemaDAO
			EsquemaPublicacionDAO esquemaDAO= new EsquemaPublicacionDAO(pubDAO.getConnection());
			
			if(!esquemaDAO.selectExists(nueva)){
				comando = ("CPYLIB FROMLIB("+ source +") TOLIB("+ nueva +")");
				//Se ejecuta comando AS400
				ejecuta= runCommand(comando);
				logger.fine("Resultado CPYLIB:" + ejecuta);

			}

		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			logger.severe("Resultado CPYLIB:" + e.getMessage());
		}
		return ejecuta;
	}
	
	public boolean existsData(String biblioteca, String tabla){
		boolean result=false;
		try {
			//se abre EsquemaDAO
			EsquemaPublicacionDAO esquemaDAO= new EsquemaPublicacionDAO(pubDAO.getConnection());
			int count= esquemaDAO.selectCount(biblioteca, tabla);
			logger.fine("Resultado ExistsData from " + biblioteca + "." + tabla + ":" + count);
			if(count>0){
				result=true;
			}

		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			logger.severe("Resultado existsData:" + e.getMessage());
		}
		return result;
	}
	
	public boolean dltLib(String source){
		boolean ejecuta=false;
		String comando;
		try {			
			comando = ("DLTLIB LIB("+ source +")");
			//Se ejecuta comando AS400
			ejecuta= runCommand(comando);
			logger.fine("Resultado DLTLIB:" + ejecuta);


		} catch (Exception e) {
			//TODO Bloque catch generado automáticamente
			e.printStackTrace();
			logger.severe("Resultado DLTLIB:" + e.getMessage());
		}
		return ejecuta;
	}
	
	public boolean copyFiles(String source, String destination, String option, String tipo){
		boolean ejecuta=false;
		String comando;
		try {			
			//Se genera comandos a ejecutar para respaldar tablas.
			if(tipo.equals("CP")){
				for (Iterator iter = getComandos(source, destination, option).iterator(); iter.hasNext();) {
					comando = (String) iter.next();
					//Se ejecuta comando AS400
					runCommand(comando);
				}
			}else if(tipo.equals("DNP")){
				for (Iterator iter = getComandosDNP(source, destination, option).iterator(); iter.hasNext();) {
					comando = (String) iter.next();
					//Se ejecuta comando AS400
					runCommand(comando);
				}
			}
			ejecuta= true;

		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return ejecuta;
	}
	
	public boolean clearLib(String source) throws SQLException{
		boolean ejecuta=false;
		int y=0;
		String comando="";
		for (int i = 0; i < tablasPublicacionCLEAN.length; i++) {
			comando= "CLRPFM FILE(" + source + "/" + tablasPublicacionCLEAN[i] + ")";
			logger.fine("comando AS400:" + comando);
			runCommand(comando);
		}
		ejecuta=true;
		//comando = ("CALL PGM(PWOBJD/DELPWF) PARM('" + source + "')");
		return ejecuta;
	}
	
	public boolean runCommand(String comando){
		boolean ejecuta=false;
		try {
			CommandCall cmd = new CommandCall(system);
			String ssID= cmd.getServerJob().getNumber();
			//Se ejecuta comando
			logger.fine("comando AS400:" + comando);
			ejecuta= cmd.run(comando);
			//Se obtiene lista de mensajes de la ejecución del comando
			AS400Message[] messageList = cmd.getMessageList();
			for (int i = 0; i < messageList.length; i++) {
				logger.fine("mensaje:" + messageList[i].getText());
			}
			
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			logger.severe(e.getMessage());
		}
		return ejecuta;
	}
	
	public void close(){
		pubDAO.closeConnectionDAO();
		system.disconnectAllServices();
	 }
}

