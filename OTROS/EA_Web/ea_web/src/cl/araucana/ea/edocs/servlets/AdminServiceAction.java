
/*
 * @(#) AdminAction.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
 

package cl.araucana.ea.edocs.servlets;


import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.core.util.security.KES;
import cl.araucana.core.util.security.HostUserACL;
import cl.araucana.ea.edocs.DocumentController;
import cl.araucana.ea.edocs.DocumentType;
import cl.araucana.ea.edocs.DocumentTypes;
import cl.araucana.ea.edocs.IndexControl;
import cl.araucana.ea.edocs.logging.Logger;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> R2T200HAL </TD>
 *            <TD align="center"> </TD>
 *            <TD> </TD>
 *            <TD> </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class AdminServiceAction extends HttpServlet implements Servlet {

	private ServletContext servletContext;
	private final Logger logger = new Logger("AdminServiceAction");

	private DocumentType[] documentTypes = DocumentTypes.documentTypes;
	
	private Map sourceDirs = new HashMap();
	private DocumentController documentController;
	
	private HostUserACL acl;
	private KES.Marshaller marshaller = KES.getMarshaller();
	private String edocsId;
	private String edocsSubject;
	
	public void init() throws ServletException {
		
		servletContext = getServletContext();
		
		logger.log("init");
		
		// Gets source directory for each document type.
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			String documentTypeName = documentType.getName();
			String initParameterName =
					"edocs." + documentTypeName.toLowerCase() + "SourceDir";
			
			String sourceDir =
					servletContext.getInitParameter(initParameterName);
					
			sourceDirs.put(documentTypeName, sourceDir);
			logger.log(
					  "    "
					+ documentTypeName.toLowerCase() + "SourceDir     = "
					+ sourceDir);
		}
		
		documentController = DocumentController.getInstance();

		// KES parameters.
		edocsId = servletContext.getInitParameter("edocs.kes.id");
		edocsSubject = servletContext.getInitParameter("edocs.kes.subject");
		
		String aclParam = servletContext.getInitParameter("edocs.acl");
		
		if (aclParam == null) {
			acl = new HostUserACL("127.0.0.1");
		} else {
			acl = new HostUserACL(aclParam);
		}
		
		logger.log("    admin ACL       = [" + acl.toString() + "]");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.log(e);
		}
	}
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Logs request.
		logger.log("");
		logger.log("new request:");
		
		String remoteAddr = request.getRemoteAddr();
		String kesData = request.getParameter("kes");

		logger.log("    remote address = " + remoteAddr);

		// Prepares output.
		Writer writer = response.getWriter();

		response.setContentType("text/html");
				
		// Gets user principal.
		String[] securityData = KES.getData(kesData, marshaller);
		
		if (securityData == null) {
			writer.write("DKD\n");
			writer.close();
			
			return;
		}
		
		String userName = securityData[0];
		String password = securityData[1];
	
		logger.log("    userName = " + userName);
		
		// Gets edocs subject.
		byte[] key = KES.hexToKey(edocsSubject);
		String subject = KES.getData(key);

		// Checks password. (AUTHENTICATION)	
		if (!subject.equals(password)) {
			writer.close();
			logger.log("    *** INVALID PASSWORD ***");
				
			return;
		}
		
		// Checks ACL. (AUTHORIZATION)
		if (!acl.isAuthorized(remoteAddr, userName)) {
			writer.close();
			logger.log("    *** USER NOT AUTHORIZED ***");
				
			return;
		}
		
		// Parameters validation.		
		String docTypesParam = request.getParameter("docTypes");
		String periodParam = request.getParameter("period");
		String replaceModeParam = request.getParameter("replaceMode");
	
		// Checks mandatory parameters.
		if (docTypesParam == null || periodParam == null
				|| replaceModeParam == null) {
				
			writer.write("STATUS: FAILED\n");
			writer.write(
					  "REASON: "
					+ "'docTypes', 'period' and 'replaceMode' "
					+ "are mandatories\n");
			writer.close();
			
			return;
		}
		
		// general validation flag.
		boolean validationOK = true;
		
		// Document types validation.
		String[] docTypes = docTypesParam.split(":");

		for (int i = 0; i < docTypes.length; i++) {
			try {
				DocumentTypes.getDocumentType(docTypes[i]);
			} catch (IllegalArgumentException e) {
				validationOK = false;
				
				break;
			}
		}

		if (!validationOK) {	
			writer.write("STATUS: FAILED\n");
			writer.write(
					"REASON: Invalid document types '" + docTypesParam + "'\n");
			writer.close();
			
			return;
		}
		
		// period validation.
		int period = 0;
		
		try {
			period = Integer.parseInt(periodParam);
			if (!DocumentController.isValidPeriod(period)) {
				validationOK = false;
			}
		} catch (NumberFormatException e) {
			validationOK = false;
		}

		if (!validationOK) {	
			writer.write("STATUS: FAILED\n");
			writer.write("REASON: Invalid period '" + periodParam + "'\n");
			writer.close();
			
			return;
		}
		
		// replace mode validation.
		boolean replaceMode = Boolean.valueOf(replaceModeParam).booleanValue();

		// optional parameters validation.
		String nFilesParam = request.getParameter("nFiles");
		String nRecordsParam = request.getParameter("nRecords");
		int[] nFiles = new int[docTypes.length];
		int[] nRecords = new int[docTypes.length];

		// nFiles parameter validation.		
		if (nFilesParam != null) {
			String [] nFilesData = nFilesParam.split(":");
			
			if (nFilesData.length != docTypes.length) {
				validationOK = false;
			} else {
				for (int i = 0; i < docTypes.length; i++) {
					try {
						nFiles[i] = Integer.parseInt(nFilesData[i]);
						
						if (nFiles[i] < 0) {
							validationOK = false;
						
							break;
						}
					} catch (NumberFormatException e) {
						validationOK = false;
						
						break;
					}
				}
			}
		}

		if (!validationOK) {
			writer.write("STATUS: FAILED\n");
			writer.write("REASON: Invalid nFiles '" + nFilesParam + "'\n");
			writer.close();
			
			return;
		}

		// nRecords parameter validation.		
		if (nRecordsParam != null) {
			String [] nRecordsData = nRecordsParam.split(":");
			
			if (nRecordsData.length != docTypes.length) {
				validationOK = false;
			} else {
				for (int i = 0; i < docTypes.length; i++) {
					try {
						nRecords[i] = Integer.parseInt(nRecordsData[i]);
						
						if (nRecords[i] < 0) {
							validationOK = false;
						
							break;
						}
					} catch (NumberFormatException e) {
						validationOK = false;
						
						break;
					}
				}
			}
		}

		if (!validationOK) {
			writer.write("STATUS: FAILED\n");
			writer.write("REASON: Invalid nRecords '" + nRecordsParam + "'\n");
			writer.close();
			
			return;
		}
		
		logger.log("");
		logger.log("    parameters:");
		logger.log("        docTypes = " + docTypesParam);
		logger.log("        period = " + periodParam);
		logger.log("        replaceMode = " + replaceModeParam);

		if (nFilesParam != null) {
			logger.log("        nFiles = " + nFilesParam);		
		}

		if (nRecordsParam != null) {
			logger.log("        nRecords = " + nRecordsParam);		
		}
		
		writer.write("STATUS: OK\n");
		
		for (int i = 0; i < docTypes.length; i++) {
			DocumentType documentType =
					DocumentTypes.getDocumentType(docTypes[i]);
			
			String documentTypeName = documentType.getName();
			String sourceDir = (String) sourceDirs.get(documentTypeName);
			IndexControl control = new IndexControl();
		
			control.setNFiles(nFiles[i]);
			control.setNRecords(nRecords[i]);
			 	
			writer.write(
					  "ACTION: index '" + documentTypeName + "' "
					+ "sourceDir=" + sourceDir + " "
					+ "period=" + period + " "
					+ "replaceMode=" + replaceMode + " "
					+ "nFiles=" + nFiles[i] + " "
					+ "nRecords=" + nRecords[i] + "\n");

///*						
				documentController.index(
						documentType, sourceDir, period, replaceMode, control);
//*/						
		}
		
		writer.write("END\n");
		writer.close();		
	}
}
